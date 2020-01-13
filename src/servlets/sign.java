package servlets;

import Users.User;
import Users.userdao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 注册 */
@WebServlet("/sign")
public class sign extends HttpServlet {
    private final static String[] hexDigits = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        String url = "/signup.jsp";
        int state = 500;
        String phoneNumber = request.getParameter("Phone");
        String password = request.getParameter("Password");
        String password2 = request.getParameter("Password2");
        Pattern p = Pattern.compile("^1[0-9]{10}$");
        Matcher m = p.matcher(phoneNumber);
        boolean b = m.matches();
        if(b){
            if (password.equals(password2)){
                password = encodeByMD5(password);
                User user = new User();
                userdao dao = new userdao(user);
                if(!dao.checkuser(phoneNumber)){
                    user.setPhoneNumber(phoneNumber);
                    user.setPassword(password);
                    dao = new userdao(user);
                    dao.insert();
                    state = 200;
                    request.getSession().setAttribute("user",user);
                    request.getSession().setMaxInactiveInterval(3600);
                    url = "/index.jsp";
                }
            }
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
