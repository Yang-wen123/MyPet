package com.company.Utils;

import com.company.Bean.EnemyPet;
import com.company.Bean.MyPet;
import com.company.Presenter.Impl.FightPreImpl;

import java.util.Scanner;

public class FightUtil {
    public static MyPet myPet=new MyPet();
    public static EnemyPet enemyPet=new EnemyPet();
    public static int fight_pet=0;
    public static void fight(){
        System.out.println("敌方信息-->");
        EnemyUtil.enemy_query(ConstantUtil.npc_id);
        enemyPet.setLv(EnemyUtil.lv);
        enemyPet.setHp(EnemyUtil.hp);
        enemyPet.setMp(EnemyUtil.mp);
        enemyPet.setAttk(EnemyUtil.attk);
        enemyPet.setDef(EnemyUtil.def);
        enemyPet.setName(EnemyUtil.name);
        PetUtil.query_my_pet(ConstantUtil.now_username);
        System.out.println("请选择战斗宠物");
        Scanner scanner=new Scanner(System.in);
        fight_pet=scanner.nextInt();
        System.out.println("当前战斗宠物");
        PetUtil.query_my_pet(ConstantUtil.now_username,fight_pet);
        myPet.setLv(PetUtil.my_pet_lv);
        myPet.setHp(PetUtil.my_pet_hp);
        myPet.setMp(PetUtil.my_pet_mp);
        myPet.setAttk(PetUtil.my_pet_attk);
        myPet.setDef(PetUtil.my_pet_def);
        myPet.setExp(PetUtil.my_pet_exp);
        myPet.setName(PetUtil.my_pet_name);
        new FightPreImpl().fight();
    }
}
