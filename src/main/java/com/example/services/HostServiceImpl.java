package com.example.services;

import com.example.model.Host;
import com.example.repositories.HostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * Created by trevor on 9/11/16.
 */
@Service
public class HostServiceImpl implements HostService{

    private Environment env;
    private StringRedisTemplate stringRedisTemplate;
    private HostRepository hostRepository;
    private ValueOperations valueOps;
    private int clusterCount = 0;

    @Autowired
    public HostServiceImpl(Environment env, HostRepository hostRepository, StringRedisTemplate stringRedisTemplate) {
        this.env = env;
        this.hostRepository = hostRepository;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    // Set the cluster count in cache value if it doesn't exist
    @PostConstruct
    public void initClusterCount() {

        if (Arrays.asList(env.getActiveProfiles()).contains("redis")) {
            valueOps = stringRedisTemplate.opsForValue();
            if (valueOps.get("cluster-count") == null) {
                valueOps.set("cluster-count", Integer.toString(clusterCount));
            }
        }
    }

    @Override
    public String getHostIpAddress() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostAddress();
    }

    @Override
    public String getHostname() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostName();
    }

    @Override
    public int calculateClusterCount() {

        clusterCount = Integer.parseInt((String) valueOps.get("cluster-count"));
        valueOps.set("cluster-count", Integer.toString(++clusterCount));
        return clusterCount;
    }

    @Override
    public Iterable<Host> listAllHosts() {
        return hostRepository.findAll();
    }

    @Override
    public Host getHostById(Integer id) {
        return hostRepository.findOne(id);
    }

    @Override
    public Host saveHost(Host Host) {
        return hostRepository.save(Host);
    }
}
