package services;



public class Sprache {
	
	//GuiKunde
	
	public String[][] kunde_button_strings={{"Neu","Create"},{"Bearbeiten","Update"}
	,{"Löschen","Delete"},{"Suchen","Search"},{"Weinflasche","Wine bottle"}
	,{"Beenden","Exit"}};
	
	public String[][] kunde_table_strings={{"ID","ID"},{"Vorname","First Name"}
	,{"Nachname","Surname"},{"Strasse","Street"},{"Plz","Postcode"}
	,{"Ort","Lokation"},{"Geburtsdatum","Birtdate"},{"Geschlecht","Gender"}};

	public String[] kunde_title={"Weinhaendler","Wine Merchants"};
	public String[] kunde_label={"Kunde Daten","Customer information"};
	
	public String[][] kunde_message={{"Sie müssen eine Kunde auswählen.","You must select a customer."},
			{"Die Weinflasche von diese kunde werden auch gelöscht.","The bottle of wine from these customers will also be deleted."},
			{"Sind Sie sicher?","Are you sure?"},{" Kann nicht gelöscht werden!"," Can not be deleted!"}};
	
	//GuiKundeNeuEdit
	
	public String[][] kunde_neuedit_title={{"Neue Kunde","New Customer"},{"Bearbeiten","Update"}};
	
	public String[][] kunde_neuedit_label_strings={{"* Vorname","* First Name"}
	,{"* Nachname","* Surname"},{"Strasse","Street"},{"Plz (1000 < plz < 99999)","Postcode (1000 < postcode < 99999)"}
	,{"Ort","Lokation"},{"Geburtsdatum (yyyy-mm-dd)","Birtdate (yyyy-mm-dd)"},{"Geschlecht","Gender"}
	,{"* Diese Felder(Stern) müssen","* These fields(Star) must."},{"ausgefüllt werden.","be filled"}};
	
	public String[][] kunde_neuedit_cbox={{"Maenlich","Male"},{"Weiblich","Female"}};
	
	public String[][] kunde_neuedit_message={{"Speichern nicht möglich, Überprüfen Sie die Werte","Can not save, check the values"},
			{" Neue Kunde wurde erfolgreich gespeichert.","New customer has been successfully saved."},
			{"Kunde wurde erfolgreich geändert","Customer has been successfully changed"}};

	public String[] kunde_neuedit_button_strings={"ABBRECHEN","CLOSE"};

	//GuiKundeSearch
	
	public String[] kunde_search_title={"Suchen","Search"};
	
	public String[][] kunde_search_label_strings={{"Nachname","Surname"}
	,{"Plz (1000 < plz < 99999)","Postcode (1000 < postcode < 99999)"}
	,{"Geburtsdatum (yyyy-mm-dd)","Birtdate (yyyy-mm-dd)"},{"Alle zeigen","Show All"}};
	
	public String[] kunde_search_button_strings={"ABBRECHEN","CLOSE"};
	
	public String[] kunde_search_message={"Suchen nicht möglich, Überprüfen Sie die Werte","Can not search, check the values"};
	
	//GuiWeinflasche
	
	public String[][] weinflasche_button_strings={{"Neu","Create"},{"Bearbeiten","Update"}
	,{"Löschen","Delete"},{"Suchen","Search"},{"Beenden","Exit"}};
	
	public String[] weinflasche_title={"Weinflasche","Wine bottle"};
	
	public String[][] weinflasche_table_strings={{"ID","ID"},{"Bezeichnung","Title"}
	,{"Hersteller","Producer"},{"Ursprungsland","Country of origin"},{"Vol","Vol"}
	,{"Inhalt","Contents"},{"Imlager","In stock"}};
	
	public String[][] weinflasche_message={{"Sie müssen eine Weinflasche auswählen.","You must select a Wine bottle."}
			,{"Sind Sie sicher?","Are you sure?"},{"Diese Kunde im Datenbank nicht mehr registiert"," This customer has not registered in the database"}
			,{" Kann nicht gelöscht werden!"," Can not be deleted!"}};
	
	//GuiWeinflascheNeuEdit
	
	public String[][] weinflasche_neuedit_title={{"Neue Weinflasche für KundeID=","New Wine bottle for CustomerID="},{"Bearbeiten Weinflasche für KundeID=","Update Wine bottle for CustomerID="}};
	
	public String[][] weinflasche_neuedit_label_strings={{"Bezeichnung","Title"}
	,{"Hersteller","Producer"},{"Ursprungsland","Country of origin"},{"Vol","Vol"}
	,{"Inhalt","Contents"},{"Imlager","In stock"}};

	public String[] weinflasche_neuedit_button_strings={"ABBRECHEN","CLOSE"};
	
	public String[][] weinflasche_neuedit_message={{"Speichern nicht möglich, Sie müssen mindestens einen Felder ausfüllen","Can not save, A field must be filled"},
			{" Neue Weinflasche wurde erfolgreich gespeichert.","New Wine bottle has been successfully saved."},
			{"Weinflasche wurde erfolgreich geändert","Wine bottle has been successfully changed"}};

	//GuiWeinflascheSearch
	
	public String[] weinflasche_search_title={"Suchen","Search"};
	
	public String[][] weinflasche_search_label_strings={{"Hersteller","Producer"}
	,{"Ursprungsland","Country of origin"},{"Imlager","In stock"},{"Alle zeigen","Show All"}};
	
	public String[] weinflasche_search_button_strings={"ABBRECHEN","CLOSE"};
	
	public String[] weinflasche_search_message={"Suchen nicht möglich, Überprüfen Sie die Werte","Can not Search, check the values"};

	//GuiReport

	public String[][] report_label_strings={{"Ort","Location"}
	,{"Geschlecht","Gender"},{"Hersteller","Producer"},{"Ursprungsland","Country of origin"}};

	public String[] report_button_strings={"ABBRECHEN","CLOSE"};
	
	public String[][] report_neuedit_cbox={{"",""},{"Maenlich","Male"},{"Weiblich","Female"}};

	//GuiReportErgebnisse
	public String[] reportergebnisse_title={"Report Ergebnisse","Report results"};
	
	public String[][] reportergebnisse_table_strings={{"ID","ID"},{"Vorname","First Name"}
	,{"Nachname","Surname"},{"Strasse","Street"},{"Plz","Postcode"}
	,{"Ort","Lokation"},{"Geburtsdatum","Birtdate"},{"Geschlecht","Gender"}
	,{"Hersteller","Producer"},{"Ursprungsland","Country of origin"}};


	
}
