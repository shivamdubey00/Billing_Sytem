package com.example.scms.Whatsaap;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.File;

@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {
    @Autowired
    private PdfService pdfService;
    @Autowired private WhatsAppService whatsAppService;

    // where to write PDFs (also exposed via static handler below)
    @Value("${invoices.dir:invoices}")
    private String invoicesDir;

    // Public base URL of your server (set this in application.properties when using ngrok or deployed)
        private String publicBaseUrl = "https://scms-lqwu.onrender.com/";


    @PostMapping(value = "/send", consumes = "application/json", produces = "application/json")
    public String createAndSend(@Valid @RequestBody InvoiceRequest req) {
        try {
            System.out.println("Service is Running");
            PdfService.PdfResult pdf = pdfService.generateInvoice(req, new File(invoicesDir));
            String mediaUrl = publicBaseUrl + "/" + invoicesDir + "/" + pdf.fileName; // e.g., https://xyz.ngrok.io/invoices/uuid.pdf
System.out.println("File is store at "+mediaUrl);
            String body = MessageBuilder.buildWhatsAppBody(req);
            return whatsAppService.sendMessage(req.getPhoneE164(), body, mediaUrl);
        } catch (Exception e) {
            return "Failed: " + e.getMessage();
        }
    }
}
