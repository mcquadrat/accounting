package application.accounting;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.LinkedList;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;


public class Buchhaltung {
	private double p;
	private List<Sparer> sparer = new LinkedList<>();
	
	private static final Logger logger = Logger.getLogger(Buchhaltung.class.getName());
	
	public Buchhaltung(double p){
		this.p = p;
	}
	
	public static void call_main(String[] args) {
	
		String baseName = "Buchhaltung";
		//ResourceBundle rb = ResourceBundle.getBundle(baseName);
		//ersetzt durch:
		File file = new File("./dist/data/lang/");
		ResourceBundle rb = null;
		try {
			URL[] urls = {file.toURI().toURL()};
			ClassLoader loader = new URLClassLoader(urls);
			rb = ResourceBundle.getBundle(baseName, Locale.getDefault(), loader);
		} catch ( MalformedURLException e) {
			e.printStackTrace();
		}
		
		//Logger wird aktiviert
		try{
			String log = args[3];
			boolean append = true;
			FileHandler fh = new FileHandler(log, append);
			fh.setFormatter(new Formatter() {
					public String format(LogRecord rec) {
						StringBuffer buf = new StringBuffer(1000);
						buf.append(new java.util.Date()).append(' ');
						buf.append(rec.getLevel()).append(' ');
						buf.append(formatMessage(rec)).append(' ');
						return buf.toString();
					}
				});
			logger.addHandler(fh);
		}
		catch (IOException e) {
			logger.severe("Datei kann nicht geschrieben werden");
			e.printStackTrace();
		}
		//logger.setLevel(Level.ALL);
		
		
		String fileIn = args[0];
		double p = Double.parseDouble(args[1]);
		Buchhaltung b = new Buchhaltung(p);
		
		BufferedReader reader;
		String line;
		try{
			reader = new BufferedReader(new FileReader(fileIn));
			
			String readinput_msg = rb.getString("readinput_msg");
			logger.info(readinput_msg + ": " + fileIn);
			
			int zeile = 0;
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
				logger.info("gelesene Zeile: " + zeile);
				zeile++;
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
