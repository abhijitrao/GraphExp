package com.ajsoft.graphexp;

import com.ajsoft.graphexp.util.BaseActivity;
import com.ajsoft.graphexp.util.L;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.view.CollapsibleActionView;
import android.widget.TextView;

public class CollasingLayoutEx extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collasing);
        setHeading("Collasing");
        initUi();
    }

    private void initUi() {

        CollapsingToolbarLayout tv=(CollapsingToolbarLayout)findViewById(R.id.cola1);
        tv.setTitle("Hey Beb");
    }
}
