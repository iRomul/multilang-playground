package io.github.iromul.term;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class LanternaDemo {

    public static void main(String[] args) {
        DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();

        defaultTerminalFactory.setForceTextTerminal(true);

        try (Terminal terminal = defaultTerminalFactory.createTerminal()) {
            terminal.setBackgroundColor(TextColor.ANSI.CYAN_BRIGHT);
            terminal.putString("Hello there");
            terminal.flush();

            Thread.sleep(1000);

            int x = 1;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
