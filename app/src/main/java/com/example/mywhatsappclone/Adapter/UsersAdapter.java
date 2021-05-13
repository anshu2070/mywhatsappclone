package com.example.mywhatsappclone.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.mywhatsappclone.Models.Users;
import com.example.mywhatsappclone.R;
import com.example.mywhatsappclone.chatsdetailactivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.MissingResourceException;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.viewHolder> {

    ArrayList<Users> list;
    Context context;

    public UsersAdapter(ArrayList<Users> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_show_users, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Users user = list.get(position);
        Picasso.get().load(user.getProfilePic()).placeholder(R.drawable.whatsappphoto).into(holder.imageView);
        //Picasso.get().load(users.getProfilePic()).placeholder(R.drawable.profile).into(holder.imageView);
        holder.userName.setText(user.getUserName());
        FirebaseDatabase.getInstance().getReference().child("user")
                .child(FirebaseAuth.getInstance().getUid() + user.getUserId()).orderByChild("timestamp")
                .limitToLast(1).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()) {
                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                        holder.lastMessage.setText(snapshot1.child("Users").getValue().toString());
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, chatsdetailactivity.class);
                intent.putExtra("userId", user.getUserId());
                intent.putExtra("profilePic", user.getProfilePic());
                intent.putExtra("userName", user.getUserName());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView userName, lastMessage;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.profile_image);
            userName = itemView.findViewById(R.id.userNameList);
            lastMessage = itemView.findViewById(R.id.lastMessage);

        }
    }
}
