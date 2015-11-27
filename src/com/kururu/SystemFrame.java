package com.kururu;

import com.sun.java.accessibility.util.SwingEventMonitor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Console;

/**
 * Created by kururu on 2015/11/25.
 */
public class SystemFrame extends JFrame implements ActionListener{

    static JPanel namePanel,passwordPanel,buttonPanel;

    static JMenuBar mainMenu;

    static JMenu fileMenu,workMenu,helpMenu;

    static JMenuItem
            quitItem,
            seryerItem,
            aboutItem;

    static JButton loginButton,quitButton;

    static JLabel nameLabel,passwordLabel;

    static JTextField nameField;//set login name textfield component

    static JPasswordField passwordField;//set login password textfield component

    public SystemFrame(String loginName,String loginPaeeword){
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
        try{
           mainInitial();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mainInitial(String loginName,String loginPaeeword) throws Exception{

        //mainPanel = (JPanel)this.getContentPane();
        //mainPanel.setLayout(null);

        this.setTitle("档案管理系统");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 400);
        this.setResizable(false);
        //this.setLayout(new GridLayout(3,1));
        this.setLayout(new GridLayout(4,1));

        setMainMenu();
        setField();
        setLabel();
        setButton();
        setPanel();

        this.add(mainMenu);
        this.add(namePanel);
        this.add(passwordPanel);
        this.add(buttonPanel);

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
        fileMenu.setFont(new java.awt.Font("Arial",0,15));
        fileMenu.setForeground(Color.black);

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
        workMenu.setFont(new java.awt.Font("Console",0,15));
        workMenu.setForeground(Color.black);

        seryerItem = new JMenuItem("server");
        workMenu.add(seryerItem);
        seryerItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == seryerItem){
                    JOptionPane.showMessageDialog(null, "Tips: server untapped！","scientific",JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        workMenu.add(seryerItem);
}

    public void setHelpMenu(){
        helpMenu = new JMenu("help");
        helpMenu.setFont(new java.awt.Font("Dialog",0,15));
        helpMenu.setForeground(Color.black);

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
        nameField = new JTextField(15);
        passwordField = new JPasswordField(15);
    }

    //labelSetting func
    public void setLabel(){
        nameLabel = new JLabel("name");
        passwordLabel = new JLabel("password");
    }

    //buttonSetting fun
    public void setButton(){
        loginButton = new JButton("login");
        loginButton.addActionListener(this);
        quitButton = new JButton("quits");
        quitButton.addActionListener(this);
    }

    //panelSetting func
    public void setPanel(){
        namePanel = new JPanel();
        passwordPanel = new JPanel();
        buttonPanel = new JPanel();

        namePanel.add(nameLabel);
        namePanel.add(nameField);

        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);

        buttonPanel.add(loginButton);
        buttonPanel.add(quitButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == quitButton){
            System.exit(0);
        }
    }
}

