package org.example.wps.controller;

import org.example.wps.core.operation.GetCapabilities;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class wpsController {
    @GetMapping("/getCapabilities")
    public String getCapabilities() {
        GetCapabilities getCapabilities = new GetCapabilities();
        return getCapabilities.generateCapabilitiesXML();
    }

    public static void main(String[] args) {
        GetCapabilities getCapabilities = new GetCapabilities();
        String string = getCapabilities.generateCapabilitiesXML();
        System.out.println(string);
    }
}