package com.company.Presenter.Impl;

import com.company.Model.Impl.FightModImpl;
import com.company.Model.Inter.FightModInter;
import com.company.Presenter.Inter.FightPreInter;
import com.company.Utils.FightUtil;

public class FightPreImpl implements FightPreInter {
    private FightModInter fightModInter;
    @Override
    public void onFightSuccess(String success) {
        System.out.println(success);

    }

    @Override
    public void onFightFail(String fail) {
        System.out.println(fail);

    }

    @Override
    public void fight() {
        fightModInter=new FightModImpl();
        fightModInter.getResult(FightUtil.myPet,FightUtil.enemyPet,this);
    }
}
