package com.kururu.frame;

import com.kururu.database.MysqlDatabaseForDoc;
import com.kururu.database.MysqlDatabaseForUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * Created by kururu on 2015/12/4.
 */
public class BrowserFrame extends JFrame{
    public JFrame BrosMainFrame;
    public JLabel BrosMainPanelBackground;
    public JPanel BrosMainPanel;
    public JMenuBar BrosMainMenu;
    public JMenu
            BrosFileMenu,
            BrosWorkMenu,
            BrosHelpMenu;
    public JMenuItem
            BrosQuitItem,
            BrosServerItem,
            BrosAboutItem;

    public JButton
            downloadFileButton,
            showFileListButton;

    public JScrollPane listScrollPane;
    public JTable BroJTableForDoc;

    public ImageIcon BrosMainBackground;

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
        setTable();
        setButton();

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

    public void setTable(){
        Vector colHead = new Vector();
        Vector rows = new Vector();
        int count = 0;

        MysqlDatabaseForDoc.getAllDocForAdmin(colHead, rows, count);
        BroJTableForDoc = new JTable(rows,colHead);
        BroJTableForDoc.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        BroJTableForDoc.setRowHeight(BrosMainBackground.getIconHeight() / 15);
        BroJTableForDoc.setFont(new Font("Consolas", 0x0, 20));

        listScrollPane = new JScrollPane(BroJTableForDoc);
        listScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        listScrollPane.setBounds(
                BrosMainBackground.getIconWidth() / 3,
                BrosMainBackground.getIconHeight() / 10,
                3 * BrosMainBackground.getIconWidth() / 5,
                BrosMainBackground.getIconHeight() / 10 + 4 * BrosMainBackground.getIconHeight()/7);
        listScrollPane.setVisible(false);
        BrosMainFrame.add(listScrollPane);
    }

    public void setButton(){

        downloadFileButton = new JButton("downloadFile");
        downloadFileButton.setBounds(
                BrosMainBackground.getIconWidth()/15,
                BrosMainBackground.getIconHeight()/10 + BrosMainBackground.getIconHeight()/5,
                BrosMainBackground.getIconWidth()/5,
                BrosMainBackground.getIconHeight()/15);
        downloadFileButton.setFont(new Font("Consolas", 1, 18));

        showFileListButton = new JButton("showFileList");
        showFileListButton.setBounds(
                BrosMainBackground.getIconWidth()/15,
                BrosMainBackground.getIconHeight()/10 + 2*BrosMainBackground.getIconHeight()/5,
                BrosMainBackground.getIconWidth()/5,
                BrosMainBackground.getIconHeight()/15);
        showFileListButton.setFont(new Font("Consolas", 1, 18));
        showFileListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == showFileListButton){
                    setTable();
                    listScrollPane.setVisible(true);
                }
            }
        });
        
        BrosMainFrame.add(downloadFileButton);
        BrosMainFrame.add(showFileListButton);
    }
}

