package com.example.finance.repositories;

import com.example.finance.utils.JsonHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Generic CRUD repository backed by a JSON file in the data/ directory.
 */
public abstract class BaseRepository<T> {

    protected List<T> items;
    protected final String fileName;

    protected BaseRepository(String fileName) {
        this.fileName = fileName;
        this.items = new ArrayList<>();
        load();
    }

    public void load() {
        List<T> loaded = JsonHandler.loadListFromFile(fileName, getType());
        this.items = loaded != null ? loaded : new ArrayList<>();
    }

    public void save() {
        JsonHandler.saveListToFile(fileName, items);
    }

    public List<T> getAll() {
        return new ArrayList<>(items);
    }

    public void add(T item) {
        items.add(item);
        save();
    }

    public void remove(T item) {
        items.remove(item);
        save();
    }

    public void clear() {
        items.clear();
        save();
    }

    /** Subclasses must declare the runtime type for Gson list deserialization. */
    protected abstract Class<T> getType();
}
