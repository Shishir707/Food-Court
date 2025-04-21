package com.example.parthajavafinalproject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BinaryFileHelper {

    // Save a single object to the file (header once)
    public static void saveObject(File file, Object obj) {
        try {
            createDirIfNotExists(file);

            boolean fileExists = file.exists();
            try (FileOutputStream fos = new FileOutputStream(file, true); // append = true
                 ObjectOutputStream oos = fileExists
                         ? new AppendableObjectOutputStream(fos) // Skip header
                         : new ObjectOutputStream(fos)) {         // Write header
                oos.writeObject(obj);
            }

        } catch (IOException e) {
            System.out.println("Error saving object: " + obj);
            e.printStackTrace();
        }
    }

    // Read all objects from the file
    public static <T> List<T> readAllObjects(File file) {
        List<T> objects = new ArrayList<>();

        if (!file.exists()) return objects;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                try {
                    @SuppressWarnings("unchecked")
                    T obj = (T) ois.readObject();
                    objects.add(obj);
                } catch (EOFException eof) {
                    break; // End of file
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading objects from file: " + file);
            e.printStackTrace(); // Optionally handle errors differently
        }

        return objects;
    }

    // Write all objects to the file
    public static <T> void writeAllObjects(File file, List<T> objects) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            for (T obj : objects) {
                oos.writeObject(obj);
            }
        } catch (IOException e) {
            System.out.println("Error writing objects to file: " + file);
            e.printStackTrace();
        }
    }

    // Create parent directory if missing
    private static void createDirIfNotExists(File file) {
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
    }

    // Inner class to avoid rewriting the stream header
    private static class AppendableObjectOutputStream extends ObjectOutputStream {
        public AppendableObjectOutputStream(OutputStream out) throws IOException {
            super(out);
        }

        @Override
        protected void writeStreamHeader() throws IOException {
            // Skip writing the header
        }
    }
}
