package com.example.zakatapp3;

import com.example.zakatapp3.Models.ZakatItemModel;

import java.util.Map;

public class EntrySet implements Map.Entry {

    ZakatItemModel zakatItemModel;
    Boolean isInList;

    public EntrySet(ZakatItemModel zakatItemModel, Boolean isInList) {
        this.zakatItemModel = zakatItemModel;
        this.isInList = isInList;
    }

    @Override
    public Object getKey() {
        return zakatItemModel;
    }

    @Override
    public Object getValue() {
        return isInList;
    }

    @Override
    public Object setValue(Object o) {
        isInList = (Boolean)o;
        return isInList;
    }
}
