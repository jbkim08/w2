package org.zerock.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectTests {

//    @Test
//    public void test1(){
//        int v1 = 10;
//        int v2 = 10;
//
//        Assertions.assertEquals(v1,v2);
//    }
    @Test
    public void testConnection() throws Exception {
        //드라이버 확인
        Class.forName("org.mariadb.jdbc.Driver");
        // 실제 DB 연결객체 생성
        Connection connection = DriverManager.getConnection(
                "jdbc:mariadb://localhost:3306/webdb",
                "webuser",
                "1234");

        Assertions.assertNotNull(connection); //연결객체가 널이면 불합격

        connection.close(); //객체 닫기
    }

    @Test
    public void testHikariCP() throws Exception {

        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.mariadb.jdbc.Driver");
        config.setJdbcUrl("jdbc:mariadb://localhost:3306/webdb");
        config.setUsername("webuser");
        config.setPassword("1234");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        HikariDataSource ds = new HikariDataSource(config);
        Connection connection = ds.getConnection();

        System.out.println(connection);
        connection.close();
    }
}
