package ru.nsu.fit.oop.lab5;

import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.io.StringWriter;
import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

class NotebookTest {
    private static Notebook generateNotebook() {
        Notebook nb = new Notebook();
        nb.add("1", "qwerty");
        nb.add("2 point", "123456");
        nb.add("3", "wwwwww");
        nb.add("4 point", "eeeeeee");
        nb.add("5", "nobodyTrustU");
        nb.add("6", "AllUrBaseRBelongToUs");
        nb.add("7", "RoflanPominki");
        nb.add("8 point", "UwU");
        nb.add("9", "Hermeus mora");

        return nb;
    }


    @Test()
    void load_save() {
        Notebook nb = generateNotebook();

        StringWriter writer = new StringWriter();
        nb.save(writer);
        StringReader reader = new StringReader(writer.toString());

        Notebook new_nb = new Notebook();
        new_nb.load(reader);

        assertThat(new_nb.getNotes()).containsExactlyElementsOf(nb.getNotes());
    }


    @Test
    void remove() {
        Notebook nb = generateNotebook();
        assertThat(nb.getNotes()).anyMatch(t -> t.getTitle().equals("9"));
        nb.remove("9");
        assertThat(nb.getNotes()).noneMatch(t -> t.getTitle().equals("9"));
        assertThat(nb.getNotes().size()).isEqualTo(8);
    }

    @Test
    void getNotes() {
        Notebook nb = generateNotebook();
        assertThat(nb.getNotes().size()).isEqualTo(9);
        assertThat(nb.getNotes(Instant.MIN, Instant.MAX, new String[]{"1"}).size()).isEqualTo(1);
        assertThat(nb.getNotes(Instant.MIN, Instant.MAX, new String[]{"point"}).size()).isEqualTo(3);
        assertThat(nb.getNotes(Instant.MIN, Instant.MAX, new String[]{"1", "point"}).size()).isEqualTo(4);
    }
}