package com.example.scms.Whatsaap;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class LineItem {
    @NotBlank
    private String material;
    @Min(1)
    private int quantity;
    @Min(0)
    private double unitPrice;


    public String getMaterial() { return material; }
    public void setMaterial(String material) { this.material = material; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public double getUnitPrice() { return unitPrice; }
    public void setUnitPrice(double unitPrice) { this.unitPrice = unitPrice; }
}