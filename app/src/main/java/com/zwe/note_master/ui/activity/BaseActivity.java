package com.zwe.note_master.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.zwe.note_master.R;

/**
 * Created by Asus on 2017/5/2.
 */

public abstract class BaseActivity extends AppCompatActivity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutRes());

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));//设置toolbar

        initViews();

        init();
    }

    protected void init() {}

    protected abstract void initViews();

    protected abstract int getLayoutRes();

    protected void goTo(Class activity,boolean isFinish){
        Intent intent=new Intent(this,activity);
        startActivity(intent);
        if (isFinish){
            finish();
        }
    }
}
