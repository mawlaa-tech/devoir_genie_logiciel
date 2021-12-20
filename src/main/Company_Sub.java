package main;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import java.io.Serializable;

public class Company_Sub implements Serializable {
	private Company_Sub_2 companyProductProduct = new Company_Sub_2();
	private ResultSet res = null;
	private javax.swing.JTable company_list;
	public void setCon(Connection con) {
		companyProductProduct.setCon(con);
	}

	public javax.swing.JTextArea getAddress() {
		return companyProductProduct.getAddress();
	}

	public void setAddress(javax.swing.JTextArea address) {
		companyProductProduct.setAddress(address);
	}

	public javax.swing.JTable getCompany_list() {
		return company_list;
	}

	public void setCompany_list(javax.swing.JTable company_list) {
		this.company_list = company_list;
	}

	public javax.swing.JTextField getName() {
		return companyProductProduct.getName();
	}

	public void setName(javax.swing.JTextField name) {
		companyProductProduct.setName(name);
	}

	public javax.swing.JTextField getPhone() {
		return companyProductProduct.getPhone();
	}

	public void setPhone(javax.swing.JTextField phone) {
		companyProductProduct.setPhone(phone);
	}

	public void saveActionPerformed(java.awt.event.ActionEvent evt) {
		companyProductProduct.saveActionPerformed(evt, this);
	}

	public void updateActionPerformed(java.awt.event.ActionEvent evt) {
		companyProductProduct.updateActionPerformed(evt, this);
	}

	public void deleteActionPerformed(java.awt.event.ActionEvent evt) {
		companyProductProduct.deleteActionPerformed(evt, this);
	}

	public void clear() {
		companyProductProduct.clear();
	}

	public void companylist() {
		companyProductProduct.company();
		try {
			res = companyProductProduct.getPre().executeQuery();
			company_list.setModel(DbUtils.resultSetToTableModel(res));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 2);
		}
	}

	public void companyProduct__1() throws HeadlessException {
		int suring = JOptionPane.showConfirmDialog(null, "Are You Sure from Deleteing This Information");
		if (suring == 0) {
			companylist();
		}
	}
}