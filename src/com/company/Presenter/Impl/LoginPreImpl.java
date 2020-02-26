package com.company.Presenter.Impl;

import com.company.Bean.PlayerBean;
import com.company.Model.Impl.LoginModImpl;
import com.company.Model.Inter.LoginModInter;
import com.company.Presenter.Inter.LoginPreInter;
import com.company.Presenter.Inter.LoginPresentIter;
import com.company.Utils.LoginUtil;
import com.company.Utils.PetWorld;

import java.util.ArrayList;

public class LoginPreImpl implements LoginPreInter, LoginPresentIter {
    private LoginModInter loginModInter;
    public LoginPreImpl(){

    }
    @Override
    public void onSuccess(String success) {
        System.out.println(success);
        PetWorld.petWorld();
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
