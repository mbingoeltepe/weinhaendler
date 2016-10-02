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

import services.Sprache;




public class GuiWeinflascheSearch extends JFrame  implements ActionListener{

	private JLabel		lbl_hersteller = new JLabel ();		
	private JTextField	txt_hersteller = new	JTextField(10);
	private JLabel		lbl_ursprungsland = new JLabel ();		
	private JTextField	txt_ursprungsland = new	JTextField(10);
	private JCheckBox   cbox_imlager = new JCheckBox();
	private JCheckBox   cbox_alle = new JCheckBox();

	private JButton 	btn_ok = new JButton("OK");
	private JButton		btn_abbrechen = new JButton();



	private GuiWeinflasche guiweinflasche;

	Sprache sprache=new Sprache();
	private String message="";




	public  GuiWeinflascheSearch(GuiWeinflasche guiweinflasche)
	{
		super();
		this.guiweinflasche=guiweinflasche;
		setLayout(new GridBagLayout());
		setTitle("Weinflasche Suche");
		setSize(350,250);

		//labels and textfields
		GridBagConstraints gbc=new GridBagConstraints();

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor=GridBagConstraints.LINE_START;
		gbc.fill=GridBagConstraints.FIRST_LINE_START;		
		this.add(lbl_hersteller,gbc);		
		gbc.gridx = 1;
		this.add(txt_hersteller,gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		this.add(lbl_ursprungsland,gbc);
		gbc.gridx = 1;
		this.add(txt_ursprungsland,gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		this.add(cbox_imlager,gbc);

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
		setTitle(sprache.weinflasche_search_title[guiweinflasche.spracheID]);
		
		//label
		lbl_hersteller.setText(sprache.weinflasche_search_label_strings[0][guiweinflasche.spracheID]);
		lbl_ursprungsland.setText(sprache.weinflasche_search_label_strings[1][guiweinflasche.spracheID]);
		cbox_imlager.setText(sprache.weinflasche_search_label_strings[2][guiweinflasche.spracheID]);
		cbox_alle.setText(sprache.weinflasche_search_label_strings[3][guiweinflasche.spracheID]);

		//Message
		message=sprache.weinflasche_search_message[guiweinflasche.spracheID].toString();

		//Button
		btn_abbrechen.setText(sprache.weinflasche_search_button_strings[guiweinflasche.spracheID].toString());




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
			txt_hersteller.setEditable(false);
			txt_ursprungsland.setEditable(false);
			cbox_imlager.setEnabled(false);
		}else{
			txt_hersteller.setEditable(true);
			txt_ursprungsland.setEditable(true);
			cbox_imlager.setEnabled(true);
		}
	}




	private void onAbbrechenClick() {
		setVisible(false);		
	}




	private void onOkClick() {


		if (!cbox_alle.isSelected()){
			try {
				String hersteller=txt_hersteller.getText();
				String ursprungsland=txt_ursprungsland.getText();
				boolean imlager=cbox_imlager.isSelected();
				guiweinflasche.getWeinflascheTable(hersteller, ursprungsland, imlager, false, guiweinflasche.aktKundeID);
				setVisible(false);

			} catch (Exception es) {
				JOptionPane.showMessageDialog(null, message );
			}			
		}else{
			guiweinflasche.getWeinflascheTable(null,null,false,true,guiweinflasche.aktKundeID);
			setVisible(false);

		}
	}
}
