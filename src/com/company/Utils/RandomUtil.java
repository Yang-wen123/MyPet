package com.company.Utils;

import java.util.Random;

/***
 * @author wen
 * @created 2020/2/27
 * @explain 随机数生成，实现概率事件
 */
public class RandomUtil {
    static Random rand = new Random();
    public static void getCoin(){
        ConstantUtil.coin=rand.nextInt(1901) + 100;
    }
    public static void getExp(){
        ConstantUtil.exp=rand.nextInt(12) + 11;
    }
    public static void meetPet(){
        ConstantUtil.pet_id=rand.nextInt(200) + 1;
    }
    public static void init_Hp(){
        ConstantUtil.hp=rand.nextInt(6) + 15;
    }
    public static void init_Mp(){
        ConstantUtil.mp=rand.nextInt(6) + 15;
    }
    public static void init_Attk(){
        ConstantUtil.attk=rand.nextInt(5) + 8;
    }
    public static void init_Def(){
        ConstantUtil.def=rand.nextInt(5) + 8;
    }
    public static void is_success(){
        ConstantUtil.success=rand.nextInt(10);
    }
    public static void makeOneRandom(){
        ConstantUtil.explore_id=rand.nextInt(1000) + 1;
    }
    //宠物
    public static void makePetRandom(){
        ConstantUtil.pet_id=rand.nextInt(3) + 1;
    }
    //神兽
    public static void makeGodRandom(){
        ConstantUtil.pet_id=rand.nextInt(4) + 3;
    }
    //高级神兽
    public static void makeHighGodRandom(){
        ConstantUtil.pet_id=rand.nextInt(4) + 6;
    }
    //稀有神兽
    public static void makeRareRandom(){
        ConstantUtil.pet_id=rand.nextInt(4) + 9;
    }
    //获取敌方信息
    public static void get_npc_id(){
        ConstantUtil.npc_id=rand.nextInt(20) + 1;
    }
    //宠物升级属性提升值
    public static void get_lv_up(){
        ConstantUtil.lv_up=rand.nextInt(3) + 2;
    }
}
