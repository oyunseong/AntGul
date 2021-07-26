package com.antgul.antgul_android;

public class Stock {
    private String stockName;
    private String stockNumber;
    private boolean checked;

    Stock(){}

    public Stock(String stockName, String stockNumber, boolean checked) {
        this.stockName = stockName;
        this.stockNumber = stockNumber;
        this.checked = checked;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
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
}
