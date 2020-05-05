package com.example.zakatapp3.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zakatapp3.LiabilityRecyclerAdapter;
import com.example.zakatapp3.R;
import com.example.zakatapp3.ZakatItemModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AssetsFragment extends Fragment {
    public RecyclerView recyclerView;
    public ArrayList<ZakatItemModel> arrayMap;

    public AssetsFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        arrayMap = new ArrayList<>();
        arrayMap.add(new ZakatItemModel("Cash", ""));
        arrayMap.add(new ZakatItemModel("Gold(g)", ""));
        arrayMap.add(new ZakatItemModel("Silver(g)", ""));
        arrayMap.add(new ZakatItemModel("Shares", ""));
        arrayMap.add(new ZakatItemModel("Business Assets", ""));
        arrayMap.add(new ZakatItemModel("Investment Properties", ""));
        arrayMap.add(new ZakatItemModel("Other", ""));

        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.tab1_assets_fragment, container, false);

        recyclerView = view.findViewById(R.id.asset_recycler_view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        LiabilityRecyclerAdapter adapter = new LiabilityRecyclerAdapter(arrayMap);
        recyclerView.setAdapter(adapter);
        return view;
    }

}


/*
 Log.d(TAG, "onSaveInstanceState: save instance");
        ArrayList<String> titles = new ArrayList<>();
        ArrayList<String> amount = new ArrayList<>();

        for (int i=0; i < arrayMap.size(); i++) {
           titles.add(arrayMap.get(i).getItem());
           amount.add(arrayMap.get(i).getAmount());
        }

        outState.putStringArrayList("title", titles);
        outState.putStringArrayList("amount", amount);

if (savedInstanceState != null) {
            arrayMap = new ArrayList<>();
            ArrayList<String> titles = savedInstanceState.getStringArrayList("title");
            ArrayList<String> amounts = savedInstanceState.getStringArrayList("amount");

            for(int i=0; i< titles.size(); i++) {
                Log.d(TAG, "onCreate: Restoring values: Title" + titles.get(i) + ": " + amounts.get(i));
                arrayMap.add(new ZakatItemModel(titles.get(i), amounts.get(i)));
            }
        }
 */