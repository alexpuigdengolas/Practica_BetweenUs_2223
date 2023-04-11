package presentation.views;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {

    static final int width = 1000;

    static final int height = 700;

    private final JPanel jpView = new JPanel();

    private CardLayout viewComponents;

    private RegisterView registerView;
    private LoginView loginView;


    public MainView(CardLayout viewComponents, RegisterView registerView, LoginView loginView) {
        this.viewComponents = viewComponents;
        this.setLayout(viewComponents);
        this.configureWindow();
        this.configureMainView();

        this.loginView = loginView;
        this.registerView = registerView;

        this.add(loginView, "loginView");
        this.add(registerView, "registerView");
    }

    /**
     * Este metodo generara la vista inicial con todos los parametros deseados
     */
    private void configureMainView() {
        jpView.setBackground(Color.BLACK);
        this.add(jpView);
        this.getContentPane().add(jpView, "main");
        this.getViewComponent().show(this.getContentPane(), "main");
    }

    /**
     * Getter para devolver los componentes del card layout
     *
     * @return card layout
     */
    private CardLayout getViewComponent() {
        return viewComponents;
    }

    /**
     * Este metodo generara el nombre de la ventana en si, con el nombre del programa y algun parametro mas
     */
    private void configureWindow() {
        setTitle("Between Us");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setSize(width, height);
    }

    /**
     * Este metodo servira para iniciar la vista de nuestro codigo
     */
    public void start() {
        setVisible(true);
        this.getViewComponent().show(this.getContentPane(), "loginView");
    }

    /**
     * Este metodo nos mostrara la pantalla de registro
     */
    public void showRegister() {
        this.getViewComponent().show(this.getContentPane(), "registerView");
    }

    /**
     * Este metodo nos mostrara la pantalla de inicio de sesion
     */
    public void showLogin() {
        this.getViewComponent().show(this.getContentPane(), "loginView");
    }
}

