package com.company.Model.Inter;

import com.company.Bean.PlayerBean;
import com.company.Presenter.Inter.LoginPreInter;


public interface LoginModInter {
    /***
     * @author wen
     * @created 2020/2/27
     * @param playerBean 用户名密码
     * @param loginPreInter 登录接口
     */
    void getresult(PlayerBean playerBean, LoginPreInter loginPreInter);
}
