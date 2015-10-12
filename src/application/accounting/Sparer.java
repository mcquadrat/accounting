package application.accounting;

import java.util.*;

public class Sparer {

	private int mitgliedsnummer;
	private String name;
	private String vorname;
	private double vorjahr;
	private Map<Integer, Double> zahlenpaare = new HashMap<>();
	private List<Integer> tage = new LinkedList<>();
	private double p;
	
	public Sparer(int mitgliedsnummer, String name, String vorname, double vorjahr, double p){
		this.mitgliedsnummer = mitgliedsnummer;
		this.name = name;
		this.vorname = vorname;
		this.vorjahr = vorjahr;
		this.p = p;
	}
	
	public void setZahlenpaar(int tag, double wert){
		this.zahlenpaare.put(tag, wert);
		this.tage.add(tag);
	}
	
	public int getMitgliedsnummer(){
		return this.mitgliedsnummer;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getVorname(){
		return this.vorname;
	}
	
	public double getGesamt(double p){
		double wert = this.vorjahr*(this.p/(100)+1);
		int i = 0;
		try{
			while (true){
				double plus = this.zahlenpaare.get(this.tage.get(i));
				wert += (plus*(this.p/(100)*(360-this.tage.get(i))/(360)+1));
				i++;
			}
		} catch (IndexOutOfBoundsException e){}
		wert *= 100;
		wert = Math.round(wert);
		wert /= 100;
		return wert;
	}
	
}
