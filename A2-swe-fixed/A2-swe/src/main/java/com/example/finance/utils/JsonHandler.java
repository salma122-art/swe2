package com.example.finance.utils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.example.finance.models.Transaction;
import com.example.finance.models.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * Generic JSON read/write helpers around Gson.
 */
public class JsonHandler {

    private static final Gson gson =
            new GsonBuilder()
                    .setPrettyPrinting()
                    .create();

    private static final String DATA_DIR = "data/";

    private JsonHandler() {
    }

    /**
     * Save any object to file.
     */
    public static <T> void saveToFile(String fileName,
                                      T data) {

        ensureDataDirectoryExists();

        try (FileWriter writer =
                     new FileWriter(fileName)) {

            gson.toJson(data, writer);

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    /**
     * Load single object.
     */
    public static <T> T loadFromFile(String fileName,
                                     Class<T> clazz) {

        try (FileReader reader =
                     new FileReader(fileName)) {

            return gson.fromJson(reader, clazz);

        } catch (IOException e) {

            return null;
        }
    }

    /**
     * Load list using Type.
     */
    public static <T> List<T> loadListFromFile(String fileName,
                                               Type type) {

        try (FileReader reader =
                     new FileReader(fileName)) {

            List<T> result =
                    gson.fromJson(reader, type);

            return result != null
                    ? result
                    : new ArrayList<>();

        } catch (IOException e) {

            return new ArrayList<>();
        }
    }

    // ======================================================
    // DATA DIRECTORY
    // ======================================================

    public static void ensureDataDirectoryExists() {

        File dir = new File(DATA_DIR);

        if (!dir.exists()) {

            dir.mkdirs();
        }
    }

    // ======================================================
    // SAVE LIST
    // ======================================================

    public static <T> void saveListToFile(String fileName,
                                          List<T> data) {

        ensureDataDirectoryExists();

        String fullPath =
                DATA_DIR + fileName;

        try (FileWriter writer =
                     new FileWriter(fullPath)) {

            gson.toJson(data, writer);

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    // ======================================================
    // GENERIC LOAD LIST
    // ======================================================

    public static <T> List<T> loadListFromFile(
            String fileName,
            Class<T> classType) {

        ensureDataDirectoryExists();

        String fullPath =
                DATA_DIR + fileName;

        File file =
                new File(fullPath);

        if (!file.exists()) {

            return new ArrayList<>();
        }

        try (FileReader reader =
                     new FileReader(fullPath)) {

            Type type =
                    TypeToken.getParameterized(
                            List.class,
                            classType
                    ).getType();

            List<T> result =
                    gson.fromJson(reader, type);

            return result != null
                    ? result
                    : new ArrayList<>();

        } catch (IOException e) {

            e.printStackTrace();

            return new ArrayList<>();
        }
    }

    // ======================================================
    // USERS
    // ======================================================

    /**
     * Load users from JSON.
     */
    public static List<User> loadUsersFromFile(
            String fileName) {

        File file = new File(fileName);

        if (!file.exists()) {

            return new ArrayList<>();
        }

        try (FileReader reader =
                     new FileReader(file)) {

            Type type =
                    new TypeToken<List<User>>() {
                    }.getType();

            List<User> users =
                    gson.fromJson(reader, type);

            return users != null
                    ? users
                    : new ArrayList<>();

        } catch (IOException e) {

            e.printStackTrace();

            return new ArrayList<>();
        }
    }

    // ======================================================
    // TRANSACTIONS
    // ======================================================

    /**
     * Load transactions from JSON.
     */
    public static List<Transaction>
    loadTransactionsFromFile(
            String fileName) {

        File file =
                new File(fileName);

        if (!file.exists()) {

            return new ArrayList<>();
        }

        try (FileReader reader =
                     new FileReader(file)) {

            Type type =
                    new TypeToken<List<Transaction>>() {
                    }.getType();

            List<Transaction> transactions =
                    gson.fromJson(reader, type);

            return transactions != null
                    ? transactions
                    : new ArrayList<>();

        } catch (IOException e) {

            e.printStackTrace();

            return new ArrayList<>();
        }
    }
}