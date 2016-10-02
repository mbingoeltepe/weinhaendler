package services;

import java.sql.Date;
import java.util.Vector;

import domain.Kunde;
import domain.Report;
import domain.Weinflasche;

/**
 * Service zwischen DAO und GUI Interface.
 * 
 * @author MURAT BINGOELTEPE
 * @MatrikelNr 0627342
 * 
 * Dieses Interface stellt alle Methoden gemaess der
 * Anwendungsfallbeschreibung	
 */
public interface InsuranceService {
	

	//KundeDao
	public Kunde createKunde(Kunde kunde) throws IllegalArgumentException;
	
	public Kunde updateKunde(Kunde kunde) throws IllegalArgumentException;

	public Kunde deleteKunde(Kunde kunde) throws IllegalArgumentException;
	
	public Kunde findByID(int id) throws IllegalArgumentException;

	public Vector<Kunde> searchKunde(String nachname, int plz,Date geburtsdatum, boolean getAlle) throws IllegalArgumentException;


	//WeinflascheDAO
	
	public Weinflasche createWeinflasche(Weinflasche weinflasche) throws IllegalArgumentException;

	public Weinflasche updateWeinflasche(Weinflasche weinflasche) throws IllegalArgumentException;

	public Weinflasche deleteWeinflasche(Weinflasche weinflasche) throws IllegalArgumentException;

	public Weinflasche findByWeinflascheID(int id) throws IllegalArgumentException;
	
	public Vector<Weinflasche> searchWeinflasche(String hersteller, String ursprungsland,boolean imlager,boolean getAlle, int kid) throws IllegalArgumentException;
	
	public Vector<Weinflasche> findByKundeID(int kid) throws IllegalArgumentException;

	// Report
	public Vector<Report> createReport(String ort, String geschlecht,String hersteller,String ursprungsland) throws IllegalArgumentException;
	
}
