//package Zajecia11;
//
//import javax.swing.*;
//import java.awt.*;
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.io.IOException;
//
//public class SwingApp {
//
//    private JTextArea textArea;
//
//    public static void main(String[] args) {
//        new SwingApp();
//    }
//
//    private SwingApp() {
//        SwingUtilities.invokeLater(
//                () -> createGui()
//        );
//    }
//
//    public void createGui() {
//        JFrame jFrame = new JFrame();
//        JPanel mainPanel = new JPanel();
//        mainPanel.setLayout(new BorderLayout());
//        jFrame.add(mainPanel);
//
//        JPanel northPanel = new JPanel();
//        northPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
//
//        JPanel centrePanel = new JPanel();
//        centrePanel.setLayout(new BorderLayout());
//
//        textArea = getTextArea(centrePanel);
//
//        JMenuBar jmbFile = getFileBar();
//        JMenuBar jmbEdit = getEditBar();
//        JMenuBar jmbOptions = getOptionsBar();
//
//        northPanel.add(jmbFile);
//        northPanel.add(jmbEdit);
//        northPanel.add(jmbOptions);
//
//        JPanel southPanel = new JPanel();
//        southPanel.setLayout(new BorderLayout());
//        JPanel southRightPanel = new JPanel();
//
//        JLabel status = new JLabel("Status");
//        southRightPanel.add(status);
//
//        JPanel southLeftPanel = new JPanel();
//        JLabel textColor = new JLabel("tekst color");
//        textColor.setForeground(Color.RED);
//        JLabel backgroundColor = new JLabel("background color");
//        backgroundColor.setForeground(Color.BLUE);
//        JLabel color = new JLabel("color");
//        southLeftPanel.add(textColor);
//        southLeftPanel.add(backgroundColor);
//        southLeftPanel.add(color);
//
//        southPanel.add(southLeftPanel, BorderLayout.WEST);
//        southPanel.add(southRightPanel, BorderLayout.EAST);
//
//        mainPanel.add(centrePanel, BorderLayout.CENTER);
//        mainPanel.add(northPanel, BorderLayout.NORTH);
//        mainPanel.add(southPanel, BorderLayout.SOUTH);
//
//        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        jFrame.setResizable(true);
//        jFrame.pack();
//        jFrame.setVisible(true);
//    }

//    public JTextArea getTextArea(JPanel centrePanel) {
//        JTextArea textArea = new JTextArea();
//        textArea.setText("Obszar do pisania");
//        JScrollPane scrollPane = new JScrollPane(textArea);
//        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
//        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
//        centrePanel.add(scrollPane);
//        return textArea;
//    }

