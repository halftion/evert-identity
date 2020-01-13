package servlets;

import Users.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;
import utils.RSAUtils;

/* 登录处理 */
@WebServlet("/login")
public class login extends HttpServlet {
    private final static String[] hexDigits = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
    private String serverPrivateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJ6euzrZak82eZBL2YX/ucigg9wxY/NurhiSIM3SkKpgmynVv0XU5cXQrgai4wKC4q3baLJp5zhu5jwx70VX+YBhNY/dqevlx0x7LxLtOR5wjs6GLMkZwywsaIsYNbJrYsdYHmDp0pEa6RiHPy70jAZqyxnzvofH85kdUYAtSKdxAgMBAAECgYEAmX5rGiCoQm0pp9vubEAxzRafYyYcXcY+DKLjwajAu07u98XS6UWC8DDnJp7bZGO57ZnDr9rRmIegnqGHtCfU1MF167rqOr65cJzwIqBjES4wk1cO1Jf3UWcSGJ+nWRPrGhdI9XxyFJYR7ljeOefGL6E40yd9GNrpY4q6yRev4r0CQQDgema/vEAojNRHomyszkRFodIdRsAWiynOHRBfTe3P36QcxxDKabWl5PlEH5j4GG+5t4G0hWMsz8p7W4CjeI5TAkEAtOTYpaUFS97eXgk/C2voU8LsB9EKwdmGqvX47Mrg+dcAv8yhhBbdYtEa9b8krXEf6WoGnZtm0KYORiA9BclSqwJAEVeDbpaVgkx27d3PdX4t6g85U0eu+U7g+Opn5GT51VBatlpuoqSOAvXwh+HFCI2iu/9ARvjzE3guFV+ZTYe3vwJBAJKGL8p00cZz/1DSUoPQ4z49zGxEcl808LU4SmqbpwIYmp3Bhi71SlwJnrniGzZ2RHAQFs3BG9X0O+mnoWY3fcMCQDbN/pEOWtzS0HMx6P/5bjrXkPZt6Ko35PSOv7oWSPusv14zOnBLmiqiHUYRyFV5uh7zohby9bOQoFNqx2w0kJk=";
    private String clientPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCenrs62WpPNnmQS9mF/7nIoIPcMWPzbq4YkiDN0pCqYJsp1b9F1OXF0K4GouMCguKt22iyaec4buY8Me9FV/mAYTWP3anr5cdMey8S7TkecI7OhizJGcMsLGiLGDWya2LHWB5g6dKRGukYhz8u9IwGassZ876Hx/OZHVGALUincQIDAQAB";
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        String url = "/verifyerror.html";
        int state = 500;
        RSAUtils rsaUtil = new RSAUtils();
        String info = "";
        String phoneNumber = request.getParameter("phoneNumber");
        try {
            phoneNumber = rsaUtil.encrypt(phoneNumber,clientPublicKey);
            System.out.println("签名：" + phoneNumber);
            phoneNumber = rsaUtil.decrypt(phoneNumber,serverPrivateKey);
            System.out.println("验签：" + phoneNumber);
        }catch (Exception e){e.printStackTrace();}
        String password = encodeByMD5(request.getParameter("Password"));
        User user = new User();
        userdao dao = new userdao(user);
        user = dao.getUser(phoneNumber);
        if(password.equals(password)){
            url = "/verify.jsp";
            state = 200;
            request.getSession().setAttribute("user",user);
            request.getSession().setMaxInactiveInterval(3600);
            info = "server";
            try {
                info = rsaUtil.encrypt(info,clientPublicKey);
                System.out.println("message：" + info);
                info = rsaUtil.decrypt(info,serverPrivateKey);
                Cookie cookie = new Cookie("info", info);
                response.addCookie(cookie);
            }catch (Exception e){e.printStackTrace();}
        }
        request.setAttribute("state",state);
        getServletContext().getRequestDispatcher(url).forward(request,response);
    }

    protected void doGet(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
        doPost(request,response);
    }

    private static String encodeByMD5(String originString){
        if (originString!=null) {
            try {
                //创建具有指定算法名称的信息摘要
                MessageDigest md5 = MessageDigest.getInstance("MD5");
                //使用指定的字节数组对摘要进行最后更新，然后完成摘要计算
                byte[] results = md5.digest(originString.getBytes());
                //将得到的字节数组变成字符串返回
                String result = byteArrayToHexString(results);
                return result;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private static String byteArrayToHexString(byte[] b){
        StringBuffer resultSb = new StringBuffer();
        for(int i=0;i<b.length;i++){
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b){
        int n = b;
        if(n<0)
            n=256+n;
        int d1 = n/16;
        int d2 = n%16;
        return hexDigits[d1] + hexDigits[d2];
    }

}
