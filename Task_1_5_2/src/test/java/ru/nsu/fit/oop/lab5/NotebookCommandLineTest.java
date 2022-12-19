package ru.nsu.fit.oop.lab5;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;


class NotebookCommandLineTest {
    @Test
    void commandline() {
        Notebook nb = new Notebook();
        NotebookCommandLine commandLine = new NotebookCommandLine(nb);
        CommandLine executor = new CommandLine(commandLine)
                .registerConverter(Date.class, s
                        -> new SimpleDateFormat("dd.MM.yyyy HH:mm").parse(s));
        executor.execute("add", "First m", "textbewr5trebtt5");
        executor.execute("add", "Bad", "textbrebttewthyw5");
        executor.execute("add", "Second m", "textbrebtt5e5h4");
        executor.execute("add", "Third k", "textbrebtt5erh5r6");
        executor.execute("rm", "Bad");

        assertThat(nb.getNotes().size()).isEqualTo(3);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        System.setOut(new PrintStream(stream));
        executor.execute("show");
        assertThat(stream.toString()).contains("First");
        assertThat(stream.toString()).contains("Second");
        assertThat(stream.toString()).contains("Third");
    }
}
