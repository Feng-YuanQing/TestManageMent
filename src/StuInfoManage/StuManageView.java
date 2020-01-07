package StuInfoManage;

import DataBaseManage.DBManage;
import Login.StringUtil;
import dao.StudentManageDao;
import model.StudentManage;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class StuManageView extends JInternalFrame {
	/**
	 *
	 */
	public JPanel contentPane;
	private static final long serialVersionUID = 1L;
	private JTextField nametxt;
	private JTextField snotxt;
	private JTextField sextxt;
	private JTextField teltxt;
	private JTextField classtxt;
	private JTextField idtxt;
	private JTextField agetxt;
	private JTextField majortxt;
	private JTextField citytxt;
	private DBManage dbstu = new DBManage();



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StuManageView frame = new StuManageView();
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
	public StuManageView() {
		getContentPane().setBackground(new Color(173, 216, 230));
		setClosable(true);
		setTitle("\u589E\u52A0\u5B66\u751F\u4FE1\u606F");
		setBounds(100, 100, 508, 515);

		JLabel lblNewLabel = new JLabel("\u59D3\u540D");

		nametxt = new JTextField();
		nametxt.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("\u5B66\u53F7");

		snotxt = new JTextField();
		snotxt.setColumns(10);

		JButton btnNewButton = new JButton("\u786E\u5B9A");
		btnNewButton.setIcon(new ImageIcon(StuManageView.class.getResource("/\u786E\u5B9A.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stuManageAdd(e);
			}
		});

		JButton btnNewButton_1 = new JButton("\u91CD\u7F6E");
		btnNewButton_1.setIcon(new ImageIcon(StuManageView.class.getResource("/\u91CD\u7F6E.png")));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset(e);
			}
		});

		JLabel label = new JLabel("\u6027\u522B");

		sextxt = new JTextField();
		sextxt.setColumns(10);

		JLabel label_1 = new JLabel("\u7535\u8BDD");

		JLabel lblId = new JLabel("\u73ED\u7EA7");

		teltxt = new JTextField();
		teltxt.setColumns(10);

		classtxt = new JTextField();
		classtxt.setColumns(10);

		JLabel lblId_1 = new JLabel("\u8EAB\u4EFD\u8BC1\u53F7");

		JLabel label_4 = new JLabel("\u5E74\u9F84");

		JLabel label_3 = new JLabel("\u4E13\u4E1A");

		JLabel label_5 = new JLabel("\u57CE\u5E02");

		idtxt = new JTextField();
		idtxt.setColumns(10);

		agetxt = new JTextField();
		agetxt.setColumns(10);

		majortxt = new JTextField();
		majortxt.setColumns(10);

		citytxt = new JTextField();
		citytxt.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(StuManageView.class.getResource("/timg.jpg")));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addContainerGap()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
												.addComponent(lblId_1, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
												.addComponent(lblNewLabel_1)
												.addComponent(lblNewLabel)
												.addComponent(label, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
												.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblId, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
												.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
												.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
												.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
										.addComponent(btnNewButton))
								.addGap(11)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
												.addComponent(majortxt, GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
												.addComponent(agetxt, GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
												.addComponent(snotxt)
												.addComponent(nametxt)
												.addComponent(classtxt, GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
												.addComponent(idtxt, GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
												.addComponent(citytxt, GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
												.addComponent(sextxt)
												.addComponent(teltxt, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE))
										.addComponent(btnNewButton_1))
								.addGap(18)
								.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 237, Short.MAX_VALUE)
								.addContainerGap())
		);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addGap(51)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblNewLabel_1)
										.addComponent(snotxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblNewLabel)
										.addComponent(nametxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(label)
										.addComponent(sextxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(label_1)
										.addComponent(teltxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblId)
										.addComponent(classtxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblId_1)
										.addComponent(idtxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(label_4)
										.addComponent(agetxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(label_3)
										.addComponent(majortxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(label_5)
										.addComponent(citytxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(35)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnNewButton_1))
								.addContainerGap(44, Short.MAX_VALUE))
						.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 474, Short.MAX_VALUE)
		);
		getContentPane().setLayout(groupLayout);
	}


	private void stuManageAdd(ActionEvent evt) {
		String Sno = this.snotxt.getText();
		String Sname = this.nametxt.getText();
		String Ssex = this.sextxt.getText();
		String Stel = this.teltxt.getText();
		String Sclass = this.classtxt.getText();
		String Sid = this.idtxt.getText();
		String Sage = this.agetxt.getText();
		String Smajor = this.majortxt.getText();
		String Snative = this.citytxt.getText();
		if(StringUtil.isEmpty(Sno)) {
			JOptionPane.showMessageDialog(null, "学号不能为空！");
			return;
		}
		StudentManage sm = new StudentManage(Sno,Sname,Ssex,Stel,Sclass,Sid,Sage,Smajor,Snative);
		Connection con = null;
		try {
			con = DBManage.CreatConnection();
			int n = StudentManageDao.add(con,sm);
			if(n==1) {
				JOptionPane.showMessageDialog(null, "添加成功！");
				resetValue();
			}
			else {
				JOptionPane.showMessageDialog(null, "添加失败");
			}
		}catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "添加失败！");
		}finally {
			try {
				dbstu.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void reset(ActionEvent evt) {
		this.resetValue();
	}

	private void resetValue()
	{
		this.snotxt.setText("");
		this.nametxt.setText("");
		this.sextxt.setText("");
		this.teltxt.setText("");
		this.classtxt.setText("");
		this.idtxt.setText("");
		this.agetxt.setText("");
		this.majortxt.setText("");
		this.citytxt.setText("");
	}
}

