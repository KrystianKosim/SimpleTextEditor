package Zajecia11;

import javax.swing.*;
import java.awt.*;

public class View {
    private final JFrame jFrame;
    private final JTextArea textArea;
    private final JMenuBar jmbFile;
    private JMenuItem jmiOpen;
    private JMenuItem jmiSave;
    private JMenuItem jmiSaveAs;
    private JMenuItem jmiExit;
    private JMenuItem jmiPraca;
    private JMenuItem jmiDom;
    private JMenuItem jmiSzkola;
    private final JMenuBar jmbEdit;
    private final JMenuBar jmbOptions;
    private final JPanel mainPanel;
    private final JPanel northPanel;
    private final JPanel centrePanel;
    private final JPanel southPanel;
    private final JPanel southRightPanel;
    private final JLabel status;
    private final OvalIcon textColorIcon;
    private final OvalIcon backgroundColorIcon;
    private final JPanel southLeftPanel;
    private final JLabel textColor;
    private final JLabel backgroundColor;
    private final JLabel size;
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
        textArea = createTextArea(centrePanel);


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
        textColorIcon = new OvalIcon(8, 8, Color.BLACK);
        textColor = new JLabel("fg");
        textColor.setIcon(textColorIcon);

        southLeftPanel.add(textColor);
        backgroundColorIcon = new OvalIcon(8, 8, Color.WHITE);
        backgroundColor = new JLabel("bg");
        backgroundColor.setIcon(backgroundColorIcon);
        southLeftPanel.add(backgroundColor);
        size = new JLabel("" + 14);
        southLeftPanel.add(size);

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

    public static JTextArea createTextArea(JPanel centrePanel) {
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
        JSeparator jSeparator = new JSeparator();
        jSeparator.setBorder(BorderFactory.createLineBorder(Color.RED));
        jmiExit = new JMenuItem("Exit");
        jmFile.add(jmiOpen);
        jmFile.add(jmiSave);
        jmFile.add(jmiSaveAs);
        jmFile.add(jSeparator);
        jmFile.add(jmiExit);
        jmbFile.add(jmFile);

        return jmbFile;
    }

    public JMenuBar getEditBar() {
        JMenuBar jmbEdit = new JMenuBar();
        JMenu jmEdit = new JMenu("Edit");
        JMenu jmAdresy = new JMenu("Adresy");

        jmiPraca = new JMenuItem("Praca");
        jmiDom = new JMenuItem("Dom");
        jmiSzkola = new JMenuItem("Szkola");
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
        JRadioButtonMenuItem[] tab = new JRadioButtonMenuItem[0];
        for (int i = 0; i < sizes.length; i++) {
            int size = sizes[i];
            JRadioButtonMenuItem jMenuItem = new JRadioButtonMenuItem("" + size + " pts");
            Font font = new Font(null, 0, size);
            jMenuItem.setFont(font);
            tab = addItemToTab(tab, jMenuItem);
            jMenu.add(jMenuItem);
        }
        jMenuItemsOptionsFontSize = tab;
        return jMenu;
    }

    private JMenu getJMenuColorsForeground() {
        Color[] colors = getColorsValue();
        String[] strings = getNamesOfColorMenuItems();
        JMenu jMenu = new JMenu("Foreground");
        JRadioButtonMenuItem[] tab = new JRadioButtonMenuItem[0];
        for (int i = 0; i < colors.length; i++) {
            String text = strings[i];
            Color color = colors[i];
            JRadioButtonMenuItem jMenuItem = new JRadioButtonMenuItem(text);
            OvalIcon ovalIcon = new OvalIcon(8, 8, color);
            jMenuItem.setIcon(ovalIcon);
            jMenuItem.setForeground(color);
            tab = addItemToTab(tab, jMenuItem);
            jMenu.add(jMenuItem);
        }
        jMenuItemsOptionsForeground = tab;
        return jMenu;
    }

    private JMenu getJMenuColorsBackground() {
        Color[] colors = getColorsValue();
        String[] strings = getNamesOfColorMenuItems();
        JMenu jMenu = new JMenu("Background");
        JRadioButtonMenuItem[] tab = new JRadioButtonMenuItem[0];
        for (int i = 0; i < colors.length; i++) {
            String text = strings[i];
            Color color = colors[i];
            JRadioButtonMenuItem jMenuItem = new JRadioButtonMenuItem(text);
            OvalIcon ovalIcon = new OvalIcon(8, 8, color);
            jMenuItem.setIcon(ovalIcon);
            jMenuItem.setForeground(color);
            tab = addItemToTab(tab, jMenuItem);
            jMenu.add(jMenuItem);
        }
        jMenutItemsOptionsBackground = tab;
        return jMenu;
    }

    private static JRadioButtonMenuItem[] addItemToTab(JRadioButtonMenuItem[] tab, JRadioButtonMenuItem jMenuItem) {
        int lengthOfNewTab = tab.length + 1;
        if (tab.length != 0) {
            JRadioButtonMenuItem[] replacementTab = new JRadioButtonMenuItem[lengthOfNewTab];
            for (int i = 0; i < tab.length; i++) {
                replacementTab[i] = tab[i];
            }
            replacementTab[lengthOfNewTab - 1] = jMenuItem;
            return replacementTab;
        } else {
            return new JRadioButtonMenuItem[]{jMenuItem};
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


    public JTextArea getTextArea() {
        return textArea;
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

    public JMenuItem getJmiExit() {
        return jmiExit;
    }

    public JMenuItem getJmiPraca() {
        return jmiPraca;
    }

    public JMenuItem getJmiDom() {
        return jmiDom;
    }

    public JMenuItem getJmiSzkola() {
        return jmiSzkola;
    }

    public OvalIcon getTextColorIcon() {
        return textColorIcon;
    }

    public OvalIcon getBackgroundColorIcon() {
        return backgroundColorIcon;
    }

    public JFrame getjFrame() {
        return jFrame;
    }

    public JLabel getStatus() {
        return status;
    }

    public JLabel getSize() {
        return size;
    }

}
