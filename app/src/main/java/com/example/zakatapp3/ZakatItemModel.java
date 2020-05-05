package com.example.zakatapp3;

public class ZakatItemModel {
    String item;
    String amount;

    public ZakatItemModel(String item, String amount) {
        this.item = item;
        this.amount = amount;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}

