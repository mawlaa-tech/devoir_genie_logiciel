package main;


import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.io.Serializable;

public class Send_Sub_2 implements Serializable {
	private Send_Sub_1 sendProduct = new Send_Sub_1();
	private ResultSet res = null;

	public Send_Sub_1 getSendProduct() {
		return sendProduct;
	}

	public void fill_username() {
		sendProduct.sendProduct();
		try {
			res = sendProduct.getPre().executeQuery();
			while (res.next()) {
				if (!(res.getString("NAME").equals(Pharmacy.username1.getText()))) {
					sendProduct.getMessage_to().addItem(res.getString("NAME"));
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 2);
		}
	}
}