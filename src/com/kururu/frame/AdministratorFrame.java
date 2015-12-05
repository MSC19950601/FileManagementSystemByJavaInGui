package com.kururu.frame;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by kururu on 2015/12/4.
 */
public class AdministratorFrame extends JFrame {

    public JFrame AdminMainFrame;
    public JLabel AdminMainPanelBackground;
    public JPanel AdminMainPanel;
    public JMenuBar AdminMainMenu;

    public JMenu
            AdminFileMenu,
            AdminWorkMenu,
            AdminHelpMenu;
    public JMenuItem
            AdminQuitItem,
            AdminServerItem,
            AdminAboutItem;

    public JButton
            changeUserInfoButton,
            delUserButton,
            addUserButton,
            listUserButton;

    private ImageIcon AdminMainBackground;

    public JScrollPane listScrollPane;

    public AdministratorFrame() {
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
        try{
            AdministratorFrameMainInitial();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void AdministratorFrameMainInitial(){
        AdminMainFrame = new JFrame("Administrator shell");

        AdminMainBackground = new ImageIcon("Icon/loginBackground.jpg");
        AdminMainPanelBackground = new JLabel(AdminMainBackground);
        AdminMainPanelBackground.setBounds(0,0,AdminMainBackground.getIconWidth(),AdminMainBackground.getIconHeight());

        AdminMainPanel = (JPanel)AdminMainFrame.getContentPane();
        AdminMainPanel.setOpaque(false);

        AdminMainPanel.setLayout(null);
        AdminMainFrame.getLayeredPane().add(AdminMainPanelBackground,new Integer(Integer.MIN_VALUE));
        AdminMainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        AdminMainFrame.setSize(AdminMainBackground.getIconWidth(), AdminMainBackground.getIconHeight());

        setMainMenu();
        setButton();
        setTable();

        AdminMainFrame.setLocation(300, 300);
        AdminMainFrame.setResizable(false);
        AdminMainFrame.setVisible(true);
    }
    //set menu
    public void setMainMenu(){
        AdminMainMenu = new JMenuBar();
        setFileMenu();
        setWorkMenu();
        setHelpMenu();
        AdminMainMenu.add(AdminFileMenu);
        AdminMainMenu.add(AdminWorkMenu);
        AdminMainMenu.add(AdminHelpMenu);
        AdminMainFrame.setJMenuBar(AdminMainMenu);
    }

    public void setFileMenu(){
        AdminFileMenu = new JMenu("file");

        AdminQuitItem = new JMenuItem("quit");
        AdminFileMenu.add(AdminQuitItem);
        AdminQuitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == AdminQuitItem)
                    System.exit(0);
            }
        });
        AdminFileMenu.add(AdminQuitItem);
    }

    public void setWorkMenu(){
        AdminWorkMenu = new JMenu("work");

        AdminServerItem= new JMenuItem("server");
        AdminWorkMenu.add(AdminServerItem);
        AdminServerItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == AdminServerItem){
                    JOptionPane.showMessageDialog(null, "Tips: server untapped","scientific",JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        AdminWorkMenu.add(AdminServerItem);
    }

    public void setHelpMenu(){
        AdminHelpMenu = new JMenu("help");

        AdminAboutItem = new JMenuItem("about");
        AdminHelpMenu.add(AdminAboutItem);
        AdminAboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == AdminAboutItem){
                    JOptionPane.showMessageDialog(null, "made by kururu007 in whut.\n"+"Thank you for use!","about",JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
    }

    public void setTable(){
        listScrollPane = new JScrollPane();
        listScrollPane.setBounds(
                AdminMainBackground.getIconWidth()/3,
                AdminMainBackground.getIconHeight()/10,
                3*AdminMainBackground.getIconWidth()/5,
                AdminMainBackground.getIconHeight()/10 + 4*AdminMainBackground.getIconHeight()/7);
        AdminMainFrame.add(listScrollPane);

    }

    public void setButton(){

        changeUserInfoButton = new JButton("changeUserInfo");
        //changeUserInfoButton.setUI(new BasicButtonUI());
        changeUserInfoButton.setBounds(
                AdminMainBackground.getIconWidth()/15,
                AdminMainBackground.getIconHeight()/10,
                AdminMainBackground.getIconWidth()/5,
                AdminMainBackground.getIconHeight()/15);
        //changeUserInfoButton.setContentAreaFilled(false);
        changeUserInfoButton.setFont(new Font("Consolas", 1, 18));
        //changeUserInfoButton.setMargin(new Insets(0, 0, 0, 0));

        delUserButton = new JButton("deleteUser");
        delUserButton.setBounds(
                AdminMainBackground.getIconWidth()/15,
                AdminMainBackground.getIconHeight()/10 + AdminMainBackground.getIconHeight()/5,
                AdminMainBackground.getIconWidth()/5,
                AdminMainBackground.getIconHeight()/15);
        delUserButton.setFont(new Font("Consolas", 1, 18));

        addUserButton = new JButton("addUser");
        addUserButton.setBounds(
                AdminMainBackground.getIconWidth()/15,
                AdminMainBackground.getIconHeight()/10 + 2*AdminMainBackground.getIconHeight()/5,
                AdminMainBackground.getIconWidth()/5,
                AdminMainBackground.getIconHeight()/15);
        addUserButton.setFont(new Font("Consolas", 1, 18));

        listUserButton = new JButton("listUser");
        listUserButton.setBounds(
                AdminMainBackground.getIconWidth()/15,
                AdminMainBackground.getIconHeight()/10 + 3*AdminMainBackground.getIconHeight()/5,
                AdminMainBackground.getIconWidth()/5,
                AdminMainBackground.getIconHeight()/15);
        listUserButton.setFont(new Font("Consolas", 1, 18));

        AdminMainFrame.add(changeUserInfoButton);
        AdminMainFrame.add(delUserButton);
        AdminMainFrame.add(addUserButton);
        AdminMainFrame.add(listUserButton);
    }

}
