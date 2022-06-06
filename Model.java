package Zajecia11;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Model {
    private String currentFile;
    private FileStatus fileStatus;
    private String name;

    public Model(String name) {
        this.name = name;
        currentFile = "bez tytułu";
        fileStatus = FileStatus.NEW;
    }

    public void openFile(String path, JTextArea textArea) {
        textArea.setText("");
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String textToArea = textArea.getText() + '\n' + line;
                textArea.setText(textToArea);
            }
            currentFile = path;
            fileStatus = FileStatus.NEW;
        } catch (IOException e) {
            System.out.println("plik nie znaleziony");
        }
    }

    public void changeFilePathOnFram(View view){
        view.getjFrame().setTitle(name + " - " + currentFile);
    }

    public void changeStatus(View view){
        view.getStatus().setText(fileStatus.getStatusValue());
    }

    public String getName() {
        return name;
    }

    public String getCurrentFile() {
        return currentFile;
    }

    public void setCurrentFile(String currentFile) {
        this.currentFile = currentFile;
    }

    public FileStatus getFileStatus() {
        return fileStatus;
    }

    public void setFileStatus(FileStatus fileStatus) {
        this.fileStatus = fileStatus;
    }
}
