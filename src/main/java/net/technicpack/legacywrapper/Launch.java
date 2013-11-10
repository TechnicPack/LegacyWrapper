package net.technicpack.legacywrapper;

import com.beust.jcommander.JCommander;

public class Launch {

    public static void main(String[] args) {
        StartupParameters params = new StartupParameters(args);

	    JCommander jc = new JCommander();
	    jc.setAcceptUnknownOptions(true);
	    jc.addObject(params);
	    jc.parse(args);
        System.out.println(args);

        Frame frame = new Frame(params);
        frame.runGame(params.getAuthPlayerName(), params.getAuthSession());
    }
}
