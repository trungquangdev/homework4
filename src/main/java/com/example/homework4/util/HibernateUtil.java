package com.example.homework4.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil {

    private static final SessionFactory FACTORY;

    static {
        Configuration configuration = new Configuration();

        Properties properties = new Properties();

        // Cấu hình Hibernate sử dụng SQL Server
        properties.put(
                Environment.DIALECT,
                "org.hibernate.dialect.SQLServer2016Dialect"
        );

        // JDBC Driver của SQL Server
        properties.put(
                Environment.DRIVER,
                "com.microsoft.sqlserver.jdbc.SQLServerDriver"
        );

        // Database cần kết nối
        properties.put(
                Environment.URL,
                "jdbc:sqlserver://localhost:1433;"
                        + "databaseName=BTVNBuoi7;"
                        + "encrypt=true;"
                        + "trustServerCertificate=true;"
        );

        // Tài khoản SQL Server
        properties.put(Environment.USER, "sa");

        // Mật khẩu SQL Server
        properties.put(Environment.PASS, "123");

        // In SQL Hibernate thực thi ra Console
        properties.put(Environment.SHOW_SQL, "true");

        // Đưa cấu hình vào Hibernate
        configuration.setProperties(properties);

        // Tạo ServiceRegistry
        ServiceRegistry serviceRegistry =
                new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties())
                        .build();

        // Tạo SessionFactory
        FACTORY = configuration.buildSessionFactory(serviceRegistry);
    }

    public static SessionFactory getFACTORY() {
        return FACTORY;
    }

    public static void main(String[] args) {
        System.out.println(getFACTORY());
    }
}