package gui;


import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import services.InsuranceService;
import services.SimpleInsuranceService;
import services.Sprache;

import domain.Kunde;



public class GuiKundeNeuEdit extends JFrame  implements ActionListener{

	private JLabel		lbl_vorname = new JLabel ();		
	private JTextField	txt_vorname = new	JTextField(10);
	private JLabel		lbl_nachname = new JLabel ();		
	private JTextField	txt_nachname = new	JTextField(10);
	private JLabel		lbl_strasse = new JLabel ();		
	private JTextField	txt_strasse = new	JTextField(10);
	private JLabel		lbl_plz = new JLabel ();		
	private JTextField	txt_plz = new	JTextField(5);
	private JLabel		lbl_ort = new JLabel ();		
	private JTextField	txt_ort = new	JTextField(10);
	private JLabel		lbl_geburtsdatum = new JLabel ();		
	private JTextField	txt_geburtsdatum = new	JTextField(10);
	private JLabel		lbl_geschlecht = new JLabel ();		
	private String[]    geschlecht_combobox_Strings = {};
	private JComboBox   geschlecht_combobox_List = new JComboBox(geschlecht_combobox_Strings);

	private JLabel		lbl_info = new JLabel ();		
	private JLabel		lbl_info1 = new JLabel ();		


	private JButton 	btn_ok = new JButton("OK");
	private JButton		btn_abbrechen = new JButton();

	private GuiKunde main;

	private InsuranceService service=new SimpleInsuranceService();

	private int updateID;
	
	Sprache sprache=new Sprache();
	private String[] message={"","",""};

