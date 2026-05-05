package com.example.finance.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Generic JSON read/write helpers around Gson.
 * Single object and list overloads, plus a data-folder bootstrap.
 */
public class JsonHandler {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final String DATA_DIR = "data/";

    private JsonHandler() {
        // utility class
    }

    /** Save a single object to the given absolute or relative path. */
    public static <T> void saveToFile(String fileName, T data) {
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Load a single object from the given path. Returns null on I/O error. */
    public static <T> T loadFromFile(String fileName, Class<T> clazz) {
        try (FileReader reader = new FileReader(fileName)) {
            return gson.fromJson(reader, clazz);
        } catch (IOException e) {
            return null;
        }
    }

    /** Load a list using a fully-qualified Type token. */
    public static <T> List<T> loadListFromFile(String fileName, Type type) {
        try (FileReader reader = new FileReader(fileName)) {
            return gson.fromJson(reader, type);
        } catch (IOException e) {
            return null;
        }
    }

    // ===== Repository helpers (auto-create data dir, file name relative to data/) =====

    public static void ensureDataDirectoryExists() {
        File dir = new File(DATA_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    public static <T> void saveListToFile(String fileName, List<T> data) {
        ensureDataDirectoryExists();
        String fullPath = DATA_DIR + fileName;
        try (FileWriter writer = new FileWriter(fullPath)) {
            gson.toJson(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> List<T> loadListFromFile(String fileName, Class<T> classType) {
        ensureDataDirectoryExists();
        String fullPath = DATA_DIR + fileName;
        File file = new File(fullPath);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (FileReader reader = new FileReader(fullPath)) {
            Type type = TypeToken.getParameterized(List.class, classType).getType();
            List<T> result = gson.fromJson(reader, type);
            return result != null ? result : new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
