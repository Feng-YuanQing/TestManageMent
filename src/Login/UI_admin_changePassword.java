package Login;

import DataBaseManage.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class UI_admin_changePassword extends JFrame implements ActionListener {

    Box basebox, box_OldPassW, box_NewPassW, box_ReNewPassW, box_JButton;
    JButton jb_Define, jb_Return;
    JPasswordField jpf_OldPassW, jpf_NewPassW, jpf_ReNewPassW;
    JLabel jl_OldPassW, jl_NewPassW, jl_ReNewPassW;

    PreparedStatement ps;
    Connection conn;
    ResultSet rs;
    String manageId;
    public static void main(String[] args) {
        new UI_admin_changePassword("51");
    }
    public UI_admin_changePassword(String str) {
        manageId = str;
        try {
            conn = DBManage.CreatConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.setBackground(new Color(176, 224, 230));
        jb_Define = new JButton("确定");
        jb_Define.addActionListener(this);
        jb_Return = new JButton("返回");
        jb_Return.addActionListener(this);

        jl_OldPassW = new JLabel("请您输入旧密码：");
        jl_NewPassW = new JLabel("请您输入新密码：");
        jl_ReNewPassW = new JLabel("再次输入新密码：");

        jpf_OldPassW = new JPasswordField(10);
        jpf_NewPassW = new JPasswordField(10);
        jpf_ReNewPassW = new JPasswordField(10);

        box_OldPassW = Box.createHorizontalBox();
        box_OldPassW.add(jl_OldPassW);
        box_OldPassW.add(jpf_OldPassW);
        box_NewPassW = Box.createHorizontalBox();
        box_NewPassW.add(jl_NewPassW);
        box_NewPassW.add(jpf_NewPassW);
        box_ReNewPassW = Box.createHorizontalBox();
        box_ReNewPassW.add(jl_ReNewPassW);
        box_ReNewPassW.add(jpf_ReNewPassW);
        box_JButton = Box.createHorizontalBox();
        box_JButton.add(jb_Define);
        box_JButton.add(Box.createHorizontalStrut(30));
        box_JButton.add(jb_Return);

        basebox = Box.createVerticalBox();
        basebox.add(Box.createVerticalStrut(20));
        basebox.add(box_OldPassW);
        basebox.add(Box.createVerticalStrut(20));
        basebox.add(box_NewPassW);
        basebox.add(Box.createVerticalStrut(20));
        basebox.add(box_ReNewPassW);
        basebox.add(Box.createVerticalStrut(20));
        basebox.add(box_JButton);
        basebox.add(Box.createVerticalStrut(20));
        add(basebox);

        setTitle("修改密码");
        setVisible(true);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(360, 250);
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String str2 = "select Cpassword from caretaker where Cno = " + manageId;
        String str3;
        if (e.getActionCommand() == "确定") {
            try {
                if (jpf_OldPassW.getPassword().length <= 0) {
                    JOptionPane.showMessageDialog(null, "请输入原密码！", "提示消息", JOptionPane.ERROR_MESSAGE);
                } else if (jpf_NewPassW.getPassword().length <= 0) {
                    JOptionPane.showMessageDialog(null, "请输入新密码！", "提示消息", JOptionPane.ERROR_MESSAGE);
                } else if (jpf_ReNewPassW.getPassword().length <= 0) {
                    JOptionPane.showMessageDialog(null, "请再次输入新密码！", "提示消息", JOptionPane.ERROR_MESSAGE);
                } else {
                    ps = conn.prepareStatement(str2);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        str3 = rs.getString(1);
                        if (new String(jpf_OldPassW.getPassword()).equals(str3)
                                && new String(jpf_NewPassW.getPassword()).equals(new String(jpf_ReNewPassW.getPassword()))) {
                            ps = conn.prepareStatement("update caretaker set Cpassword = ? where Cno = ?");
                            ps.setString(1, new String(jpf_ReNewPassW.getPassword()));
                            ps.setString(2, manageId);
                            ps.executeUpdate();
                            JOptionPane.showMessageDialog(null, "修改成功！", "提示消息", JOptionPane.INFORMATION_MESSAGE);
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "输入有误，请重新输入！", "提示消息", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        if (e.getActionCommand() == "返回") {
            dispose();
        }
    }
}

