package com.ngatdo.WebCrawl.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;
@JsonIgnoreProperties(ignoreUnknown=true)
@Entity
public class Topic {
    @Id
    @Column(name = "article_id")
    private String article_id;
    @Basic
    @Column(name = "category_id")
    private String category_id;
    @Basic
    @Column(name = "article_type")
    private String article_type;
    @Basic
    @Column(name = "original_cate")
    private String original_cate;
    @Basic
    @Column(name = "site_id")
    private String site_id;
    @Basic
    @Column(name = "title")
    private String title;
    @Basic
    @Column(name = "lead")
    private String lead;
    @Basic
    @Column(name = "share_url")
    private String share_url;
    @Basic
    @Column(name = "publish_time")
    private String publish_time;
    @Basic
    @Column(name = "privacy")
    private String privacy;
    @Basic
    @Column(name = "author_id")
    private String author_id;
    @Basic
    @Column(name = "score")
    private String score;
    @Basic
    @Column(name = "location_name", columnDefinition = "varchar(20) default 'unknown'")
    private String location_name;

    public String getArticle_id() {
        return article_id;
    }

    public void setArticle_id(String article_id) {
        this.article_id = article_id;
    }

    public String getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(String publish_time) {
        this.publish_time = publish_time;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getArticle_type() {
        return article_type;
    }

    public void setArticle_type(String article_type) {
        this.article_type = article_type;
    }

    public String getOriginal_cate() {
        return original_cate;
    }

    public void setOriginal_cate(String original_cate) {
        this.original_cate = original_cate;
    }

    public String getSite_id() {
        return site_id;
    }

    public void setSite_id(String site_id) {
        this.site_id = site_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLead() {
        return lead;
    }

    public void setLead(String lead) {
        this.lead = lead;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }


    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

    public String getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(String author_id) {
        this.author_id = author_id;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getLocation_name() {
        return location_name;
    }

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }
}
