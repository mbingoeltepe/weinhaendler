package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
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

import db.HSQLDbHandler;
import domain.Kunde;
import domain.Weinflasche;



public class GuiKunde extends JFrame implements ActionListener{

	Sprache sprache=new Sprache();

	private String[] message={"","","",""};



	private String[]    sprache_combobox_Strings = { "Deutsch", "English" };
	public JComboBox    sprache_combobox_List = new JComboBox(sprache_combobox_Strings);

	private JButton 	btn_kunde_create = new JButton();
	private JButton 	btn_kunde_update = new JButton();
	private JButton 	btn_kunde_delete = new JButton();
	private JButton 	btn_kunde_search = new JButton();
	private JButton 	btn_kunde_weinflasche = new JButton();
	private JButton 	btn_kunde_report = new JButton("Report");
	private JButton 	btn_kunde_exit = new JButton();
	private JLabel	lbl1 = new JLabel();



	private  JPanel untenPanel;

	private static Logger logger = Logger.getLogger(GuiKunde.class);

	public int aktkundeID;

	//Kunde column

	private String[] kunde_colnames= {"", "", "", "", "", "", "", ""};

	private Object[][] 	kunde_datas = {{"", "", "", "", "", "", "", "" }
	};

	public JTable kunde_table=new JTable(kunde_datas,kunde_colnames){
		public boolean isCellEditable(int row, int column)
		{
			return false;
		}

	};

	private JScrollPane scrl_kunde_table=new JScrollPane(kunde_table);
	private Object[][] datas = null;   


	private InsuranceService service=new SimpleInsuranceService();



	public boolean editMode;


	private void exit(){
		logger.info("Application wird beendet");
		HSQLDbHandler.closeConnection();
		System.exit(0);
	}

	public int spracheID;


	public GuiKunde(){
		super();
		this.spracheID=sprache_combobox_List.getSelectedIndex();
		addWindowListener(new WindowAdapter() {
			public void windowClosing(final WindowEvent we) {
				exit();
			}
		});



		setLayout(new BorderLayout());
		setSize(950,600);



		untenPanel=new JPanel(new GridBagLayout());
		untenPanel.setBackground(Color.LIGHT_GRAY);
		untenPanel.setSize(750,100);


		this.add(lbl1,BorderLayout.PAGE_START);
		this.add(scrl_kunde_table);
		this.add(untenPanel,BorderLayout.SOUTH);



		addUntenPanel();
		getKundeTable(null,0,null,true);
		setSprache();
		setVisible(true);
	}


	private void setSprache() {

		//Buttons
		btn_kunde_create.setText(sprache.kunde_button_strings[0][spracheID].toString());
		btn_kunde_update.setText(sprache.kunde_button_strings[1][spracheID].toString());
		btn_kunde_delete.setText(sprache.kunde_button_strings[2][spracheID].toString());
		btn_kunde_search.setText(sprache.kunde_button_strings[3][spracheID].toString());
		btn_kunde_weinflasche.setText(sprache.kunde_button_strings[4][spracheID].toString());
		btn_kunde_exit.setText(sprache.kunde_button_strings[5][spracheID].toString());
	
		//Title
		setTitle(sprache.kunde_title[spracheID]);
		//Label
		lbl1.setText(sprache.kunde_label[spracheID]);
		//Message
		message[0]=sprache.kunde_message[0][spracheID].toString();
		message[1]=sprache.kunde_message[1][spracheID].toString();
		message[2]=sprache.kunde_message[2][spracheID].toString();
		message[3]=sprache.kunde_message[3][spracheID].toString();

	}

	public void ChangeName(JTable table, int col_index, String col_name){
		table.getColumnModel().getColumn(col_index).setHeaderValue(col_name);
	}



	public void getKundeTable(String nachname,int plz,Date geburtsdatum,boolean getAlle) {

		
		Vector<Kunde> list = service.searchKunde(nachname, plz, geburtsdatum, getAlle);
		

		datas = new Object[list.size()][8];
		int i = 0;
		for(Kunde kunde : list){
			datas[i][0] = kunde.getID();
			datas[i][1] = kunde.getVorname();
			datas[i][2] = kunde.getNachname();
			datas[i][3] = kunde.getStrasse();
			datas[i][4] = kunde.getPlz();
			datas[i][5] = kunde.getOrt();
			datas[i][6] = kunde.getGeburtsdatum();
			datas[i][7] = kunde.getTyp();
			if (spracheID==1){
				if (kunde.getTyp().toString()=="Maenlich")
					datas[i][7] = "Male";
				else
					datas[i][7] = "Female";					
			}
			i++;
		}
		DefaultTableModel tm = new DefaultTableModel(datas, kunde_colnames);
		kunde_table.setModel(tm);

		setColumName();

	}


	private void setColumName() {
		//Columsname
		ChangeName(kunde_table,0,sprache.kunde_table_strings[0][spracheID].toString());
		ChangeName(kunde_table,1,sprache.kunde_table_strings[1][spracheID].toString());
		ChangeName(kunde_table,2,sprache.kunde_table_strings[2][spracheID].toString());
		ChangeName(kunde_table,3,sprache.kunde_table_strings[3][spracheID].toString());
		ChangeName(kunde_table,4,sprache.kunde_table_strings[4][spracheID].toString());
		ChangeName(kunde_table,5,sprache.kunde_table_strings[5][spracheID].toString());
		ChangeName(kunde_table,6,sprache.kunde_table_strings[6][spracheID].toString());
		ChangeName(kunde_table,7,sprache.kunde_table_strings[7][spracheID].toString());
		
	}


