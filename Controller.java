package Zajecia11;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import static java.awt.event.ActionEvent.CTRL_MASK;

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

        /** here */

        JMenuItem jmiOpen = view.getJmiOpen();
        jmiOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        jmiOpen.addActionListener(e ->
                model.openFile(view, jmiOpen)
        );

        JMenuItem jmiSave = view.getJmiSave();
        jmiSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        jmiSave.addActionListener(e ->
            model.saveFile(view)
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
    }
}
