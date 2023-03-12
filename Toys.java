import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    public static void addNewToy() throws IOException {

        Scanner toyScanner = new Scanner(System.in);
        System.out.println("Укажите через запятую: Название игрушки, количество, вероятность выпадения в лотерее (в виде целого числа)");
        String toyInput = toyScanner.nextLine();
        toyInput = toyInput.replace(", ", ",");
        String[] temp = toyInput.split(",");
        toyScanner.close();
        
        // Считываем из файла последнюю строку чтобы узнать какой присвоить ID новой игрушке
        BufferedReader input = new BufferedReader(new FileReader("Stock.txt"));
        String last, line;
        last = "";
        line = "";
        while (null != (line = input.readLine())) {
            last = line;
        }

        String[] forNewToysId = ((String) last).split(",");
        Toys newToys = new Toys(Integer.parseInt(forNewToysId[0])+1, temp[0], Integer.parseInt(temp[1]), Integer.parseInt(temp[2]));
        addToysInStock(newToys);   
    }

    public static void addToysInStock(Toys toysForAdd) throws IOException {
        FileWriter stockFill = new FileWriter("Stock.txt", true);
        stockFill.write(Integer.toString(toysForAdd.id) + "," + toysForAdd.name + "," + Integer.toString(toysForAdd.number) + "," + Integer.toString(toysForAdd.probability) + "\n");
        stockFill.flush();
        stockFill.close();       
    }

    // Метод изменения веса. Считывает из файла список игрушек, находит по идентификатору нужную, меняет её вес и перезаписывает файл с новыми данными
    public static void changeProbability() throws IOException {
        Scanner toyScanner = new Scanner(System.in);
        System.out.println("Укажите через запятую идентификатор игрушки и её новый вес, вот так: id,newProbability");
        String toyInput = toyScanner.nextLine();
        toyInput = toyInput.replace(", ", ",");
        String[] temp = toyInput.split(",");
        toyScanner.close();

        ArrayList<Toys> toysNewId = new ArrayList<Toys>();
        
        readStock(toysNewId);

        for (int i = 0; i < toysNewId.size(); i++) {
            if (toysNewId.get(i).id == Integer.parseInt(temp[0])) {
                Toys tempToy = new Toys(Integer.parseInt(temp[0]), toysNewId.get(i).name, toysNewId.get(i).number, Integer.parseInt(temp[1]));
                toysNewId.set(i, tempToy);
            }
        }
        
        FileWriter clearFile = new FileWriter("Stock.txt", false);
        clearFile.write("");
        clearFile.flush();
        clearFile.close(); 
        
        for (int i = 0; i < toysNewId.size(); i++) {
            addToysInStock(toysNewId.get(i));
        }
    }

    // Метод для изменения количества игрушек.
    public static void changeNums(Toys newNums) throws IOException {
        ArrayList<Toys> toysNewId = new ArrayList<Toys>();
        readStock(toysNewId);

        for (int i = 0; i < toysNewId.size(); i++) {
            if (toysNewId.get(i).id == newNums.id) {
                toysNewId.set(i, newNums);
            }
        }
        
        FileWriter clearFile = new FileWriter("Stock.txt", false);
        clearFile.write("");
        clearFile.flush();
        clearFile.close(); 
        
        for (int i = 0; i < toysNewId.size(); i++) {
            addToysInStock(toysNewId.get(i));
        }
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
