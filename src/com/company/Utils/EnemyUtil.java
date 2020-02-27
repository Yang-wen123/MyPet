package com.company.Utils;
/***
 * @author wen
 * @created 2020/2/27
 * @explain npc工具
 */
public class EnemyUtil {
    public static void enemy_query(int npc_id){
        try{
            String sql;
            sql = "SELECT * FROM npc_pet where npc_id = "+npc_id+";";
            DBUtil.stmt = DBUtil.conn.createStatement();
            DBUtil.rs = DBUtil.stmt.executeQuery(sql);
            while(DBUtil.rs.next()){
                int pet_id=DBUtil.rs.getInt("pet_id");
                PetUtil.query_all_pet(pet_id);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
