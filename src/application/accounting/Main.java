package application.accounting;

public class Main{
	public static void main(String []args){
		if (args.length==0){
			Buchhaltung.call_main(args);
		} else {
			ArgParser ap = new ArgParser(args);
			args[0] = ap.getInputFilename();
			args[1] = ap.getInterest();
			args[2] = ap.getOutputFilename();
			args[3] = ap.getLogFilename();
			Buchhaltung.call_main(args);
		}
	}
}