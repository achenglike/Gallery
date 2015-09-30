package com.example.like.mylibrary;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class BaseAdapterHelper {

    private final SparseArray<View> views;

    private View convertView;
    
    private int position;
    
    public BaseAdapterHelper(Context context, ViewGroup parent, int layoutId, 
            int position) {
        this.position = position;
        this.views = new SparseArray<View>();
        this.convertView = LayoutInflater.from(context)
                .inflate(layoutId, parent, false);
        this.convertView.setTag(this);
    }
    
    public static BaseAdapterHelper get(Context context, View convertView,
            ViewGroup parent, int layoutId) {
        return get(context, convertView, parent, layoutId, -1);
    }

    public static BaseAdapterHelper get(Context context, View convertView,
            ViewGroup parent, int layoutId, int position) {
        if (convertView == null) {
            return new BaseAdapterHelper(context, parent, layoutId, position);
        }
        BaseAdapterHelper adapter = (BaseAdapterHelper) convertView.getTag();
        adapter.setPosition(position);
        return adapter;
    }
    
    /**
     * Retrieve the overall position of the data in the list.
     * 
     * @throws IllegalArgumentException
     *             If the position hasn't been set at the construction of the
     *             this helper.
     */
    public int getPosition() {
        if (position == -1)
            throw new IllegalStateException(
                    "Use BaseAdapterHelper constructor "
                            + "with position if you need to retrieve the position.");
        return position;
    }
    
    private void setPosition(int position) {
        this.position = position;
    }
    
    public View getView() {
        return convertView;
    }
    
    public <T extends View> T getView(int viewId) {
        return retrieveView(viewId);
    }

    public BaseAdapterHelper setText(int viewId, String value) {
        TextView view = retrieveView(viewId);
        view.setText(value);
        return this;
    }
    
    public BaseAdapterHelper setTextColor(int viewId, int color) {
        TextView view = retrieveView(viewId);
        view.setTextColor(color);
        return this;
    }
    
    public BaseAdapterHelper setTextEnabled(int viewId,Boolean enabled){
        TextView view = retrieveView(viewId);
        view.setEnabled(enabled);
        return this;
    }
    
    public BaseAdapterHelper setTextOnClickListener(int viewId,OnClickListener listener){
        TextView view = retrieveView(viewId);
        view.setOnClickListener(listener);
        return this;
    }
    
    public BaseAdapterHelper setLayoutOnClickListener(int viewId,OnClickListener listener){
        RelativeLayout view = retrieveView(viewId);
        view.setOnClickListener(listener);
        return this;
    }
    
    public BaseAdapterHelper setImageOnClickListener(int viewId,OnClickListener listener){
        ImageView view = retrieveView(viewId);
        view.setOnClickListener(listener);
        return this;
    }
    
    public BaseAdapterHelper setViewBackground(int viewId,int resid){
        View view = retrieveView(viewId);
        view.setBackgroundResource(resid);
        return this;
    }

    public BaseAdapterHelper setImageResource(int viewId, int imageResId) {
        ImageView view = retrieveView(viewId);
        view.setImageResource(imageResId);
        return this;
    }

    public BaseAdapterHelper setImageDrawable(int viewId, Drawable drawable) {
        ImageView view = retrieveView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    public BaseAdapterHelper setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView view = retrieveView(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }

    public BaseAdapterHelper setAlpha(int viewId, float value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            retrieveView(viewId).setAlpha(value);
        } else {
            // Pre-honeycomb hack to set Alpha value
            AlphaAnimation alpha = new AlphaAnimation(value, value);
            alpha.setDuration(0);
            alpha.setFillAfter(true);
            retrieveView(viewId).startAnimation(alpha);
        }
        return this;
    }

    public BaseAdapterHelper setVisible(int viewId, boolean visible) {
        View view = retrieveView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    public BaseAdapterHelper linkify(int viewId) {
        TextView view = retrieveView(viewId);
        Linkify.addLinks(view, Linkify.ALL);
        return this;
    }

    public BaseAdapterHelper setTypeface(int viewId, Typeface typeface) {
        TextView view = retrieveView(viewId);
        view.setTypeface(typeface);
        return this;
    }

    public BaseAdapterHelper setTypeface(Typeface typeface, int... viewIds) {
        for (int viewId : viewIds) {
            TextView view = retrieveView(viewId);
            view.setTypeface(typeface);
        }
        return this;
    }

    public BaseAdapterHelper setProgress(int viewId, int progress) {
        ProgressBar view = retrieveView(viewId);
        view.setProgress(progress);
        return this;
    }

    public BaseAdapterHelper setProgress(int viewId, int progress, int max) {
        ProgressBar view = retrieveView(viewId);
        view.setProgress(progress);
        view.setMax(max);
        return this;
    }

    public BaseAdapterHelper setMax(int viewId, int max) {
        ProgressBar view = retrieveView(viewId);
        view.setMax(max);
        return this;
    }

    public BaseAdapterHelper setRating(int viewId, float rating) {
        RatingBar view = retrieveView(viewId);
        view.setRating(rating);
        return this;
    }
    
    public BaseAdapterHelper setRating(int viewId, float rating, int max) {
        RatingBar view = retrieveView(viewId);
        view.setRating(rating);
        view.setMax(max);
        return this;
    }
    
    @SuppressWarnings("unchecked")
    private <T extends View> T retrieveView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = convertView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }
}
