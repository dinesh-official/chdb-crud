package com.devng.chdb_crud.crud;

import com.devng.chdb_crud.utility.Ch;
import com.devng.chdb_crud.utility.Query;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Create {

    public static void main(String[] args) {

        try (Connection conn = DriverManager.getConnection(Ch.getUrl(), Ch.getUser(), Ch.getPassword());
             Statement stmt = conn.createStatement()) {

            stmt.execute(Query.CREATE_HUNTER_MAIL_TABLE);
            System.out.println("Table created or already exists.");

            // 2. Prepare date-time in ClickHouse format (yyyy-MM-dd HH:mm:ss)
            String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            // 3. Insert sample data
            String insertSQL = String.format(
                "INSERT INTO hunter (id, ip, count, email, created_at) VALUES " +
                "(1, '192.168.1.1', 3, 'user1@example.com', '%s'), " +
                "(2, '10.0.0.5', 5, 'user2@example.com', '%s'), " +
                "",
                now, now, now
            );

            stmt.execute(insertSQL);
            System.out.println("Data inserted.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
