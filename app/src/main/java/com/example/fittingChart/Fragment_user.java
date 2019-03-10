package com.example.fittingChart;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_user extends Fragment {

    private EditText et_user;
    private EditText et_slogan;
    private ImageView iv_user;
    private Button bt_update;

    public Fragment_user() {
        // Required empty public constructor
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("Fragment", "Fragment_user.onViewCreated: ");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.i("Fragment", "Fragment_user.onCreateView: ");

        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("Fragment", "Fragment_user.onActivityCreated: ");
        et_user = (EditText)getActivity().findViewById(R.id.fragUser_userName);
        et_slogan = (EditText)getActivity().findViewById(R.id.fragUser_slogan);
        bt_update = (Button)getActivity().findViewById(R.id.fragUser_btn_update);
        bt_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"Fragment1",Toast.LENGTH_SHORT).show();
                et_user.setText(getArguments().getString("username"));
                et_slogan.setText(getArguments().getString("slogan"));
            }
        });
    }

    public void onAmend(String strName, String strSlogan){
        et_user.setText(strName);
        et_slogan.setText(strSlogan);
    }
}
