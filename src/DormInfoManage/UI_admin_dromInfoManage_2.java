package DormInfoManage;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Login.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

class HomePanel2 extends JPanel {
    ImageIcon icon;
    Image img;

    public HomePanel2() {
        // /img/HomeImg.jpg 是存放在你正在编写的项目的bin文件夹下的img文件夹下的一个图片
        icon = new ImageIcon(getClass().getResource("/035.jpg"));
        img = icon.getImage();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 下面这行是为了背景图片可以跟随窗口自行调整大小，可以自己设置成固定大小
        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}

public class UI_admin_dromInfoManage_2 extends JFrame {

    Connection conn;
    PreparedStatement ps;
    Statement st;
    ResultSet rs;
    String name;
    String str1, str2, str3, str4;
    JPanel contentPane;
    JTextField Text_input_LouguanID;
    JTextField Text_input_louguanName;
    JTextField Text_input_louguanAge;
    JTextField Text_input_louguanTel;
    JTextField Text_input_louguanPassword;
    JTextField Text_out_LouguanID;
    JTextField Text_out_louguanName;
    JTextField Text_out_louguanAge;
    JTextField Text_out_louguanSex;
    JTextField Text_input_FloorId;
    JTextField Text_input_DormNum;
    JTextField Text_input_jl_DormStandard;
    JTextField Text_input_louguanId;
    JTextField Text_out_FloorId;
    JTextField Text_out_FloorSex;
    JTextField Text_out_DormNum;
    JTextField Text_out_DormStandard;
    JTextField Text_out_louguanId;
    JTextField Text_input_DormID;
    JTextField Text_input_FloorID;
    JTextField Text_input_DormBossName;
    JTextField Text_out_DormId;
    JTextField Text_out_FloorID;
    JTextField Text_out_DormBossName;
    JComboBox jcb;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UI_admin_dromInfoManage_2 frame = new UI_admin_dromInfoManage_2();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });
    }

    public UI_admin_dromInfoManage_2(String str){
        name = str;
        init();
    }
    public UI_admin_dromInfoManage_2() {
        init();
    }
    void init(){
        conn = DataBaseManage.DBManage.CreatConnection();
        CardLayout ccc = new CardLayout();
        JPanel jp_Total = new JPanel();
        JPanel jp_Total_Second = new JPanel();
        JPanel jp_louguanInfoManage = new JPanel(ccc);
        JPanel jp_FloorInfoManage = new JPanel(ccc);
        JPanel jp_DormInfoManage = new JPanel(ccc);
        JPanel jp_Input_LouguanInfo = new HomePanel2();
        JPanel jp_Search_LouguanInfo = new HomePanel2();
        JPanel jp_Input_FloorInfo = new HomePanel2();
        JPanel jp_Search_FloorInfo = new HomePanel2();
        JPanel jp_Input_DormInfo = new HomePanel2();
        JPanel jp_Search_DormInfo = new HomePanel2();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 641);

        //region 菜单栏
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menu = new JMenu("楼管信息管理     ");
        menu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                ccc.show(contentPane, "name_341270404191905");
            }
        });
        menuBar.add(menu);
        JMenu menu_1 = new JMenu("宿舍楼信息管理     ");
        menu_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ccc.show(contentPane, "name_341272467812709");
            }
        });
        menuBar.add(menu_1);
        JMenu menu_2 = new JMenu("宿舍信息管理     ");
        menu_2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ccc.show(contentPane, "name_341274148139802");
            }
        });
        menuBar.add(menu_2);




        JMenu menu_4 = new JMenu("返回上一级");
        menu_4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                UI_admin frame = new UI_admin(name);
                frame.setVisible(true);
            }
        });
        menuBar.add(menu_4);
        //endregion

        contentPane = new JPanel(ccc);
        setTitle("宿舍信息管理                                                    当前登录用户为：" + name);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        setLocationRelativeTo(null); // 使窗口居中显示
        // contentPane.setLayout(new CardLayout(0, 0));

        // JPanel panel = new JPanel();
        contentPane.add(jp_Total, "name_341270404191905");
        jp_Total.setLayout(null);

        JButton button = new JButton("录入楼管信息");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ccc.show(jp_louguanInfoManage, "name_341450332403556");
            }
        });
        button.setBounds(25, 107, 134, 27);
        jp_Total.add(button);

        JButton btnNewButton = new JButton("查询楼管信息");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnNewButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ccc.show(jp_louguanInfoManage, "name_341450342718654");
            }
        });
        btnNewButton.setBounds(25, 198, 134, 27);
        jp_Total.add(btnNewButton);

        // JPanel panel_3 = new JPanel();
        jp_louguanInfoManage.setBounds(186, 13, 472, 532);
        jp_Total.add(jp_louguanInfoManage);
        // panel_3.setLayout(new CardLayout(0, 0));

        // JPanel panel_4 = new JPanel();
        jp_louguanInfoManage.add(jp_Input_LouguanInfo, "name_341450332403556");
        jp_Input_LouguanInfo.setLayout(null);

        JLabel jl_LouguanID = new JLabel("楼管编号：");
        jl_LouguanID.setBounds(85, 69, 80, 18);
        jp_Input_LouguanInfo.add(jl_LouguanID);

        JLabel jl_LouguanName = new JLabel(" 姓   名：");
        jl_LouguanName.setBounds(90, 136, 72, 18);
        jp_Input_LouguanInfo.add(jl_LouguanName);

        JLabel jl_louguanAge = new JLabel(" 年   龄：");
        jl_louguanAge.setBounds(90, 203, 72, 18);
        jp_Input_LouguanInfo.add(jl_louguanAge);

        JLabel jl_louguanSex = new JLabel(" 性   别：");
        jl_louguanSex.setBounds(90, 270, 72, 18);
        jp_Input_LouguanInfo.add(jl_louguanSex);

        JLabel jl_louguanTel = new JLabel(" 电   话：");
        jl_louguanTel.setBounds(90, 337, 72, 18);
        jp_Input_LouguanInfo.add(jl_louguanTel);

        JLabel jl_louguanPassword = new JLabel("登录密码：");
        jl_louguanPassword.setBounds(85, 404, 80, 18);
        jp_Input_LouguanInfo.add(jl_louguanPassword);

        Text_input_LouguanID = new JTextField();
        Text_input_LouguanID.setBounds(159, 69, 180, 24);
        jp_Input_LouguanInfo.add(Text_input_LouguanID);
        Text_input_LouguanID.setColumns(10);

        Text_input_louguanName = new JTextField();
        Text_input_louguanName.setBounds(159, 136, 180, 24);
        jp_Input_LouguanInfo.add(Text_input_louguanName);
        Text_input_louguanName.setColumns(10);

        Text_input_louguanAge = new JTextField();
        Text_input_louguanAge.setBounds(159, 203, 180, 24);
        jp_Input_LouguanInfo.add(Text_input_louguanAge);
        Text_input_louguanAge.setColumns(10);

        jcb = new JComboBox(); // 下拉列表
        // 下拉列表内容
        jcb.addItem("男");
        jcb.addItem("女");

        jcb.setBounds(159, 270, 180, 24);
        jp_Input_LouguanInfo.add(jcb);


        Text_input_louguanTel = new JTextField();
        Text_input_louguanTel.setBounds(159, 337, 180, 24);
        jp_Input_LouguanInfo.add(Text_input_louguanTel);
        Text_input_louguanTel.setColumns(10);

        Text_input_louguanPassword = new JTextField();
        Text_input_louguanPassword.setBounds(159, 404, 180, 24);
        jp_Input_LouguanInfo.add(Text_input_louguanPassword);
        Text_input_louguanPassword.setColumns(10);

        JButton button_3 = new JButton("确定");
        button_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // dispose();
                str1 = Text_input_LouguanID.getText();
                str2 = "select * from Caretaker where Cno = " + str1;
                // TODO Auto-generated method stub
                if (e.getActionCommand() == "确定") {
                    if (Text_input_LouguanID.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "请输入楼管编号！", "提示消息", JOptionPane.WARNING_MESSAGE);
                    } else if (!Text_input_LouguanID.getText().matches("[0-9]+")) {
                        JOptionPane.showMessageDialog(null, "输入的编号必须为数字！", "提示消息", JOptionPane.WARNING_MESSAGE);
                    } else if (Text_input_louguanName.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "请输入姓名！", "提示消息", JOptionPane.WARNING_MESSAGE);
                    } else if (Text_input_louguanAge.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "请输入年龄！", "提示消息", JOptionPane.WARNING_MESSAGE);
                    } else if (Text_input_louguanTel.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "请输入电话！", "提示消息", JOptionPane.WARNING_MESSAGE);
                    } else if (Text_input_louguanPassword.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "请输入密码！", "提示消息", JOptionPane.WARNING_MESSAGE);
                    } else {
                        try {
                            // 判断此用户是否存在
                            st = conn.createStatement();
                            if (st.executeQuery(str2).next()) {
                                JOptionPane.showMessageDialog(null, "此用户已存在！", "提示消息", JOptionPane.WARNING_MESSAGE);
                            } else {
                                ps = conn.prepareStatement("insert into Caretaker values (?,?,?,?,?,?)");
                                if (Text_input_LouguanID.getText().length() > 0) {
                                    ps.setString(1, Text_input_LouguanID.getText());
                                }
                                if (Text_input_louguanName.getText().length() > 0) {
                                    ps.setString(2, Text_input_louguanName.getText());
                                }
                                if (Text_input_louguanAge.getText().length() > 0) {
                                    ps.setString(3, Text_input_louguanAge.getText());
                                }
                                ps.setString(4, (String) jcb.getSelectedItem());

                                if (Text_input_louguanTel.getText().length() > 0) {
                                    ps.setString(5, Text_input_louguanTel.getText());
                                }
                                if (Text_input_louguanPassword.getText().length() > 0) {
                                    ps.setString(6, Text_input_louguanPassword.getText());
                                }
                                ps.executeUpdate();
                                JOptionPane.showMessageDialog(null, "录入成功！", "提示消息", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } catch (Exception e3) {
                            e3.printStackTrace();
                        }
                    }
                }
            }
        });
        button_3.setBounds(180, 472, 113, 27);
        jp_Input_LouguanInfo.add(button_3);

        jp_louguanInfoManage.add(jp_Search_LouguanInfo, "name_341450342718654");
        jp_Search_LouguanInfo.setLayout(null);

        JLabel jl_LouguanId = new JLabel("楼管编号：");
        jl_LouguanId.setBounds(85, 70, 80, 18);
        jp_Search_LouguanInfo.add(jl_LouguanId);

        JLabel lblNewLabel_7 = new JLabel("  姓  名：");
        lblNewLabel_7.setBounds(90, 163, 72, 18);
        jp_Search_LouguanInfo.add(lblNewLabel_7);

        JLabel lblNewLabel_8 = new JLabel("  年  龄：");
        lblNewLabel_8.setBounds(90, 256, 72, 18);
        jp_Search_LouguanInfo.add(lblNewLabel_8);

        JLabel lblNewLabel_9 = new JLabel("  性  别：");
        lblNewLabel_9.setBounds(90, 350, 72, 18);
        jp_Search_LouguanInfo.add(lblNewLabel_9);

        Text_out_LouguanID = new JTextField();
        Text_out_LouguanID.setBounds(159, 70, 180, 24);
        jp_Search_LouguanInfo.add(Text_out_LouguanID);
        Text_out_LouguanID.setColumns(10);

        Text_out_louguanName = new JTextField();
        Text_out_louguanName.setBounds(159, 163, 180, 24);
        jp_Search_LouguanInfo.add(Text_out_louguanName);
        Text_out_louguanName.setColumns(10);

        Text_out_louguanAge = new JTextField();
        Text_out_louguanAge.setBounds(159, 256, 180, 24);
        jp_Search_LouguanInfo.add(Text_out_louguanAge);
        Text_out_louguanAge.setColumns(10);

        Text_out_louguanSex = new JTextField();
        Text_out_louguanSex.setBounds(159, 350, 180, 24);
        jp_Search_LouguanInfo.add(Text_out_louguanSex);
        Text_out_louguanSex.setColumns(10);

        JButton button_5 = new JButton("确定");
        button_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // 查询视图，管理员看不到其他管理员的密码
                    ps = conn.prepareStatement(
                            "select * from V_Caretaker where Cno like ? and Cname like ? and Cage like ? and Csex like ?  ORDER BY Cno");
                    if (Text_out_LouguanID.getText().length() > 0) {
                        ps.setString(1, Text_out_LouguanID.getText() + "%");
                    } else {
                        ps.setString(1, "%");
                    }
                    if (Text_out_louguanName.getText().length() > 0) {
                        ps.setString(2, Text_out_louguanName.getText() + "%");
                    } else {
                        ps.setString(2, "%");
                    }
                    if (Text_out_louguanAge.getText().length() > 0) {
                        ps.setString(3, Text_out_louguanAge.getText() + "%");
                    } else {
                        ps.setString(3, "%");
                    }
                    if (Text_out_louguanSex.getText().length() > 0) {
                        ps.setString(4, Text_out_louguanSex.getText() + "%");
                    } else {
                        ps.setString(4, "%");
                    }
                    rs = ps.executeQuery();
                    Mytable_Care selectcareInfo_table = new Mytable_Care(rs);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        button_5.setBounds(180, 472, 113, 27);
        jp_Search_LouguanInfo.add(button_5);
