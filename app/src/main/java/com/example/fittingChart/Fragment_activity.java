package com.example.fittingChart;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_activity extends Fragment {

    EditText et_name;
    EditText et_slogan;


    public Fragment_activity() {
        // Required empty public constructor
        Log.i("Fragment","Fragment_activity.Fragment_activity");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.i("Fragment","Fragment_activity.onCreateView");
        Bundle bundle = this.getArguments();
        if(bundle != null)
        {
            String strName = bundle.getString("name");
            String strSlogan = bundle.getString("slogan");
        }
        return inflater.inflate(R.layout.fragment_activity, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("Fragment","Fragment_activity.onViewCreated");
//        et_name = (EditText)view.findViewById(R.id.fragUser_userName);
//        et_slogan = (EditText)view.findViewById(R.id.fragUser_slogan);
//        String strName = savedInstanceState.getString("name");
//        String strSlogan = savedInstanceState.getString("slogan");
//        et_name.setText(strName);
//        et_name.setText(strSlogan);
    }

    public void btn_update_user_info_cb(View view){
        Log.i("SQLite","btn_update_user_info_cb");

//        List<Users> users = db.getAllUsers();
//        for (Users cn : users) {
//            String log = "Id: "+cn.getID()+" ,Name: " + cn.getUsername() + " ,Slogan: " + cn.getSlogan();
//            Log.i("SQLite: ", log);
//        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
