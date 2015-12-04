package com.kururu.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by kururu on 2015/12/4.
 */
public class OperatorFrame extends JFrame{
    public static JFrame OpeMainFrame;
    public static JLabel OpeMainPanelBackground;
    public static JPanel OpeMainPanel;
    public static JMenuBar OpeMainMenu;
    public static JMenu OpeFileMenu,OpeWorkMenu,OpeHelpMenu;
    public static JMenuItem OpeQuitItem,
            OpeServerItem,
            OpeAboutItem;

    public static ImageIcon OpeMainBackground,
            welcomeImage,
            nameLabelBackground, passwordLabelBackground,
            nameButtonBackground,resetButtonBackground;

    public OperatorFrame() {
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
        try{
            OperatorFrameMainInitial();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void OperatorFrameMainInitial(){
        OpeMainFrame = new JFrame("Operator shell");

        OpeMainBackground = new ImageIcon("Icon/loginBackground.jpg");
        OpeMainPanelBackground = new JLabel(OpeMainBackground);
        OpeMainPanelBackground.setBounds(0,0,OpeMainBackground.getIconWidth(),OpeMainBackground.getIconHeight());

        OpeMainPanel = (JPanel)OpeMainFrame.getContentPane();
        OpeMainPanel.setOpaque(false);

        OpeMainPanel.setLayout(null);
        OpeMainFrame.getLayeredPane().add(OpeMainPanelBackground,new Integer(Integer.MIN_VALUE));
        OpeMainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        OpeMainFrame.setSize(OpeMainBackground.getIconWidth(), OpeMainBackground.getIconHeight());

        setMainMenu();

        OpeMainFrame.setLocation(300, 300);
        OpeMainFrame.setResizable(false);
        OpeMainFrame.setVisible(true);
    }

    public void setMainMenu(){
        OpeMainMenu = new JMenuBar();
        setFileMenu();
        setWorkMenu();
        setHelpMenu();
        OpeMainMenu.add(OpeFileMenu);
        OpeMainMenu.add(OpeWorkMenu);
        OpeMainMenu.add(OpeHelpMenu);
        OpeMainFrame.setJMenuBar(OpeMainMenu);
    }

    public void setFileMenu(){
        OpeFileMenu = new JMenu("file");

        OpeQuitItem = new JMenuItem("quit");
        OpeFileMenu.add(OpeQuitItem);
        OpeQuitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == OpeQuitItem)
                    System.exit(0);
            }
        });
        OpeFileMenu.add(OpeQuitItem);
    }

    public void setWorkMenu(){
        OpeWorkMenu = new JMenu("work");

        OpeServerItem= new JMenuItem("server");
        OpeWorkMenu.add(OpeServerItem);
        OpeServerItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == OpeServerItem){
                    JOptionPane.showMessageDialog(null, "Tips: server untapped","scientific",JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        OpeWorkMenu.add(OpeServerItem);
    }

    public void setHelpMenu(){
        OpeHelpMenu = new JMenu("help");

        OpeAboutItem = new JMenuItem("about");
        OpeHelpMenu.add(OpeAboutItem);
        OpeAboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == OpeAboutItem){
                    JOptionPane.showMessageDialog(null, "made by kururu007 in whut.\n"+"Thank you for use!","about",JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
    }
}
