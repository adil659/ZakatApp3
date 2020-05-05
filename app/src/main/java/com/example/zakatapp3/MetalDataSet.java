package com.example.zakatapp3;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MetalDataSet {

    @SerializedName("dataset")
    @Expose
    private DataSet dataset;

    public DataSet getDataset() {
        return dataset;
    }
}
