package application.accounting;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Buchhaltung {
	private double p;
	private List<Sparer> sparer = new LinkedList<>();
	
	public Buchhaltung(double p){
		this.p = p;
	}
	
	public static void call_main(String[] args) {
		String fileIn = args[0];
		double p = Double.parseDouble(args[1]);
		Buchhaltung b = new Buchhaltung(p);
		
		BufferedReader reader;
		String line;
		try{
			reader = new BufferedReader(new FileReader(fileIn));
			while ((line = reader.readLine()) != null){
				if (line.startsWith("#")){
					System.out.println(line);
					continue;
				}
				int j = 0;
				try{
					while (line.charAt(j) == ' '){
						j++;
					}
				}catch (StringIndexOutOfBoundsException e){
					System.out.println(line);
					continue;
				}
				line = line.replace(',', '.');
				b.setSparer(line);
			}
		} catch (IOException e){
			System.out.println("Fehler beim Lesen der Datei!");
		}
		
		
	}
	
	
	
	public void setSparer(String line){
		String splitBy = ";";
		String[] daten = line.split(splitBy);
		Sparer s = new Sparer(Integer.parseInt(daten[0]), daten[1], daten[2], Double.parseDouble(daten[3]), this.p);
		try {
			int i = 4;
			while (true){
				s.setZahlenpaar(Integer.parseInt(daten[i]), Double.parseDouble(daten[i+1]));
				i+=2;
			}
		} catch (ArrayIndexOutOfBoundsException e){}
		
		
		System.out.println(daten[0]+";"+daten[1]+";"+daten[2]+";"+s.getGesamt(this.p));
	}
}
