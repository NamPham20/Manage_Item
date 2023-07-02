package com.example.manage_item;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends  RecyclerView.Adapter<UserAdapter.UserViewHolder> {

   private List<User> mListUser;
   public  void setData(List<User> list){
       this.mListUser=list;
       notifyDataSetChanged();
   }

   private iclickItem iclick ;

    public interface iclickItem{
          void updateUser(User user);

          void deleteUser(User user);

          void clickUser (User user);
    }

    public UserAdapter(iclickItem iclick) {
        this.iclick = iclick;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.frame_user,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        final User user = mListUser.get(position);
        if(user == null){
            return;
        }
        holder.tvfullName.setText(user.getFullName());
        holder.tvId.setText(user.getUserNameId());
        holder.tvSex.setText(user.getSex());
        holder.tvAddress.setText(user.getAddress());
        holder.tvContact.setText(user.getContact());

        holder.imvUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iclick.updateUser(user);
            }
        });

        holder.imvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iclick.deleteUser(user);
            }
        });

        holder.tvfullName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iclick.clickUser(user);
            }
        });


    }

    @Override
    public int getItemCount() {
       if(mListUser != null){
           return mListUser.size();
       }
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        private TextView tvfullName ;
        private TextView tvId ;
        private TextView tvSex ;
        private TextView tvAddress ;
        private TextView tvContact ;
        private ImageView imvUpdate;

        private ImageView imvDelete;


        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            tvfullName = (TextView) itemView.findViewById(R.id.tvFullName_frame);
            tvId = itemView.findViewById(R.id.tvId_frame);
            tvSex = itemView.findViewById(R.id.tvSex_frame);
            tvAddress = itemView.findViewById(R.id.tvAddress_frame);
            tvContact = itemView.findViewById(R.id.tvContac_frame);
            imvUpdate = itemView.findViewById(R.id.imvUpdate_frame);
            imvDelete = itemView.findViewById(R.id.imvDelete_frame);


        }
    }
}
