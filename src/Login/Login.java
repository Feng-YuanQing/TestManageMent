package Login;

import DataBaseManage.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Login extends JFrame implements ActionListener {

    Box baseBox, box_Name, box_PassW, box_Jrb, box_Jb;
    JButton jb_logon, jb_exit, jb_register, jb_find;
    JRadioButton jrb1;
    JTextField jtf_Name;
    JPasswordField jpf_PassW;
    ButtonGroup group;
    JPanel panel;
    JLabel label;

    static String manage_Id; // 管理员登录名
    static String manage_PassWord; // 管理员登录密码
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    public static void main(String[] args) {
        Login box = new Login();
        box.setSize(535, 470);
        box.setTitle("宿舍管理系统登录");
        box.setLocationRelativeTo(null); // 使窗口居中显示
        box.setVisible(true);
        box.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public Login()  {
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch (Exception e){}

        conn = DBManage.CreatConnection();
        panel = new JPanel();
        panel.setBackground(new Color(176, 224, 230));
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        label = new JLabel();
        ImageIcon img = new ImageIcon(getClass().getResource("/back2.jpg"));
        label.setIcon(img);
        panel.add(label);
        getContentPane().add(panel);
        add(getContentPane().add(panel));
        setContentPane(panel);
        setLayout(new FlowLayout());

        jb_logon = new JButton("登录");
        jb_exit = new JButton("退出");
        jb_register = new JButton("注册");
        jb_find = new JButton("找回");
        // 设置监听：
        jb_logon.addActionListener(this);
        jb_exit.addActionListener(this);
        jb_register.addActionListener(this);
        jb_find.addActionListener(this);

//        group = new ButtonGroup();
//        //创建一个单选按钮，默认选中
//        jrb1 = new JRadioButton("宿舍管理员", true);
//        group.add(jrb1);

        jtf_Name = new JTextField(10);
        jpf_PassW = new JPasswordField(10);

        Box box_Title = Box.createHorizontalBox();
        JLabel label_Title = new JLabel("宿舍管理系统");
        label_Title.setFont(new Font("宋体", Font.BOLD, 28));
        box_Title.add(label_Title);

        //region JLable、JTextField系列
        box_Name = Box.createHorizontalBox();
        JLabel jl_ManageId =  new JLabel(" 用户id ：  ");
        jl_ManageId.setIcon(new ImageIcon(getClass().getResource("/userIcon.png")));
        box_Name.add(jl_ManageId);
        box_Name.add(Box.createHorizontalStrut(10));
        box_Name.add(jtf_Name);
        box_Name.add(Box.createHorizontalStrut(10));

        box_PassW = Box.createHorizontalBox();
        JLabel jl_PassW = new JLabel(" 密  码 ：  ");
        jl_PassW.setIcon(new ImageIcon(getClass().getResource("/Password.png")));
        box_PassW.add(jl_PassW);
        box_PassW.add(Box.createHorizontalStrut(10));
        box_PassW.add(jpf_PassW);
        box_PassW.add(Box.createHorizontalStrut(10));
        //endregion

        box_Jrb = Box.createHorizontalBox();
        //box_Jrb.add(jrb1);

        //region JButton系列
        box_Jb = Box.createHorizontalBox();
        box_Jb.add(jb_logon);
        box_Jb.add(Box.createHorizontalStrut(10));

        box_Jb.add(jb_register);
        box_Jb.add(Box.createHorizontalStrut(10));
        box_Jb.add(jb_find);
        box_Jb.add(Box.createHorizontalStrut(10));
        box_Jb.add(jb_exit);

        baseBox = Box.createVerticalBox();
        baseBox.add(Box.createVerticalStrut(20));
        baseBox.add(box_Title);
        baseBox.add(Box.createVerticalStrut(30));
        baseBox.add(box_Name);
        baseBox.add(Box.createVerticalStrut(20));
        baseBox.add(box_PassW);
        baseBox.add(Box.createVerticalStrut(20));
        baseBox.add(box_Jrb);
        baseBox.add(Box.createVerticalStrut(20));
        baseBox.add(box_Jb);
        baseBox.add(Box.createVerticalStrut(20));
        add(baseBox);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand() == "登录") {
                if (jtf_Name.getText().isEmpty() && new String(jpf_PassW.getPassword()).isEmpty()) {//getPassword()得到的是字节数组，流程中需要转为String
                    //弹窗、弹框JOptionPane的showMessageDialog方法.
                    // 第一个参数是parentComponent -确定Frame在其中显示的对话框; 如果null ，或者如果parentComponent没有Frame ，则使用默认值Frame，这里使用的是null
                    //第二个参数message - 要显示的 Object ，message就是我们要在提示框里显示的信息，使用的是字符串
                    //第三个新增参数title是设置了可以改变窗体的主题信息
                    //第四个参数messageType是设置了信息提示内容的图标
                    JOptionPane.showMessageDialog(null, "请输入用户id和密码！", "提示消息", JOptionPane.WARNING_MESSAGE);
                } else if (jtf_Name.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "请输入用户id！", "提示消息", JOptionPane.WARNING_MESSAGE);
                } else if (new String(jpf_PassW.getPassword()).isEmpty()) {
                    JOptionPane.showMessageDialog(null, "请输密码！", "提示消息", JOptionPane.WARNING_MESSAGE);
                } else {
                    try {
                        ps = conn.prepareStatement("select Cno,Cpassword from caretaker where Cno = ?",ResultSet.CONCUR_UPDATABLE);
                        ps.setString(1, jtf_Name.getText());
                        rs = ps.executeQuery();
                        // 循环取出
                        if (rs.next()) {
                            rs.beforeFirst();
                            while (rs.next()) {
                                manage_Id = rs.getString(1);
                                manage_PassWord = rs.getString(2);
                                if (manage_Id.equals(jtf_Name.getText())
                                        && manage_PassWord.equals(new String(jpf_PassW.getPassword()))) {
                                    JOptionPane.showMessageDialog(null, "登录成功！", "提示消息", JOptionPane.WARNING_MESSAGE);
                                    dispose(); // 关闭当前界面
                                    UI_admin frame = new UI_admin(jtf_Name.getText());
                                    frame.setVisible(true);

                                } else {
                                    JOptionPane.showMessageDialog(null, "用户id或密码错误！", "提示消息", JOptionPane.ERROR_MESSAGE);
                                    clear();//刷新
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "用户id不存在！", "提示消息", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                }
        }
        if (e.getActionCommand() == "退出") {
            JOptionPane.showMessageDialog(null, "感谢您的使用！", "提示消息", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
        if (e.getActionCommand() == "注册") {
            new UI_Registered();
        }
        if (e.getActionCommand() == "找回") {
            new UI_RetrievePassword();
        }
    }
    // 清空文本框和密码框
    public void clear() {
        jpf_PassW.setText("");
        jtf_Name.setText("");
    }
}

