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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import services.Sprache;


public class GuiReport extends JFrame  implements ActionListener{

	private JCheckBox   cbox_ort = new JCheckBox();
	private JCheckBox   cbox_geschlecht = new JCheckBox();
	private JCheckBox   cbox_hersteller = new JCheckBox();
	private JCheckBox   cbox_ursprungsland = new JCheckBox();

	public JTextField	txt_ort = new	JTextField(10);
	public JTextField	txt_hersteller = new	JTextField(10);
	public JTextField	txt_ursprungsland = new	JTextField(10);

	private String[]    geschlecht_combobox_Strings = {};
	public JComboBox   geschlecht_combobox_List = new JComboBox(geschlecht_combobox_Strings);


	private JButton 	btn_ok = new JButton("OK");
	private JButton		btn_abbrechen = new JButton();

	private GuiKunde main;


	Sprache sprache=new Sprache();
	public int spracheID;

	public  GuiReport(GuiKunde main)
	{
		super();
		this.main=main;
		this.spracheID=main.spracheID;
		setTitle("Report");
		setLayout(new GridBagLayout());
		setSize(350,250);

		//combobox and textfields
		GridBagConstraints gbc=new GridBagConstraints();

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor=GridBagConstraints.LINE_START;
		gbc.fill=GridBagConstraints.FIRST_LINE_START;		
		this.add(cbox_ort,gbc);		
		gbc.gridx = 1;
		this.add(txt_ort,gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		this.add(cbox_geschlecht,gbc);
		gbc.gridx = 1;
		this.add(geschlecht_combobox_List,gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		this.add(cbox_hersteller,gbc);
		gbc.gridx = 1;
		this.add(txt_hersteller,gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		this.add(cbox_ursprungsland,gbc);
		gbc.gridx = 1;
		this.add(txt_ursprungsland,gbc);

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

		cbox_ort.addActionListener(this);
		cbox_ort.setActionCommand("ort");

		cbox_geschlecht.addActionListener(this);
		cbox_geschlecht.setActionCommand("geschlecht");

		cbox_hersteller.addActionListener(this);
		cbox_hersteller.setActionCommand("hersteller");

		cbox_ursprungsland.addActionListener(this);
		cbox_ursprungsland.setActionCommand("ursprungsland");

		txt_ort.setEnabled(false);
		geschlecht_combobox_List.setEnabled(false);
		txt_hersteller.setEnabled(false);
		txt_ursprungsland.setEnabled(false);
		
		setSprache();

	}




	private void setSprache() {


		//label
		cbox_ort.setText(sprache.report_label_strings[0][main.spracheID]);
		cbox_geschlecht.setText(sprache.report_label_strings[1][main.spracheID]);
		cbox_hersteller.setText(sprache.report_label_strings[2][main.spracheID]);
		cbox_ursprungsland.setText(sprache.report_label_strings[3][main.spracheID]);

		//Button
		btn_abbrechen.setText(sprache.report_button_strings[main.spracheID].toString());

		//cbox
		geschlecht_combobox_List.insertItemAt(sprache.report_neuedit_cbox[0][main.spracheID], 0);
		geschlecht_combobox_List.insertItemAt(sprache.report_neuedit_cbox[1][main.spracheID], 1);
		geschlecht_combobox_List.insertItemAt(sprache.report_neuedit_cbox[2][main.spracheID], 2);
		
		geschlecht_combobox_List.setSelectedIndex(0);


	}




	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getActionCommand().equals("ok")){
			onOkClick();

		}

		if(e.getActionCommand().equals("abbrechen")){
			onAbbrechenClick();
		}

		if(e.getActionCommand().equals("ort")){
			onOrtClick();
		}

		if(e.getActionCommand().equals("geschlecht")){
			onGeschlechtClick();
		}

		if(e.getActionCommand().equals("hersteller")){
			onHerstellerClick();
		}

		if(e.getActionCommand().equals("ursprungsland")){
			onUrsprungslandClick();
		}

	}




	private void onUrsprungslandClick() {
		if (cbox_ursprungsland.isSelected())
			txt_ursprungsland.setEnabled(true);
		else{
			txt_ursprungsland.setText("");	
			txt_ursprungsland.setEnabled(false);
		}
	}




	private void onHerstellerClick() {
		if (cbox_hersteller.isSelected())
			txt_hersteller.setEnabled(true);
		else {
			txt_hersteller.setEnabled(false);
			txt_hersteller.setText("");	
		}
	}




	private void onGeschlechtClick() {
		if (cbox_geschlecht.isSelected())
			geschlecht_combobox_List.setEnabled(true);
		else {
			geschlecht_combobox_List.setEnabled(false);
			geschlecht_combobox_List.setSelectedIndex(0);
		}

	}



	private void onOrtClick() {
		if (cbox_ort.isSelected())
			txt_ort.setEnabled(true);
		else {
			txt_ort.setEnabled(false);
			txt_ort.setText("");	
		}

	}


	private void onAbbrechenClick() {
		setVisible(false);		
	}




	private void onOkClick() {
		GuiReportErgebnisse guireportergebnisse = new GuiReportErgebnisse(this);
		guireportergebnisse.setVisible(true);



	}

}