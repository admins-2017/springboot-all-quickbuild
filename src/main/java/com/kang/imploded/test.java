package com.kang.imploded;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class test {

    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
//        String encode = bCryptPasswordEncoder.encode("kdw051755");
//        System.out.println(encode);
        String encode="$2a$10$EJi3ULXh19SHoN7/EVcJBegIT5My6ZDYP0q2VKxkkqILvPaXgIX4a";
        boolean d = bCryptPasswordEncoder.matches("kdw051755.", encode);
        System.out.println(d);
    }
}
