package com.company.Utils;

import java.util.Scanner;

/***
 * @author wen
 * @created 2020/2/27
 * @explain 宠物管理系统用户信息管理
 */
public class UserManageUtil {

    /***
     * 宠物管理系统用户信息管理界面
     */
    public static void userManage() {
        System.out.println("**********************************************");
        System.out.println("***                                        ***");
        System.out.println("***                用户信息管理             ***");
        System.out.println("*\t1.注册\t2.删除\t3.查询\t4.修改密码\t5.返回\t *");
        System.out.println("***                                        ***");
        System.out.println("**********************************************");
        System.out.print("请选择:");
        Scanner user_input = new Scanner(System.in);
        int user_choice = user_input.nextInt();
        switch (user_choice){
            case 1:
                DBUtil.openConn();
                DBUtil.addPalyer();
                DBUtil.closeConn();
                is_userManager();
                break;
            case 2:
                DBUtil.openConn();
                DBUtil.deletePlayer();
                DBUtil.closeConn();
                is_userManager();
                break;
            case 3:
                DBUtil.openConn();
                DBUtil.query();
                DBUtil.closeConn();
                is_userManager();
                break;
            case 4:
                DBUtil.openConn();
                DBUtil.updatePlayer();
                DBUtil.closeConn();
                is_userManager();
                break;
            case 5:

                break;
            default:
                System.out.print("请输入正确的指令:");
                userManage();
                break;
        }
    }
    /***
     * 用户信息管理结束调用
     */
    public static void is_userManager() {
        System.out.print("是否继续管理用户信息(Y/y继续,任意键退出)");
        Scanner scanner = new Scanner(System.in);
        String is_yes = scanner.next();
        if(is_yes.equals("y")||is_yes.equals("Y")){
            userManage();
        }else {
            WelcomUtil.welcom();
        }
    }
}
