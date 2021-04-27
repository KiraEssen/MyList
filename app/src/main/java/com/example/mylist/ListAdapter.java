package com.example.mylist;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

    private List<User> usersList = new ArrayList<>();
    private User[] user;
    private OnUserClickListener onUserClickListener;

    public ListAdapter(OnUserClickListener onUserClickListener) {
        this.onUserClickListener = onUserClickListener;
    }

    public void setItems(Collection<User> users){
        usersList.clear();
        usersList.addAll(users);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        holder.bind(usersList.get(position));
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        private ImageView userPhoto;
        private TextView userName;
        private TextView userDateOfBirth;
        private TextView userEmail;

        public ListViewHolder(@NonNull final View itemView) {
            super(itemView);

            userPhoto = itemView.findViewById(R.id.imageView);
            userName = itemView.findViewById(R.id.name);
            userDateOfBirth = itemView.findViewById(R.id.dateOfBirth);
            userEmail = itemView.findViewById(R.id.email);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    User users = usersList.get(getLayoutPosition());
                    onUserClickListener.onUserClick(users);
                }
            });


            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    MainActivity activity = (MainActivity) itemView.getContext();
                    activity.deleteUser(getLayoutPosition());
                    return false;
                }
            });

            //itemView.setOnCreateContextMenuListener(this);
        }

        public void bind(User user){
            userName.setText(user.getName());
            userDateOfBirth.setText(user.getDateOfBirth());
            userEmail.setText(user.getEmail());

            Glide.with(itemView.getContext())
                    .load(user.getMyURL())
                    .into(userPhoto);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Select The Action");
            menu.add(0, v.getId(), 0, "Delete");
        }
    }

    public interface OnUserClickListener {
        void onUserClick(User user);
    }
}
