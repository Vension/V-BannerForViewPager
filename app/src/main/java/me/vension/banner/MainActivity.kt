package me.vension.banner

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.CompoundButton
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*
import me.vension.banner.pageTransformer.HorizontalStackTransformerWithRotation
import me.vension.banner.pageTransformer.StackPageTransformer
import me.vension.banner.pageTransformer.VerticalStackPageTransformerWithRotation
import me.vension.banner.transformer.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var mAdapter: MyPagerAdapter
    internal var imgRes = intArrayOf(R.drawable.kobe_1, R.drawable.kobe_2, R.drawable.kobe_3,
            R.drawable.kobe_4, R.drawable.kobe_5, R.drawable.kobe_6, R.drawable.kobe_7)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViewPager(viewpager_h,HorizontalStackTransformerWithRotation(this,viewpager_h))
        initViewPager(viewpager_v,VerticalStackPageTransformerWithRotation(this,viewpager_v))
        initViewPager(viewpager_s,StackPageTransformer(3))
        mAdapter = MyPagerAdapter(this, imgRes, false)
        my_viewpager.pageMargin = 40
        my_viewpager.offscreenPageLimit = 3
        my_viewpager.adapter = mAdapter
        my_viewpager.setPageTransformer(true, AlphaPageTransformer())

        cb_loop.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { compoundButton, b ->
            mAdapter = MyPagerAdapter(this, imgRes, b)
            my_viewpager.adapter = mAdapter
        })

    }


    private fun initViewPager(pager : ViewPager,transformer : ViewPager.PageTransformer) {
        pager.offscreenPageLimit = 3
        pager.adapter = BannerAdapter(getViews())
        pager.setPageTransformer(true,transformer)
    }

    private fun getViews(): List<View> {
        val views = ArrayList<View>()
        for (i in 0..4) {
            val mImageView = ImageView(this)
            mImageView.setImageResource(R.mipmap.img_banner_4)
            views.add(mImageView)
        }
        return views
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val effects = this.resources.getStringArray(R.array.magic_effect)
        for (effect in effects)
            menu.add(effect)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val title = item.title.toString()
        my_viewpager.adapter = mAdapter

        when (title) {
            "RotateDown" -> my_viewpager.setPageTransformer(true, RotateDownPageTransformer())
            "RotateUp" -> my_viewpager.setPageTransformer(true, RotateUpPageTransformer())
            "RotateY" -> my_viewpager.setPageTransformer(true, RotateYTransformer(45f))
            "Standard" -> //            mViewPager.setClipChildren(false);
                my_viewpager.setPageTransformer(true, NonPageTransformer.INSTANCE)
            "Alpha" -> //            mViewPager.setClipChildren(false);
                my_viewpager.setPageTransformer(true, AlphaPageTransformer())
            "ScaleIn" -> my_viewpager.setPageTransformer(true, ScaleInTransformer())
            "RotateDown and Alpha" -> my_viewpager.setPageTransformer(true, RotateDownPageTransformer(AlphaPageTransformer()))
            "RotateDown and Alpha And ScaleIn" -> my_viewpager.setPageTransformer(true, RotateDownPageTransformer(AlphaPageTransformer(ScaleInTransformer())))
        }

        setTitle(title)

        return true
    }
}
