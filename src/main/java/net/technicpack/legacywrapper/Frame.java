package net.technicpack.legacywrapper;

import net.minecraft.Launcher;
import net.technicpack.legacywrapper.exception.CorruptedMinecraftJarException;
import net.technicpack.legacywrapper.exception.MinecraftVerifyException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Frame extends JFrame implements WindowListener {

    private static final long serialVersionUID = 1L;
    private Launcher minecraft;

    public static final int RETRYING_LAUNCH = -1;
    public static final int ERROR_IN_LAUNCH = 0;
    public static final int SUCCESSFUL_LAUNCH = 1;

    private int width;
    private int height;

    private boolean shouldRun = true;
    private StartupParameters startupParams;

	public Frame(StartupParameters params) {
        super("Technic");
        this.startupParams = params;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        width = params.getWidth();
        height = params.getHeight();
        if (width != -1 && height != -1) {
            this.setResizable(false);
        } else {
            this.setResizable(true);
            width = 900;
            height = 540;
        }

        this.setSize(width, height);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int)screenSize.getWidth();
        int screenHeight = (int)screenSize.getHeight();

        this.setLocation((screenWidth >> 1) - (width >> 1), (screenHeight >> 1) - (height >> 1));

        this.addWindowListener(this);
 	}

    public void setShouldRun(boolean shouldRun) {
        this.shouldRun = shouldRun;
    }

    public void runGame(String user, String session) {
        shouldRun = true;

        Applet applet = null;
        try {
            applet = MinecraftLauncher.getMinecraftApplet(this.startupParams);
        } catch (CorruptedMinecraftJarException corruption) {
            corruption.printStackTrace();
        } catch (MinecraftVerifyException verify) {
            verify.printStackTrace();
            this.setVisible(false);
            this.dispose();
            return;
        }
        if (applet == null) {
            String message = "Failed to launch mod pack!";
            this.setVisible(false);
            JOptionPane.showMessageDialog(getParent(), message);
            this.dispose();
            return;
        }

        minecraft = new Launcher(applet);
        minecraft.addParameter("username", user);
        minecraft.addParameter("sessionid", session);

        applet.setStub(minecraft);
        this.add(minecraft);

        validate();
        this.setVisible(true);
        minecraft.init();
        minecraft.setSize(getWidth(), getHeight());
        minecraft.start();
        return;
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        if (this.minecraft != null) {
            this.minecraft.stop();
            this.minecraft.destroy();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) { }
        }
        System.out.println("Exiting mod pack");
        this.dispose();
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
}
