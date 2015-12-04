package com.kururu.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by kururu on 2015/12/4.
 */
public class BrowserFrame extends JFrame{
    public static JFrame BrosMainFrame;
    public static JLabel BrosMainPanelBackground;
    public static JPanel BrosMainPanel;
    public static JMenuBar BrosMainMenu;
    public static JMenu BrosFileMenu,BrosWorkMenu,BrosHelpMenu;
    public static JMenuItem BrosQuitItem,
            BrosServerItem,
            BrosAboutItem;

    public static ImageIcon BrosMainBackground,
            welcomeImage,
            nameLabelBackground, passwordLabelBackground,
            nameButtonBackground,resetButtonBackground;

    public BrowserFrame() {
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
        try{
            BrowserFrameMainInitial();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void BrowserFrameMainInitial(){
        BrosMainFrame = new JFrame("Browser shell");

        BrosMainBackground = new ImageIcon("Icon/loginBackground.jpg");
        BrosMainPanelBackground = new JLabel(BrosMainBackground);
        BrosMainPanelBackground.setBounds(0,0,BrosMainBackground.getIconWidth(),BrosMainBackground.getIconHeight());

        BrosMainPanel = (JPanel)BrosMainFrame.getContentPane();
        BrosMainPanel.setOpaque(false);

        BrosMainPanel.setLayout(null);
        BrosMainFrame.getLayeredPane().add(BrosMainPanelBackground,new Integer(Integer.MIN_VALUE));
        BrosMainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BrosMainFrame.setSize(BrosMainBackground.getIconWidth(), BrosMainBackground.getIconHeight());

        setMainMenu();

        BrosMainFrame.setLocation(300, 300);
        BrosMainFrame.setResizable(false);
        BrosMainFrame.setVisible(true);
    }

    public void setMainMenu(){
        BrosMainMenu = new JMenuBar();
        setFileMenu();
        setWorkMenu();
        setHelpMenu();
        BrosMainMenu.add(BrosFileMenu);
        BrosMainMenu.add(BrosWorkMenu);
        BrosMainMenu.add(BrosHelpMenu);
        BrosMainFrame.setJMenuBar(BrosMainMenu);
    }

    public void setFileMenu(){
        BrosFileMenu = new JMenu("file");

        BrosQuitItem = new JMenuItem("quit");
        BrosFileMenu.add(BrosQuitItem);
        BrosQuitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == BrosQuitItem)
                    System.exit(0);
            }
        });
        BrosFileMenu.add(BrosQuitItem);
    }

    public void setWorkMenu(){
        BrosWorkMenu = new JMenu("work");

        BrosServerItem= new JMenuItem("server");
        BrosWorkMenu.add(BrosServerItem);
        BrosServerItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == BrosServerItem){
                    JOptionPane.showMessageDialog(null, "Tips: server untapped","scientific",JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        BrosWorkMenu.add(BrosServerItem);
    }

    public void setHelpMenu(){
        BrosHelpMenu = new JMenu("help");

        BrosAboutItem = new JMenuItem("about");
        BrosHelpMenu.add(BrosAboutItem);
        BrosAboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == BrosAboutItem){
                    JOptionPane.showMessageDialog(null, "made by kururu007 in whut.\n"+"Thank you for use!","about",JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
    }
}
