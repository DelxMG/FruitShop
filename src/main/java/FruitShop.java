import java.util.Scanner;

public class FruitShop {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option = 0;

        do {
            FruitManager.showMenu();
            try {
                option = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opción inválida. Introduce un número.");
                continue;
            }

            switch (option) {
                case 1 -> FruitManager.addFruit(sc);
                case 2 -> FruitManager.listFruits();
                case 3 -> FruitManager.exportToTxt();
                case 4 -> FruitManager.importFromTxt();
                case 5 -> System.out.println("¡Hasta pronto!");
                default -> System.out.println("Opción inválida. Debe ser un número entre el 1 y el 5");
            }

        } while (option != 5);

        sc.close();
    }
}