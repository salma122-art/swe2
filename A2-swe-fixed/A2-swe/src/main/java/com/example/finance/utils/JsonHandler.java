package com.example.finance.utils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.finance.models.Expense;
import com.example.finance.models.Income;
import com.example.finance.models.Transaction;
import com.example.finance.models.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
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
     * Ensure data folder exists.
     */
    public static void ensureDataDirectoryExists() {

        File dir = new File(DATA_DIR);

        if (!dir.exists()) {

            dir.mkdirs();
        }
    }

    // ======================================================
    // USERS
    // ======================================================

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
     * Custom transaction loader.
     */
    public static List<Transaction>
    loadTransactionsFromFile(
            String fileName) {

        List<Transaction> transactions =
                new ArrayList<>();

        File file =
                new File(fileName);

        if (!file.exists()) {

            return transactions;
        }

        try (FileReader reader =
                     new FileReader(file)) {

            JsonArray jsonArray =
                    gson.fromJson(
                            reader,
                            JsonArray.class
                    );

            if (jsonArray == null) {

                return transactions;
            }

            for (JsonElement element : jsonArray) {

                JsonObject obj =
                        element.getAsJsonObject();

                double amount =
                        obj.get("amount")
                                .getAsDouble();

                int transactionId =
                        obj.get("transactionId")
                                .getAsInt();

                String notes =
                        obj.get("notes")
                                .getAsString();

                Date date =
                        gson.fromJson(
                                obj.get("date"),
                                Date.class
                        );

                // Expense
                if (obj.has("categoryId")) {

                    int categoryId =
                            obj.get("categoryId")
                                    .getAsInt();

                    transactions.add(
                            new Expense(
                                    transactionId,
                                    amount,
                                    date,
                                    notes,
                                    categoryId
                            )
                    );
                }

                // Income
                else if (obj.has("source")) {

                    String source =
                            obj.get("source")
                                    .getAsString();

                    transactions.add(
                            new Income(
                                    transactionId,
                                    amount,
                                    date,
                                    notes,
                                    source
                            )
                    );
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return transactions;
    }

    // ======================================================
    // SAVE GENERIC LIST
    // ======================================================

    public static <T> void saveListToFile(
            String fileName,
            List<T> data) {

        ensureDataDirectoryExists();

        try (FileWriter writer =
                     new FileWriter(fileName)) {

            gson.toJson(data, writer);

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    // ======================================================
    // LOAD GENERIC LIST
    // ======================================================

    public static <T> List<T> loadListFromFile(
            String fileName,
            Class<T> classType) {

        File file = new File(fileName);

        if (!file.exists()) {

            return new ArrayList<>();
        }

        try (FileReader reader =
                     new FileReader(file)) {

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
}