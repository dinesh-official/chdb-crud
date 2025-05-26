package com.devng.chdb_crud.utility;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Ssh {

    public static boolean supportsPasswordAuth(String ip) {
        try {
            ProcessBuilder pb = new ProcessBuilder("ssh", "-o", "StrictHostKeyChecking=no", "-o", "BatchMode=no", "root@" + ip);
            pb.redirectErrorStream(true);

            Process process = pb.start();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                long start = System.currentTimeMillis();
                while ((line = reader.readLine()) != null) {
                    line = line.toLowerCase();
                    if (line.contains("password")) {
                        process.destroyForcibly(); // kill after detection
                        return true;
                    }
                    if (System.currentTimeMillis() - start > 8000) {
                        process.destroyForcibly();
                        break;
                    }
                }
            }

        } catch (Exception e) {
            System.err.println("Error checking SSH for " + ip + ": " + e.getMessage());
        }

        return false;
    }
}
