package com.example.zakatapp3.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.example.zakatapp3.Adapters.LiabilityRecyclerAdapter;
import com.example.zakatapp3.Models.MetalDataSet;
import com.example.zakatapp3.R;
import com.example.zakatapp3.ViewModel.SharedDataViewModel;
import com.example.zakatapp3.Models.ZakatItemModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AssetsFragment extends Fragment {
    public RecyclerView recyclerView;
    public ArrayList<ZakatItemModel> arrayMap;
    private SharedDataViewModel sharedDataViewModel;

    SimpleDateFormat monthformat = new SimpleDateFormat("MMMM", Locale.getDefault());
    SimpleDateFormat dayFormat = new SimpleDateFormat("dd", Locale.getDefault());
    SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.getDefault());
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()); // get todays date and make it in this format


    private static String METAL_GOLD = "GOLD";
    private static String METAL_SILVER = "SILVER";

    private static int METAL_VALUE_LOCATION = 0;

    private static int METAL_VALUE_USD_AM = 1;


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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedDataViewModel = new ViewModelProvider(requireActivity()).get(SharedDataViewModel.class);
        setGoldDataListener(sharedDataViewModel);
        setSilverDataListener(sharedDataViewModel);
    }

    private void setGoldDataListener(SharedDataViewModel goldDataListener) {
        goldDataListener.getGoldDataSet(METAL_GOLD).observe(getViewLifecycleOwner(), new Observer<MetalDataSet>() {
            @Override
            public void onChanged(MetalDataSet metalDataSet) {

                Date dataDate;
                LiabilityRecyclerAdapter liabilityRecyclerAdapter = (LiabilityRecyclerAdapter) recyclerView.getAdapter();
                try {
                    dataDate = simpleDateFormat.parse(metalDataSet.getDataset().end_date);

                    String day = dayFormat.format(dataDate);
                    String month = monthformat.format(dataDate);
                    String year = yearFormat.format(dataDate);

                    liabilityRecyclerAdapter.setGoldDay(day);
                    liabilityRecyclerAdapter.setGoldMonth(month);
                    liabilityRecyclerAdapter.setGoldYear(year);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Double goldValue = (double) metalDataSet.getDataset().data.get(METAL_VALUE_LOCATION).get(METAL_VALUE_USD_AM);
                liabilityRecyclerAdapter.setGoldValue(goldValue);
            }
        });
    }

    private void setSilverDataListener(SharedDataViewModel silverDataListener) {
        silverDataListener.getSilverDataSet(METAL_SILVER).observe(getViewLifecycleOwner(), new Observer<MetalDataSet>() {
            @Override
            public void onChanged(MetalDataSet metalDataSet) {
                LiabilityRecyclerAdapter liabilityRecyclerAdapter = (LiabilityRecyclerAdapter) recyclerView.getAdapter();
                Date dataDate;
                try {
                    dataDate = simpleDateFormat.parse(metalDataSet.getDataset().end_date);

                    String day = dayFormat.format(dataDate);
                    String month = monthformat.format(dataDate);
                    String year = yearFormat.format(dataDate);

                    liabilityRecyclerAdapter.setSilverDay(day);
                    liabilityRecyclerAdapter.setSilverMonth(month);
                    liabilityRecyclerAdapter.setSilverYear(year);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                double silverValue = (double) metalDataSet.getDataset().data.get(METAL_VALUE_LOCATION).get(METAL_VALUE_USD_AM);
                liabilityRecyclerAdapter.setSilverValue(silverValue);
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        sharedDataViewModel.setAssetZakatItemList(arrayMap);

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