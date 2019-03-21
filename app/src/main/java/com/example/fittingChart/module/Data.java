package com.example.fittingChart.module;

import android.app.Application;

import com.example.fittingChart.R;

import java.util.ArrayList;
import java.util.List;

public class Data extends Application {
    public String TABLE_USER = "users";
    public int DATABASE_VERSION = 2;

    public List<FittingListItemData> breastList = new ArrayList<>();
    public List<FittingListItemData> shoulderList = new ArrayList<>();
    public List<FittingListItemData> backList = new ArrayList<>();
    public List<FittingListItemData> armList = new ArrayList<>();
    public List<FittingListItemData> bellyList = new ArrayList<>();
    public List<FittingListItemData> legList = new ArrayList<>();
    public List<FittingListItemData> brainList = new ArrayList<>();
    public List<FittingListItemData> otherList = new ArrayList<>();


    @Override
    public void onCreate() {
        breastList.add(new FittingListItemData("俯卧撑","就这样",R.drawable.ic_dashboard_black_24dp));
        breastList.add(new FittingListItemData("跪姿俯卧撑","做不了标准俯卧撑的小盆友们，半程膝盖着地比较适合你们",R.drawable.ic_dashboard_black_24dp));
        breastList.add(new FittingListItemData("扶墙俯卧撑","爽",R.drawable.ic_dashboard_black_24dp));

        shoulderList.add(new FittingListItemData("哑铃前平举","爽",R.drawable.ic_dashboard_black_24dp));
        shoulderList.add(new FittingListItemData("哑铃侧平举","爽",R.drawable.ic_dashboard_black_24dp));

        backList.add(new FittingListItemData("哑铃飞鸟","爽",R.drawable.ic_dashboard_black_24dp));

        armList.add(new FittingListItemData("哑铃肱二头肌弯举","爽",R.drawable.ic_dashboard_black_24dp));
        armList.add(new FittingListItemData("哑铃肱三头肌弯举","爽",R.drawable.ic_dashboard_black_24dp));

        bellyList.add(new FittingListItemData("卷腹","爽",R.drawable.ic_dashboard_black_24dp));
        bellyList.add(new FittingListItemData("仰卧起坐","爽",R.drawable.ic_dashboard_black_24dp));
        bellyList.add(new FittingListItemData("平板支撑","爽",R.drawable.ic_dashboard_black_24dp));

        legList.add(new FittingListItemData("深蹲","爽",R.drawable.ic_dashboard_black_24dp));
        legList.add(new FittingListItemData("靠墙蹲","爽",R.drawable.ic_dashboard_black_24dp));

        brainList.add(new FittingListItemData("最强大脑之数字迷宫","爽",R.drawable.ic_dashboard_black_24dp));

        otherList.add(new FittingListItemData("体重","爽",R.drawable.ic_dashboard_black_24dp));
        otherList.add(new FittingListItemData("波比跳","爽",R.drawable.ic_dashboard_black_24dp));

        super.onCreate();
    }
}