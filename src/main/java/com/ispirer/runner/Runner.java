package com.ispirer.runner;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.ResourceBundle;

public class Runner {

    private JFrame mainFrame;
    private JMenuBar menuBar;
    private JMenuItem russianLanguage;
    private JMenuItem englishLanguage;
    private JButton button;
    private JMenu languagesMenu;

    private JLabel label;
    private ResourceBundle bundle;
    public Runner() {
        prepareGUI();
    }

    public static void main(String[] args) {
        Runner swingControlDemo = new Runner();
        swingControlDemo.mainFrame.setVisible(true);
    }

    private void updateLanguage(Locale locale) {
        bundle = ResourceBundle.getBundle("locale", locale);
        mainFrame.setTitle(bundle.getString("application.title"));
        russianLanguage.setText(bundle.getString("application.button.language.russian"));
        englishLanguage.setText(bundle.getString("application.button.language.english"));
        languagesMenu.setText(bundle.getString("application.button.language"));
        button.setText(bundle.getString("application.button.testbutton"));
        label.setText(bundle.getString("application.lable.testlabel"));
    }

    private void prepareGUI() {
        //
        mainFrame = new JFrame("");
        mainFrame.setSize(400, 400);
        mainFrame.setLayout(new GridLayout(3, 1));
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                mainFrame.setTitle("ABC");
            }
        });
        //label
        label = new JLabel("", SwingConstants.CENTER);
        mainFrame.add(label);
        //menu
        menuBar = new JMenuBar();
        languagesMenu = new JMenu("languages");
        languagesMenu.setMnemonic(KeyEvent.VK_L);
        russianLanguage = new JMenuItem("Russian");//TODO radiobutton
        russianLanguage.addActionListener((ActionEvent e) -> updateLanguage(new Locale("ru", "RU")));
        englishLanguage = new JMenuItem("English");
        englishLanguage.addActionListener((ActionEvent e) -> updateLanguage(new Locale("en", "US")));
        menuBar.add(languagesMenu);
        languagesMenu.add(russianLanguage);
        languagesMenu.addSeparator();
        languagesMenu.add(englishLanguage);
        mainFrame.setJMenuBar(menuBar);
        //button
        button = new JButton("Clicker");
        button.setBounds(30,30,30,30);
        button.addActionListener(e->label.setText(""));
        mainFrame.add(button);
    }
}