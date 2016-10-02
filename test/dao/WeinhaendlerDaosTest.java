package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import services.InsuranceService;
import services.SimpleInsuranceService;
import static org.junit.Assert.*;
import db.HSQLDbHandler;
import domain.Kunde;
import domain.Weinflasche;





public class WeinhaendlerDaosTest {

	
	private InsuranceService service;
	
	private Kunde k = new Kunde();
	private Weinflasche wf = new Weinflasche();


	Connection hsqldbhandler;

	@Before
	public void setUp() throws Exception {
		service=new SimpleInsuranceService();
		
		hsqldbhandler = HSQLDbHandler.getConnection();

		

		try{
		hsqldbhandler.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
		}catch(SQLException e){

			e.printStackTrace();
		}
		HSQLDbHandler.getConnection().setAutoCommit(false);
	}

	@After
	public void tearDown() throws Exception {
		HSQLDbHandler.getConnection().rollback();
	}

	@Test
	public void create_search_Kunde(){
		Kunde kunde = new Kunde();
		
		kunde.setVorname("Gerhard");
		kunde.setNachname("Huber");
		kunde.setStrasse("Linzer str.");
		kunde.setPlz(1210);
		kunde.setOrt("Wien");
		kunde.setGeburtsdatum(Date.valueOf("1980-1-1"));
		kunde.setTyp("Maenlich");

		service.createKunde(kunde);

		Vector<Kunde> List_create_search_Kunde = service.searchKunde(kunde.getNachname(),kunde.getPlz(),kunde.getGeburtsdatum(),false);
		
		k = List_create_search_Kunde.get(List_create_search_Kunde.size()-1);
		
		assertTrue(k.getVorname().toString().equals("Gerhard"));
		assertTrue(k.getNachname().toString().equals("Huber"));
		assertTrue(k.getStrasse().toString().equals("Linzer str."));
		
		service.deleteKunde(k);

		
	}

	@Test
	public void update_Kunde(){
		Kunde kunde = new Kunde();

		kunde.setVorname("Gerhard");
		kunde.setNachname("Huber");
		kunde.setStrasse("Linzer str.");
		kunde.setPlz(1210);
		kunde.setOrt("Wien");
		kunde.setGeburtsdatum(Date.valueOf("1980-1-1"));
		kunde.setTyp("Maenlich");


		service.createKunde(kunde);

		Vector<Kunde> List_update_Kunde = service.searchKunde(kunde.getNachname(),kunde.getPlz(),kunde.getGeburtsdatum(),false);
		k = List_update_Kunde.get(List_update_Kunde.size()-1);
		
		k.setVorname("Stephan");
		k.setNachname("Hofer");
		k.setStrasse("Wiener str.");		

		service.updateKunde(k);
		
		Vector<Kunde> List_update_Kunde_1 = service.searchKunde(k.getNachname(),k.getPlz(),k.getGeburtsdatum(),false);
		k = List_update_Kunde_1.get(List_update_Kunde_1.size()-1);
		
		assertTrue(k.getVorname().toString().equals("Stephan"));
		assertTrue(k.getNachname().toString().equals("Hofer"));
		assertTrue(k.getStrasse().toString().equals("Wiener str."));

		service.deleteKunde(k);

	}

	@Test
	public void delete_Kunde(){
		Kunde kunde = new Kunde();

		kunde.setVorname("Gerhard");
		kunde.setNachname("Huber");
		kunde.setStrasse("Linzer str.");
		kunde.setPlz(1210);
		kunde.setOrt("Wien");
		kunde.setGeburtsdatum(Date.valueOf("1980-1-1"));
		kunde.setTyp("Maenlich");

		service.createKunde(kunde);

		Vector<Kunde> List_delete_Kunde = service.searchKunde(kunde.getNachname(),kunde.getPlz(),kunde.getGeburtsdatum(),false);
		k = List_delete_Kunde.get(List_delete_Kunde.size()-1);
		
		service.deleteKunde(k);
		
		Vector<Kunde> List_delete_Kunde_1 = service.searchKunde(kunde.getNachname(),kunde.getPlz(),kunde.getGeburtsdatum(),false);

		assertTrue(List_delete_Kunde_1.isEmpty());
	}
	
