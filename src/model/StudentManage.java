package model;

public class StudentManage {
	private String Sno;
	private String Sname;
	private String Ssex;
	private String Stel;
	private String Sclass;
	private String Sid;
	private String Sage;
	private String Smajor;
	private String Snative;
	
	public StudentManage() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public StudentManage(String sno, String sname, String ssex, String stel, String sclass,
                         String sid, String sage, String smajor, String snative) {
		super();
		Sno = sno;
		Sname = sname;
		Ssex = ssex;
		Stel = stel;
		Sclass = sclass;
		Sid = sid;
		Sage = sage;
		Smajor = smajor;
		Snative = snative;
	}

	public String getSno() {
		return Sno;
	}

	public void setSno(String sno) {
		Sno = sno;
	}

	public String getSname() {
		return Sname;
	}

	public void setSname(String sname) {
		Sname = sname;
	}

	public String getSsex() {
		return Ssex;
	}

	public void setSsex(String ssex) {
		Ssex = ssex;
	}

	public String getStel() {
		return Stel;
	}

	public void setStel(String stel) {
		Stel = stel;
	}
	
	public String getSclass() {
		return Sclass;
	}

	public void setSclass(String sclass) {
		Sclass = sclass;
	}

	public String getSid() {
		return Sid;
	}

	public void setSid(String sid) {
		Sid = sid;
	}

	public String getSage() {
		return Sage;
	}

	public void setSage(String sage) {
		Sage = sage;
	}

	public String getSmajor() {
		return Smajor;
	}

	public void setSmajor(String smajor) {
		Smajor = smajor;
	}

	public String getSnative() {
		return Snative;
	}

	public void setSnative(String snative) {
		Snative = snative;
	}
	

}
