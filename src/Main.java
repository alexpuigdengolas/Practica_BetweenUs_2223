import Conn.*;
import entities.User;
import entities.User;

import javax.swing.*;

public class Main {
    private static Data data;
    private static DBConnector conn;
    private static UserDAO userDAO;


    public static void main(String[] args) {


        //aixo s ha de adaptar al codi on toqui. JA registra
        userDAO = new UserSQLDAO();

        //es crea un user a lo guarro y es registra
        //User user = new User("mario","mario@mario.com","mario12345","mario12345");
        //userDAO.registerUser(user);

        //Comrpova login false no true si
        System.out.println(userDAO.checkLoginUser("mario","mario12345"));

    }
}