//



        contentPane.add(jp_Total_Second, "name_341272467812709");
        jp_Total_Second.setLayout(null);

        JButton jb_input_Drominfo = new JButton("录入宿舍楼信息");
        jb_input_Drominfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        jb_input_Drominfo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ccc.show(jp_FloorInfoManage, "name_342117044636829");
            }
        });
        jb_input_Drominfo.setBounds(26, 106, 146, 27);
        jp_Total_Second.add(jb_input_Drominfo);

        JButton jb_query_Dorminfo = new JButton("查询宿舍楼信息");
        jb_query_Dorminfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        jb_query_Dorminfo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ccc.show(jp_FloorInfoManage, "name_342117058108366");
            }
        });
        jb_query_Dorminfo.setBounds(26, 197, 146, 27);
        jp_Total_Second.add(jb_query_Dorminfo);


        jp_FloorInfoManage.setBounds(188, 13, 470, 532);
        jp_Total_Second.add(jp_FloorInfoManage);
        jp_FloorInfoManage.add(jp_Input_FloorInfo, "name_342117044636829");
        jp_Input_FloorInfo.setLayout(null);

        JLabel jl_FloorId = new JLabel(" 楼  号：");
        jl_FloorId.setBounds(70, 78, 72, 18);
        jp_Input_FloorInfo.add(jl_FloorId);

        JLabel jl_FloorSex = new JLabel(" 楼  别：");
        jl_FloorSex.setBounds(70, 134, 72, 18);
        jp_Input_FloorInfo.add(jl_FloorSex);

        JLabel jl_DormNum = new JLabel("宿舍数量：");
        jl_DormNum.setBounds(70, 190, 80, 18);
        jp_Input_FloorInfo.add(jl_DormNum);

        JLabel jl_DormStandard = new JLabel("宿舍标准：");
        jl_DormStandard.setBounds(70, 246, 80, 18);
        jp_Input_FloorInfo.add(jl_DormStandard);

        JLabel jl_louguanID = new JLabel("楼管编号：");
        jl_louguanID.setBounds(70, 316, 80, 18);
        jp_Input_FloorInfo.add(jl_louguanID);



        Text_input_FloorId = new JTextField();
        Text_input_FloorId.setBounds(149, 78, 180, 24);
        jp_Input_FloorInfo.add(Text_input_FloorId);
        Text_input_FloorId.setColumns(10);

        JComboBox comboBox = new JComboBox();
        comboBox.addItem("男");
        comboBox.addItem("女");
        comboBox.setBounds(149, 131, 50, 24);
        jp_Input_FloorInfo.add(comboBox);

        Text_input_DormNum = new JTextField();
        Text_input_DormNum.setBounds(149, 187, 180, 24);
        jp_Input_FloorInfo.add(Text_input_DormNum);
        Text_input_DormNum.setColumns(10);

        Text_input_jl_DormStandard = new JTextField();
        Text_input_jl_DormStandard.setBounds(149, 243, 180, 24);
        jp_Input_FloorInfo.add(Text_input_jl_DormStandard);
        Text_input_jl_DormStandard.setColumns(10);

        Text_input_louguanId = new JTextField();
        Text_input_louguanId.setBounds(149, 313, 180, 24);
        jp_Input_FloorInfo.add(Text_input_louguanId);
        Text_input_louguanId.setColumns(10);


        JButton button_17 = new JButton("确定录入");
        button_17.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                str1 = Text_input_FloorId.getText();
                str2 = "select * from floor where Fno = " + str1;
                str3 = Text_input_louguanId.getText();
                str4 = "select * from caretaker where Cno = " + str3;
                if (e.getActionCommand() == "确定录入") {
                    if (Text_input_FloorId.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "请输入楼号！", "提示消息", JOptionPane.WARNING_MESSAGE);
                    } else if (Text_input_DormNum.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "请输入宿舍数量！", "提示消息", JOptionPane.WARNING_MESSAGE);
                    } else if (Text_input_jl_DormStandard.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "请输入宿舍标准！", "提示消息", JOptionPane.WARNING_MESSAGE);
                    } else if (Text_input_louguanId.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "楼管编号！", "提示消息", JOptionPane.WARNING_MESSAGE);
                    } else {
                        try {
                            st = conn.createStatement();
                            if (st.executeQuery(str2).next()) {
                                JOptionPane.showMessageDialog(null, "此楼号已存在！", "提示消息", JOptionPane.WARNING_MESSAGE);

                            } else if (!st.executeQuery(str4).next()) {
                                JOptionPane.showMessageDialog(null, "此楼管编号不存在！", "提示消息", JOptionPane.WARNING_MESSAGE);
                            } else {
                                ps = conn.prepareStatement("insert into floor values (?,?,?,?,?)");
                                ps.setString(1, Text_input_FloorId.getText());
                                ps.setString(2, (String) jcb.getSelectedItem()); // 设置为选择框里的内容
                                ps.setString(3, Text_input_DormNum.getText());
                                ps.setString(4, Text_input_jl_DormStandard.getText());
                                ps.setString(5, Text_input_louguanId.getText());

                                ps.executeUpdate();

                                System.out.println(4);
                                JOptionPane.showMessageDialog(null, "录入成功！", "提示消息", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } catch (Exception e3) {
                            e3.printStackTrace();
                        }
                    }
                }
            }
        });
        button_17.setBounds(180, 392, 113, 27);
        jp_Input_FloorInfo.add(button_17);

        jp_FloorInfoManage.add(jp_Search_FloorInfo, "name_342117058108366");
        jp_Search_FloorInfo.setLayout(null);

        JLabel jl_out_FloorId = new JLabel(" 楼  号：");
        jl_out_FloorId.setBounds(70, 78, 72, 18);
        jp_Search_FloorInfo.add(jl_out_FloorId);

        JLabel jl_out_FloorSex = new JLabel(" 楼  别：");
        jl_out_FloorSex.setBounds(70, 134, 72, 18);
        jp_Search_FloorInfo.add(jl_out_FloorSex);

        JLabel jl_out_DormNum = new JLabel("宿舍数量：");
        jl_out_DormNum.setBounds(70, 190, 72, 18);
        jp_Search_FloorInfo.add(jl_out_DormNum);

        JLabel jl_out_DormStandard = new JLabel("宿舍标准：");
        jl_out_DormStandard.setBounds(70, 246, 80, 18);
        jp_Search_FloorInfo.add(jl_out_DormStandard);

        JLabel jl_out_louguanId = new JLabel("楼管编号：");
        jl_out_louguanId.setBounds(70, 316, 80, 18);
        jp_Search_FloorInfo.add(jl_out_louguanId);



        Text_out_FloorId = new JTextField();
        Text_out_FloorId.setBounds(149, 78, 180, 24);
        jp_Search_FloorInfo.add(Text_out_FloorId);
        Text_out_FloorId.setColumns(10);

        Text_out_FloorSex = new JTextField();
        Text_out_FloorSex.setBounds(149, 131, 180, 24);
        jp_Search_FloorInfo.add(Text_out_FloorSex);
        Text_out_FloorSex.setColumns(10);

        Text_out_DormNum = new JTextField();
        Text_out_DormNum.setBounds(149, 187, 180, 24);
        jp_Search_FloorInfo.add(Text_out_DormNum);
        Text_out_DormNum.setColumns(10);

        Text_out_DormStandard = new JTextField();
        Text_out_DormStandard.setBounds(149, 243, 180, 24);
        jp_Search_FloorInfo.add(Text_out_DormStandard);
        Text_out_DormStandard.setColumns(10);

        Text_out_louguanId = new JTextField();
        Text_out_louguanId.setBounds(149, 313, 180, 24);
        jp_Search_FloorInfo.add(Text_out_louguanId);
        Text_out_louguanId.setColumns(10);



        JButton button_15 = new JButton("确定");
        button_15.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    ps = conn.prepareStatement("select * from Floor_Caretaker "
                            + "where Fno like ? and Fsex like ?  and Fnumber like ? and DS like ? and Cno like ?"
                    );
                    if (Text_out_FloorId.getText().length() > 0) {
                        ps.setString(1, Text_out_FloorId.getText() + "%");
                    } else {
                        ps.setString(1, "%");
                    }
                    if (Text_out_FloorSex.getText().length() > 0) {
                        ps.setString(2, Text_out_FloorSex.getText() + "%");
                    } else {
                        ps.setString(2, "%");
                    }
                    if (Text_out_DormNum.getText().length() > 0) {
                        ps.setString(3, Text_out_DormNum.getText() + "%");
                    } else {
                        ps.setString(3, "%");
                    }
                    if (Text_out_DormStandard.getText().length() > 0) {
                        ps.setString(4, Text_out_DormStandard.getText() + "%");
                    } else {
                        ps.setString(4, "%");
                    }
                    if (Text_out_louguanId.getText().length() > 0) {
                        ps.setString(5, Text_out_louguanId.getText() + "%");
                    } else {
                        ps.setString(5, "%");
                    }

                    rs = ps.executeQuery();
                    Mytable_Floor selectFloorInfo_table = new Mytable_Floor(rs);
                    // }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        button_15.setBounds(180, 392, 113, 27);
        jp_Search_FloorInfo.add(button_15);


        JPanel panel_2 = new JPanel();
        contentPane.add(panel_2, "name_341274148139802");
        panel_2.setLayout(null);

        JButton button_10 = new JButton("录入宿舍信息");
        button_10.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        button_10.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ccc.show(jp_DormInfoManage, "name_343748617794731");
            }
        });
        button_10.setBounds(26, 106, 134, 27);
        panel_2.add(button_10);

        JButton button_14 = new JButton("查询宿舍信息");
        button_14.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        button_14.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ccc.show(jp_DormInfoManage, "name_343751153817128");
            }
        });
        button_14.setBounds(26, 197, 134, 27);
        panel_2.add(button_14);


        jp_DormInfoManage.setBounds(188, 13, 470, 532);
        panel_2.add(jp_DormInfoManage);
        // panel_7.setLayout(new CardLayout(0, 0));

        // JPanel panel_12 = new JPanel();
        jp_DormInfoManage.add(jp_Input_DormInfo, "name_343748617794731");
        jp_Input_DormInfo.setLayout(null);

        JLabel jl_input_DormID = new JLabel("宿舍编号：");
        jl_input_DormID.setBounds(70, 101, 72, 18);
        jp_Input_DormInfo.add(jl_input_DormID);

        JLabel jl_input_FloorID = new JLabel(" 楼   号：");
        jl_input_FloorID.setBounds(70, 189, 72, 18);
        jp_Input_DormInfo.add(jl_input_FloorID);

        JLabel jl_input_DormBossName = new JLabel("舍长姓名：");
        jl_input_DormBossName.setBounds(70, 268, 72, 18);
        jp_Input_DormInfo.add(jl_input_DormBossName);



        Text_input_DormID = new JTextField();
        Text_input_DormID.setBounds(141, 98, 180, 24);
        jp_Input_DormInfo.add(Text_input_DormID);
        Text_input_DormID.setColumns(10);

        Text_input_FloorID = new JTextField();
        Text_input_FloorID.setBounds(141, 186, 180, 24);
        jp_Input_DormInfo.add(Text_input_FloorID);
        Text_input_FloorID.setColumns(10);

        Text_input_DormBossName = new JTextField();
        Text_input_DormBossName.setBounds(141, 265, 180, 24);
        jp_Input_DormInfo.add(Text_input_DormBossName);
        Text_input_DormBossName.setColumns(10);


        JButton button_20 = new JButton("确定");
        button_20.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                str1 = Text_input_DormID.getText();
                str2 = "select * from Dorm where Dno = " + str1;
                str3 = Text_input_FloorID.getText();
                str4 = "select * from Floor where Fno = " + str3;
                if (Text_input_DormID.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "请输入宿舍编号！", "提示消息", JOptionPane.WARNING_MESSAGE);
                } else if (Text_input_FloorID.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "请输入楼号！", "提示消息", JOptionPane.WARNING_MESSAGE);
                } else

                {try {
                    st = conn.createStatement();
                    if (st.executeQuery(str2).next()) {
                        JOptionPane.showMessageDialog(null, "此宿舍已存在！", "提示消息", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    if (!st.executeQuery(str4).next()) {
                        JOptionPane.showMessageDialog(null, "此宿舍楼不存在！", "提示消息", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    ps = conn.prepareStatement("insert into Dorm values (?,?,?)");
                    ps.setString(1, Text_input_DormID.getText());
                    ps.setString(2, Text_input_FloorID.getText());
                    ps.setString(3, Text_input_DormBossName.getText());

                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null, "录入成功！", "提示消息", JOptionPane.INFORMATION_MESSAGE);

                } catch (Exception e3) {
                    e3.printStackTrace();
                }
                }
            }
        });
        button_20.setBounds(180, 352, 113, 27);
        jp_Input_DormInfo.add(button_20);

        jp_DormInfoManage.add(jp_Search_DormInfo, "name_343751153817128");
        jp_Search_DormInfo.setLayout(null);

        JLabel jl_out_DormID = new JLabel("宿舍编号：");
        jl_out_DormID.setBounds(70, 101, 72, 18);
        jp_Search_DormInfo.add(jl_out_DormID);

        JLabel jl_out_FloorID = new JLabel(" 楼   号：");
        jl_out_FloorID.setBounds(70, 189, 72, 18);
        jp_Search_DormInfo.add(jl_out_FloorID);

        JLabel jl_out_DormBoss = new JLabel("舍长姓名：");
        jl_out_DormBoss.setBounds(70, 268, 72, 18);
        jp_Search_DormInfo.add(jl_out_DormBoss);



        Text_out_DormId = new JTextField();
        Text_out_DormId.setBounds(141, 98, 180, 24);
        jp_Search_DormInfo.add(Text_out_DormId);
        Text_out_DormId.setColumns(10);

        Text_out_FloorID = new JTextField();
        Text_out_FloorID.setBounds(141, 186, 180, 24);
        jp_Search_DormInfo.add(Text_out_FloorID);
        Text_out_FloorID.setColumns(10);

        Text_out_DormBossName = new JTextField();
        Text_out_DormBossName.setBounds(141, 265, 180, 24);
        jp_Search_DormInfo.add(Text_out_DormBossName);
        Text_out_DormBossName.setColumns(10);



        JButton button_22 = new JButton("确定");
        button_22.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    ps = conn.prepareStatement(
                            "select * from dorm where Dno like ? and Fno like ? and DHname like ? "
                    );
                    if (Text_out_DormId.getText().length() > 0) {
                        ps.setString(1, Text_out_DormId.getText() + "%");
                    } else {
                        ps.setString(1, "%");
                    }
                    if (Text_out_FloorID.getText().length() > 0) {
                        ps.setString(2, Text_out_FloorID.getText() + "%");
                    } else {
                        ps.setString(2, "%");
                    }
                    if (Text_out_DormBossName.getText().length() > 0) {
                        ps.setString(3, Text_out_DormBossName.getText() + "%");
                    } else {
                        ps.setString(3, "%");
                    }

                    rs = ps.executeQuery();
                    Mytable_Dorm selectDormInfo_table = new Mytable_Dorm(rs);
                    // }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        button_22.setBounds(180, 352, 113, 27);
        jp_Search_DormInfo.add(button_22);

    }

}
