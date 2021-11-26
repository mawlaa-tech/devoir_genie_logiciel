package main;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;
import javax.swing.JOptionPane;
import java.io.Serializable;

public class Expired_Drug_Sub_1 implements Serializable {
	private Connection con = null;
	private PreparedStatement pre = null;
	private ResultSet res = null;
	private javax.swing.JTable expired_list;

	public void setCon(Connection con) {
		this.con = con;
	}

	public javax.swing.JTable getExpired_list() {
		return expired_list;
	}

	public void setExpired_list(javax.swing.JTable expired_list) {
		this.expired_list = expired_list;
	}

	public void show_table() {
		String sql = "select NAME,BARCODE,PRODUCTION_DATE,EXPIRATION_DATE, QUANTITY,EXPIRY from drugs where EXPIRY='Expired' ";
		try {
			pre = con.prepareStatement(sql);
			res = pre.executeQuery();
			expired_list.setModel(DbUtils.resultSetToTableModel(res));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 2);
		}
	}
}