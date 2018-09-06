package com.realpower.petition.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.realpower.petition.R;
import com.realpower.petition.activity.PetitionNewActivity_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/9.
 */
@EFragment(R.layout.fragment_petition_group)
public class PetitionGroupFragment extends BaseFragment {

    private PagerAdapter pagerAdapter;
    @ViewById
    TabLayout tl_petition;

    @ViewById
    ViewPager vp_petition;

    @ViewById
    EditText et_search;

    PetitionChildernFragment childernFragment1;
    PetitionChildernFragment childernFragment2;
    PetitionChildernFragment childernFragment3;
    PetitionChildernFragment childernFragment4;
    private List<String> titleData;

    @AfterViews
    void initViews() {
        titleData = new ArrayList<>();
        titleData.add("全部");
        titleData.add("待解决");
        titleData.add("解决中");
        titleData.add("已解决");
        childernFragment1 = PetitionChildernFragment_.builder().build();
        childernFragment2 = PetitionChildernFragment_.builder().build();
        childernFragment3 = PetitionChildernFragment_.builder().build();
        childernFragment4 = PetitionChildernFragment_.builder().build();
        childernFragment1.setCategory(0);
        childernFragment2.setCategory(1);
        childernFragment3.setCategory(2);
        childernFragment4.setCategory(3);

        pagerAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public int getCount() {
                return 4;
            }

            @Override
            public Fragment getItem(int position) {
                if (position == 0) {
                    return childernFragment1;
                } else if (position == 1) {
                    return childernFragment2;
                } else if (position == 2) {
                    return childernFragment3;
                } else {
                    return childernFragment4;
                }
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titleData.get(position);

            }


        };
        vp_petition.setAdapter(pagerAdapter);
        tl_petition.setupWithViewPager(vp_petition);
        tl_petition.setTabMode(TabLayout.MODE_FIXED);
        tl_petition.setTabsFromPagerAdapter(pagerAdapter);
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                vp_petition.setCurrentItem(0);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (et_search.getText().toString().trim().length() == 0) {
                    childernFragment1.getData();
                } else {

                    childernFragment1.searchPetitionByCriteria(et_search.getText().toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Click(R.id.iv_add)
    void onViewClick() {
        PetitionNewActivity_.intent(getContext()).start();
    }
}