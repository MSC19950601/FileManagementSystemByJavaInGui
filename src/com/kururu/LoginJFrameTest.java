package com.kururu;

/**
 * Created by kururu on 2015/11/28.
 */
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginJFrameTest {
    private JFrame jframe;
    private JLabel jlabel, jlabel1;
    private GridBagLayout gridbag;
    private GridBagConstraints constraints;
    private JTextField jtfield1;
    private JPasswordField jpfield1;
    private JButton jbutton1, jbutton2, jbutton3;
    private JPanel jpanel;

    public LoginJFrameTest() {
        jframe = new JFrame();
        jlabel = new JLabel();
        jlabel1 = new JLabel();
        jtfield1 = new JTextField();
        jpfield1 = new JPasswordField();
        gridbag = new GridBagLayout();
        jbutton1 = new JButton();
        jbutton2 = new JButton();
        jbutton3 = new JButton();

        init();
    }

    /**
     * init()初始化并显示界面
     */

    private void init() {

        jframe.setTitle("全屏幕测试");

        jpanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon img = new ImageIcon("Icon/loginBackground.jpg");
                img.paintIcon(this, g, 0, 0);
            }
        };

        jlabel.setText("用户名：");
        jlabel1.setText("密    码：");
        jbutton1.setText("登    录");
        jbutton2.setText("退    出");
        jbutton3.setText("更改密码");

        //设置JFrame为全屏
        jframe.setUndecorated(true);
        jframe.getGraphicsConfiguration().getDevice().setFullScreenWindow(jframe);

        //设置JPanel为透明，且使用GridBagLayout布局管理器
        jpanel.setOpaque(true);
        jpanel.setLayout(gridbag);

        //用户名文本框显示
        constraints = getGridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(10, 0, 10, 0), 0, 0);

        gridbag.setConstraints(jlabel, constraints);
        jpanel.add(jlabel);

        //用户名输入框显示
        constraints = getGridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(10, 0, 10, 0), 100, 0);

        gridbag.setConstraints(jtfield1, constraints);
        jpanel.add(jtfield1);

        //密码文本框显示
        constraints = getGridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(10, 0, 10, 0), 0, 0);
        gridbag.setConstraints(jlabel1, constraints);
        jpanel.add(jlabel1);

        //密码输入框显示
        constraints = getGridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(10, 0, 10, 0), 100, 0);

        gridbag.setConstraints(jpfield1, constraints);
        jpanel.add(jpfield1);

        //更改密码按钮显示
        constraints = getGridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(10, 0, 10, 0), 0, 0);

        gridbag.setConstraints(jbutton3, constraints);
        jpanel.add(jbutton3);

        //登录按钮显示
        constraints = getGridBagConstraints(1, 2, 1, 1, 0, 0, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(10, 0, 10, 0), 0, 0);

        gridbag.setConstraints(jbutton1, constraints);
        jpanel.add(jbutton1);

        //退出按钮显示
        constraints = getGridBagConstraints(2, 2, 1, 1, 0, 0, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(10, 0, 10, 0), 0, 0);

        gridbag.setConstraints(jbutton2, constraints);
        jpanel.add(jbutton2);

        jframe.add(jpanel);
    }

    private static GridBagConstraints getGridBagConstraints(int gridx, int gridy, int gridwidth,
                                                            int gridheight, double weightx, double weighty, int anchor, int fill, Insets insets,
                                                            int ipadx, int ipady) {

        return new GridBagConstraints(gridx, gridy, gridwidth, gridheight, weightx, weighty,
                anchor, fill, insets, ipadx, ipady);
    }

    public void showMe() {
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setVisible(true);
    }
}
