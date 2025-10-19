package course.decorator;

import course.component.CourseComponent;
import java.util.*;

public class GamificationDecorator extends CourseDecorator {
    private static Map<String, Integer> globalPoints = new HashMap<>();
    private static Map<String, Set<String>> globalAchievements = new HashMap<>();
    private int currentStreak = 0;

    public GamificationDecorator(CourseComponent course) {
        super(course);
    }

    @Override
    public void deliverContent() {
        super.deliverContent();
        System.out.println("  + Gamification active");
        System.out.println("  + Earn points and achievements!");
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " [Gamified]";
    }

    @Override
    public int getCost() {
        return super.getCost() + 30;
    }

    public void awardPoints(int points, String studentName) {
        int current = globalPoints.getOrDefault(studentName, 0);
        int newTotal = current + points;
        globalPoints.put(studentName, newTotal);

        System.out.println("\n+" + points + " points! Total: " + newTotal);
        checkAchievements(studentName, newTotal);
    }

    public void incrementStreak() {
        currentStreak++;
        System.out.println("Streak: " + currentStreak + " sessions!");
    }

    private void checkAchievements(String studentName, int totalPoints) {
        Set<String> achievements = globalAchievements.getOrDefault(studentName, new HashSet<>());

        if (totalPoints >= 100 && !achievements.contains("First Century")) {
            achievements.add("First Century");
            System.out.println("Achievement: First Century (100 points)");
        }
        if (totalPoints >= 500 && !achievements.contains("Point Master")) {
            achievements.add("Point Master");
            System.out.println("Achievement: Point Master (500 points)");
        }
        if (totalPoints >= 1000 && !achievements.contains("Legend")) {
            achievements.add("Legend");
            System.out.println("Achievement: Legend (1000 points)");
        }

        globalAchievements.put(studentName, achievements);
    }

    public void showStats() {
        System.out.println("\nStreak: " + currentStreak + " sessions");
    }

    public static void showLeaderboard() {
        System.out.println("\n========================================");
        System.out.println("         LEADERBOARD");
        System.out.println("========================================");

        if (globalPoints.isEmpty()) {
            System.out.println("No students yet.");
            return;
        }

        List<Map.Entry<String, Integer>> sorted = new ArrayList<>(globalPoints.entrySet());
        sorted.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        int rank = 1;
        for (Map.Entry<String, Integer> entry : sorted) {
            String medal = rank == 1 ? "1st" : rank == 2 ? "2nd" : rank == 3 ? "3rd" : rank + "th";
            System.out.println(medal + " " + entry.getKey() + " - " + entry.getValue() + " pts");

            Set<String> achievements = globalAchievements.get(entry.getKey());
            if (achievements != null && !achievements.isEmpty()) {
                System.out.println("   " + String.join(", ", achievements));
            }
            rank++;
        }
        System.out.println("========================================");
    }

    public int getPoints(String studentName) {
        return globalPoints.getOrDefault(studentName, 0);
    }
}