package com.devng.chdb_crud.utility;

public class Query {

    public static String getMail(int daysAgo, String vmId, String vmOwner, String vmIp, String vmName, String mailType) {

        StringBuilder sql = new StringBuilder("SELECT * FROM hunter.mail WHERE 1=1");

        if (daysAgo > 0) {
            sql.append(" AND createdAd >= NOW() - INTERVAL ").append(daysAgo).append(" DAY");
        }
        if (vmId != null && !vmId.isEmpty()) {
            sql.append(" AND vmId = '").append(vmId).append("'");
        }
        if (vmOwner != null && !vmOwner.isEmpty()) {
            sql.append(" AND vmOwner = '").append(vmOwner).append("'");
        }
        if (vmIp != null && !vmIp.isEmpty()) {
            sql.append(" AND vmIp = '").append(vmIp).append("'");
        }
        if (vmName != null && !vmName.isEmpty()) {
            sql.append(" AND vmName = '").append(vmName).append("'");
        }
        if (mailType != null && !mailType.isEmpty()) {
            sql.append(" AND mailType = '").append(mailType).append("'");
        }

        sql.append(" ORDER BY createdAd DESC");
        return sql.toString();
    }






    public static String getPassword(int ipDstPort, int dstAsn, int intervalHours, int flowCountThreshold) {
        return String.format("""
        SELECT
            IPv4NumToString(IPV4_DST_ADDR) AS dst_ip,
            COUNT(*) AS flow_count
        FROM ntopng.flows
        WHERE IP_DST_PORT = %d
          AND DST_ASN = %d
          AND LAST_SEEN >= (now() - toIntervalHour(%d))
        GROUP BY dst_ip
        HAVING flow_count >= %d
        ORDER BY flow_count DESC
        """, ipDstPort, dstAsn, intervalHours, flowCountThreshold);
    }


    // SQL to create the mail table (MySQL style)
    public static final String CREATE_HUNTER_MAIL_TABLE =
            "CREATE TABLE IF NOT EXISTS hunter.mail (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "timestamp DATETIME NOT NULL, " +
                    "vmId VARCHAR(100), " +
                    "vmName VARCHAR(100), " +
                    "vmIp VARCHAR(45), " +
                    "vmOwner VARCHAR(100), " +
                    "mailType VARCHAR(50)" +
                    ")";
    // Method to generate dynamic INSERT SQL for mail table (simple version)
    public static String insertMail(
            String vmId, String vmName, String vmIp, String vmOwner, String mailType
    ) {
        return String.format(
                "INSERT INTO hunter.mail (timestamp, vmId, vmName, vmIp, vmOwner, mailType) " +
                        "VALUES (NOW(), '%s', '%s', '%s', '%s', '%s')",
                vmId,
                vmName,
                vmIp,
                vmOwner,
                mailType
        );
    }

}
