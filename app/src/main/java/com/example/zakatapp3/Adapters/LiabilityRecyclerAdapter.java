package com.example.zakatapp3.Adapters;

import android.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zakatapp3.Models.MetalDataSet;
import com.example.zakatapp3.Models.ZakatItemModel;
import com.example.zakatapp3.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import static android.content.ContentValues.TAG;

public class LiabilityRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<ZakatItemModel> mDataset;
    ArrayList<ZakatItemModel> currentList;

    private double goldValue;
    private String goldDay;
    private String goldMonth;
    private String goldYear;

    public void setGoldDay(String goldDay) {
        this.goldDay = goldDay;
    }

    public void setGoldMonth(String goldMonth) {
        this.goldMonth = goldMonth;
    }

    public void setGoldYear(String goldYear) {
        this.goldYear = goldYear;
    }

    public void setSilverDay(String silverDay) {
        this.silverDay = silverDay;
    }

    public void setSilverMonth(String silverMonth) {
        this.silverMonth = silverMonth;
    }

    public void setSilverYear(String silverYear) {
        this.silverYear = silverYear;
    }

    private double silverValue;
    private String silverDay;
    private String silverMonth;
    private String silverYear;



    public void setGoldValue(Double goldValue) {
        this.goldValue = goldValue;
    }

    public void setSilverValue(double silverValue) {
        this.silverValue = silverValue;
    }

    private static String GOLD = "Gold(g)";
    private static String SILVER = "Silver(g)";

    private static int TYPE_EXTRA_INFO_ITEM = 1;
    private static int TYPE_NORMAL_ITEM = 2;


    public static class LiabilityViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private View view;
        private TextView textView;
        private EditText editText;
        private ConstraintLayout parentLayout;

        public LiabilityViewHolder(View v) {
            super(v);
            view = v;
            textView = v.findViewById(R.id.textView_list);
            editText = v.findViewById(R.id.editText);
            parentLayout = v.findViewById(R.id.parent_layout);
        }
    }

    public static class LiabilityInfoViewHolder extends LiabilityViewHolder {
        // each data item is just a string in this case

        private ImageView imageView;

        public LiabilityInfoViewHolder(View v) {
            super(v);
            imageView = v.findViewById(R.id.image_button);
        }
    }

    public LiabilityRecyclerAdapter(ArrayList<ZakatItemModel> myDataset) {
        mDataset = myDataset;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View view;
        if (viewType == TYPE_NORMAL_ITEM) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.liability_item, parent, false);
            LiabilityViewHolder viewHolder = new LiabilityViewHolder(view);
            return viewHolder;
        } else {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.liability_item_with_info, parent, false);
            LiabilityInfoViewHolder viewHolder = new LiabilityInfoViewHolder(view);
            return viewHolder;

        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        LiabilityViewHolder viewHolder = null;
        if (getItemViewType(position) == TYPE_NORMAL_ITEM || getItemViewType(position) == TYPE_EXTRA_INFO_ITEM) {
            viewHolder = (LiabilityViewHolder) holder;
        }

        viewHolder.textView.setText(mDataset.get(position).getItem());
        LiabilityViewHolder finalViewHolder = viewHolder;
        viewHolder.editText.setOnFocusChangeListener((view, b) -> {
            if (!b) {
                EditText editText = (EditText) view;
                Log.d(TAG, "onFocusChange: " + finalViewHolder.textView.getText() + ": " + editText.getText());
                mDataset.get(position).setAmount(editText.getText().toString());
            }
        });

        if (getItemViewType(position) == TYPE_EXTRA_INFO_ITEM) {
            LiabilityInfoViewHolder infoViewHolder = (LiabilityInfoViewHolder) viewHolder;
            infoViewHolder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mDataset.get(position).getItem().equals(GOLD)) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

                        builder.setMessage("The price of gold as of " + goldDay + " " +
                                goldMonth + ", " + goldYear + "\n is " + goldValue + "/g")
                                .setTitle("Gold");

                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }
                    else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

                        builder.setMessage("The price of silver as of " + silverDay + " " +
                                silverMonth + ", " + silverYear + "\n is " + silverValue + "/g")
                                .setTitle("Silver");

                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }

                }
            });

        }


    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mDataset.get(position).getItem().equals(GOLD) || mDataset.get(position).getItem().equals(SILVER)) {
            return TYPE_EXTRA_INFO_ITEM;
        } else {
            return TYPE_NORMAL_ITEM;
        }
    }
}
