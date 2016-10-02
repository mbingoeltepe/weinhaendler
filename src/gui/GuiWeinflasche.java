package gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;

import services.InsuranceService;
import services.SimpleInsuranceService;
import services.Sprache;

import domain.Kunde;
import domain.Weinflasche;



public class GuiWeinflasche extends JFrame  implements ActionListener{

	Sprache sprache=new Sprache();
	private String[] message={"","","",""};
	
	private String vorname,nachname,text;
	private int plz;
	private Date geburtsdatum;

	private JButton 	btn_weinflasche_create = new JButton();
	private JButton 	btn_weinflasche_update = new JButton();
	private JButton 	btn_weinflasche_delete = new JButton();
	private JButton 	btn_weinflasche_search = new JButton();
	private JButton 	btn_weinflasche_abbrechen = new JButton();


	private  JPanel untenPanel;

	private static Logger logger = Logger.getLogger(GuiKunde.class);



	public boolean editMode;

	public int aktKundeID,aktWeinflascheID;

	//Weinflasche column
	private String[] weinflasche_colnames = {"", "", "", "", "", "",""};
	private Object[][] 	weinflasche_datas = {{"", "", "", "", "", "", "" }};


	
	private InsuranceService service=new SimpleInsuranceService();

	private JTable weinflasche_table=new JTable(weinflasche_datas,weinflasche_colnames){
		public boolean isCellEditable(int row, int column)
		{
			return false;
		}

	};

	private JScrollPane scrl_weinflasche_table=new JScrollPane(weinflasche_table);
	private Object[][] datas = null;   

	private GuiKunde main;

	public int spracheID;
	
	public  GuiWeinflasche(GuiKunde main)
	{
		super();
		this.main=main;
		this.spracheID=main.spracheID;
		aktKundeID=main.aktkundeID;


		setTitle("Weinflasche");
		setLayout(new BorderLayout());
		setSize(750,300);



		untenPanel=new JPanel(new GridBagLayout());
		untenPanel.setBackground(Color.LIGHT_GRAY);
		untenPanel.setSize(750,100);


		this.add(scrl_weinflasche_table);
		this.add(untenPanel,BorderLayout.SOUTH);


		addUntenPanel();
		setVisible(true);

		getWeinflascheTable(null,null,false,true,aktKundeID);	
		setSprache();

	}





	private void setSprache() {
		//Buttons
		btn_weinflasche_create.setText(sprache.weinflasche_button_strings[0][main.spracheID].toString());
		btn_weinflasche_update.setText(sprache.weinflasche_button_strings[1][main.spracheID].toString());
		btn_weinflasche_delete.setText(sprache.weinflasche_button_strings[2][main.spracheID].toString());
		btn_weinflasche_search.setText(sprache.weinflasche_button_strings[3][main.spracheID].toString());
		btn_weinflasche_abbrechen.setText(sprache.weinflasche_button_strings[4][main.spracheID].toString());

		//Title
		setTitle(sprache.weinflasche_title[main.spracheID]);
		
		//Message
		message[0]=sprache.weinflasche_message[0][main.spracheID].toString();
		message[1]=sprache.weinflasche_message[1][main.spracheID].toString();
		message[2]=sprache.weinflasche_message[2][main.spracheID].toString();
		message[3]=sprache.weinflasche_message[3][main.spracheID].toString();

		
	}





	private void addUntenPanel() {

		GridBagConstraints gbc=new GridBagConstraints();

		gbc.gridx = 0;
		gbc.insets = new Insets(0,5,0,0);
		untenPanel.add(btn_weinflasche_create,gbc);

		gbc.gridx = 1;
		untenPanel.add(btn_weinflasche_update,gbc);

		gbc.gridx = 2;
		untenPanel.add(btn_weinflasche_delete,gbc);

		gbc.gridx = 3;
		untenPanel.add(btn_weinflasche_search,gbc);

		gbc.gridx = 4;
		gbc.insets = new Insets(0,200,0,0);
		untenPanel.add(btn_weinflasche_abbrechen,gbc);

		//ActionListener
		btn_weinflasche_create.addActionListener(this);
		btn_weinflasche_create.setActionCommand("create");

		btn_weinflasche_update.addActionListener(this);
		btn_weinflasche_update.setActionCommand("update");

		btn_weinflasche_delete.addActionListener(this);
		btn_weinflasche_delete.setActionCommand("delete");

		btn_weinflasche_search.addActionListener(this);
		btn_weinflasche_search.setActionCommand("search");


		btn_weinflasche_abbrechen.addActionListener(this);
		btn_weinflasche_abbrechen.setActionCommand("abbrechen");


	}

	public void getWeinflascheTable(String hersteller,String ursprungsland,boolean imlager,boolean getAlle,int kid) 
	{
		Vector<Weinflasche> list = service.searchWeinflasche(hersteller, ursprungsland, imlager, getAlle, kid);
		datas = new Object[list.size()][7];
		int i = 0;
		for(Weinflasche wf : list){
			datas[i][0] = wf.getID();
			datas[i][1] = wf.getBezeichnung();
			datas[i][2] = wf.getHersteller();
			datas[i][3] = wf.getUrsprungsland();
			datas[i][4] = wf.getVol();
			datas[i][5] = wf.getInhalt();
			datas[i][6] = wf.getImLager() ? "Ja" : "Nein";

			if (spracheID==1)
				datas[i][6] = wf.getImLager() ? "Yes" : "No";

			i++;
		}
		DefaultTableModel tm = new DefaultTableModel(datas, weinflasche_colnames);
		weinflasche_table.setModel(tm);

		setColumName();


	}

