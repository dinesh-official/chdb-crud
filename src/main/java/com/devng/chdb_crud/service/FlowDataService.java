package com.devng.chdb_crud.service;

import com.devng.chdb_crud.model.FlowData;
import com.devng.chdb_crud.utility.Query;
import com.devng.chdb_crud.utility.SSHUtil;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class FlowDataService {
    private final String url = "jdbc:clickhouse://216.48.176.80:8123/default";
    private final String user = "default";
    private final String password = "";

    public List<FlowData> getFlowData() {
        List<FlowData> results = new ArrayList<>();

        // Use Query to get query dynamically - you can pass parameters here
        String query = Query.getPassword(22, 132420, 60, 30);

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String ip = rs.getString("src_ip");
                long count = rs.getLong("flow_count");
                // Filter IPs that support password-based SSH authentication
                if (SSHUtil.supportsPasswordAuth(ip)) {
                    results.add(new FlowData(ip, count));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return results;
    }
}
