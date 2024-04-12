import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String[] timeline = readFromFile("input.txt");
        System.out.println("проверка:"+ Arrays.toString(timeline));
        int otvet = findMaxInterval(timeline);
        System.out.println("ответ "+otvet);
        Map<String, Integer> m = findMaxIntervalForEachCard(timeline);
        System.out.println(m);
        printMaxIntervals(m);   //m сделан в виде HashMap, поэтому результат неотсортирован
        Map<String, Integer> m2 = new TreeMap<>(m);
        printMaxIntervals(m2);  //m2 сделан в виде TreeMap, результат отсортирован по ключу
    }

    private static void printMaxIntervals(Map<String, Integer> m) {
        System.out.println("максимальные интервалы для каждой карты");
        for (Map.Entry<String, Integer> pair : m.entrySet()) {
            System.out.println("Достоинство: "+pair.getKey()+" интервал "+pair.getValue() );
        }
    }

    private static int findMaxInterval(String[] timeline) {
        Map<String, Integer> lastVisitTime = new HashMap<>(14); //исходную емкость словаря задавать необязательно, но в данном случае мы знаем какие бывают карты - 14 вариантов
        int maxInterval=0;
        for (int i = 0; i < timeline.length; i++) {
            String card = timeline[i];
            if(lastVisitTime.containsKey( card )){
                int interval = i - lastVisitTime.get(card);
                if( maxInterval < interval)
                    maxInterval = interval;
            }
            lastVisitTime.put(card, i);
        }
        return maxInterval;
    }

    private static String[] readFromFile(String fname) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(fname));
        int n = scanner.nextInt();
        scanner.nextLine(); //надо добавить переход на следующую строку
        String bigStr = scanner.nextLine();
        scanner.close();
        String[] masStr = bigStr.split(" ");
        return masStr;
    }

    private static Map<String, Integer> findMaxIntervalForEachCard(String[] timeline) {
        Map<String, Integer> lastVisitTime = new HashMap<>();
        Map<String, Integer> maxIntervalOfCard= new HashMap<>();

        for (int i = 0; i < timeline.length; i++) {
            String card = timeline[i];
            if(lastVisitTime.containsKey( card )){
                int interval = i - lastVisitTime.get(card);
                if(maxIntervalOfCard.containsKey(card)) {
                    int m = maxIntervalOfCard.get(card);
                    if (m < interval)
                        maxIntervalOfCard.put(card, interval);
                }
                else
                    maxIntervalOfCard.put(card, interval);
            }
            lastVisitTime.put(card, i);
        }
        return maxIntervalOfCard;
    }
}