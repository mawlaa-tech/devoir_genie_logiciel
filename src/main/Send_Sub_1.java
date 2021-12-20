package main;


import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import java.io.Serializable;

public class Send_Sub_1 implements Serializable {
	private Send_Sub_1_2 send_Sub_1_2 = new Send_Sub_1_2();
	private javax.swing.JTextField message_from;
	private javax.swing.JTextArea message_text;
	private javax.swing.JComboBox<String> message_to;

	public Connection getCon() {
		return send_Sub_1_2.getCon();
	}

	public void setCon(Connection con) {
		send_Sub_1_2.setCon(con);
	}

	public PreparedStatement getPre() {
		return send_Sub_1_2.getPre();
	}

	public void setPre(PreparedStatement pre) {
		send_Sub_1_2.setPre(pre);
	}

	public javax.swing.JTextField getMessage_from() {
		return message_from;
	}

	public void setMessage_from(javax.swing.JTextField message_from) {
		this.message_from = message_from;
	}

	public javax.swing.JTextArea getMessage_text() {
		return message_text;
	}

	public void setMessage_text(javax.swing.JTextArea message_text) {
		this.message_text = message_text;
	}

	public javax.swing.JComboBox<String> getMessage_to() {
		return message_to;
	}

	public void setMessage_to(javax.swing.JComboBox<String> message_to) {
		this.message_to = message_to;
	}

	public void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		if (message_from.getText().equals("") || message_to.getSelectedIndex() == 0
				|| message_text.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Complete Your Message_Information", "Missing Information", 2);
		} else if (message_from.getText().equals(message_to.getSelectedItem())) {
			JOptionPane.showMessageDialog(null, "You do not need to send message to yourself", "Failed Operation", 2);
		} else {
			String sql = "insert into inbox (MESSAGE_FROM,MESSAGE_TO,MESSAGE_TEXT) values ('" + message_from.getText()
					+ "' ,'" + message_to.getSelectedItem() + "' ,'" + message_text.getText() + "' )";
			try {
				send_Sub_1_2.setPre(send_Sub_1_2.getCon().prepareStatement(sql));
				send_Sub_1_2.getPre().execute();
				JOptionPane.showMessageDialog(null, "Message has been sent Successfully", "Success Operation", 1);
				message_history();
				clear();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 1);
			}
		}
	}

	public void message_history() {
		send_Sub_1_2();
		try {
			send_Sub_1_2.getPre().execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 1);
		}
	}

	private void send_Sub_1_2() {
		String sql = "insert into message_history (MESSAGE_FROM,MESSAGE_TO,MESSAGE_TEXT) values ('"
				+ message_from.getText() + "' ,'" + message_to.getSelectedItem() + "' ,'" + message_text.getText()
				+ "' )";
		try {
			send_Sub_1_2.setPre(send_Sub_1_2.getCon().prepareStatement(sql));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 1);
		}
	}

	public void clear() {
		message_from.setText("");
		message_to.setSelectedIndex(0);
		message_text.setText("");
	}

	public void sendProduct() {
		send_Sub_1_2.sendProduct(this);
	}
}