package com.example.zakatapp3;

import java.util.ArrayList;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedDataViewModel extends ViewModel {
    private MutableLiveData<ArrayList<ZakatItemModel>> zakatItemList;

    public MutableLiveData<ArrayList<ZakatItemModel>> getZakatItemList() {
        return zakatItemList;
    }

    public void setZakatItemList(MutableLiveData<ArrayList<ZakatItemModel>> zakatItemList) {
        this.zakatItemList = zakatItemList;
    }
}
