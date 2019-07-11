package ribbonconfiguration;

import com.netflix.loadbalancer.IRule;
import com.show.itmuch.contentcenter.configuration.NacosSameClusterWeightedRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义Ribbon负载均衡规则
 *
 * @author xuanweiyao
 * @date 10:11 2019/7/11
 */
@Configuration
public class RibbonConfiguration {
  /**
   * 使用自定义的负载均衡算法
   *
   * @author xuanweiyao
   * @date 10:10 2019/7/11
   * @return com.netflix.loadbalancer.IRule
   */
  @Bean
  public IRule ribbonRule() {
    return new NacosSameClusterWeightedRule();
  }
}
