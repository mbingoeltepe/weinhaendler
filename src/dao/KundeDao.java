package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import org.apache.log4j.Logger;


import db.HSQLDbHandler;
import domain.Kunde;
import domain.Report;

public class KundeDao implements IKundeDao{

	private static Logger logger = Logger.getLogger(KundeDao.class);

	@Override
	public Kunde findByID(int id) {
		Connection con = HSQLDbHandler.getConnection();
		Statement stmt = null;
		String query = null;
		ResultSet rs = null;

		try{
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
		}catch(SQLException e){

			e.printStackTrace();
		}

		query = "SELECT * FROM kunde WHERE id="+id;

		logger.info(query);

		try{
			rs = stmt.executeQuery(query);
		}catch(SQLException e){


			e.printStackTrace();
		}

		Kunde kunde = new Kunde();

		try {
			while(rs.next()){
				kunde.setID((Integer)rs.getObject("ID"));
				kunde.setVorname((String)rs.getObject("Vorname"));
				kunde.setNachname((String)rs.getObject("Nachname"));
				kunde.setStrasse((String)rs.getObject("Strasse"));
				kunde.setPlz((Integer)rs.getObject("Plz"));
				kunde.setOrt((String)rs.getObject("Ort"));
				kunde.setGeburtsdatum((Date)rs.getObject("Geburtsdatum"));
				kunde.setTyp((String)rs.getObject("Geschlecht"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return kunde;

	}



	@Override
	public Kunde create(Kunde k) {

		Connection con = HSQLDbHandler.getConnection();
		Statement stmt = null;
		String query = null;

		try{
			stmt = con.createStatement();
		}catch(SQLException e){

			e.printStackTrace();

		}	

		query = "INSERT INTO kunde VALUES(DEFAULT,'" + k.getVorname() + "','" 
		+ k.getNachname()+ "','" +k.getStrasse()+"'," + k.getPlz()+ ",'" + k.getOrt() + "','" 
		+k.getGeburtsdatum()+"','"+k.getTyp()+"');";


		logger.info(query);

		try{
			stmt.executeUpdate(query);
		}catch(SQLException e){

			e.printStackTrace();

		}
		return k;
	}

	@Override
	public Vector<Kunde> searchKunde(String nachname, int plz, Date geburtsdatum,
			boolean getAlle) {

		Vector<Kunde> foundkunde = new Vector<Kunde>();

		Connection con = HSQLDbHandler.getConnection();
		Statement stmt = null;
		String query = null;
		ResultSet rs = null;

		try{
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
		}catch(SQLException e){

			e.printStackTrace();

		}


		if (getAlle)	
			query = "SELECT * FROM kunde;";
		else{
			query = "SELECT * FROM kunde WHERE nachname LIKE '%" + nachname+"%'"; 
			if (plz != -1) query +=" AND plz = " + plz;
			if (geburtsdatum != null) query +=" AND geburtsdatum = '" + geburtsdatum +"'";
		}


		try{
			rs = stmt.executeQuery(query);
		}catch(SQLException e){

			e.printStackTrace();
		}

		logger.info(query);

		try {
			while(rs.next())
			{
				Kunde kunde = new Kunde();

				kunde.setID((Integer)rs.getObject("ID"));
				kunde.setVorname((String)rs.getObject("Vorname"));
				kunde.setNachname((String)rs.getObject("Nachname"));
				kunde.setStrasse((String)rs.getObject("Strasse"));
				kunde.setPlz((Integer)rs.getObject("Plz"));
				kunde.setOrt((String)rs.getObject("Ort"));
				kunde.setGeburtsdatum((Date)rs.getObject("Geburtsdatum"));
				kunde.setTyp((String)rs.getObject("Geschlecht"));
				foundkunde.add(kunde);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return foundkunde;
	}



	@Override
	public Kunde update(Kunde k) {

		Connection con = HSQLDbHandler.getConnection();
		Statement stmt = null;
		String query = null;

		try{
			stmt = con.createStatement();
		}catch(SQLException e){

			e.printStackTrace();

		}	

		query = "UPDATE kunde SET vorname='" + k.getVorname() + "' , nachname='" 
		+ k.getNachname()+ "' , strasse='" +k.getStrasse()+"' , plz=" 
		+ k.getPlz()+ ",ort='" + k.getOrt() + "' , geburtsdatum='" 
		+k.getGeburtsdatum()+"', geschlecht='"+k.getTyp()+"' WHERE id="+k.getID()+";";


		logger.info(query);

		try{
			stmt.executeUpdate(query);
		}catch(SQLException e){

			e.printStackTrace();

		}
		return k;


	}



	@Override
	public Kunde delete(Kunde k) {
		Connection con = HSQLDbHandler.getConnection();
		Statement stmt = null;
		String query = null;

		try{
			stmt = con.createStatement();
		}catch(SQLException e){

			e.printStackTrace();

		}	

		query = "DELETE FROM kunde WHERE  id=" + k.getID();
		
		logger.info(query);

		try{			
			stmt.executeUpdate(query);
			
		}catch(SQLException e){
			
			e.printStackTrace();
		}
		return k;



		
	}






	@Override
	public Vector<Report> createReport(String ort, String geschlecht,
			String hersteller, String ursprungsland) {

		Vector<Report> reports = new Vector<Report>();

		Connection con = HSQLDbHandler.getConnection();
		Statement stmt = null;
		String query = null;
		ResultSet rs = null;

		try{
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
		}catch(SQLException e){

			e.printStackTrace();

		}


			query = "SELECT k.*, w.hersteller,w.ursprungsland "+
					"FROM kunde k,weinflasche w "+
					"WHERE k.ort LIKE '%"+ort+"%'"+
					" AND k.geschlecht LIKE '%"+geschlecht+"%'"+
					" AND w.hersteller LIKE '%"+hersteller+"%'"+
					" AND w.ursprungsland LIKE '%"+ursprungsland+"%'"+
					" AND k.ID=w.KID; ";

		try{ 
			rs = stmt.executeQuery(query);
		}catch(SQLException e){

			e.printStackTrace();
		}

		logger.info(query);

		try {
			while(rs.next())
			{
				Report report = new Report();

				report.setID((Integer)rs.getObject("ID"));
				report.setVorname((String)rs.getObject("Vorname"));
				report.setNachname((String)rs.getObject("Nachname"));
				report.setStrasse((String)rs.getObject("Strasse"));
				report.setPlz((Integer)rs.getObject("Plz"));
				report.setOrt((String)rs.getObject("Ort"));
				report.setGeburtsdatum((Date)rs.getObject("Geburtsdatum"));
				report.setTyp((String)rs.getObject("Geschlecht"));
				report.setHersteller((String)rs.getObject("Hersteller"));
				report.setUrsprungsland((String)rs.getObject("Ursprungsland"));
				reports.add(report);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return reports;
	
	}





}
