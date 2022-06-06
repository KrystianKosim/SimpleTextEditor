package Zajecia11;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class Controller {
    private View view;
    private Model model;
    private String workAddress;
    private String homeAddress;
    private String schoolAddress;

    public Controller(View view, Model model,String workAddress, String homeAddress, String schoolAddress) {
        this.view = view;
        this.model = model;
        this.workAddress = workAddress;
        this.homeAddress = homeAddress;
        this.schoolAddress = schoolAddress;
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
            jMenuItem.addActionListener((e -> {
                view.getTextArea().setForeground(color);
                model.changeTextColorIconOnDown(view, color);
            }));
        }

        JMenuItem[] tabBackground = view.getjMenutItemsOptionsBackground();
        for (int i = 0; i < tabBackground.length; i++) {
            JMenuItem jMenuItem = tabBackground[i];
            Color color = jMenuItem.getForeground();
            jMenuItem.addActionListener((e) -> {
                view.getTextArea().setBackground(color);
                model.changeBackgroundColorIconOnDown(view, color);
            });
        }

        JMenuItem[] tabFontSizes = view.getjMenuItemsOptionsFontSize();
        for (int i = 0; i < tabFontSizes.length; i++) {
            JMenuItem jMenuItem = tabFontSizes[i];
            Font font = jMenuItem.getFont();
            jMenuItem.addActionListener((e -> {
                view.getTextArea().setFont(font);
                model.changeSizeOnDown(view, font.getSize());
            }
            ));
        }

        JMenuItem jmiOpen = view.getJmiOpen();
        jmiOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        jmiOpen.addActionListener(e ->
                model.openFile(view, jmiOpen)
        );

        JMenuItem jmiSave = view.getJmiSave();
        jmiSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        jmiSave.addActionListener(e ->
                model.saveFile(view, jmiSave)
        );

        JMenuItem jmiSaveAs = view.getJmiSaveAs();
        jmiSaveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
        jmiSaveAs.addActionListener(e ->
                model.saveAsFile(view, jmiSaveAs)
        );

        JMenuItem jmiExit = view.getJmiExit();
        jmiExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));
        jmiExit.addActionListener(e ->
                System.exit(1)
        );

        view.getTextArea().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                model.setFileStatus(FileStatus.MODIFIED, view);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                model.setFileStatus(FileStatus.MODIFIED, view);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
        JMenuItem jmiWork = view.getJmiPraca();
        jmiWork.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK));
        jmiWork.addActionListener(e -> {
            JTextArea textArea = view.getTextArea();
            int currentPosition = textArea.getCaretPosition();
            textArea.insert(workAddress, currentPosition);
        });

        JMenuItem jmiHome = view.getJmiDom();
        jmiHome.addActionListener(e -> {
            JTextArea textArea = view.getTextArea();
            int currentPosition = textArea.getCaretPosition();
            textArea.insert(homeAddress, currentPosition);

        });

        JMenuItem jmiSchool = view.getJmiSzkola();
        jmiSchool.addActionListener(e -> {
            JTextArea textArea = view.getTextArea();
            int currentPosition = textArea.getCaretPosition();
            textArea.insert(schoolAddress, currentPosition);
        });
    }
}
