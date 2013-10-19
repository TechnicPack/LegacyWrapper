package net.technicpack.legacywrapper;

import com.beust.jcommander.JCommander;

public class Launch {

    public static void main(String[] args) {
        StartupParameters params = new StartupParameters(args);

        new JCommander(params, args);
        System.out.println(args);

        Frame frame = new Frame(params);
        frame.runGame(params.getAuthPlayerName(), params.getAuthSession());
    }
}
