package AccomInfoManage;

import DataBaseManage.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Mytable_accom extends JFrame implements ActionListener {

    JTable table;
    Object a[][];
    Object[] name = { "学号", "姓名", "专业", "楼号" , "宿舍号", "床号", "舍长姓名" };
    JButton jb1, jb2;
    JPanel jp;

    static Connection conn;
    java.sql.PreparedStatement ps;

    static String x, x1, x2, x3, x4, x5, x6, x7,x11;

    public Mytable_accom(ResultSet rs) throws Exception {
        // TODO Auto-generated constructor stub
        //获取结果集rs的行数，从而构建“灵活”的表！
        int count = 0;
        rs.last();
        count = rs.getRow();
        rs.beforeFirst();
        conn = DBManage.CreatConnection();

        a = new Object[count][7];
        int i = 0;
        while (rs.next()) {
            String x1 = rs.getString("Sno");
            String x2 = rs.getString("Sname");
            String x3 = rs.getString("Smajor");
            String x4 = rs.getString("Fno");
            String x5 = rs.getString("Dno");
            String x6 = rs.getString("Bno");
            String x7 = rs.getString("DHname");
            a[i][0] = x1;
            a[i][1] = x2;
            a[i][2] = x3;
            a[i][3] = x4;
            a[i][4] = x5;
            a[i][5] = x6;
            a[i][6] = x7;
            i++;
        }

        jb2 = new JButton("返回");
        jb2.setFont(new Font("黑体", Font.PLAIN, 18));
        jb2.addActionListener(this);

        jp = new JPanel();
        jp.setBackground(new Color(176, 224, 230));
        jp.add(jb2);
        table = new JTable(a, name);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(jp, BorderLayout.SOUTH);
        setSize(960, 540);
        setVisible(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("学生信息查询结果");
        validate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand() == "返回") {
            dispose();
        }
    }

}

