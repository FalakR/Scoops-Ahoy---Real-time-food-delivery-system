package data_access;
import entities.IceCream;
import entities.IceCreamFactory;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class IceCreamDataAccessObject {

    private final File csvFile;
    private final Map<String, Integer> headers = new HashMap<>();
    private final Map<String, IceCream> iceCreams = new HashMap<>();
    private final IceCreamFactory iceCreamFactory;

    public IceCreamDataAccessObject(String csvPath, IceCreamFactory iceCreamFactory) throws IOException {
        this.iceCreamFactory = iceCreamFactory;

        csvFile = new File(csvPath);
        headers.put("name", 0);
        headers.put("flavour", 1);
        headers.put("price", 2);

        if (csvFile.length() == 0) {
            save(); // Create an empty CSV file if it doesn't exist
        } else {
            loadIceCreamsFromCSV(); // Load ice creams from existing CSV file
        }
    }

    private void loadIceCreamsFromCSV() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String header = reader.readLine();

            assert header.equals("name,flavour,price");

            String row;
            while ((row = reader.readLine()) != null) {
                String[] col = row.split(",");
                String name = col[headers.get("name")];
                String flavour = col[headers.get("flavour")];
                double price = Double.parseDouble(col[headers.get("price")]);

                IceCream iceCream = iceCreamFactory.create(name, flavour, price);
                iceCreams.put(name, iceCream);
            }
        }
    }

    public void save() {
        // Save ice creams to the CSV file
        try (FileWriter writer = new FileWriter(csvFile)) {
            writer.write("name,flavour,price\n");

            for (IceCream iceCream : iceCreams.values()) {
                writer.write(String.format("%s,%s,%.2f\n",
                        iceCream.getName(), iceCream.getFlavour(), iceCream.getPrice()));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public IceCream getIceCreamByName(String name) {
        return iceCreams.get(name);
    }

    public void addIceCream(IceCream iceCream) {
        iceCreams.put(iceCream.getName(), iceCream);
        save();
    }

    public void removeIceCream(IceCream iceCream) {
        iceCreams.remove(iceCream.getName());
        save();
    }

    public Map<String, IceCream> getAllIceCreams() {
        return new HashMap<>(iceCreams);
    }
}
