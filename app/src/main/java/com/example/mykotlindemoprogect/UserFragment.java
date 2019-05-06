package com.example.mykotlindemoprogect;

import Data.Dao.Subtype.User;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class UserFragment extends Fragment {
    public static final String UID_KEY = "uid";
    public static final int LOAD_USER_ID = 0;
    private UserProfileViewModel viewModel;
    private int userId;
    private TextView userInfo;
    private Button commitBtn;
    private EditText editText;
    User currentUser;
    Handler handler = new Handler();
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        userId = getArguments().getInt(UID_KEY);
        viewModel = ViewModelProviders.of(this).get(UserProfileViewModel.class);
        new MyThread().start();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_user,container,  false);
        userInfo = v.findViewById(R.id.mUserInfo);
        commitBtn = v.findViewById(R.id.mCommitBtn);
        editText = v.findViewById(R.id.mEditText);
        commitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String edittext = editText.getText().toString();
                currentUser.setName(edittext);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        viewModel.changeUserName(currentUser);
                    }
                }).start();
            }
        });
        return v;
    }

    private void updateView(User u){
        userInfo.setText(u.getAge() + u.getGender() + u.getName() + u.getPhone());
    }

    private class MyThread extends Thread{
        @Override
        public void run() {
            viewModel.init(userId, getActivity(), new UserProfileViewModel.InitFinishCallBack() {
                @Override
                public void onInitFinish() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            viewModel.getUser().observe(UserFragment.this, new Observer<User>() {
                                @Override
                                public void onChanged(User user) {
                                    updateView(user);
                                    currentUser = user;
                                }
                            });

                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                }
                            }, 4000l);
                        }
                    });
                }
            });
        }
    }
}
