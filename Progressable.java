package course;

public interface Progressable {
    int getProgressPercentage();
    void updateProgress(int amount);
    boolean isCompleted();
    void markAsComplete();
}