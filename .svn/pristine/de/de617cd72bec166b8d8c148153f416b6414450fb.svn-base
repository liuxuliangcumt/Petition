package com.realpower.petition.fragment;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.realpower.petition.R;
import com.realpower.petition.activity.LegalaidActivity_;
import com.realpower.petition.activity.LegalcaseActivity_;
import com.realpower.petition.activity.NewDetailActivity_;
import com.realpower.petition.activity.NoticeActivity_;
import com.realpower.petition.activity.RegulationActivity_;
import com.realpower.petition.adapter.HomeAdapter;
import com.realpower.petition.bean.NewsBean;
import com.realpower.petition.net.MyCallback;
import com.realpower.petition.net.param.PageParam;
import com.realpower.petition.net.result.NewsResult;
import com.realpower.petition.util.GlideRoundTransform;
import com.realpower.petition.views.CustomListView;
import com.realpower.petition.views.MyScrollView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import me.crosswall.lib.coverflow.core.PagerContainer;
import retrofit2.Call;

/**
 */
@EFragment(R.layout.fragment_home)
public class HomeFragment extends BaseFragment {
    @ViewById
    PagerContainer pager_container;
    private List<String> imgData;

    @ViewById
    CustomListView lv_home;
    List<NewsBean.ListBean> data;
    HomeAdapter adapter;
    @ViewById
    MyScrollView sv;

    //政策法规，法律援助，法律案例  regulation，legalaid，legalcase
    @AfterViews
    void initView() {
        imgData = new ArrayList<>();
        imgData.add("http://pic2.16pic.com/00/12/41/16pic_1241515_b.jpg");
        imgData.add("http://pic2.16pic.com/00/12/41/16pic_1241515_b.jpg");
        imgData.add("http://pic2.16pic.com/00/12/41/16pic_1241515_b.jpg");
        imgData.add("http://file04.16sucai.com/d/file/2015/0510/188a09d6bdacd85284c2058aed01647b.jpg");
        imgData.add("http://img18.3lian.com/d/file/201709/21/f498e01633b5b704ebfe0385f52bad20.jpg");
        imgData.add("http://a0.att.hudong.com/54/49/01300542891809141879496908378.jpg");
        imgData.add("http://img1.imgtn.bdimg.com/it/u=3323017670,3063552351&fm=27&gp=0.jpg");

        ViewPager pager = pager_container.getViewPager();
        pager.setAdapter(new MyPagerAdapter());
        pager.setClipChildren(false);
        //
        pager.setOffscreenPageLimit(15);

        if (false) {
           /* new CoverFlow.Builder()
                    .with(pager)
                    .scale(0.3f)
                    .pagerMargin(getResources().getDimensionPixelSize(R.dimen.pager_margin))
                    .spaceSize(0f)
                    .build();*/

        } else {
            pager.setPageMargin(30);
        }
        data = new ArrayList<>();

        adapter = new HomeAdapter(getContext(), data);
        lv_home.setAdapter(adapter);
        lv_home.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewDetailActivity_.intent(getActivity()).bean(data.get(position)).start();
            }
        });
        getNewsData();
    }

    private void getNewsData() {
        Call<NewsResult> call = apiService.getNews(new PageParam(1));
        call.enqueue(new MyCallback<NewsResult>() {
            @Override
            public void onSuccessRequest(NewsResult result) {
                if ("1".equals(result.getStatus())) {
                    setNewData(result.getMessage().getList());
                }


            }

            @Override
            public void afterRequest() {

            }

            @Override
            public void onFailureRequest(Call<NewsResult> call, Throwable t) {

            }
        });
    }

    private void setNewData(List<NewsBean.ListBean> list) {
        data.clear();
        data.addAll(list);
        adapter.notifyDataSetChanged();
    }


    private class MyPagerAdapter extends PagerAdapter {

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            RequestOptions myOptions = new RequestOptions()

                    .transform(new GlideRoundTransform(30));
            View view = LayoutInflater.from(getContext()).inflate(R.layout.banner, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.iv_banner);
            //   imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Glide.with(getContext()).load(imgData.get(position)).apply(myOptions).into(imageView);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return imgData.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return (view == object);
        }
    }


    @Click({R.id.ll_notice, R.id.ll_policy, R.id.ll_flow, R.id.ll_Legalcas, R.id.ll_legalaid})
    void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.ll_notice:
                NoticeActivity_.intent(getContext()).start();
                break;
            case R.id.ll_policy:
                RegulationActivity_.intent(getContext()).start();
                break;
            case R.id.ll_flow:

                break;
            case R.id.ll_Legalcas:
                LegalcaseActivity_.intent(getContext()).start();
                break;
            case R.id.ll_legalaid:
                LegalaidActivity_.intent(getContext()).start();
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            sv.setScrollY(sv.getScrollY());
        }
    }
}
