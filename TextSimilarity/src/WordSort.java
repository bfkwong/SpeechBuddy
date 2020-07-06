import java.util.*;

public class WordSort {

    private String words;
    private String[] wordList; 
    private Map<String, Integer> wordMap;

    public WordSort(String words) {
        this.words = words;
        this.wordList = createWordList(words);
        this.wordMap = createWordMap(this.wordList);
    }

    private static String[] createWordList(String words) {
        words = words.replaceAll("\\p{P}", "").toLowerCase();
        String[] wordList = words.split("\\s+");
        return wordList;
    }

    private static Map<String, Integer> createWordMap(String[] words) {
        Map<String, Integer> wordMap = new HashMap<>();
        Map<String, Integer> sortedWordMap = new LinkedHashMap<>();

        for(String word: words) {
            Integer count = wordMap.get(word);
            count = (count==null) ? 1 : ++count;
            wordMap.put(word, count);
        }
        wordMap.entrySet()
               .stream()
               .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
               .forEachOrdered(x -> sortedWordMap.put(x.getKey(), x.getValue()));
        return sortedWordMap;
    }

    public static void printMap(Map<String, Integer> wordMap) {
        for(Map.Entry<String, Integer> entry: wordMap.entrySet()) {
            System.out.println("Key: " + entry.getKey() + " | Val: " + entry.getValue());
        }
    }
    
    public String getWords() {
        return this.words;
    }

    public String[] getWordList() {
        return this.wordList;
    }

    public Map<String, Integer> getWordMap() {
        return this.wordMap;
    }
}