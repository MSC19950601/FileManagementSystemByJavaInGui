package com.kururu.frame;

import com.kururu.netServer.Client;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;

/**
 * Created by kururu on 2015/12/4.
 */
public class BrowserFrame extends JFrame{
    private JFrame BrosMainFrame;
    private JLabel BrosMainPanelBackground;
    private JPanel BrosMainPanel;
    private JMenuBar BrosMainMenu;
    private JMenu
            BrosFileMenu,
            BrosWorkMenu,
            BrosHelpMenu;
    private JMenuItem
            BrosQuitItem,
            BrosServerItem,
            BrosAboutItem;
    private JButton
            downloadFileButton,
            showFileListButton;
    private JScrollPane listScrollPane;
    private JTable BroJTableForDoc;
    private ImageIcon BrosMainBackground;

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
        BrosMainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
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
                if(e.getSource() == BrosQuitItem) {
                    try {
                        Client.quit();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    System.exit(0);
                }
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
        Vector colHead;
        Vector rows;
        Vector [] res = Client.getDocTable();
        colHead = res[0];
        rows = res[1];
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
        downloadFileButton.setFont(new Font("Consolas", 0x1, 18));
        downloadFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String click = "download";
                if(listScrollPane.isVisible()) {
                    int selectedRow = BroJTableForDoc.getSelectedRow();
                    if (selectedRow == -1) {
                        JOptionPane.showMessageDialog(null,
                                "No SelectedCell!", "error",
                                JOptionPane.ERROR_MESSAGE);
                    }else {
                        int selectedColumn = BroJTableForDoc.getSelectedColumn();
                        String fileName = BroJTableForDoc.getValueAt(selectedRow,selectedColumn).toString();
                        Client.download(fileName,click);
                    }
                }else{
                    JOptionPane.showMessageDialog(null,
                            "No List!", "error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        showFileListButton = new JButton("refresh");
        showFileListButton.setBounds(
                BrosMainBackground.getIconWidth()/15,
                BrosMainBackground.getIconHeight()/10 + 2*BrosMainBackground.getIconHeight()/5,
                BrosMainBackground.getIconWidth()/5,
                BrosMainBackground.getIconHeight()/15);
        showFileListButton.setFont(new Font("Consolas", 0x1, 18));
        showFileListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == showFileListButton){
                    listScrollPane.setVisible(true);
                    setTable();

                }
            }
        });
        BrosMainFrame.add(downloadFileButton);
        BrosMainFrame.add(showFileListButton);
    }

}

