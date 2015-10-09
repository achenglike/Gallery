# Gallery
viewpager实现的Gallery库  
项目分为app(demo工程)，mylib(依赖库工程)  
Gallery的item可以自定义布局

###Gallery库的使用
##### 直接使用

```
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    xmlns:gallery="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context=".MainActivity"
    android:background="@android:color/darker_gray">

    <com.example.like.mylibrary.Gallery
        android:id="@+id/gallery"
        android:layout_width="match_parent"
        android:layout_height="match_parent">
    </com.example.like.mylibrary.Gallery>


</LinearLayout>
```

##### 定义Gallery  

定义Gallery主图片两侧的边距   gallery_padding(也可使用 gallery_padding_top等等)  
定义Gallery图片间的间距       gallery_pics_space  
定义Gallery图片缩小的最小比例 scaleP  
例如：
```
    <com.example.like.mylibrary.Gallery
        android:id="@+id/gallery"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        gallery:scaleP="0.9"
        gallery:gallery_pics_space="20dp"
        gallery:gallery_padding="50dp">
    </com.example.like.mylibrary.Gallery>
```  
### 为Gallery设置数据
参考了base-adapter-helper的实现方式，可以方便的为Gallery设置布局与数据
```
mQuickPagerAdapter = new QuickPagerAdapter<Bean>(this, R.layout.gallery_item_layout, data) {
            @Override
            protected void convertView(BaseAdapterHelper helper, final Bean item) {
                helper.setImageResource(R.id.imageview, item.getImgResId());
                helper.setText(R.id.textview, getString(item.getStrResId()));
                helper.setImageOnClickListener(R.id.imageview, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getBaseContext(), getString(item.getStrResId()), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        gallery.setAdapter(mQuickPagerAdapter);
```  


### 效果图

![image](https://github.com/achenglike/Gallery/raw/master/pic/GIF.gif)


### 参考内容
[Gallery滑动一页(一个Item)效果](http://www.trinea.cn/android/gallery-scroll-one-page/)   
base-adapter-helper 项目，但是已经找不到原来的地址，知道的可以给我说下
