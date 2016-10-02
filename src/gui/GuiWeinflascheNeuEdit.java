package gui;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import services.InsuranceService;
import services.SimpleInsuranceService;
import services.Sprache;

import domain.Weinflasche;



public class GuiWeinflascheNeuEdit extends JFrame  implements ActionListener{

	private JLabel		lbl_bezeichnung = new JLabel ();		
	private JTextField	txt_bezeichnung = new	JTextField(15);
	private JLabel		lbl_hersteller = new JLabel ();		
	private JTextField	txt_hersteller = new	JTextField(15);
	private JLabel		lbl_ursprungsland = new JLabel ();		
	private JTextField	txt_ursprungsland = new	JTextField(15);
	private JLabel		lbl_vol = new JLabel ();		
	private JTextField	txt_vol = new	JTextField(5);
	private JLabel		lbl_inhalt = new JLabel ();		
	private JTextField	txt_inhalt = new	JTextField(5);
	private JCheckBox   cbox_imlager = new JCheckBox();


	private JButton 	btn_ok = new JButton("OK");
	private JButton		btn_abbrechen = new JButton();

	private GuiWeinflasche guiweinflasche;


	private InsuranceService service=new SimpleInsuranceService();

	Sprache sprache=new Sprache();
	private String[] message={"","",""};


	public  GuiWeinflascheNeuEdit(GuiWeinflasche guiweinflasche)
	{
		super();
		this.guiweinflasche=guiweinflasche;
		setLayout(new GridBagLayout());
		setSize(400,250);

		//labels and textfields
		GridBagConstraints gbc=new GridBagConstraints();

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor=GridBagConstraints.LINE_START;
		gbc.fill=GridBagConstraints.FIRST_LINE_START;		
		this.add(lbl_bezeichnung,gbc);		
		gbc.gridx = 1;
		this.add(txt_bezeichnung,gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		this.add(lbl_hersteller,gbc);
		gbc.gridx = 1;
		this.add(txt_hersteller,gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		this.add(lbl_ursprungsland,gbc);
		gbc.gridx = 1;
		this.add(txt_ursprungsland,gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		this.add(lbl_vol,gbc);
		gbc.gridx = 1;
		this.add(txt_vol,gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;
		this.add(lbl_inhalt,gbc);
		gbc.gridx = 1;
		this.add(txt_inhalt,gbc);

		gbc.gridx = 0;
		gbc.gridy = 5;
		this.add(cbox_imlager,gbc);

		gbc.gridx = 0;
		gbc.gridy = 6;
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
		
		lbl_bezeichnung.setText(sprache.weinflasche_neuedit_label_strings[0][guiweinflasche.spracheID]);
		lbl_hersteller.setText(sprache.weinflasche_neuedit_label_strings[1][guiweinflasche.spracheID]);
		lbl_ursprungsland.setText(sprache.weinflasche_neuedit_label_strings[2][guiweinflasche.spracheID]);
		lbl_vol.setText(sprache.weinflasche_neuedit_label_strings[3][guiweinflasche.spracheID]);
		lbl_inhalt.setText(sprache.weinflasche_neuedit_label_strings[4][guiweinflasche.spracheID]);
		cbox_imlager.setText(sprache.weinflasche_neuedit_label_strings[5][guiweinflasche.spracheID]);
		
		//Button
		btn_abbrechen.setText(sprache.weinflasche_neuedit_button_strings[guiweinflasche.spracheID].toString());

		//Message
		message[0]=sprache.weinflasche_neuedit_message[0][guiweinflasche.spracheID].toString();
		message[1]=sprache.weinflasche_neuedit_message[1][guiweinflasche.spracheID].toString();
		message[2]=sprache.weinflasche_neuedit_message[2][guiweinflasche.spracheID].toString();

		
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

		boolean kontrol=false;

		try {

			if (!txt_bezeichnung.getText().isEmpty()) kontrol=true; 

			if (!txt_hersteller.getText().isEmpty()) kontrol=true;

			if (!txt_ursprungsland.getText().isEmpty()) kontrol=true;

			if (!txt_vol.getText().isEmpty()) kontrol=true;

			if (!txt_inhalt.getText().isEmpty()) kontrol=true;

			if (!kontrol) JOptionPane.showMessageDialog(null, message[0]);
			
			if (kontrol) {

				Weinflasche wf = new Weinflasche();

				if (txt_vol.getText().isEmpty()) txt_vol.setText("0");
				if (txt_inhalt.getText().isEmpty()) txt_inhalt.setText("0");
				
				wf.setBezeichnung(txt_bezeichnung.getText());
				wf.setHersteller(txt_hersteller.getText());
				wf.setUrsprungsland(txt_ursprungsland.getText());
				wf.setVol(Double.parseDouble(txt_vol.getText()));
				wf.setInhalt(Double.parseDouble(txt_inhalt.getText()));
				wf.setImLager(cbox_imlager.isSelected());
				
				if (!guiweinflasche.editMode) 
				{
					wf.setKID(guiweinflasche.aktKundeID);
					service.createWeinflasche(wf);
					JOptionPane.showMessageDialog(null, message[1] );
				}

				if (guiweinflasche.editMode) 
				{
					wf.setID(guiweinflasche.aktWeinflascheID);
					service.updateWeinflasche(wf);
					JOptionPane.showMessageDialog(null, message[2] );
				}

				setVisible(false);
				guiweinflasche.getWeinflascheTable(null,null,false,true,guiweinflasche.aktKundeID);	
			

			}
		} catch (Exception es) {
			JOptionPane.showMessageDialog(null, message[0] );
			return;
		}			

	}

	public void setWeinflasche(Weinflasche weinflasche)
	{
		txt_bezeichnung.setText(weinflasche.getBezeichnung());
		txt_hersteller.setText(weinflasche.getHersteller());
		txt_ursprungsland.setText(weinflasche.getUrsprungsland());
		txt_vol.setText(""+weinflasche.getVol());
		txt_inhalt.setText(""+weinflasche.getInhalt());
		cbox_imlager.setSelected(weinflasche.getImLager());
	}



}
