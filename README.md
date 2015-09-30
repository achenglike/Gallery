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

定义Gallery主图片两侧的边距   scaleP  
定义Gallery图片间的间距       gallery_padding(也可使用 gallery_padding_top等等)  
定义Gallery图片缩小的最小比例 gallery_pics_space  
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

### 效果图

![image](https://github.com/achenglike/Gallery/raw/master/pic/GIF.gif)
