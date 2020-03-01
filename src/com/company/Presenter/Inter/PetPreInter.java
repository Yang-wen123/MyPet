package com.company.Presenter.Inter;

public interface PetPreInter {
    /***
     * 宠物升级回调
     */
    void lvUp();

    /***
     * 宠物进化回调
     */
    void evolve();
    /***
     * 我方战斗回合
     */
    void myBout();
    /***
     * 敌方战斗回合
     */
    void enemyBout();
}
