package Zajecia11;

public enum FileStatus {
    NEW("New"),
    MODIFIED("Modified"),
    SAVED("Saved");

    private final String statusValue;

    FileStatus(String statusValue) {
        this.statusValue = statusValue;
    }

    public String getStatusValue() {
        return statusValue;
    }
}
