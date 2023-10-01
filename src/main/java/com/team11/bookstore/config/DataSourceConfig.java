package com.team11.bookstore.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    @Bean
    public DataSource dataSource() {
        Dotenv dotenv = Dotenv.load(); // loads the environment variables from .env file (must be created in the root directory)

        String username = dotenv.get("DB_USERNAME");
        String password = dotenv.get("DB_PASSWORD");

        String url = "jdbc:postgresql://team11.cnusvsjowtcz.us-east-1.rds.amazonaws.com:5432/bookstore"; // db connection URI

        return DataSourceBuilder.create()
                .url(url)
                .username(username)
                .password(password)
                .driverClassName("org.postgresql.Driver")
                .build();
    }
}
