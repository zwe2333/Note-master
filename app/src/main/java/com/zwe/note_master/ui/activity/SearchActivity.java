package com.zwe.note_master.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chaychan.viewlib.PowerfulEditText;
import com.zwe.note_master.R;
import com.zwe.note_master.adapter.SearchAdapter;
import com.zwe.note_master.database.NoteBean;
import com.zwe.note_master.presenter.SearchPresenter;
import com.zwe.note_master.presenter.impl.SearchPresenterImpl;
import com.zwe.note_master.view.SearchView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus on 2017/5/2.
 */

public class SearchActivity extends BaseActivity implements View.OnClickListener,SearchView{
    private ImageView search_back;
    private ImageView imgSearch;

    private PowerfulEditText edtSearch;

    private RecyclerView mRecyclerView;
    private SearchAdapter mAdapter;


    private SearchPresenter mSearchPresenter;

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected void initViews() {

        mSearchPresenter=new SearchPresenterImpl(this);

        search_back= (ImageView) findViewById(R.id.search_back);
        search_back.setOnClickListener(this);

        edtSearch= (PowerfulEditText) findViewById(R.id.edtSearch);

        mRecyclerView= (RecyclerView) findViewById(R.id.searchRecycler);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mAdapter=new SearchAdapter(mSearchPresenter.getList());
        mRecyclerView.setAdapter(mAdapter);

        imgSearch= (ImageView) findViewById(R.id.imgSearch);
        imgSearch.setOnClickListener(this);

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_search;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.search_back:
                mSearchPresenter.backToMain();
                break;
            case R.id.imgSearch:
                String input=edtSearch.getText().toString().trim();
                mSearchPresenter.searchMsg(input);
                break;
        }
    }

    @Override
    public void backToMain() {
        finish();
    }

    @Override
    public void searchSuccess() {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void searchFail() {
        Toast.makeText(this,"找不到数据",Toast.LENGTH_SHORT).show();
    }
}
