package com.leyou.auth.test;

import com.leyou.entity.UserInfo;
import com.leyou.utils.JwtUtils;
import com.leyou.utils.RsaUtils;
import org.junit.Before;
import org.junit.Test;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @author shkstart
 * @create 2020-04-10 13:19
 */
public class JwtTest {
    private static final String pubKeyPath = "C:\\Users\\haiyaofeng\\Desktop\\haha\\rsa.pub";

    private static final String priKeyPath = "C:\\Users\\haiyaofeng\\Desktop\\haha\\rsa.pri";

    private PublicKey publicKey;

    private PrivateKey privateKey;

    @Test
    public void testRsa() throws Exception {
        RsaUtils.generateKey(pubKeyPath, priKeyPath, "234");
    }

    @Before
    public void testGetRsa() throws Exception {
        this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
        this.privateKey = RsaUtils.getPrivateKey(priKeyPath);
    }

    @Test
    public void testGenerateToken() throws Exception {
        // 生成token
        String token = JwtUtils.generateToken(new UserInfo(20L, "jack"), privateKey, 5);
        System.out.println("token = " + token);
    }

    @Test
    public void testParseToken() throws Exception {
        String token = "eyJhbGciOiJSUzI1NiJ9.eyJpZCI6MjAsInVzZXJuYW1lIjoiamFjayIsImV4cCI6MTU4Njk0MjkwMX0.AK22us6vfWH4YUOWLfhsc_ifwGjH3VjMDWB9JNNuP4jeSPaAXlRcEdEEgCwttzmyZpN1bCrfkcIGB5VNtzaOygPHuJQZCe0DZbMu4OW1tT_JkPeBMmeBVb1q9t9zRDw3jmoHGyIyn1tfg9tshKtT1CUMOM2H0XrqJFISJDSwaTk";

        // 解析token
        UserInfo user = JwtUtils.getInfoFromToken(token, publicKey);
        System.out.println("id: " + user.getId());
        System.out.println("userName: " + user.getUsername());
    }
}
