import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UI {

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите цифру, соответствующую необходимой операции: ");
        System.out.println("1 - Добавить новую игрушку");
        System.out.println("2 - Поменять вес игрушки");
        System.out.println("3 - Организовать розыгрыш игрушек");
        int whatDoYouWant = in.nextInt();
        if (whatDoYouWant==1) {
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
            Toys.AddToysInStock(newToys);            
        } 
        if (whatDoYouWant==2) {
            Scanner toyScanner = new Scanner(System.in);
            System.out.println("Укажите через запятую идентификатор игрушки и её новый вес, вот так: id,newProbability");
            String toyInput = toyScanner.nextLine();
            toyInput = toyInput.replace(", ", ",");
            String[] temp = toyInput.split(",");
            toyScanner.close();

            ArrayList<Toys> toysNewId = new ArrayList<Toys>();
            Toys.readStock(toysNewId);

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
                Toys.AddToysInStock(toysNewId.get(i));
            }

        }
        if (whatDoYouWant==3) {

        }
        in.close();
    }
}
