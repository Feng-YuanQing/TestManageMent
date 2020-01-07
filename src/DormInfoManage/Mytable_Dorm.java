package DormInfoManage;

import DataBaseManage.DBManage;

import java.awt.BorderLayout;
import java.awt.Font;
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

public class Mytable_Dorm extends JFrame implements ActionListener {

    JTable table;
    Object a[][];
    Object[] name = { "宿舍编号", "楼号", "舍长姓名"};
    JButton jb1,jb2;
    JPanel jp;
    static Connection conn ;
    java.sql.PreparedStatement ps;

    static String x, tempDormNo, tempFno, tempDname;

    public Mytable_Dorm(ResultSet rs) throws Exception {
        //获取结果集rs的行数，从而构建“灵活”的表！
        int count = 0;
        rs.last();
        count = rs.getRow();
        rs.beforeFirst();
        conn = DBManage.CreatConnection();
        a = new Object[count][5];
        int i = 0;
        while (rs.next()) {
            String tempDormNo = rs.getString("Dno");
            String tempFno = rs.getString("Fno");
            String tempDname = rs.getString("DHname");

            a[i][0] = tempDormNo;
            a[i][1] = tempFno;
            a[i][2] = tempDname;
            i++;
        }
        jb1 = new JButton("修改");
        jb1.setFont(new Font("黑体", Font.PLAIN, 18));
        jb1.addActionListener(this);

        jb2 = new JButton("返回");
        jb2.setFont(new Font("黑体", Font.PLAIN, 18));
        jb2.addActionListener(this);
        table = new JTable(a, name);

        jp = new JPanel();
        jp.add(jb1);
        jp.add(jb2);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(jp,BorderLayout.SOUTH);
        setSize(960,540);
        setVisible(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("宿舍信息查询结果");
        validate();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(e.getActionCommand()=="修改"){
            int[] rows = table.getSelectedRows();
            for( int i = 0 ; i < rows.length ; i ++ ){
                tempDormNo = (String) table.getValueAt(rows[i], 0);
                tempFno = (String) table.getValueAt(rows[i], 1);
                tempDname = (String) table.getValueAt(rows[i], 2);
                try {
                    ps = conn.prepareStatement("update Dorm set  Fno = ? ,DHname = ?    where Dno = ?");
                    ps.setString(1, tempDormNo);
                    ps.setString(2, tempFno);
                    ps.setString(3, tempDname);
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

