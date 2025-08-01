package password;

import lombok.Data;

/**
 * @author KCWang
 * @version 1.0  常用 相关用户及密码
 * @date 2023/6/11 上午1:46
 */

public enum Password {

    GIT_HUB("KCWang152253", "wangcheng152253"),
    Mysql("root", "kcwang"),
    闻喜华庭("闻喜华庭1幢1901房间密码","302798"),
    postman_qq("KCWang","wangcheng152253"),
    postman_sanofi("KCWang","KC!2zxcvbnm"),
    redis("默认无用户名密码","kcwang"),
    mac_office("激.活.号：v817@new365.vip","密..码：Abc399971"),
    gmail_1994("kcwang1994@gmail.com","密..码：wangcheng152253"),
    github("KCWang152253","密..码：wangcheng152253"),
    hsbc("kckc","密..码：Wanaa53!"),
    qq("2747962529@qq.com","密..码：wangcheng_152253 渣打： Wangcheng_152253  "),
    teams("kcwang1994@outlook.com","密..码：wangcheng_152253   "),
    zoom("名：kc  姓：wang    ","密..码：Wangcheng_152253   "),
    Apple("Username：wkc152253@icloud.com    ","密..码：Wangcheng123456!   "),
    GOTO("Username：g001675    ","密..码：KC!1zxcvbnm   "),
    GOTO_EMAIL("Username：kecheng.wang@gojek.com    ","密..码：kc!1kc!1zxcvbnm   "),
    // Temporary Password: 654GUk"D   Username: g001675 kc!1zxcvbnm  KC!1zxcvbnm  654GUk"D

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
