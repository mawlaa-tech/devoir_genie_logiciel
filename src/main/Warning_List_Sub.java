package main;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;
import javax.swing.JOptionPane;
import java.io.Serializable;

public class Warning_List_Sub implements Serializable {
	private Connection con = null;
	private PreparedStatement pre = null;
	private ResultSet res = null;
	private javax.swing.JTable close_to_expired;

	public void setCon(Connection con) {
		this.con = con;
	}

	public javax.swing.JTable getClose_to_expired() {
		return close_to_expired;
	}

	public void setClose_to_expired(javax.swing.JTable close_to_expired) {
		this.close_to_expired = close_to_expired;
	}

	public void show_List() {
		String sql = sql_7();
		try {
			pre = con.prepareStatement(sql);
			res = pre.executeQuery();
			close_to_expired.setModel(DbUtils.resultSetToTableModel(res));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 2);
		}
	}

	private String sql_7() {
		String sql;
		if (Pharmacy.ex == 1) {
			sql = "select NAME,BARCODE,EXPIRY,SELLING_PRICE,QUANTITY from drugs where BARCODE='"
					+ Pharmacy.almost_expired_bar + "' ";
		} else {
			sql = "select NAME,BARCODE,EXPIRY,SELLING_PRICE,QUANTITY from drugs where BARCODE='" + Pharmacy.expired_bar
					+ "' ";
		}
		return sql;
	}
}