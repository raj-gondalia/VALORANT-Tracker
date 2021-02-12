package com.example.valoranttracker.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.valoranttracker.R;
import com.example.valoranttracker.activities.Stats;
import com.example.valoranttracker.database.DBHelper;
import com.example.valoranttracker.models.HistoryModel;
import com.example.valoranttracker.sharedPreferences.SharedPref;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private Context context;
    private ArrayList<HistoryModel> arrayList;

    public HistoryAdapter(Context context, ArrayList<HistoryModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.history_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.ViewHolder holder, int position) {

        HistoryModel historyModel = arrayList.get(position);

        holder.gameName.setText(historyModel.getGameName());
        holder.tag.setText(historyModel.getTag());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBHelper dbHelper = new DBHelper(context);

                dbHelper.insertHistory(historyModel.getGameName(), historyModel.getTag(), historyModel.getRegion());

                SharedPref.getInstance(context).addRegion(historyModel.getRegion());

                Intent intent = new Intent(context, Stats.class);
                intent.putExtra("gameName", historyModel.getGameName());
                intent.putExtra("tag", historyModel.getTag());
                context.startActivity(intent);

                ((Activity)context).finish();

            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout linearLayout;
        private TextView gameName, tag;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            linearLayout = itemView.findViewById(R.id.historyRowLinearLayout);

            gameName = itemView.findViewById(R.id.historyRowGameName);
            tag = itemView.findViewById(R.id.historyRowTag);
        }
    }
}
