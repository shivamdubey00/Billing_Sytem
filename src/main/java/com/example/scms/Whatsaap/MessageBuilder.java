package com.example.scms.Whatsaap;


import java.text.NumberFormat;
import java.util.Locale;

public class MessageBuilder {
    public static String buildWhatsAppBody(InvoiceRequest req) {
        NumberFormat inr = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));
        StringBuilder sb = new StringBuilder();
        sb.append("*").append(req.getStoreName()).append("*\n");
        sb.append("Invoice for *").append(req.getCustomerName()).append("*\n\n");
        double subtotal = 0;
        int idx = 1;
        for (LineItem li : req.getItems()) {
            double line = li.getQuantity() * li.getUnitPrice();
            subtotal += line;
            sb.append(idx++).append(". ").append(li.getMaterial())
                    .append(" — ").append(li.getQuantity()).append(" x ")
                    .append(inr.format(li.getUnitPrice())).append(" = ")
                    .append(inr.format(line)).append("\n");
        }
        double tax = subtotal * (req.getTaxRatePercent()/100.0);
        double total = subtotal + tax;
        sb.append("\nSubtotal: ").append(inr.format(subtotal)).append("\n");
        sb.append("Tax (").append(req.getTaxRatePercent()).append("%): ")
                .append(inr.format(tax)).append("\n");
        sb.append("Total: *").append(inr.format(total)).append("*\n\n");
        if (req.getNotes() != null && !req.getNotes().isBlank()) {
            sb.append("Notes: ").append(req.getNotes()).append("\n\n");
        }
        sb.append("Thank you for your purchase!\n");
        sb.append("— ").append(req.getStoreName());
        return sb.toString();
    }
}


