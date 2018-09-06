package com.realpower.petition.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.realpower.petition.R;


/**
 * Created by Administrator on 2017/11/9.
 */
public class DataGenerator {

   /* public static final int[] mTabRes = new int[]{R.drawable.main_petition_d, R.drawable.main_list_d, R.drawable.bai, R.drawable.main_me_d};
    public static final int[] mTabResPressed = new int[]{R.drawable.main_petition_c, R.drawable.main_suggest_c, R.drawable.main_me_c};
    public static final String[] mTabTitle = new String[]{"诉求", "意见", "我的"};*/

    public static final int[] mTabRes = new int[]{R.mipmap.home_normal_icon, R.mipmap.appeal_normal_icon, R.mipmap.opinion_normal_icon, R.mipmap.user_normal_icon};
    public static final int[] mTabResPressed = new int[]{R.mipmap.home_press_icon, R.mipmap.appeal_press_icon, R.mipmap.opinion_press_icon, R.mipmap.user_press_icon};
    public static final String[] mTabTitle = new String[]{"首页", "诉求", "意见", "我的"};

    /*
     public static final int []mTabRes = new int[]{R.drawable.tab_home_selector,R.drawable.tab_discovery_selector,R.drawable.tab_attention_selector,R.drawable.tab_profile_selector};
    public static final int []mTabResPressed = new int[]{R.drawable.ic_tab_strip_icon_feed_selected,R.drawable.ic_tab_strip_icon_category_selected,R.drawable.ic_tab_strip_icon_pgc_selected,R.drawable.ic_tab_strip_icon_profile_selected};
    public static final String []mTabTitle = new String[]{"首页","发现","关注","我的"};

     */
  /*  public static Fragment[] getFragments(String from) {
        Fragment fragments[] = new Fragment[4];
        fragments[0] = HomeFragment.newInstance(from);
        fragments[1] = DiscoveryFragment.newInstance(from);
        fragments[2] = AttentionFragment.newInstance(from);
        fragments[3] = ProfileFragment.newInstance(from);
        return fragments;
    }*/

    /**
     * 获取Tab 显示的内容
     *
     * @param context
     * @param position
     * @return
     */
    public static View getTabView(Context context, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.tab_layout, null);
        ImageView tabIcon = (ImageView) view.findViewById(R.id.tab_content_image);
        tabIcon.setImageResource(DataGenerator.mTabRes[position]);
        TextView tabText = (TextView) view.findViewById(R.id.tab_content_text);
        tabText.setText(mTabTitle[position]);
        return view;
    }
}
