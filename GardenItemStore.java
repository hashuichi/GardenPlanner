import java.util.*;
import java.io.*;

public class GardenItemStore {
    private Map<String, List<String>> map;
    private String filename;

    public GardenItemStore(){
        this.map = new HashMap<String, List<String>>();
    }

    public GardenItemStore(String filename){
        this.map = new HashMap<String, List<String>>();
        this.filename = filename;
        try (BufferedReader file = new BufferedReader(new FileReader(filename));) {
            String line;
            while (null != (line = file.readLine())) {
                String plant = line.toLowerCase();
                String key = plant.substring(0, 1);
                put(key, plant);
            }
         } catch(IOException e) {
            System.err.println(e.getMessage());
         }
    }

    public GardenItemStore(String filename, int prefixLen){
        this.map = new HashMap<String, List<String>>();
        this.filename = filename;
        try (BufferedReader file = new BufferedReader(new FileReader(filename));) {
            String line;
            while (null != (line = file.readLine())) {
                String plant = line.toLowerCase();
                for (int i = prefixLen; i > 0; i--){
                    if (plant.length() > i-1){
                        String key = plant.substring(0, i);
                        put(key, plant);
                    } 
                }
            }
         } catch(IOException e) {
            System.err.println(e.getMessage());
         }
    }

    public boolean containsKey(String key) {
        return map.containsKey(key) ? true : false;
    }

    public void put(String key, String plant){
        List<String> plants = new ArrayList<String>();
        if (map.get(key) == null){
            plants.add(plant.substring(0, key.length()).toUpperCase() + plant.substring(key.length(), plant.length()));
        } else {
            plants = map.get(key);
            plants.add(plant.substring(0, key.length()).toUpperCase() + plant.substring(key.length(), plant.length()));
        }
        map.put(key, plants);
    }

    public String getRandomItem(String key){
        if (map.get(key) != null){
            List<String> plants = map.get(key);
            Random r = new Random();
            String plant = plants.get(r.nextInt(plants.size()));
            return plant;
        }
        return null;   
    }
}