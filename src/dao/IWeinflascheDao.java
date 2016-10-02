package dao;

import java.util.Vector;

import domain.Weinflasche;




/**
 * Data Access Object für Service.
 * 
 * @author MURAT BINGOELTEPE
 * @MatrikelNr 0627342
 *
 */


public interface IWeinflascheDao {

	/**
	 * In diesem Method wird die Weinflasche mit seiner id gesucht. 
	 * @param id 
	 * @throws Wenn beim Suchen ein Fehler auftritt, wird SQLException geworfen.
	 * @return die gefundene Weinflasche 
	 */	
	
	public Weinflasche findByWeinflascheID(int id);
	
	/**
	 * In diesem Method wird die Weinflasche mit seiner kid(Kunden id) gesucht. 
	 * @param kid 
	 * @throws Wenn beim Suchen ein Fehler auftritt, wird SQLException geworfen.
	 * @return die gefundene Weinflasche 
	 */	

	public Vector<Weinflasche> findByKundeID(int kid);

	/**
	 * In diesem Method wird neue Weinflasche in der Tabelle von Weinflasche eingefügt. 
	 * @throws Wenn beim Einfügen ein Fehler auftritt, wird SQLException geworfen.
	 * @return die eingefügte Weinflasche 
	 */	
	
	public Weinflasche create(Weinflasche wf);
	
	/**
	 * In diesem Method wird die Weinflasche in der Tabelle von Weinflasche geändert.  
	 * @throws Wenn bei der Änderung ein Fehler auftritt, wird SQLException geworfen.
	 * @return die geänderte Weinflasche 
	 */	

	public Weinflasche update(Weinflasche wf);
	
	/**
	 * In diesem Method wird die Weinflasche Table von Weinflasche gelöscht. 
	 * @throws Wenn beim Löschen ein Fehler auftritt, wird SQLException geworfen.
	 * @return die gelöschte Weinflasche 
	 */	

	public Weinflasche delete(Weinflasche wf);	
	
	/**
	 * Falls getAlle=false, In diesem Method wird die Weinflasche mit gegebenen Parametren gesucht. 
	 * @param hersteller 
	 * @param ursprungsland
	 * @param imlager
	 * @param getAlle Wenn true ist wird alle Kunden returned.
	 * @param kid 
	 * @throws Wenn beim Löschen ein Fehler auftritt, wird SQLException geworfen.
	 * @return die gefundene Weinflasche 
	 */	
	
	public Vector<Weinflasche> searchWeinflasche(String hersteller, String ursprungsland,boolean imlager,boolean getAlle, int kid);
				
	
}
