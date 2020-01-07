package AccomInfoManage;

import DataBaseManage.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

public class Mytable_DormAdjust extends JFrame implements ActionListener {

    JTable table;
    Object objects[][];
    Object[] name = { "学号", "姓名","楼号", "宿舍号", "床号" };
    JButton button_ModifyConfirm, button_Back;
    JPanel jp;
    Connection conn ;
    java.sql.PreparedStatement ps;

    public Mytable_DormAdjust(ResultSet rs) throws Exception {
        // 连接数据库
        conn = DBManage.CreatConnection();

        //获取结果集rs的行数，从而构建“灵活”的表！
        int count = 0;
        rs.last();
        count = rs.getRow();
        rs.beforeFirst();
        objects = new Object[count][10];
        int i = 0;
        while (rs.next()) {
            String tempStuID = rs.getString("Sno");
            String tempStuName = rs.getString("Sname");
            String tempBuildID = rs.getString("Fno");
            String tempDormID = rs.getString("Dno");
            String tempBedID = rs.getString("Bno");

            objects[i][0] = tempStuID;
            objects[i][1] = tempStuName;
            objects[i][2] = tempBuildID;
            objects[i][3] = tempDormID;
            objects[i][4] = tempBedID;
            i++;
        }
        button_ModifyConfirm = new JButton("修改");
        button_ModifyConfirm.setFont(new Font("黑体", Font.PLAIN, 18));
        button_ModifyConfirm.addActionListener(this);

        button_Back = new JButton("返回");
        button_Back.setFont(new Font("黑体", Font.PLAIN, 18));
        button_Back.addActionListener(this);
        table = new MyJTable(objects, name);
        table.getTableHeader().setReorderingAllowed(false);

        jp = new JPanel();
        this.setBackground(new Color(176, 224, 230));
        jp.add(button_ModifyConfirm);
        jp.add(button_Back);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(jp,BorderLayout.SOUTH);
        setSize(960,540);
        setVisible(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("住宿信息调整");
        validate();

    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand()=="修改"){
            int[] rows = table.getSelectedRows();

            for( int i = 0 ; i < rows.length ; i ++ ){
                String str_StuID = (String) table.getValueAt(rows[i], 0);
                String str_BuildID = (String) table.getValueAt(rows[i], 2);
                String str_DormID = (String) table.getValueAt(rows[i], 3);
                String str_BedID = (String) table.getValueAt(rows[i], 4);

                try {
                    //判断
                    Statement st = conn.createStatement();
                    // 判断宿舍是否存在
                    ResultSet result_Dorm = st.executeQuery("select * from dorm where dno =" + str_DormID + " and fno = " + str_BuildID);//-
                    if(!result_Dorm.next()){
                        JOptionPane.showMessageDialog(null, "宿舍不存在！", "提示消息", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    // 床号是否存在
                    ResultSet result_Building = st.executeQuery("select  * from floor where fno = " + str_BuildID);
                    result_Building.next();
                    int dormSize = result_Building.getInt("DS"); // 宿舍标准
                    ResultSet result_PeoInDorm = st.executeQuery("select * from accommodation where Dno = "+ str_DormID + " and Fno = " + str_BuildID);//-
                    if(Integer.parseInt( str_BedID ) > dormSize || Integer.parseInt( str_BedID ) < 1){
                        JOptionPane.showMessageDialog(null, "床号不存在！", "提示消息", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    // 判断床号是否有人
                    result_PeoInDorm.beforeFirst();
                    while(result_PeoInDorm.next()){
                        if(result_PeoInDorm.getString("Bno").equals(str_BedID)){
                            JOptionPane.showMessageDialog(null, "该床号已有人！", "提示消息", JOptionPane.WARNING_MESSAGE);
                            return;
                        }
                    }

                    // 修改
                    ps = conn.prepareStatement("update accommodation set Fno = ? , Dno = ? ,  Bno = ?  where Sno = ?");
                    System.out.println(str_StuID + " " + str_BedID);
                    ps.setString(1, str_BuildID);
                    ps.setString(2, str_DormID);
                    ps.setString(3, str_BedID);
                    ps.setString(4, str_StuID);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null,"修改成功！","提示消息",JOptionPane.WARNING_MESSAGE);
                    dispose();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }
        if(e.getActionCommand()=="返回"){
            dispose();
        }
    }
}
class MyJTable extends  JTable{
    public boolean isCellEditable(int row, int column) {
        switch (column){
            case 2:
            case 3:
            case 4:
                return true;
            default:
                return false;
        }
    }
    public MyJTable(Object[][] objs1,Object[] objs2){
        super(objs1,objs2);
    }
}


