package com.example.valoranttracker.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.valoranttracker.R;
import com.example.valoranttracker.models.MatchDetailModel;

import java.util.ArrayList;

public class MatchDialogAdapter extends RecyclerView.Adapter<MatchDialogAdapter.ViewHolder> {

    private Context context;
    private ArrayList<MatchDetailModel> arrayList;

    public MatchDialogAdapter(Context context, ArrayList<MatchDetailModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MatchDialogAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.match_dialog_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchDialogAdapter.ViewHolder holder, int position) {
        MatchDetailModel matchDetailModel = arrayList.get(position);

        holder.gameNameTextView.setText(matchDetailModel.getGameName());
        holder.agentImageView.setImageResource(matchDetailModel.getAgentPlayed());
        holder.kdaTextView.setText(matchDetailModel.getKda());
        holder.tierImageView.setImageResource(matchDetailModel.getTier());
        holder.tagTextView.setText(matchDetailModel.getTagLine());

        if(matchDetailModel.getTeam().equals("Blue")) {
            holder.linearLayout.setBackgroundResource(R.color.colorBlueTeam);
        } else {
            holder.linearLayout.setBackgroundResource(R.color.colorRedTeam);
        }

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView gameNameTextView, tagTextView, kdaTextView;
        private ImageView agentImageView, tierImageView;
        private LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            linearLayout = itemView.findViewById(R.id.matchDialogRowLinearLayout);

            gameNameTextView = itemView.findViewById(R.id.matchDialogRowGameNameTextView);
            tagTextView = itemView.findViewById(R.id.matchDialogRowTagTextView);
            agentImageView = itemView.findViewById(R.id.matchDialogRowAgentImageView);
            tierImageView = itemView.findViewById(R.id.matchDialogRowRankImageView);
            kdaTextView = itemView.findViewById(R.id.matchDialogRowKDATextView);
        }
    }
}
