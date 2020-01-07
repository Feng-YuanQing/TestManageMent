package DormInfoManage;

import DataBaseManage.DBManage;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Mytable_Care extends JFrame implements ActionListener {

    JTable table;
    Object a[][];
    Object[] name = { "楼管编号", "姓名", "年龄","性别","电话" };
    JButton jb1,jb2;
    JPanel jp;

//	public int[] rows;

    Connection conn ;
    PreparedStatement ps;

    static String tempLouGuanID, tempLouGuanname, tempLouGuanAge, tempLouGuanSex, tempLouGuanTel;

    public Mytable_Care(ResultSet rs) throws Exception {
        //获取结果集rs的行数，从而构建“灵活”的表！root
        int count = 0;
        rs.last();
        count = rs.getRow();
        rs.beforeFirst();
        conn = DBManage.CreatConnection();
        a = new Object[count][5];
        int i = 0;
        while (rs.next()) {
            String tempLouGuanID = rs.getString("Cno");
            String tempLouGuanname = rs.getString("Cname");
            String tempLouGuanAge = rs.getString("Cage");
            String tempLouGuanSex = rs.getString("Csex");
            String tempLouGuanTel = rs.getString("Ctel");
            a[i][0] = tempLouGuanID;
            a[i][1] = tempLouGuanname;
            a[i][2] = tempLouGuanAge;
            a[i][3] = tempLouGuanSex;
            a[i][4] = tempLouGuanTel;
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
        setTitle("楼管信息查询结果");
        setResizable(false);
        validate();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand()=="修改"){
            int[] rows = table.getSelectedRows();
            for( int i = 0 ; i < rows.length ; i ++ ){
                tempLouGuanID = (String) table.getValueAt(rows[i], 0);
                tempLouGuanname = (String) table.getValueAt(rows[i], 1);
                tempLouGuanAge = (String) table.getValueAt(rows[i], 2);
                tempLouGuanSex = (String) table.getValueAt(rows[i], 3);
                tempLouGuanTel = (String) table.getValueAt(rows[i], 4);
                try {
                    ps = conn.prepareStatement("update Caretaker set  Cno = ? , Cname = ? ,Cage = ? , Csex = ? ,Ctel = ? where Cno = ?");
                    ps.setString(1, tempLouGuanID);
                    ps.setString(2, tempLouGuanname);
                    ps.setString(3, tempLouGuanAge);
                    ps.setString(4, tempLouGuanSex);
                    ps.setString(5, tempLouGuanTel);
                    ps.setString(6, tempLouGuanID);
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

