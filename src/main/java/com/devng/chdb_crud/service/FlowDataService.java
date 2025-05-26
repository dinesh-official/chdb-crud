package com.devng.chdb_crud.service;

import com.devng.chdb_crud.model.FlowData;
import com.devng.chdb_crud.utility.Ch;
import com.devng.chdb_crud.utility.Query;
import com.devng.chdb_crud.utility.Ssh;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class FlowDataService {

    public List<FlowData> getFlowData(int ipDstPort, int dstAsn, int intervalHour, int flowCountThreshold, int maxResults) {
        List<FlowData> results = new ArrayList<>();

        // Use Query to get query dynamically - pass parameters here
        String query = Query.getPassword(ipDstPort, dstAsn, intervalHour, flowCountThreshold);

        try (Connection conn = DriverManager.getConnection(Ch.getUrl(), Ch.getUser(), Ch.getPassword());
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String ip = rs.getString("src_ip");
                long count = rs.getLong("flow_count");
                // Filter IPs that support password-based SSH authentication
                if (Ssh.supportsPasswordAuth(ip)) {
                    results.add(new FlowData(ip, count));
                    if (results.size() >= maxResults) {  // use maxResults dynamically
                        break;
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return results;
    }
}
