package com.kururu.frame;

import com.kururu.basement.DataProcessing;
import com.kururu.database.MysqlDatabaseForUser;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;

/**
 * Created by kururu on 2015/12/4.
 */
public class AdministratorFrame extends JFrame {

    /*final public static String connectionAddress= "jdbc:mysql://127.0.0.1:3306/baseforsystemcharger";
    final public static String connectionName = "root";
    final public static String connectionPassword = "mo123456";
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private ResultSetMetaData rsmd;*/

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
    public TableModel AdminTableModel;
    public JTable AdminJTableForUser;
    public JTableHeader AdminJTableForUserHeader;

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
        //setTable();

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

        Vector colHead = new Vector();
        Vector rows = new Vector();
        int count = 0;

        MysqlDatabaseForUser.getAllUserForAdmin(colHead,rows,count);
        AdminJTableForUser = new JTable(rows,colHead);
        AdminJTableForUser.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        AdminJTableForUser.setRowHeight(AdminMainBackground.getIconHeight()/15);
        AdminJTableForUser.setFont(new Font("Consolas", 0x0, 20));

        try{
            for(int i = 1; i < count ; i++){

                TableColumn column = AdminJTableForUser.getColumnModel().getColumn(i);
                column.setWidth(AdminMainBackground.getIconWidth() / 5);

                DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
                renderer.setHorizontalAlignment(SwingConstants.CENTER);
                AdminJTableForUser.getColumn(i).setCellRenderer(renderer);
                //column.setPreferredWidth(AdminMainBackground.getIconWidth()/5);
            }
        }catch (Exception e){
            e.printStackTrace();
        }


        listScrollPane = new JScrollPane(AdminJTableForUser);
        listScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        listScrollPane.setBounds(
                AdminMainBackground.getIconWidth()/3,
                AdminMainBackground.getIconHeight()/10,
                3*AdminMainBackground.getIconWidth()/5,
                AdminMainBackground.getIconHeight()/10 + 4*AdminMainBackground.getIconHeight()/7);
        AdminMainFrame.add(listScrollPane);

        /*AdminTableModel = new DefaultTableModel();

        AdminJTableForUser = new JTable(AdminTableModel);*/

        //listScrollPane.setViewportView(AdminJTableForUser);

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
        addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddUserFrame();
                AdminMainFrame.dispose();
            }
        });

        listUserButton = new JButton("listUser");
        listUserButton.setBounds(
                AdminMainBackground.getIconWidth()/15,
                AdminMainBackground.getIconHeight()/10 + 3*AdminMainBackground.getIconHeight()/5,
                AdminMainBackground.getIconWidth()/5,
                AdminMainBackground.getIconHeight()/15);
        listUserButton.setFont(new Font("Consolas", 1, 18));
        listUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == listUserButton){
                    setTable();
                }
            }
        });

        AdminMainFrame.add(changeUserInfoButton);
        AdminMainFrame.add(delUserButton);
        AdminMainFrame.add(addUserButton);
        AdminMainFrame.add(listUserButton);
    }

}

class AddUserFrame extends JDialog{

    public JDialog mainDialogOfAddUser;
    public JPanel mainPanelOfAddUser;
    public ImageIcon mainBackgroundOfAddUser;
    public JLabel
            mainPanelOfAddUserBackground,
            nameLabelOfAddUser,
            passwordLabelOfAddUser,
            roleLabelOfAddUser;

    public JButton addButton,cancelButton;

    public JTextField nameFieldOfAddUser,roleFieldOfAddUser;
    public JPasswordField passwordFieldOfAddUser;

    public JCheckBox adminCheck,opeCheck,broCheck;


    public String nameOfAddUser,passwordOfAddUser,roleOfAdduser;


