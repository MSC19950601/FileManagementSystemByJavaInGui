package com.kururu.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by kururu on 2015/12/4.
 */
public class AdministratorFrame extends JFrame {

    public static JFrame AdminMainFrame;
    public static JLabel AdminMainPanelBackground;
    public static JPanel AdminMainPanel;
    public static JMenuBar AdminMainMenu;
    public static JMenu AdminFileMenu,AdminWorkMenu,AdminHelpMenu;
    public static JMenuItem AdminQuitItem,
            AdminServerItem,
            AdminAboutItem;

    public static ImageIcon AdminMainBackground,
            welcomeImage,
            nameLabelBackground, passwordLabelBackground,
            nameButtonBackground,resetButtonBackground;

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

        AdminMainFrame.setLocation(300, 300);
        AdminMainFrame.setResizable(false);
        AdminMainFrame.setVisible(true);
    }

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
}
