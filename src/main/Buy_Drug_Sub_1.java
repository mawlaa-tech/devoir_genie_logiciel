package main;


import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.io.Serializable;

public class Buy_Drug_Sub_1 implements Serializable {
	private javax.swing.JButton makedeal;
	private javax.swing.JButton update;

	public javax.swing.JButton getMakedeal() {
		return makedeal;
	}

	public void setMakedeal(javax.swing.JButton makedeal) {
		this.makedeal = makedeal;
	}

	public javax.swing.JButton getUpdate() {
		return update;
	}

	public void setUpdate(javax.swing.JButton update) {
		this.update = update;
	}

	public void buydrugActionPerformed(java.awt.event.ActionEvent evt, Buy_Drug buy_Drug) {
		buy_Drug.setVisible(true);
		this.makedeal.setEnabled(true);
		this.update.setEnabled(false);
	}

	public void updatedealsActionPerformed(java.awt.event.ActionEvent evt, Buy_Drug buy_Drug) {
		buy_Drug.setVisible(true);
		this.update.setEnabled(true);
		this.makedeal.setEnabled(false);
	}

	public void buy_DrugProduct() {
		setMakedeal(new javax.swing.JButton());
		setUpdate(new javax.swing.JButton());
	}
}