package com.example.zakatapp3;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import static android.content.ContentValues.TAG;

public class LiabilityRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<ZakatItemModel> mDataset;
    ArrayList<ZakatItemModel> currentList;

    static String GOLD = "Gold(g)";
    static String SILVER = "Silver(g)";

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
                    Log.d(TAG, "onClick: Clicked on gold or silver");
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

                    builder.setMessage("Whats goodie" )
                            .setTitle("AYO");

                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
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
