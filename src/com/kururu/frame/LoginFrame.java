package com.kururu.frame;

import com.kururu.basement.DataProcessing;
import com.kururu.basement.User;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Created by kururu on 2015/11/28.
 */
public class LoginFrame extends JFrame {

    public static String loginName;
    public static String loginPassword;
    public static User loginUser;
    public static JFrame mainFrame;
    public static JLabel mainPanelBackground,
    nameLabel,passwordLabel;
    public static JPanel mainPanel;
    public static JMenuBar mainMenu;
    public static JMenu fileMenu,workMenu,helpMenu;
    public static JMenuItem quitItem,
            serverItem,
            aboutItem;
    public static JButton loginButton,resetButton;

    public static ImageIcon mainBackground,
                     welcomeImage,
                     nameLabelBackground, passwordLabelBackground,
                     nameButtonBackground,resetButtonBackground;

    public static JTextField nameField;//set login name textfield component

    public static JPasswordField passwordField;//set login password textfield component

    public LoginFrame() {
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
        try{
            mainInitial();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mainInitial() throws Exception{
        mainFrame = new JFrame("档案管理系统");

        mainBackground = new ImageIcon("Icon/loginBackground.jpg");
        mainPanelBackground = new JLabel(mainBackground);
        mainPanelBackground.setBounds(0,0,mainBackground.getIconWidth(),mainBackground.getIconHeight());

        mainPanel = (JPanel)mainFrame.getContentPane();
        mainPanel.setOpaque(false);

        mainPanel.setLayout(null);
        mainFrame.getLayeredPane().add(mainPanelBackground,new Integer(Integer.MIN_VALUE));
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(mainBackground.getIconWidth(), mainBackground.getIconHeight());

        setMainMenu();
        setField();
        setLabel();
        setButton();

        mainPanel.add(nameField);
        mainPanel.add(passwordField);
        mainFrame.add(nameLabel);
        mainFrame.add(passwordLabel);
        mainFrame.add(loginButton);
        mainFrame.add(resetButton);

        mainFrame.setLocation(300, 300);
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);

    }
    // menuSetting funcs
    public void setMainMenu(){
        mainMenu = new JMenuBar();
        setFileMenu();
        setWorkMenu();
        setHelpMenu();
        mainMenu.add(fileMenu);
        mainMenu.add(workMenu);
        mainMenu.add(helpMenu);
        mainFrame.setJMenuBar(mainMenu);
    }

    public void setFileMenu(){
        fileMenu = new JMenu("file");

        quitItem = new JMenuItem("quit");
        fileMenu.add(quitItem);
        quitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == quitItem)
                    System.exit(0);
            }
        });
        fileMenu.add(quitItem);
    }

    public void setWorkMenu(){
        workMenu = new JMenu("work");

        serverItem = new JMenuItem("server");
        workMenu.add(serverItem);
        serverItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == serverItem){
                    JOptionPane.showMessageDialog(null, "Tips: server untapped","scientific",JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        workMenu.add(serverItem);
    }

    public void setHelpMenu(){
        helpMenu = new JMenu("help");

        aboutItem = new JMenuItem("about");
        helpMenu.add(aboutItem);
        aboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == aboutItem){
                    JOptionPane.showMessageDialog(null, "made by kururu007 in whut.\n"+"Thank you for use!","about",JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
    }

    public void setField(){
        nameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        nameField.setFont(new Font("Consolas", 0x1, 20));
        passwordField.setFont(new Font("Consolas", 0x1, 20));
        nameField.setBounds(mainBackground.getIconWidth() / 2,
                mainBackground.getIconHeight() / 3,
                mainBackground.getIconWidth() / 5,
                mainBackground.getIconHeight() / 15);
        passwordField.setBounds(mainBackground.getIconWidth()/2,
                mainBackground.getIconHeight()/3 + mainBackground.getIconHeight()/13,
                mainBackground.getIconWidth()/5,
                mainBackground.getIconHeight()/15);
        passwordField.setEchoChar('*');
    }

    public void setLabel(){
        nameLabel = new JLabel("name");
        passwordLabel = new JLabel("password");
        nameLabel.setBounds(
                mainBackground.getIconWidth() / 3 + mainBackground.getIconWidth() / 19,
                mainBackground.getIconHeight() / 3,
                mainBackground.getIconWidth() / 5,
                mainBackground.getIconHeight() / 15);
        nameLabel.setFont(new Font("Consolas", 0x1, 25));
        nameLabel.setBackground(Color.BLACK);
        //nameLabelBackground = new ImageIcon("Icon/")
        //nameLabel.setIcon();

        passwordLabel.setBounds(
                mainBackground.getIconWidth()/3 + mainBackground.getIconWidth()/50,
                mainBackground.getIconHeight()/3 + mainBackground.getIconHeight()/13,
                mainBackground.getIconWidth()/5,
                mainBackground.getIconHeight()/15
        );
        passwordLabel.setFont(new Font("Consolas", 1, 25));

    }

    public void setButton(){
        nameButtonBackground = new ImageIcon("Icon/nameButtonBackground.png");
        loginButton = new JButton("login",nameButtonBackground);
        loginButton.setUI(new BasicButtonUI());
        loginButton.setBounds(
                mainBackground.getIconWidth() / 5,
                3*mainBackground.getIconHeight() / 5,
                nameButtonBackground.getIconWidth()*4,
                3*nameButtonBackground.getIconHeight()/2);
        loginButton.setContentAreaFilled(false);
        loginButton.setFont(new Font("Consolas", 1, 25));
        loginButton.setMargin(new Insets(0, 0, 0, 0));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginName = nameField.getText();
                loginPassword = new String(passwordField.getPassword());
                try{
                    loginUser = DataProcessing.searchUser(loginName, loginPassword);
                }catch(SQLException SQLExceptionInLoginFrame){
                    SQLExceptionInLoginFrame.printStackTrace();
                }catch (IllegalStateException IllegalStateExceptionInLoginFrame){
                    IllegalStateExceptionInLoginFrame.printStackTrace();
                }finally {
                    if(loginName.equals("")){
                        JOptionPane.showMessageDialog(
                                null,
                                "LOGIN name can't be empty", "error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }else if(loginPassword.equals("")){
                        JOptionPane.showMessageDialog(
                                null,
                                "LOGIN password can't be empty", "error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }else if(loginUser != null){
                        if(loginUser.getRole().equals("administrator")){
                            JOptionPane.showMessageDialog(null,
                                    "Welcome back Administrator!" +  " " + loginUser.getName() +"\n" + "Thank you for use!",
                                    "LOGIN successfully!",
                                    JOptionPane.PLAIN_MESSAGE);
                            mainFrame.dispose();
                            new AdministratorFrame();
                        }else if(loginUser.getRole().equals("operator")){
                            JOptionPane.showMessageDialog(null,
                                    "Welcome back Operator!" +  " " + loginUser.getName() +"\n" + "Thank you for use!",
                                    "LOGIN successfully!",
                                    JOptionPane.PLAIN_MESSAGE);
                            mainFrame.dispose();
                            new OperatorFrame();
                        }else{
                            JOptionPane.showMessageDialog(null,
                                    "Welcome back Browser!" +  " " + loginUser.getName() +"\n" + "Thank you for use!",
                                    "LOGIN successfully!",
                                    JOptionPane.PLAIN_MESSAGE);
                            mainFrame.dispose();
                            new BrowserFrame();
                        }
                    }else{
                        JOptionPane.showMessageDialog(
                                null,
                                "name or password is wrong!", "error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        resetButtonBackground = new ImageIcon("Icon/resetButtonBackground.png");
        resetButton = new JButton("reset",resetButtonBackground);
        resetButton.setUI(new BasicButtonUI());
        resetButton.setBounds(
                4*mainBackground.getIconWidth() / 7,
                3*mainBackground.getIconHeight() / 5,
                nameButtonBackground.getIconWidth()*4,
                3*nameButtonBackground.getIconHeight()/2);
        resetButton.setContentAreaFilled(false);
        resetButton.setFont(new Font("Consolas", 0x1, 25));
        resetButton.setMargin(new Insets(0, 0, 0, 0));
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameField.setText("");
                passwordField.setText("");
            }
        });
    }
}
