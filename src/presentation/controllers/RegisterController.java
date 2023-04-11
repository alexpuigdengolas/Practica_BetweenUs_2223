package presentation.controllers;

import business.entities.User;
import presentation.views.MainView;
import presentation.views.RegisterView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

public class RegisterController implements ActionListener {

    private final RegisterView registerView;
    private final MainView mainView;
    private final CardLayout viewComponents;

    public RegisterController(RegisterView registerView, MainView mainView, CardLayout viewComponents) {
        this.registerView = registerView;
        this.mainView = mainView;
        this.viewComponents = viewComponents;
    }

    /**
     * Este metodo recibe los strings de nuestro inicio de sesion y su contraseña y comprobamos que su estado sea le correcto
     * @param e es el objecto asignado al boton selecionado
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case RegisterView.BTN_REGISTER:
                String username = registerView.getInputUsername();
                String email = registerView.getInputEmail();
                String password = String.valueOf(registerView.getInputPassword());
                String confPassword = String.valueOf(registerView.getInputConfPassword());
                //TODO: Gestión con la base de datos


                break;

            case RegisterView.BTN_BACK:
                registerView.resetInputInfo();
                registerView.setmainView(mainView);
                registerView.setComponents(viewComponents);
                mainView.showLogin();
                break;
        }
    }

    /**
     * Este metodo sirve para validar que la contraseña tenga el formato correcto
     * @param password la string que contiene la contraseña que deseamos analizar
     * @return el estado de nuestra contraseña
     */
    public boolean isValidPassword(String password) {
        int upper = 0, lower = 0, number = 0;

        for(int i = 0; i < password.length(); i++)
        {
            char ch = password.charAt(i);
            if (ch >= 'A' && ch <= 'Z') {
                upper++;
            } else if (ch >= 'a' && ch <= 'z'){
                lower++;
            } else if (ch >= '0' && ch <= '9') {
                number++;
            }
        }
        if (upper == 0  || lower == 0 || number == 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Este metodo sirve para validar que la correo electronico tenga el formato correcto
     * @param email la string que contiene el correo que deseamos analizar
     * @return el estado de nuestro correo
     */
    public static boolean isValidMail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&-]+(?:\\."+"[a-zA-Z0-9_+&-]+)*@"+"(?:[a-zA-Z0-9-]+\\.)+[a-z"+"A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
}
