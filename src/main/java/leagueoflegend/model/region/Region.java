package leagueoflegend.model.region;

import leagueoflegend.model.GeneralInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Region extends GeneralInfo {
    private static int nextId = 1;
    private Map<String, Integer> demonym = new HashMap<>();;
    private Map<String, Integer> establishmentHistory = new HashMap<>();;

    public  Region() {

    }
    public Region(String name,
                  String description,
                  ArrayList<String> relatedChampions,
                  String imageURL,
                  ArrayList<String> demonym,
                  ArrayList<String> establishmentHistory) {
        super(nextId++, name, description, relatedChampions, imageURL);
        this.setDemonym(demonym);
        this.setEstablishmentHistory(establishmentHistory);
    }

    public Map<String, Integer> getDemonym() {
        return demonym;
    }

    public void setDemonym(ArrayList<String> demonyms) {
        for (String demonym : demonyms) {
            this.demonym.put(demonym, 0); //id 0 means unknown
        }
    }
    public Map<String, Integer> getEstablishmentHistory() {
        return establishmentHistory;
    }

    public void setEstablishmentHistory(ArrayList<String> establishmentHistory) {
        for (String establishment : establishmentHistory) {
            this.establishmentHistory.put(establishment, 0); //id 0 means unknown
        }
    }


}
