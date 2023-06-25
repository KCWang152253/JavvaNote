package password;

import lombok.Data;

/**
 * @author KCWang
 * @version 1.0  常用 相关用户及密码
 * @date 2023/6/11 上午1:46
 */

public enum Password {

    GIT_HUB("mysql root", "kcwang"),
    Mysql("root", "kcwang"),
    ;
    private String user;
    private String password;

    Password(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
