import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class GardenPlanner {
    public static void main(String[] args){
        List<String> plants;
        if (args.length < 2){
            plants = generate(args[0].toLowerCase());
        } else{
            plants = generatePlantsWithPrefix(args[0].toLowerCase(), Integer.parseInt(args[1]));
        }
        for (int i = 0; i < plants.size(); i++){
            System.out.println(plants.get(i));
        }
    }
    
    public static List<String> generate(String input){
        List<String> list = new ArrayList<String>();
        GardenItemStore shrubs = new GardenItemStore("shrubs.txt");
        GardenItemStore orchids = new OrchidStore("orchids.txt");
        GardenItemStore trees = new TreeStore("trees.txt");
        int i = 0;
        while (i < input.length()){
            char c = input.charAt(i);
            list.add(shrubs.getRandomItem(Character.toString(c).toLowerCase()));
            i++;
            if (i != input.length()){
                c = input.charAt(i);
                list.add(orchids.getRandomItem(Character.toString(c).toLowerCase()));
                i++; 
            }
            if (i != input.length()){
                c = input.charAt(i);
                list.add(trees.getRandomItem(Character.toString(c).toLowerCase()));
                i++;
            }
        }
        return list;
    }

    //generate the whole list of plants when a prefix length is given
    public static List<String> generatePlantsWithPrefix(String input, int prefixLen){
        List<String> list = new ArrayList<String>();
        GardenItemStore shrubs = new GardenItemStore("shrubs.txt", prefixLen);
        GardenItemStore orchids = new OrchidStore("orchids.txt", prefixLen);
        GardenItemStore trees = new TreeStore("trees.txt", prefixLen);
        int i = 0;
        while (i < input.length()){
            list = plantWithPrefix(list, shrubs, input, prefixLen, i);
            i = i + Integer.parseInt(list.get(list.size()-1));
            list.remove(list.size()-1);
            if (i != input.length()){
                list = plantWithPrefix(list, orchids, input, prefixLen, i);
                i = i + Integer.parseInt(list.get(list.size()-1));
                list.remove(list.size()-1);
            }
            if (i != input.length()){
                list = plantWithPrefix(list, trees, input, prefixLen, i);
                i = i + Integer.parseInt(list.get(list.size()-1));
                list.remove(list.size()-1);
            }
        }
        return list;
    }

    //generate one plant with a prefix length anywhere between the max and 1
    public static List<String> plantWithPrefix(List<String> list, GardenItemStore store, String input, int prefixLen, int stringStart){
        int l = prefixLen;
        while (l > 0){
            if (l+stringStart <= input.length()){
                String key = input.substring(stringStart, l+stringStart);
                String plant = store.getRandomItem(key);
                if (plant != null){
                    list.add(plant);
                    list.add(Integer.toString(l));
                    l = 0;
                } else if (plant == null) {
                    l--;
                }
            } else{
                l--;
            }
        }
        return list;
    }
}