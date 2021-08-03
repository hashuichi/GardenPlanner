import java.util.*;

public class OrchidStore extends GardenItemStore {
    public OrchidStore() {
        super();
    }

    public OrchidStore(String filename) {
        super(filename);
    }

    public OrchidStore(String filename, int prefixLen) {
        super(filename, prefixLen);
    }
    
    @Override
    public String getRandomItem(String key){
        String plant = super.getRandomItem(key);
        if (plant != null) {
            return plant+ " (orchid)";
        }
        return null;
    }
}