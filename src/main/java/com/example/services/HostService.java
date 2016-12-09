package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by trevor on 9/11/16.
 */
@Service
public class HostService {

    @Autowired
    private Environment env;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private ValueOperations valueOps;

    private int clusterCount = 0;

    // Set the cluster count in cache value if it doesn't exist
    @PostConstruct
    public void initClusterCount() throws Exception {
        String[] envs = this.env.getActiveProfiles();
        if (envs[0].toString().equals("redis")) {
            valueOps = stringRedisTemplate.opsForValue();
            if (valueOps.get("cluster-count") == null) {
                valueOps.set("cluster-count", Integer.toString(clusterCount));
            }
        }
    }

    public String getHostIpAddress() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostAddress();
    }

    public String getHostname() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostName();
    }

    public int calculateClusterCount() {

        clusterCount = Integer.parseInt((String) valueOps.get("cluster-count"));
        valueOps.set("cluster-count", Integer.toString(++clusterCount));
        return clusterCount;
    }
}
