package Zajecia11;

import javax.swing.*;
import java.io.*;

public class Model {
    private String currentFile;
    private FileStatus fileStatus;
    private String name;

    public Model(String name) {
        this.name = name;
        currentFile = "bez tytułu";
        fileStatus = FileStatus.NEW;
    }

    public void openFile(View view, JMenuItem jmiOpen) {
        JFileChooser jFileChooser;
        if(!currentFile.equals("bez tytułu")) {
            jFileChooser = new JFileChooser(currentFile);
        } else {
            jFileChooser = new JFileChooser();
        }
            String pathToFile = currentFile;
            int result = jFileChooser.showOpenDialog(jmiOpen);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = jFileChooser.getSelectedFile();
                pathToFile = selectedFile.getAbsolutePath();
                changeFilePathOnFrame(view);
            }

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(pathToFile))) {
            view.getTextArea().setText("");
            JTextArea textArea;
            textArea = view.getTextArea();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String textToArea = textArea.getText() + '\n' + line;
                textArea.setText(textToArea);
            }
            currentFile = pathToFile;
            fileStatus = FileStatus.SAVED;
            changeStatus(view);
            changeFilePathOnFrame(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        public void saveFile (View view, JMenuItem jmiSave){
            if(currentFile.equals("bez tytułu")){
                saveAsFile(view,jmiSave);
            }else {
                String text = view.getTextArea().getText();
                try (PrintWriter printWriter = new PrintWriter(currentFile)) {
                    printWriter.print(text);
                    fileStatus = FileStatus.SAVED;
                    changeStatus(view);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public void saveAsFile(View view, JMenuItem jmiSaveAs){
            JFileChooser jFileChooser;
            if(!currentFile.equals("bez tytułu")) {
                jFileChooser = new JFileChooser(currentFile);
            } else {
                jFileChooser = new JFileChooser();
            }
        int result = jFileChooser.showSaveDialog(jmiSaveAs);
        if(result == JFileChooser.APPROVE_OPTION){
            File selectedFile = jFileChooser.getSelectedFile();
            String text = view.getTextArea().getText();
            String pathToFileToSave = selectedFile.getAbsolutePath();
            try(PrintWriter printWriter = new PrintWriter(pathToFileToSave)){
                printWriter.print(text);
                fileStatus = FileStatus.SAVED;
                currentFile = pathToFileToSave;
                changeStatus(view);
                changeFilePathOnFrame(view);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        }

        public void changeFilePathOnFrame(View view){
            view.getjFrame().setTitle(name + " - " + currentFile);
        }

        public void changeStatus (View view){
            view.getStatus().setText(fileStatus.getStatusValue());
        }

        public String getName () {
            return name;
        }

        public String getCurrentFile () {
            return currentFile;
        }

        public void setCurrentFile (String currentFile){
            this.currentFile = currentFile;
        }

        public FileStatus getFileStatus () {
            return fileStatus;
        }

        public void setFileStatus (FileStatus fileStatus,View view){
            this.fileStatus = fileStatus;
            changeStatus(view);
        }
    }
