package com.devng.chdb_crud.dao;

import com.devng.chdb_crud.model.Mail;

import com.devng.chdb_crud.utility.Config;
import com.devng.chdb_crud.utility.Query;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MailDao {

    private final Config db;

    public MailDao(Config db) {
        this.db = db;
    }

    public List<Mail> fetchMailRecords(String vmId, String vmOwner, String vmIp, String vmName, String mailType, int lastDays) {
        List<Mail> results = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(db.getMysqlUrl(), db.getMysqlUsername(), db.getMysqlPassword());
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(Query.getMail(lastDays, vmId, vmOwner, vmIp, vmName, mailType))) {

            while (rs.next()) {
                Mail record = new Mail();

                record.setId(rs.getLong("id"));
                record.setCreatedAd(rs.getTimestamp("createdAd").toLocalDateTime());
                record.setVmId(rs.getString("vmId"));
                record.setVmName(rs.getString("vmName"));
                record.setVmIp(rs.getString("vmIp"));
                record.setVmOwner(rs.getString("vmOwner"));
                record.setMailType(rs.getString("mailType"));

                results.add(record);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return results;
    }

    public boolean insertMailRecord(String vmId, String vmName, String vmIp, String vmOwner, String mailType) {
        String sql = Query.insertMail(vmId, vmName, vmIp, vmOwner, mailType);

        try (Connection conn = DriverManager.getConnection(db.getMysqlUrl(), db.getMysqlUsername(), db.getMysqlPassword());
             Statement stmt = conn.createStatement()) {

            int rowsAffected = stmt.executeUpdate(sql);
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
