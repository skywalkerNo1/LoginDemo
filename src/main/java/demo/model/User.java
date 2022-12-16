package demo.model;

import javax.persistence.*;

@Table(name = "user")
public class User {
    @Id
    private Integer id;

    /**
     * 登录账号
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户头像
     */
    @Column(name = "user_profile")
    private String userProfile;

    /**
     * 用户状态id
     */
    @Column(name = "user_state_id")
    private Integer userStateId;

    /**
     * 是否可用
     */
    @Column(name = "is_enabled")
    private Boolean isEnabled;

    /**
     * 是否被锁定
     */
    @Column(name = "is_locked")
    private Boolean isLocked;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取登录账号
     *
     * @return username - 登录账号
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置登录账号
     *
     * @param username 登录账号
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * 获取昵称
     *
     * @return nickname - 昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置昵称
     *
     * @param nickname 昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 获取用户头像
     *
     * @return user_profile - 用户头像
     */
    public String getUserProfile() {
        return userProfile;
    }

    /**
     * 设置用户头像
     *
     * @param userProfile 用户头像
     */
    public void setUserProfile(String userProfile) {
        this.userProfile = userProfile == null ? null : userProfile.trim();
    }

    /**
     * 获取用户状态id
     *
     * @return user_state_id - 用户状态id
     */
    public Integer getUserStateId() {
        return userStateId;
    }

    /**
     * 设置用户状态id
     *
     * @param userStateId 用户状态id
     */
    public void setUserStateId(Integer userStateId) {
        this.userStateId = userStateId;
    }

    /**
     * 获取是否可用
     *
     * @return is_enabled - 是否可用
     */
    public Boolean getIsEnabled() {
        return isEnabled;
    }

    /**
     * 设置是否可用
     *
     * @param isEnabled 是否可用
     */
    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    /**
     * 获取是否被锁定
     *
     * @return is_locked - 是否被锁定
     */
    public Boolean getIsLocked() {
        return isLocked;
    }

    /**
     * 设置是否被锁定
     *
     * @param isLocked 是否被锁定
     */
    public void setIsLocked(Boolean isLocked) {
        this.isLocked = isLocked;
    }
}