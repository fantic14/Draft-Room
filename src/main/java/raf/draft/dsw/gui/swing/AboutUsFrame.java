package raf.draft.dsw.gui.swing;

import raf.draft.dsw.controller.actions.ActionManager;

import javax.swing.*;
import java.awt.*;

public class AboutUsFrame extends JFrame {
    private static AboutUsFrame instance;
    private ActionManager actionManager = new ActionManager();



    public AboutUsFrame(){
        initialize();

    }

    private void initialize() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth/3, screenHeight/2);
        setLocationRelativeTo(null);
        setTitle("About Us");
        setResizable(false);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));

        JPanel IPanel = new JPanel(new GridLayout(1,2));
        JPanel TPanel = new JPanel(new GridLayout(1,2));

        ImageIcon icon1 = new ImageIcon("src/main/resources/images/jt.JPG");
        Image slika1 = icon1.getImage().getScaledInstance(250, 350, Image.SCALE_SMOOTH);
        JLabel member1Image = new JLabel(new ImageIcon(slika1), JLabel.CENTER);
        JLabel member1Name = new JLabel("Jovan Tijanic", JLabel.CENTER);

        ImageIcon icon2 = new ImageIcon("src/main/resources/images/fa.JPG");
        Image slika2 = icon2.getImage().getScaledInstance(250, 350, Image.SCALE_SMOOTH);
        JLabel member2Image = new JLabel(new ImageIcon(slika2), JLabel.CENTER);
        JLabel member2Name = new JLabel("Filip Antic", JLabel.CENTER);

        IPanel.add(member1Image);
        IPanel.add(member2Image);
        TPanel.add(member1Name);
        TPanel.add(member2Name);

        add(IPanel, BorderLayout.CENTER);
        add(TPanel, BorderLayout.SOUTH);

    }

    public static AboutUsFrame getInstance() {
        if (instance == null)
            instance = new AboutUsFrame();
        return instance;
    }
}
