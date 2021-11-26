package main;


import java.awt.event.ActionEvent;
import java.io.Serializable;

public class Pharmacy_Sub_2 implements Serializable {
	private User user;

	public void setUser(User user) {
		this.user = user;
	}

	public void updateuserActionPerformed(java.awt.event.ActionEvent evt) {
		user.updateuserActionPerformed(evt);
	}

	public void deleteuserActionPerformed(java.awt.event.ActionEvent evt) {
		user.deleteuserActionPerformed(evt);
	}

	public void adduserActionPerformed(java.awt.event.ActionEvent evt) {
		user.adduserActionPerformed(evt);
	}
}