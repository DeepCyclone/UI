package com.ispirer.presentation;

import com.ispirer.listener.ListEventListener;
import com.ispirer.model.Counter;
import com.ispirer.model.ListUsr;

import javax.swing.*;
import java.awt.*;
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

    private JTextArea logTextArea;
    private ResourceBundle bundle;

    public void prepareGUI() {
        initMainFrame();
        initTextLabel();
        initMenuBar();
        initLanguagesMenu();
        initButton();
        initTextArea();
        prepareTestData();
        mainFrame.setJMenuBar(menuBar);
        mainFrame.add(label);
        mainFrame.add(button);
        mainFrame.setVisible(true);
    }

    private void rerenderText(Locale locale) {
        currentLocale = locale;
        bundle = ResourceBundle.getBundle(LOCALIZATION, locale);
        mainFrame.setTitle(bundle.getString(RendererTextData.TITLE));
        russianLanguage.setText(bundle.getString(RendererTextData.RUSSIAN_LANGUAGE));
        englishLanguage.setText(bundle.getString(RendererTextData.ENGLISH_LANGUAGE));
        languagesMenu.setText(bundle.getString(RendererTextData.LANGUAGE_CHANGER));
        button.setText(bundle.getString(RendererTextData.ACTION_BUTTON));
        label.setText(bundle.getString(RendererTextData.TEST_LABEL));
    }

    private void initMainFrame(){
        mainFrame = new JFrame(ResourceBundle.getBundle(LOCALIZATION,currentLocale).getString(RendererTextData.TITLE));
        mainFrame.setSize(600, 600);
        mainFrame.setLayout(new GridLayout(2, 1));
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    private void initTextLabel(){
        label = new JLabel(ResourceBundle.getBundle(LOCALIZATION,currentLocale).getString(RendererTextData.TEST_LABEL), SwingConstants.CENTER);
        label.setVisible(false);
    }
    private void initButton(){
        button = new JButton(ResourceBundle.getBundle(LOCALIZATION,currentLocale).getString(RendererTextData.ACTION_BUTTON));
        button.addActionListener(e->label.setVisible(!label.isVisible()));
    }
    private void initMenuBar(){
        menuBar = new JMenuBar();
    }
    private void initLanguagesMenu(){
        languagesMenu = new JMenu(ResourceBundle.getBundle(LOCALIZATION,currentLocale).getString(RendererTextData.LANGUAGE_CHANGER));
        russianLanguage = new JMenuItem(ResourceBundle.getBundle(LOCALIZATION,currentLocale).getString(RendererTextData.RUSSIAN_LANGUAGE));
        russianLanguage.addActionListener(event -> rerenderText(LOCALE_RU));
        englishLanguage = new JMenuItem(ResourceBundle.getBundle(LOCALIZATION,currentLocale).getString(RendererTextData.ENGLISH_LANGUAGE));
        englishLanguage.addActionListener(event -> rerenderText(LOCALE_EN));
        languagesMenu.add(russianLanguage);
        languagesMenu.addSeparator();
        languagesMenu.add(englishLanguage);
        menuBar.add(languagesMenu);
    }
    private void initTextArea(){
        logTextArea = new JTextArea();
        JScrollPane scroll = new JScrollPane (logTextArea,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        mainFrame.add(scroll);
    }

    private void prepareTestData(){
        StringBuilder b = new StringBuilder();
        for(int i = 0;i<10;++i) {
            Counter c = new Counter();
            b.append("Current object number " + c.getCurrentNumber() + "\n");
        }
        logTextArea.append(b.toString());
        logTextArea.append(Counter.getObjects() + " \n");
        ListUsr<String> userList = new ListUsr<>(3);
        userList.events.subscribe("add_element",new ListEventListener());
        logTextArea.append("LIST TEST==============\n");
        logTextArea.append("adding string,SIZE = " + userList.getCapacity() + "\n");
        userList.add("T");
        logTextArea.append("added string,SIZE = "  + userList.getCapacity() + "\n");
        logTextArea.append("set string at index 0" + "\n");
        userList.setElement(0,"XXX");
        logTextArea.append("ALL ELEMENTS:" + userList + "\n");
    }
}
