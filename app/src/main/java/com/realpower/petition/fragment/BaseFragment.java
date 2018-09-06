package com.realpower.petition.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.TextView;
import com.realpower.petition.R;
import com.realpower.petition.activity.BaseActivity;
import com.realpower.petition.net.ApiService;
import com.realpower.petition.net.PetitionPref_;
import org.androidannotations.annotations.EFragment;

/**
 * Created by Administrator on 2017/11/9.
 */
@EFragment
public class BaseFragment extends Fragment {
    public static PetitionPref_ mypref;
    public static ApiService apiService;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiService = ((BaseActivity) getActivity()).apiService;
    }

    public void setTitleName(String name) {
        TextView textView = (TextView) getView().findViewById(R.id.tv_title);
        if (textView != null) {
            textView.setText(name);
        }
    }

    public void setRightName(String name) {
        TextView textView = (TextView) getView().findViewById(R.id.tv_right);
        if (textView != null) {
            textView.setText(name);
        }
    }
}