	private void addUntenPanel() {

		GridBagConstraints gbc=new GridBagConstraints();

		gbc.gridx = 0;
		gbc.insets = new Insets(0,5,0,0);
		untenPanel.add(btn_kunde_create,gbc);

		gbc.gridx = 1;
		untenPanel.add(btn_kunde_update,gbc);

		gbc.gridx = 2;
		untenPanel.add(btn_kunde_delete,gbc);

		gbc.gridx = 3;
		untenPanel.add(btn_kunde_search,gbc);

		gbc.gridx = 4;
		gbc.insets = new Insets(0,150,0,0);
		untenPanel.add(btn_kunde_weinflasche,gbc);

		gbc.gridx = 5;
		gbc.insets = new Insets(0,5,0,0);
		untenPanel.add(btn_kunde_report,gbc);

		gbc.gridx = 6;
		gbc.insets = new Insets(0,5,0,0);
		untenPanel.add(btn_kunde_exit,gbc);

		gbc.gridx = 7;
		gbc.insets = new Insets(0,20,0,0);
		untenPanel.add(sprache_combobox_List,gbc);
		
		sprache_combobox_List.setSelectedIndex(spracheID);

		//ActionListener
		sprache_combobox_List.addActionListener(this);
		sprache_combobox_List.setActionCommand("box");
		
		btn_kunde_create.addActionListener(this);
		btn_kunde_create.setActionCommand("create");

		btn_kunde_update.addActionListener(this);
		btn_kunde_update.setActionCommand("update");

		btn_kunde_delete.addActionListener(this);
		btn_kunde_delete.setActionCommand("delete");

		btn_kunde_search.addActionListener(this);
		btn_kunde_search.setActionCommand("search");

		btn_kunde_weinflasche.addActionListener(this);
		btn_kunde_weinflasche.setActionCommand("weinflasche");

		btn_kunde_report.addActionListener(this);
		btn_kunde_report.setActionCommand("report");

		btn_kunde_exit.addActionListener(this);
		btn_kunde_exit.setActionCommand("exit");
		


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

		if(e.getActionCommand().equals("weinflasche")){
			onWeinflascheClick();

		}

		if(e.getActionCommand().equals("exit")){
			exit();
		}
		
		if(e.getActionCommand().equals("box")){
			onBoxClick();
		}

		if(e.getActionCommand().equals("report")){
			onReportClick();
		}


	}


	private void onReportClick() {
		GuiReport guireport = new GuiReport(this);
		guireport.setVisible(true);
		
	}


	private void onBoxClick() {
		this.spracheID=sprache_combobox_List.getSelectedIndex();
		setSprache();
		getKundeTable(null,0,null,true);
		
	}


	private void onWeinflascheClick() {
		int kundeID = SelectedRowVonKunde();
		if (kundeID==-1)
			return;

		aktkundeID=kundeID;

		Kunde kunde = service.findByID(kundeID);
		GuiWeinflasche guiweinflasche = new GuiWeinflasche(this);
		guiweinflasche.setKunde(kunde);		
		guiweinflasche.setVisible(true);



	}


	private void onSearchClick() {
		GuiKundeSearch guikundesearch = new GuiKundeSearch(this);
		guikundesearch.setVisible(true);


	}




	private int SelectedRowVonKunde()
	{
		int row = kunde_table.getSelectedRow();

		if(row == -1)
		{
			JOptionPane.showMessageDialog(null, message[0]);

			return -1;
		}

		return (Integer)kunde_table.getValueAt(row, 0);

	}

	private void onCreateClick() {
		GuiKundeNeuEdit guikundeneuedit = new GuiKundeNeuEdit(this);
		editMode=false;
		guikundeneuedit.setTitle(sprache.kunde_neuedit_title[0][spracheID]);			
		guikundeneuedit.setVisible(true);

	}

	private void onUpdateClick() {

		int kundeID = SelectedRowVonKunde();
		if (kundeID==-1)
			return;


		Kunde kunde = service.findByID(kundeID);


		GuiKundeNeuEdit guikundeneuedit = new GuiKundeNeuEdit(this);
		editMode=true;
		guikundeneuedit.setTitle(sprache.kunde_neuedit_title[1][spracheID]);
		guikundeneuedit.setVisible(true);		
		guikundeneuedit.setKunde(kunde);		

	}

	private void onDeleteClick() {
		String message_delete_1="";
		int kundeID = SelectedRowVonKunde();
		if (kundeID==-1)
			return;


		Kunde kunde = service.findByID(kundeID);

		Vector<Weinflasche> list = service.findByKundeID(kundeID);

		if (list.size()>0){ 
			message_delete_1 =message[1];

		}
		int d = JOptionPane.showOptionDialog(null, 
				message_delete_1+message[2],"", 
				JOptionPane.YES_NO_OPTION, 
				JOptionPane.QUESTION_MESSAGE, null, null,null );

		if(d==JOptionPane.YES_OPTION){
			try {
				service.deleteKunde(kunde);
				getKundeTable(null,0,null,true);
			} catch (Exception ed) {
				JOptionPane.showMessageDialog(null, message[3] );

			}
		}				


	}


}
