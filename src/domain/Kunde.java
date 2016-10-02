/**
 * 
 */

package domain;

import java.sql.Date;

/**
 * 
 * @author MURAT BINGOELTEPE
 * @MatrikelNr 0627342
 * 
 */

public class Kunde {

	public static enum Typ {
		/**
		 *Verwenden Sie diesen Typ(auf Deutsch) für die maenliche Geschlecht der Kunde.	
		 */
		Maenlich,
		/**
		 *Verwenden Sie diesen Typ(auf Deutsch) für die weibliche Geschlecht der Kunde.	
		 */
		Weiblich
	};


	private int id;
	private String vorname="";
	private String nachname="";
	private String strasse="";
	private int plz;
	private String ort="";
	private Date geburtsdatum;
	private Typ geschlecht;

	public Typ getTyp() {
		return geschlecht;
	}

	public void setTyp(String geschlecht) {
		if (geschlecht.equals("Maenlich")) 
			this.geschlecht = Typ.Maenlich;
		if (geschlecht.equals("Weiblich")) 
			this.geschlecht = Typ.Weiblich;
	}

	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}

	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getStrasse() {
		return strasse;
	}
	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	public int getPlz() {
		return plz;
	}
	public void setPlz(int plz) {
		this.plz = plz;
	}

	public String getOrt() {
		return ort;
	}
	public void setOrt(String ort) {
		this.ort = ort;
	}

	public Date getGeburtsdatum() {
		return geburtsdatum;
	}
	public void setGeburtsdatum(Date geburtsdatum) {
		this.geburtsdatum = geburtsdatum;
	}


}
