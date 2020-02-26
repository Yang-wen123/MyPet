package com.company.Model.Impl;

import com.company.Bean.PlayerBean;
import com.company.Model.Inter.LoginModInter;
import com.company.Presenter.Inter.LoginPreInter;
import com.company.Presenter.Inter.LoginPresentIter;
import com.company.Utils.DBUtil;
import com.company.Utils.LoginUtil;
import com.company.Utils.WelcomUtil;

import java.util.Scanner;

public class LoginModImpl implements LoginModInter {
    private int username;
    private String password;
    @Override
    public void getresult(PlayerBean playerBean, LoginPreInter loginPreInter) {
        username=playerBean.getUsername();
        password=playerBean.getPassword();
        DBUtil.openConn();
        try{
            String sql;
            sql = "SELECT username, password FROM login";
            DBUtil.stmt = DBUtil.conn.createStatement();
            DBUtil.rs = DBUtil.stmt.executeQuery(sql);
            //遍历查询，验证登录
            while(DBUtil.rs.next()){
                int user = DBUtil.rs.getInt("username");
                String pass = DBUtil.rs.getString("password");
                if(username==user){
                    if(password.equals(pass)){
                        loginPreInter.onSuccess("登录成功");
                        break;
                    }else{
                        while(true){
                            loginPreInter.onError("密码错误，请重新输入（输入0取消）");
                            Scanner s=new Scanner(System.in);
                            String new_pass=s.next();
                            if(new_pass.equals(pass)){
                                loginPreInter.onSuccess("登录成功");
                                break;
                            }
                        }
                    }
                }
            }
        }catch (Exception e){
            loginPreInter.onError("找不到这个用户");
            e.printStackTrace();
        }
        DBUtil.closeConn();
    }
}
