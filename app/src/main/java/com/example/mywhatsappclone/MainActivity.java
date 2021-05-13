package com.example.mywhatsappclone;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mywhatsappclone.Adapter.FragmentsAdapter;
import com.example.mywhatsappclone.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;

import static com.example.mywhatsappclone.R.*;


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth = FirebaseAuth.getInstance();


        //____________________ViewPager ______________________________
        binding.viewPager.setAdapter(new FragmentsAdapter(getSupportFragmentManager()));
        binding.tabLayout.setupWithViewPager(binding.viewPager);

    }

    //_______________________MENU BAR_______________________________________________
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.setting:
                Intent intent2 = new Intent(MainActivity.this, signinactivity.class);
                startActivity(intent2);

                break;
            case R.id.logout:
                auth.signOut();
                Intent intent = new Intent(MainActivity.this, signinactivity.class);
                startActivity(intent);
                break;


        }
        return true;
    }

}