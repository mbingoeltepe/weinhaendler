package services;

import java.sql.Date;
import java.util.Vector;

import dao.IKundeDao;
import dao.IWeinflascheDao;
import dao.KundeDao;
import dao.WeinflascheDao;
import domain.Kunde;
import domain.Report;
import domain.Weinflasche;

public class SimpleInsuranceService implements InsuranceService {

	private IKundeDao kundeDao=new KundeDao();
	private IWeinflascheDao weinflascheDao=new WeinflascheDao();



	//KundeDao
	@Override
	public Kunde createKunde(Kunde kunde) throws IllegalArgumentException {
		return kundeDao.create(kunde);
	}

	@Override
	public Kunde updateKunde(Kunde kunde) throws IllegalArgumentException {
		return kundeDao.update(kunde);
	}

	@Override
	public Kunde deleteKunde(Kunde kunde) throws IllegalArgumentException {
		return kundeDao.delete(kunde);
	}

	@Override
	public Kunde findByID(int id) throws IllegalArgumentException {

		return kundeDao.findByID(id);
	}

	@Override
	public Vector<Kunde> searchKunde(String nachname, int plz, Date geburtsdatum,
			boolean getAlle) throws IllegalArgumentException {

		return kundeDao.searchKunde(nachname, plz, geburtsdatum, getAlle);
	}

	//Weinflasche

	@Override
	public Weinflasche createWeinflasche(Weinflasche weinflasche)
	throws IllegalArgumentException {

		return weinflascheDao.create(weinflasche);
	}

	@Override
	public Weinflasche updateWeinflasche(Weinflasche weinflasche)
	throws IllegalArgumentException {

		return weinflascheDao.update(weinflasche);
	}

	@Override
	public Weinflasche deleteWeinflasche(Weinflasche weinflasche)
	throws IllegalArgumentException {

		return weinflascheDao.delete(weinflasche);
	}

	@Override
	public Weinflasche findByWeinflascheID(int id) throws IllegalArgumentException{

		return weinflascheDao.findByWeinflascheID(id);
	}

	@Override
	public Vector<Weinflasche> searchWeinflasche(String hersteller, String ursprungsland,
			boolean imlager, boolean getAlle, int kid) throws IllegalArgumentException {
		return weinflascheDao.searchWeinflasche(hersteller, ursprungsland, imlager, getAlle, kid);
	}

	@Override
	public Vector<Weinflasche> findByKundeID(int kid) throws IllegalArgumentException {

		return weinflascheDao.findByKundeID(kid);
	}

	//Report
	
	@Override
	public Vector<Report> createReport(String ort, String geschlecht,
			String hersteller, String ursprungsland) throws IllegalArgumentException {
		
		return kundeDao.createReport(ort, geschlecht, hersteller, ursprungsland);
	}




}
