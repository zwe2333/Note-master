package com.zwe.note_master.presenter.impl;

import android.text.TextUtils;

import com.zwe.note_master.database.NoteBean;
import com.zwe.note_master.presenter.AddNotePrestenter;
import com.zwe.note_master.view.AddNoteView;

/**
 * Created by Asus on 2017/5/3.
 */

public class AddNotePresenterImpl implements AddNotePrestenter{
    private AddNoteView mAddNoteView;
    public AddNotePresenterImpl(AddNoteView addNoteView){
        mAddNoteView=addNoteView;
    }

    @Override
    public void backToMain() {
        mAddNoteView.backToMain();
    }

    @Override
    public void checkMsg(String title, String content,String imgUrl) {
        if (TextUtils.isEmpty(title)||TextUtils.isEmpty(content)){
            return;
        }
        NoteBean bean=new NoteBean();
        if (imgUrl!=null){
            bean.setImgPath(imgUrl);
        }
        bean.setTitle(title);
        bean.setContent(content);
        bean.save();
    }
}
