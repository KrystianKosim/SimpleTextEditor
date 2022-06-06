package Zajecia11;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Controller {
    private View view;
    private Model model;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
    }

    public void initView() {
        view.getStatus().setText(model.getFileStatus().getStatusValue());
        view.getjFrame().setTitle(model.getName() + " - " + model.getCurrentFile());
    }

    public void initController() {
        JMenuItem[] tabForeground = view.getjMenuItemsOptionsForeground();
        for (int i = 0; i < tabForeground.length; i++) {
            JMenuItem jMenuItem = tabForeground[i];
            Color color = jMenuItem.getForeground();
            jMenuItem.addActionListener((e ->
                    view.getTextArea().setForeground(color)
            ));
        }

        JMenuItem[] tabBackground = view.getjMenutItemsOptionsBackground();
        for (int i = 0; i < tabBackground.length; i++) {
            JMenuItem jMenuItem = tabBackground[i];
            Color color = jMenuItem.getForeground();
            jMenuItem.addActionListener((e) ->
                    view.getTextArea().setBackground(color)
            );
        }

        JMenuItem[] tabFontSizes = view.getjMenuItemsOptionsFontSize();
        for (int i = 0; i < tabFontSizes.length; i++) {
            JMenuItem jMenuItem = tabFontSizes[i];
            Font font = jMenuItem.getFont();
            jMenuItem.addActionListener((e ->
                    view.getTextArea().setFont(font)
            ));
        }

        JMenuItem jmiOpen = view.getJmiOpen();
        jmiOpen.addActionListener((e -> {
            JFileChooser jFileChooser = new JFileChooser();
            JTextArea textArea = view.getTextArea();
            int result = jFileChooser.showOpenDialog(jmiOpen);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = jFileChooser.getSelectedFile();
                model.setCurrentFile(selectedFile.getAbsolutePath());
                model.changeFilePathOnFram(view);
                model.openFile(selectedFile.getAbsolutePath(), textArea);
            }
        }));

        JMenuItem jmiSave = view.getJmiSave();
        jmiSave.addActionListener((e -> {
            String text = view.getTextArea().getText();
            try(PrintWriter printWriter = new PrintWriter(model.getCurrentFile())){
                printWriter.print(text);
                model.setFileStatus(FileStatus.SAVED);
                model.changeStatus(view);
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }));

        JMenuItem jmiSaveAs = view.getJmiSaveAs();
        jmiSaveAs.addActionListener((e) -> {
            String text = view.getTextArea().getText();
            JFileChooser jFileChooser = new JFileChooser();
            int result = jFileChooser.showOpenDialog(jmiSaveAs);
            if(result == JFileChooser.APPROVE_OPTION){
                File selectedFile = jFileChooser.getSelectedFile();
                model.setCurrentFile(selectedFile.getAbsolutePath());
            }
        });

        JMenuItem jmiExit = view.getJmiExit();
        jmiExit.addActionListener((e) ->
            System.exit(1)
        );
    }
}
