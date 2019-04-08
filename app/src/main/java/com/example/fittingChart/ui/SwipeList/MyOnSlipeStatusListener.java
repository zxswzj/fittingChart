package com.example.fittingChart.ui.SwipeList;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class MyOnSlipeStatusListener implements SwipeListLayout.OnSwipeStatusListener {

    private SwipeListLayout slipListLayout;
    Set<SwipeListLayout> sets;// = new HashSet();

    public MyOnSlipeStatusListener(SwipeListLayout slipListLayout, Set<SwipeListLayout> sets) {
        this.slipListLayout = slipListLayout;
        this.sets = sets;
    }

    @Override
    public void onStatusChanged(SwipeListLayout.Status status) {
        if (status == SwipeListLayout.Status.Open) {
            //若有其他的item的状态为Open，则Close，然后移除
            if (sets.size() > 0) {
                for (SwipeListLayout s : sets) {
                    s.setStatus(SwipeListLayout.Status.Close, true);
                    sets.remove(s);
                }
            }
            sets.add(slipListLayout);
        } else {
            if (sets.contains(slipListLayout))
                sets.remove(slipListLayout);
        }
    }

    @Override
    public void onStartCloseAnimation() {

    }

    @Override
    public void onStartOpenAnimation() {

    }

}