	@Test
	public void create_search_Weinflasche(){
		Kunde kunde = new Kunde();

		kunde.setVorname("Gerhard");
		kunde.setNachname("Huber");
		kunde.setStrasse("Linzer str.");
		kunde.setPlz(1210);
		kunde.setOrt("Wien");
		kunde.setGeburtsdatum(Date.valueOf("1980-1-1"));
		kunde.setTyp("Maenlich");

		service.createKunde(kunde);

		Vector<Kunde> List_create_search_Weinflasche = service.searchKunde(kunde.getNachname(),kunde.getPlz(),kunde.getGeburtsdatum(),false);
		k = List_create_search_Weinflasche.get(List_create_search_Weinflasche.size()-1);
		
		Weinflasche weinflasche = new Weinflasche();

		weinflasche.setKID(k.getID());
		weinflasche.setBezeichnung("A");
		weinflasche.setHersteller("A");
		weinflasche.setUrsprungsland("A");
		weinflasche.setImLager(true);
		weinflasche.setVol(1);
		weinflasche.setInhalt(1);
		
		service.createWeinflasche(weinflasche);
		
		Vector<Weinflasche> List_create_search_Weinflasche_1 = service.searchWeinflasche(weinflasche.getHersteller(), weinflasche.getUrsprungsland(), weinflasche.getImLager(), false, weinflasche.getKID());
		wf = List_create_search_Weinflasche_1.get(List_create_search_Weinflasche_1.size()-1);
		
		
		assertTrue(wf.getBezeichnung().toString().equals("A"));
		assertTrue(wf.getHersteller().toString().equals("A"));
		assertTrue(wf.getImLager());

		service.deleteWeinflasche(wf);
		service.deleteKunde(k);



		
	}

	@Test
	public void update_Weinflasche(){
		Kunde kunde = new Kunde();

		kunde.setVorname("Gerhard");
		kunde.setNachname("Huber");
		kunde.setStrasse("Linzer str.");
		kunde.setPlz(1210);
		kunde.setOrt("Wien");
		kunde.setGeburtsdatum(Date.valueOf("1980-1-1"));
		kunde.setTyp("Maenlich");

		service.createKunde(kunde);

		Vector<Kunde> List_update_Weinflasche = service.searchKunde(kunde.getNachname(),kunde.getPlz(),kunde.getGeburtsdatum(),false);
		k = List_update_Weinflasche.get(List_update_Weinflasche.size()-1);
		
		Weinflasche weinflasche = new Weinflasche();

		weinflasche.setKID(k.getID());
		weinflasche.setBezeichnung("A");
		weinflasche.setHersteller("A");
		weinflasche.setUrsprungsland("A");
		weinflasche.setImLager(true);
		weinflasche.setVol(1);
		weinflasche.setInhalt(1);
		
		service.createWeinflasche(weinflasche);
		
		Vector<Weinflasche> List_update_Weinflasche_1 = service.searchWeinflasche(weinflasche.getHersteller(), weinflasche.getUrsprungsland(), weinflasche.getImLager(), false, weinflasche.getKID());
		wf = List_update_Weinflasche_1.get(List_update_Weinflasche_1.size()-1);
		
		wf.setBezeichnung("B");
		wf.setHersteller("B");
		wf.setImLager(false);
		
		service.updateWeinflasche(wf);

		Vector<Weinflasche> List_update_Weinflasche_2 = service.searchWeinflasche(wf.getHersteller(), wf.getUrsprungsland(), wf.getImLager(), false, wf.getKID());
		wf = List_update_Weinflasche_2.get(List_update_Weinflasche_1.size()-1);

		
		assertTrue(wf.getBezeichnung().toString().equals("B"));
		assertTrue(wf.getHersteller().toString().equals("B"));
		assertFalse(wf.getImLager());

		service.deleteWeinflasche(wf);
		service.deleteKunde(k);


		
	}

	@Test
	public void delete_Weinflasche(){
		Kunde kunde = new Kunde();

		kunde.setVorname("Gerhard");
		kunde.setNachname("Huber");
		kunde.setStrasse("Linzer str.");
		kunde.setPlz(1210);
		kunde.setOrt("Wien");
		kunde.setGeburtsdatum(Date.valueOf("1980-1-1"));
		kunde.setTyp("Maenlich");

		service.createKunde(kunde);

		Vector<Kunde> List_delete_Weinflasche = service.searchKunde(kunde.getNachname(),kunde.getPlz(),kunde.getGeburtsdatum(),false);
		k = List_delete_Weinflasche.get(List_delete_Weinflasche.size()-1);
		
		Weinflasche weinflasche = new Weinflasche();

		weinflasche.setKID(k.getID());
		weinflasche.setBezeichnung("A");
		weinflasche.setHersteller("A");
		weinflasche.setUrsprungsland("A");
		weinflasche.setImLager(true);
		weinflasche.setVol(1);
		weinflasche.setInhalt(1);
		
		service.createWeinflasche(weinflasche);
		Vector<Weinflasche> List_delete_Weinflasche_1 = service.searchWeinflasche(weinflasche.getHersteller(), weinflasche.getUrsprungsland(), weinflasche.getImLager(), false, weinflasche.getKID());
		wf = List_delete_Weinflasche_1.get(List_delete_Weinflasche_1.size()-1);
		
		service.deleteWeinflasche(wf);


		Vector<Weinflasche> List_delete_Weinflasche_2 = service.searchWeinflasche(weinflasche.getHersteller(), weinflasche.getUrsprungsland(), weinflasche.getImLager(), false, weinflasche.getKID());

		assertTrue(List_delete_Weinflasche_2.isEmpty());

		
		service.deleteKunde(k);


		
	}

}
