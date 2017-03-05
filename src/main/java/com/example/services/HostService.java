package com.example.services;

import com.example.model.Host;

import java.net.UnknownHostException;

/**
 * Created by trevor on 3/5/17.
 */
public interface HostService {


    String getHostIpAddress() throws UnknownHostException;

    String getHostname() throws UnknownHostException;

    int calculateClusterCount();

    Iterable<Host> listAllHosts();

    Host getHostById(Integer id);

    Host saveHost(Host product);
    
}
