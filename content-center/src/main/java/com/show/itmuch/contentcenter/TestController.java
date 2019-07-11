package com.show.itmuch.contentcenter;

import com.show.itmuch.contentcenter.dao.content.ShareMapper;
import com.show.itmuch.contentcenter.domain.entity.content.Share;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TestController {
  private final ShareMapper shareMapper;
  private final DiscoveryClient discoveryClient;

  @GetMapping("test")
  public List<Share> testInsert() {
    // 1.做插入
    Share share = new Share();
    share.setCreateTime(new Date());
    share.setUpdateTime(new Date());
    share.setTitle("XXX");
    share.setCover("XXX");
    share.setAuthor("SHOW");
    share.setBuyCount(1);
    shareMapper.insertSelective(share);
    // 2.做查询：查询当前数据库所有的 select * from share;
    List<Share> shares = shareMapper.selectAll();
    return shares;
  }

  /**
   * 测试：服务发现，证明内容中心总能找到用户中心
   *
   * @author xuanweiyao
   * @date 15:33 2019/7/10
   * @return 用户中心所有实例的地址信息
   */
  @GetMapping("/discoveryTest")
  public List<ServiceInstance> getDiscovery() {
    // 查询指定服务的所有信息
    // consul/eureka/zookeeper..也可以调该API
    return this.discoveryClient.getInstances("user");
  }
}
