import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

// Класс "Игрушки" с содержанием из ТЗ
public class Toys {

    Integer id;
    String name;
    Integer number;
    Integer probability;

    // Конструктор для класса
    public Toys(Integer id, String name, Integer number, Integer probability) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.probability = probability;
    }

    // Метод для добавления новой игрушки в файл
    public static void AddToysInStock(Toys toysForAdd) throws IOException {
        FileWriter stockFill = new FileWriter("Stock.txt", true);
        stockFill.write(Integer.toString(toysForAdd.id) + "," + toysForAdd.name + "," + Integer.toString(toysForAdd.number) + "," + Integer.toString(toysForAdd.probability) + "\n");
        stockFill.flush();
        stockFill.close();       
    }

    // Метод изменения веса
    public static void ChangeProbability(Toys toysForChange, Integer newProbability) {
        toysForChange.probability = newProbability;
    }

    // Метод считывает из файла все строки и преобразует каждую строку в экземляр класса Toys, после чего отправляет его в массив
    public static void readStock (ArrayList<Toys> toysNewId) throws IOException {
        List toysCollection = Files.readAllLines(Paths.get("Stock.txt"));
        for (Object line : toysCollection) {
            String[] toys = ((String) line).split(",");
            toysNewId.add(new Toys(Integer.parseInt(toys[0]), toys[1], Integer.parseInt(toys[2]), Integer.parseInt(toys[3])));
        }
    }
}
