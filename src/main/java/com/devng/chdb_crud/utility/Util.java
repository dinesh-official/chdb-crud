package com.devng.chdb_crud.utility;

import com.devng.chdb_crud.model.Mail;

import java.util.List;
import java.util.Objects;

public class Util {
    public static boolean alreadyMailed(List<Mail> results, String vmId, String vmOwner, String vmIp, String vmName, String mailType) {
        return results.parallelStream()
                .anyMatch(mail ->
                        Objects.equals(vmId, mail.getVmId()) &&
                                Objects.equals(vmOwner, mail.getVmOwner()) &&
                                Objects.equals(vmIp,mail.getVmIp()) &&
                                Objects.equals(mailType,mail.getMailType())
                );
    }
}
