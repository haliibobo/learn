package com.github.haliibobo.learn.design.patterns.creational.builder;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-06-18 11:38
 * @description describe what this class do
 */
public class DruidSource extends DruidDataSource {


    private DruidSource(Builder builder) {

        if(builder.getUsername() != null){
            setUsername(builder.getUsername());
        }
        if(builder.getDriverClassName() != null){
            setDriverClassName(builder.getDriverClassName());
        }
        if(builder.getInitialSize() != null){
            setInitialSize(builder.getInitialSize());
        }
        if(builder.getMaxActive() != null){
            setMaxActive(builder.getMaxActive());
        }
        if(builder.getMaxPoolPreparedStatementPerConnectionSize() != null){
            setMaxPoolPreparedStatementPerConnectionSize(builder.getMaxPoolPreparedStatementPerConnectionSize());
        }
        if(builder.getMaxWait() != null){
            setMaxWait(builder.getMaxWait());
        }
        if(builder.getMinIdle() != null){
            setMinIdle(builder.getMinIdle());
        }
        if(builder.getName() != null){
            setName(builder.getName());
        }
        if(builder.getPassword() != null){
            setPassword(builder.getPassword());
        }
        if(builder.getTestWhileIdle() != null){
            setTestWhileIdle(builder.getTestWhileIdle());
        }
        if(builder.getValidationQuery() != null){
            setValidationQuery(builder.getValidationQuery());
        }
        if(builder.getUrl() != null){
            setUrl(builder.getUrl());
        }
        if(builder.getTransactionThresholdMillis() != null){
            setTransactionQueryTimeout(builder.getTransactionThresholdMillis());
        }



    }
    private DruidSource(){

    }

    public static Builder builder()
    {
        return new Builder();
    }
    public static class Builder {
        private String name;
        private String driverClassName;
        private String url;
        private String username;
        private String password;
        private String validationQuery;
        private Integer maxActive;
        private Integer minIdle;
        private Integer initialSize;
        private Integer timeBetweenEvictionRunsMillis;
        private Integer maxWait;
        private Boolean testWhileIdle;
        private Integer maxPoolPreparedStatementPerConnectionSize;
        private Integer transactionThresholdMillis;

        private Builder() {

        }

        public String getName() {
            return name;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public String getDriverClassName() {
            return driverClassName;
        }

        public Builder driver(String driverClassName) {
            this.driverClassName = driverClassName;
            return this;
        }

        public String getUrl() {
            return url;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public String getUsername() {
            return username;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public String getPassword() {
            return password;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public String getValidationQuery() {
            return validationQuery;
        }

        public Builder validationQuery(String validationQuery) {
            this.validationQuery = validationQuery;
            return this;
        }

        public Integer getMaxActive() {
            return maxActive;
        }

        public Builder maxActive(Integer maxActive) {
            this.maxActive = maxActive;
            return this;
        }

        public Integer getMinIdle() {
            return minIdle;
        }

        public Builder minIdle(Integer minIdle) {
            this.minIdle = minIdle;
            return this;
        }

        public Integer getInitialSize() {
            return initialSize;
        }

        public Builder initialSize(Integer initialSize) {
            this.initialSize = initialSize;
            return this;
        }

        public Integer getTimeBetweenEvictionRunsMillis() {
            return timeBetweenEvictionRunsMillis;
        }

        public Builder timeBetweenEvictionRunsMillis(Integer timeBetweenEvictionRunsMillis) {
            this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
            return this;
        }

        public Integer getMaxWait() {
            return maxWait;
        }

        public Builder maxWait(Integer maxWait) {
            this.maxWait = maxWait;
            return this;
        }

        public Boolean getTestWhileIdle() {
            return testWhileIdle;
        }

        public Builder testWhileIdle(Boolean testWhileIdle) {
            this.testWhileIdle = testWhileIdle;
            return this;
        }

        public Integer getMaxPoolPreparedStatementPerConnectionSize() {
            return maxPoolPreparedStatementPerConnectionSize;
        }

        public Builder maxPoolPreparedStatementPerConnectionSize(
            Integer maxPoolPreparedStatementPerConnectionSize) {
            this.maxPoolPreparedStatementPerConnectionSize = maxPoolPreparedStatementPerConnectionSize;
            return this;
        }

        public Integer getTransactionThresholdMillis() {
            return transactionThresholdMillis;
        }

        public Builder transactionThresholdMillis(Integer transactionThresholdMillis) {
            this.transactionThresholdMillis = transactionThresholdMillis;
            return this;
        }

        public DruidSource build()
        {
            return new DruidSource(this);
        }

    }
}
