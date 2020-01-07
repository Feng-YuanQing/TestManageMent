package Login;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import StuInfoManage.*;
import DailyManage.ui_admin_DailyManage_2;
import DormInfoManage.UI_admin_dromInfoManage_2;
import AccomInfoManage.UI_admin_accomInfoManage;

class HomePanel extends JPanel {
    ImageIcon icon;
    Image img;

    public HomePanel() {

        icon = new ImageIcon(getClass().getResource("/back3.jpg"));
        img = icon.getImage();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 下面这行是为了背景图片可以跟随窗口自行调整大小，可以自己设置成固定大小
        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}

public class UI_admin extends JFrame implements ActionListener {

    public JPanel contentPane;
    String name;
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UI_admin frame = new UI_admin();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // 带参数的构造方法
    public UI_admin(String str) {
        name = str;
        init();
    }
    // 不带参数的构造方法
    public UI_admin() {
        init();
    }

    void init() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(764, 564);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);
        setTitle("当前登录的用户id为：" + name);
        JPanel panel = new HomePanel();
        panel.setBounds(0, 0, 758, 529);
        contentPane.add(panel);
        panel.setLayout(null);

        JButton jb_StuInfo = new JButton("学生信息管理");
        jb_StuInfo.addActionListener(this);
        jb_StuInfo.setSize(new Dimension(2, 2));
        jb_StuInfo.setBounds(174, 200, 133, 50);
        panel.add(jb_StuInfo);

        JButton jb_DormInfo = new JButton("宿舍信息管理");
        jb_DormInfo.addActionListener(this);
        jb_DormInfo.setBounds(446, 200, 133, 50);
        panel.add(jb_DormInfo);

        JButton jb_DormM = new JButton("住宿信息管理");
        jb_DormM.addActionListener(this);
        jb_DormM.setBounds(174, 293, 133, 50);
        panel.add(jb_DormM);

        JButton jb_DailyM = new JButton("日常信息管理");
        jb_DailyM.addActionListener(this);

        jb_DailyM.setBounds(446, 293, 133, 50);
        panel.add(jb_DailyM);

        JButton jb_Modify = new JButton("修改密码");
        jb_Modify.addActionListener(this);
        jb_Modify.setBounds(174, 387, 133, 50);
        panel.add(jb_Modify);

        JButton jb_Exit = new JButton("退出系统");
        jb_Exit.addActionListener(this);
        jb_Exit.setBounds(446, 387, 133, 50);
        panel.add(jb_Exit);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand() == "学生信息管理") {
            dispose();
            UI_admin_stuInfoMange frame = new UI_admin_stuInfoMange(name);
            frame.setVisible(true);

        }
        if (e.getActionCommand() == "宿舍信息管理") {
            dispose();
            UI_admin_dromInfoManage_2 frame = new UI_admin_dromInfoManage_2(name);
            frame.setVisible(true);
        }
        if (e.getActionCommand() == "住宿信息管理") {
            dispose();
            UI_admin_accomInfoManage frame = new UI_admin_accomInfoManage(name);
            frame.setVisible(true);
        }
        if (e.getActionCommand() == "日常信息管理") {
            dispose();
            ui_admin_DailyManage_2 frame = new ui_admin_DailyManage_2(name);
            frame.setVisible(true);
        }
        if (e.getActionCommand() == "修改密码") {
            UI_admin_changePassword uacp = new UI_admin_changePassword(name);
        }
        if (e.getActionCommand() == "退出系统") {
            JOptionPane.showMessageDialog(null, "感谢您的使用！", "提示消息", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }
}
