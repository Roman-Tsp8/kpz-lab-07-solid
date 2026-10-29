package variant8_logisticsdelivery.ui;

import java.io.PrintStream;
import java.util.Scanner;

/** Тонкий шар консольного вводу/виводу. Жодної логіки. */
public final class ConsoleShipmentView {
    private final Scanner in;
    private final PrintStream out;

    public ConsoleShipmentView(Scanner in, PrintStream out) {
        this.in = in;
        this.out = out;
    }

    /** @return введений рядок або null, якщо ввід закінчився */
    public String ask(String prompt) {
        out.print(prompt);
        out.flush();
        return in.hasNextLine() ? in.nextLine() : null;
    }

    public void show(String text) {
        out.println(text);
    }

    public void showError(String message) {
        out.println("Помилка: " + message);
    }
}
