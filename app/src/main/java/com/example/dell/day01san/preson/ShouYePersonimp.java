package com.example.dell.day01san.preson;

import com.example.dell.day01san.bean.Ben;
import com.example.dell.day01san.callback.ShouCallBack;
import com.example.dell.day01san.model.ShouYeModel;
import com.example.dell.day01san.view.ShouYeView;

public class ShouYePersonimp implements ShouYePerson, ShouCallBack {
    private ShouYeModel shouYeModel;
    private ShouYeView shouYeView;

    public ShouYePersonimp(ShouYeModel shouYeModel, ShouYeView shouYeView) {
        this.shouYeModel = shouYeModel;
        this.shouYeView = shouYeView;
    }

    @Override
    public void p() {
        shouYeModel.m(this);
    }

    @Override
    public void cheng(Ben ben) {
        shouYeView.cheng(ben);
    }
}
