package com.example.mywhatsappclone.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


import com.example.mywhatsappclone.Fragments.CallsFragments;
import com.example.mywhatsappclone.Fragments.StatusFragments;
import com.example.mywhatsappclone.Fragments.chatsfragment;

public class FragmentsAdapter extends FragmentPagerAdapter {


    public FragmentsAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new chatsfragment();
            case 1:
                return new StatusFragments();
            case 2:
                return new CallsFragments();
            default:
                return new chatsfragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String Title = null;
        if (position == 0) {
            Title = "CHATS";
        }
        if (position == 1) {
            Title = "STATUS";
        }
        if (position == 2) {
            Title = "CALLS";
        }
        return Title;
    }
}
