package com.github.haliibobo.learn.druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.google.common.base.Strings;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;


/**
 *
 * @author lizibo
 *create user 'halibobo'@'%' identified by 'DDC7wq#v&Y4B'
 */
public class DataSourceService {
    private static Log log = LogFactory.getLog(DataSourceService.class);
    private DataSource dataSource;


    public static DataSource fetchDataSource(Map<String, String> map) {
        DruidDataSource druid = new DruidDataSource();
        try {
            // 1.名称
            druid.setName(map.get("code"));
            // 2.驱动
            String driverClassName = map.get("driver");
            druid.setDriverClassName(driverClassName);
            // 3.url
            druid.setUrl(map.get("url"));
            // 4.用户名
            druid.setUsername(map.get("user"));
            if(!Strings.isNullOrEmpty(map.get("pw"))) {
                // 5.密码
                druid.setPassword(map.get("pw"));
            }
            // 6.验证测试sql
            String preferredTestQuery = map.get("preferredTestQuery");

            if (Strings.isNullOrEmpty(preferredTestQuery)) {
                    preferredTestQuery = "SELECT 1";
            }
            if (!Strings.isNullOrEmpty(preferredTestQuery)) {
                druid.setValidationQuery(preferredTestQuery);
            }
            if (!Strings.isNullOrEmpty(map.get("minPoolSize"))) {
                // 7.最小连接数
                int minPoolSize = Integer.parseInt(map.get("minPoolSize"));
                druid.setMinIdle(minPoolSize);
            }
            if (!Strings.isNullOrEmpty(map.get("maxPoolSize"))) {
                // 8.最大连接数
                int maxPoolSize = Integer.parseInt(map.get("maxPoolSize"));
                druid.setMaxActive(maxPoolSize);
            }
            if (!Strings.isNullOrEmpty(map.get("initialPoolSize"))) {
                // 9.初始连接数
                int initialPoolSize = Integer.parseInt(map.get("initialPoolSize"));
                druid.setInitialSize(initialPoolSize);
            }

            if (!Strings.isNullOrEmpty(map.get("idleConnectionTestPeriod"))) {
                // 10.有两个含义：
                //1) Destroy线程会检测连接的间隔时间，如果连接空闲时间大于等于minEvictableIdleTimeMillis则关闭物理连接。
                //2) testWhileIdle的判断依据，详细看testWhileIdle属性的说明
                int idleConnectionTestPeriod = Integer.parseInt(map.get("idleConnectionTestPeriod"));
                druid.setTimeBetweenEvictionRunsMillis(idleConnectionTestPeriod);
            }
            if (!Strings.isNullOrEmpty(map.get("checkoutTimeout"))) {
                // 11.获取新连接等待时间（毫秒）
                int checkoutTimeout = Integer.parseInt(map.get("checkoutTimeout"));
                druid.setMaxWait(checkoutTimeout);
            }
            // 12.建议配置为true，不影响性能，并且保证安全性。申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。
            druid.setTestWhileIdle(true);

            if (!Strings.isNullOrEmpty(map.get("maxStatementsPerConnection"))) {
                // 13.要启用PSCache，必须配置大于0，当大于0时，poolPreparedStatements自动触发修改为true。在Druid中，不会存在Oracle下PSCache占用内存过多的问题，可以把这个数值配置大一些，比如说100
                int maxStatementsPerConnection = Integer
                    .parseInt(map.get("maxStatementsPerConnection"));
                druid.setMaxPoolPreparedStatementPerConnectionSize(maxStatementsPerConnection);
            }
            //13.事务超时时间
            if (!Strings.isNullOrEmpty(map.get("transactionThresholdMillis"))) {
                long transactionThresholdMillis = Long
                    .parseLong(map.get("transactionThresholdMillis"));
                druid.setTransactionThresholdMillis(transactionThresholdMillis);
            }

            // 14.配置监控
            //druid.addFilters("stat");
            //druid.addFilters("wall");

            return druid;
        } catch (Exception e) {
            log.error("DataSourceService.createDataSource:" + e, e);
        }
    return null;
    }

    @Before
    public void setSup (){
        Map<String,String> parameters = new HashMap<>();
        parameters.put("code","halibobo");
        parameters.put("driver","com.mysql.cj.jdbc.Driver");
        parameters.put("url","jdbc:mysql://halibobo.cn:3306/wordpress?serverTimezone=Asia/Chongqing&useUnicode=true&characterEncoding=utf8&characterSetResults=utf8&useSSL=true&verifyServerCertificate=false&autoReconnct=true&autoReconnectForPools=true&allowMultiQueries=true");
        parameters.put("user","halibobo");
        parameters.put("pw","DDC7wq#v&Y4B");
        dataSource =DataSourceService.fetchDataSource(parameters);
    }

    @Test
    public void test() throws SQLException {
        System.out.println(dataSource.getConnection().getClientInfo());
    }
}