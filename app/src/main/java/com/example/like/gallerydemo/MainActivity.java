package com.example.like.gallerydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.like.mylibrary.BaseAdapterHelper;
import com.example.like.mylibrary.Gallery;
import com.example.like.mylibrary.QuickPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Gallery gallery;
    QuickPagerAdapter<Bean> mQuickPagerAdapter;
    List<Bean> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data = new ArrayList<Bean>();
        data.add(new Bean(R.drawable.a, R.string.demo_string_a));
        data.add(new Bean(R.drawable.b, R.string.demo_string_b));
        data.add(new Bean(R.drawable.c, R.string.demo_string_c));
        data.add(new Bean(R.drawable.d, R.string.demo_string_d));
        data.add(new Bean(R.drawable.e, R.string.demo_string_e));
        data.add(new Bean(R.drawable.f, R.string.demo_string_f));
        data.add(new Bean(R.drawable.g, R.string.demo_string_g));
        data.add(new Bean(R.drawable.h, R.string.demo_string_h));
        data.add(new Bean(R.drawable.i, R.string.demo_string_i));
        data.add(new Bean(R.drawable.j, R.string.demo_string_j));
        data.add(new Bean(R.drawable.k, R.string.demo_string_k));
        gallery = (Gallery) findViewById(R.id.gallery);
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
