package main;


import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.io.Serializable;

public class Send_Sub_1_2 implements Serializable {
	private Connection con = null;
	private PreparedStatement pre = null;

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	public PreparedStatement getPre() {
		return pre;
	}

	public void setPre(PreparedStatement pre) {
		this.pre = pre;
	}

	public void sendProduct(Send_Sub_1 send_Sub_1) {
		String sql = "select NAME from users";
		try {
			send_Sub_1.setPre(con.prepareStatement(sql));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 2);
		}
	}
}