	public  GuiKundeNeuEdit(GuiKunde main)
	{
		super();
		this.main=main;
		setLayout(new GridBagLayout());
		setSize(350,350);

		//labels and textfields
		GridBagConstraints gbc=new GridBagConstraints();

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor=GridBagConstraints.LINE_START;
		gbc.fill=GridBagConstraints.FIRST_LINE_START;		
		this.add(lbl_vorname,gbc);		
		gbc.gridx = 1;
		this.add(txt_vorname,gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		this.add(lbl_nachname,gbc);
		gbc.gridx = 1;
		this.add(txt_nachname,gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		this.add(lbl_strasse,gbc);
		gbc.gridx = 1;
		this.add(txt_strasse,gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		this.add(lbl_plz,gbc);
		gbc.gridx = 1;
		this.add(txt_plz,gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;
		this.add(lbl_ort,gbc);
		gbc.gridx = 1;
		this.add(txt_ort,gbc);

		gbc.gridx = 0;
		gbc.gridy = 5;
		this.add(lbl_geburtsdatum,gbc);
		gbc.gridx = 1;
		this.add(txt_geburtsdatum,gbc);

		gbc.gridx = 0;
		gbc.gridy = 6;
		this.add(lbl_geschlecht,gbc);
		gbc.gridx = 1;
		this.add(geschlecht_combobox_List,gbc);

		
		lbl_info.setForeground(Color.RED);
		lbl_info1.setForeground(Color.RED);

		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.insets = new Insets(5,0,0,0);		
		this.add(lbl_info,gbc);
		gbc.gridy = 8;
		this.add(lbl_info1,gbc);

		gbc.gridx = 0;
		gbc.gridy = 9;
		gbc.insets = new Insets(30,0,0,0);
		this.add(btn_ok,gbc);
		gbc.gridx = 1;
		this.add(btn_abbrechen,gbc);

		//ActionListener
		btn_ok.addActionListener(this);
		btn_ok.setActionCommand("ok");

		btn_abbrechen.addActionListener(this);
		btn_abbrechen.setActionCommand("abbrechen");
		
		setSprache();
		
	}




	private void setSprache() {
	
		
		//label
		lbl_vorname.setText(sprache.kunde_neuedit_label_strings[0][main.spracheID]);
		lbl_nachname.setText(sprache.kunde_neuedit_label_strings[1][main.spracheID]);
		lbl_strasse.setText(sprache.kunde_neuedit_label_strings[2][main.spracheID]);
		lbl_plz.setText(sprache.kunde_neuedit_label_strings[3][main.spracheID]);
		lbl_ort.setText(sprache.kunde_neuedit_label_strings[4][main.spracheID]);
		lbl_geburtsdatum.setText(sprache.kunde_neuedit_label_strings[5][main.spracheID]);
		lbl_geschlecht.setText(sprache.kunde_neuedit_label_strings[6][main.spracheID]);
		lbl_info.setText(sprache.kunde_neuedit_label_strings[7][main.spracheID]);
		lbl_info1.setText(sprache.kunde_neuedit_label_strings[8][main.spracheID]);
		
		
	
		geschlecht_combobox_List.insertItemAt(sprache.kunde_neuedit_cbox[0][main.spracheID], 0);
		geschlecht_combobox_List.insertItemAt(sprache.kunde_neuedit_cbox[1][main.spracheID], 1);
		
		geschlecht_combobox_List.setSelectedIndex(0);
		
		//Message
		message[0]=sprache.kunde_neuedit_message[0][main.spracheID].toString();
		message[1]=sprache.kunde_neuedit_message[1][main.spracheID].toString();
		message[2]=sprache.kunde_neuedit_message[2][main.spracheID].toString();

		//Button
		btn_abbrechen.setText(sprache.kunde_neuedit_button_strings[main.spracheID].toString());


	}


	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getActionCommand().equals("ok")){
			onOkCreateClick();

		}

		if(e.getActionCommand().equals("abbrechen")){
			onAbbrechenClick();
		}


	}




	private void onAbbrechenClick() {
		setVisible(false);		
	}




	private void onOkCreateClick() {

		boolean kontrol=true;

		try {


			if(txt_vorname.getText().isEmpty()) kontrol=false;
			if(txt_nachname.getText().isEmpty()) kontrol=false;
			int plz=Integer.parseInt(txt_plz.getText().toString());

			if ((plz>99998) || (plz<1001))  kontrol=false;

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date geburtsdatum = null;
			geburtsdatum = new Date(formatter.parse(txt_geburtsdatum.getText()).getTime());

			if (!kontrol) JOptionPane.showMessageDialog(null,message[0]);
			if (kontrol) {

				Kunde k = new Kunde();

				k.setVorname(txt_vorname.getText());
				k.setNachname(txt_nachname.getText());
				k.setStrasse(txt_strasse.getText());
				k.setPlz(Integer.parseInt(txt_plz.getText()));
				k.setOrt(txt_ort.getText());			
				k.setGeburtsdatum(geburtsdatum);
				k.setTyp(geschlecht_combobox_List.getSelectedItem().toString());
				
				if (main.spracheID==1){
					if (geschlecht_combobox_List.getSelectedItem().toString()=="Male")
						k.setTyp("Maenlich");
					else
						k.setTyp("Weiblich");
				}
				
				k.setID(updateID);

				if (!main.editMode) 
				{					
					service.createKunde(k);
					JOptionPane.showMessageDialog(null,message[1]);
				}

				if (main.editMode) 
				{
					service.updateKunde(k);
					JOptionPane.showMessageDialog(null,message[2]);
				}

				setVisible(false);
				main.getKundeTable(null,0,null,true);

			}
		} catch (Exception es) {
			JOptionPane.showMessageDialog(null,message[0]);
			return;
		}			
	}

	public void setKunde(Kunde kunde)
	{

		txt_vorname.setText(kunde.getVorname());
		txt_nachname.setText(kunde.getNachname());
		txt_strasse.setText(kunde.getStrasse());
		txt_plz.setText(Integer.toString(kunde.getPlz()));
		txt_ort.setText(kunde.getOrt());
		txt_geburtsdatum.setText(""+kunde.getGeburtsdatum());
		geschlecht_combobox_List.setSelectedItem(kunde.getTyp().toString());
		
		if (main.spracheID==1){
			if (kunde.getTyp().toString()=="Maenlich")
				geschlecht_combobox_List.setSelectedIndex(0);
			else
				geschlecht_combobox_List.setSelectedIndex(1);		
		}

		updateID = kunde.getID();

	}



}
