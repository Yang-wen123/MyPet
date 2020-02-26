package com.company.Utils;

import com.company.Bean.PlayerBean;

import java.sql.*;
import java.util.Scanner;

public class DBUtil {
    // MySQL 8.0 以下版本 - JDBC 驱动名及数据库 URL
    //static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    //static final String DB_URL = "jdbc:mysql://localhost:3306/RUNOOB";

    // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/pet?useSSL=false&serverTimezone=UTC";

    // 数据库的用户名与密码
    static final String USER = "root";
    static final String PASS = "123456";
    public static Connection conn = null;
    public static Statement stmt = null;
    public static ResultSet rs=null;
    // 注册数据库驱动
    static {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("注册失败！");
            e.printStackTrace();
        }
    }

    // 打开连接
    public static void openConn(){
        System.out.println("连接数据库...");
        try{
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        }catch (SQLException e){
            System.out.println("连接数据库失败");
            e.printStackTrace();
        }
    }

    // 关闭连接
    public static void closeConn() {
        // 关闭资源
        try{
            if(stmt!=null) stmt.close();
        }catch(SQLException se2){
            se2.printStackTrace();
        }
        try{
            if(conn!=null) conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }
        try{
            if(rs!=null) rs.close();
        }catch(SQLException se){
            se.printStackTrace();
        }
    }

    //查询已有用户
    public static void query(){
        // 执行查询
        System.out.println(" 查询中...");
        try{
            String sql;
            sql = "SELECT username, password FROM login";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            // 展开结果集数据库
            int i=1;
            while(rs.next()){
                // 通过字段检索
                int username  = rs.getInt("username");
                // 输出数据
                System.out.print("账户 " +i+":");
                System.out.print(" 用户名: " + username);
                System.out.print("\n");
                i++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //增加用户
    public static void addPalyer(){
        // 执行增加
        System.out.println("注册中...");
        try{
            System.out.print("请输入用户名:");
            Scanner input_name = new Scanner(System.in);
            int uname = input_name.nextInt();
            System.out.print("请输入密码（输入0取消）:");
            Scanner input_pass=new Scanner(System.in);
            String pass = input_pass.next();
            if(Integer.parseInt(pass)!=0){
                String sql = "insert into login values("+uname+" , '"+pass+"')";
                stmt = conn.createStatement();
                stmt.executeUpdate(sql);
                System.out.println("注册成功");
            }else {
                System.out.println("注册取消");
            }
        }catch (Exception e){
            System.out.println(" 注册失败");
            e.printStackTrace();
        }
    }
    //删除用户
    public static void deletePlayer(){
        // 执行增加
        System.out.println("删除中...");
        try{
            System.out.print("请输入要删除的用户名（输入0取消）：");
            Scanner delete_username = new Scanner(System.in);
            int delete = delete_username.nextInt();
            if(delete!=0){
                stmt = conn.createStatement();
                String sql = "SELECT username, password FROM login";
                rs = stmt.executeQuery(sql);
                int user=999999999;
                while(rs.next()){
                    // 通过字段检索
                    user  = rs.getInt("username");
                    System.out.println("用户名："+user);
                    if(delete==user){
                        String sqlDelete = "DELETE FROM login WHERE username="+delete+";";
                        stmt = conn.createStatement();
                        stmt.executeUpdate(sqlDelete);
                        System.out.println("删除成功");
                        break;
                    }
                }if(delete>user){
                    System.out.println("找不到这个用户！");
                }
            }else {
                System.out.println("删除取消");
            }
        }catch (Exception e){
            System.out.println(" 连接失败");
            e.printStackTrace();
        }
    }
    //修改用户名密码
    public static void updatePlayer(){
        System.out.print("修改密码");
        try {
            //用户输入要修改的账户
            System.out.print("请输入用户名（输入0取消）:");
            Scanner input_user = new Scanner(System.in);
            int update_user = input_user.nextInt();
            if(update_user!=0){
                //创建Statement对象
                stmt = conn.createStatement();
                String sql = "SELECT username, password FROM login";
                rs = stmt.executeQuery(sql);
                int username=999999999;
                // 展开结果集数据库
                while(rs.next()){
                    // 通过字段检索
                    username = rs.getInt("username");
                    if(update_user==username){
                        System.out.print("请输入新的密码:");
                        Scanner input_new=new Scanner(System.in);
                        String NewPwd = input_new.next();
                        //定义Sql语句
                        String UpdateSql = "UPDATE login SET password = '"+NewPwd+"' WHERE username = "+update_user+";";
                        stmt.executeUpdate(UpdateSql);
                        System.out.println("密码修改成功");
                        break;
                    }
                }if(update_user>username){
                    System.out.println("找不到这个用户！");
                }
            }else {
                System.out.println("修改取消");
            }
        } catch (SQLException e) {
            System.out.println("连接失败");
            e.printStackTrace();
        }
    }
}
