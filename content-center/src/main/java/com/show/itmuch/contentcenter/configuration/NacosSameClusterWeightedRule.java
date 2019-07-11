package com.show.itmuch.contentcenter.configuration;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.client.naming.core.Balancer;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.alibaba.nacos.NacosDiscoveryProperties;
import org.springframework.cloud.alibaba.nacos.ribbon.NacosServer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 同一集群优先调用
 *
 * @author xuanweiyao
 * @date 11:07 2019/7/11
 */
@Slf4j
public class NacosSameClusterWeightedRule extends AbstractLoadBalancerRule {
  @Autowired private NacosDiscoveryProperties nacosDiscoveryProperties;

  @Override
  public void initWithNiwsConfig(IClientConfig clientConfig) {}
  /**
   * 1. 找到指定服务的所有实例 A<br>
   * 2. 过滤出相同集群下的所有实例 B<br>
   * 3. 如果B是空，就用A<br>
   * 4. 基于权重的负载均衡算法，返回一个实例<br>
   *
   * @author xuanweiyao
   * @date 11:08 2019/7/11
   * @return com.netflix.loadbalancer.Server
   */
  @Override
  public Server choose(Object key) {
    try {
      // 拿到配置文件中集群名称 NJ
      //  spring.cloud.nacos.discovery.cluster-name: NJ
      String clusterName = nacosDiscoveryProperties.getClusterName();
      BaseLoadBalancer loadBalancer = (BaseLoadBalancer) this.getLoadBalancer();
      log.info("lb = {}", loadBalancer);
      // 想要请求的微服务的名称
      String name = loadBalancer.getName();
      // 拿到服务发现的相关API
      NamingService namingService = nacosDiscoveryProperties.namingServiceInstance();
      // 1. 找到指定服务的所有实例 A<br> turn :只拿健康的实例
      List<Instance> instances = namingService.selectInstances(name, true);
      // 2. 过滤出相同集群下的所有实例 B<br>
      List<Instance> sameClusterInstances =
          instances.stream()
              .filter(instance -> Objects.equals(instance.getClusterName(), clusterName))
              .collect(Collectors.toList());
      // 3. 如果B是空，就用A<br>
      List<Instance> instancesToBeChosen = new ArrayList<>();
      if (sameClusterInstances.isEmpty()) {
        instancesToBeChosen = instances;
        log.warn(
            "发现跨集群的调用， name = {}，clusterName = {}, instances = {}", name, clusterName, instances);
      } else {
        instancesToBeChosen = sameClusterInstances;
      }
      // 4. 基于权重的负载均衡算法，返回一个实例<br>
      Instance instance = ExtendBalancer.getHostByRandomWeight2(instancesToBeChosen);
      log.info("选择的实例是 port = {}， instance = {}", instance.getPort(), instance);
      return new NacosServer(instance);
    } catch (NacosException e) {
      log.error("发生异常了", e);
      return null;
    }
  }
}
/**
 * 因为nacos暂时没有api是可以实现自己传入instances进行权重分配<br>
 * 所以我们可以追踪 namingService.selectOneHealthyInstance() 源码查看底层如何进行权重分配<br>
 * 源码的方法是 protected 权限的，我们可以新建一个类继承它的父类实现调用。<br>
 * 源码地址：com.alibaba.nacos.client.naming.core.Balancer#getHostByRandomWeight(java.util.List)<br>
 *
 * @author xuanweiyao
 * @date 11:40 2019/7/11
 */
class ExtendBalancer extends Balancer {
  static Instance getHostByRandomWeight2(List<Instance> hosts) {

    return getHostByRandomWeight(hosts);
  }
}
