package com.company.Model.Impl;

import com.company.Bean.EnemyPet;
import com.company.Bean.MyPet;
import com.company.Model.Inter.FightModInter;
import com.company.Presenter.Inter.FightPreInter;
import com.company.Presenter.Inter.PetPreInter;
import com.company.Utils.ConstantUtil;
import com.company.Utils.FightUtil;
import com.company.Utils.PetUtil;
import com.company.Utils.RandomUtil;

public class FightModImpl implements FightModInter,PetPreInter {
    private static String name;
    private static int pet_lv = 0,pet_exp= 0,pet_hp= 0,pet_mp= 0,pet_attk= 0,pet_def= 0,exp=0;
    private static String enemy_name=null;
    private static int enemy_lv=0,enemy_hp=0,enemy_mp=0,enemy_attk=0,enemy_def=0;
    @Override
    public void getResult(MyPet myPet, EnemyPet enemyPet, FightPreInter fightPreInter) {
        name=myPet.getName();
        pet_lv=myPet.getLv();
        pet_hp=myPet.getHp();
        pet_mp=myPet.getMp();
        pet_attk=myPet.getAttk();
        pet_def=myPet.getDef();
        pet_exp=myPet.getExp();
        enemy_name=enemyPet.getName();
        enemy_lv=enemyPet.getLv();
        enemy_hp=enemyPet.getHp();
        enemy_mp=enemyPet.getMp();
        enemy_attk=enemyPet.getAttk();
        enemy_def=enemyPet.getDef();
        exp=100-(pet_lv-enemy_lv);
        if(pet_lv>=enemy_lv){
            myBout();
            if(pet_mp<=0){
                pet_hp=0;
                fightPreInter.onFightFail("战斗失败，快去恢复中心给宠物治疗伤势吧！");
                PetUtil.update_hp(ConstantUtil.now_username,FightUtil.fight_pet,pet_hp,pet_mp);
            }else if(enemy_mp<=0){
                enemy_hp=0;
                fightPreInter.onFightSuccess("战斗胜利,获得经验："+exp);
                pet_exp+=exp;
                lvUpOrEvolve();
            }else if(pet_hp<=0){
                pet_hp=0;
                fightPreInter.onFightFail("战斗失败，快去恢复中心给宠物治疗伤势吧！");
                PetUtil.update_hp(ConstantUtil.now_username,FightUtil.fight_pet,pet_hp,pet_mp);
            }else {
                fightPreInter.onFightSuccess("战斗胜利,获得经验："+exp);
                pet_exp+=exp;
                lvUpOrEvolve();
            }
        }else{
            enemyBout();
            if(pet_mp<=0){
                pet_hp=0;
                fightPreInter.onFightFail("战斗失败，快去恢复中心给宠物治疗伤势吧！");
                PetUtil.update_hp(ConstantUtil.now_username,FightUtil.fight_pet,pet_hp,pet_mp);
            }else if(enemy_mp<=0){
                enemy_hp=0;
                fightPreInter.onFightSuccess("战斗胜利,获得经验："+exp);
                pet_exp+=exp;
                lvUpOrEvolve();
            }else if(pet_hp<=0){
                pet_hp=0;
                fightPreInter.onFightFail("战斗失败，快去恢复中心给宠物治疗伤势吧！");
                PetUtil.update_hp(ConstantUtil.now_username,FightUtil.fight_pet,pet_hp,pet_mp);
            }else {
                fightPreInter.onFightSuccess("战斗胜利,获得经验："+exp);
                pet_exp+=exp;
                lvUpOrEvolve();
            }
        }
    }

    @Override
    public void lvUp() {
        pet_lv++;
        PetUtil.set_lv(ConstantUtil.now_username,FightUtil.fight_pet,pet_lv);
        RandomUtil.get_lv_up();
        PetUtil.set_hp(ConstantUtil.now_username,FightUtil.fight_pet,pet_hp+ConstantUtil.lv_up);
        PetUtil.set_mp(ConstantUtil.now_username,FightUtil.fight_pet,pet_mp+ConstantUtil.lv_up);
        PetUtil.set_attk(ConstantUtil.now_username,FightUtil.fight_pet,pet_attk+ConstantUtil.lv_up);
        PetUtil.set_def(ConstantUtil.now_username,FightUtil.fight_pet,pet_def+ConstantUtil.lv_up);
        System.out.println("等级提升,hp，mp，攻击力，防御力增加："+ConstantUtil.lv_up);
    }

