package leagueoflegend.application.controller.screencontroller.champion.filtercontroller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import leagueoflegend.application.controller.screencontroller.champion.ChampionScreenController;
import leagueoflegend.executiondata.championdata.ChampionData;
import leagueoflegend.model.champion.Champion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FilterController {

    protected static ChampionScreenController championScreenController;
    protected static final List<Champion> allList = ChampionData.getChampionData().getData();
    protected static List<Champion> currentRoleList;
    protected static List<Champion> currentSearchList;
    protected static List<Champion> currentDifficultyList;
    public static List<Champion> filteredList = allList;
    public List<Champion> getCurrentList(List<Champion> list1, List<Champion> list2) {
        List<Champion> currentList  = new ArrayList<>();
        for(Champion champion : list1) {
            if(list2.contains(champion))
                currentList.add(champion);
        }
        return currentList;
    }

    public void setChampionScreenController(ChampionScreenController championScreenController) {
        FilterController.championScreenController = championScreenController;
    }

}
