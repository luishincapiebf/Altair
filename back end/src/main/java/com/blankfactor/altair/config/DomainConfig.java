package com.blankfactor.altair.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("com.blankfactor.altair")
@EnableJpaRepositories("com.blankfactor.altair")
@EnableTransactionManagement
public class DomainConfig {
}
