package reminder_streak_checker;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DailyCheck {
    private static final String FILE_NAME = "days_logged_in.txt";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public void DailyCheckMethod() {
        if (!hasOpenedToday()) {
            logCurrentDay();
            System.out.println("Print Day Logged in Success");
        }else{
            System.out.println("Already Logged In");
//        }
        }
    }

    public void PrintStreak() {
        // Print day streaks
        printDayStreaks();
    }

    public int getDayStreaks() {
        int streak = calculateDayStreaks();
//        printDayStreaks();
        return streak;
    }

    private static void printDayStreaks() {
        int streak = calculateDayStreaks();
        System.out.println("Day streaks: " + streak);
    }

    private static int calculateDayStreaks() {
        int streak = 0;
        LocalDate today = LocalDate.now();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                LocalDate loggedDate = LocalDate.parse(line, DATE_FORMATTER);
                if (loggedDate.equals(today.minusDays(streak))) {
                    streak++;
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            // File not found or other IO exception
        }

        return streak;
    }

    private static boolean hasOpenedToday() {
        LocalDate today = LocalDate.now();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                LocalDate loggedDate = LocalDate.parse(line, DATE_FORMATTER);
                if (today.equals(loggedDate)) {
                    return true;
                }
            }
        } catch (IOException e) {
            // File not found or other IO exception
        }

        return false;
    }

    private static void logCurrentDay() {
        LocalDate today = LocalDate.now();
        try {
            // Read the content of the existing file, if it exists
            StringBuilder content = new StringBuilder();
            File file = new File(FILE_NAME);
            if (file.exists()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        content.append(line).append("\n");
                    }
                }
            }

            // Write the new day to the top of the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
                writer.write(today.format(DATE_FORMATTER));
                writer.newLine();
                writer.write(content.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}