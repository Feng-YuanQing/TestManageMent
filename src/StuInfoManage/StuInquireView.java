package StuInfoManage;

import DataBaseManage.DBManage;
import Login.StringUtil;
import dao.StudentManageDao;
import model.StudentManage;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;


public class StuInquireView extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable stuinfo;
	private DBManage dbstu = new DBManage();
	private StudentManageDao smd = new StudentManageDao();
	private JTextField searchtxt;
	private JTextField snotxt;
	private JTextField snametxt;
	private JTextField ssextxt;
	private JTextField steltxt;
	private JTextField sclasstxt;
	private JTextField sidtxt;
	private JTextField sagetxt;
	private JTextField smajortxt;
	private JTextField snativetxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StuInquireView frame = new StuInquireView();
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
	public StuInquireView() {
		getContentPane().setBackground(new Color(173, 216, 230));
		setIconifiable(true);
		setClosable(true);
		setRootPaneCheckingEnabled(false);
		setTitle("\u67E5\u8BE2\u4E0E\u7BA1\u7406");
		setBounds(100, 100, 1141, 541);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel = new JLabel("\u8981\u67E5\u8BE2\u7684\u5B66\u53F7\uFF1A");
		
		searchtxt = new JTextField();
		searchtxt.setColumns(10);
		
		JButton btnNewButton = new JButton("\u67E5\u8BE2");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search(e);
			}
		});
		btnNewButton.setIcon(new ImageIcon(StuInquireView.class.getResource("/\u67E5\u8BE2-default.png")));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(176, 224, 230));
		panel.setBorder(new TitledBorder(null, "\u5B66\u751F\u4FE1\u606F\u7BA1\u7406", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(97)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 933, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addGap(31)
							.addComponent(searchtxt, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
							.addGap(86)
							.addComponent(btnNewButton))
						.addComponent(scrollPane, Alignment.TRAILING))
					.addGap(95))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(36)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(searchtxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel)
						.addComponent(btnNewButton))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)
					.addGap(49))
		);
		
		JLabel label = new JLabel("\u5B66\u53F7\uFF1A");
		
		snotxt = new JTextField();
		snotxt.setEditable(false);
		snotxt.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\u59D3\u540D\uFF1A");
		
		snametxt = new JTextField();
		snametxt.setColumns(10);
		
		JLabel label_1 = new JLabel("\u6027\u522B\uFF1A");
		
		ssextxt = new JTextField();
		ssextxt.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("\u7535\u8BDD\uFF1A");
		
		steltxt = new JTextField();
		steltxt.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("\u73ED\u7EA7\uFF1A");
		
		sclasstxt = new JTextField();
		sclasstxt.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("\u8EAB\u4EFD\u8BC1\u53F7\uFF1A");
		
		JLabel lblNewLabel_6 = new JLabel("\u5E74\u9F84\uFF1A");
		
		JLabel lblNewLabel_7 = new JLabel("\u4E13\u4E1A\uFF1A");
		
		JLabel lblNewLabel_8 = new JLabel("\u7C4D\u8D2F\uFF1A");
		
		sidtxt = new JTextField();
		sidtxt.setColumns(10);
		
		sagetxt = new JTextField();
		sagetxt.setColumns(10);
		
		smajortxt = new JTextField();
		smajortxt.setColumns(10);
		
		snativetxt = new JTextField();
		snativetxt.setColumns(40);
		
		JButton btnNewButton_1 = new JButton("\u4FEE\u6539");
		btnNewButton_1.setIcon(new ImageIcon(StuInquireView.class.getResource("/\u4FEE\u6539(1).png")));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stuUpdate(e);
			}
		});
		
		JButton btnNewButton_2 = new JButton("\u5220\u9664");
		btnNewButton_2.setIcon(new ImageIcon(StuInquireView.class.getResource("/\u5220\u9664.png")));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				studel(e);
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
							.addComponent(label)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(snotxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(snametxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnNewButton_1)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblNewLabel_4)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(sclasstxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblNewLabel_5)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(sidtxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_6)
								.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(ssextxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(sagetxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_2)
								.addComponent(lblNewLabel_7)))
						.addComponent(btnNewButton_2))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(smajortxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblNewLabel_8)
							.addPreferredGap(ComponentPlacement.RELATED, 175, Short.MAX_VALUE)
							.addComponent(snativetxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(steltxt, GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE))
					.addGap(96))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(snotxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(snametxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1)
						.addComponent(ssextxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1)
						.addComponent(lblNewLabel_2)
						.addComponent(steltxt, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_4)
						.addComponent(sclasstxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_5)
						.addComponent(sidtxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(snativetxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_6)
						.addComponent(sagetxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_7)
						.addComponent(smajortxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_8))
					.addGap(32)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton_2))
					.addContainerGap(54, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		stuinfo = new JTable();
		stuinfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				stuMousePressed(e);
			}
		});
		stuinfo.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Sno", "Sname", "Ssex", "Stel", "Sclass", "Sid", "Sage", "Smajor", "Snative"
			}
		));
		scrollPane.setViewportView(stuinfo);
		getContentPane().setLayout(groupLayout);

		this.fillTable(new StudentManage());
	}
	private void studel(ActionEvent evt) {
		String Sno = this.snotxt.getText();
		if(StringUtil.isEmpty(Sno)) {
			JOptionPane.showMessageDialog(null, "请选择要删除的记录");
			return;
		}
		int n = JOptionPane.showConfirmDialog(null, "确定要删除吗？");
		if(n==0) {
			Connection con = null;
			try {
				con = DBManage.CreatConnection();
				int delnum = StudentManageDao.delete(con, Sno);
				if(delnum==1)
				{
					JOptionPane.showMessageDialog(null, "删除成功");
					this.resetValue();
					this.fillTable(new StudentManage());//刷新表
				}
				else {
					JOptionPane.showMessageDialog(null, "删除失败");
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				try {
					dbstu.closeCon(con);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private void stuUpdate(ActionEvent evt) {
		String Sno = this.snotxt.getText();
		String Sname = this.snametxt.getText();
		String Ssex = this.ssextxt.getText();
		String Stel = this.steltxt.getText();
		String Sclass = this.sclasstxt.getText();
		String Sid = this.sidtxt.getText();
		String Sage = this.sagetxt.getText();
		String Smajor = this.smajortxt.getText();
		String Snative = this.snativetxt.getText();
		if(StringUtil.isEmpty(Sno)) {
			JOptionPane.showMessageDialog(null, "请选择要修改的记录");
			return;
		}
		StudentManage sm1 = new StudentManage(Sno,Sname,Ssex,Stel,Sclass,Sid,Sage,Smajor,Snative);
		Connection con = null;
		try {
			con = DBManage.CreatConnection();
			int num= StudentManageDao.update(con, sm1);
			if(num==1) {
				JOptionPane.showMessageDialog(null, "修改成功");
				this.resetValue();
				this.fillTable(new StudentManage());//刷新表
			}
			else {
				JOptionPane.showMessageDialog(null, "修改失败");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				dbstu.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	//表格行点击事件
	private void stuMousePressed(MouseEvent evt) {
		int row = stuinfo.getSelectedRow();
		snotxt.setText((String)stuinfo.getValueAt(row, 0));
		snametxt.setText((String)stuinfo.getValueAt(row, 1));
		ssextxt.setText((String)stuinfo.getValueAt(row, 2));
		steltxt.setText((String)stuinfo.getValueAt(row, 3));
		sclasstxt.setText((String)stuinfo.getValueAt(row, 4));
		sidtxt.setText((String)stuinfo.getValueAt(row, 5));
		sagetxt.setText((String)stuinfo.getValueAt(row, 6));
		smajortxt.setText((String)stuinfo.getValueAt(row, 7));
		snativetxt.setText((String)stuinfo.getValueAt(row, 8));
	}

	//搜索
	private void search(ActionEvent e) {
		String searchSno = this.searchtxt.getText();
		StudentManage sm = new StudentManage();
		sm.setSno(searchSno);
		this.fillTable(sm);
	}

	//初始化表格
	private void fillTable(StudentManage sm)
	{
		DefaultTableModel dtm =(DefaultTableModel)stuinfo.getModel();
		dtm.setRowCount(0);//清空表
		Connection con = null;
		try {
			con = DBManage.CreatConnection();
			ResultSet rs = StudentManageDao.inquire(con, sm);
			while(rs.next()) {
				Vector v = new Vector();
				v.add(rs.getString("Sno"));
				v.add(rs.getString("Sname"));
				v.add(rs.getString("Ssex"));
				v.add(rs.getString("Stel"));
				v.add(rs.getString("Sclass"));
				v.add(rs.getString("Sid"));
				v.add(rs.getString("Sage"));
				v.add(rs.getString("Smajor"));
				v.add(rs.getString("Snative"));
				dtm.addRow(v);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				dbstu.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private void resetValue()
	{
		this.snotxt.setText("");
		this.snametxt.setText("");
		this.ssextxt.setText("");
		this.steltxt.setText("");
		this.sclasstxt.setText("");
		this.sidtxt.setText("");
		this.sagetxt.setText("");
		this.smajortxt.setText("");
		this.snativetxt.setText("");
	}
}
