package com.show.itmuch.contentcenter.domain.entity.user;

import java.util.Date;
import javax.persistence.*;

@Table(name = "user")
public class User {
    /**
     * Id
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 微信id
     */
    @Column(name = "wx_id")
    private String wxId;

    /**
     * 微信昵称
     */
    @Column(name = "wx_nickname")
    private String wxNickname;

    /**
     * 角色
     */
    private String roles;

    /**
     * 头像地址
     */
    @Column(name = "avatar_url")
    private String avatarUrl;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 积分
     */
    private Integer bonus;

    /**
     * 获取Id
     *
     * @return id - Id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置Id
     *
     * @param id Id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取微信id
     *
     * @return wx_id - 微信id
     */
    public String getWxId() {
        return wxId;
    }

    /**
     * 设置微信id
     *
     * @param wxId 微信id
     */
    public void setWxId(String wxId) {
        this.wxId = wxId;
    }

    /**
     * 获取微信昵称
     *
     * @return wx_nickname - 微信昵称
     */
    public String getWxNickname() {
        return wxNickname;
    }

    /**
     * 设置微信昵称
     *
     * @param wxNickname 微信昵称
     */
    public void setWxNickname(String wxNickname) {
        this.wxNickname = wxNickname;
    }

    /**
     * 获取角色
     *
     * @return roles - 角色
     */
    public String getRoles() {
        return roles;
    }

    /**
     * 设置角色
     *
     * @param roles 角色
     */
    public void setRoles(String roles) {
        this.roles = roles;
    }

    /**
     * 获取头像地址
     *
     * @return avatar_url - 头像地址
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * 设置头像地址
     *
     * @param avatarUrl 头像地址
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取修改时间
     *
     * @return update_time - 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置修改时间
     *
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取积分
     *
     * @return bonus - 积分
     */
    public Integer getBonus() {
        return bonus;
    }

    /**
     * 设置积分
     *
     * @param bonus 积分
     */
    public void setBonus(Integer bonus) {
        this.bonus = bonus;
    }
}