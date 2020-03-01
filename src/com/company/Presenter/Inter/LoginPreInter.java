package com.company.Presenter.Inter;

import com.company.Bean.PlayerBean;

public interface LoginPreInter {
    /**
     * 用户登录方法
     * @param success  登录成功回调
     *
     */
    void onSuccess(String success);

    void onError(String error);

}
