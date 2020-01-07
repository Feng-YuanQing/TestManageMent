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
import javax.swing.JTextField;

public class UI_RetrievePassword extends JFrame implements ActionListener {
    Box basebox, box_ManageId, box_Name, box_JButton;
    JButton jb_Find, jb_Return;
    JLabel jl_ManageId, jl_Name;
    JTextField jtf_ManageId, jtf_Name;
    PreparedStatement ps;
    Connection conn;
    ResultSet rs;
    String x1, x2;

    public UI_RetrievePassword() {

        conn = DBManage.CreatConnection();

        this.setBackground(new Color(176, 224, 230));
        jb_Find = new JButton("找回");
        jb_Find.addActionListener(this);
        jb_Return = new JButton("返回");
        jb_Return.addActionListener(this);

        jl_ManageId = new JLabel("请您输入用户id：");
        jl_Name = new JLabel("请输入真实姓名：");
        jtf_ManageId = new JTextField(10);
        jtf_Name = new JTextField(10);

        box_ManageId = Box.createHorizontalBox();
        box_ManageId.add(jl_ManageId);
        box_ManageId.add(jtf_ManageId);
        box_Name = Box.createHorizontalBox();
        box_Name.add(jl_Name);
        box_Name.add(jtf_Name);
        box_JButton = Box.createHorizontalBox();
        box_JButton.add(jb_Find);
        box_JButton.add(Box.createHorizontalStrut(20));
        box_JButton.add(jb_Return);

        basebox = Box.createVerticalBox();
        basebox.add(Box.createVerticalStrut(20));
        basebox.add(box_ManageId);
        basebox.add(Box.createVerticalStrut(20));
        basebox.add(box_Name);
        basebox.add(Box.createVerticalStrut(20));
        basebox.add(box_JButton);
        basebox.add(Box.createVerticalStrut(20));
        add(basebox);

        setTitle("找回密码");
        setVisible(true);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(320, 200);
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String str1 = jtf_ManageId.getText();
        String str2 = jtf_Name.getText();
        if (e.getActionCommand() == "找回") {

            try {
                ps = conn.prepareStatement("select * from caretaker where Cno = " + str1);
                rs = ps.executeQuery();
                if (rs.next()) {
                    ps = conn.prepareStatement("select Cname from caretaker where Cno = " + str1);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        x1 = rs.getString(1);
                        if (jtf_Name.getText().equals(x1)) {
                            ps = conn.prepareStatement("select Cpassword from caretaker where Cno = " + str1);
                            rs = ps.executeQuery();
                            while (rs.next()) {
                                x2 = "您的密码为" + rs.getString(1);
                            }
                            JOptionPane.showMessageDialog(null, x2, "提示消息", JOptionPane.INFORMATION_MESSAGE);
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "姓名错误！", "提示消息", JOptionPane.ERROR_MESSAGE);
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
