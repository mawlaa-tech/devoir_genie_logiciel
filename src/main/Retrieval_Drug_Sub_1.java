package main;


import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import java.io.Serializable;

public class Retrieval_Drug_Sub_1 implements Serializable {
	private javax.swing.JTextField barcode;
	private javax.swing.JTextField drug_name;

	public javax.swing.JTextField getBarcode() {
		return barcode;
	}

	public void setBarcode(javax.swing.JTextField barcode) {
		this.barcode = barcode;
	}

	public javax.swing.JTextField getDrug_name() {
		return drug_name;
	}

	public void setDrug_name(javax.swing.JTextField drug_name) {
		this.drug_name = drug_name;
	}

	public void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		if (barcode.getText().equals("") || drug_name.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Complete Your Information", "Missing Information", 2);
		} else {
		}
	}

	public void retrieval_DrugProduct() {
		setBarcode(new javax.swing.JTextField());
		setDrug_name(new javax.swing.JTextField());
	}
}