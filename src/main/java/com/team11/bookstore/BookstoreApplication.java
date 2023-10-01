package com.team11.bookstore;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookstoreApplication {
    private final String dbUsername;
    private final String dbPassword;

    public BookstoreApplication() {
        Dotenv dotenv = Dotenv.load();
        this.dbUsername = dotenv.get("DB_USERNAME");
        this.dbPassword = dotenv.get("DB_PASSWORD");
    }

    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }

}
