package com.kururu;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

import javax.swing.*;
import java.awt.*;

/**
 * Created by kururu on 2015/11/29.
 */
public class LoginJFrameFinal extends JFrame {

    static String loginName;
    static String loginPassword;
    static User loginUser;
    static JFrame mainFrame;
    static JLabel mainPanelBackground,
            nameLabel,passwordLabel;
    static JPanel mainPanel;
    static JMenuBar mainMenu;
    static JMenu fileMenu,workMenu,helpMenu;
    static JMenuItem quitItem,
            serverItem,
            aboutItem;
    static JButton loginButton,resetButton;

    static ImageIcon mainBackground,
            welcomeImage,
            nameLabelBackground, passwordLabelBackground;

    static JTextField nameField;//set login name textfield component

    static JPasswordField passwordField;//set login password textfield component

    public LoginJFrameFinal(){
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
        try{
            mainInitial();
            detailInitial();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mainInitial(){
        mainFrame = new JFrame("档案管理系统");

        mainBackground = new ImageIcon("Icon/loginBackground.jpg");
        mainPanelBackground = new JLabel(mainBackground);
        mainPanelBackground.setBounds(0,0,mainBackground.getIconWidth(),mainBackground.getIconHeight());
        nameLabel = new JLabel("name");
        passwordLabel = new JLabel("password");

        mainPanel = new JPanel();

        mainMenu = new JMenuBar();

        fileMenu = new JMenu("file");
        workMenu = new JMenu("work");
        helpMenu = new JMenu("help");

        quitItem = new JMenuItem("quit");
        serverItem = new JMenuItem("server");
        aboutItem = new JMenuItem("about");

        loginButton = new JButton("LOGIN");
        resetButton = new JButton("RESET");

        nameField = new JTextField(20);
        passwordField = new JPasswordField(20);
    }

    public void detailInitial(){

    }
}
