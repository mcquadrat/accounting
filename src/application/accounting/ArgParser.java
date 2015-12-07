//Klasse ArgParser, die eine Programmzeile ausliesst und analysiert.
package application.accounting;
import gnu.getopt.*;
public class ArgParser {

    private String[] args = null;
    private boolean showHelp = false;
    private boolean showVersion = false;
    private String inputFilename = null;
    private String outputFilename = null;
    private String logFilename = null;
    private String nonOptions = null;
    private String interest = null;

    public ArgParser(String[] args) {

        this.args = args;
        parseArgs();
        
    } // end of special constructor "ArgParser(String[])"

		/*
		parseArgs() liesst Programmzeile aus
		*/
    private void parseArgs() {

        StringBuffer sb = null;
                               
        /*for ( int i = 0; i < args.length; i++ ) {

            if ( args[i].equals("-h") || args[i].equals("--help") ) {
                System.out.println("User asks for help");
                showHelp = true;
            } // end of if ( args[i].equals("-h") ... ) )
            else if ( args[i].equals("-v") || args[i].equals("--version") ) {
                System.out.println("User asks for the program's version");
                showVersion = true;
            } // end of else if ( args[i].equals("-v") ... )
            else if ( args[i].equals("-i") || args[i].equals("--input-file") ) {

                if ( i + 1 < args.length ) {
                    inputFilename = args[++i];
                } // end of if ( i + 1 < args.length )
                else {
                    throw new IllegalArgumentException("missing filename");
                } // end of if ( i + 1 < args.length ) else

            } // end of else if ( args[i].equals("-i") ... )
            else if ( args[i].equals("-o") || args[i].equals("--output-file") ) {

                if ( i + 1 < args.length ) {
                    outputFilename = args[++i];
                } // end of if ( i + 1 < args.length )
                else {
                    throw new IllegalArgumentException("missing filename");
                } // end of if ( i + 1 < args.length ) else

            } // end of else if ( args[i].equals("-o") ... )
            else if ( args[i].equals("-l") || args[i].equals("--log-file") ) {

                if ( i + 1 < args.length ) {
                    logFilename = args[++i];
                } // end of if ( i + 1 < args.length )
                else {
                    throw new IllegalArgumentException("missing filename");
                } // end of if ( i + 1 < args.length ) else

            } // end of else if ( args[i].equals("-l") ... )
            else {
                
                if ( sb == null ) {
                    sb = new StringBuffer();
                    sb.append(args[i]);
                } // end of if ( sb == null )
                else {
                    sb.append(" ").append(args[i]);
                } // end of if ( sb == null ) else

            } // end of if ( args[i].equals("-h") ... ) else
                
        } // end of for (int i = 0; i < args.length; i++)

        if ( sb != null ) {
            nonOptions = sb.toString();
        } // end of if (
        */
        LongOpt[] longopts = 
				{
					new LongOpt("input-file", LongOpt.REQUIRED_ARGUMENT, sb, 'i'),
					new LongOpt("output-file", LongOpt.REQUIRED_ARGUMENT, sb, 'o'),
					new LongOpt("log-file", LongOpt.REQUIRED_ARGUMENT, sb, 'l'),
					new LongOpt("rate-of-interest", LongOpt.REQUIRED_ARGUMENT, sb, 'r'),
					new LongOpt("help", LongOpt.NO_ARGUMENT, null, 'h'),
					new LongOpt("version", LongOpt.NO_ARGUMENT, null, 'v'),
				};
        
        
        Getopt g = new Getopt("Buchhaltung", args, "i:o:l:r:h:v", longopts);
        
        int c;
        String arg;
        while ((c = g.getopt()) != -1){
					switch(c){
						case 'i':
							arg = g.getOptarg();
							this.inputFilename = arg;break;
						case 'o':
							arg = g.getOptarg();
							this.outputFilename = arg;break;
						case 'l':
							arg = g.getOptarg();
							this.logFilename = arg;break;
						case 'r':
							arg = g.getOptarg();
							this.interest = arg;break;
						case 'h':
							this.showHelp = true;break;
						case 'v':
							this.showVersion = true;break;
						default:
							this.nonOptions = this.nonOptions + ", " + c;
					}
				}
        
        
        
        
        
    } // end of method "parseArgs()"


    @Override
    /*
    Analysiert die Eingabe und gibt die Analyse als String zurueck.
    */
    public String toString() {

        StringBuffer sb = new StringBuffer();

        for ( int i = 0; i < args.length; i++ ) {

            if ( args[i].equals("-h") || args[i].equals("--h") ||
                 args[i].equals("-v") || args[i].equals("--v") ) {

                sb.append(args[i]).append("\n");

            } // end of if ( args[i].equals("-h")  ...)
            else if ( args[i].equals("-i") || args[i].equals("--input-file") ||
                args[i].equals("-o") || args[i].equals("--output-file") ) {

                System.out.println("i: " + i + ", args.length: " + args.length);
                if ( i + 1 < args.length ) {
                    sb.append(args[i] + " " + args[++i]).append("\n");
                } // end of if ( i + 1 < args.length )
                else {
                    throw new IllegalArgumentException("missing filename");
                } // end of if ( i + 1 < args.length ) else

            } // end of else if ( args[i].equals("-i") ... )
            else if ( args[i].equals("-l") || args[i].equals("--log-file") ) {

                if ( i + 1 < args.length ) {

                    if ( args[i + 1].startsWith("-") ) {
                        sb.append(args[i]).append("\n");
                    } // end of if ( args[ i + 1].startsWith("-") )
                    else {
                        sb.append(args[i] + " " + args[++i]).append("\n");
                    } // end of if ( args[ i + 1].startsWith("-") ) else

                } // end of if ( i + 1 < args.length )

            } // end of else if ( args[i].equals("-i") ... )
            else {

                sb.append("non-option argument: " +
                          args[i]).append("\n");

            } // end of if ( args[i].equals("-h") || args[i].equals("--h") ) else

        } // end of for (int i = 0; i  < args.length; i++)

        return sb.toString();

    } // end of method "toString()"


    public boolean getShowHelp() {
        return showHelp;
    } // end of method "getShowHelp()"


    public boolean getShowVersion() {
        return showHelp;
    } // end of method "getShowVersion()"


    public String getInputFilename() {
        return inputFilename;
    } // end of method "getInputFilename()"


    public String getOutputFilename() {
        return outputFilename;
    } // end of method "getOutputFilename()"


    public String getLogFilename() {
        return logFilename;
    } // end of method "getLogFilename()"


    public String getNonOptions() {
        return nonOptions;
    } // end of method "getNonOptions()"

    public String getInterest() {
				return interest;
		}

    public static final void main(final String[] args) {
        ArgParser argParser = new ArgParser(args);
        System.out.println(argParser);
    } // end of method "main(String[] args)"

} // end of class "ArgParser"
