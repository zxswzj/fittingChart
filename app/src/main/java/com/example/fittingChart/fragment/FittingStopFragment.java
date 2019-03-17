package com.example.fittingChart.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;


import com.example.fittingChart.R;
import com.example.fittingChart.module.FittingData;
import com.example.fittingChart.ui.TimePicker.BaseFragment;
import com.example.fittingChart.ui.TimePicker.BasePicker;
import com.example.fittingChart.ui.TimePicker.PickerView;
import com.example.fittingChart.ui.autoLogin.DBHelper;
//import com.example.fittingChart.ui.TimePicker.TimePicker;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
import android.widget.TimePicker;
import android.widget.Toast;

/**
 * 创建时间：2018年01月31日15:49 <br>
 * 作者：fuchaoyang <br>
 * 描述：强大的type模式自由组合（当然应该是有意义的）
 */

public class FittingStopFragment extends Fragment {

    View view;
    private TimePicker timePicker;
    Button btn_save, btn_cancel;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Fragment", "FittingStopFragment.onCreate");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("Fragment", "FittingStopFragment.onCreateView");
        view = inflater.inflate(R.layout.fragment_fitting_stop, container, false);
        timePicker = view.findViewById(R.id.fitting_timePicker);
        btn_save = view.findViewById(R.id.fitting_btn_save);
        btn_cancel = view.findViewById(R.id.fitting_btn_cancel);

        timePicker.setIs24HourView(true);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay,
                                      int minute) {
                Toast.makeText(getActivity(),
                        hourOfDay + "小时" + minute + "分钟",
                        Toast.LENGTH_SHORT).show();
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            DBHelper db = new DBHelper(getContext());
            FittingData fd = new FittingData();
            fd.setNumber(16);
            fd.setTime("16:16");
            db.addFittingItem(fd, "pushup");
            db.getAllFitting("pushup");
//                long aa = db.myquery();
                //boolean hastable = db.isTableExist("table_users");
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }
}