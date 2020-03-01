package com.company.Model.Inter;

import com.company.Bean.EnemyPet;
import com.company.Bean.MyPet;
import com.company.Presenter.Inter.FightPreInter;
import com.company.Presenter.Inter.PetPreInter;

public interface FightModInter {
    /***
     * @author wen
     * @created 2020/2/27
     * @param myPet 宠物属性
     * @param enemyPet 地方属性
     * @param fightPreInter 战斗接口
     */
    void getResult(MyPet myPet, EnemyPet enemyPet,FightPreInter fightPreInter);
}
