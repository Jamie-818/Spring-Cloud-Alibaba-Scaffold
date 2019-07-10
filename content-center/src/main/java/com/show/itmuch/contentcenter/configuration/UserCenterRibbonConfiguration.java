package com.show.itmuch.contentcenter.configuration;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;
import ribbonconfiguration.RibbonConfiguration;

@Configuration
@RibbonClient(name = "user", configuration = RibbonConfiguration.class)
public class UserCenterRibbonConfiguration {
}
