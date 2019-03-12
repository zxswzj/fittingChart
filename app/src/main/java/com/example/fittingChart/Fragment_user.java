package com.example.fittingChart;


import android.content.Context;
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
    private OnFragmentInteractionListener listener;
    public Fragment_user() {
        // Required empty public constructor
    }


    //在Fragment被绘制后，调用此方法，可以初始化控件资源。
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("Fragment", "Fragment_user.onViewCreated");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.i("Fragment", "Fragment_user.onCreateView");

        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("Fragment", "Fragment_user.onDestroyView");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("Fragment", "Fragment_user.onPause");
    }

    //当onCreate()，onCreateView()，onViewCreated()方法执行完后调用，也就是Activity被渲染绘制出来后。
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("Fragment", "Fragment_user.onActivityCreated");
        et_user = (EditText)getActivity().findViewById(R.id.fragUser_userName);
        et_slogan = (EditText)getActivity().findViewById(R.id.fragUser_slogan);
        bt_update = (Button)getActivity().findViewById(R.id.fragUser_btn_update);
        Bundle bundle = getArguments();
        et_user.setText(bundle.getString("username"));
        et_slogan.setText(bundle.getString("slogan"));
        bt_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"Fragment1",Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                bundle.putString("username",et_user.getText().toString());
                bundle.putString("slogan",et_slogan.getText().toString());
                listener.OnClicked(et_user.getText().toString(), et_slogan.getText().toString());
            }
        });
    }

    public void onAmend(String strName, String strSlogan){
        Log.i("Fragment", "Fragment_user.onAmend");
        //et_user.setText(strName);
        //et_slogan.setText(strSlogan);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("Fragment", "Fragment_user.onDetach");

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i("Fragment", "Fragment_user.onAttach");
        if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Fragment", "Fragment_user.onCreate");

    }

    public interface OnFragmentInteractionListener {
        void OnClicked(String name, String slogan);
    }
}
