package ru.nsu.fit.oop.lab5;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;
import com.google.gson.reflect.TypeToken;

import java.io.Reader;
import java.io.Writer;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Notebook {
    private final Gson json;
    private List<Note> notes = new ArrayList<>();

    Notebook() {
        json = new GsonBuilder()
                .registerTypeAdapter(Instant.class, (JsonDeserializer<Instant>) (json, typeOfT, context)
                        -> Instant.ofEpochMilli(json.getAsLong()))
                .registerTypeAdapter(Instant.class, (JsonSerializer<Instant>) (src, typeOfSrc, context)
                        -> new JsonPrimitive(src.toEpochMilli()))
                .create();
    }

    public void load(Reader reader) {
        ArrayList<Note> loaded = json.fromJson(reader, new TypeToken<ArrayList<Note>>() {
        }.getType());
        if (loaded != null) {
            notes = loaded;
        }
    }

    public void save(Writer writer) {
        json.toJson(notes, writer);
    }

    public void add(String title, String text) {
        notes.add(new Note(Instant.now().truncatedTo(ChronoUnit.MILLIS), title, text));
    }

    public void remove(String title) {
        notes.removeIf(note -> note.getTitle().equals(title));
    }

    public List<Note> getNotes() {
        return notes;
    }

    public List<Note> getNotes(Instant minDate, Instant maxDate, String[] keywords) {
        return notes.stream().filter((note) ->
                note.getTimestamp().isAfter(minDate) &&
                        note.getTimestamp().isBefore(maxDate) &&
                        Arrays.stream(keywords).anyMatch((keyword)
                                -> note.getTitle().toLowerCase().contains(keyword.toLowerCase()))
        ).collect(Collectors.toList());
    }
}
