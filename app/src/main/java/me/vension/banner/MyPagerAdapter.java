package me.vension.banner;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author ：Created by Administrator on 2018/5/29 17:26.
 * @email：kevin-vension@foxmail.com
 * @desc character determines attitude, attitude determines destiny
 */
public class MyPagerAdapter extends PagerAdapter {

	private Context mContext;//上下文
    private boolean isLoop = false;//是否支持无限循环
    private int[] lists;//图片资源集合


	public MyPagerAdapter(Context mContext, int[] imgRes,boolean isLoop) {
		this.mContext = mContext;
		this.lists = imgRes;
		this.isLoop = isLoop;
	}


	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		View itemView = View.inflate(mContext,R.layout.item_viewpager,null);
		ImageView ivBanner = itemView.findViewById(R.id.iv_banner);
		TextView tvTips = itemView.findViewById(R.id.tv_banner_tips);
		if (isLoop){
			position = getRealPosition(position);
			ivBanner.setImageResource(lists[position]);
		}else{
			ivBanner.setImageResource(lists[position]);
			tvTips.setText("position：" + position);
		}
		container.addView(itemView);
		final int finalPosition = position;
		itemView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(mContext, "click position= " + finalPosition, Toast.LENGTH_SHORT).show();
			}
		});
		return itemView;
	}


	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}


	@Override
	public int getItemPosition(Object object) {
		return isLoop ? POSITION_NONE : super.getItemPosition(object);
	}

	@Override
	public int getCount() {
		return isLoop ? Integer.MAX_VALUE : lists.length;
	}

	@Override
	public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
		return view == object;
	}


	@Override
	public void startUpdate(ViewGroup container) {
		if (isLoop){
			ViewPager viewPager = (ViewPager) container;
			int position = viewPager.getCurrentItem();
			if (position == 0) {
				position = getFirstItemPosition();
			} else if (position == getCount() - 1) {
				position = getLastItemPosition();
			}
			viewPager.setCurrentItem(position, false);
		}else{
			super.startUpdate(container);
		}
	}


	private int getRealCount() {
		return lists.length;
	}

	private int getRealPosition(int position) {
		return position % getRealCount();
	}

	private int getFirstItemPosition() {
		return Integer.MAX_VALUE / getRealCount() / 2 * getRealCount();
	}

	private int getLastItemPosition() {
		return Integer.MAX_VALUE / getRealCount() / 2 * getRealCount() - 1;
	}

	public void setLoop(boolean b) {
		this.isLoop = b;
		this.notifyDataSetChanged();
	}
}
