package main;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.Serializable;

public class Pharmacy_Sub_3 implements Serializable {
	private Pharmacy_Sub_1 pharmacyProduct = new Pharmacy_Sub_1();
	private Connection con = null;
	private PreparedStatement pre = null;
	private ResultSet res = null;
	private javax.swing.JPanel msgAlertDialog;
	private javax.swing.JLabel username;

	public Pharmacy_Sub_1 getPharmacyProduct() {
		return pharmacyProduct;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	public javax.swing.JPanel getMsgAlertDialog() {
		return msgAlertDialog;
	}

	public void setMsgAlertDialog(javax.swing.JPanel msgAlertDialog) {
		this.msgAlertDialog = msgAlertDialog;
	}

	public javax.swing.JLabel getUsername() {
		return username;
	}

	public void setUsername(javax.swing.JLabel username) {
		this.username = username;
	}

	public void btnShowMsgActionPerformed(java.awt.event.ActionEvent evt) {
		msgAlertDialog.setVisible(false);
		new Show_Message().setVisible(true);
		deleteMsg();
	}

	public void purchaseActionPerformed(java.awt.event.ActionEvent evt, Pharmacy pharmacy) {
		if (!username.getText().equals("Employee")) {
			pharmacy.enterpurchase();
		} else {
			String pass = JOptionPane.showInputDialog(
					"You are not allowed to check user Inforamtion\nTo get in please confirm Admin Password");
			String sql = "select NAME,PASSWORD from users where NAME='Ebrahem Samer' ";
			if (!pass.equals("")) {
				try {
					pre = con.prepareStatement(sql);
					res = pre.executeQuery();
					if (res.next()) {
						if (res.getString("PASSWORD").equals(pass)) {
							pharmacy.enterpurchase();
						} else if (pass.isEmpty()) {
							JOptionPane.showMessageDialog(null, "You must write admin Password", "Failed Access", 2);
						} else {
							JOptionPane.showMessageDialog(null, "Wrong Password", "Failed Access", 2);
						}
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 2);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Please type in Admin Password", "Failed Access", 2);
			}
		}
	}

	public void loginas() {
		String sql = "select ID,NAME from users where ID='" + Login.id.getText() + "' ";
		try {
			pre = con.prepareStatement(sql);
			res = pre.executeQuery();
			if (res.next()) {
				Pharmacy.username1.setText(res.getString("NAME"));
				if (res.getString("ID").equals("1")) {
					username.setText("Admin");
				} else {
					username.setText("Employee");
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 2);
		}
	}

	public void userBActionPerformed(java.awt.event.ActionEvent evt, Pharmacy pharmacy) {
		if (!username.getText().equals("Employee")) {
			pharmacy.enteruser();
		} else {
			String pass = JOptionPane.showInputDialog(null,
					"You are not allowed to check user Inforamtion\nTo get in please confirm Admin Password");
			String sql = "select NAME,PASSWORD from users where NAME='Syed Ibrahim' ";
			if (!pass.equals("")) {
				try {
					pre = con.prepareStatement(sql);
					res = pre.executeQuery();
					if (res.next()) {
						if (res.getString("PASSWORD").equals(pass)) {
							pharmacy.enteruser();
						} else if (pass.isEmpty()) {
							JOptionPane.showMessageDialog(null, "You must write admin Password", "Failed Access", 2);
						} else {
							JOptionPane.showMessageDialog(null, "Wrong Password", "Failed Access", 2);
						}
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 2);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Please type in Admin password", "Failed Access", 2);
			}
		}
	}

	public void login_as() {
		Date d = new Date();
		SimpleDateFormat dd = new SimpleDateFormat("hh:mm:ss");
		String sql = "insert into login (NAME,TYPE,DATE,TIME) values ('" + Pharmacy.username1.getText() + "' ,'"
				+ username.getText() + "' ,'" + Pharmacy.today.getText() + "' ,'" + dd.format(d) + "' )";
		try {
			pre = con.prepareStatement(sql);
			pre.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 2);
		}
	}

	public void alert_message() {
		String sql = "select MESSAGE_TO,MESSAGE_FROM,MESSAGE_TEXT from message_history where MESSAGE_TO='"
				+ Pharmacy.username1.getText() + "' ";
		try {
			pre = con.prepareStatement(sql);
			res = pre.executeQuery();
			if (res.next()) {
				Pharmacy.to = res.getString("MESSAGE_TO");
				Pharmacy.from = res.getString("MESSAGE_FROM");
				Pharmacy.text = res.getString("MESSAGE_TEXT");
				msgAlertDialog.setVisible(true);
			} else {
				msgAlertDialog.setVisible(false);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 2);
		}
	}

	public void update_to_expired() {
		String sql = "update drugs set EXPIRY='Expired' where BARCODE='" + Pharmacy.expired_bar + "' ";
		try {
			pre = con.prepareStatement(sql);
			pre.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 2);
		}
	}

	public void deleteMsg() {
		String sql = "delete from message_history";
		try {
			pre = con.prepareStatement(sql);
			pre.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 2);
		}
	}

	public void warning() {
		int ex_year;
		int current_year;
		int ex_month;
		int ex_day;
		int current_month;
		int current_day;
		String sql = "select BARCODE,EXPIRATION_DATE from drugs";
		try {
			pre = con.prepareStatement(sql);
			res = pre.executeQuery();
			while (res.next()) {
				Warning warning = new Warning();
				ex_year = Integer.parseInt(res.getString("EXPIRATION_DATE").split("-")[2]);
				ex_month = Integer.parseInt(res.getString("EXPIRATION_DATE").split("-")[1]);
				ex_day = Integer.parseInt(res.getString("EXPIRATION_DATE").split("-")[0]);
				current_year = Integer.parseInt(pharmacyProduct.getDd().format(pharmacyProduct.getD()).split("-")[2]);
				current_month = Integer.parseInt(pharmacyProduct.getDd().format(pharmacyProduct.getD()).split("-")[1]);
				current_day = Integer.parseInt(pharmacyProduct.getDd().format(pharmacyProduct.getD()).split("-")[0]);
				if (ex_year == current_year) {
					if (ex_month - current_month == 2) {
						Pharmacy.ex = 1;
						Pharmacy.almost_expired_bar = res.getString("BARCODE");
						warning.setVisible(true);
					} else if (ex_month == current_month) {
						if (ex_day == current_day) {
							Pharmacy.ex = 0;
							Pharmacy.expired_bar = res.getString("BARCODE");
							update_to_expired();
							warning.setVisible(true);
						}
					}
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 2);
		}
	}
}