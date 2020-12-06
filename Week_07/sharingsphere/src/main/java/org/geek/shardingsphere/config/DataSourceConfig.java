package org.geek.shardingsphere.config;

import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.infra.config.algorithm.ShardingSphereAlgorithmConfiguration;
import org.apache.shardingsphere.replicaquery.api.config.ReplicaQueryRuleConfiguration;
import org.apache.shardingsphere.replicaquery.api.config.rule.ReplicaQueryDataSourceRuleConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;

@Configuration
public class DataSourceConfig {

    @Bean(name = "primaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "secondary01DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.secondary01")
    public DataSource secondary01DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "secondary02DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.secondary02")
    public DataSource secondary02DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public DataSource dataSource(DataSource primaryDataSource, DataSource secondary01DataSource, DataSource secondary02DataSource) throws SQLException {
        Map<String, DataSource> dataSourceMap = new HashMap<>();
        dataSourceMap.put("primary", primaryDataSource);
        dataSourceMap.put("secondary01", secondary01DataSource);
        dataSourceMap.put("secondary02", secondary02DataSource);
        // 读写分离规则
        List<ReplicaQueryDataSourceRuleConfiguration> configurations = new ArrayList<>();
        configurations.add(new ReplicaQueryDataSourceRuleConfiguration("ds", "primary", Arrays.asList("secondary01", "secondary02"), "load_balancer"));
        // 从库负载均衡算法
        Map<String, ShardingSphereAlgorithmConfiguration> loadBalancer = new HashMap<>();
        loadBalancer.put("load_balancer", new ShardingSphereAlgorithmConfiguration("ROUND_ROBIN", new Properties()));
        ReplicaQueryRuleConfiguration ruleConfiguration = new ReplicaQueryRuleConfiguration(configurations, loadBalancer);
        return ShardingSphereDataSourceFactory.createDataSource(dataSourceMap, Collections.singletonList(ruleConfiguration), new Properties());
    }

}
