package com.example.fittingChart.module;

import android.app.Application;

import com.example.fittingChart.R;
import com.example.fittingChart.database.MyDatabaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class Data extends Application {
    public String TABLE_USER = "users";
    public int DATABASE_VERSION = 1;
    MyDatabaseAdapter dbAdapter;

    public static final String TABLE_FITTING_BREAST = "fitting_breast";
    public static final String TABLE_FITTING_SHOULDER = "fitting_shoulder";
    public static final String TABLE_FITTING_BACK = "fitting_back";
    public static final String TABLE_FITTING_ARM = "fitting_arm";
    public static final String TABLE_FITTING_BELLY = "fitting_belly";
    public static final String TABLE_FITTING_LEG = "fitting_leg";
    public static final String TABLE_FITTING_BRAIN = "fitting_brain";
    public static final String TABLE_FITTING_OTHER = "fitting_other";


    public List<FittingTableData> breastList = new ArrayList<>();
    public List<FittingTableData> shoulderList = new ArrayList<>();
    public List<FittingTableData> backList = new ArrayList<>();
    public List<FittingTableData> armList = new ArrayList<>();
    public List<FittingTableData> bellyList = new ArrayList<>();
    public List<FittingTableData> legList = new ArrayList<>();
    public List<FittingTableData> brainList = new ArrayList<>();
    public List<FittingTableData> otherList = new ArrayList<>();


    @Override
    public void onCreate() {
//        DBHelper db = new DBHelper(getBaseContext());
//        db.openDatabase();
//        db.closeDatabase();
        //dbAdapter = new MyDatabaseAdapter(getApplicationContext());
        //dbAdapter.open();
        breastList.add(new FittingTableData("俯卧撑","FUWOCHENG","就这样",R.drawable.pushup));
        breastList.add(new FittingTableData("跪姿俯卧撑","GUIZIFUWOCHENG","做不了标准俯卧撑的小盆友们，半程膝盖着地比较适合你们",R.drawable.knee_pushup));
        breastList.add(new FittingTableData("扶墙俯卧撑","FUQIANGFUWOCHENG","爽",R.drawable.ic_dashboard_black_24dp));
        breastList.add(new FittingTableData("add","ADD","爽",R.drawable.icon_add));

        shoulderList.add(new FittingTableData("哑铃前平举","YALINGQIANPINGJU","爽",R.drawable.ic_dashboard_black_24dp));
        shoulderList.add(new FittingTableData("哑铃侧平举","YALINGCEPINGJU","爽",R.drawable.ic_dashboard_black_24dp));
        shoulderList.add(new FittingTableData("add","ADD","爽",R.drawable.icon_add));

        backList.add(new FittingTableData("哑铃飞鸟","YALINGFEINIAO","爽",R.drawable.ic_dashboard_black_24dp));
        backList.add(new FittingTableData("add","ADD","爽",R.drawable.icon_add));

        armList.add(new FittingTableData("哑铃肱二头肌弯举","YALINGGONGERTOUJIWANJU","爽",R.drawable.ic_dashboard_black_24dp));
        armList.add(new FittingTableData("哑铃肱三头肌弯举","YALINGGONGSANTOUJIWANJU","爽",R.drawable.ic_dashboard_black_24dp));
        armList.add(new FittingTableData("add","ADD","爽",R.drawable.icon_add));

        bellyList.add(new FittingTableData("卷腹","JUANFU","爽",R.drawable.ic_dashboard_black_24dp));
        bellyList.add(new FittingTableData("仰卧起坐","YANGWOQIZUO","爽",R.drawable.ic_dashboard_black_24dp));
        bellyList.add(new FittingTableData("平板支撑","PINGBANZHICHENG","爽",R.drawable.ic_dashboard_black_24dp));
        bellyList.add(new FittingTableData("add","ADD","爽",R.drawable.icon_add));

        legList.add(new FittingTableData("深蹲","SHENDUN","爽",R.drawable.ic_dashboard_black_24dp));
        legList.add(new FittingTableData("靠墙蹲","KAOQIANGDUN","爽",R.drawable.ic_dashboard_black_24dp));
        legList.add(new FittingTableData("add","ADD","爽",R.drawable.icon_add));

        brainList.add(new FittingTableData("最强大脑之数字迷宫","ZUIQIANGDANAOZHISHUZIMIGONG","爽",R.drawable.ic_dashboard_black_24dp));
        brainList.add(new FittingTableData("add","ADD","爽",R.drawable.icon_add));

        otherList.add(new FittingTableData("体重","TIZHONG","爽",R.drawable.ic_dashboard_black_24dp));
        otherList.add(new FittingTableData("波比跳","BOBITIAO","爽",R.drawable.ic_dashboard_black_24dp));
        otherList.add(new FittingTableData("开合跳","KAIHETIAO","爽",R.drawable.ic_dashboard_black_24dp));
        otherList.add(new FittingTableData("add","ADD","爽",R.drawable.icon_add));

        super.onCreate();
    }
}