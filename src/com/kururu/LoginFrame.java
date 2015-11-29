package com.kururu;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

/**
 * Created by kururu on 2015/11/28.
 */
public class LoginFrame extends JFrame {

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
                     nameLabelBackground, passwordLabelBackground,
                     nameButtonBackground,resetButtonBackground;

    static JTextField nameField;//set login name textfield component

    static JPasswordField passwordField;//set login password textfield component

    public LoginFrame() {
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
        try{
            mainInitial();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mainInitial() throws Exception{
        mainFrame = new JFrame("µµ∞∏π‹¿ÌœµÕ≥");

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
        nameField.setBounds(mainBackground.getIconWidth()/2,
                mainBackground.getIconHeight()/3,
                mainBackground.getIconWidth()/5,
                mainBackground.getIconHeight()/15);
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
                loginUser = DataProcessing.search(loginName,loginPassword);
                //System.out.println(loginName+"\n"+passwordField.getPassword()+"\n"+loginPassword);
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
                    loginUser.showMenu();
                    return;
                }else{
                    JOptionPane.showMessageDialog(
                            null,
                            "name or password is wrong!", "error",
                            JOptionPane.ERROR_MESSAGE);
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

    /*@Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == resetButton){
            nameField.setText("");
            passwordField.setText("");
        }
        if(e.getSource() == loginButton){
            loginName = nameField.getText();
            loginPassword = Arrays.toString(passwordField.getPassword());
            if(loginName.equals("")){
                JOptionPane.showMessageDialog(null, "Ë¥¶Âè∑‰∏çËÉΩ‰∏∫Á©∫", "¥ÌŒÛ", JOptionPane.ERROR_MESSAGE);
            }else{
                if(loginPassword.equals("")){
                    JOptionPane.showMessageDialog(null, "ÂØÜÁ†Å‰∏çËÉΩ‰∏∫Á©∫", "ÈîôËØØ", JOptionPane.ERROR_MESSAGE);
                }else{
                    loginUser = DataProcessing.search(loginName,loginPassword);
                    if(loginUser == null){
                        JOptionPane.showMessageDialog(null, "ÂØÜÁ†ÅÈîôËØØÔº?", "ÈîôËØØ", JOptionPane.ERROR_MESSAGE);
                    }else
                        loginUser.showMenu();
                }
            }
        }
    }*/
}
