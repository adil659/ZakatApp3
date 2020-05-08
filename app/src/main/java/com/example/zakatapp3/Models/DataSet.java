package com.example.zakatapp3.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DataSet {

    @SerializedName("id")
    @Expose
    public int id;

    @SerializedName("dataset_code")
    @Expose
    public String dataset_code;

    @SerializedName("database_code")
    @Expose
    public String database_code;

    @SerializedName("newest_available_date")
    @Expose
    public String newest_available_date;

    @SerializedName("oldest_available_date")
    @Expose
    public  String oldest_available_date;

    @SerializedName("start_date")
    @Expose
    public String start_date;

    @SerializedName("end_date")
    @Expose
    public String end_date;

    @SerializedName("column_names")
    @Expose
    public ArrayList<String> column_names;

    @SerializedName("data")
    @Expose
    public ArrayList<ArrayList<Object>> data;
}
