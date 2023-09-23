package leagueoflegend.datasaver;

import leagueoflegend.model.GeneralInfo;
import leagueoflegend.model.champion.Champion;
import leagueoflegend.model.species.Species;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DataLinker {

    public static <T extends GeneralInfo> void linkChampListAnd(List<T> dataList, List<Champion> listOfChampions) {
        // Specify id of related champions into T list
        for (T data : dataList) {
            for (Map.Entry<String, Integer> entry : data.getRelatedChampions().entrySet()) {

                for (Champion c : listOfChampions) {
                    if (c.getName().equalsIgnoreCase(entry.getKey())) {
                        data.addRelatedChampion(entry.getKey(), c.getId());
                        break;
                    }
                }
            }
        }
        // Specify id of related T into champion list
        T testElement = dataList.get(0);
        for(Champion champion : listOfChampions) {
            Set<Map.Entry<String, Integer>> entries = null;

            if(testElement instanceof leagueoflegend.model.region.Region)
                entries = champion.getRelatedRegion().entrySet();
            else if(testElement instanceof Species)
                entries = champion.getSpecies().entrySet();

            Map.Entry<String, Integer> entry = entries.iterator().next();
            if(entry.getKey().equalsIgnoreCase("runeterra")) champion.addRelatedRegion("Runeterra",0);
            for(T data : dataList) {
                if(data.getName().toLowerCase().contains(entry.getKey().toLowerCase())) {

                    if(testElement instanceof leagueoflegend.model.region.Region)
                        champion.addRelatedRegion(entry.getKey(), data.getId());
                    else if(testElement instanceof Species)
                        champion.addSpecies(entry.getKey(), data.getId());
                    break;
                }

            }
        }
    }

    public static void linkChampToChamp(List<Champion> listOfChampions) {

        for(Champion champion : listOfChampions) {
            for (Map.Entry<String, Integer> entry : champion.getRelatedChampions().entrySet()) {
                for (Champion relatedChampion : listOfChampions) {
                    if (relatedChampion.getName().equalsIgnoreCase(entry.getKey())) {
                        champion.addRelatedChampion(entry.getKey(), relatedChampion.getId());
                        break;
                    }
                }
            }
        }
    }

    public static void linkSubAndParentSpecies(List<Species> listOfSpecies) {

        for(Species species : listOfSpecies) {
            // Specify id of subspecies
            for (Map.Entry<String, Integer> entry : species.getSubSpecies().entrySet()) {
                for (Species subSpecies : listOfSpecies) {
                    if (subSpecies.getName().equalsIgnoreCase(entry.getKey())) {
                        species.addSubSpecies(entry.getKey(), subSpecies.getId());
                        break;
                    }
                }
            }
            // Specify id of parent species
            for (Map.Entry<String, Integer> entry : species.getParentSpecies().entrySet()) {
                for (Species parentSpecies : listOfSpecies) {
                    if (parentSpecies.getName().equalsIgnoreCase(entry.getKey())) {
                        species.addParentSpecies(entry.getKey(), parentSpecies.getId());
                        break;
                    }
                }
            }
        }

    }

}
