package main;


import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import java.io.Serializable;

public class Company_Sub_2 implements Serializable {
	private Connection con = null;
	private PreparedStatement pre = null;
	private javax.swing.JTextArea address;
	private javax.swing.JTextField name;
	private javax.swing.JTextField phone;

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

	public javax.swing.JTextArea getAddress() {
		return address;
	}

	public void setAddress(javax.swing.JTextArea address) {
		this.address = address;
	}

	public javax.swing.JTextField getName() {
		return name;
	}

	public void setName(javax.swing.JTextField name) {
		this.name = name;
	}

	public javax.swing.JTextField getPhone() {
		return phone;
	}

	public void setPhone(javax.swing.JTextField phone) {
		this.phone = phone;
	}

	public void saveActionPerformed(java.awt.event.ActionEvent evt, Company_Sub_1 companyProduct) {
		companyProduct(companyProduct);
		if (name.getText().equals("") || address.getText().equals("") || phone.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Complete Company Information", "Missing Information", 2);
		} else {
			String sql = "insert into company (NAME,ADDRESS,PHONE) values ('" + name.getText() + "' ,'"
					+ address.getText() + "' ,'" + phone.getText() + "' )";
			try {
				int suring = JOptionPane.showConfirmDialog(null,
						"Are You Sure from this Information\n" + "Name : " + name.getText() + "\n" + "Address : "
								+ address.getText() + "\n" + "Phone : " + phone.getText()
								+ "\n\n Note : Company_Name Will not be updated");
				if (suring == 0) {
					pre = con.prepareStatement(sql);
					pre.execute();
					JOptionPane.showMessageDialog(null, "Company_Information has been Saved Successfully",
							"Success Operation", 1);
					clear();
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 2);
			}
		}
	}

	private void companyProduct(Company_Sub_1 companyProduct) {
		if (name.getText().equals("") || address.getText().equals("") || phone.getText().equals("")) {
		} else {
			companyProduct_(companyProduct);
		}
	}

	private void companyProduct_(Company_Sub_1 companyProduct) {
		try {
			int suring = JOptionPane.showConfirmDialog(null,
					"Are You Sure from this Information\n" + "Name : " + name.getText() + "\n" + "Address : "
							+ address.getText() + "\n" + "Phone : " + phone.getText()
							+ "\n\n Note : Company_Name Will not be updated");
			if (suring == 0) {
				companyProduct.companylist();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 2);
		}
	}

	public void updateActionPerformed(java.awt.event.ActionEvent evt, Company_Sub_1 companyProduct) {
		companyProduct__(companyProduct);
		if (name.getText().equals("") || address.getText().equals("") || phone.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Complete Company Information", "Missing Information", 2);
		} else {
			String sql = "update company set ADDRESS='" + address.getText() + "' ,PHONE='" + phone.getText()
					+ "' where NAME='" + name.getText() + "' ";
			try {
				pre = con.prepareStatement(sql);
				pre.execute();
				JOptionPane.showMessageDialog(null, "Company_Information has been Saved Successfully",
						"Success Operation", 1);
				clear();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 2);
			}
		}
	}

	private void companyProduct__(Company_Sub_1 companyProduct) {
		if (name.getText().equals("") || address.getText().equals("") || phone.getText().equals("")) {
		} else {
			companyProduct.companylist();
		}
	}

	public void deleteActionPerformed(java.awt.event.ActionEvent evt, Company_Sub_1 companyProduct) {
		companyProduct_2(companyProduct);
		if (name.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Enter Company_Name You Want to Delete", "Missing Information", 2);
		} else {
			String sql = "delete from company where NAME='" + name.getText() + "' ";
			int suring = JOptionPane.showConfirmDialog(null, "Are You Sure from Deleteing This Information");
			try {
				if (suring == 0) {
					pre = con.prepareStatement(sql);
					pre.execute();
					JOptionPane.showMessageDialog(null, "Company_Information has been Deleted Successfully",
							"Success Operation", 1);
					clear();
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 2);
			}
		}
	}

	private void companyProduct_2(Company_Sub_1 companyProduct) throws HeadlessException {
		if (name.getText().equals("")) {
		} else {
			companyProduct.companyProduct__1();
		}
	}

	public void clear() {
		name.setText("");
		address.setText("");
		phone.setText("");
	}
}