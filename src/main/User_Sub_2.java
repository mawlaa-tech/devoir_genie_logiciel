package main;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JTable;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import java.io.Serializable;

public class User_Sub_2 implements Serializable {
	private Connection con = null;
	private PreparedStatement pre = null;
	private ResultSet res = null;
	private javax.swing.JTable users;

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

	public ResultSet getRes() {
		return res;
	}

	public void setRes(ResultSet res) {
		this.res = res;
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
}