public class GardenItemStoreTest {
    public static void main(String[] args){
        GardenItemStore store = new GardenItemStore();
        store.put("a", "azalea");
        store.put("ba", "burning bush");
        store.put("ba", "bursting heart");
        store.put("a", "amur chokecherry");

        GardenItemStore store2 = new GardenItemStore("trees.txt");
        System.out.println(store2.containsKey("a"));
        System.out.println(store2.containsKey("c"));
        System.out.println(store2.containsKey("b"));
        System.out.println(store2.containsKey("g"));
        System.out.println(store2.containsKey("v"));

        System.out.println(store2.getRandomItem("p"));
        System.out.println(store2.getRandomItem("ab"));
        System.out.println(store2.getRandomItem("a"));
    }
}