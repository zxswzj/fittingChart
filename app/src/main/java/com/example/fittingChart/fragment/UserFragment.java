package com.example.fittingChart.fragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.fittingChart.R;
import com.example.fittingChart.database.FittingItem;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment {

    View view;
    private EditText et_user;
    private EditText et_slogan;
    private ImageView iv_user;
    private Button bt_update;
    private OnFragmentInteractionListener listener;
    LineChart pushupchart;
    private Uri imageUri;


    public static final int CAMERA_REQUEST_CODE = 100;
    public static final int IMAGE_REQUEST_CODE = 101;
    public static final int RESULT_REQUEST_CODE = 102;

    public static final String PHOTO_IMAGE_FILE_NAME = "fileImg.jpg";
    private File tempFile = null;



    private static int output_X = 480;
    private static int output_Y = 480;

    public UserFragment() {
        // Required empty public constructor
    }


    //在Fragment被绘制后，调用此方法，可以初始化控件资源。
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("Fragment", "UserFragment.onViewCreated");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.i("Fragment", "UserFragment.onCreateView");
        view =  inflater.inflate(R.layout.fragment_user, container, false);
        et_user = (EditText)view.findViewById(R.id.fragUser_userName);
        et_slogan = (EditText)view.findViewById(R.id.fragUser_slogan);
        bt_update = (Button)view.findViewById(R.id.fragUser_btn_update);
        pushupchart = (LineChart) view.findViewById(R.id.chart1);
        iv_user = view.findViewById(R.id.fragUser_userImg);
        iv_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toPicture();
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("Fragment", "UserFragment.onDestroyView");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("Fragment", "UserFragment.onPause");
    }

    //当onCreate()，onCreateView()，onViewCreated()方法执行完后调用，也就是Activity被渲染绘制出来后。
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("Fragment", "UserFragment.onActivityCreated");
        ArrayList<FittingItem> pushuplist = dbAdapter.getAllFitting("FUWOCHENG");
        List<Entry> entryCnt = new ArrayList<>();
        List<Entry> entryTime = new ArrayList<>();
        for (int i = 0; i < pushuplist.size(); i++) {
            entryCnt.add(new Entry(pushuplist.get(i).getLocalTime(), pushuplist.get(i).getNumber()));
            entryTime.add(new Entry(pushuplist.get(i).getLocalTime(), pushuplist.get(i).getDurationTime()));
        }
        if(pushuplist.size() != 0){
            LineDataSet dataSetCnt = new LineDataSet(entryCnt, "数量"); // add entries to dataset
            LineDataSet dataSetTime = new LineDataSet(entryTime, "时间"); // add entries to dataset
            dataSetCnt.setColor(Color.RED);

            LineData lineData = new LineData(dataSetCnt,dataSetTime);
            pushupchart.setData(lineData);
            XAxis xAxis = pushupchart.getXAxis();
            IAxisValueFormatter xFormatter = new IAxisValueFormatter() {
                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
                    return sdf.format(value);
                }
            };
            xAxis.setValueFormatter(xFormatter);
        }


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
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("Fragment", "UserFragment.onDetach");

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i("Fragment", "UserFragment.onAttach");
        if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Fragment", "UserFragment.onCreate");

    }

    public interface OnFragmentInteractionListener {
        void OnClicked(String name, String slogan);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != getActivity().RESULT_CANCELED) {
            switch (requestCode) {
                //相机数据
                case CAMERA_REQUEST_CODE:
                    //tempFile = new File(Environment.getExternalStorageDirectory(), PHOTO_IMAGE_FILE_NAME);
                    //startPhotoZoom(Uri.fromFile(tempFile));
                    startPhotoZoom(imageUri);
                    break;
                //相册数据
                case IMAGE_REQUEST_CODE:
                    startPhotoZoom(data.getData());
                    break;
                case RESULT_REQUEST_CODE:
                    //有可能点击舍弃
                    if (data != null) {
                        //拿到图片设置
                        setImageToView(data);
                        //删除原来的图片
                        if (tempFile != null) {
                            tempFile.delete();
                        }
                    }
                    break;
            }
        }
    }

    private void startPhotoZoom(Uri uri) {
        if (uri == null) {
//            Log.e("uri == null");
            return;
        }

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        //裁剪
        intent.putExtra("crop", true);
        //宽高比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        //图片质量
        intent.putExtra("outputX", 320);
        intent.putExtra("outputY", 320);
        //发送数据
        intent.putExtra("return-data", true);
        startActivityForResult(intent, RESULT_REQUEST_CODE);
    }

    //设置图片
    private void setImageToView(Intent data) {
        Bundle bundle = data.getExtras();
        if (bundle != null) {
            Bitmap bitmap = bundle.getParcelable("data");
            iv_user.setImageBitmap(bitmap);
        }
    }

    private void toCamera() {

        File outputImage = new File(getActivity().getExternalCacheDir(),
                PHOTO_IMAGE_FILE_NAME);
        try {
            if (outputImage.exists()) {
                outputImage.delete();
            }
            outputImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (Build.VERSION.SDK_INT >= 24) {
            imageUri = FileProvider.getUriForFile(getActivity(),
                    "com.liushengjie.smartbutler.fileprovider", outputImage);
        } else {
            imageUri = Uri.fromFile(outputImage);
        }
        //启动相机程序
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, CAMERA_REQUEST_CODE);
//        dialog.dismiss();

    }

    //跳转相册
    private void toPicture() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_REQUEST_CODE);
//        dialog.dismiss();
    }
}
