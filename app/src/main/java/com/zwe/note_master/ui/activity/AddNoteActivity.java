package com.zwe.note_master.ui.activity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chaychan.viewlib.PowerfulEditText;
import com.zwe.note_master.R;
import com.zwe.note_master.presenter.AddNotePrestenter;
import com.zwe.note_master.presenter.impl.AddNotePresenterImpl;
import com.zwe.note_master.view.AddNoteView;

/**
 * Created by Asus on 2017/5/3.
 */

public class AddNoteActivity extends BaseActivity implements AddNoteView,View.OnClickListener{
    private static final int IMAGE_CODE = 1001;
    private AddNotePrestenter mNotePrestenter;
    private PowerfulEditText addTitle,addContent;
    private ImageView imgOpenPic;
    private String imgUrl=null;
    private ImageView imgAddHead;

    @Override
    protected void init() {
        super.init();
        mNotePrestenter=new AddNotePresenterImpl(this);

        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar!=null){
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    @Override
    protected void initViews() {
        addTitle= (PowerfulEditText) findViewById(R.id.addTitle);
        addContent= (PowerfulEditText) findViewById(R.id.addContent);

        imgOpenPic= (ImageView) findViewById(R.id.imgOpenPic);
        imgOpenPic.setOnClickListener(this);

        imgAddHead= (ImageView) findViewById(R.id.imgAddHead);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_addnote;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.addnote_toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                String title=addTitle.getText().toString().trim();
                String content=addContent.getText().toString().trim();
                mNotePrestenter.checkMsg(title,content,imgUrl);
                mNotePrestenter.backToMain();
                break;
        }
        return true;
    }

    @Override
    public void backToMain() {
        finish();
    }

    private void selectImage(){
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, IMAGE_CODE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imgOpenPic:
                selectImage();
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==IMAGE_CODE) {
            if (data == null) {
                return;
            }
            Uri uri = data.getData();

            String[] proj = {MediaStore.Images.Media.DATA};

            // 好像是android多媒体数据库的封装接口，具体的看Android文档
            Cursor cursor = managedQuery(uri, proj, null, null, null);

            // 按我个人理解 这个是获得用户选择的图片的索引值
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            // 将光标移至开头 ，这个很重要，不小心很容易引起越界
            cursor.moveToFirst();
            // 最后根据索引值获取图片路径
            imgUrl = cursor.getString(column_index);

            if (imgUrl!=null){
                Glide.with(this).load(imgUrl).into(imgAddHead);
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
