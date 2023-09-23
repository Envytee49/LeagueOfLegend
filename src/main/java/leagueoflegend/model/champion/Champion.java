package leagueoflegend.model.champion;

import leagueoflegend.model.GeneralInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Champion extends GeneralInfo {
    private static int nextId = 1;
    private String title;
    private String role;
    private String difficulty;
    private String mainScreenImage;
    private Map<String, Integer> species = new HashMap<>();;
    private ArrayList<Ability> abilities;
    private Map<String, Integer> relatedRegion = new HashMap<>();;
    public  Champion () {
        super();
    }
    public Champion(String name,
                    String description,
                    ArrayList<String> relatedChampions,
                    String imageURL,
                    String mainScreenImage,
                    String title,
                    String role,
                    String difficulty,
                    ArrayList<String> species,
                    ArrayList<Ability> abilities,
                    ArrayList<String> relatedRegion) {
        super(nextId++, name, description, relatedChampions, imageURL);
        this.mainScreenImage = mainScreenImage;
        this.title = title;
        this.role = role;
        this.difficulty = difficulty;
        this.setSpecies(species);
        this.abilities = abilities;
        this.setRelatedRegion(relatedRegion);
    }

    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        Champion.nextId = nextId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMainScreenImage() {
        return mainScreenImage;
    }

    public void setMainScreenImage(String mainScreenImage) {
        this.mainScreenImage = mainScreenImage;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Map<String, Integer> getSpecies() {
        return species;
    }

    public void setSpecies(ArrayList<String> species) {
        for (String specie : species) {
            this.species.put(specie, 0); //id 0 means unknown
        }
    }
    public void addSpecies(String name, Integer id) {
        this.species.put(name, id);
    }
    public ArrayList<Ability> getAbilities() {
        return abilities;
    }

    public void setAbilities(ArrayList<Ability> abilities) {
        this.abilities = abilities;
    }

    public Map<String, Integer> getRelatedRegion() {
        return relatedRegion;
    }

    public void setRelatedRegion(ArrayList<String> relatedRegion) {
        for (String region : relatedRegion) {
            this.relatedRegion.put(region, 0); //id 0 means unknown
        }
    }
    public void addRelatedRegion(String name, Integer id) {
        System.out.println(this.getName());
        System.out.println(name+" "+id);
        this.relatedRegion.put(name, id);
    }
}
