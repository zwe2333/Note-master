package com.zwe.note_master.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zwe.note_master.R;
import com.zwe.note_master.database.NoteBean;
import com.zwe.note_master.ui.activity.DetailActivity;

import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Asus on 2017/5/2.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder>{

    private List<NoteBean> mNoteBean;
    private onDeleteListener mListener;

    public MainAdapter(List<NoteBean> noteBean,onDeleteListener listener){
        mNoteBean=noteBean;
        mListener=listener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        View noteView;
        ImageView imgHead;
        TextView tvTitle;
        TextView tvContent;
        TextView tvTime;
        ImageView imgSign;
        ImageView imgClock;
        ImageView imgDel;
        public ViewHolder(View itemView) {
            super(itemView);
            noteView=itemView;
            imgHead= (ImageView) itemView.findViewById(R.id.imgHead);
            tvTitle= (TextView) itemView.findViewById(R.id.tvTitle);
            tvContent= (TextView) itemView.findViewById(R.id.tvContent);
            tvTime= (TextView) itemView.findViewById(R.id.tvTime);
            imgSign= (ImageView) itemView.findViewById(R.id.imgSign);
            imgClock= (ImageView) itemView.findViewById(R.id.imgClock);
            imgDel= (ImageView) itemView.findViewById(R.id.imgDel);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        // TODO: 2017/5/2 写点击事件
        return holder;
    }

    private String parseDate() {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date();
        String str=sdf.format(date);
        return str;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        NoteBean bean=mNoteBean.get(position);
        final String imgPath=bean.getImgPath();
        final String content=bean.getContent();
        final String title=bean.getTitle();
        final String time=bean.getHoldTime();
        final boolean isRemind=bean.isRemind();
        final boolean isClock=bean.isClock();
        if (imgPath!=null){
            Glide.with(holder.itemView.getContext()).load(imgPath).into(holder.imgHead);
        }
        holder.tvTitle.setText(title);
        holder.tvContent.setText(content);


        String date=parseDate();
        holder.tvTime.setText(date);
        if (isRemind){
            holder.imgSign.setImageResource(R.mipmap.star_sign);
        }else {
            holder.imgSign.setImageResource(R.mipmap.star_nosign);
        }
        if (isClock){
            holder.imgClock.setImageResource(R.mipmap.clock_sign);
        }else {
            holder.imgClock.setImageResource(R.mipmap.clock_nosign);
        }
        holder.noteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(holder.noteView.getContext(), DetailActivity.class);
                intent.putExtra("title",title);
                intent.putExtra("imgPath",imgPath);
                intent.putExtra("content",content);
                intent.putExtra("time",time);
                intent.putExtra("isRemind",isRemind);
                intent.putExtra("isClock",isClock);
                holder.noteView.getContext().startActivity(intent);
            }
        });
        holder.imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataSupport.deleteAll(NoteBean.class,"title=?",title);
                mListener.deleteSuccess();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNoteBean.size();
    }
}
