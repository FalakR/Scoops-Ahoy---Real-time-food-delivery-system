package data_access;

import entities.IceCream;
import entities.IceCreamFactory;
import use_cases.add_to_cart.AddToCartDataAccessInterface;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FileIceCreamDataAccessObject implements AddToCartDataAccessInterface {

    private final File csvFile;
    private final Map<String, Integer> headers = new LinkedHashMap<>();


    private final Map<String, IceCream> accounts = new HashMap<>();


    private IceCreamFactory iceCreamFactory;

    public FileIceCreamDataAccessObject(String csvPath, IceCreamFactory iceCreamFactory) throws IOException {
        this.iceCreamFactory = iceCreamFactory;

        csvFile = new File(csvPath);
        headers.put("name", 0);
        headers.put("flavour", 1);
        headers.put("price", 2);

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {

            String header = reader.readLine();

            assert header.equals("name,flavour,price");

            String row;
            while ((row = reader.readLine()) != null) {
                String[] col = row.split(",");
                String name = String.valueOf(col[headers.get("name")]);
                String flavour = String.valueOf(col[headers.get("flavour")]);
                Integer price = Integer.valueOf(String.valueOf(col[headers.get("price")]));
                IceCream iceCream = iceCreamFactory.create(name, flavour, price);
                accounts.put(name, iceCream);
            }
        }
    }

    public IceCream getIceCream(String name) {
        return accounts.get(name);
    }

}
