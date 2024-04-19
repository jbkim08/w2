package org.zerock.w2.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;

//클래스 대신 enum 을 사용해 하나의(싱글턴) 객체만 생성하여 사용함
public enum ConnectionUtil {
    INSTANCE; //자동으로 1개만 생성됨

    //커넥션 풀 객체 ds 선언
    private HikariDataSource ds;
    //커넥션 풀 생성
    ConnectionUtil(){
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.mariadb.jdbc.Driver");
        config.setJdbcUrl("jdbc:mariadb://localhost:3306/webdb");
        config.setUsername("webuser");
        config.setPassword("1234");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        ds = new HikariDataSource(config);
    }
    //ds 객체를 가져오는 메소드
    public Connection getConnection() throws Exception {
        return ds.getConnection();
    }
}
