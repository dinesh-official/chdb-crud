package com.devng.chdb_crud.utility;

public class Query {

        public static String getPassword(int ipDstPort, int dstAsn, int intervalHours, int flowCountThreshold) {
            return String.format("""
                SELECT
                    IPv4NumToString(IPV4_SRC_ADDR) AS src_ip,
                    COUNT(*) AS flow_count
                FROM ntopng.flows
                WHERE (IP_DST_PORT = %d)
                  AND (DST_ASN = %d)
                  AND (LAST_SEEN >= (now() - toIntervalHour(%d)))
                GROUP BY src_ip
                HAVING flow_count > %d
                ORDER BY flow_count DESC
                """, ipDstPort, dstAsn, intervalHours, flowCountThreshold);
        }


}
