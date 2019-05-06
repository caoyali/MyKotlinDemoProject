package Views.fragment;

import Data.Dao.Subtype.User;
import Data.Model.UserProfileViewModel;
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
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.example.mykotlindemoprogect.R;

import java.util.List;

public class UserFragment extends Fragment {
    public static final String UID_KEY = "uid";
    public static final int LOAD_USER_ID = 0;
    private UserProfileViewModel viewModel;
    private int userId;
    private TextView userInfo;
    private TextView allInfoTv;
    private Button commitBtn;
    private EditText editText;
    private Button mAddBtn;
    private Button mLookAll;
//    User currentUser;
    Handler handler = new Handler();
    List<User> uss;
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
        mAddBtn = v.findViewById(R.id.mAddBtn);
        allInfoTv = v.findViewById(R.id.mAllInfoTv);
        mLookAll = v.findViewById(R.id.mLookAll);
        commitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String edittext = editText.getText().toString();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        uss = viewModel.getAllUser();
                        if (null != uss){
                            User u = uss.get(uss.size()-1);
                            u.setName(edittext);
                            viewModel.changeUserName(u);
                        }
                    }
                }).start();
            }
        });

        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String edittext = editText.getText().toString();
                        User user = new User();
                        user.setName(edittext);
                        user.setAge(0);
                        user.setGender("--");
                        user.setPhone("手机未知");
                        uss = viewModel.getAllUser();
                        if (null != uss){
                            User u = uss.get(uss.size()-1);
                            u.setName(edittext);
                            user.setId(u.getId()+1);
                        } else {
                            user.setId(0);
                        }
                        viewModel.insertNewUser(user);
                    }
                }).start();
            }
        });

        mLookAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        uss = viewModel.getAllUser();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
//                                updateView(null);
                            }
                        });
                    }
                }).start();
            }
        });


        return v;
    }

    private void updateView(User u){
        if (null != u){
            userInfo.setText(u.getAge() + u.getGender() + u.getName() + u.getPhone());
        }
        String s ="";
        if (null != uss){
            for (User user : uss){
                s = s + user.toString();
            }
            allInfoTv.setText(s);
        }
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


    private class MyLiveData<T> extends LiveData<T>{

    }
}
