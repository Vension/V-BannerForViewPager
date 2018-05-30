package me.vension.banner;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author ：Created by Administrator on 2018/5/29 15:15.
 * @email：kevin-vension@foxmail.com
 * @desc character determines attitude, attitude determines destiny
 */
public class BannerAdapter extends PagerAdapter {
	private List<View> viewLists;

	public BannerAdapter(List<View> viewLists) {
		this.viewLists = viewLists;
	}

	@Override
	public int getCount() {                                                                 //获得size
		return viewLists.size();
	}


	@Override
	public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
		return view == object;
	}

	@Override
	public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
		((ViewPager) container).removeView(viewLists.get(position));
	}

	@NonNull
	@Override
	public Object instantiateItem(@NonNull ViewGroup container, int position) {
		((ViewPager) container).addView(viewLists.get(position), 0);
		return viewLists.get(position);
	}
}
