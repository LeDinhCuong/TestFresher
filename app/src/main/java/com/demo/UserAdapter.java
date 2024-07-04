package com.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>{
  private List<User> users;
  private Context context;
  private OnUserClickListener onUserClickListener;
  public UserAdapter(List<User> users, Context context) {
    this.users = users;
    this.context = context;
  }

  // Phương thức để thiết lập nghe sự kiện nhấn
  public void setOnUserClickListener(OnUserClickListener onUserClickListener) {
    this.onUserClickListener = onUserClickListener;
  }

  @NonNull
  @Override
  public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
    return new UserViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
    User user = users.get(position);
    holder.name.setText(user.getLogin());
    holder.link.setText(user.getHtml_url());
    Glide.with(context).load(user.getAvatar_url()).circleCrop().into(holder.avatar);

    holder.itemView.setOnClickListener(v -> {
      if (onUserClickListener != null) {
        onUserClickListener.onUserClick(position);
      }
    });
  }

  @Override
  public int getItemCount() {
    return users.size();
  }

  public class UserViewHolder extends RecyclerView.ViewHolder{
    ImageView avatar;
    TextView name, link;

    public UserViewHolder(@NonNull View itemView) {
      super(itemView);
      avatar = itemView.findViewById(R.id.user_avatar);
      name = itemView.findViewById(R.id.user_name);
      link = itemView.findViewById(R.id.user_github_link);
    }
  }
}
