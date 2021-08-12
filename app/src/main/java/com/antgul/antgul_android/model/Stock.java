package com.antgul.antgul_android.model;

public class Stock {
    private String stockName;
    private String stockNumber;
    private String largeCategory;
    private String smallCategory;
    private String PER;
    private String PBR;
    private String PSR;
    private boolean checked;

    public Stock(){}

    public Stock(String stockName, String stockNumber, String largeCategory, String smallCategory, String PER, String PBR, String PSR, boolean checked) {
        this.stockName = stockName;
        this.stockNumber = stockNumber;
        this.largeCategory = largeCategory;
        this.smallCategory = smallCategory;
        this.PER = PER;
        this.PBR = PBR;
        this.PSR = PSR;
        this.checked = checked;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getStockNumber() {
        return stockNumber;
    }

    public void setStockNumber(String stockNumber) {
        this.stockNumber = stockNumber;
    }

    public String getLargeCategory() {
        return largeCategory;
    }

    public void setLargeCategory(String largeCategory) {
        this.largeCategory = largeCategory;
    }

    public String getSmallCategory() {
        return smallCategory;
    }

    public void setSmallCategory(String smallCategory) {
        this.smallCategory = smallCategory;
    }

    public String getPER() {
        return PER;
    }

    public void setPER(String PER) {
        this.PER = PER;
    }

    public String getPBR() {
        return PBR;
    }

    public void setPBR(String PBR) {
        this.PBR = PBR;
    }

    public String getPSR() {
        return PSR;
    }

    public void setPSR(String PSR) {
        this.PSR = PSR;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
