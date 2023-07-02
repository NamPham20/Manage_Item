package com.example.manage_item;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProItemAdapter extends RecyclerView.Adapter<ProItemAdapter.ProItemViewHolder> {

    private List<ProItem> mListItem;
    public  void setData(List<ProItem> list){
        this.mListItem=list;
        notifyDataSetChanged();
    }
    private iclick click;

  public interface iclick{
        void iclikDelete(ProItem proItem);
  }

    public ProItemAdapter(iclick click) {
        this.click = click;
    }



    @NonNull
    @Override
    public ProItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.frame_item,parent,false);
        return new ProItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProItemViewHolder holder, int position) {
       final ProItem proItem = mListItem.get(position);
       if(proItem == null){
           return;
       }
       holder.tvIdItem.setText(proItem.getIdItem());
        holder.tvNameItem.setText(proItem.getNameItem());

        holder.imvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.iclikDelete(proItem);
            }
        });


    }

    @Override
    public int getItemCount() {
        if(mListItem != null){
            return mListItem.size();
        }
        return 0;
    }

    public class ProItemViewHolder extends RecyclerView.ViewHolder{

      private TextView tvIdItem;
      private TextView tvNameItem;
      private ImageView imvDelete;

        public ProItemViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNameItem = itemView.findViewById(R.id.tvNameItem_frame);
            tvIdItem = itemView.findViewById(R.id.tvIdItem_frame);
            imvDelete = itemView.findViewById(R.id.imvDeleteItem_frame);


        }
    }
}
