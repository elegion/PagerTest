package com.elegion.pagertest.ui.main;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.elegion.pagertest.R;
import com.elegion.pagertest.util.ViewUtils;
import com.elegion.pagertest.widget.CircleIndicator;


public class MainActivity extends AppCompatActivity {

    public final static int DIRECTION_LEFT = -1;
    public final static int DIRECTION_RIGHT = 1;
    public final static int DIRECTION_NONE = 0;

    private int[] mTitles = new int[]{
            R.string.page_one_title,
            R.string.page_two_title,
            R.string.page_three_title
    };

    private ViewPager mViewPager;
    private CircleIndicator mPageIndicator;
    private TextView mCurrentTitle;
    private TextView mTitleOfNeighbour;

    private PhotoPagerAdapter mPagerAdapter = new PhotoPagerAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_main);
        bindViews();
        setUpViewPager();
        setPagerTitles();
    }

    private void bindViews() {
        mViewPager = ViewUtils.findView(this, R.id.vpPhoto);
        mPageIndicator = ViewUtils.findView(this, R.id.ciPages);
        mCurrentTitle = ViewUtils.findView(this, R.id.tvCurrentTitle);
        mTitleOfNeighbour = ViewUtils.findView(this, R.id.tvNeighbourTitle);
    }

    private void setUpViewPager() {
        mViewPager.setAdapter(mPagerAdapter);
        mPageIndicator.setViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(new OnPhotoPageChangeListener());
    }

    private void setPagerTitles() {
        mCurrentTitle.setText(mTitles[0]);
        mTitleOfNeighbour.setText(mTitles[1]);
        mTitleOfNeighbour.setAlpha(0);
    }

    private class OnPhotoPageChangeListener extends ViewPager.SimpleOnPageChangeListener {

        private int mCurrentPosition = 0;
        @SwipeDirection int mDirection = DIRECTION_NONE;

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (mCurrentPosition == position) {
                //swipe right
                mCurrentTitle.setAlpha(1 - positionOffset);
                mTitleOfNeighbour.setAlpha(positionOffset);
                if (mDirection != DIRECTION_RIGHT && mTitles.length > position + 1) {
                    mTitleOfNeighbour.setText(mTitles[position + 1]);
                }
                mDirection = DIRECTION_RIGHT;
            } else if (mCurrentPosition > position) {
                //swipe left
                mCurrentTitle.setAlpha(positionOffset);
                mTitleOfNeighbour.setAlpha(1 - positionOffset);
                if (mDirection != DIRECTION_LEFT) {
                    mTitleOfNeighbour.setText(mTitles[position]);
                }
                mDirection = DIRECTION_LEFT;
            }
        }

        @Override
        public void onPageSelected(int position) {
            mCurrentPosition = position;
            mDirection = DIRECTION_NONE;

            TextView view = mCurrentTitle;
            mCurrentTitle = mTitleOfNeighbour;
            mTitleOfNeighbour = view;
        }
    }

}