//    public JMenuBar getFileBar() {
//        JMenuBar jmbFile = new JMenuBar();
//        JMenu jmFile = new JMenu("File");
//        JMenuItem jbOpen = new JMenuItem("Open");
//        JFileChooser jFileChooser = new JFileChooser();
//        jbOpen.addActionListener((e -> {
//            int result = jFileChooser.showOpenDialog(jbOpen);
//            if (result == JFileChooser.APPROVE_OPTION) {
//                File selectedFile = jFileChooser.getSelectedFile();
//                System.out.println("Selected file: " + selectedFile.getAbsolutePath());
//                openFile(selectedFile.getAbsolutePath());
//            }
//
//        }));
//        JMenuItem jmiSave = new JMenuItem("Save");
//        JMenuItem jmiSaveAs = new JMenuItem("Save As");
//        JMenuItem jmiExit = new JMenuItem("Exit");
//        jmiExit.setBorder(BorderFactory.createLineBorder(Color.RED));
//        jmFile.add(jbOpen);
//        jmFile.add(jmiSave);
//        jmFile.add(jmiSaveAs);
//        jmFile.add(jmiExit);
//        jmbFile.add(jmFile);
//        return jmbFile;
//    }
//
//    public void openFile(String path){
//        textArea.setText("");
//        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(path))){
//            String line;
//            while((line = bufferedReader.readLine()) !=null){
//                String textToArea = textArea.getText() + '\n' + line;
//                textArea.setText(textToArea);
//            }
//        } catch(IOException e){
//            System.out.println("plik nie znaleziony");
//        }
//    }
//
//    public JMenuBar getEditBar() {
//        JMenuBar jmbEdit = new JMenuBar();
//        JMenu jmEdit = new JMenu("Edit");
//
//        JMenu jmAdresy = new JMenu("Adresy");
//
//        JMenuItem jmiPraca = new JMenuItem("Praca");
//        JMenuItem jmiDom = new JMenuItem("Dom");
//        JMenuItem jmiSzkola = new JMenuItem("Szkola");
//        jmbEdit.add(jmEdit);
//        jmEdit.add(jmAdresy);
//        jmAdresy.add(jmiPraca);
//        jmAdresy.add(jmiDom);
//        jmAdresy.add(jmiSzkola);
//        return jmbEdit;
//    }
//
//    public JMenuBar getOptionsBar() {
//        JMenuBar jmbOptions = new JMenuBar();
//        JMenu jmOptions = new JMenu("Options");
//        JMenu jmForeground = getJMenuColorsForeground();
//        JMenu jmBackground = getJMenuColorsBackground();
//        JMenu jmFontSize = getJMenutFontSize();
//        jmbOptions.add(jmOptions);
//        jmOptions.add(jmForeground);
//        jmOptions.add(jmBackground);
//        jmOptions.add(jmFontSize);
//        return jmbOptions;
//    }
//
//    private JMenu getJMenutFontSize() {
//        int[] sizes = getSizesOfFont();
//        JMenu jMenu = new JMenu("Font Size");
//        for (int i = 0; i < sizes.length; i++) {
//            int size = sizes[i];
//            JMenuItem jMenuItem = new JMenuItem("" + size + " pts");
//            Font font = new Font(null, 0, size);
//            jMenuItem.setFont(font);
//            jMenuItem.addActionListener(e ->
//                    textArea.setFont(font)
//            );
//            jMenu.add(jMenuItem);
//        }
//        return jMenu;
//    }
//
//    private JMenu getJMenuColorsForeground() {
//        Color[] colors = getColorsValue();
//        String[] strings = getNamesOfColorMenuItems();
//        JMenu jMenu = new JMenu("Foreground");
//        for (int i = 0; i < colors.length; i++) {
//            String text = strings[i];
//            Color color = colors[i];
//            JMenuItem jMenuItem = new JMenuItem(text);
//            jMenuItem.setForeground(color);
//            jMenuItem.addActionListener((e -> {
//                textArea.setForeground(color);
//            }));
//            jMenu.add(jMenuItem);
//        }
//        return jMenu;
//    }
//
//    private JMenu getJMenuColorsBackground() {
//        Color[] colors = getColorsValue();
//        String[] strings = getNamesOfColorMenuItems();
//        JMenu jMenu = new JMenu("Background");
//        for (int i = 0; i < colors.length; i++) {
//            String text = strings[i];
//            Color color = colors[i];
//            JMenuItem jMenuItem = new JMenuItem(text);
//            jMenuItem.setForeground(color);
//            jMenuItem.addActionListener((e -> {
//                textArea.setBackground(color);
//            }));
//            jMenu.add(jMenuItem);
//        }
//        return jMenu;
//    }
//
//    private int[] getSizesOfFont() {
//        return new int[]{
//                8, 10, 12, 14, 16, 18, 20, 22, 24
//        };
//    }
//
//    private String[] getNamesOfColorMenuItems() {
//        return new String[]{
//                "Green", "Orange", "Red", "Black", "White", "Yellow", "Blue"
//        };
//    }
//
//    private Color[] getColorsValue() {
//        return new Color[]{
//                Color.GREEN, Color.ORANGE, Color.RED, Color.BLACK, Color.WHITE, Color.YELLOW, Color.BLUE
//        };
//
//    }
//
//}
