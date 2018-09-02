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
 * 题干表
 * </p>
 *
 * @author lunzi
 * @since 2018-05-30
 */
@TableName("question_content")
public class QuestionContent extends Model<QuestionContent> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "question_id", type = IdType.AUTO)
    private Long questionId;
    /**
     * 题目内容
     */
    @TableField("question_subject")
    private String questionSubject;
    /**
     * 题目答案
     */
    @TableField("question_answer")
    private String questionAnswer;
    /**
     * 题目解析
     */
    @TableField("question_analyse")
    private String questionAnalyse;
    /**
     * 题目类型 1-单选 2-多选 3-判断
     */
    @TableField("question_type")
    private Integer questionType;
    /**
     * 题目主题
     */
    @TableField("question_topic_id")
    private Integer questionTopicId;
    /**
     * 状态 0-删除 1-可用
     */
    private Integer status;
    /**
     * 创建时间
     */
    @TableField("gmt_create")
    private Date gmtCreate;
    /**
     * 修改时间
     */
    @TableField("gmt_modified")
    private Date gmtModified;


    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getQuestionSubject() {
        return questionSubject;
    }

    public void setQuestionSubject(String questionSubject) {
        this.questionSubject = questionSubject;
    }

    public String getQuestionAnswer() {
        return questionAnswer;
    }

    public void setQuestionAnswer(String questionAnswer) {
        this.questionAnswer = questionAnswer;
    }

    public String getQuestionAnalyse() {
        return questionAnalyse;
    }

    public void setQuestionAnalyse(String questionAnalyse) {
        this.questionAnalyse = questionAnalyse;
    }

    public Integer getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Integer questionType) {
        this.questionType = questionType;
    }

    public Integer getQuestionTopicId() {
        return questionTopicId;
    }

    public void setQuestionTopicId(Integer questionTopicId) {
        this.questionTopicId = questionTopicId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    protected Serializable pkVal() {
        return this.questionId;
    }

    @Override
    public String toString() {
        return "QuestionContent{" +
        ", questionId=" + questionId +
        ", questionSubject=" + questionSubject +
        ", questionAnswer=" + questionAnswer +
        ", questionAnalyse=" + questionAnalyse +
        ", questionType=" + questionType +
        ", questionTopicId=" + questionTopicId +
        ", status=" + status +
        ", gmtCreate=" + gmtCreate +
        ", gmtModified=" + gmtModified +
        "}";
    }
}
