package com.example.like.mylibrary;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

public abstract class QuickPagerAdapter<T> extends PagerAdapter{

    private static final String TAG = QuickPagerAdapter.class.getSimpleName();

    private final int layoutResId;
  
    private final List<T> data;
  
    private final Context mContext;
    
    private final SparseArray<View> liveViews;
    // adapter destroyItem 时存放view
    private Stack<View> viewStack;
    
    public QuickPagerAdapter(Context mContext, int layoutResId) {
        this(mContext, layoutResId, null);
    }
    
    public QuickPagerAdapter(Context mContext, int layoutResId, List<T> data) {
        this.mContext = mContext;
        this.layoutResId = layoutResId;
        this.data = data==null ? new ArrayList<T>() : new ArrayList<T>(data);
        this.liveViews = new SparseArray<View>();
        viewStack = new Stack<View>();
    }

    public T getItem(int position) {
        if (position >= getCount()){
            return null;
        } 
        return data.get(position);
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        // TODO Auto-generated method stub
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        viewStack.push((View)object);
        liveViews.remove(position);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View convertView = null;
        if (viewStack != null && !viewStack.empty()) {
            try {
                convertView = viewStack.pop();
            } catch (Exception e) {
                convertView = null;
            }
        }
        final BaseAdapterHelper helper = BaseAdapterHelper.get(mContext, convertView, container, layoutResId, position);
        convertView(helper, getItem(position));
        final View view = helper.getView();
        container.addView(view, 0);
        VersionDiffUtils.scaleY(view, Constant.GALLEY_SCALE_RATE);
        VersionDiffUtils.scaleX(view, Constant.GALLEY_SCALE_RATE);
        
        liveViews.put(position, view);
        return view;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        VersionDiffUtils.scaleY(liveViews.get(position), 1f);
        VersionDiffUtils.scaleX(liveViews.get(position), 1f);
        super.setPrimaryItem(container, position, object);
    }
    
    public View getLiveView(int position) {
        return liveViews == null||liveViews.size() > 0 ? liveViews.get(position) : null;
    }
    
    public void add(T elem) {
        data.add(elem);
        notifyDataSetChanged();
    }

    public void add(int index,T elem) {
        data.add(index,elem);
        notifyDataSetChanged();
    }
    
    public void addAll(List<T> elem) {
        data.addAll(elem);
        notifyDataSetChanged();
    }

    public List<T> getAdapterList(){
        return data;
    }
    
    public void set(T oldElem, T newElem) {
        set(data.indexOf(oldElem), newElem);
    }

    public void set(int index, T elem) {
        data.set(index, elem);
        notifyDataSetChanged();
    }

    public void remove(T elem) {
        data.remove(elem);
        notifyDataSetChanged();
    }

    public void removeAll(List<T> elems){
        data.removeAll(elems);
        notifyDataSetChanged();
    }
    
    public int indexOfItem(T elem){
        return data.indexOf(elem);
    }
    
    public void remove(int index) {
        data.remove(index);
        notifyDataSetChanged();
    }

    public boolean contains(T elem) {
        return data.contains(elem);
    }

    /** Clear data list */
    public void clear() {
        data.clear();
        notifyDataSetChanged();
    }
    
    protected abstract void convertView(BaseAdapterHelper helper, T item);
}
