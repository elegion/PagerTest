package com.elegion.pagertest.util;

import android.app.Activity;
import android.content.res.Resources;
import android.support.annotation.IdRes;
import android.view.View;

/**
 * @author Max Kuznetsov on 18-Sep-17.
 */

public class ViewUtils {

    @SuppressWarnings("unchecked")
    public static <T> T findView(Activity activity, @IdRes int id) {
        return (T) activity.findViewById(id);
    }

    @SuppressWarnings("unchecked")
    public static <T> T findView(View view, @IdRes int id) {
        return (T) view.findViewById(id);
    }

    public static int dpToPx(float dp) {
        float density = Resources.getSystem().getDisplayMetrics().density;
        return Math.round(dp * density);
    }

}
