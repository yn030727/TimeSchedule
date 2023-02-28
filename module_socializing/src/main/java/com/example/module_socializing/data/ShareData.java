package com.example.module_socializing.data;

public class ShareData {
    //这个头像的数据类型待定  不好说
    public String profit;
    public String account;//
    public String title; //小标题（20字最多）
    public String photo; //也是图片  待定
    public String time; //发帖时间 (看能不能后台根据传过去的时间自动生成  不能就我这获取然后传过去
    public String name; //发帖人的用户名
    public String content; //帖子内容（长的）
    public String like; //点赞数量

    public ShareData(String like , String title , String name){
        this.like = like;
        this.title = title;
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfit() {
        return profit;
    }

    public void setProfit(String profit) {
        this.profit = profit;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

}
