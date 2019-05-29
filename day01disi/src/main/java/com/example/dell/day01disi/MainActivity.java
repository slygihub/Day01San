package com.example.dell.day01disi;
//桑林颖
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.dell.day01disi.adapter.FragAdapter;
import com.example.dell.day01disi.fragment.DangFragment;
import com.example.dell.day01disi.fragment.DingFragment;
import com.example.dell.day01disi.fragment.ShouFragment;
import com.example.dell.day01disi.fragment.WoFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager vp;
    private TabLayout tab;
    private FragAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

   //asdfghjkdfvebgrty
        initView();
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new ShouFragment());
        fragments.add(new DingFragment());
        fragments.add(new DangFragment());
        fragments.add(new WoFragment());
        adapter = new FragAdapter(getSupportFragmentManager(), fragments, this);
        vp.setAdapter(adapter);
        tab.setupWithViewPager(vp);
        tab.getTabAt(0).setText("首页").setIcon(R.drawable.sh1);
        tab.getTabAt(1).setText("定制").setIcon(R.drawable.sh2);
        tab.getTabAt(2).setText("当地玩乐").setIcon(R.drawable.sh3);
        tab.getTabAt(3).setText("我的").setIcon(R.drawable.sh4);
    }

    private void initView() {
        vp = (ViewPager) findViewById(R.id.vp);
        tab = (TabLayout) findViewById(R.id.tab);
    }
}
