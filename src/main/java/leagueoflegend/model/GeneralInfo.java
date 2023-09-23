package leagueoflegend.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class GeneralInfo {
    private int id;
    private String name;
    private String description;
    private Map<String, Integer> relatedChampions = new HashMap<>();
    private String imageURL;

    public GeneralInfo(int id, String name, String description, ArrayList<String> relatedChampions, String imageURL) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.setRelatedChampions(relatedChampions);
        this.imageURL = imageURL;
    }

    public GeneralInfo() {

    }


    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<String, Integer> getRelatedChampions() {
        return relatedChampions;
    }

    public void setRelatedChampions(ArrayList<String> relatedChampions) {
        if(relatedChampions == null) {
//            this.relatedChampions = null;
            return;
        }
        for (String champ : relatedChampions) {
            this.relatedChampions.put(champ, 0); //id 0 means unknown
        }
    }

    public void addRelatedChampion(String name, Integer id) {
        this.relatedChampions.put(name, id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public boolean containsNameForSearch(String name) {
        if (name == null)
            return false;

        if (this.name == null)
            return false;

        return this.name.toLowerCase().contains(name.toLowerCase());
    }

    public boolean containsID(int id) {
        return this.id == id;
    }
}
