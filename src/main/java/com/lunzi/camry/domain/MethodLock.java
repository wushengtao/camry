package com.lunzi.camry.domain;

import com.baomidou.mybatisplus.enums.IdType;
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
 * @since 2018-09-12
 */
@TableName("method_lock")
public class MethodLock extends Model<MethodLock> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "lock_id", type = IdType.AUTO)
    private Long lockId;
    /**
     * 锁的名称
     */
    @TableField("lock_name")
    private String lockName;
    /**
     * 创建时间
     */
    @TableField("lock_create")
    private Long lockCreate;
    /**
     * 修改时间
     */
    @TableField("lock_modified")
    private Long lockModified;
    /**
     * 锁的描述
     */
    @TableField("lock_desc")
    private String lockDesc;
    /**
     * 逻辑删除
     */
    @TableField("is_deleted")
    private Integer isDeleted;


    public Long getLockId() {
        return lockId;
    }

    public void setLockId(Long lockId) {
        this.lockId = lockId;
    }

    public String getLockName() {
        return lockName;
    }

    public void setLockName(String lockName) {
        this.lockName = lockName;
    }

    public Long getLockCreate() {
        return lockCreate;
    }

    public void setLockCreate(Long lockCreate) {
        this.lockCreate = lockCreate;
    }

    public Long getLockModified() {
        return lockModified;
    }

    public void setLockModified(Long lockModified) {
        this.lockModified = lockModified;
    }

    public String getLockDesc() {
        return lockDesc;
    }

    public void setLockDesc(String lockDesc) {
        this.lockDesc = lockDesc;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    protected Serializable pkVal() {
        return this.lockId;
    }

    @Override
    public String toString() {
        return "MethodLock{" +
        ", lockId=" + lockId +
        ", lockName=" + lockName +
        ", lockCreate=" + lockCreate +
        ", lockModified=" + lockModified +
        ", lockDesc=" + lockDesc +
        ", isDeleted=" + isDeleted +
        "}";
    }
}
