package kh.com.foss.sample.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EntityScan("kh.com.foss.sample.entity")
@EnableJpaRepositories("kh.com.foss.sample.dao")
public class RepositoryConfiguration {}
