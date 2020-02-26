package com.company;

import com.company.Bean.PlayerBean;
import com.company.Utils.ConstantUtil;
import com.company.Utils.DBUtil;
import com.company.Utils.WelcomUtil;

import java.util.Scanner;


public class Main {
    public static void main(String[] args){
        while (!ConstantUtil.is_break){
            WelcomUtil.welcom();
        }
        System.out.print("再见！");
    }
}
