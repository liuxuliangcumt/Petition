package com.realpower.petition.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.realpower.petition.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * Created by ruipu on 2018/8/21.
 */
@EFragment(R.layout.fragment_petitions)
public class PetitionSFragment extends BaseFragment {

    private PagerAdapter pagerAdapter;
    @ViewById
    TabLayout tl_petition;

    @ViewById
    ViewPager vp_petition;

    TextFragment textFragment;

    MeFragment meFragment;

    @AfterViews
    void initViews() {
        meFragment = MeFragment_.builder().build();
        textFragment = new TextFragment();
        pagerAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public Fragment getItem(int position) {
                if (position == 0) {
                    return meFragment;
                } else {

                    return textFragment;
                }
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return "标题" + position;

            }


        };
        vp_petition.setAdapter(pagerAdapter);
        tl_petition.setupWithViewPager(vp_petition);
        tl_petition.setTabMode(TabLayout.MODE_FIXED);
        tl_petition.setTabsFromPagerAdapter(pagerAdapter);
        tl_petition.addTab(tl_petition.newTab().setText("标题"));
        tl_petition.addTab(tl_petition.newTab().setText("标题"));
        tl_petition.addTab(tl_petition.newTab().setText("标题")); /* */
    }
}
