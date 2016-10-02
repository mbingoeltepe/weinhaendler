package gui;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import services.Sprache;


public class GuiKundeSearch extends JFrame  implements ActionListener{

	private JLabel		lbl_nachname = new JLabel ();		
	private JTextField	txt_nachname = new	JTextField(10);
	private JLabel		lbl_plz = new JLabel ();		
	private JTextField	txt_plz = new	JTextField(5);
	private JLabel		lbl_geburtsdatum = new JLabel ();		
	private JTextField	txt_geburtsdatum = new	JTextField(10);
	private JCheckBox   cbox_alle = new JCheckBox();

	private JButton 	btn_ok = new JButton("OK");
	private JButton		btn_abbrechen = new JButton("ABBRECHEN");

	private GuiKunde main;


	Sprache sprache=new Sprache();
	private String message="";

	public  GuiKundeSearch(GuiKunde main)
	{
		super();
		this.main=main;
		setLayout(new GridBagLayout());
		setSize(350,250);

		//labels and textfields
		GridBagConstraints gbc=new GridBagConstraints();

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor=GridBagConstraints.LINE_START;
		gbc.fill=GridBagConstraints.FIRST_LINE_START;		
		this.add(lbl_nachname,gbc);		
		gbc.gridx = 1;
		this.add(txt_nachname,gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		this.add(lbl_plz,gbc);
		gbc.gridx = 1;
		this.add(txt_plz,gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		this.add(lbl_geburtsdatum,gbc);
		gbc.gridx = 1;
		this.add(txt_geburtsdatum,gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		this.add(cbox_alle,gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.insets = new Insets(30,0,0,0);
		this.add(btn_ok,gbc);
		gbc.gridx = 1;
		this.add(btn_abbrechen,gbc);

		//ActionListener
		btn_ok.addActionListener(this);
		btn_ok.setActionCommand("ok");

		btn_abbrechen.addActionListener(this);
		btn_abbrechen.setActionCommand("abbrechen");

		cbox_alle.addActionListener(this);
		cbox_alle.setActionCommand("alle");

		setSprache();
		
	}




	private void setSprache() {
		
		//Title
		setTitle(sprache.kunde_search_title[main.spracheID]);
		
		//label
		lbl_nachname.setText(sprache.kunde_search_label_strings[0][main.spracheID]);
		lbl_plz.setText(sprache.kunde_search_label_strings[1][main.spracheID]);
		lbl_geburtsdatum.setText(sprache.kunde_search_label_strings[2][main.spracheID]);
		cbox_alle.setText(sprache.kunde_search_label_strings[3][main.spracheID]);
		
		//Message
		message=sprache.kunde_search_message[main.spracheID].toString();

		//Button
		btn_abbrechen.setText(sprache.kunde_search_button_strings[main.spracheID].toString());

		
	}




	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getActionCommand().equals("ok")){
			onOkClick();

		}

		if(e.getActionCommand().equals("abbrechen")){
			onAbbrechenClick();
		}

		if(e.getActionCommand().equals("alle")){
			onAlleClick();
		}


	}




	private void onAlleClick() {
		if (cbox_alle.isSelected()){
			txt_nachname.setEditable(false);
			txt_plz.setEditable(false);
			txt_geburtsdatum.setEditable(false);
		}else{
			txt_nachname.setEditable(true);
			txt_plz.setEditable(true);
			txt_geburtsdatum.setEditable(true);			
		}
	}




	private void onAbbrechenClick() {
		setVisible(false);		
	}




	private void onOkClick() {
		boolean kontrol=true;
		int plz = -1;
		Date geburtsdatum = null;

		if (!cbox_alle.isSelected()){
			try {


				if (!txt_plz.getText().isEmpty()) {
					plz=Integer.parseInt(txt_plz.getText().toString());
					if ((plz>99999) || (plz<1000))  kontrol=false;
				}

				if (!txt_geburtsdatum.getText().isEmpty()){
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					geburtsdatum = new Date(formatter.parse(txt_geburtsdatum.getText()).getTime());				
				}

				if (!kontrol) JOptionPane.showMessageDialog(null, message );
				if (kontrol) {

					String nachname = txt_nachname.getText();
					main.getKundeTable(nachname, plz, geburtsdatum, false);
					setVisible(false);

				}
			} catch (Exception es) {
				JOptionPane.showMessageDialog(null, message );
				return;
			}			
		}else{
			main.getKundeTable(null, 0, null, true);
			setVisible(false);

		}
			
	}


}
