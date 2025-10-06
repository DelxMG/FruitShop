import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FruitManager {

    private static List <Fruit> fruits = new ArrayList<>();
    private static final Path RUTA = Paths.get("fruits.txt");

    public static void addFruit(Scanner sc) {

        try {
            System.out.println("Introduce el ID de la fruta: ");
            int id = Integer.parseInt(sc.nextLine());

            System.out.println("Introduce el nombre de la fruta: ");
            String name = sc.nextLine();
            if (name.trim().length() <= 2) throw new IllegalArgumentException("Nombre demasiado corto");

            System.out.println("Introduce el precio por Kg de la fruta: ");
            double pricePerKg = Double.parseDouble(sc.nextLine());
            if (pricePerKg <= 0) throw new IllegalArgumentException("El precio no puede ser igual o menor que 0");

            System.out.println("Introduce el stock en Kg de la fruta: ");
            int stockKg = Integer.parseInt(sc.nextLine());
            if (stockKg <= 0) throw new IllegalArgumentException("El stock no puede ser igual o menor a 0");

            Fruit fruit = new Fruit(id, name, pricePerKg, stockKg);
            fruits.add(fruit);
            System.out.println("Fruta añadida correctamente");

        } catch (NumberFormatException e) {
            System.out.println("La entrada tiene que ser un número");

        } catch (IllegalArgumentException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public static void listFruits() {
        if (fruits.isEmpty()) {
            System.out.println("No hay frutas en la lista.");
        } else {
            for (Fruit fruit : fruits) {
                System.out.println(fruit);
            }
        }
    }

    public static void exportToTxt() {

        try (BufferedWriter bw = Files.newBufferedWriter(RUTA, StandardCharsets.UTF_8)) {

            for (Fruit fruit : fruits) {
                bw.write(fruit.getId() + ";" + fruit.getName() + ";" + fruit.getPricePerKg() + ";" + fruit.getStockKg());
                bw.newLine();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void showMenu() {
            System.out.println("\n--- Fruit Shop Menu ---");
            System.out.println("1. Add fruit");
            System.out.println("2. List fruits");
            System.out.println("3. Export to TXT");
            System.out.println("4. Import from TXT");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
    }

    public static void importFromTxt() {

        List<Fruit> read = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(RUTA, StandardCharsets.UTF_8)) {

            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");

                if (parts.length != 4) {
                    System.err.println("Linea con formato incorrecto " + line);
                }

                try {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    double pricePerKg = Double.parseDouble(parts[2]);
                    int stockKg = Integer.parseInt(parts[3]);

                    Fruit fruit = new Fruit(id, name, pricePerKg, stockKg);
                    read.add(fruit);


                } catch (NumberFormatException e) {
                    System.err.println("Err: " + line);
                }
            }

            fruits.clear();
            fruits.addAll(read);

            System.out.println("Frutas importadas desde el archivo:");
            listFruits();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
