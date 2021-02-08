package com.example.valoranttracker.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.valoranttracker.R;
import com.example.valoranttracker.models.OverviewModel;

import java.util.ArrayList;

public class OverviewAdapter extends RecyclerView.Adapter<OverviewAdapter.ViewHolder> {

    private ArrayList<OverviewModel> arrayList;
    private Context context;

    public OverviewAdapter(Context context, ArrayList<OverviewModel> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
    }


    @NonNull
    @Override
    public OverviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.overview_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OverviewAdapter.ViewHolder holder, int position) {
        holder.valueText.setText(arrayList.get(position).getValue());
        holder.nameText.setText(arrayList.get(position).getKey());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nameText, valueText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            valueText = itemView.findViewById(R.id.overviewRowValueTextView);
            nameText = itemView.findViewById(R.id.overviewRowNameTextView);
        }
    }


}
