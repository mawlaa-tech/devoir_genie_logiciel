package main;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import java.awt.event.MouseEvent;
import java.io.Serializable;

public class User_Sub implements Serializable {
	private Connection con = null;
	private PreparedStatement pre = null;
	private ResultSet res = null;
	private String t;
	private javax.swing.JTextArea address;
	private javax.swing.JTextField name;
	private javax.swing.JPasswordField password;
	private javax.swing.JTextField phone;
	private javax.swing.JTextField salary;
	private javax.swing.JTable users;

	public void setCon(Connection con) {
		this.con = con;
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

	public javax.swing.JPasswordField getPassword() {
		return password;
	}

	public void setPassword(javax.swing.JPasswordField password) {
		this.password = password;
	}

	public javax.swing.JTextField getPhone() {
		return phone;
	}

	public void setPhone(javax.swing.JTextField phone) {
		this.phone = phone;
	}

	public javax.swing.JTextField getSalary() {
		return salary;
	}

	public void setSalary(javax.swing.JTextField salary) {
		this.salary = salary;
	}

	public javax.swing.JTable getUsers() {
		return users;
	}

	public void setUsers(javax.swing.JTable users) {
		this.users = users;
	}

	public void deleteuserActionPerformed(java.awt.event.ActionEvent evt) {
		if (User.id.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Enter ID You Want to delete", "Missing Information", 2);
		} else if (checkid()) {
			String sql = "delete from users where ID='" + User.id.getText() + "' ";
			try {
				int check = JOptionPane.showConfirmDialog(null, "Are You Sure From Deleting This User");
				if (check == 0) {
					pre = con.prepareStatement(sql);
					pre.execute();
					JOptionPane.showMessageDialog(null, "User has been Deleted Successfully", "Success", 1);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 2);
			}
			refresh();
		} else {
			JOptionPane.showMessageDialog(null, "User is not Found", "Wrong Operation", 2);
		}
	}

	public boolean checkid() {
		boolean check = false;
		String sql = "select ID from users where ID='" + User.id.getText() + "' ";
		try {
			pre = con.prepareStatement(sql);
			res = pre.executeQuery();
			if (res.next()) {
				check = true;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 2);
		}
		return check;
	}

	public void refresh() {
		String sql = "select ID,NAME,DOB,ADDRESS,PHONE,SALARY from users";
		try {
			pre = con.prepareStatement(sql);
			res = pre.executeQuery();
			users.setModel(DbUtils.resultSetToTableModel(res));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 2);
		}
	}

	public void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
		User.id.setText("");
		password.setText("");
		name.setText("");
		address.setText("");
		phone.setText("");
		salary.setText("");
		User.day.setSelectedIndex(0);
		User.month.setSelectedIndex(0);
		User.year.setSelectedIndex(0);
	}

	public void clear() {
		User.id.setText("");
		name.setText("");
		address.setText("");
		phone.setText("");
		salary.setText("");
		password.setText("");
		User.day.setSelectedIndex(0);
		User.month.setSelectedIndex(0);
		User.year.setSelectedIndex(0);
	}

	public void adduserActionPerformed(java.awt.event.ActionEvent evt) {
		if (User.id.getText().equals("") || name.getText().equals("") || User.day.getSelectedIndex() == 0
				|| User.month.getSelectedIndex() == 0 || User.year.getSelectedIndex() == 0
				|| address.getText().equals("") || phone.getText().equals("") || password.getText().equals("")
				|| salary.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Complete Your Information", "Missing Information", 2);
		} else if (!checkid()) {
			if (password.getText().length() < 6) {
				JOptionPane.showMessageDialog(null, "User_Password Should be at least 6 Characters", "Wrong Operation",
						2);
			} else {
				String sql = "insert into users (ID,NAME,DOB,ADDRESS,PHONE,SALARY,PASSWORD) values ('"
						+ User.id.getText() + "','" + name.getText() + "' , '" + User.day.getSelectedItem() + "-"
						+ User.month.getSelectedItem() + "-" + User.year.getSelectedItem() + "' ,'" + address.getText()
						+ "' ,'" + phone.getText() + "' ,'" + salary.getText() + "' ,'" + password.getText() + "')";
				try {
					pre = con.prepareStatement(sql);
					pre.execute();
					JOptionPane.showMessageDialog(null, "User has been Added Successfully", "Success", 1);
					clear();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 2);
				}
				refresh();
			}
		} else {
			JOptionPane.showMessageDialog(null, "User is Already inserted", "Wrong Operation", 2);
		}
	}

	public void updateuserActionPerformed(java.awt.event.ActionEvent evt) {
		if (User.id.getText().equals("") || name.getText().equals("") || User.day.getSelectedIndex() == 0
				|| User.month.getSelectedIndex() == 0 || User.year.getSelectedIndex() == 0
				|| address.getText().equals("") || phone.getText().equals("") || salary.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Complete Your Information", "Missing Information", 2);
		} else {
			String sql = "update users set NAME='" + name.getText() + "' ,ADDRESS='" + address.getText() + "' ,PHONE='"
					+ phone.getText() + "' ,SALARY='" + salary.getText() + "' where ID='" + User.id.getText() + "' ";
			try {
				pre = con.prepareStatement(sql);
				pre.execute();
				JOptionPane.showMessageDialog(null, "User has been added Successfully", "Success", 1);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 2);
			}
			refresh();
		}
	}

	public void usersMouseClicked(java.awt.event.MouseEvent evt) {
		int row = users.getSelectedRow();
		t = users.getModel().getValueAt(row, 0).toString();
		String sql = "select * from users where ID='" + t + "' ";
		try {
			pre = con.prepareStatement(sql);
			res = pre.executeQuery();
			if (res.next()) {
				String Id = res.getString("ID");
				User.id.setText(Id);
				String Name = res.getString("NAME");
				name.setText(Name);
				String Day = res.getString("DOB").split("-")[0];
				User.day.setSelectedItem(Day);
				String Month = res.getString("DOB").split("-")[1];
				User.month.setSelectedItem(Month);
				String Year = res.getString("DOB").split("-")[2];
				User.year.setSelectedItem(Year);
				String Address = res.getString("ADDRESS");
				address.setText(Address);
				String Phone = res.getString("PHONE");
				phone.setText(Phone);
				String Salary = res.getString("SALARY");
				salary.setText(Salary);
				String Password = res.getString("PASSWORD");
				password.setText(Password);
				password.setEchoChar('*');
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 2);
		}
	}
}