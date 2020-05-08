package com.example.zakatapp3.Repositories;

import com.example.zakatapp3.Models.ZakatItemModel;

import java.util.ArrayList;

public class ItemsRepository {
    private static ItemsRepository instance;
    private ArrayList<ZakatItemModel> dataSet = new ArrayList<>();

    public static ItemsRepository getInstance() {
        if (instance == null) {

        }
        return instance;
    }
}
