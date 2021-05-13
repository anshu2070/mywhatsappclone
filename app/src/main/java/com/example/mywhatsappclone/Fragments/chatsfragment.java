package com.example.mywhatsappclone.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mywhatsappclone.Adapter.UsersAdapter;
import com.example.mywhatsappclone.Models.Users;
import com.example.mywhatsappclone.databinding.FragmentChatsfragmentBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class  chatsfragment extends Fragment {

public  chatsfragment() {
}

    FragmentChatsfragmentBinding binding;
    ArrayList<Users> list = new ArrayList<>();
    FirebaseDatabase database;


    @Override
    public LinearLayout onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                                     Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentChatsfragmentBinding.inflate(inflater , container, false);
        database = FirebaseDatabase.getInstance();
        UsersAdapter adapter = new UsersAdapter(list , getContext());
        binding.Recyclerview.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.Recyclerview.setLayoutManager(layoutManager);
        database.getReference().child("User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Users user = dataSnapshot.getValue(Users.class);
                  user.getUserId(dataSnapshot.getKey());
                   list.add(user);
                }
               adapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return binding.getRoot();
    }
}