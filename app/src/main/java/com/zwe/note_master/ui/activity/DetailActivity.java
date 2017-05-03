package com.zwe.note_master.ui.activity;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zwe.note_master.R;

/**
 * Created by Asus on 2017/5/3.
 */

public class DetailActivity extends BaseActivity{

    private Toolbar mToolbar;
    private CollapsingToolbarLayout mToolbarLayout;
    private ImageView mImageView;
    private TextView mTextView;


    @Override
    protected void init() {
        super.init();
        Intent intent=getIntent();
        String title=intent.getStringExtra("title");
        String content=intent.getStringExtra("content");
        String path=intent.getStringExtra("imgPath");
        mToolbarLayout.setTitle(title);
        mTextView.setText(content);
        if (path==null){
            mImageView.setImageResource(R.drawable.bg_main);
        }else {
            Glide.with(this).load(path).into(mImageView);
        }
    }

    @Override
    protected void initViews() {
        mToolbar= (Toolbar) findViewById(R.id.detailToolbar);
        mToolbarLayout= (CollapsingToolbarLayout) findViewById(R.id.collapsing);
        mImageView= (ImageView) findViewById(R.id.imgDetailHead);
        mTextView= (TextView) findViewById(R.id.tvDetailContent);
        setSupportActionBar(mToolbar);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_detail;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
