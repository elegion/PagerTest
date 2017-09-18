package com.elegion.pagertest.ui.main;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.elegion.pagertest.R;
import com.elegion.pagertest.util.ViewUtils;

/**
 * @author Max Kuznetsov on 18-Sep-17.
 */

public class PhotoPagerAdapter extends PagerAdapter {

    private static final int VIEW_PAGER_SIZE = 3;

    private int[] mImages = new int[]{
            R.drawable.ic_ny,
            R.drawable.ic_barcelona,
            R.drawable.ic_london
    };

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final LayoutInflater inflater = LayoutInflater.from(container.getContext());
        final View view = inflater.inflate(R.layout.pg_photo, container, false);
        final ImageView image = ViewUtils.findView(view, R.id.ivPhoto);
        image.setImageResource(mImages[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return VIEW_PAGER_SIZE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
