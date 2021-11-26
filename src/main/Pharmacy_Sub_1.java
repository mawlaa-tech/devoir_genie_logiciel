package main;


import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.Serializable;

public class Pharmacy_Sub_1 implements Serializable {
	private Date d;
	private SimpleDateFormat dd;

	public Date getD() {
		return d;
	}

	public SimpleDateFormat getDd() {
		return dd;
	}

	public void showDate() {
		d = new Date();
		dd = new SimpleDateFormat("dd-MM-yyyy");
		Pharmacy.today.setText(dd.format(d));
		new Timer(0, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				Date d = new Date();
				SimpleDateFormat dd = new SimpleDateFormat("hh:mm:ss a");
				Pharmacy.time.setText(dd.format(d));
			}
		}).start();
	}
}