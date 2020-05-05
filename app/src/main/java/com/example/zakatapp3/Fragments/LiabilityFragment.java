package com.example.zakatapp3.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zakatapp3.EntrySet;
import com.example.zakatapp3.LiabilityRecyclerAdapter;
import com.example.zakatapp3.R;
import com.example.zakatapp3.ZakatItemModel;

import java.util.ArrayList;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static android.content.ContentValues.TAG;

public class LiabilityFragment extends Fragment {
    //public ArrayList<String> arrayMap;
    //ArrayList<EntrySet> currentList;
    public ArrayList<ZakatItemModel> arrayMap;
    public RecyclerView recyclerView;

    public LiabilityFragment() {
        arrayMap = new ArrayList<>();
        arrayMap.add(new ZakatItemModel("Rent", ""));
        arrayMap.add(new ZakatItemModel("Personal Living Expenses", ""));
        arrayMap.add(new ZakatItemModel("Utilities", ""));
        arrayMap.add(new ZakatItemModel("School Debt", ""));
        arrayMap.add(new ZakatItemModel("Loan", ""));
        arrayMap.add(new ZakatItemModel("Bonds", ""));
        arrayMap.add(new ZakatItemModel("Business Liabilities", ""));
        arrayMap.add(new ZakatItemModel("Other", ""));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2_liability_fragment,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.liability_recycler_view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        LiabilityRecyclerAdapter adapter = new LiabilityRecyclerAdapter(arrayMap);
        recyclerView.setAdapter(adapter);
    }

}
