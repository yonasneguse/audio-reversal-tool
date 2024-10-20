import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Read a .dat file and reverse it.
 *
 *
 */
public class BackMasking{

    //@SuppressWarnings("unused")
    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println(" Incorrect number of arguments");
            System.err.println(" Usage: ");
            System.err.println("\tjava BackMasking <stack type: list/array> <input file> <output file>");
            System.exit(1);
        }

        boolean useList = true;
        if (args[0].compareTo("list") == 0)
            useList = true;
        else if (args[0].compareTo("array") == 0)
            useList = false;
        else {
            System.err.println("\tsaw " + args[0]
                    + " instead of list or array as first argument");
            System.exit(1);
        }

        try {

            BufferedReader fileIn = new BufferedReader(new FileReader(args[1]));
            PrintWriter fileOut = new PrintWriter(new BufferedWriter(
                    new FileWriter(args[2])));


            StringTokenizer str;
            String oneLine;
            int sampleRate;
            String strJunk;

            oneLine = fileIn.readLine();

            str = new StringTokenizer(oneLine);
            strJunk = str.nextToken();
            strJunk = str.nextToken();
            strJunk = str.nextToken();


            sampleRate = Integer.parseInt(str.nextToken());



            BKStack s;
            if (useList)
                s = new ListStack();
            else
                s = new ArrayStack();
            String timestep;
            double data;

            while ((oneLine = fileIn.readLine()) != null) {
                if (oneLine.charAt(0) == ';') {
                    continue;
                }
                str = new StringTokenizer(oneLine);


                timestep = str.nextToken();

                data = Double.parseDouble(str.nextToken());
                s.push(data);
            }

            System.out.println(s.count() + " samples in file");



            fileOut.println("; Sample Rate " + sampleRate);


            int numSteps = 0;

            while (!s.isEmpty()) {
                fileOut.println((double) numSteps / sampleRate + "\t" + s.pop());
                numSteps++;
            }
            fileIn.close();
            fileOut.close();

        } catch (IOException ioe) {
            System.err
                    .println("Error opening/reading/writing input or output file.");
            System.exit(1);
        } catch (NumberFormatException nfe) {
            System.err.println(nfe.toString());
            System.err.println("Error in file format");
            System.exit(1);
        }
    }
}