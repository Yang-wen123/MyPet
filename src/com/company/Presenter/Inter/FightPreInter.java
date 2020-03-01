package com.company.Presenter.Inter;

public interface FightPreInter {
    /***
     * @param success 战斗胜利回调
     */
    void onFightSuccess(String success);
    /***
     * @param fail 战斗失败回调
     */
    void onFightFail(String fail);
    /***
     * @method 战斗方法
     */
    void fight();
}
