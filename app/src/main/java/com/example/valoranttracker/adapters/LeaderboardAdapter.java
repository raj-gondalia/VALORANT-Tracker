package com.example.valoranttracker.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.valoranttracker.R;
import com.example.valoranttracker.models.LeaderboardModel;

import java.util.ArrayList;


public class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardAdapter.ViewHolder> {

    private Context context;
    private ArrayList<LeaderboardModel> arrayList;

    public LeaderboardAdapter(Context context, ArrayList<LeaderboardModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public LeaderboardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.leaderboard_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LeaderboardAdapter.ViewHolder holder, int position) {
        LeaderboardModel leaderboardModel = arrayList.get(position);

        if(leaderboardModel.getRank()%10 == 1) {
            holder.rank.setText(leaderboardModel.getRank() + "st");
        } else if(leaderboardModel.getRank()%10 == 2) {
            holder.rank.setText(leaderboardModel.getRank() + "nd");
        } else if(leaderboardModel.getRank()%10 == 3) {
            holder.rank.setText(leaderboardModel.getRank() + "rd");
        } else {
            holder.rank.setText(leaderboardModel.getRank() + "th");
        }

        holder.rankedRating.setText(String.valueOf(leaderboardModel.getRankedRating()));
        holder.gameName.setText(leaderboardModel.getGameName());
        holder.tagLine.setText(leaderboardModel.getTagLine());
        holder.numberOfWins.setText(String.valueOf(leaderboardModel.getNumberOfWins()));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView rank, rankedRating, numberOfWins, gameName, tagLine;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            rank = itemView.findViewById(R.id.leaderboardRowRank);
            rankedRating = itemView.findViewById(R.id.leaderboardRowRankedRating);
            gameName = itemView.findViewById(R.id.leaderboardRowGameName);
            tagLine = itemView.findViewById(R.id.leaderboardRowTagLine);
            numberOfWins = itemView.findViewById(R.id.leaderboardRowWins);
        }
    }
}
