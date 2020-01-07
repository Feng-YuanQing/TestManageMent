package AccomInfoManage;

import DataBaseManage.*;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Login.UI_admin;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

class HomePanel extends JPanel {
    ImageIcon icon;
    Image img;

    public HomePanel() {
        // /img/HomeImg.jpg 是存放在你正在编写的项目的bin文件夹下的img文件夹下的一个图片
        icon = new ImageIcon(getClass().getResource("/6666.jpg"));
        img = icon.getImage();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 下面这行是为了背景图片可以跟随窗口自行调整大小，可以自己设置成固定大小
        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
public class UI_admin_accomInfoManage extends JFrame {

    //region 变量声明
    private JPanel contentPane;
    private JTextField text_Input_StuID;
    private JTextField text_Input_DormID;
    private JTextField text_Input_BedID;
    private JTextField text_Input_Date;
    private JTextField text_Input_Note;
    private JTextField text_Input_BuildID;
    private JTextField text_Search_StuID;
    private JTextField text_Search_StuName;
    private JTextField text_Search_StuMajor;
    private JTextField text_Search_DormMonitorName;
    private JTextField text_Search_BuildID;
    Connection conn;
    java.sql.PreparedStatement ps;
    ResultSet rs;
    Statement st;

    String str_InputStuID, sql_SearchStu;
    private JTextField text_Search_DormID;
    private JTextField text_PeopleLeave;
    String name;
    //endregion

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UI_admin_accomInfoManage frame = new UI_admin_accomInfoManage();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public UI_admin_accomInfoManage(String str) {
        this.name = str;
        init();
    }

    public UI_admin_accomInfoManage() {
        init();
    }

    void init() {
        // 连接数据库
        conn = DBManage.CreatConnection();

        // 新建布局
        CardLayout eee = new CardLayout();

        // 新建Panel
        JPanel panel_1 = new JPanel(eee);
        JPanel panel_InputInfoUI = new HomePanel(); // 查询宿舍信息
        JPanel panel_SearchInfoUI = new HomePanel();
        JPanel panel_4 = new HomePanel();

        //region ===  窗口的相关设置  ===
        setResizable(false); // 是否可以改窗口大小大小
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 关闭窗口时退出程序
        setSize(700, 650); // 设置窗口的大小
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null); // 没有layout

        JPanel panel = new JPanel();
        panel.setBackground(new Color(238, 238, 238));
        panel.setSize(694, 615);
        contentPane.add(panel);
        panel.setLayout(null);
        setLocationRelativeTo(null);
        setTitle("住宿信息管理                                                    当前登录用户为：" + name);
        //endregion

        //region ===  创建左侧按钮  ===
        // 录入信息按钮
        JButton button_InputInfoUI = new JButton("录入住宿信息");
        button_InputInfoUI.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                eee.show(panel_1, "name_3899849045844"); // ????
            }
        });
        button_InputInfoUI.setBounds(31, 100, 130, 40); // 设置按钮位置
        panel.add(button_InputInfoUI); // 将按钮添加到Panel中

        // 查询住宿信息按钮
        JButton button_SearchInfoUI = new JButton("查询住宿信息");
        button_SearchInfoUI.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                eee.show(panel_1, "name_3899859768506");// ????
            }
        });
        button_SearchInfoUI.setBounds(31, 190, 130, 40); // 设置按钮位置
        panel.add(button_SearchInfoUI); // 将按钮添加到panel中

        // 个别学生退宿按钮
        JButton button_StuLeaveUI = new JButton("个别学生退宿");
        button_StuLeaveUI.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                eee.show(panel_1, "name_514319847206393");// ????
            }
        });
        button_StuLeaveUI.setBounds(31, 280, 130, 40);// 设置按钮位置
        panel.add(button_StuLeaveUI);// 将按钮添加到panel中

        // 宿舍人员调整按钮
        JButton button_PeopleAdjustUI = new JButton("宿舍人员调整");
        button_PeopleAdjustUI.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // 获取需要查询的学号
                    String stuNo = JOptionPane.showInputDialog("请输入要调整的学号:");
                    if(stuNo == null){
                        return;
                    }
                    // 多表连接查询
                    if(stuNo.isEmpty()){
                        ps = conn.prepareStatement("select student.sno , sname , dorm.Fno , dorm.dno , bno "
                                + "from student , dorm , accommodation "
                                + "where accommodation.fno = dorm.fno and student.sno = accommodation.sno AND accommodation.dno = dorm.dno");
                    }else{
                        ps = conn.prepareStatement("select student.sno , sname , dorm.Fno , dorm.dno , bno "
                                + "from student , dorm , accommodation "
                                + "where accommodation.fno = dorm.fno and student.sno = accommodation.sno AND accommodation.dno = dorm.dno AND student.sno = " + stuNo);
                    }
                    rs = ps.executeQuery();
                    Mytable_DormAdjust mytable_dormAdjustdjust = new Mytable_DormAdjust(rs); // 打开调整界面
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        button_PeopleAdjustUI.setBounds(31, 370, 130, 40); // 设置按钮位置
        panel.add(button_PeopleAdjustUI);// 将按钮添加到panel中

        // 返回上一级按钮
        JButton button_BackPreviousLevelUI = new JButton("返回上一级");
        button_BackPreviousLevelUI.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // 关闭当前界面
                UI_admin frame = new UI_admin(name); // 新建主界面
                frame.setVisible(true);
            }
        });
        button_BackPreviousLevelUI.setBounds(31, 460, 130, 40);// 设置按钮位置
        panel.add(button_BackPreviousLevelUI);// 将按钮添加到panel中
        //endregion

        panel_1.setBounds(196, 13, 484, 589);
        panel.add(panel_1);

        //region 创建录入信息界面相关
        panel_1.add(panel_InputInfoUI, "name_3899849045844");
        panel_InputInfoUI.setLayout(null);

        JLabel lblNewLabel = new JLabel(" 学   号：");
        lblNewLabel.setBounds(87, 100, 72, 18);
        panel_InputInfoUI.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel(" 宿舍号 ：");
        lblNewLabel_1.setBounds(87, 170, 80, 18);
        panel_InputInfoUI.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel(" 床   号：");
        lblNewLabel_2.setBounds(87, 240, 72, 18);
        panel_InputInfoUI.add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel(" 楼   号：");
        lblNewLabel_3.setBounds(87, 310, 80, 18);
        panel_InputInfoUI.add(lblNewLabel_3);

        JLabel lblNewLabel_4 = new JLabel("入住时间：");
        lblNewLabel_4.setBounds(87, 380, 72, 18);
        panel_InputInfoUI.add(lblNewLabel_4);

        JLabel lblNewLabel_5 = new JLabel(" 备   注：");
        lblNewLabel_5.setBounds(87, 450, 72, 18);
        panel_InputInfoUI.add(lblNewLabel_5);

        text_Input_StuID = new JTextField();
        text_Input_StuID.setBounds(162, 97, 180, 24);
        panel_InputInfoUI.add(text_Input_StuID);
        text_Input_StuID.setColumns(10);

        text_Input_DormID = new JTextField();
        text_Input_DormID.setBounds(162, 167, 180, 24);
        panel_InputInfoUI.add(text_Input_DormID);
        text_Input_DormID.setColumns(10);

        text_Input_BedID = new JTextField();
        text_Input_BedID.setBounds(162, 237, 180, 24);
        panel_InputInfoUI.add(text_Input_BedID);
        text_Input_BedID.setColumns(10);

        text_Input_BuildID = new JTextField();
        text_Input_BuildID.setBounds(162, 307, 180, 24);
        panel_InputInfoUI.add(text_Input_BuildID);
        text_Input_BuildID.setColumns(10);

        text_Input_Date = new JTextField();
        text_Input_Date.setBounds(162, 377, 180, 24);
        panel_InputInfoUI.add(text_Input_Date);
        text_Input_Date.setColumns(10);
        // 设置当前的日期为默认值
        text_Input_Date.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        text_Input_Note = new JTextField();
        text_Input_Note.setBounds(162, 447, 180, 24);
        panel_InputInfoUI.add(text_Input_Note);
        text_Input_Note.setColumns(10);

        JButton button_Input_Confirm = new JButton("确定");
        button_Input_Confirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // 数据库连接
                    st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY,ResultSet.HOLD_CURSORS_OVER_COMMIT);

                    //region 获取输入
                    String str_StuID = text_Input_StuID.getText();
                    String str_DormID = text_Input_DormID.getText();
                    String str_BedID = text_Input_BedID.getText();
                    String str_BuildID = text_Input_BuildID.getText();
                    String str_Date = text_Input_Date.getText();
                    String str_Note = text_Input_Note.getText();
                    //endregion

                    //region 判断是否为空
                    if(str_StuID.isEmpty()){
                        JOptionPane.showMessageDialog(null, "学号不能为空！", "提示消息", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    if(str_DormID.isEmpty()){
                        JOptionPane.showMessageDialog(null, "宿舍号不能为空！", "提示消息", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    if(str_BedID.isEmpty()){
                        JOptionPane.showMessageDialog(null, "床号不能为空！", "提示消息", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    if(str_BuildID.isEmpty()){
                        JOptionPane.showMessageDialog(null, "楼号不能为空！", "提示消息", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    if(str_Date.isEmpty()){
                        JOptionPane.showMessageDialog(null, "入住时间不能为空！", "提示消息", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    //endregion

                    //region 判断输入的数据合不合要求
                    // 判断学号是否存在
                    ResultSet result_Stu = st.executeQuery("select * from student where Sno =" + str_StuID);//-

                    if(!result_Stu.next()){
                        JOptionPane.showMessageDialog(null, "学号不存在！", "提示消息", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    // 判断学生是否已经入住
                    ResultSet result_Accommodation = st.executeQuery("select * from accommodation where Sno = "+ str_StuID);//-
                    if(result_Accommodation.next()){
                        System.out.println(result_Accommodation.getString(1));
                        JOptionPane.showMessageDialog(null, "该学生已经安排住宿！", "提示消息", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    // 判断宿舍是否存在
                    ResultSet result_Dorm = st.executeQuery("select * from dorm where dno =" + str_DormID + " and fno = " + str_BuildID);//-
                    if(!result_Dorm.next()){
                        JOptionPane.showMessageDialog(null, "宿舍不存在！", "提示消息", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    // 判断宿舍楼是否存在
                    ResultSet result_Building = st.executeQuery("select  * from floor where fno = " + str_BuildID);//-
                    if(!result_Building.next()){
                        JOptionPane.showMessageDialog(null, "宿舍楼不存在！", "提示消息", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    // 判断楼别与性别是否对应
                    String buildSex = result_Building.getString("Fsex");
                    int dormSize = result_Building.getInt("DS"); // 宿舍标准
                    result_Stu = st.executeQuery("select * from student where Sno =" + str_StuID);//-
                    result_Stu.next();
                    String stuSex = result_Stu.getString("Ssex");
                    if(!buildSex.equals(stuSex)){
                        JOptionPane.showMessageDialog(null, "楼别与性别不对应！", "提示消息", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    // 床号是否存在
                    ResultSet result_PeoInDorm = st.executeQuery("select * from accommodation where Dno = "+ str_DormID + " and Fno = " + str_BuildID);//-
                    //result_PeoInDorm.next();//-
                    if(Integer.parseInt( str_BedID ) > dormSize || Integer.parseInt( str_BedID ) < 1){
                        JOptionPane.showMessageDialog(null, "床号不存在！", "提示消息", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    // 判断宿舍是否已满
                    result_PeoInDorm.last();
                    if(result_PeoInDorm.getRow() >= dormSize){
                        JOptionPane.showMessageDialog(null, "该宿舍已满！", "提示消息", JOptionPane.WARNING_MESSAGE);
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
                    //endregion

                    //region 插入数据
                    ps = conn.prepareStatement("insert into Accommodation values (?,?,?,?,?,?)");
                    ps.setString(1,str_StuID);
                    ps.setString(2,str_DormID);
                    ps.setString(3,str_BedID);
                    if(str_Note.isEmpty())
                        ps.setNull(4, Types.VARCHAR);
                    else
                        ps.setString(4,str_Note);
                    ps.setString(5,str_Date);
                    ps.setString(6,str_BuildID);
                    ps.executeUpdate(); // 执行语句
                    JOptionPane.showMessageDialog(null, "录入成功！", "提示消息", JOptionPane.INFORMATION_MESSAGE);
                    //endregion
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    return;
                }
            }
        });
        button_Input_Confirm.setBounds(195, 486, 113, 27);
        panel_InputInfoUI.add(button_Input_Confirm);
        //endregion

        //region 创建搜索界面相关
        panel_1.add(panel_SearchInfoUI, "name_3899859768506");
        panel_SearchInfoUI.setLayout(null);

        JLabel lblNewLabel_6 = new JLabel(" 学    号：");
        lblNewLabel_6.setBounds(87, 100, 72, 18);
        panel_SearchInfoUI.add(lblNewLabel_6);

        JLabel lblNewLabel_7 = new JLabel(" 姓    名：");
        lblNewLabel_7.setBounds(87, 170, 80, 18);
        panel_SearchInfoUI.add(lblNewLabel_7);

        JLabel lblNewLabel_8 = new JLabel(" 专    业：");
        lblNewLabel_8.setBounds(87, 240, 72, 18);
        panel_SearchInfoUI.add(lblNewLabel_8);

        JLabel lblNewLabel_9 = new JLabel(" 宿舍号  ：");
        lblNewLabel_9.setBounds(87, 310, 80, 18);
        panel_SearchInfoUI.add(lblNewLabel_9);

        JLabel lblNewLabel_10 = new JLabel(" 舍长姓名：");
        lblNewLabel_10.setBounds(87, 380, 72, 18);
        panel_SearchInfoUI.add(lblNewLabel_10);

        JLabel lblNewLabel_11 = new JLabel(" 楼    号：");
        lblNewLabel_11.setBounds(87, 450, 72, 18);
        panel_SearchInfoUI.add(lblNewLabel_11);

        text_Search_StuID = new JTextField();
        text_Search_StuID.setBounds(162, 97, 180, 24);
        panel_SearchInfoUI.add(text_Search_StuID);
        text_Search_StuID.setColumns(10);

        text_Search_StuName = new JTextField();
        text_Search_StuName.setBounds(162, 167, 180, 24);
        panel_SearchInfoUI.add(text_Search_StuName);
        text_Search_StuName.setColumns(10);

        text_Search_StuMajor = new JTextField();
        text_Search_StuMajor.setBounds(162, 237, 180, 24);
        panel_SearchInfoUI.add(text_Search_StuMajor);
        text_Search_StuMajor.setColumns(10);

        text_Search_DormID = new JTextField();
        text_Search_DormID.setBounds(162, 307, 180, 24);
        panel_SearchInfoUI.add(text_Search_DormID);
        text_Search_DormID.setColumns(10);

        text_Search_DormMonitorName = new JTextField();
        text_Search_DormMonitorName.setBounds(162, 377, 180, 24);
        panel_SearchInfoUI.add(text_Search_DormMonitorName);
        text_Search_DormMonitorName.setColumns(10);

        text_Search_BuildID = new JTextField();
        text_Search_BuildID.setBounds(162, 447, 180, 24);
        panel_SearchInfoUI.add(text_Search_BuildID);
        text_Search_BuildID.setColumns(10);

        JButton button_Search_Confirm = new JButton("确定");
        button_Search_Confirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // 这样操作可以实现模糊查询！
                    // 查询视图
                    // 床号、入住时间的查询还没加进去
                    ps = conn.prepareStatement("select * from S_accomm_dorm where Sno LIKE ? and Sname LIKE ?  "
                            + "and Smajor LIKE ?  and Dno LIKE ? and  DHname LIKE ? and Fno LIKE ? order by Sno");

                    if (text_Search_StuID.getText().length() > 0) {
                        ps.setString(1, text_Search_StuID.getText() + "%");
                    } else {
                        ps.setString(1, "%");
                    }
                    if (text_Search_StuName.getText().length() > 0) {
                        ps.setString(2, text_Search_StuName.getText() + "%");
                    } else {
                        ps.setString(2, "%");
                    }
                    if (text_Search_StuMajor.getText().length() > 0) {
                        ps.setString(3, text_Search_StuMajor.getText() + "%");
                    } else {
                        ps.setString(3, "%");
                    }
                    if (text_Search_DormID.getText().length() > 0) {
                        ps.setString(4, text_Search_DormID.getText() + "%");
                    } else {
                        ps.setString(4, "%");
                    }
                    if (text_Search_DormMonitorName.getText().length() > 0) {
                        ps.setString(5, text_Search_DormMonitorName.getText() + "%");
                    } else {
                        ps.setString(5, "%");
                    }
                    if (text_Search_BuildID.getText().length() > 0) {
                        ps.setString(6, text_Search_BuildID.getText() + "%");
                    } else {
                        ps.setString(6, "%");
                    }
                    rs = ps.executeQuery();
                    Mytable_accom selectaccomInfo_table = new Mytable_accom(rs);

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        button_Search_Confirm.setBounds(180, 487, 113, 27);
        panel_SearchInfoUI.add(button_Search_Confirm);
        //endregion

        //region 创建退宿界面相关
        panel_1.add(panel_4, "name_514319847206393");
        panel_4.setLayout(null);

        JLabel label = new JLabel("请输入需要退宿的学号：");
        label.setBounds(94, 206, 156, 18);
        panel_4.add(label);

        text_PeopleLeave = new JTextField();
        text_PeopleLeave.setBounds(258, 203, 163, 24);
        panel_4.add(text_PeopleLeave);
        text_PeopleLeave.setColumns(10);

        JButton button_PeopleLeaveConfirm = new JButton("确定");
        button_PeopleLeaveConfirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                str_InputStuID = text_PeopleLeave.getText();
                sql_SearchStu = "select Sno from Student where Sno = " + str_InputStuID;
                try {
                    st = conn.createStatement();
                    if (text_PeopleLeave.getText().length() == 0) {
                        JOptionPane.showMessageDialog(null, "请输入需要删除的信息！", "提示消息", JOptionPane.WARNING_MESSAGE);
                    } else if (!st.executeQuery(sql_SearchStu).next()) {
                        JOptionPane.showMessageDialog(null, "此学号不存在！", "提示消息", JOptionPane.WARNING_MESSAGE);
                    } else {
                        st.executeUpdate("delete from Accommodation where Sno = " + str_InputStuID);
                        JOptionPane.showMessageDialog(null, "删除成功！", "提示消息", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        button_PeopleLeaveConfirm.setBounds(195, 486, 113, 27);
        panel_4.add(button_PeopleLeaveConfirm);
        //endregion
    }
}
