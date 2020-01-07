package DailyManage;

import DataBaseManage.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Mytable_Violations extends JFrame implements ActionListener {

    JTable table;
    Object a[][];
    Object[] name = { "学号", "姓名", "楼管编号", "楼管姓名", "违纪时间", "违纪内容", "处理方式", "备注" };
    JButton jb_Modify, jb_Return;
    JPanel jPanel;
    Connection conn ;
    java.sql.PreparedStatement ps;
    static String x,x1,x2,x3,x4,x5,x6,x7,x8;


    public Mytable_Violations (ResultSet rs) throws Exception {
        int count = 0;
        rs.last();
        count = rs.getRow();
        rs.beforeFirst();
        conn = DBManage.CreatConnection();

        a = new Object[count][8];
        int i = 0;
        while (rs.next()) {
            String Sno = rs.getString("Sno");
            String Sname = rs.getString("Sname");
            String Cno = rs.getString("Cno");
            String Cname = rs.getString("Cname");
            String Mdate = rs.getString("Mdate");
            String Mmatter = rs.getString("Mmatter");
            String Mway = rs.getString("Mway");
            String Mnote = rs.getString("Mnote");

            a[i][0] = Sno;
            a[i][1] = Sname;
            a[i][2] = Cno;
            a[i][3] = Cname;
            a[i][4] = Mdate;
            a[i][5] = Mmatter;
            a[i][6] = Mway;
            a[i][7] = Mnote;
            i++;
        }

        jb_Modify = new JButton("修改");
        jb_Modify.setFont(new Font("黑体", Font.PLAIN, 18));
        jb_Modify.addActionListener(this);

        jb_Return = new JButton("返回");
        jb_Return.setFont(new Font("黑体", Font.PLAIN, 18));
        jb_Return.addActionListener(this);
        table = new JTable(a, name);

        jPanel = new JPanel();
        jPanel.add(jb_Modify);
        jPanel.add(jb_Return);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(jPanel,BorderLayout.SOUTH);
        setSize(960,540);
        setVisible(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("学生信息查询结果");
        validate();//使容器再次布置其子组件,validate方法调整组件的大小
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(e.getActionCommand()=="修改"){
            int[] rows = table.getSelectedRows();

            for( int i = 0 ; i < rows.length ; i ++ ){
                x1 = (String) table.getValueAt(rows[i], 0);
                x2 = (String) table.getValueAt(rows[i], 1);
                x3 = (String) table.getValueAt(rows[i], 2);
                x4 = (String) table.getValueAt(rows[i], 3);
                x5 = (String) table.getValueAt(rows[i], 4);
                x6 = (String) table.getValueAt(rows[i], 5);
                x7 = (String) table.getValueAt(rows[i], 6);
                x8 = (String) table.getValueAt(rows[i], 7);
                try {
                    ps = conn.prepareStatement("update Student set  Sname = ? where Sno = ?");
                    ps.setString(1, x2);
                    ps.setString(2, x1);
                    ps.executeUpdate();

                    ps = conn.prepareStatement("update caretaker set Cname = ? where Cno = ?");
                    ps.setString(1, x4);
                    ps.setString(2, x3);
                    ps.executeUpdate();
                    ps = conn.prepareStatement("update Management set  Sno = ? , Mdate = ? , Cno = ? ,"
                            + "Mmatter = ? ,Mway = ? , Mnote = ? where Sno = ?");

                    ps.setString(1, x1);
                    System.out.println(x5);
                    ps.setString(2, x5);
                    ps.setString(3, x3);
                    ps.setString(4, x6);
                    ps.setString(5, x7);
                    ps.setString(6, x8);
                    ps.setString(7, x1);
                    ps.executeUpdate();

                    JOptionPane.showMessageDialog(null,"修改成功！","提示消息",JOptionPane.WARNING_MESSAGE);
                    dispose();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        }
        if(e.getActionCommand()=="返回"){
            dispose();
        }
    }
}

