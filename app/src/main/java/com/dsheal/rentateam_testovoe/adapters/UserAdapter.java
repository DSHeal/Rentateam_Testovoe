package com.dsheal.rentateam_testovoe.adapters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import com.dsheal.rentateam_testovoe.R;
import com.dsheal.rentateam_testovoe.model.pojo.User;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>{

    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private TextView textViewName;
        private TextView textViewLastName;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewItemListUserName);
            textViewLastName = itemView.findViewById(R.id.textViewItemListUserLastName);
            cardView = itemView.findViewById(R.id.cardView_user);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                        Bundle bundle = new Bundle();
                        bundle.putString("avatar_path", users.get(position).getAvatar());
                        bundle.putString("name", users.get(position).getFirstName());
                        bundle.putString("surname", users.get(position).getLastName());
                        bundle.putString("email", users.get(position).getEmail());
                        Navigation.findNavController(view).navigate(R.id.user_info_fragment, bundle);
                }
            });
        }
    }
        @NonNull
        @Override
        public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate((R.layout.user_item), parent, false);
            return new UserViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
            User user = users.get(position);
            holder.textViewName.setText(user.getFirstName());
            holder.textViewLastName.setText(user.getLastName());
        }
    }

