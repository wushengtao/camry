package com.lunzi.camry.domain;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author lunzi
 * @since 2019-02-21
 */
@TableName("zh_user")
public class ZhUser extends Model<ZhUser> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField("user_token")
    private String userToken;
    private String location;
    private String business;
    private String sex;
    private String employment;
    private String education;
    private String username;
    private String url;
    private Integer agrees;
    private Integer thanks;
    private Integer asks;
    private Integer answers;
    private Integer posts;
    private Integer followees;
    private Integer followers;
    @TableField("hash_id")
    private String hashId;
    @TableField("created_time")
    private Date createdTime;
    @TableField("updated_time")
    private Date updatedTime;
    private String position;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmployment() {
        return employment;
    }

    public void setEmployment(String employment) {
        this.employment = employment;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getAgrees() {
        return agrees;
    }

    public void setAgrees(Integer agrees) {
        this.agrees = agrees;
    }

    public Integer getThanks() {
        return thanks;
    }

    public void setThanks(Integer thanks) {
        this.thanks = thanks;
    }

    public Integer getAsks() {
        return asks;
    }

    public void setAsks(Integer asks) {
        this.asks = asks;
    }

    public Integer getAnswers() {
        return answers;
    }

    public void setAnswers(Integer answers) {
        this.answers = answers;
    }

    public Integer getPosts() {
        return posts;
    }

    public void setPosts(Integer posts) {
        this.posts = posts;
    }

    public Integer getFollowees() {
        return followees;
    }

    public void setFollowees(Integer followees) {
        this.followees = followees;
    }

    public Integer getFollowers() {
        return followers;
    }

    public void setFollowers(Integer followers) {
        this.followers = followers;
    }

    public String getHashId() {
        return hashId;
    }

    public void setHashId(String hashId) {
        this.hashId = hashId;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ZhUser{" +
        ", id=" + id +
        ", userToken=" + userToken +
        ", location=" + location +
        ", business=" + business +
        ", sex=" + sex +
        ", employment=" + employment +
        ", education=" + education +
        ", username=" + username +
        ", url=" + url +
        ", agrees=" + agrees +
        ", thanks=" + thanks +
        ", asks=" + asks +
        ", answers=" + answers +
        ", posts=" + posts +
        ", followees=" + followees +
        ", followers=" + followers +
        ", hashId=" + hashId +
        ", createdTime=" + createdTime +
        ", updatedTime=" + updatedTime +
        ", position=" + position +
        "}";
    }
}
