package carsharing;

/** Простий консольний журнал: [час] [джерело] подія. Час умовний (віртуальний годинник). */
public final class Logger {
    private static int seconds = 9 * 3600;

    private Logger() {
    }

    public static synchronized void log(String source, String event) {
        seconds++;
        System.out.printf("[%02d:%02d:%02d] [%s] %s%n",
                seconds / 3600, (seconds / 60) % 60, seconds % 60, source, event);
    }

    /** Емуляція «дорогої» операції: затримка + повідомлення. */
    public static void expensive(String source, String event, long millis) {
        log(source, event + " ...");
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void section(String title) {
        System.out.println();
        System.out.println("=== " + title + " ===");
    }
}
