package com.example.dell.day01disi.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dell.day01disi.R;
import com.example.dell.day01disi.adapter.FragAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DingFragment extends Fragment {


    private TabLayout tab;
    private ViewPager vp;
    private FragAdapter adapter;

    public DingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ding, container, false);
        initView(view);
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new OuFragment());
        fragments.add(new DongNanFragment());
        fragments.add(new HaiFragment());
        fragments.add(new RiFragment());
        fragments.add(new AoFragment());
        adapter = new FragAdapter(getChildFragmentManager(), fragments, getContext());
        vp.setAdapter(adapter);
        tab.setupWithViewPager(vp);
        tab.getTabAt(0).setText("欧美");
        tab.getTabAt(1).setText("东南亚");
        tab.getTabAt(2).setText("海岛");
        tab.getTabAt(3).setText("日系");
        tab.getTabAt(4).setText("奥斯其他");
        return view;
    }

    private void initView(View view) {
        tab = (TabLayout) view.findViewById(R.id.tab);
        vp = (ViewPager) view.findViewById(R.id.vp);
    }
}