    @Override
    public void evolve() {
        System.out.println("恭喜你训练师，您的宠物进化了"+name+"-->裂空座");
        PetUtil.set_evolve(ConstantUtil.now_username,FightUtil.fight_pet,"裂空座");
        PetUtil.set_hp(ConstantUtil.now_username,FightUtil.fight_pet,pet_hp+40);
        PetUtil.set_mp(ConstantUtil.now_username,FightUtil.fight_pet,pet_mp+40);
        PetUtil.set_attk(ConstantUtil.now_username,FightUtil.fight_pet,pet_attk+30);
        PetUtil.set_def(ConstantUtil.now_username,FightUtil.fight_pet,pet_def+20);
        PetUtil.query_my_pet(ConstantUtil.now_username,FightUtil.fight_pet);
    }
    private void lvUpOrEvolve(){
        while(pet_exp>=100){
            pet_exp=pet_exp-100;
            PetUtil.set_exp(ConstantUtil.now_username,FightUtil.fight_pet,pet_exp);
            lvUp();
            if(pet_lv==16){
                evolve();
            }else if(pet_lv==36){
                evolve();
            }
        }
        PetUtil.set_exp(ConstantUtil.now_username,FightUtil.fight_pet,pet_exp);
    }
    @Override
    public void myBout() {
        do{
            pet_mp--;
            if(pet_mp<=0){
                pet_mp=0;
                System.out.println("我方剩余技能:"+pet_mp);
                break;
            }else{
                System.out.println("我方剩余技能:"+pet_mp);
            }
            int my_dam=2*(pet_attk)-enemy_def;
            if(my_dam<=0){
                my_dam=1;
            }
            enemy_hp-=my_dam;
            if(enemy_hp<=0){
                enemy_hp=0;
                System.out.println("敌方血量:"+enemy_hp);
                break;
            }else {
                System.out.println("敌方血量:"+enemy_hp);
            }
            enemy_mp--;
            if(enemy_mp<=0){
                enemy_mp=0;
                System.out.println("敌方方剩余技能:"+pet_mp);

                break;
            }else{
                System.out.println("敌方剩余技能:"+enemy_mp);
            }
            int ememy_dam=2*(enemy_attk)-pet_def;
            if(ememy_dam<=0){
                ememy_dam=1;
            }
            pet_hp-=ememy_dam;
            if(pet_hp<=0){
                pet_hp=0;
                System.out.println("我方血量:"+pet_hp);
                break;
            }else {
                System.out.println("我方血量:"+pet_hp);
            }
        }while (true);
    }

    @Override
    public void enemyBout() {
        do{
            enemy_mp--;
            if(enemy_mp<=0){
                break;
            }else{
                System.out.println("敌方剩余技能:"+enemy_mp);
            }
            int ememy_dam=2*(enemy_attk)-pet_def;
            if(ememy_dam<=0){
                ememy_dam=1;
            }
            pet_hp-=ememy_dam;
            if(pet_hp<=0){
                pet_hp=0;
                System.out.println("我方血量:"+pet_hp);
                break;
            }else {
                System.out.println("我方血量:"+pet_hp);
            }
            pet_mp--;
            if(pet_mp<=0){
                break;
            }else{
                System.out.println("我方剩余技能:"+pet_mp);
            }
            int my_dam=2*(pet_attk)-enemy_def;
            if(my_dam<=0){
                my_dam=1;
            }
            enemy_hp-=my_dam;
            if(enemy_hp<=0){
                enemy_hp=0;
                System.out.println("敌方血量:"+enemy_hp);
                break;
            }else {
                System.out.println("敌方血量:"+enemy_hp);
            }
        }while (true);
    }
}
