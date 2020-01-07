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

public class Mytable_Floor extends JFrame implements ActionListener {

    JTable table;
    Object a[][];
    Object[] name = { "楼号", "楼别","楼管编号", "楼管姓名","楼管性别","宿舍数量","宿舍标准"};
    JButton jb1,jb2;
    JPanel jp;
    static Connection conn ;
    java.sql.PreparedStatement ps;

    static String x, tempFno, tempFsex, tempCno, tempCname, tempCsex,tempFnumber, tempDS;

    public Mytable_Floor(ResultSet rs) throws Exception {
        // TODO Auto-generated constructor stub
        //获取结果集rs的行数，从而构建“灵活”的表！
        int count = 0;
        rs.last();
        count = rs.getRow();
        rs.beforeFirst();
        conn = DBManage.CreatConnection();
        a = new Object[count][9];
        int i = 0;
        while (rs.next()) {
            String tempFno = rs.getString("Fno");
            String tempFsex = rs.getString("Fsex");
            String tempCno = rs.getString("Cno");
            String tempCname = rs.getString("Cname");
            String tempCsex= rs.getString("Csex");
            String tempFnumber = rs.getString("Fnumber");
            String tempDS = rs.getString("DS");
            a[i][0] = tempFno;
            a[i][1] = tempFsex;
            a[i][2] = tempCno;
            a[i][3] = tempCname;
            a[i][4] = tempCsex;
            a[i][5] = tempFnumber;
            a[i][6] = tempDS;
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
        setTitle("宿舍楼信息查询结果");
        validate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(e.getActionCommand()=="返回"){
            dispose();
        }
        if(e.getActionCommand()=="修改"){
            int[] rows = table.getSelectedRows();

            for( int i = 0 ; i < rows.length ; i ++ ){
                tempFno = (String) table.getValueAt(rows[i], 0);
                tempFsex = (String) table.getValueAt(rows[i], 1);
                tempCno = (String) table.getValueAt(rows[i], 2);
                tempCname = (String) table.getValueAt(rows[i], 3);
                tempCsex = (String) table.getValueAt(rows[i], 4);
                tempFnumber = (String) table.getValueAt(rows[i], 5);
                tempDS = (String) table.getValueAt(rows[i], 6);
                try {
                    ps = conn.prepareStatement("update Floor set  Fno = ? , Fsex = ? , Cno = ? , Fnumber = ? , DS = ?  where Fno = ?");
                    ps.setString(1, tempFno);
                    ps.setString(2, tempFsex);
                    ps.setString(3, tempCno);
                    ps.setString(4, tempFnumber);
                    ps.setString(5, tempDS);
                    ps.setString(6, tempFno);

                    ps.executeUpdate();
                    ps = conn.prepareStatement("update Caretaker set  Cname = ? , Csex = ?  where Cno = ?");
                    ps.setString(1, tempCname);
                    ps.setString(2, tempCsex);
                    ps.setString(3, tempCno);

                    ps.executeUpdate();

                    JOptionPane.showMessageDialog(null,"修改成功！","提示消息",JOptionPane.WARNING_MESSAGE);
                    dispose();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        }
    }

}
