package com.kururu.frame;

import com.kururu.basement.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

/**
 * Created by kururu on 2015/11/25.
 */
public class SystemFrame extends JFrame implements ActionListener{

    static String loginName;
    static String loginPassword;

    static User loginUser;

    static Dimension screenSize;

    static ImageIcon welcomeImage;

    static JPanel
            mainPanel,
            welcomePanel,
            fieldPanel,namePanel,passwordPanel,
            buttonPanel;

    static JMenuBar mainMenu;

    static JMenu fileMenu,workMenu,helpMenu;

    static JMenuItem
            quitItem,
            serverItem,
            aboutItem;

    static JButton loginButton,resetButton;

    static JLabel welcomeLabel,
                  nameLabel,passwordLabel;

    static JTextField nameField;//set login name textfield component

    static JPasswordField passwordField;//set login password textfield component

    public SystemFrame(){
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
        try{
           mainInitial();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mainInitial() throws Exception{

        Toolkit kit = Toolkit.getDefaultToolkit();
        screenSize = kit.getScreenSize();

        this.setTitle("档案管理系统");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setSize(500, 400);
        //this.setSize(screenSize.width/2,screenSize.height/2);
        this.setSize(500,400);
        this.setLocation(500,500);
        this.setResizable(false);
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        this.add(mainPanel);

        setMainMenu();
        setField();
        setLabel();
        setButton();
        setOtherPanel();

        this.setJMenuBar(mainMenu);
        mainPanel.add("North",welcomePanel);
        mainPanel.add("Center",fieldPanel);
        mainPanel.add("South",buttonPanel);

        this.setVisible(true);
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
                    JOptionPane.showMessageDialog(null, "Tips: server untapped！","scientific",JOptionPane.WARNING_MESSAGE);
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

    //fieldSetting func
    public void setField(){
        nameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        if(!passwordField.echoCharIsSet()){
            passwordField.setEchoChar('*');
        }
    }

    //labelSetting func
    public void setLabel(){
        nameLabel = new JLabel("name");
        passwordLabel = new JLabel("password");

        welcomeImage = new ImageIcon("loginBackground.jpg");
        welcomeLabel = new JLabel(welcomeImage);
        welcomeLabel.setBounds(0,0,welcomeImage.getIconWidth(),welcomeImage.getIconHeight());

    }

    //buttonSetting fun
    public void setButton(){
        loginButton = new JButton("login");
        loginButton.addActionListener(this);
        resetButton = new JButton("reset");
        resetButton.addActionListener(this);
    }


    //panelSetting func
    public void setOtherPanel(){
        welcomePanel = new JPanel();
        fieldPanel = new JPanel();
        namePanel = new JPanel();
        passwordPanel = new JPanel();
        buttonPanel = new JPanel();

        namePanel.add(nameLabel);
        namePanel.add(nameField);

        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);

        fieldPanel.add(namePanel);
        fieldPanel.add(passwordPanel);

        welcomePanel.setOpaque(false);
        welcomePanel.add(welcomeLabel);

        buttonPanel.add(loginButton);
        buttonPanel.add(resetButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == resetButton){
            nameField.setText("");
            passwordField.setText("");
        }
        if(e.getSource() == loginButton){
            loginName = nameField.getText();
            loginPassword = Arrays.toString(passwordField.getPassword());
            if(loginName.equals("")){
                JOptionPane.showMessageDialog(null, "账号不能为空", "错误", JOptionPane.ERROR_MESSAGE);
            }else{
                if(loginPassword.equals("")){
                    JOptionPane.showMessageDialog(null, "密码不能为空", "错误", JOptionPane.ERROR_MESSAGE);
                }else{
                    //loginUser = DataProcessing.search(loginName,loginPassword);
                    if(loginUser == null){
                        JOptionPane.showMessageDialog(null, "密码错误！", "错误", JOptionPane.ERROR_MESSAGE);
                    }else
                        loginUser.showMenu();
                }
            }
        }
    }
}

