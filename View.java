package Zajecia11;

import javax.swing.*;
import java.awt.*;

public class View {
    private JFrame jFrame;
    private JTextArea textArea;
    private JMenuBar jmbFile;
    private JMenuItem jmiOpen;
    private JMenuItem jmiSave;
    private JMenuItem jmiSaveAs;
    private JMenuItem jmiExit;
    private JMenuBar jmbEdit;
    private JMenuBar jmbOptions;
    private JPanel mainPanel;
    private JPanel northPanel;
    private JPanel centrePanel;
    private JPanel southPanel;
    private JPanel southRightPanel;
    private JLabel status;
    private JPanel southLeftPanel;
    private JLabel textColor;
    private JLabel backgroundColor;
    private JLabel color;
    private JMenuItem[] jMenuItemsOptionsForeground;
    private JMenuItem[] jMenutItemsOptionsBackground;
    private JMenuItem[] jMenuItemsOptionsFontSize;


    public View() {
        jFrame = new JFrame();
        jFrame.setTitle("Prosty edytor");
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        jFrame.add(mainPanel);

        northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        centrePanel = new JPanel();
        centrePanel.setLayout(new BorderLayout());
        textArea = getTextArea(centrePanel);

        jmbFile = getFileBar();

        northPanel.add(jmbFile);
        jmbEdit = getEditBar();

        northPanel.add(jmbEdit);
        jmbOptions = getOptionsBar();

        northPanel.add(jmbOptions);

        southPanel = new JPanel();
        southPanel.setLayout(new BorderLayout());
        southRightPanel = new JPanel();

        status = new JLabel("Status");
        southRightPanel.add(status);

        southLeftPanel = new JPanel();
        textColor = new JLabel("Text Color");
        southLeftPanel.add(textColor);
        backgroundColor = new JLabel("Background Color");
        southLeftPanel.add(backgroundColor);
        color = new JLabel("Color ");
        southLeftPanel.add(color);

        southPanel.add(southLeftPanel, BorderLayout.WEST);
        southPanel.add(southRightPanel, BorderLayout.EAST);

        mainPanel.add(centrePanel, BorderLayout.CENTER);
        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.add(southPanel, BorderLayout.SOUTH);

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(true);
        jFrame.pack();
        jFrame.setVisible(true);
    }

    public static JTextArea getTextArea(JPanel centrePanel) {
        JTextArea textArea = new JTextArea();
        textArea.setText("Obszar do pisania");
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        centrePanel.add(scrollPane);
        return textArea;
    }

    public JMenuBar getFileBar() {
        JMenuBar jmbFile = new JMenuBar();
        JMenu jmFile = new JMenu("File");
        jmiOpen = new JMenuItem("Open");
        jmiSave = new JMenuItem("Save");
        jmiSaveAs = new JMenuItem("Save As");
        jmiExit = new JMenuItem("Exit");
        jmiExit.setBorder(BorderFactory.createLineBorder(Color.RED));
        jmFile.add(jmiOpen);
        jmFile.add(jmiSave);
        jmFile.add(jmiSaveAs);
        jmFile.add(jmiExit);
        jmbFile.add(jmFile);

        return jmbFile;
    }

    public JMenuBar getEditBar() {
        JMenuBar jmbEdit = new JMenuBar();
        JMenu jmEdit = new JMenu("Edit");
        JMenu jmAdresy = new JMenu("Adresy");

        JMenuItem jmiPraca = new JMenuItem("Praca");
        JMenuItem jmiDom = new JMenuItem("Dom");
        JMenuItem jmiSzkola = new JMenuItem("Szkola");
        jmbEdit.add(jmEdit);
        jmEdit.add(jmAdresy);
        jmAdresy.add(jmiPraca);
        jmAdresy.add(jmiDom);
        jmAdresy.add(jmiSzkola);
        return jmbEdit;
    }

    public JMenuBar getOptionsBar() {
        JMenuBar jmbOptions = new JMenuBar();
        JMenu jmOptions = new JMenu("Options");
        JMenu jmForeground = getJMenuColorsForeground();
        JMenu jmBackground = getJMenuColorsBackground();
        JMenu jmFontSize = getJMenutFontSize();
        jmbOptions.add(jmOptions);
        jmOptions.add(jmForeground);
        jmOptions.add(jmBackground);
        jmOptions.add(jmFontSize);
        return jmbOptions;
    }

    private JMenu getJMenutFontSize() {
        int[] sizes = getSizesOfFont();
        JMenu jMenu = new JMenu("Font Size");
        JMenuItem [] tab = new JMenuItem[0];
        for (int i = 0; i < sizes.length; i++) {
            int size = sizes[i];
            JMenuItem jMenuItem = new JMenuItem("" + size + " pts");
            Font font = new Font(null, 0, size);
            jMenuItem.setFont(font);
            tab = addItemToTab(tab,jMenuItem);
            jMenu.add(jMenuItem);
        }
        jMenuItemsOptionsFontSize = tab;
        return jMenu;
    }

    private JMenu getJMenuColorsForeground() {
        Color[] colors = getColorsValue();
        String[] strings = getNamesOfColorMenuItems();
        JMenu jMenu = new JMenu("Foreground");
        JMenuItem [] tab = new JMenuItem[0];
        for (int i = 0; i < colors.length; i++) {
            String text = strings[i];
            Color color = colors[i];
            JMenuItem jMenuItem = new JMenuItem(text);
            jMenuItem.setForeground(color);
            tab = addItemToTab(tab,jMenuItem);
            jMenu.add(jMenuItem);
        }
        jMenuItemsOptionsForeground = tab;
        return jMenu;
    }

