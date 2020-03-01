package com.company.Utils;

import java.util.Random;
import java.util.Scanner;

/***
 * @author wen
 * @created 2020/2/27
 */
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
        System.out.println("* 1.冒险  2.战斗  3.遭遇  4.宠物  5.恢复中心  6.角色信息  7.切换城镇  8.商店  0.退出 *");
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
                fight_pet(PetUtil.town_id);
                player_wprld();
                break;
            case 3:
                enemy();
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
        DBUtil.openConn();
        PetUtil.query_my_pet(ConstantUtil.now_username);
        System.out.println("请选择受伤的宠物");
        Scanner scanner=new Scanner(System.in);
        int choose_pet=scanner.nextInt();
        PetUtil.recover_pet(ConstantUtil.now_username,choose_pet);
        ConstantUtil.coin=-800;
        PetUtil.add_coin(ConstantUtil.now_username,ConstantUtil.coin);
        System.out.println("伤势治愈，损失金币:"+ConstantUtil.coin);
        DBUtil.closeConn();
    }

    private static void pet_bag() {
        DBUtil.openConn();
        PetUtil.query_my_pet(ConstantUtil.now_username);
        DBUtil.closeConn();
    }
    private static void enemy() {
        System.out.println("对战吧，少年，遇到了训练师\n敌方信息-->");
        RandomUtil.get_npc_id();
        DBUtil.openConn();
        FightUtil.fight();
        DBUtil.closeConn();
    }
    //战斗
    private static void fight_pet(int town_id) {
        RandomUtil.is_success();
        DBUtil.openConn();
        PetUtil.query_my_pet(ConstantUtil.now_username);
        System.out.println("请选择战斗宠物");
        Scanner scanner=new Scanner(System.in);
        int choose_pet=scanner.nextInt();
        if(ConstantUtil.success>town_id){
            RandomUtil.getCoin();
            PetUtil.add_coin(ConstantUtil.now_username,ConstantUtil.coin);
            System.out.println("战斗胜利！恭喜获得金币:"+ConstantUtil.coin);
        }else {
            System.out.println("战斗失败");
            PetUtil.update_hp(ConstantUtil.now_username,choose_pet,0,0);
        }
        DBUtil.closeConn();
    }
    private static void choose_pet(){
        PetUtil.add_pet(ConstantUtil.now_username,ConstantUtil.pet_id);
        System.out.println("请选择战斗宠物");
        Scanner scanner=new Scanner(System.in);
        int choose_pet=scanner.nextInt();
        System.out.println("宠物id:"+ConstantUtil.pet_id);
        System.out.println("捕获成功 ");
    }
    private static void fight_pet(){
        PetUtil.query_my_pet(ConstantUtil.now_username);
        System.out.println("请选择战斗宠物");
        Scanner scanner=new Scanner(System.in);
        int choose_pet=scanner.nextInt();
        System.out.print("战斗胜利 ");
    }
    //冒险
    private static void explore_pet() {
        DBUtil.openConn();
        RandomUtil.makeOneRandom();
        System.out.println(ConstantUtil.explore_id);
        //50%几率出500金币
        if (ConstantUtil.explore_id>500){
            PetUtil.add_coin(ConstantUtil.now_username,500);
            System.out.println("恭喜获得金币:"+500);
        }else if(ConstantUtil.explore_id>200){
            //30%出抓捕野生宠物
            RandomUtil.makePetRandom();
            System.out.println("恭喜遇到野生宠物");
            choose_pet();
        }else if(ConstantUtil.explore_id>100){
            //10%遇敌
            System.out.println("对战吧，少年，遇到了训练师\n敌方信息-->");
            RandomUtil.get_npc_id();
            EnemyUtil.enemy_query(ConstantUtil.npc_id);
            fight_pet();
        }else if(ConstantUtil.explore_id>50){
            //5%不出
            System.out.println("很遗憾，没有收获");
        }else if(ConstantUtil.explore_id>30){
            //2%出普通神兽
            RandomUtil.makeGodRandom();
            System.out.println("恭喜遇到野生神兽");
            choose_pet();
        }else if(ConstantUtil.explore_id>10){
            //1%出高级神兽
            System.out.println("恭喜遇到野生高级神兽");
            RandomUtil.makeHighGodRandom();
            choose_pet();
        }else{
            //小于1%出稀有神兽
            RandomUtil.makeRareRandom();
            System.out.println("恭喜遇到野生稀有神兽");
            choose_pet();
        }
        DBUtil.closeConn();
    }
}