	private void setColumName() {
		main.ChangeName(weinflasche_table,0,sprache.weinflasche_table_strings[0][main.spracheID].toString());
		main.ChangeName(weinflasche_table,1,sprache.weinflasche_table_strings[1][main.spracheID].toString());
		main.ChangeName(weinflasche_table,2,sprache.weinflasche_table_strings[2][main.spracheID].toString());
		main.ChangeName(weinflasche_table,3,sprache.weinflasche_table_strings[3][main.spracheID].toString());
		main.ChangeName(weinflasche_table,4,sprache.weinflasche_table_strings[4][main.spracheID].toString());
		main.ChangeName(weinflasche_table,5,sprache.weinflasche_table_strings[5][main.spracheID].toString());
		main.ChangeName(weinflasche_table,6,sprache.weinflasche_table_strings[6][main.spracheID].toString());
		
	}





	public void actionPerformed(final ActionEvent e) {

		if(e.getActionCommand().equals("create")){
			onCreateClick();
		}

		if(e.getActionCommand().equals("update")){
			onUpdateClick();
		}

		if(e.getActionCommand().equals("delete")){
			onDeleteClick();
		}

		if(e.getActionCommand().equals("search")){
			onSearchClick();
		}

		if(e.getActionCommand().equals("abbrechen")){
			onAbbrechenClick();
		}



	}






	private void onAbbrechenClick() {
		setVisible(false);


	}





	private void onDeleteClick() {

		int weinflascheID = SelectedRowVonWeinflasche();
		if (weinflascheID==-1)
			return;

		Weinflasche wf = service.findByWeinflascheID(weinflascheID);


		int d = JOptionPane.showOptionDialog(null, 
				message[1],"", 
				JOptionPane.YES_NO_OPTION, 
				JOptionPane.QUESTION_MESSAGE, null, null,null );

		if(d==JOptionPane.YES_OPTION){
			try {
				service.deleteWeinflasche(wf);
			} catch (Exception ed) {
				JOptionPane.showMessageDialog(null, message[3] );
			}
			getWeinflascheTable(null,null,false,true,aktKundeID);	

		}				





	}





	private int SelectedRowVonWeinflasche()
	{
		int row = weinflasche_table.getSelectedRow();

		if(row == -1)
		{
			JOptionPane.showMessageDialog(null, message[0]);
			return -1;
		}

		return (Integer)weinflasche_table.getValueAt(row, 0);
	}
	private void onCreateClick() {

		Kunde kunde = service.findByID(aktKundeID);
		GuiWeinflascheNeuEdit guiwfne = new GuiWeinflascheNeuEdit(this);

		if (kunde.getID()!=0){
			guiwfne.setTitle(sprache.weinflasche_neuedit_title[0][main.spracheID]+aktKundeID+" "+vorname);
			editMode=false;
			guiwfne.setVisible(true);
		}else{
			JOptionPane.showMessageDialog(null, message[2]);
			onAbbrechenClick();
		}

	}

	private void onUpdateClick() {
		int weinflascheID = SelectedRowVonWeinflasche();

		if (weinflascheID==-1)
			return;

		Kunde kunde = service.findByID(aktKundeID);
		GuiWeinflascheNeuEdit guiwfne = new GuiWeinflascheNeuEdit(this);

		if (kunde.getID()!=0){	
			Weinflasche wf = service.findByWeinflascheID(weinflascheID);
			aktWeinflascheID=weinflascheID;
			guiwfne.setTitle(sprache.weinflasche_neuedit_title[1][main.spracheID]+aktKundeID+" "+vorname);
			editMode=true;
			guiwfne.setVisible(true);
			guiwfne.setWeinflasche(wf);
		}else{
			JOptionPane.showMessageDialog(null, message[2]);
			onAbbrechenClick();

		}


	}







	private void onSearchClick() {
		GuiWeinflascheSearch guiwfsearch = new GuiWeinflascheSearch(this);
		guiwfsearch.setVisible(true);


	}

	public void setKunde(Kunde kunde)
	{
		vorname=kunde.getVorname().toString();
		nachname=kunde.getNachname();
		plz=kunde.getPlz();
		geburtsdatum=kunde.getGeburtsdatum();
		
		if (main.spracheID==0)
			text="Vorname= "+vorname+"  Nachname= "+nachname+"  Plz= "+plz+"  Geburtsdatum= "+geburtsdatum;
		else
			text="First name= "+vorname+"  Surname= "+nachname+"  PostCode= "+plz+"  Birthdate= "+geburtsdatum;			
		JLabel		lbl1 = new JLabel(text);
		this.add(lbl1,BorderLayout.PAGE_START);
	}



}
