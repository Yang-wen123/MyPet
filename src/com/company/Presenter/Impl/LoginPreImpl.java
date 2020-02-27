package com.company.Presenter.Impl;

import com.company.Model.Impl.LoginModImpl;
import com.company.Model.Inter.LoginModInter;
import com.company.Presenter.Inter.LoginPreInter;
import com.company.Presenter.Inter.LoginPresentIter;
import com.company.Utils.*;

public class LoginPreImpl implements LoginPreInter, LoginPresentIter {
    private LoginModInter loginModInter;
    public LoginPreImpl(){

    }
    @Override
    public void onSuccess(String success) {
        System.out.println(success);
        DBUtil.openConn();
        PetUtil.query(ConstantUtil.now_username);
        if(PetUtil.user==ConstantUtil.now_username){
            PetWorldUtil.player_wprld();
        }else{
            PetWorldUtil.petWorld();
        }
        DBUtil.closeConn();
    }

    @Override
    public void onError(String error) {
        System.out.println(error);
    }

    @Override
    public void login() {
        loginModInter=new LoginModImpl();
        loginModInter.getresult(LoginUtil.playerBean,this);
    }
}
