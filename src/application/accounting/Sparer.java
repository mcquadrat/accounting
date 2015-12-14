package application.accounting;

import java.util.*;

public class Sparer {

	private int mitgliedsnummer;
	private String name;
	private String vorname;
	private BigDecimal vorjahr;
	private Map<Integer, BigDecimal> zahlenpaare = new HashMap<>();
	private List<Integer> tage = new LinkedList<>();
	private BigDecimal p;
	
	public Sparer(int mitgliedsnummer, String name, String vorname, BigDecimal vorjahr, BigDecimal p){
		this.mitgliedsnummer = mitgliedsnummer;
		this.name = name;
		this.vorname = vorname;
		this.vorjahr = vorjahr;
		this.p = p;
	}
	
	public void setZahlenpaar(int tag, BigDecimal wert){
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
	
	public BigDecimal getGesamt(BigDecimal p){
		BigDecimal wert = new BigDecimal(this.vorjahr.mult(this.p.divide(100).add(1)));
		int i = 0;
		try{
			while (true){
				BigDecimal plus = new BigDecimal(this.zahlenpaare.get(this.tage.get(i)));
				//wert += (plus*(this.p/(100)*(360-this.tage.get(i))/(360)+1));
				wert.add(this.interestAmount(plus, this.p, this.tage.get(i)));
				i++;
			}
		} catch (IndexOutOfBoundsException e){}
		wert = wert.mult(100);
		//wert = Math.round(wert);
		/////wert.setScale(0, BigDecimal.ROUND_HALF_UP).toString();
		wert /= 100;
		return wert;
	}
	
	BigDecimal interestAmount(BigDecimal plus, BigDecimal p, int tage){
		return (plus.mult(p.divide(100).mult(360-tage).divide(360).add(1)));
	}
	
}
