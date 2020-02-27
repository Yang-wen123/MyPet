package com.company.Utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class PetUtil {
    public static int user;
    public static int hp=0;
    public static int mp=0;
    public static int attk=0;
    public static int lv=0;
    public static int def=0;
    public static int exp=0;
    public static String pet_name=null;
    public static int town_id;
    public static int my_pet_id=0;
    //创建玩家
    public static void add_user(int username,int sex,int coin,int pet_number,int town_id){
        try{
            String sql;
            sql = "insert into player_info values("+username+" , '"+sex+"' , '"+coin+"' , '"+pet_number+"' , '"+town_id+"')";
            DBUtil.stmt = DBUtil.conn.createStatement();
            DBUtil.stmt.executeUpdate(sql);
            System.out.println("玩家信息已保存，开始您的游戏吧 ");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //查询用户信息
    public static void query(int username){
        // 执行查询
        System.out.println(" 玩家信息查询中...");
        try{
            String sql;
            sql = "SELECT * FROM player_info where username = "+username+";";
            DBUtil.stmt = DBUtil.conn.createStatement();
            DBUtil.rs = DBUtil.stmt.executeQuery(sql);
            while(DBUtil.rs.next()){
                user  = DBUtil.rs.getInt("username");
                int sex=DBUtil.rs.getInt("sex");
                int coin=DBUtil.rs.getInt("coin");
                int pet_number=DBUtil.rs.getInt("petnumber");
                int town_id=DBUtil.rs.getInt("town_id");
                String sql_town;
                sql_town = "SELECT * FROM town where town_id = "+town_id+";";
                DBUtil.stmt = DBUtil.conn.createStatement();
                DBUtil.rs = DBUtil.stmt.executeQuery(sql_town);
                while(DBUtil.rs.next()) {
                    ConstantUtil.town = DBUtil.rs.getString("town_name");
                }
                System.out.print(" 用户名: " + user);
                if(sex==1){
                    System.out.print(" 性别: 男");
                }else {
                    System.out.print(" 性别: 女");
                }
                System.out.print(" 金币: " + coin);
                System.out.print(" 宠物数量: " + pet_number);
                System.out.print(" 当前城镇: " + ConstantUtil.town+"\t胜率："+(100-10*town_id)+"%");
                System.out.print("\n");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //切换城镇
    public static void change_town(int username,int town_id){
        try{
            String sql;
            sql = "UPDATE player_info SET town_id = '"+town_id+"' where username = "+username+";";
            DBUtil.stmt = DBUtil.conn.createStatement();
            DBUtil.stmt.executeUpdate(sql);
            findTown();
            System.out.println("切换成功，当前地区->"+ConstantUtil.town);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void findTown() {
        try{
            String sql;
            sql = "SELECT * FROM player_info where username = "+ConstantUtil.now_username+";";
            DBUtil.stmt = DBUtil.conn.createStatement();
            DBUtil.rs = DBUtil.stmt.executeQuery(sql);
            while(DBUtil.rs.next()){
                town_id=DBUtil.rs.getInt("town_id");
                String sql_town;
                sql_town = "SELECT * FROM town where town_id = "+town_id+";";
                DBUtil.stmt = DBUtil.conn.createStatement();
                DBUtil.rs = DBUtil.stmt.executeQuery(sql_town);
                while(DBUtil.rs.next()) {
                    ConstantUtil.town = DBUtil.rs.getString("town_name");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //金币增加
    public static void add_coin(int username,int add_coin){
        try{
            String sql;
            sql = "SELECT * FROM player_info where username = "+username+";";
            DBUtil.stmt = DBUtil.conn.createStatement();
            DBUtil.rs = DBUtil.stmt.executeQuery(sql);
            int new_coin=0;
            while (DBUtil.rs.next()){
                int coin=DBUtil.rs.getInt("coin");
                new_coin=coin+add_coin;
            }
            String sql_add;
            sql_add = "UPDATE player_info SET coin = '"+new_coin+"' where username = "+username+";";
            DBUtil.stmt = DBUtil.conn.createStatement();
            DBUtil.stmt.executeUpdate(sql_add);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //得到宠物
    public static void add_pet(int username,int pet_id){
        try{
            String sql;
            sql = "insert into player_pet(username,pet_id) values ("+username+" , '"+pet_id+"' )";
            DBUtil.stmt = DBUtil.conn.createStatement();
            DBUtil.stmt.executeUpdate(sql);
            query_all_pet(pet_id);
            query_my_pet(ConstantUtil.now_username);
            my_pet_id++;
            String sql_main;
            sql_main= "insert into mypet(pet_id,name,lv,hp,mp,attk,def,exp,username,my_pet_id) values("+pet_id+
                    " , '"+pet_name+"' , '"+lv+"' , '"+hp+"' , '"+mp+"' , '"+attk+"' , '"+def+"' , '"+exp+
                    "' , '"+username+"' , '"+my_pet_id+"')";
            DBUtil.stmt = DBUtil.conn.createStatement();
            DBUtil.stmt.executeUpdate(sql_main);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //查看宠物图鉴
    public static void query_all_pet(){
        try{
            String sql;
            sql = "SELECT * FROM pet_info";
            DBUtil.stmt = DBUtil.conn.createStatement();
            DBUtil.rs = DBUtil.stmt.executeQuery(sql);
            System.out.println("宠物名  等级  血量  技能点  攻击力  防御力  经验");
            while (DBUtil.rs.next()){
                String pet_name=DBUtil.rs.getString("pet_name");
                int pet_lv=DBUtil.rs.getInt("pet_lv");
                int pet_exp=DBUtil.rs.getInt("pet_exp");
                int pet_hp=DBUtil.rs.getInt("pet_hp");
                int pet_mp=DBUtil.rs.getInt("pet_mp");
                int pet_attk=DBUtil.rs.getInt("pet_attk");
                int pet_def=DBUtil.rs.getInt("pet_def");
                System.out.println(pet_name+"  "+pet_lv+"    "+pet_hp+"    "+pet_mp+"     "
                        +pet_attk+"      "+pet_def+"      "+pet_exp);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //查找宠物id
    public static void query_all_pet(int pet_id) {
        try {
            String sql;
            sql = "SELECT * FROM pet_info where pet_id=" + pet_id + "";
            DBUtil.stmt = DBUtil.conn.createStatement();
            DBUtil.rs = DBUtil.stmt.executeQuery(sql);
            System.out.println("宠物名  等级  血量  技能点  攻击力  防御力  经验");
            while (DBUtil.rs.next()) {
                pet_name = DBUtil.rs.getString("pet_name");
                lv = DBUtil.rs.getInt("pet_lv");
                exp = DBUtil.rs.getInt("pet_exp");
                hp = DBUtil.rs.getInt("pet_hp");
                mp = DBUtil.rs.getInt("pet_mp");
                attk = DBUtil.rs.getInt("pet_attk");
                def = DBUtil.rs.getInt("pet_def");
            }
            System.out.println(pet_name+"  "+lv+"    "+hp+"    "+mp+"     "
                    +attk+"      "+def+"      "+exp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //查看我的宠物
    public static void query_my_pet(int username){
        try{
            System.out.println("宠物背包-->>");
            System.out.println("序号  宠物名  等级  血量  技能点  攻击力  防御力  经验");
            String sql_petname;
            sql_petname = "SELECT * FROM mypet where username = "+username+"";
            DBUtil.stmt = DBUtil.conn.createStatement();
            DBUtil.rs = DBUtil.stmt.executeQuery(sql_petname);
            int pet_lv = 0,pet_exp= 0,pet_hp= 0,pet_mp= 0,pet_attk= 0,pet_def= 0;
            String pet_name=null;
            while (DBUtil.rs.next()){
                my_pet_id=DBUtil.rs.getInt("my_pet_id");
                pet_name=DBUtil.rs.getString("name");
                pet_lv=DBUtil.rs.getInt("lv");
                pet_exp=DBUtil.rs.getInt("exp");
                pet_hp=DBUtil.rs.getInt("hp");
                pet_mp=DBUtil.rs.getInt("mp");
                pet_attk=DBUtil.rs.getInt("attk");
                pet_def=DBUtil.rs.getInt("def");
                System.out.println(my_pet_id+"     "+pet_name+"  "+pet_lv+"    "+pet_hp+"    "+pet_mp+"     "
                        +pet_attk+"      "+pet_def+"      "+pet_exp);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //减少宠物血量
    public static void update_hp(int username,int my_pet_id) {
        try {
            String sql_hp;
            String sql_petname;
            int pet_hp=0;
            sql_petname = "SELECT * FROM mypet where my_pet_id = "+my_pet_id+" and username = "+username+"";
            DBUtil.stmt = DBUtil.conn.createStatement();
            DBUtil.rs = DBUtil.stmt.executeQuery(sql_petname);
            while (DBUtil.rs.next()){
                pet_hp=DBUtil.rs.getInt("hp");
            }
            int new_hp = pet_hp-10;
            sql_hp = "UPDATE mypet SET hp = '"+new_hp+"' where my_pet_id = "+my_pet_id+" and username = "+username+"";
            DBUtil.stmt = DBUtil.conn.createStatement();
            DBUtil.stmt.executeUpdate(sql_hp);
            System.out.println("宠物血量减少"+10);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void recover_pet(int username,int my_pet_id) {
        try {
            String sql_hp,sql_pet_id;
            String sql_petname;
            int pet_hp=0,pet_id=0;
            sql_pet_id = "SELECT * FROM mypet where my_pet_id = "+my_pet_id+"";
            DBUtil.stmt = DBUtil.conn.createStatement();
            DBUtil.rs = DBUtil.stmt.executeQuery(sql_pet_id);
            while (DBUtil.rs.next()){
                pet_id=DBUtil.rs.getInt("pet_id");
            }
            sql_petname = "SELECT * FROM pet_info where pet_id = "+pet_id+"";
            DBUtil.stmt = DBUtil.conn.createStatement();
            DBUtil.rs = DBUtil.stmt.executeQuery(sql_petname);
            while (DBUtil.rs.next()){
                pet_hp=DBUtil.rs.getInt("pet_hp");
            }
            sql_hp = "UPDATE mypet SET hp = '"+pet_hp+"' where my_pet_id = "+my_pet_id+" and username = "+username+"";
            DBUtil.stmt = DBUtil.conn.createStatement();
            DBUtil.stmt.executeUpdate(sql_hp);
            System.out.println("宠物恢复健康，当前hp："+pet_hp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
