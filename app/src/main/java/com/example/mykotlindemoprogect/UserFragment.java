package com.example.mykotlindemoprogect;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class UserFragment extends Fragment {
    private static final String UID_KEY = "uid";
    private UserProfileViewModel viewModel;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        String userId = getArguments().getString(UID_KEY);
//        viewModel = ViewModelProviders.of(this).get(UserProfileViewModel.class);
//        viewModel.init(userId);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_user,container,  false);
        return v;
    }
}
