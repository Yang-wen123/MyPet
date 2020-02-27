package com.company.Utils;

import java.util.Random;

public class RandomUtil {
    static Random rand = new Random();
    public static void getCoin(){
        ConstantUtil.coin=rand.nextInt(2000) + 100;
    }
    public static void getExp(){
        ConstantUtil.exp=rand.nextInt(20) + 11;
    }
    public static void meetPet(){
        ConstantUtil.pet_id=rand.nextInt(200) + 1;
    }
    public static void init_Hp(){
        ConstantUtil.hp=rand.nextInt(20) + 15;
    }
    public static void init_Mp(){
        ConstantUtil.mp=rand.nextInt(20) + 15;
    }
    public static void init_Attk(){
        ConstantUtil.attk=rand.nextInt(12) + 8;
    }
    public static void init_Def(){
        ConstantUtil.def=rand.nextInt(12) + 8;
    }
    public static void is_success(){
        ConstantUtil.success=rand.nextInt(10);
    }
    public static void makeOneRandom(){
        ConstantUtil.explore_id=rand.nextInt(10000) + 1;
    }
    //宠物
    public static void makePetRandom(){
        ConstantUtil.pet_id=rand.nextInt(100) + 1;
    }
    //神兽
    public static void makeGodRandom(){
        ConstantUtil.pet_id=rand.nextInt(120) + 101;
    }
    //高级神兽
    public static void makeHighGodRandom(){
        ConstantUtil.pet_id=rand.nextInt(130) + 121;
    }
    //稀有神兽
    public static void makeRareRandom(){
        ConstantUtil.pet_id=rand.nextInt(135) + 131;
    }
}
