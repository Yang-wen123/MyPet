package com.company.Bean;

public class EnemyPet {
    private int hp;
    private int mp;
    private int attk;
    private int def;
    private int lv;
    private String name;
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }
    public void setLv(int lv){
        this.lv=lv;
    }
    public int getLv(){
        return lv;
    }
    public void setHp(int hp){
        this.hp=hp;
    }
    public int getHp(){
        return hp;
    }
    public void setMp(int mp){
        this.mp=mp;
    }
    public int getMp(){
        return mp;
    }
    public void setAttk(int attk){
        this.attk=attk;
    }
    public int getAttk(){
        return attk;
    }
    public void setDef(int def){
        this.def=def;
    }
    public int getDef(){
        return def;
    }
}
