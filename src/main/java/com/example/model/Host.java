package com.example.model;

/**
 * Created by trevor on 9/11/16.
 */
public class Host {

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

    @Override
    public String toString() {
        return "Host{" +
                "ipAddress='" + ipAddress + '\'' +
                ", hostname='" + hostname + '\'' +
                '}';
    }
}
