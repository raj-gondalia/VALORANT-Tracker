package com.example.valoranttracker.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.valoranttracker.R;
import com.example.valoranttracker.dialog.MatchDialog;
import com.example.valoranttracker.models.CareerModel;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CareerAdapter extends RecyclerView.Adapter<CareerAdapter.ViewHolder> {

    public static final String TAG = "Career Adapter";

    private ArrayList<CareerModel> arrayList;
    private Context context;

    public CareerAdapter(Context context, ArrayList<CareerModel> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CareerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.career_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CareerAdapter.ViewHolder holder, int position) {
        CareerModel careerModel = arrayList.get(position);

        holder.agentImageView.setImageResource(careerModel.getAgent());
        holder.kdaTextView.setText(careerModel.getKda());
        holder.roundsWonTextView.setText(String.valueOf(careerModel.getRoundswon()));
        holder.roundsLostTextView.setText(String.valueOf(careerModel.getRoundslost()));
        holder.mapTextView.setText(careerModel.getMap());

        Timestamp timestamp = null;

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(careerModel.getTimestamp().substring(0, 10) + " " + careerModel.getTimestamp().substring(11, 23));
            Log.d(TAG, "onBindViewHolder: " + parsedDate);
            timestamp = new java.sql.Timestamp(parsedDate.getTime());
        } catch(Exception e) {
            Log.d(TAG, "onBindViewHolder: " + e.getMessage());
        }

        if(timestamp != null) {
            holder.dateTextView.setText(timestamp.toString().substring(0, 10));
            holder.timeTextView.setText(timestamp.toString().substring(11, 16));
        }

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MatchDialog matchDialog = new MatchDialog(context, careerModel.getGameid());
                matchDialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView agentImageView;
        private LinearLayout linearLayout;
        private TextView mapTextView, kdaTextView, timeTextView, dateTextView, roundsWonTextView, roundsLostTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            linearLayout = itemView.findViewById(R.id.careerRowLinearLayout);

            agentImageView = itemView.findViewById(R.id.careerRowAgentImageView);
            mapTextView = itemView.findViewById(R.id.careerRowMapTextView);
            kdaTextView = itemView.findViewById(R.id.careerRowKdaTextView);
            timeTextView = itemView.findViewById(R.id.careerRowTimeTextView);
            dateTextView = itemView.findViewById(R.id.careerRowDateTextView);
            roundsLostTextView = itemView.findViewById(R.id.careerRowRoundsLostTextView);
            roundsWonTextView = itemView.findViewById(R.id.careerRowRoundsWonTextView);

        }
    }
}
