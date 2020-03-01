package com.company.Utils;
/***
 * @author wen
 * @created 2020/2/27
 * @explain npc工具
 */
public class EnemyUtil {
    public static String name=null;
    public static int lv = 0,hp= 0,mp= 0,attk= 0,def= 0;
    public static void enemy_query(int npc_id){
        try{
            String sql;
            sql = "SELECT * FROM npc_pet where npc_id = "+npc_id+";";
            DBUtil.stmt = DBUtil.conn.createStatement();
            DBUtil.rs = DBUtil.stmt.executeQuery(sql);
            while(DBUtil.rs.next()){
                int pet_id=DBUtil.rs.getInt("pet_id");
                String sql_enemy;
                sql_enemy = "SELECT * FROM pet_info where pet_id=" + pet_id + "";
                DBUtil.stmt = DBUtil.conn.createStatement();
                DBUtil.rs = DBUtil.stmt.executeQuery(sql_enemy);
                System.out.println("宠物名  等级  血量  技能点  攻击力  防御力");
                while (DBUtil.rs.next()) {
                    name = DBUtil.rs.getString("pet_name");
                    lv = DBUtil.rs.getInt("pet_lv");
                    hp = DBUtil.rs.getInt("pet_hp");
                    mp = DBUtil.rs.getInt("pet_mp");
                    attk = DBUtil.rs.getInt("pet_attk");
                    def = DBUtil.rs.getInt("pet_def");
                }
                System.out.println(name+"  "+lv+"    "+hp+"    "+mp+"     "
                        +attk+"      "+def);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
