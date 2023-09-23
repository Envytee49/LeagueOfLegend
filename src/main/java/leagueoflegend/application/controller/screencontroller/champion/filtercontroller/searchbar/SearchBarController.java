package leagueoflegend.application.controller.screencontroller.champion.filtercontroller.searchbar;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import leagueoflegend.application.controller.screencontroller.champion.filtercontroller.FilterController;
import leagueoflegend.application.controller.screencontroller.champion.filtercontroller.rolebar.RoleBarController;
import leagueoflegend.model.champion.Champion;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
public class SearchBarController extends FilterController implements Initializable {
    @FXML
    private TextField searchBox;
    public List<Champion> getContainNameList(String newValue,
                                             List<Champion> currentRoleList,
                                             List<Champion> currentDifficultyList) {
        List<Champion> currentList = getCurrentList(currentRoleList, currentDifficultyList);
        return currentList.stream().filter(s -> s.getName().toLowerCase().contains(newValue.toLowerCase())).toList();
    }
    public void setSearchBox() {
        searchBox.textProperty().addListener((observable, oldValue, newValue) -> {
            searchBox.setText(newValue.toUpperCase());
            if (newValue.isEmpty()) {
                currentSearchList = allList;
            }else {
                currentSearchList = allList.stream()
                        .filter(s -> s.getName().toLowerCase().contains(newValue.toLowerCase())).toList();
            }
            filteredList = getContainNameList(newValue, currentRoleList, currentDifficultyList);
            championScreenController.setFilteredGrid();
        });
    }
    public String getNewValue() {
        return searchBox.getText();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new RoleBarController().setSearchBarController(this);
        System.out.println(this);
        setSearchBox();
        currentSearchList = allList;
    }
}