    private JMenu getJMenuColorsBackground() {
        Color[] colors = getColorsValue();
        String[] strings = getNamesOfColorMenuItems();
        JMenu jMenu = new JMenu("Background");
        JMenuItem [] tab = new JMenuItem[0];
        for (int i = 0; i < colors.length; i++) {
            String text = strings[i];
            Color color = colors[i];
            JMenuItem jMenuItem = new JMenuItem(text);
            jMenuItem.setForeground(color);
            tab = addItemToTab(tab,jMenuItem);
            jMenu.add(jMenuItem);
        }
        jMenutItemsOptionsBackground = tab;
        return jMenu;
    }

    private static JMenuItem[] addItemToTab(JMenuItem[] tab, JMenuItem jMenuItem) {
        int lengthOfNewTab = tab.length + 1;
        if(tab.length != 0) {
            JMenuItem[] replacementTab = new JMenuItem[lengthOfNewTab];
            for (int i = 0; i < tab.length; i++) {
                replacementTab[i] = tab[i];
            }
            replacementTab[lengthOfNewTab - 1] = jMenuItem;
            return replacementTab;
        } else {
            return new JMenuItem[]{jMenuItem};
        }
    }

    private static int[] getSizesOfFont() {
        return new int[]{
                8, 10, 12, 14, 16, 18, 20, 22, 24
        };
    }

    private static String[] getNamesOfColorMenuItems() {
        return new String[]{
                "Green", "Orange", "Red", "Black", "White", "Yellow", "Blue"
        };
    }

    private static Color[] getColorsValue() {
        return new Color[]{
                Color.GREEN, Color.ORANGE, Color.RED, Color.BLACK, Color.WHITE, Color.YELLOW, Color.BLUE
        };
    }

    public JMenuItem[] getjMenuItemsOptionsForeground() {
        return jMenuItemsOptionsForeground;
    }

    public JMenuItem[] getjMenutItemsOptionsBackground() {
        return jMenutItemsOptionsBackground;
    }

    public JMenuItem[] getjMenuItemsOptionsFontSize() {
        return jMenuItemsOptionsFontSize;
    }

    public JMenuItem getJmiOpen() {
        return jmiOpen;
    }

    public JMenuItem getJmiSave() {
        return jmiSave;
    }

    public JMenuItem getJmiSaveAs() {
        return jmiSaveAs;
    }

    public JMenuItem getJmiExit(){
        return jmiExit;
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public JFrame getjFrame() {
        return jFrame;
    }

    public void setjFrame(JFrame jFrame) {
        this.jFrame = jFrame;
    }

    public void setTextArea(JTextArea textArea) {
        this.textArea = textArea;
    }

    public JMenuBar getJmbFile() {
        return jmbFile;
    }

    public void setJmbFile(JMenuBar jmbFile) {
        this.jmbFile = jmbFile;
    }

    public void setJmiOpen(JMenuItem jmiOpen) {
        this.jmiOpen = jmiOpen;
    }

    public void setJmiSave(JMenuItem jmiSave) {
        this.jmiSave = jmiSave;
    }

    public void setJmiSaveAs(JMenuItem jmiSaveAs) {
        this.jmiSaveAs = jmiSaveAs;
    }

    public void setJmiExit(JMenuItem jmiExit) {
        this.jmiExit = jmiExit;
    }

    public JMenuBar getJmbEdit() {
        return jmbEdit;
    }

    public void setJmbEdit(JMenuBar jmbEdit) {
        this.jmbEdit = jmbEdit;
    }

    public JMenuBar getJmbOptions() {
        return jmbOptions;
    }

    public void setJmbOptions(JMenuBar jmbOptions) {
        this.jmbOptions = jmbOptions;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public JPanel getNorthPanel() {
        return northPanel;
    }

    public void setNorthPanel(JPanel northPanel) {
        this.northPanel = northPanel;
    }

    public JPanel getCentrePanel() {
        return centrePanel;
    }

    public void setCentrePanel(JPanel centrePanel) {
        this.centrePanel = centrePanel;
    }

    public JPanel getSouthPanel() {
        return southPanel;
    }

    public void setSouthPanel(JPanel southPanel) {
        this.southPanel = southPanel;
    }

    public JPanel getSouthRightPanel() {
        return southRightPanel;
    }

    public void setSouthRightPanel(JPanel southRightPanel) {
        this.southRightPanel = southRightPanel;
    }

    public JLabel getStatus() {
        return status;
    }

    public void setStatus(JLabel status) {
        this.status = status;
    }

    public JPanel getSouthLeftPanel() {
        return southLeftPanel;
    }

    public void setSouthLeftPanel(JPanel southLeftPanel) {
        this.southLeftPanel = southLeftPanel;
    }

    public JLabel getTextColor() {
        return textColor;
    }

    public void setTextColor(JLabel textColor) {
        this.textColor = textColor;
    }

    public JLabel getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(JLabel backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public JLabel getColor() {
        return color;
    }

    public void setColor(JLabel color) {
        this.color = color;
    }

    public void setjMenuItemsOptionsForeground(JMenuItem[] jMenuItemsOptionsForeground) {
        this.jMenuItemsOptionsForeground = jMenuItemsOptionsForeground;
    }

    public void setjMenutItemsOptionsBackground(JMenuItem[] jMenutItemsOptionsBackground) {
        this.jMenutItemsOptionsBackground = jMenutItemsOptionsBackground;
    }

    public void setjMenuItemsOptionsFontSize(JMenuItem[] jMenuItemsOptionsFontSize) {
        this.jMenuItemsOptionsFontSize = jMenuItemsOptionsFontSize;
    }
}
