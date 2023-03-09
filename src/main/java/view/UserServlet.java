package view;

import controller.ProvinceServices;
import controller.UserServices;
import model.Province;
import model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UserServlet", value = "/user")
public class UserServlet extends HttpServlet {
    UserServices userServices=new UserServices();
    ProvinceServices provinceServices=new ProvinceServices();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action=request.getParameter("action");
        if(action==null)action="";
        switch (action){
            case "create":
                showCreateUserForm(request,response);
                break;
            case "delete":
                deleteUser(request,response);
                break;
            case "update":
                showUpdateUserForm(request,response);
                break;
            default:
                viewAllUser(request,response);
        }
    }

    private void showUpdateUserForm(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher requestDispatcher= request.getRequestDispatcher("user/update.jsp");
        int id= Integer.parseInt(request.getParameter("id"));
        ArrayList<Province> list=provinceServices.readAll();
        request.setAttribute("provinces",list);
        User user=userServices.findByIndex(id);
        request.setAttribute("user",user);
        try {
            requestDispatcher.forward(request,response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showCreateUserForm(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher requestDispatcher=request.getRequestDispatcher("user/create.jsp");
        ArrayList<Province> list=provinceServices.readAll();
        request.setAttribute("provinces",list);
        try {
            requestDispatcher.forward(request,response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) {
        int id= Integer.parseInt(request.getParameter("id"));
        userServices.delete(id);
        viewAllUser(request,response);
    }

    private void viewAllUser(HttpServletRequest request, HttpServletResponse response) {
        List<User>list=userServices.readAll();
        request.setAttribute("users",list);
        RequestDispatcher requestDispatcher=request.getRequestDispatcher("user/user.jsp");
        try{
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action=request.getParameter("action");
        switch(action) {
            case "create":
                addUser(request,response);
                break;
            case "update":
                updateUser(request,response);
        }
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) {
        int id= Integer.parseInt(request.getParameter("province_id"));
        Province province=new Province(id);
        int user_id= Integer.parseInt(request.getParameter("id"));
        String name=request.getParameter("name");
        userServices.update(new User(user_id,name,province));
        viewAllUser(request,response);
    }

    private void addUser(HttpServletRequest request, HttpServletResponse response) {
        int id= Integer.parseInt(request.getParameter("province_id"));
        Province province=new Province(id);
        String name=request.getParameter("name");
        userServices.create(new User(name,province));
        viewAllUser(request,response);
    }
}
