package com.bazn.document.bean;


import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author itle
 * @version 1.0
 * @date 2020/11/13
 */
public class Document {
    private Long id;
    private String title;   //文章标题
    private String mdContent;   // markdown内容
    private String htmlContent; //html内容
    private String summary;   //显示的文章内容
    private Long tid;   //分类id
    private Long uid;   //作者id
    private Timestamp publishDate;  //发布时间
    private Timestamp editTime; //编辑时间
    private Integer state;  //状态 0表示草稿箱，1表示已发表，2表示已删除
    private Integer pageView;   //被查看次数
    private String nickname;
    private String tagName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMdContent() {
        return mdContent;
    }

    public void setMdContent(String mdContent) {
        this.mdContent = mdContent;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long cid) {
        this.tid = cid;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Timestamp getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Timestamp publishDate) {
        this.publishDate = publishDate;
    }

    public Timestamp getEditTime() {
        return editTime;
    }

    public void setEditTime(Timestamp editTime) {
        this.editTime = editTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getPageView() {
        return pageView;
    }

    public void setPageView(Integer pageView) {
        this.pageView = pageView;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
