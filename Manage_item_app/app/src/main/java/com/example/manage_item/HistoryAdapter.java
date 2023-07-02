package com.example.manage_item;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private List<History> mListHistory;

    public  void setData(List<History> list){
        this.mListHistory=list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.frame_history,parent,false);
        return new HistoryAdapter.HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        final History history = mListHistory.get(position);
        if(history== null){
            return;
        }
        holder.tvIdUser.setText(history.getIdUser());
        holder.tvNameUser.setText(history.getNameId());
        holder.tvTime.setText(history.getTime());
        holder.tvResult.setText(history.getResult());

    }

    @Override
    public int getItemCount() {
        if(mListHistory != null){
            return mListHistory.size();
        }
        return 0;
    }

    public class  HistoryViewHolder extends RecyclerView.ViewHolder {

        private TextView tvIdUser;
        private TextView tvNameUser;
        private TextView tvResult;
        private TextView tvTime;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);

            tvIdUser =itemView.findViewById(R.id.tvIdUser_frame);
            tvNameUser=itemView.findViewById(R.id.tvNameUser_frame);
            tvResult=itemView.findViewById(R.id.tvResult_frame);
            tvTime=itemView.findViewById(R.id.tvTime_frame);

        }
    }
}
