package com.zwe.note_master.database;

import org.litepal.crud.DataSupport;

/**
 * Created by Asus on 2017/5/2.
 */

public class NoteBean extends DataSupport{
    private String title;//标题
    private String content;//内容
    private String imgPath;//图片路径
    private String holdTime;//保存时间
    private boolean isRemind;//是否收藏

    public boolean isClock() {
        return isClock;
    }

    public void setClock(boolean clock) {
        isClock = clock;
    }

    private boolean isClock;//是否闹钟

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getHoldTime() {
        return holdTime;
    }

    public void setHoldTime(String holdTime) {
        this.holdTime = holdTime;
    }

    public boolean isRemind() {
        return isRemind;
    }

    public void setRemind(boolean remind) {
        isRemind = remind;
    }
}
