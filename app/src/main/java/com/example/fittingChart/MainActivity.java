package com.example.fittingChart;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private LineChart mLineChart;
    SQLiteDatabase db;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    setContentView(R.layout.login);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    setContentView(R.layout.activity_main);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

 /*
        mLineChart = findViewById(R.id.chart1);
        //显示边界
        mLineChart.setDrawBorders(true);
        //设置数据
        List<Entry> entry1 = new ArrayList<>();
        List<Entry> entry2 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            entry1.add(new Entry(i, new Random().nextInt(300)));
            entry2.add(new Entry(i, new Random().nextInt(200)));
        }
        //一个LineDataSet就是一条线
        LineDataSet lineDataSet1 = new LineDataSet(entry1, "Label");
        LineDataSet lineDataSet2 = new LineDataSet(entry2, "aaa");

        //LineData lineData  = new LineData(dataSet);
        List<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet1);
        dataSets.add(lineDataSet2);
        LineData lineData = new LineData(dataSets);

        mLineChart.setData(lineData);
        mLineChart.invalidate();
*/


        //SQLite
        //db=openOrCreateDatabase("UserDB", Context.MODE_PRIVATE, null);
        //db.execSQL("CREATE TABLE IF NOT EXISTS user(username VARCHAR,password VARCHAR);");

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
