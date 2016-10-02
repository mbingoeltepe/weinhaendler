package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import org.apache.log4j.Logger;

import db.HSQLDbHandler;
import domain.Weinflasche;

public class WeinflascheDao implements IWeinflascheDao{

	private static Logger logger = Logger.getLogger(WeinflascheDao.class);

	@Override
	public Vector<Weinflasche> findByKundeID(int kid) {
		Vector<Weinflasche> foundweinflasche = new Vector<Weinflasche>();

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


			query = "SELECT * FROM weinflasche WHERE kid="+kid+" ORDER BY id ASC"; 


		try{
			rs = stmt.executeQuery(query);
		}catch(SQLException e){

			e.printStackTrace();
		}

		logger.info(query);

		try {
			while(rs.next())
			{
				Weinflasche wflasche = new Weinflasche();

				wflasche.setID((Integer)rs.getObject("ID"));
				wflasche.setKID((Integer)rs.getObject("KID"));
				wflasche.setBezeichnung((String)rs.getObject("Bezeichnung"));
				wflasche.setHersteller((String)rs.getObject("Hersteller"));
				wflasche.setUrsprungsland((String)rs.getObject("Ursprungsland"));
				wflasche.setVol((Double)rs.getObject("Vol"));
				wflasche.setInhalt((Double)rs.getObject("Inhalt"));
				wflasche.setImLager((Boolean)rs.getObject("Imlager"));

				foundweinflasche.add(wflasche);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return foundweinflasche;

	}

	@Override
	public Weinflasche findByWeinflascheID(int id) {
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

		query = "SELECT * FROM weinflasche WHERE id="+id;

		logger.info(query);

		try{
			rs = stmt.executeQuery(query);
		}catch(SQLException e){


			e.printStackTrace();
		}

		Weinflasche wflasche = new Weinflasche();

		try {
			while(rs.next()){
				wflasche.setID((Integer)rs.getObject("ID"));
				wflasche.setKID((Integer)rs.getObject("KID"));
				wflasche.setBezeichnung((String)rs.getObject("Bezeichnung"));
				wflasche.setHersteller((String)rs.getObject("Hersteller"));
				wflasche.setUrsprungsland((String)rs.getObject("Ursprungsland"));
				wflasche.setVol((Double)rs.getObject("Vol"));
				wflasche.setInhalt((Double)rs.getObject("Inhalt"));
				wflasche.setImLager((Boolean)rs.getObject("Imlager"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return wflasche;

	}



	@Override
	public Weinflasche create(Weinflasche wf) {

		Connection con = HSQLDbHandler.getConnection();
		Statement stmt = null;
		String query = null;

		try{
			stmt = con.createStatement();
		}catch(SQLException e){

			e.printStackTrace();

		}	

		query = "INSERT INTO weinflasche VALUES(DEFAULT, '" + wf.getBezeichnung() + "','" 
		+ wf.getHersteller()+ "','" +wf.getUrsprungsland()+"'," + wf.getVol()+ "," + wf.getInhalt() + ",'" 
		+wf.getImLager()+"', "+ wf.getKID()+");";


		logger.info(query);

		try{
			stmt.executeUpdate(query);
		}catch(SQLException e){

			e.printStackTrace();

		}
		return wf;
	}



	@Override
	public Weinflasche update(Weinflasche wf) {

		Connection con = HSQLDbHandler.getConnection();
		Statement stmt = null;
		String query = null;

		try{
			stmt = con.createStatement();
		}catch(SQLException e){

			e.printStackTrace();

		}	

		query = "UPDATE weinflasche SET bezeichnung='" + wf.getBezeichnung() + "' , hersteller='" 
		+ wf.getHersteller()+ "' , ursprungsland='" +wf.getUrsprungsland()+"' , vol=" 
		+ wf.getVol()+ ",inhalt=" + wf.getInhalt() + " , imlager='" +wf.getImLager()+"'WHERE id="+wf.getID()+";";


		logger.info(query);

		try{
			stmt.executeUpdate(query);
		}catch(SQLException e){

			e.printStackTrace();

		}
		return wf;


	}



	@Override
	public Vector<Weinflasche> searchWeinflasche(String hersteller, String ursprungsland,
			boolean imlager,boolean getAlle,int kid) {
		Vector<Weinflasche> foundweinflasche = new Vector<Weinflasche>();

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
			query = "SELECT * FROM weinflasche WHERE kid="+kid+" ORDER BY id ASC";
		else{
			query = "SELECT * FROM weinflasche WHERE hersteller LIKE '%" + hersteller +"%' AND ursprungsland LIKE '%" 
			+ursprungsland+"%' AND imlager= " + imlager +" AND kid="+kid;
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
				Weinflasche wflasche = new Weinflasche();

				wflasche.setID((Integer)rs.getObject("ID"));
				wflasche.setKID((Integer)rs.getObject("KID"));
				wflasche.setBezeichnung((String)rs.getObject("Bezeichnung"));
				wflasche.setHersteller((String)rs.getObject("Hersteller"));
				wflasche.setUrsprungsland((String)rs.getObject("Ursprungsland"));
				wflasche.setVol((Double)rs.getObject("Vol"));
				wflasche.setInhalt((Double)rs.getObject("Inhalt"));
				wflasche.setImLager((Boolean)rs.getObject("Imlager"));

				foundweinflasche.add(wflasche);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return foundweinflasche;

	}

	@Override
	public Weinflasche delete(Weinflasche wf) {

		Connection con = HSQLDbHandler.getConnection();
		Statement stmt = null;
		String query = null;

		try{
			stmt = con.createStatement();
		}catch(SQLException e){

			e.printStackTrace();

		}	

		query = "DELETE FROM weinflasche WHERE  id=" + wf.getID();
		
		logger.info(query);

		try{			
			stmt.executeUpdate(query);
			
		}catch(SQLException e){
			
			e.printStackTrace();
		}
		return wf;

	}








}
