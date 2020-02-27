package com.company.Utils;

import java.util.Random;
import java.util.Scanner;

public class PetWorldUtil {
    public static void petWorld(){
        System.out.println("***************************************************************");
        System.out.println("***                      欢迎来到宠物世界                    ***");
        System.out.println("*                        请选择你的性别                        *");
        System.out.println("***                       1.男   2.女                       ***");
        System.out.println("**************************************************************");
        Scanner input_sex = new Scanner(System.in);
        int sex = input_sex.nextInt();
        System.out.println("***************************************************************");
        System.out.println("***                      欢迎来到宠物世界                    ***");
        System.out.println("*                        请选择你的宠物                        *");
        System.out.println("***               1.小火龙   2.杰尼龟   3.妙蛙种            ***");
        System.out.println("**************************************************************");
        Scanner input_pet = new Scanner(System.in);
        int init_pet = input_pet.nextInt();
        DBUtil.openConn();
        PetUtil.add_user(ConstantUtil.now_username,sex,0,1,1);
        PetUtil.add_pet(ConstantUtil.now_username,init_pet);
        DBUtil.closeConn();
        player_wprld();
    }
    public static void player_wprld(){
        DBUtil.openConn();
        PetUtil.findTown();
        DBUtil.closeConn();
        System.out.println("*********************************************************************************");
        System.out.println("***                                                                           ***");
        System.out.println("***                             欢迎来到宠物世界                                ***");
        System.out.println("***                      当前城镇->"+ConstantUtil.town+"胜率"+(100-10*PetUtil.town_id)+"%                         ***");
        System.out.println("* 1.冒险  2.战斗  3.背包  4.宠物  5.恢复中心  6.角色信息  7.切换城镇  8.商店  0.退出 *");
        System.out.println("***                                                                           ***");
        System.out.println("*********************************************************************************");
        System.out.print("请选择:");
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();
        switch (choice){
            case 0:
                WelcomUtil.welcom();
                break;
            case 1:
                explore_pet();
                player_wprld();
                break;
            case 2:
                fight_pet(ConstantUtil.town_id);
                player_wprld();
                break;
            case 3:
                bag();
                player_wprld();
                break;
            case 4:
                pet_bag();
                player_wprld();
                break;
            case 5:
                recover_center();
                player_wprld();
                break;
            case 6:
                user_info();
                player_wprld();
                break;
            case 7:
                change_town();
                player_wprld();
            break;
            case 8:
                store_pet();
                player_wprld();
                break;
            default:
                System.out.print("请输入正确的指令:");
                player_wprld();
                break;
        }
    }



    private static void store_pet() {
        DBUtil.openConn();
        PetUtil.query_all_pet();
        DBUtil.closeConn();
    }


    private static void change_town() {
        System.out.println("*********************************************************************************");
        System.out.println("***                                                                           ***");
        System.out.println("***                             请选择切换城镇                                ***");
        System.out.println("*                   1.丰缘地区 2.关都地区  3.城都地区  4.阿罗拉地区                *");
        System.out.println("***                 5.合众地区 6.神奥地区  7.联盟大赛  8.卡洛斯地区               ***");
        System.out.println("***                           9.极限挑战  0.退出切换                            ***");
        System.out.println("***                                                                           ***");
        System.out.println("*********************************************************************************");
        System.out.print("请选择:");
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();
        if(choice!=0){
            DBUtil.openConn();
            PetUtil.change_town(ConstantUtil.now_username,choice);
            DBUtil.closeConn();
        }else{
            player_wprld();
        }
    }

    private static void user_info() {
        DBUtil.openConn();
        PetUtil.query(ConstantUtil.now_username);
        DBUtil.closeConn();
    }

    private static void recover_center() {
    }

    private static void pet_bag() {
        DBUtil.openConn();
        PetUtil.query_my_pet(ConstantUtil.now_username);
        DBUtil.closeConn();
    }
    private static void bag() {
    }
    //战斗
    private static void fight_pet(int town_id) {
        RandomUtil.is_success();
        if(ConstantUtil.success>town_id){
            RandomUtil.getCoin();
            DBUtil.openConn();
            PetUtil.add_coin(ConstantUtil.now_username,ConstantUtil.coin);
            DBUtil.closeConn();
        }else {
            System.out.println("战斗失败");
        }
    }
    //冒险
    private static void explore_pet() {
        RandomUtil.makeOneRandom();
        //50%几率出500金币
        if (ConstantUtil.explore_id>500){
            PetUtil.add_coin(ConstantUtil.now_username,500);
        }else if(ConstantUtil.explore_id>200){
            //30%出抓捕野生宠物
            RandomUtil.makePetRandom();

        }else if(ConstantUtil.explore_id>100){
            //10%出恢复hp、mp道具

        }else if(ConstantUtil.explore_id>50){
            //5%出hp、mp、attack、def属性提升道具

        }else if(ConstantUtil.explore_id>30){
            //2%出普通神兽

        }else if(ConstantUtil.explore_id>10){
            //1%出高级神兽

        }else{
            //小于1%出稀有神兽
        }
    }
}
