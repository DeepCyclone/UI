package com.ispirer.presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Locale;
import java.util.ResourceBundle;

public class GUIRenderer {
    private static final Locale LOCALE_RU = new Locale("ru", "RU");
    private static final Locale LOCALE_EN = new Locale("en", "US");

    private static final String LOCALIZATION = "locale";

    private Locale currentLocale = LOCALE_EN;
    private JFrame mainFrame;
    private JMenuBar menuBar;
    private JMenuItem russianLanguage;
    private JMenuItem englishLanguage;
    private JButton button;
    private JMenu languagesMenu;
    private JLabel label;
    private ResourceBundle bundle;

    public void prepareGUI() {
        initMainFrame();
        initTextLabel();
        initMenuBar();
        initLanguagesMenu();
        initButton();
        mainFrame.setJMenuBar(menuBar);
        mainFrame.add(label);
        mainFrame.add(button);
        mainFrame.setVisible(true);
    }

    private void rerenderText(Locale locale) {
        currentLocale = locale;
        bundle = ResourceBundle.getBundle(LOCALIZATION, locale);
        mainFrame.setTitle(bundle.getString("application.title"));
        russianLanguage.setText(bundle.getString("application.button.language.russian"));
        englishLanguage.setText(bundle.getString("application.button.language.english"));
        languagesMenu.setText(bundle.getString("application.button.language"));
        button.setText(bundle.getString("application.button.testbutton"));
        label.setText(bundle.getString("application.label.testlabel"));
    }

    private void initMainFrame(){
        mainFrame = new JFrame(ResourceBundle.getBundle(LOCALIZATION,currentLocale).getString("application.title"));
        mainFrame.setSize(600, 600);
        mainFrame.setLayout(new GridLayout(2, 1));
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    private void initTextLabel(){
        label = new JLabel(ResourceBundle.getBundle(LOCALIZATION,currentLocale).getString("application.label.testlabel"), SwingConstants.CENTER);
        label.setVisible(false);
    }
    private void initButton(){
        button = new JButton(ResourceBundle.getBundle(LOCALIZATION,currentLocale).getString("application.button.testbutton"));
        button.addActionListener(e->label.setVisible(!label.isVisible()));
    }
    private void initMenuBar(){
        menuBar = new JMenuBar();
    }
    private void initLanguagesMenu(){
        languagesMenu = new JMenu(ResourceBundle.getBundle(LOCALIZATION,currentLocale).getString("application.button.language"));
        russianLanguage = new JMenuItem(ResourceBundle.getBundle(LOCALIZATION,currentLocale).getString("application.button.language.russian"));//TODO radiobutton
        russianLanguage.addActionListener(event -> rerenderText(LOCALE_RU));
        englishLanguage = new JMenuItem(ResourceBundle.getBundle(LOCALIZATION,currentLocale).getString("application.button.language.english"));
        englishLanguage.addActionListener(event -> rerenderText(LOCALE_EN));
        languagesMenu.add(russianLanguage);
        languagesMenu.addSeparator();
        languagesMenu.add(englishLanguage);
        menuBar.add(languagesMenu);
    }
}
