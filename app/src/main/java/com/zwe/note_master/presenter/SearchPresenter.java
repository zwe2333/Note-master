package com.zwe.note_master.presenter;

import com.zwe.note_master.database.NoteBean;

import java.util.List;

/**
 * Created by Asus on 2017/5/2.
 */

public interface SearchPresenter {
    void backToMain();

    void searchMsg(String input);

    List<NoteBean> getList();
}
