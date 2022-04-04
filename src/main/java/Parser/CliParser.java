package Parser;

import org.apache.commons.cli.*;

public class CliParser {

    public static int N;
    public static int D;

    private Options getCommandlineOptions() {
        Options options = new Options();
        options.addOption("n", true, "total amount of particles");
        options.addOption("N", true, "total amount of particles");

        options.addOption("d", true, "gap size (in cells)");
        options.addOption("D", true, "gap size (in cells)");

        options.addOption("h", false, "help");
        options.addOption("help", false, "help");

        return options;
    }

    private static void help() {
        System.out.println("Please remember the valid options are: ");
        System.out.println("-n or -N <value> to specify the number of particles,");
        System.out.println("-d or -D <value> to specify the size in cells of the gap,");
        System.out.println("-h or -help to see the menu.");
    }

    public CliParser(String[] args) {
        Options options = getCommandlineOptions();
        CommandLineParser commandLineParser = new DefaultParser();
        try {
            CommandLine commandLine = commandLineParser.parse(options, args);

            if (commandLine.hasOption("h") || commandLine.hasOption("help")) {
                help();
                System.exit(1);
            }
            if (commandLine.hasOption("n")) {
                N = Integer.parseInt(commandLine.getOptionValue("n"));
            } else if (commandLine.hasOption("N")) {
                N = Integer.parseInt(commandLine.getOptionValue("N"));
            } else {
                N = 5000;
            }
            if (commandLine.hasOption("d")) {
                D = Integer.parseInt(commandLine.getOptionValue("d"));
                if (D > 200){
                    System.out.println("The specified number is too big, by default D will now be 50. ");
                }
            } else if (commandLine.hasOption("D")) {
                D = Integer.parseInt(commandLine.getOptionValue("D"));
                if (D > 200){
                    System.out.println("The specified number is too big, by default D will now be 50. ");
                }
            } else {
                D = 50;
            }
        } catch (ParseException e) {
            System.out.println(e.getMessage() + ".");
            help();
            System.exit(1);
        }
    }

}