    public AddUserFrame(){
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
        try{
            mainInitialOfAddUser();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mainInitialOfAddUser(){
        mainDialogOfAddUser = new JDialog();
        mainDialogOfAddUser.setTitle("AddUser Shell");

        mainBackgroundOfAddUser = new ImageIcon("Icon/dialogBackground.jpg");
        mainPanelOfAddUserBackground = new JLabel(mainBackgroundOfAddUser);
        mainPanelOfAddUserBackground.setBounds(
                0,
                0,
                mainBackgroundOfAddUser.getIconWidth(),
                mainBackgroundOfAddUser.getIconHeight());

        mainPanelOfAddUser = (JPanel)mainDialogOfAddUser.getContentPane();
        mainPanelOfAddUser.setOpaque(false);
        mainPanelOfAddUser.setLayout(null);
        mainDialogOfAddUser.getLayeredPane().add(mainPanelOfAddUserBackground,new Integer(Integer.MIN_VALUE));
        mainDialogOfAddUser.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);//
        mainDialogOfAddUser.setSize(
                mainBackgroundOfAddUser.getIconWidth(),
                mainBackgroundOfAddUser.getIconHeight());
        mainDialogOfAddUser.setSize(
                mainBackgroundOfAddUser.getIconWidth(),
                mainBackgroundOfAddUser.getIconHeight());


        setFieldOfAddUser();
        setLabelOfAddUser();
        setButtonOfAddUser();
        setCheckBox();


        mainDialogOfAddUser.setResizable(false);
        mainDialogOfAddUser.setVisible(true);


    }

    private void setButtonOfAddUser() {
        addButton = new JButton("ADD");
        addButton.setBounds(
                mainBackgroundOfAddUser.getIconWidth() / 5,
                5 * mainBackgroundOfAddUser.getIconHeight() / 7,
                mainBackgroundOfAddUser.getIconWidth() / 5,
                mainBackgroundOfAddUser.getIconHeight() / 10
        );
        addButton.setFont(new Font("Consolas", 1, 18));
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameOfAddUser = nameFieldOfAddUser.getText();
                passwordOfAddUser = new String(passwordFieldOfAddUser.getPassword());
                //roleOfAdduser = roleFieldOfAddUser.getText();

                if(nameOfAddUser.equals("")){
                    JOptionPane.showMessageDialog(
                            null,
                            "name can't be empty", "error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    nameFieldOfAddUser.setText("");
                    passwordFieldOfAddUser.setText("");
                    //roleFieldOfAddUser.setText("");
                }else if(passwordOfAddUser.equals("")){
                    JOptionPane.showMessageDialog(
                            null,
                            "password can't be empty", "error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    nameFieldOfAddUser.setText("");
                    passwordFieldOfAddUser.setText("");
                    //roleFieldOfAddUser.setText("");
                }else if(roleOfAdduser.equals("")) {
                    JOptionPane.showMessageDialog(
                            null,
                            "role can't be empty or a wrong role", "error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    nameFieldOfAddUser.setText("");
                    passwordFieldOfAddUser.setText("");
                    //roleFieldOfAddUser.setText("");
                }

                try{
                    //DataProcessing.insertUser(nameOfAddUser,passwordOfAddUser,roleOfAdduser);
                    MysqlDatabaseForUser.insertUser(nameOfAddUser,passwordOfAddUser,roleOfAdduser);

                }catch (Exception e1){
                    e1.printStackTrace();
                }finally {
                    JOptionPane.showMessageDialog(null,
                            "Add a " + roleOfAdduser + "successfully!",
                            "ADD successfully!",
                            JOptionPane.PLAIN_MESSAGE);
                    mainDialogOfAddUser.dispose();
                    new AdministratorFrame();
                }
            }
        });

        cancelButton = new JButton("CANCEL");
        cancelButton.setBounds(
                3*mainBackgroundOfAddUser.getIconWidth() / 5,
                5*mainBackgroundOfAddUser.getIconHeight() / 7,
                mainBackgroundOfAddUser.getIconWidth() / 5,
                mainBackgroundOfAddUser.getIconHeight() / 10
        );
        cancelButton.setFont(new Font("Consolas", 1, 18));
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainDialogOfAddUser.dispose();
                new AdministratorFrame();
            }
        });

        mainDialogOfAddUser.add(addButton);
        mainDialogOfAddUser.add(cancelButton);
    }

    private void setLabelOfAddUser() {
        nameLabelOfAddUser = new JLabel("name");
        passwordLabelOfAddUser = new JLabel("password");
        //roleLabelOfAddUser = new JLabel("role");

        nameLabelOfAddUser.setBounds(
                mainBackgroundOfAddUser.getIconWidth() / 3,
                mainBackgroundOfAddUser.getIconHeight() / 5 - 30,
                mainBackgroundOfAddUser.getIconWidth() / 5,
                mainBackgroundOfAddUser.getIconHeight() / 11
        );
        nameLabelOfAddUser.setFont(new Font("Consolas", 0x1, 25));

        passwordLabelOfAddUser.setBounds(
                mainBackgroundOfAddUser.getIconWidth() / 3 - 30,
                2*mainBackgroundOfAddUser.getIconHeight() / 5 -30,
                mainBackgroundOfAddUser.getIconWidth() / 5,
                mainBackgroundOfAddUser.getIconHeight() / 11
        );
        passwordLabelOfAddUser.setFont(new Font("Consolas", 0x1, 25));

        /*roleLabelOfAddUser.setBounds(
                mainBackgroundOfAddUser.getIconWidth() / 6 - 30,
                3*mainBackgroundOfAddUser.getIconHeight() / 5 -30,
                mainBackgroundOfAddUser.getIconWidth() / 5,
                mainBackgroundOfAddUser.getIconHeight() / 11
        );
        roleLabelOfAddUser.setFont(new Font("Consolas", 0x1, 25));*/

        mainDialogOfAddUser.add(nameLabelOfAddUser);
        mainDialogOfAddUser.add(passwordLabelOfAddUser);
        //mainDialogOfAddUser.add(roleLabelOfAddUser);


    }

    private void setFieldOfAddUser() {
        nameFieldOfAddUser = new JTextField(20);
        passwordFieldOfAddUser = new JPasswordField(20);
        //roleFieldOfAddUser = new JTextField(20);
        nameFieldOfAddUser.setFont(new Font("Consolas", 0x1, 20));
        passwordFieldOfAddUser.setFont(new Font("Consolas", 0x1, 20));
        //roleFieldOfAddUser.setFont(new Font("Consolas", 0x1, 20));
        passwordFieldOfAddUser.setEchoChar('*');
        nameFieldOfAddUser.setBounds(
                mainBackgroundOfAddUser.getIconWidth() / 2,
                mainBackgroundOfAddUser.getIconHeight() / 5 -30,
                mainBackgroundOfAddUser.getIconWidth() / 5,
                mainBackgroundOfAddUser.getIconHeight() / 11)
        ;
        passwordFieldOfAddUser.setBounds(
                mainBackgroundOfAddUser.getIconWidth() / 2,
                2 * mainBackgroundOfAddUser.getIconHeight() / 5 -30,
                mainBackgroundOfAddUser.getIconWidth() / 5,
                mainBackgroundOfAddUser.getIconHeight() / 11
        );
        /*roleFieldOfAddUser.setBounds(
                mainBackgroundOfAddUser.getIconWidth() / 2,
                3*mainBackgroundOfAddUser.getIconHeight() / 5 -30,
                mainBackgroundOfAddUser.getIconWidth() / 5,
                mainBackgroundOfAddUser.getIconHeight() / 11
        );*/

        mainDialogOfAddUser.add(nameFieldOfAddUser);
        mainDialogOfAddUser.add(passwordFieldOfAddUser);
        //mainDialogOfAddUser.add(roleFieldOfAddUser);
    }

    public void setCheckBox(){

        adminCheck = new JCheckBox("administrator",false);
        opeCheck = new JCheckBox("operation",false);
        broCheck = new JCheckBox("browser",false);

        adminCheck.setBounds(
                mainBackgroundOfAddUser.getIconWidth() / 10,
                3*mainBackgroundOfAddUser.getIconHeight() / 5 -30,
                mainBackgroundOfAddUser.getIconWidth() /5 + 50,
                mainBackgroundOfAddUser.getIconHeight() / 11
        );
        adminCheck.setContentAreaFilled(false);
        adminCheck.setFont(new Font("Consolas", 0x1, 18));
        adminCheck.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    roleOfAdduser = "administrator";
                }
            }
        });

        opeCheck.setBounds(
                mainBackgroundOfAddUser.getIconWidth() / 10 + 175,
                3*mainBackgroundOfAddUser.getIconHeight() / 5 -30,
                mainBackgroundOfAddUser.getIconWidth() /5 + 25,
                mainBackgroundOfAddUser.getIconHeight() / 11
        );
        opeCheck.setContentAreaFilled(false);
        opeCheck.setFont(new Font("Consolas", 0x1, 18));
        opeCheck.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    roleOfAdduser = "operator";
                }
            }
        });

        broCheck.setBounds(
                mainBackgroundOfAddUser.getIconWidth() / 10 + 320,
                3*mainBackgroundOfAddUser.getIconHeight() / 5 -30,
                mainBackgroundOfAddUser.getIconWidth() /5,
                mainBackgroundOfAddUser.getIconHeight() / 11
        );
        broCheck.setContentAreaFilled(false);
        broCheck.setFont(new Font("Consolas", 0x1, 18));
        broCheck.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                roleOfAdduser = "browser";
            }
        });

        mainDialogOfAddUser.add(adminCheck);
        mainDialogOfAddUser.add(opeCheck);
        mainDialogOfAddUser.add(broCheck);

    }

}

class DelUserFrame extends JDialog{

    public static JDialog mainDialogOfDelUser;
    public static JPanel mainPanelOfDelUser;
    public static ImageIcon mainBackgroundOfDelUser;

    public DelUserFrame(){
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
        try{
            mainInitialOfDelUser();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mainInitialOfDelUser(){

    }
}

class ChangeUserInfoFrame extends JDialog{

    public static JDialog mainDialogOfChangeUserInfo;
    public static JPanel mainPanelOfChangeUserInfor;
    public static ImageIcon mainBackgroundOfChangeUserInfo;

    public ChangeUserInfoFrame(){
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
        try{
            mainInitialOfChangeUserInfo();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mainInitialOfChangeUserInfo(){

    }
}