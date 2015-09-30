package com.example.like.mylibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;


public class Gallery extends RelativeLayout {

    private ViewPager   viewPager;
    private static int OFFSCREEN_PAGE_LIMIT = 2;
    private QuickPagerAdapter<Object> mQuickPagerAdapter;

    private int galleryPaddingLeft = getResources().getDimensionPixelSize(R.dimen.gallery_left_margin);
    private int galleryPaddingRight = getResources().getDimensionPixelSize(R.dimen.gallery_right_margin);
    private int galleryPaddingTop = getResources().getDimensionPixelSize(R.dimen.gallery_top_margin);
    private int galleryPaddingBottom = getResources().getDimensionPixelSize(R.dimen.gallery_bottom_margin);
    private int galleryCenterMargin = getResources().getDimensionPixelSize(R.dimen.gallery_center_margin);

    public Gallery(Context context) {
        this(context, null);
    }

    public Gallery(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Gallery(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Gallery);
        Constant.GALLEY_SCALE_RATE = a.getFloat(R.styleable.Gallery_scaleP, Constant.GALLEY_SCALE_RATE);
        int galleryPadding = a.getDimensionPixelSize(R.styleable.Gallery_gallery_padding, 0);
        if (galleryPadding > 0) {
            galleryPaddingLeft = galleryPaddingRight = galleryPaddingTop = galleryPaddingBottom = galleryPadding;
        } else {
            galleryPaddingLeft = a.getDimensionPixelSize(R.styleable.Gallery_gallery_padding_left, galleryPaddingLeft);
            galleryPaddingRight = a.getDimensionPixelSize(R.styleable.Gallery_gallery_padding_right, galleryPaddingRight);
            galleryPaddingTop = a.getDimensionPixelSize(R.styleable.Gallery_gallery_padding_top, galleryPaddingTop);
            galleryPaddingBottom = a.getDimensionPixelSize(R.styleable.Gallery_gallery_padding_bottom, galleryPaddingBottom);
        }
        galleryCenterMargin = a.getDimensionPixelSize(R.styleable.Gallery_gallery_pics_space, galleryCenterMargin);
        a.recycle();
        
        LayoutInflater.from(context).inflate(R.layout.gallery_layout, this, true);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        ((MarginLayoutParams)viewPager.getLayoutParams()).setMargins(galleryPaddingLeft, galleryPaddingTop, galleryPaddingRight, galleryPaddingBottom);
        viewPager.setPageMargin(galleryCenterMargin);

        viewPager.setOffscreenPageLimit(OFFSCREEN_PAGE_LIMIT);
        setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return viewPager.dispatchTouchEvent(event);
            }
        });
        viewPager.addOnPageChangeListener(new PageChangeListener());
    }
    
    public class PageChangeListener implements OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            updateAnimation(arg0, arg1);
            invalidate();

        }

        @Override
        public void onPageSelected(int arg0) {

        }
        
    }
    
    /**
     * 更新pager变动动画
     * @param leftPostion 左侧pager的position
     * @param seed 变动因子，当前页面偏移的百分比
     */
    public void updateAnimation(int leftPostion, float seed) {
        if (seed < 0.5) {
            VersionDiffUtils.scaleY(mQuickPagerAdapter.getLiveView(leftPostion),
                    Constant.GALLEY_SCALE_RATE + (0.5f-seed)/0.5f * (1f-Constant.GALLEY_SCALE_RATE));
            VersionDiffUtils.scaleX(mQuickPagerAdapter.getLiveView(leftPostion),
                    Constant.GALLEY_SCALE_RATE + (0.5f-seed)/0.5f * (1f-Constant.GALLEY_SCALE_RATE));
        } else {
            VersionDiffUtils.scaleY(mQuickPagerAdapter.getLiveView(leftPostion+1),
                    Constant.GALLEY_SCALE_RATE + (seed-0.5f)/0.5f * (1f-Constant.GALLEY_SCALE_RATE));
            VersionDiffUtils.scaleX(mQuickPagerAdapter.getLiveView(leftPostion+1),
                    Constant.GALLEY_SCALE_RATE + (seed-0.5f)/0.5f * (1f-Constant.GALLEY_SCALE_RATE));
        }
        
    }

    public void setAdapter(QuickPagerAdapter adapter) {
        viewPager.setAdapter(adapter);
        mQuickPagerAdapter = adapter; 
    }
  
}
