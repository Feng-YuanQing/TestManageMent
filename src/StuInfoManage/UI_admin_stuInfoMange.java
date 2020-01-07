package StuInfoManage;

import Login.UI_admin;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI_admin_stuInfoMange extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JDesktopPane table = null;
	private static String name;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws Exception {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI_admin_stuInfoMange frame = new UI_admin_stuInfoMange(name);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the frame.
	 */
	public UI_admin_stuInfoMange(String name) {
		this.name = name;
		setTitle("学生信息管理                                                    当前登录用户为：" + name);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 764, 564);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("\u57FA\u672C\u6570\u636E\u7EF4\u62A4");
		menuBar.add(mnNewMenu);
		
		JMenu mnNewMenu_2 = new JMenu("\u5B66\u751F\u4FE1\u606F\u7BA1\u7406");
		mnNewMenu.add(mnNewMenu_2);
		
		JMenuItem menuItem = new JMenuItem("\u5B66\u751F\u4FE1\u606F\u589E\u52A0");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StuManageView stuMV = new StuManageView();
				stuMV.setVisible(true);
				table.add(stuMV);
			}
		});
		mnNewMenu_2.add(menuItem);
		
		JMenuItem menuItem_4 = new JMenuItem("\u5B66\u751F\u4FE1\u606F\u67E5\u8BE2\u4E0E\u4FEE\u6539");
		menuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StuInquireView stuIV = new StuInquireView();
				stuIV.setVisible(true);
				table.add(stuIV);
			}
		});
		mnNewMenu_2.add(menuItem_4);
		
		JMenuItem menuItem_2 = new JMenuItem("\u8FD4\u56DE\u4E0A\u4E00\u7EA7");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
	            UI_admin frame = new UI_admin(name);
	            frame.setVisible(true);
			}
		});
		mnNewMenu.add(menuItem_2);
		
		JMenu menu = new JMenu("\u5173\u4E8E\u6211\u4EEC");
		menuBar.add(menu);
		
		JMenuItem menuItem_3 = new JMenuItem("\u5173\u4E8E\u521B\u4F5C\u56E2\u961F");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OurInterView interview = new OurInterView();
				interview.setVisible(true);
				table.add(interview);
			}
		});
		menu.add(menuItem_3);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		table = new JDesktopPane();
		table.setBackground(new Color(176, 224, 230));
		

		contentPane.add(table, BorderLayout.CENTER);
		this.setLocationRelativeTo(null);//JFrame居中
	}
}


