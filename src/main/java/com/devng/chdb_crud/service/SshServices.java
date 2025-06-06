package com.devng.chdb_crud.service;

import com.devng.chdb_crud.model.FlowData;
import com.devng.chdb_crud.utility.Config;
import com.devng.chdb_crud.utility.Query;
import com.devng.chdb_crud.utility.Ssh;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SshServices{
    private final Config db;

    public SshServices(Config db) {
        this.db = db;
    }

    Set<String> noPass = new HashSet<>();
    public List<FlowData> getSsh(int ipDstPort, int dstAsn, int intervalHour, int flowCountThreshold, int maxResults, int noPasswordFlag) {
        List<FlowData> results = new ArrayList<>();

        String query = Query.getPassword(ipDstPort, dstAsn, intervalHour, flowCountThreshold);

        try (Connection conn = DriverManager.getConnection(db.getClickhouseUrl(),db.getClickhouseUsername(),db.getClickhousePassword());
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String ip = rs.getString("dst_ip");
                long count = rs.getLong("flow_count");

                if (noPasswordFlag == 2) {
                    results.add(new FlowData(ip, count));
                    if (results.size() >= maxResults) {
                        break;
                    }
                    continue;
                }

                if (noPass.contains(ip)) {
                    if (noPasswordFlag == 1) {
                        results.add(new FlowData(ip, count));
                    }
                    continue;
                }

                if (Ssh.supportsPasswordAuth(ip)) {
                    if (noPasswordFlag == 0) {
                        results.add(new FlowData(ip, count));
                    }
                } else {
                    noPass.add(ip);
                    if (noPasswordFlag == 1) {
                        results.add(new FlowData(ip, count));
                    }
                }

                if (results.size() >= maxResults) {
                    break;
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return results;
    }

}
