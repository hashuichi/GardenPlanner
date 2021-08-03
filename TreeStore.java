import java.util.*;

public class TreeStore extends GardenItemStore {
    public TreeStore() {
        super();
    }

    public TreeStore(String filename) {
        super(filename);
    }

    public TreeStore(String filename, int prefixLen) {
        super(filename, prefixLen);
    }
    
    @Override
    public String getRandomItem(String key){
        String line = super.getRandomItem(key);
        if (line != null) {
            int height = Integer.parseInt(line.replaceAll("\\D",""));
            String plant = removeHeight(line);
            if (height > 80){
                return plant+ "(very tall tree)";
            } else if (height > 15){
                return plant+ "(tall tree)";
            } else{
                return plant+ "(small tree)";
            }
        }
        return null;
    }

    //remove the numerical height from the string of the tree
    public String removeHeight(String line){
        int i = 0;
        String result = "";
        while (!Character.isDigit(line.charAt(i))){
            result = result + line.charAt(i);
            i++;
        }
        return result;
    }
}