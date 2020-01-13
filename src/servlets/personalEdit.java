package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import Users.*;

/* 个人信息修改 */
@WebServlet("/personalEdit")
public class personalEdit extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String url = "/edit.jsp";
        int state = 500;
        try {
            if (request.getSession().getAttribute("user") != null){
                User user = (User)request.getSession().getAttribute("user");
                int sexNumber = Integer.parseInt(request.getParameter("sexNumber"));
                int age = Integer.parseInt(request.getParameter("age"));
                String userName = request.getParameter("UserName");
                String headPic = request.getParameter("headPic");
                String signature = request.getParameter("signature");
                String address = request.getParameter("address");
                String introduction = request.getParameter("introduction");
                String profession = request.getParameter("profession");
                String coverPic = request.getParameter("coverPic");
                String phoneNumber = request.getParameter("phoneNumber");
                if(phoneNumber.equals(user.getPhoneNumber())){
                    user.setHeadPic(headPic);
                    user.setCoverPic(coverPic);
                    user.setProfession(profession);
                    user.setUserName(userName);
                    user.setIntroduction(introduction);
                    user.setSignature(signature);
                    user.setAddress(address);
                    user.setAge(age);
                    user.setSexNumber(sexNumber);
                    userdao userd = new userdao(user);
                    int sta = userd.update();
                    if (sta != 0) state = 200;
                    request.getSession().setAttribute("user",user);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        request.setAttribute("state",state);
        getServletContext().getRequestDispatcher(url).forward(request,response);
    }
    @Override
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        doPost(request,response);
    }
}
