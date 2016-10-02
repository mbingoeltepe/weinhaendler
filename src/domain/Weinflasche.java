/**
 * 
 */

package domain;


/**
 * 
 * @author MURAT BINGOELTEPE
 * @MatrikelNr 0627342
 * 
 */

public class Weinflasche {
		
	private int id;
	private int kid;
	private String bezeichnung="";
	private String hersteller="";
	private String ursprungsland="";
	private double vol;
	private double inhalt;
	private boolean imlager;

	
	public int getID() {
		return id;
	}
	
	public void setID(int id) {
		this.id = id;
	}


	public int getKID() {
		return kid;
	}

	public void setKID(int kid) {
		this.kid = kid;
	}

	public String getBezeichnung() {
		return bezeichnung;
	}
	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	public String getHersteller() {
		return hersteller;
	}
	public void setHersteller(String hersteller) {
		this.hersteller = hersteller;
	}

	public String getUrsprungsland() {
		return ursprungsland;
	}
	public void setUrsprungsland(String ursprungsland) {
		this.ursprungsland = ursprungsland;
	}

	public double getVol() {
		return vol;
	}
	public void setVol(double vol) {
		this.vol = vol;
	}
			
	public double getInhalt() {
		return inhalt;
	}
	public void setInhalt(double inhalt) {
		this.inhalt = inhalt;
	}
	
	public boolean getImLager() {
		return imlager;
	}
	public void setImLager(boolean imlager) {
		this.imlager = imlager;
	}

}
