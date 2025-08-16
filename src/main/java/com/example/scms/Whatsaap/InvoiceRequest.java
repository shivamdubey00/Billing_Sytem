package com.example.scms.Whatsaap;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class InvoiceRequest {
    @NotBlank
    private String storeName;
    @NotBlank
    private String customerName;
    @NotBlank
    private String phoneE164; // +91...
    @NotNull
    private List<LineItem> items;
    private double taxRatePercent;
    private String notes;


    public String getStoreName() { return storeName; }
    public void setStoreName(String storeName) { this.storeName = storeName; }
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public String getPhoneE164() { return phoneE164; }
    public void setPhoneE164(String phoneE164) { this.phoneE164 = phoneE164; }
    public List<LineItem> getItems() { return items; }
    public void setItems(List<LineItem> items) { this.items = items; }
    public double getTaxRatePercent() { return taxRatePercent; }
    public void setTaxRatePercent(double taxRatePercent) { this.taxRatePercent = taxRatePercent; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
