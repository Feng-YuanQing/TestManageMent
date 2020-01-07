package DailyManage;

import DataBaseManage.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Login.UI_admin;

import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

class HomePanel extends JPanel {
    ImageIcon icon;
    Image img;

    public HomePanel() {
        //  是存放在文件夹下的img文件夹下的一个图片
        icon = new ImageIcon(getClass().getResource("/winter-scene.png"));
        img = icon.getImage();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 下面这行是为了背景图片可以跟随窗口自行调整大小，可以自己设置成固定大小
        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}

public class ui_admin_DailyManage_2 extends JFrame {
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    Statement st;

    private JPanel contentPane;
    private JTextField jtf_StuId;
    private JTextField jtf_BreakRuTime;
    private JTextField jtf_ManageId;
    private JTextField jtf_BreakRuContent;
    private JTextField jtf_DealWay;
    private JTextField jtf_Remark;
    private JTextField jtf_QuStuId;
    private JTextField jtf_QuBrRuTime;
    private JTextField jtf_QuManageId;
    String str1, str2, str3, str4;
    String name;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ui_admin_DailyManage_2 frame = new ui_admin_DailyManage_2();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ui_admin_DailyManage_2(String str) {
        name = str;
        init();
    }

    public ui_admin_DailyManage_2() {
        init();
    }

    void init() {

        conn = DBManage.CreatConnection();

        CardLayout cardLayout = new CardLayout();
        contentPane = new JPanel(cardLayout);
        JPanel jP_Total = new JPanel(cardLayout);
        JPanel jP_Modify = new HomePanel();
        JPanel jP_Query = new HomePanel();
        this.setBackground(new Color(176, 224, 230));
        setResizable(false);//窗口大小不可改变
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 644);
        setTitle("日常信息管理                                                      当前登录用户为：" + name);
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        setLocationRelativeTo(null); // 使窗口居中显示

