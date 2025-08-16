package com.example.scms.Whatsaap;

import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;

@Service
public class WhatsAppService {
    @Value("${twilio.accountSid}") private String accountSid;
    @Value("${twilio.authToken}") private String authToken;
    @Value("${twilio.whatsappNumber}") private String fromNumber; // e.g., whatsapp:+14155238886

    public String sendMessage(String to, String body, String mediaUrl) {
        try {

            System.out.println("Sending WhatsApp message to: " + to);
            System.out.println("Media URL: " + mediaUrl);

            Twilio.init(accountSid, authToken);
            Message message = Message.creator(
                            new com.twilio.type.PhoneNumber("whatsapp:" + to),
                            new com.twilio.type.PhoneNumber(fromNumber),
                            body
                    ).setMediaUrl(List.of(URI.create(mediaUrl)))
                    .create();

            System.out.println("Sending WhatsApp message to: " + to);
            System.out.println("Media URL: " + mediaUrl);

            return "Message sent. SID: " + message.getSid();
        } catch (ApiException e) {
            return "Twilio error: " + e.getMessage();
        } catch (Exception e) {
            return "Unexpected error: " + e.getMessage();
        }
    }
}