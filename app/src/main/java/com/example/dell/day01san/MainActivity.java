package com.example.dell.day01san;
//桑林颖

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.dell.day01san.adapter.FragAdapter;
import com.example.dell.day01san.fragment.DangDiFragment;
import com.example.dell.day01san.fragment.DingZhiFragment;
import com.example.dell.day01san.fragment.ShouYeFragment;
import com.example.dell.day01san.fragment.WoDeFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager vp;
    private TabLayout tab;
    private FragAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new ShouYeFragment());
        fragments.add(new DingZhiFragment());
        fragments.add(new DangDiFragment());
        fragments.add(new WoDeFragment());
        adapter = new FragAdapter(getSupportFragmentManager(), fragments, this);
        vp.setAdapter(adapter);
        tab.setupWithViewPager(vp);
        tab.getTabAt(0).setText("首页").setIcon(R.drawable.sh1);
        tab.getTabAt(1).setText("定制").setIcon(R.drawable.sh2);
        tab.getTabAt(2).setText("当地").setIcon(R.drawable.sh3);
        tab.getTabAt(3).setText("首页").setIcon(R.drawable.sh4);
    }

    private void initView() {
        vp = (ViewPager) findViewById(R.id.vp);
        tab = (TabLayout) findViewById(R.id.tab);
    }
}
