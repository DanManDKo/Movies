package com.example.danman.movies.ui.main.view_pager;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.danman.movies.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 09.12.2017.
 */

public class SectionsPagerAdapter extends FragmentPagerAdapter {
    private static final int PAGE_COUNT = 2;
    private Context mContext;
    private List<Fragment> mList;

    public SectionsPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
        mList = new ArrayList<>();

    }

    public void addFragment(Fragment fragment) {
        mList.add(fragment);
    }

    public void AddFragments(List<Fragment> fragments) {
        mList.addAll(fragments);
    }

    @Override
    public Fragment getItem(int position) {

        return mList.get(position);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return mContext.getString(R.string.page_title_popular);
            case 1:
                return mContext.getString(R.string.page_title_favorite);

        }
        return null;
    }
}
