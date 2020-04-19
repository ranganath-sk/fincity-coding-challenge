package com.example.fincity.coding.challenge.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Ranganatha S K
 *
 */
@Component
@NoArgsConstructor
@Getter
public class AppConfig {

  @Value("${spring.datasource.url}")
  private String springDatasourceUrl;

  @Value("${spring.datasource.username}")
  private String springDatasourceUsername;

  @Value("${spring.datasource.password}")
  private String springDatasourcePassword;

  @Value("${spring.session.timeout}")
  private String springSessionTimeout;
}
