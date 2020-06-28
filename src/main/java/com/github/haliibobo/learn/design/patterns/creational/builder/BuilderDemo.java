package com.github.haliibobo.learn.design.patterns.creational.builder;

import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import org.junit.Test;

/**
 * say something.
 *
 * @author lizibo
 * @version 1.0
 * @date 2020-06-18 15:58
 * @description describe what this class do
 */
public class BuilderDemo {


    @Test
    public void test() throws SQLException {
        DataSource druidSource = DruidSource.builder()
            .username("halibobo")
            .url("jdbc:mysql://halibobo.cn:3306/wordpress?serverTimezone=Asia/Chongqing&useUnicode=true&characterEncoding=utf8&characterSetResults=utf8&useSSL=true&verifyServerCertificate=false&autoReconnct=true&autoReconnectForPools=true&allowMultiQueries=true")
            .driver("com.mysql.cj.jdbc.Driver")
            .password("DDC7wq#v&Y4B")
            .name("test").build();
        Statement statement = druidSource.getConnection().createStatement();
        statement.execute("select 1;");
        statement.getResultSet();
    }

}
