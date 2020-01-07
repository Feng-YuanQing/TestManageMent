package Login;

import DataBaseManage.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class UI_Registered extends JFrame implements ActionListener {
    private Box baseBox, box_ManageId, box_Name, box_Age, box_Sex, box_Tel, box_PassW, box_JButton;
    private JButton jb_Define, jb_Cancel;
    private JTextField jtf_ManageId, jtf_Name, jtf_Age, jtf_Tel, jtf_PassW;
    private JComboBox jcb_Sex;
    private JLabel jl1_ManageId, jl2_Name, jl3_Age, jl4_Sex, jl5_Tel, jl6_PassW;

    private Connection conn;
    private PreparedStatement ps;
    private Statement st;

    private String str1;
    private String str2;

    public static void main(String[] args) {
        new UI_Registered();
    }

    public UI_Registered() {

        conn = DBManage.CreatConnection();

        jtf_ManageId = new JTextField(10);
        jtf_Name = new JTextField(10);
        jtf_Age = new JTextField(10);
        jcb_Sex = new JComboBox(); // 下拉列表
        // 下拉列表内容
        jcb_Sex.addItem("男");
        jcb_Sex.addItem("女");
        jtf_Tel = new JTextField(10);
        jtf_PassW = new JTextField(10);

        jl1_ManageId = new JLabel("楼管编号 ");
        jl2_Name = new JLabel("姓   名 ");
        jl3_Age = new JLabel("年    龄 ");
        jl4_Sex = new JLabel("性    别 ");
        jl5_Tel = new JLabel("电    话 ");
        jl6_PassW = new JLabel("登录密码 ");
        jb_Define = new JButton("确定");
        jb_Define.addActionListener(this);
        jb_Cancel = new JButton("取消");
        jb_Cancel.addActionListener(this);

        box_ManageId = Box.createHorizontalBox();
        box_Name = Box.createHorizontalBox();
        box_Age = Box.createHorizontalBox();
        box_Sex = Box.createHorizontalBox();
        box_Tel = Box.createHorizontalBox();
        box_PassW = Box.createHorizontalBox();
        box_JButton = Box.createHorizontalBox();

        box_ManageId.add(Box.createVerticalStrut(25));
        box_ManageId.add(jl1_ManageId);
        box_ManageId.add(jtf_ManageId);
        box_Name.add(jl2_Name);
        box_Name.add(jtf_Name);
        box_Age.add(jl3_Age);
        box_Age.add(jtf_Age);

        box_Sex.add(jl4_Sex);
        box_Sex.add(jcb_Sex);

        box_Tel.add(jl5_Tel);
        box_Tel.add(jtf_Tel);
        box_PassW.add(jl6_PassW);
        box_PassW.add(jtf_PassW);

        box_JButton.add(jb_Define);
        box_JButton.add(Box.createHorizontalStrut(20));
        box_JButton.add(jb_Cancel);

        baseBox = Box.createVerticalBox();
        baseBox.add(Box.createVerticalStrut(50));
        baseBox.add(box_ManageId);
        baseBox.add(Box.createVerticalStrut(20));
        baseBox.add(box_Name);
        baseBox.add(Box.createVerticalStrut(20));
        baseBox.add(box_Age);
        baseBox.add(Box.createVerticalStrut(20));
        baseBox.add(box_Sex);
        baseBox.add(Box.createVerticalStrut(20));
        baseBox.add(box_Tel);
        baseBox.add(Box.createVerticalStrut(20));
        baseBox.add(box_PassW);
        baseBox.add(Box.createVerticalStrut(40));
        baseBox.add(box_JButton);
        baseBox.add(Box.createVerticalStrut(20));
        add(baseBox);

        setVisible(true);
        setLayout(new FlowLayout());
        setTitle("管理员注册");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // 使窗口居中显示
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        str1 = jtf_ManageId.getText();
        str2 = "select * from Caretaker where Cno = " + str1;

        if (e.getActionCommand().equals("确定")) {
            if (jtf_ManageId.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "请输入楼管编号！", "提示消息", JOptionPane.WARNING_MESSAGE);
            } else if (!jtf_ManageId.getText().matches("[0-9]+")) {
                JOptionPane.showMessageDialog(null, "输入的编号必须为数字！", "提示消息", JOptionPane.WARNING_MESSAGE);
            } else if (jtf_Name.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "请输入姓名！", "提示消息", JOptionPane.WARNING_MESSAGE);
            } else if (jtf_Age.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "请输入年龄！", "提示消息", JOptionPane.WARNING_MESSAGE);
            } else if (jtf_Tel.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "请输入电话！", "提示消息", JOptionPane.WARNING_MESSAGE);
            } else if (jtf_PassW.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "请输入密码！", "提示消息", JOptionPane.WARNING_MESSAGE);
            } else {
                try {
                    // 判断此用户是否存在
                    st = conn.createStatement();
                    if (st.executeQuery(str2).next()) {
                        JOptionPane.showMessageDialog(null, "此用户已存在！", "提示消息", JOptionPane.WARNING_MESSAGE);
                    } else {
                        ps = conn.prepareStatement("insert into Caretaker values (?,?,?,?,?,?)");
                        if (jtf_ManageId.getText().length() > 0) {
                            ps.setString(1, jtf_ManageId.getText());
                        }
                        if (jtf_Name.getText().length() > 0) {
                            ps.setString(2, jtf_Name.getText());
                        }
                        if (jtf_Age.getText().length() > 0) {
                            ps.setString(3, jtf_Age.getText());
                        }
                        ps.setString(4, (String) jcb_Sex.getSelectedItem());

                        if (jtf_Tel.getText().length() > 0) {
                            ps.setString(5, jtf_Tel.getText());
                        }
                        if (jtf_PassW.getText().length() > 0) {
                            ps.setString(6, jtf_PassW.getText());
                        }
                        ps.executeUpdate();
                        JOptionPane.showMessageDialog(null, "注册成功！", "提示消息", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    }
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
        }
        if (e.getActionCommand() == "取消") {
            dispose();
        }
    }

}

