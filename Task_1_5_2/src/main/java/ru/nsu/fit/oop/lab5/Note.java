package ru.nsu.fit.oop.lab5;

import java.time.Instant;

/**
 * Note class.
 */
public final class Note {
    private final Instant timestamp;
    private final String title;
    private final String text;

    /**
     * Note creator.
     *
     * @param timestamp time we create note
     * @param title title of note
     * @param text text of note
     */
    public Note(Instant timestamp, String title, String text) {
        this.timestamp = timestamp;
        this.title = title;
        this.text = text;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Note note = (Note) o;
        return timestamp.compareTo(note.timestamp)
                == 0 && title.equals(note.title) && text.equals(note.text);
    }
}
