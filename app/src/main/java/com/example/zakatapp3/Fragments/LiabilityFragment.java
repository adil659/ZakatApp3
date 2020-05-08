package com.example.zakatapp3.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.example.zakatapp3.Adapters.LiabilityRecyclerAdapter;
import com.example.zakatapp3.R;
import com.example.zakatapp3.ViewModel.SharedDataViewModel;
import com.example.zakatapp3.Models.ZakatItemModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class LiabilityFragment extends Fragment {
    //public ArrayList<String> arrayMap;
    //ArrayList<EntrySet> currentList;
    public ArrayList<ZakatItemModel> arrayMap;
    public RecyclerView recyclerView;
    SharedDataViewModel sharedDataViewModel;

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
        recyclerView = view.findViewById(R.id.liability_recycler_view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        LiabilityRecyclerAdapter adapter = new LiabilityRecyclerAdapter(arrayMap);
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedDataViewModel = new ViewModelProvider(requireActivity()).get(SharedDataViewModel.class);
    }

    @Override
    public void onPause() {
        super.onPause();
        sharedDataViewModel.setLiabilityZakatItemList(arrayMap);
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        recyclerView.clearFocus();
        View view = getActivity().getCurrentFocus();
        if (view == null) {
            view = new View(getActivity());
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}

/*
ArrayList<String> titles = new ArrayList<>();
        ArrayList<String> amount = new ArrayList<>();


        for (int i=0; i < arrayMap.size(); i++) {
            titles.add(arrayMap.get(i).getItem());
            amount.add(arrayMap.get(i).getAmount());
        }
        Bundle result = new Bundle();
        result.putStringArrayList("liability_titles", titles);
        result.putStringArrayList("liability_amounts", amount);
        getParentFragmentManager().setFragmentResult("liabilityBundle", result);

        for (int i =0; i < arrayMap.size(); i++) {
            Log.d(TAG, "onPause: Item: " + arrayMap.get(i).getItem() + " amount: " + arrayMap.get(i).getAmount());
        }
 */