        //region JMenu 违纪管理
        JMenu jm_BreakRules = new JMenu("违纪管理     ");
        jm_BreakRules.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(contentPane, "name_424633793506497");
            }
        });
        menuBar.add(jm_BreakRules);

        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        contentPane.add(panel, "name_424633793506497");
        panel.setLayout(null);
        //endregion

        //region 按钮设置
        JButton jb_AddBrRu = new JButton("添加违纪");
        jb_AddBrRu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(jP_Total, "name_424837464476714");
            }
        });
        jb_AddBrRu.setBounds(26, 130, 113, 49);
        panel.add(jb_AddBrRu);

        JButton jb_QueryBrRu = new JButton("查询违纪");
        jb_QueryBrRu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(jP_Total, "name_424844103865194");
            }
        });
        jb_QueryBrRu.setBounds(26, 250, 113, 49);
        panel.add(jb_QueryBrRu);

        JButton jb_Return = new JButton("返回上一级");
        jb_Return.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                UI_admin frame = new UI_admin(name);
                frame.setVisible(true);
            }
        });
        jb_Return.setBounds(26, 370, 113, 49);
        panel.add(jb_Return);

        jP_Total.setBounds(161, 13, 497, 535);
        panel.add(jP_Total);
        //endregion

        //region 添加违纪
        jP_Total.add(jP_Modify, "name_424837464476714");
        jP_Modify.setLayout(null);

        JLabel jl_StuId = new JLabel("学    号：");
        jl_StuId.setBounds(110, 40, 80, 18);
        jP_Modify.add(jl_StuId);

        JLabel jl_BreakRuTime = new JLabel("违纪时间：");
        jl_BreakRuTime.setBounds(110, 100, 80, 18);
        jP_Modify.add(jl_BreakRuTime);

        JLabel jl_ManageId = new JLabel("楼管编号：");
        jl_ManageId.setBounds(110, 160, 80, 18);
        jP_Modify.add(jl_ManageId);

        JLabel jl_BreakRuContent = new JLabel("违纪内容：");
        jl_BreakRuContent.setBounds(110, 220, 80, 18);
        jP_Modify.add(jl_BreakRuContent);

        JLabel jl_DealWay = new JLabel("处理方式：");
        jl_DealWay.setBounds(110, 280, 80, 18);
        jP_Modify.add(jl_DealWay);

        JLabel jl_Remark = new JLabel(  "备    注：");
        jl_Remark.setBounds(110, 340, 80, 18);
        jP_Modify.add(jl_Remark);

        jtf_StuId = new JTextField();
        jtf_StuId.setBounds(190, 40, 180, 24);
        jP_Modify.add(jtf_StuId);
        jtf_StuId.setColumns(10);

        jtf_BreakRuTime = new JTextField();
        jtf_BreakRuTime.setBounds(190, 100, 180, 24);
        jP_Modify.add(jtf_BreakRuTime);
        jtf_BreakRuTime.setColumns(10);
        jtf_BreakRuTime.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        jtf_ManageId = new JTextField();
        jtf_ManageId.setBounds(190, 160, 180, 24);
        jP_Modify.add(jtf_ManageId);
        jtf_ManageId.setColumns(10);

        jtf_BreakRuContent = new JTextField();
        jtf_BreakRuContent.setBounds(190, 220, 180, 24);
        jP_Modify.add(jtf_BreakRuContent);
        jtf_BreakRuContent.setColumns(10);

        jtf_DealWay = new JTextField();
        jtf_DealWay.setBounds(190, 280, 180, 24);
        jP_Modify.add(jtf_DealWay);
        jtf_DealWay.setColumns(10);

        jtf_Remark = new JTextField();
        jtf_Remark.setBounds(190, 340, 180, 24);
        jP_Modify.add(jtf_Remark);
        jtf_Remark.setColumns(10);

        JButton jb_Define = new JButton("确定");
        jb_Define.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                str1 = jtf_StuId.getText();
                str2 = "select * from Student where Sno = " + str1;
                str3 = jtf_ManageId.getText();
                str4 = "select * from caretaker where where Cno = " + str3;
                if (jtf_StuId.getText().isEmpty() || jtf_BreakRuTime.getText().isEmpty() || jtf_ManageId.getText().isEmpty()
                        || jtf_BreakRuContent.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "请输入输入信息！", "提示消息", JOptionPane.WARNING_MESSAGE);
                } else {
                    try {
                        // 判断此用户是否存在
                        st = conn.createStatement();
                        if (!st.executeQuery(str2).next()) {
                            JOptionPane.showMessageDialog(null, "此学号不存在！", "提示消息", JOptionPane.WARNING_MESSAGE);
                        } else if (!st.executeQuery(str4).next()) {
                            JOptionPane.showMessageDialog(null, "此楼管号不存在！", "提示消息", JOptionPane.WARNING_MESSAGE);
                        } else {
                            ps = conn.prepareStatement("insert into Management values (?,?,?,?,?,?)");
                            if (jtf_StuId.getText().length() > 0) {
                                ps.setString(1, jtf_StuId.getText());
                            }
                            if (jtf_BreakRuTime.getText().length() > 0) {
                                ps.setString(2, jtf_BreakRuTime.getText());
                            }
                            if (jtf_ManageId.getText().length() > 0) {
                                ps.setString(3, jtf_ManageId.getText());
                            }
                            if (jtf_BreakRuContent.getText().length() > 0) {
                                ps.setString(4, jtf_BreakRuContent.getText());
                            }
                            if (jtf_DealWay.getText().length() > 0) {
                                ps.setString(5, jtf_DealWay.getText());
                            } else {
                                ps.setNull(5, 6);
                            }
                            if (jtf_Remark.getText().length() > 0) {
                                ps.setString(6, jtf_Remark.getText());
                            } else {
                                ps.setNull(6, 7);
                            }
                            ps.executeUpdate();
                            JOptionPane.showMessageDialog(null, "录入成功！", "提示消息", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        jb_Define.setBounds(200, 440, 113, 27);
        jP_Modify.add(jb_Define);
        //endregion

        //region 查询违纪

        jP_Total.add(jP_Query, "name_424844103865194");
        jP_Query.setLayout(null);

        JLabel jl_QuStuId = new JLabel("学    号：");
        jl_QuStuId.setBounds(110, 120, 80, 18);
        jP_Query.add(jl_QuStuId);

        JLabel jl_QuBrRuTime = new JLabel("违纪时间：");
        jl_QuBrRuTime.setBounds(110, 180, 80, 18);
        jP_Query.add(jl_QuBrRuTime);

        JLabel jl_QuManageId = new JLabel("楼管编号：");
        jl_QuManageId.setBounds(110, 240, 80, 18);
        jP_Query.add(jl_QuManageId);

        jtf_QuStuId = new JTextField();
        jtf_QuStuId.setBounds(190, 118, 180, 24);
        jP_Query.add(jtf_QuStuId);
        jtf_QuStuId.setColumns(10);

        jtf_QuBrRuTime = new JTextField();
        jtf_QuBrRuTime.setBounds(190, 178, 180, 24);
        jP_Query.add(jtf_QuBrRuTime);
        jtf_QuBrRuTime.setColumns(10);

        jtf_QuManageId = new JTextField();
        jtf_QuManageId.setBounds(190, 238, 180, 24);
        jP_Query.add(jtf_QuManageId);
        jtf_QuManageId.setColumns(10);

        JButton btnNewButton_4 = new JButton("确定");
        btnNewButton_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    ps = conn.prepareStatement(
                            "select Student.Sno,Sname,Caretaker.Cno,Cname,Mdate,Mmatter,Mway,Mnote"
                                    + " from Student,Caretaker,Management where Student.sno = Management.Sno "
                                    + "AND Management.Cno = Caretaker.Cno "
                                    + "AND Management.Sno LIKE ? AND Mdate LIKE ? AND Management.Cno LIKE ? order by Mdate");
                    if (jtf_QuStuId.getText().length() > 0) {
                        ps.setString(1, jtf_QuStuId.getText()+"%");
                    } else {
                        ps.setString(1, "%");
                    }
                    if (jtf_QuBrRuTime.getText().length() > 0) {
                        ps.setString(2, jtf_QuBrRuTime.getText()+"%");
                    } else {
                        ps.setString(2, "%");
                    }
                    if (jtf_QuManageId.getText().length() > 0) {
                        ps.setString(3, jtf_QuManageId.getText()+"%");
                    } else {
                        ps.setString(3, "%");
                    }
                    rs = ps.executeQuery();
                    new Mytable_Violations(rs);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }

        });

        btnNewButton_4.setBounds(200, 340, 113, 27);
        jP_Query.add(btnNewButton_4);
        //endregion
    }

}
