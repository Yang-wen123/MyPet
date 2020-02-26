package com.company.Utils;

import java.util.Scanner;

public class PetWorld {
    public static void petWorld(){
        System.out.println("**********************************************");
        System.out.println("***                                        ***");
        System.out.println("***             欢迎来到宠物世界             ***");
        System.out.println("* 1.抓捕  2.放生  3.宠物背包  4.恢复中心  5.退出 *");
        System.out.println("***                                        ***");
        System.out.println("**********************************************");
        System.out.print("请选择:");
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();
        switch (choice){
            case 1:
                catch_pet();
                petWorld();
                break;
            case 2:
                abondon_pet();
                petWorld();
                break;
            case 3:
                pet_bag();
                petWorld();
                break;
            case 4:
                recover_center();
                petWorld();
                break;
            case 5:
                WelcomUtil.welcom();
                break;
        }
    }

    private static void recover_center() {
    }

    private static void pet_bag() {
    }

    private static void abondon_pet() {
    }

    private static void catch_pet() {
    }
}
