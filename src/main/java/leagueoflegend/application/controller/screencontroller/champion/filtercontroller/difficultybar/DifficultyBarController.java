package leagueoflegend.application.controller.screencontroller.champion.filtercontroller.difficultybar;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import leagueoflegend.application.controller.screencontroller.champion.ChampionScreenController;
import leagueoflegend.application.controller.screencontroller.champion.filtercontroller.FilterController;
import leagueoflegend.application.controller.screencontroller.champion.filtercontroller.rolebar.RoleBarController;
import leagueoflegend.model.champion.Champion;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DifficultyBarController extends FilterController implements Initializable {
    @FXML
    private ComboBox<String> dropdown;
    public List<Champion> getDifficultyList(String difficulty,
                                            List<Champion> currentRoleList,
                                            List<Champion> currentSearchList) {

        List<Champion> currentList  = getCurrentList(currentRoleList, currentSearchList);
        if(difficulty.equals("ALL")) {
            return currentList;
        }
        return currentList.stream().filter(s->s.getDifficulty().equalsIgnoreCase(difficulty)).toList();
    }
    public void setDropdown() {
        dropdown.getItems().addAll("ALL", "LOW", "MODERATE", "HIGH");
        dropdown.getSelectionModel().selectFirst();
        dropdown.setOnAction(event -> {

            String difficulty = dropdown.getValue();
            if(difficulty.equals("ALL")) currentDifficultyList = allList;
            else {
                currentDifficultyList = allList.stream()
                        .filter(s -> s.getDifficulty().equalsIgnoreCase(difficulty)).toList();
            }
            filteredList = getDifficultyList(difficulty, currentRoleList, currentSearchList);
            championScreenController.setFilteredGrid();

        });
    }
    public String getDifficulty() {
        return dropdown.getValue();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new RoleBarController().setDifficultyBarController(this);
        setDropdown();
        currentDifficultyList = allList;
    }
}
