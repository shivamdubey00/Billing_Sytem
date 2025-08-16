package com.example.scms.Whatsaap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/whatsapp")
public class WhatsaapController {

    @Autowired
    private WhatsAppService whatsAppService;

    @PostMapping(value = "/send", consumes = "application/json", produces = "application/json")
    public String sendMessage(@RequestBody WhatsAppRequest request) {
        return whatsAppService.sendMessage(
                request.getTo(),
                request.getBody(),
                request.getMediaUrl()
        );
    }
}