package gui;

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import services.InsuranceService;
import services.SimpleInsuranceService;
import services.Sprache;


import dao.IKundeDao;
import dao.KundeDao;
import domain.Report;



public class GuiReportErgebnisse extends JFrame{

	private JLabel	lbl = new JLabel("Reports");



	//Report column

	private String[] report_colnames= {"", "", "", "", "", "", "", "", "", ""};

	private Object[][] 	report_datas = {{"", "", "", "", "", "", "", "", "", "" }};

	public JTable report_table=new JTable(report_datas,report_colnames){
		public boolean isCellEditable(int row, int column)
		{
			return false;
		}

	};

	private JScrollPane scrl_report_table=new JScrollPane(report_table);
	private Object[][] datas = null;   


	private InsuranceService service=new SimpleInsuranceService();

	Sprache sprache=new Sprache();

	GuiReport report;

	public GuiReportErgebnisse(GuiReport report){
		super();
		this.report=report;


		setLayout(new BorderLayout());
		setSize(1200,300);



		this.add(lbl,BorderLayout.PAGE_START);
		this.add(scrl_report_table);


		
		getReportTable(report.txt_ort.getText(),report.geschlecht_combobox_List.getSelectedItem().toString(),report.txt_hersteller.getText(),report.txt_ursprungsland.getText());
		setSprache();
		
		
	}


	private void setSprache() {
		//Title
		setTitle(sprache.reportergebnisse_title[report.spracheID]);
		
		//Columsname
		ChangeName(report_table,0,sprache.reportergebnisse_table_strings[0][report.spracheID].toString());
		ChangeName(report_table,1,sprache.reportergebnisse_table_strings[1][report.spracheID].toString());
		ChangeName(report_table,2,sprache.reportergebnisse_table_strings[2][report.spracheID].toString());
		ChangeName(report_table,3,sprache.reportergebnisse_table_strings[3][report.spracheID].toString());
		ChangeName(report_table,4,sprache.reportergebnisse_table_strings[4][report.spracheID].toString());
		ChangeName(report_table,5,sprache.reportergebnisse_table_strings[5][report.spracheID].toString());
		ChangeName(report_table,6,sprache.reportergebnisse_table_strings[6][report.spracheID].toString());
		ChangeName(report_table,7,sprache.reportergebnisse_table_strings[7][report.spracheID].toString());
		ChangeName(report_table,8,sprache.reportergebnisse_table_strings[8][report.spracheID].toString());
		ChangeName(report_table,9,sprache.reportergebnisse_table_strings[9][report.spracheID].toString());

	}

	public void ChangeName(JTable table, int col_index, String col_name){
		table.getColumnModel().getColumn(col_index).setHeaderValue(col_name);
	}



	public void getReportTable(String ort, String geschlecht,String hersteller,String ursprungsland) {

		if (geschlecht=="Male") geschlecht="Maenlich";
		if (geschlecht=="Female") geschlecht="Weiblich";
		
		Vector<Report> list = service.createReport(ort, geschlecht, hersteller, ursprungsland);
		

		datas = new Object[list.size()][10];
		int i = 0;
		for(Report reports : list){
			datas[i][0] = reports.getID();
			datas[i][1] = reports.getVorname();
			datas[i][2] = reports.getNachname();
			datas[i][3] = reports.getStrasse();
			datas[i][4] = reports.getPlz();
			datas[i][5] = reports.getOrt();
			datas[i][6] = reports.getGeburtsdatum();
			datas[i][7] = reports.getTyp();
			
			if (report.spracheID==1){
				if (reports.getTyp().toString()=="Maenlich")
					datas[i][7] = "Male";
				else
					datas[i][7] = "Female";					
			}

			datas[i][8] = reports.getHersteller();
			datas[i][9] = reports.getUrsprungsland();

			i++;
		}
		DefaultTableModel tm = new DefaultTableModel(datas, report_colnames);
		report_table.setModel(tm);


	}





}
