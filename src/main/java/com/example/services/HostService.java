package com.example.services;

import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by trevor on 9/11/16.
 */
@Service
public class HostService {

    public String getHostIpAddress() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostAddress();
    }

    public String getHostname() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostName();
    }
}
