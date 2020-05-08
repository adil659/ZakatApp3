package com.example.zakatapp3.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.zakatapp3.R;
import com.example.zakatapp3.ViewModel.SharedDataViewModel;
import com.example.zakatapp3.Models.ZakatItemModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import static android.content.ContentValues.TAG;

public class CalculateFragment extends Fragment {

    Button button;

    private ArrayList<ZakatItemModel> liabilitiesList;
    private ArrayList<ZakatItemModel> assetsList;
    private SharedDataViewModel sharedDataViewModel;



    private TextView zakatEligible;
    private TextView amountPay;

    private TextView nisaabAmount;
    private TextView netWorthAmount;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        liabilitiesList = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab3_calculate_fragment,container,false);
        button = view.findViewById(R.id.calculate_button);
        amountPay = view.findViewById(R.id.amount_pay);
        zakatEligible = view.findViewById(R.id.zakat_eligible);
        nisaabAmount = view.findViewById(R.id.nisaabValue);
        netWorthAmount = view.findViewById(R.id.net_worth);

        setCalcButtonListener(button);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedDataViewModel = new ViewModelProvider(requireActivity()).get(SharedDataViewModel.class);
        sharedDataViewModel.getLiabilityZakatItemList().observe(getViewLifecycleOwner(), new Observer<ArrayList<ZakatItemModel>>() {
            @Override
            public void onChanged(ArrayList<ZakatItemModel> zakatItemModels) {
                Log.d(TAG, "onChanged: Zakatitems Changed Rent: " + zakatItemModels.get(0).getAmount());
                liabilitiesList = zakatItemModels;
            }
        });

        sharedDataViewModel.getAssetZakatItemList().observe(getViewLifecycleOwner(), new Observer<ArrayList<ZakatItemModel>>() {
            @Override
            public void onChanged(ArrayList<ZakatItemModel> zakatItemModels) {
                assetsList = zakatItemModels;
            }
        });
    }

    private double calculateNisaab() {

        double nisaabValue = 1673.05 * 3;


        return nisaabValue;
    }

    private double calculateAssets() {

        double total =0;
        for (int i=0; i<assetsList.size(); i++) {
            if(!assetsList.get(i).getAmount().isEmpty()) {
                String string = assetsList.get(i).getItem();
                String value  = assetsList.get(i).getAmount();
                if (!value.isEmpty()) {
                    double actualValue = Double.parseDouble(value);
                    if (string.equals("Gold(g)")) {
                        double goldCalc = 0; //(actualValue / GRAM_TO_OUNCE_DIVIDER) * 1;
                        //Log.v("Calculation", "gold(g|oz)[" + actualValue + "|" +
                              //  (actualValue/GRAM_TO_OUNCE_DIVIDER) + "] goldvalue[ " + mGoldValue + "][adding asset " + string + "[" + goldCalc + "]");
                        total += goldCalc;

                    } else if (string.equals("Silver(g)")) {
                        double silverCalc = 0; //(actualValue / GRAM_TO_OUNCE_DIVIDER) * mSilverValue;
                       // Log.v("Calculation", "silver(g|oz)[" + actualValue + "|" +
                                //(actualValue/GRAM_TO_OUNCE_DIVIDER) + "] silverValue[ " + mSilverValue + "][adding asset " + string + "[" + silverCalc + "]");
                        total += silverCalc;

                    } else {
                        total += actualValue;
                        Log.v("Calculation", "adding asset " + string + "[" + actualValue + "]");
                    }
                }
            }
        }


        return total;
    }

    private double calculateLiabilities() {

        double total =0;
        for (int i=0; i<liabilitiesList.size(); i++) {
            if(!liabilitiesList.get(i).getAmount().isEmpty()) {
                String string = liabilitiesList.get(i).getItem();
                String value = liabilitiesList.get(i).getAmount();

                if(!value.isEmpty()) {
                    double actualValue = Double.parseDouble(value);
                    total += actualValue;
                    Log.v("Calculation", "adding liability " + string + "[" + actualValue + "]");

                }
            }
        }
        return total;
    }

    private void setCalcButtonListener(Button button) {
        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                //amountPay.setText(liabilitiesList.get(0).getAmount());

                double nisaabValue = calculateNisaab();
                double totalAssets = calculateAssets(); // get total assets
                double totalLiabilities = calculateLiabilities(); // get total liabilities

                double netWorth = totalAssets - totalLiabilities;
                Log.v("Calculation", "netWorth[" + netWorth + "]");

                if(netWorth > nisaabValue) {
                    double zakatToPay = netWorth * 0.025;
                    //mCalculateFragment.calculateResult("yes", String.valueOf(zakatToPay), String.valueOf(nisaabValue), String.valueOf(netWorth));
                    // put the values in UI
                    zakatEligible.setText("yes");
                    nisaabAmount.setText(String.valueOf(nisaabValue));
                    netWorthAmount.setText(String.valueOf(netWorth));
                    amountPay.setText(String.valueOf(zakatToPay));
                    Log.v("Calculation", "can pay zakat");
                }
                else{
                    //mCalculateFragment.calculateResult("No", "0", String.valueOf(nisaabValue), String.valueOf(netWorth));
                    Log.v("Calculation", "not eligible to pay zakat");
                    zakatEligible.setText("no");
                    nisaabAmount.setText(String.valueOf(nisaabValue));
                    netWorthAmount.setText(String.valueOf(netWorth));
                    amountPay.setText("0");
                }
            }
        });
    }


}


/*
private void setFragmentListener(FragmentManager fragmentManager) {
        fragmentManager.setFragmentResultListener("liabilityBundle", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                Log.d(TAG, "onFragmentResult: received liability fragment results");
                //liabilitiesList = new ArrayList<>();
                ArrayList<String> liabilityTitles = result.getStringArrayList("liability_titles");
                ArrayList<String> liabilityAmounts = result.getStringArrayList("liability_amounts");
                for (int i=0; i < liabilityTitles.size(); i++) {
                    //liabilitiesList.add(new ZakatItemModel(liabilityTitles.get(i), liabilityAmounts.get(i)));
                }
            }
        });
    }
 */