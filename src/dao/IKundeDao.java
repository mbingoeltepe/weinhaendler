package dao;

import java.sql.Date;
import java.util.Vector;


import domain.Kunde;
import domain.Report;



/**
 * Data Access Object für Service.
 * 
 * @author MURAT BINGOELTEPE
 * @MatrikelNr 0627342
 * 
 */


public interface IKundeDao {

	/**
	 * In diesem Method wird die Kunde mit seiner id gesucht. 
	 * @param id 
	 * @throws Wenn beim Suchen ein Fehler auftritt, wird SQLException geworfen.
	 * @return die gefundene Kunde 
	 */	

	public Kunde findByID(int id);
	
	/**
	 * In diesem Method wird neue Kunde in der Tabelle von Kunde eingefügt. 
	 * @throws Wenn beim Einfügen ein Fehler auftritt, wird SQLException geworfen.
	 * @return die eingefügte Kunde  
	 */	
	
	public Kunde create(Kunde k);

	/**
	 * In diesem Method wird die Kunde in der Tabelle von Kunde geändert.  
	 * @throws Wenn bei der Änderung ein Fehler auftritt, wird SQLException geworfen.
	 * @return die geänderte Kunde   
	 */	

	public Kunde update(Kunde k);

	/**
	 * In diesem Method wird die Kunde Table von Kunde gelöscht. 
	 * @throws Wenn beim Löschen ein Fehler auftritt, wird SQLException geworfen.
	 * @return die gelöschte Kunde 
	 */	

	public Kunde delete(Kunde k);	
	
	/**
	 * Falls getAlle=false, In diesem Method wird die Kunde mit gegebenen Parametren gesucht. 
	 * @param nachname 
	 * @param plz
	 * @param geburtsdatum
	 * @param getAlle Wenn true ist wird alle Kunden returned. 
	 * @throws Wenn beim Löschen ein Fehler auftritt, wird SQLException geworfen.
	 * @return die gefundene Kunde 
	 */	
	
	public Vector<Kunde> searchKunde(String nachname, int plz,Date geburtsdatum, boolean getAlle);
	
	public Vector<Report> createReport(String ort, String geschlecht,String hersteller,String ursprungsland);
	
}
