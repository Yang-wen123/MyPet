package com.company.Utils;

import com.company.Presenter.Impl.LoginPreImpl;

import java.util.Scanner;

/***
 * @author wen
 * @created 2020/2/27
 * @explain 宠物管理系统主界面
 */
public class WelcomUtil {
    /***
     * 宠物管理系统主界面
     */
    public static void welcom() {
        System.out.println("**********************************************");
        System.out.println("***                                        ***");
        System.out.println("***           欢迎来到宠物管理系统           ***");
        System.out.println("*\t1.登录\t2.注册\t3.用户信息管理\t4.退出\t *");
        System.out.println("***                                        ***");
        System.out.println("**********************************************");
        System.out.print("请选择:");
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();
        switch (choice){
            case 1:
                ConstantUtil.is_break=false;
                LoginUtil.login_view();
                break;
            case 2:
                ConstantUtil.is_break=false;
                DBUtil.openConn();
                DBUtil.addPalyer();
                DBUtil.closeConn();
                break;
            case 3:
                ConstantUtil.is_break=false;
                UserManageUtil.userManage();
                break;
            case 4:
                ConstantUtil.is_break=true;
                break;
            default:
                System.out.println("请输入正确的指令:");
                welcom();
                break;
        }
    }
}
