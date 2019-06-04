package com.example.fittingChart.module;

import com.example.fittingChart.R;

/**
 * Created by anupamchugh on 26/12/15.
 */
public enum ModelObject {

    BLUE(R.string.blue, R.layout.view_blue),
    GREEN(R.string.green, R.layout.view_green),
    RED(R.string.red, R.layout.view_red);

    private int mTitleResId;
    private int mLayoutResId;

    ModelObject(int titleResId, int layoutResId) {
        mTitleResId = titleResId;
        mLayoutResId = layoutResId;
    }

    public int getTitleResId() {
        return mTitleResId;
    }

    public int getLayoutResId() {
        return mLayoutResId;
    }

}
