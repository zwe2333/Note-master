package com.zwe.note_master.presenter.impl;

import android.widget.Toast;

import com.zwe.note_master.database.NoteBean;
import com.zwe.note_master.presenter.SearchPresenter;
import com.zwe.note_master.ui.activity.SearchActivity;
import com.zwe.note_master.view.SearchView;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus on 2017/5/2.
 */

public class SearchPresenterImpl implements SearchPresenter{
    private List<NoteBean> mNoteBeen;
    private SearchView mSearchView;
    public SearchPresenterImpl(SearchView searchView){
        mSearchView=searchView;
        mNoteBeen=new ArrayList<>();
    }

    @Override
    public void backToMain() {
        mSearchView.backToMain();
    }

    @Override
    public void searchMsg(String input) {
        mNoteBeen.clear();
        List<NoteBean> noteBeen = DataSupport.where("title=?", input).find(NoteBean.class);
        if (noteBeen.size()==0){
            mSearchView.searchFail();
            return;
        }
        mNoteBeen.addAll(noteBeen);
        mSearchView.searchSuccess();
    }

    @Override
    public List<NoteBean> getList() {
        return mNoteBeen;
    }
}
