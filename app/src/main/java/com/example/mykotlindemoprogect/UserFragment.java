package com.example.mykotlindemoprogect;

import Data.Dao.Subtype.User;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class UserFragment extends Fragment {
    public static final String UID_KEY = "uid";
    private UserProfileViewModel viewModel;
    private TextView userInfo;
    Handler handler = new Handler();
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String userId = getArguments().getString(UID_KEY);

        viewModel = ViewModelProviders.of(this).get(UserProfileViewModel.class);
        viewModel.init(Integer.parseInt(userId), getActivity());
        viewModel.getUser().observe(this, user -> {
            updateView(user);
        });

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
            }
        }, 4000l);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_user,container,  false);
        userInfo = v.findViewById(R.id.mUserInfo);
        return v;
    }

    private void updateView(User u){
        userInfo.setText(u.getAge() + u.getGender() + u.getName() + u.getPhone());
    }
}
