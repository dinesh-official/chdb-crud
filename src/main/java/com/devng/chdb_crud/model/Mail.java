package com.devng.chdb_crud.model;

import java.time.LocalDateTime;

public class Mail {
    private int id;
    private String ip;
    private int count;
    private String email;
    private LocalDateTime createdAt;

    public Mail() {
    }

    public Mail(int id, String ip, int count, String email, LocalDateTime createdAt) {
        this.id = id;
        this.ip = ip;
        this.count = count;
        this.email = email;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Mail{" +
                "id=" + id +
                ", ip='" + ip + '\'' +
                ", count=" + count +
                ", email='" + email + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
