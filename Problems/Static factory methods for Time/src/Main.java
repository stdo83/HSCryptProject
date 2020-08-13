import java.util.Scanner;

class Time {

    int hour;
    int minute;
    int second;

    public Time(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public static Time noon() {
        // write your code here
        return new Time(12, 0, 0);
    }

    public static Time midnight() {
        // write your code here
        return new Time(0, 0, 0);
    }

    public static Time ofSeconds(long seconds) {
        int secondsInDay = 60 * 60 * 24;
        int secondsSinceMidnight = (int) (seconds % secondsInDay);

        int hourN = secondsSinceMidnight / 3600;
        int minuteN = (secondsSinceMidnight / 60) % 60;
        int secondN = secondsSinceMidnight % 60;

        return new Time(hourN, minuteN, secondN);
    }

    public static Time of(int hour, int minute, int second) {
        // write your code here
        if (second >= 0 && second < 60 && minute >= 0 && minute < 60 && hour >= 0 && hour < 24) {
            return new Time(hour, minute, second);
        } else {
            return null;
        }
    }
}

/* Do not change code below */
public class Main {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        final String type = scanner.next();
        Time time = null;

        switch (type) {
            case "noon":
                time = Time.noon();
                break;
            case "midnight":
                time = Time.midnight();
                break;
            case "hms":
                int h = scanner.nextInt();
                int m = scanner.nextInt();
                int s = scanner.nextInt();
                time = Time.of(h, m, s);
                break;
            case "seconds":
                time = Time.ofSeconds(scanner.nextInt());
                break;
            default:
                time = null;
                break;
        }

        if (time == null) {
            System.out.println(time);
        } else {
            System.out.println(String.format("%s %s %s", time.hour, time.minute, time.second));
        }
//        Time time = Time.ofSeconds(500000);
//        System.out.println(String.format("%s %s %s", time.hour, time.minute, time.second));
    }
}