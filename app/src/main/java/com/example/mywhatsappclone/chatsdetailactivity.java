package com.example.mywhatsappclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mywhatsappclone.databinding.ActivityChatsdetailactivityBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class chatsdetailactivity extends AppCompatActivity {
ActivityChatsdetailactivityBinding binding;
FirebaseDatabase database;
FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatsdetailactivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        String senderId = auth.getUid();
        String receiverId = getIntent().getStringExtra("userId");
        String username = getIntent().getStringExtra("username");
        String profilpics= getIntent().getStringExtra("profilpics");

binding.user.setText(username);
        Picasso.get().load(profilpics).placeholder(R.drawable.whatsappphoto).into(binding.profileImage);
binding.backarrow.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(chatsdetailactivity.this , MainActivity.class);
        startActivity(intent);
    }
});
    }
}