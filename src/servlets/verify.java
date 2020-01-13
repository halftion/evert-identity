package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.crypto.Cipher;
import java.security.Key;
import javax.crypto.KeyGenerator;


@WebServlet("/verify")
public class verify extends HttpServlet {
    private Key key;
    KeyGenerator keyGenerator;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String url = "/verifyerror.html";
        int state = 500;
        String success = "";
        byte[] cipherText = null;
        try{
            success = request.getParameter("success");
            url = "/index";
            // 生成Cipher对象
            Cipher cipher=Cipher.getInstance("AES");
            // 用密钥加密明文(plainText),生成密文(cipherText)
            key = initKey(request.getSession().getId());
            cipher.init(Cipher.ENCRYPT_MODE, key); // 操作模式为加密(Cipher.ENCRYPT_MODE),key为密钥
            cipherText = cipher.doFinal(success.getBytes());// 得到加密后的字节数组
            String str = new String(cipherText);
            state = 200;
            System.out.println("数据内容：" + str);
            System.out.println("数据解密后：" + success);
        }catch (Exception e){
            e.printStackTrace();
        }
        request.setAttribute("state",state);
        getServletContext().getRequestDispatcher(url).forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    public Key initKey(String keyString) {

        try {
            // 初始化密钥key
            keyGenerator =KeyGenerator.getInstance("AES");
            keyGenerator.init(256);// 选择AES算法,密钥长度必须扩展为256位
            key = keyGenerator.generateKey();// 生成密钥，每次生成的密钥都是不一样的

        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return key;
    }
}
