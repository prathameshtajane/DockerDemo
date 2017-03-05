package com.example.controller;

import com.example.model.Host;
import com.example.services.HostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * Created by trevor on 9/11/16.
 */
@Controller
public class HostInfoController {

    private int counter = 0;
    private static final Logger LOGGER = LoggerFactory.getLogger(HostInfoController.class);

    private Environment env;
    private HostService hostService;

    @Autowired
    public HostInfoController(Environment env, HostService hostService) {
        this.env = env;
        this.hostService = hostService;
    }

    @RequestMapping("/host")
    public String getHost(Model model) {

        try {
            String hostname = hostService.getHostname();
            model.addAttribute("hostname", hostname);
            model.addAttribute("counter", ++counter);

            if (Arrays.asList(env.getActiveProfiles()).contains("redis")) {
                model.addAttribute("clusterCount", hostService.calculateClusterCount());
            } else {
                model.addAttribute("clusterCount", "Redis not enabled");
            }
            hostService.saveHost(new Host(hostService.getHostIpAddress(), hostname));
            LOGGER.debug("Saved host {}", hostname);
            return "host";
        } catch (UnknownHostException e) {
            LOGGER.error("Unable to obtain host information", e);
            return null;
        }
    }

}
