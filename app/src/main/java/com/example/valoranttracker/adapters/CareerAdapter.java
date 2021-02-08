package com.example.valoranttracker.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.valoranttracker.R;
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
        holder.roundsTextView.setText(careerModel.getRoundswon() + " - " + careerModel.getRoundslost());
        holder.mapTextView.setText(careerModel.getMap());
        holder.modeImageView.setImageResource(careerModel.getModeSrc());
        holder.modeTextView.setText(careerModel.getMode());

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
            if (position == 0 || (!careerModel.getTimestamp().substring(0, 10).equals(arrayList.get(position - 1).getTimestamp().substring(0, 10)))) {
                holder.dateTextView.setVisibility(View.VISIBLE);
                holder.dateTextView.setText(timestamp.toString().substring(0, 10));
            } else {
                holder.dateTextView.setVisibility(View.GONE);
            }

            holder.timeTextView.setText(timestamp.toString().substring(11, 16));
        }



//        holder.timeTextView.setText();
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView agentImageView, modeImageView;
        private TextView mapTextView, modeTextView, roundsTextView, kdaTextView, timeTextView, mvpTextView, dateTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            agentImageView = itemView.findViewById(R.id.careerRowAgentImageView);
            modeImageView = itemView.findViewById(R.id.careerRowModeImageView);
            modeTextView = itemView.findViewById(R.id.careerRowModeTextView);
            mapTextView = itemView.findViewById(R.id.careerRowMapTextView);
            roundsTextView = itemView.findViewById(R.id.careerRowRoundsTextView);
            kdaTextView = itemView.findViewById(R.id.careerRowKdaTextView);
            timeTextView = itemView.findViewById(R.id.careerRowTimeTextView);
            mvpTextView = itemView.findViewById(R.id.careerRowMvpTextView);
            dateTextView = itemView.findViewById(R.id.careerRowDateTextView);

        }
    }
}
