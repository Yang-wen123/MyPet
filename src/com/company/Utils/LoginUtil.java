package com.company.Utils;

import com.company.Bean.PlayerBean;
import com.company.Presenter.Impl.LoginPreImpl;

import java.util.Scanner;

public class LoginUtil {
    public static PlayerBean playerBean=new PlayerBean();
    public static void login_view(){
        System.out.print("请输入用户名:");
        Scanner input_name = new Scanner(System.in);
        int username = input_name.nextInt();
        playerBean.setUsername(username);
        System.out.print("请输入密码（输入0取消）:");
        Scanner input_pass=new Scanner(System.in);
        String password = input_pass.next();
        playerBean.setPassword(password);
        new LoginPreImpl().login();
    }
}
