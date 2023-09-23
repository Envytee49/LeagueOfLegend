package leagueoflegend.model.species;

import leagueoflegend.model.GeneralInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Species extends GeneralInfo {
    private static int nextId = 1;
    private Map<String, Integer> title = new HashMap<>();;
    private Map<String, Integer> parentSpecies = new HashMap<>();;
    private Map<String, Integer> subSpecies = new HashMap<>();;

    public Species() {

    }
    public Species(String name,
                   String description,
                   ArrayList<String> relatedChampions,
                   String imageURL,
                   ArrayList<String> title,
                   ArrayList<String> parentSpecies,
                   ArrayList<String> subSpecies) {
        super(nextId++, name, description, relatedChampions, imageURL);
        this.setTitle(title);
        this.setParentSpecies(parentSpecies);
        this.setSubSpecies(subSpecies);
    }

    public Map<String, Integer> getTitle() {
        return title;
    }

    public void setTitle(ArrayList<String> titles) {
        for (String title : titles) {
            this.title.put(title, 0); //id 0 means unknown
        }
    }

    public Map<String, Integer> getParentSpecies() {
        return parentSpecies;
    }

    public void setParentSpecies(ArrayList<String> parentSpecies) {
        for (String parentSpecie : parentSpecies) {
            this.parentSpecies.put(parentSpecie, 0); //id 0 means unknown
        }
    }
    public void addParentSpecies(String name, Integer id) {
        this.parentSpecies.put(name, id);
    }
    public Map<String, Integer> getSubSpecies() {
        return subSpecies;
    }

    public void setSubSpecies(ArrayList<String> subSpecies) {
        for (String subSpecie : subSpecies) {
            this.subSpecies.put(subSpecie, 0); //id 0 means unknown
        }
    }

    public void addSubSpecies(String name, Integer id) {
        this.subSpecies.put(name, id);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Species species = (Species) obj;
        return getName().equals(species.getName());
    }
}
