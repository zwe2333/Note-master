package com.zwe.note_master.ui.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.cleveroad.fanlayoutmanager.FanLayoutManager;
import com.cleveroad.fanlayoutmanager.FanLayoutManagerSettings;
import com.zwe.note_master.R;
import com.zwe.note_master.adapter.MainAdapter;
import com.zwe.note_master.adapter.onDeleteListener;
import com.zwe.note_master.database.NoteBean;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener,onDeleteListener {
    private RecyclerView mRecyclerView;
    private MainAdapter mMainAdapter;
    private List<com.zwe.note_master.database.NoteBean> mNoteBeen=new ArrayList<>();

    private FloatingActionButton mFloatingActionButton;


    @Override
    protected void initViews() {
        mRecyclerView= (RecyclerView) findViewById(R.id.mainRecycler);

        mFloatingActionButton= (FloatingActionButton) findViewById(R.id.btnFloat);
        mFloatingActionButton.setOnClickListener(this);

    }

    @Override
    protected void init() {
        super.init();
        //initData();
        FanLayoutManagerSettings fanLayoutManagerSettings = FanLayoutManagerSettings
                .newBuilder(this)
                .withFanRadius(true)
                .withAngleItemBounce(5)
                .withViewWidthDp(250)
                .withViewHeightDp(300)
                .build();
        FanLayoutManager fanLayoutManager=new FanLayoutManager(this,fanLayoutManagerSettings);
        //LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(fanLayoutManager);
        mMainAdapter=new MainAdapter(mNoteBeen,this);
        mRecyclerView.setAdapter(mMainAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        mNoteBeen.clear();
        List<NoteBean> noteList = DataSupport.findAll(NoteBean.class);
        mNoteBeen.addAll(noteList);
        mMainAdapter.notifyDataSetChanged();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.main_search:
                goTo(SearchActivity.class,false);
                break;
            default:
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnFloat:
                goTo(AddNoteActivity.class,false);
                break;
        }
    }

    @Override
    public void deleteSuccess() {
        initData();
        mMainAdapter.notifyDataSetChanged();
    }
}
