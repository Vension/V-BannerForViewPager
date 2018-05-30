package me.vension.banner.pageTransformer;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * ========================================================
 * 作  者：Vension
 * 日  期：2018/5/7 16:24
 * 描  述：层叠式ViewPager PageTransformer
 * ========================================================
 */

public class StackPageTransformer implements ViewPager.PageTransformer {
    private static final float CENTER_PAGE_SCALE = 0.8f;
    private int offscreenPageLimit;

    public StackPageTransformer(int offscreenPageLimit) {
        this.offscreenPageLimit = offscreenPageLimit;
    }

    @Override
    public void transformPage(@NonNull View view, float position) {
        if (position >= offscreenPageLimit) {
            view.setVisibility(View.GONE);
        } else {
            view.setVisibility(View.VISIBLE);
        }
        float translationX = (80 - view.getWidth()) * position;
        if (position >= 0) {
            view.setTranslationX(translationX);
        }
        if (position == 0) {
            view.setScaleX(CENTER_PAGE_SCALE);
            view.setScaleY(CENTER_PAGE_SCALE);
        } else {
            view.setScaleX(CENTER_PAGE_SCALE - position * 0.1f);
            view.setScaleY(CENTER_PAGE_SCALE - position * 0.1f);
        }
        ViewCompat.setElevation(view, (offscreenPageLimit - position) * 3);
//        ((CardView) view).setCardElevation((offscreenPageLimit - position) * 3);
    }


}
