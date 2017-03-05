package com.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by trevor on 9/11/16.
 */
@Entity
public class Host {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private final String ipAddress;
    private final String hostname;

    public Host(String ipAddress, String hostname) {
        this.ipAddress = ipAddress;
        this.hostname = hostname;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getHostname() {
        return hostname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Host{" +
                "ipAddress='" + ipAddress + '\'' +
                ", hostname='" + hostname + '\'' +
                '}';
    }
}
