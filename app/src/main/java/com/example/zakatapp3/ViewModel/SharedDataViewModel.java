package com.example.zakatapp3.ViewModel;

import android.util.Log;

import com.example.zakatapp3.Models.MetalDataSet;
import com.example.zakatapp3.Models.ZakatItemModel;
import com.example.zakatapp3.QuandlMetalApi;
import com.example.zakatapp3.QuandlMetalApiClient;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class SharedDataViewModel extends ViewModel implements Callback<MetalDataSet>{
    private MutableLiveData<ArrayList<ZakatItemModel>> liabilityItemList = new MutableLiveData<>();
    private MutableLiveData<ArrayList<ZakatItemModel>> assetItemList = new MutableLiveData<>();


    private MutableLiveData<MetalDataSet> goldDataSet = new MutableLiveData<>();
    private MutableLiveData<MetalDataSet> silverDataSet = new MutableLiveData<>();



    public MutableLiveData<ArrayList<ZakatItemModel>> getLiabilityZakatItemList() {
        return liabilityItemList;
    }

    public void setLiabilityZakatItemList(ArrayList<ZakatItemModel> zakatItemList) {
        liabilityItemList.setValue(zakatItemList);
    }

    public MutableLiveData<ArrayList<ZakatItemModel>> getAssetZakatItemList() {
        return assetItemList;
    }

    public void setAssetZakatItemList(ArrayList<ZakatItemModel> zakatItemList) {
        assetItemList.setValue(zakatItemList);
    }

    public MutableLiveData<MetalDataSet> getGoldDataSet(String metal) {
        SimpleDateFormat apiDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()); // get todays date and make it in this format
        Date todayDate = Calendar.getInstance().getTime();
        String today = apiDateFormat.format(todayDate);
        QuandlMetalApiClient.getInstance().getMetalData(metal, today).enqueue(SharedDataViewModel.this);
        return goldDataSet;
    }

    public void setGoldDataSet(MetalDataSet dataSetApi) {
        goldDataSet.setValue(dataSetApi);
    }

    public MutableLiveData<MetalDataSet> getSilverDataSet(String metal) {
        SimpleDateFormat apiDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()); // get todays date and make it in this format
        Date todayDate = Calendar.getInstance().getTime();
        String today = apiDateFormat.format(todayDate);
        QuandlMetalApiClient.getInstance().getMetalData(metal, today).enqueue(SharedDataViewModel.this);
        return silverDataSet;
    }

    public void setSilverDataSet(MetalDataSet dataSetApi) {
        silverDataSet.setValue(dataSetApi);
    }


    @Override
    public void onResponse(Call<MetalDataSet> call, Response<MetalDataSet> response) {
        Log.d(TAG, "onResponse: Am i in new callbacks?");
        //Log.d("On Response", response.body().toString());
        MetalDataSet metalDataSet = response.body();
        String metal = metalDataSet.getDataset().dataset_code;
        if(!metalDataSet.getDataset().data.isEmpty()) {
            if(metal.equals("GOLD")) {
                goldDataSet.setValue(metalDataSet);
            }
            else {
                silverDataSet.setValue(metalDataSet);
            }

        } else {
            String endDate = metalDataSet.getDataset().end_date;
            QuandlMetalApiClient quandlMetalApiClient = QuandlMetalApiClient.getInstance();
            if(metal.equals("GOLD")) {
                quandlMetalApiClient.getMetalData("GOLD", endDate).enqueue(SharedDataViewModel.this);
            }
            else {
                quandlMetalApiClient.getMetalData("SILVER", endDate).enqueue(SharedDataViewModel.this);
            }

        }

    }

    @Override
    public void onFailure(Call<MetalDataSet> call, Throwable t) {
        Log.d("API Failure", t.getMessage());

    }
}
