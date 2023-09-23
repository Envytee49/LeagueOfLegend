package leagueoflegend.application.controller.screencontroller.champion.filtercontroller.rolebar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import leagueoflegend.application.controller.screencontroller.champion.filtercontroller.FilterController;
import leagueoflegend.application.controller.screencontroller.champion.filtercontroller.difficultybar.DifficultyBarController;
import leagueoflegend.application.controller.screencontroller.champion.filtercontroller.searchbar.SearchBarController;
import leagueoflegend.model.champion.Champion;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RoleBarController extends FilterController implements Initializable {
    @FXML
    private Button allButton;

    @FXML
    private Button assasinButton;

    @FXML
    private Button fighterButton;

    @FXML
    private Button mageButton;

    @FXML
    private Button marksmenButton;

    @FXML
    private Button supportButton;

    @FXML
    private Button tankButton;
    private Button currentButton;
    private static SearchBarController searchBarController;
    private static DifficultyBarController difficultyBarController;
    public void setSearchBarController(SearchBarController searchBarController) {
        RoleBarController.searchBarController = searchBarController;
    }

    public static void setDifficultyBarController(DifficultyBarController difficultyBarController) {
        RoleBarController.difficultyBarController = difficultyBarController;
    }

    public List<Champion> getRoleList(String role,
                                      List<Champion> currentSearchList,
                                      List<Champion> currentDifficultyList) {
        List<Champion> currentList = getCurrentList(currentDifficultyList, currentSearchList);
        if (role.equals("All")) return currentList;
        return currentList.stream().filter(s -> s.getRole().equalsIgnoreCase(role)).toList();
    }
    public void setButtonOnFocused(Button button) {
        currentButton.setId("");
        currentButton = button;
        button.setId("focused-filter-role");
    }
    @FXML
    void showAll(ActionEvent event) {
        setButtonOnFocused(allButton);
        if(searchBarController.getNewValue().isEmpty() && difficultyBarController.getDifficulty().equals("ALL"))
            filteredList = allList;
        else filteredList = getRoleList("All", currentSearchList, currentDifficultyList);
        currentRoleList = allList;
        championScreenController.setFilteredGrid();
    }
    @FXML
    void showAssassin(ActionEvent event) {
        setButtonOnFocused(assasinButton);
        filteredList = getRoleList("Assassin", currentSearchList, currentDifficultyList);
        currentRoleList = allList.stream().filter(s -> s.getRole().equalsIgnoreCase("Assassin")).toList();
        championScreenController.setFilteredGrid();
    }
    @FXML
    void showFighter(ActionEvent event) {
        setButtonOnFocused(fighterButton);
        filteredList = getRoleList("Fighter", currentSearchList, currentDifficultyList);
        currentRoleList = allList.stream().filter(s -> s.getRole().equalsIgnoreCase("Fighter")).toList();
        championScreenController.setFilteredGrid();
    }

    @FXML
    void showMage(ActionEvent event) {
        setButtonOnFocused(mageButton);
        filteredList = getRoleList("Mage", currentSearchList, currentDifficultyList);
        currentRoleList = allList.stream().filter(s -> s.getRole().equalsIgnoreCase("Mage")).toList();
        championScreenController.setFilteredGrid();
    }

    @FXML
    void showMarksmen(ActionEvent event) {
        setButtonOnFocused(marksmenButton);
        filteredList= getRoleList("Marksman", currentSearchList, currentDifficultyList);
        currentRoleList = allList.stream().filter(s -> s.getRole().equalsIgnoreCase("Marksman")).toList();
        championScreenController.setFilteredGrid();
    }

    @FXML
    void showSupport(ActionEvent event) {
        setButtonOnFocused(supportButton);
        filteredList = getRoleList("Support", currentSearchList, currentDifficultyList);
        currentRoleList = allList.stream().filter(s -> s.getRole().equalsIgnoreCase("Support")).toList();
        championScreenController.setFilteredGrid();
    }

    @FXML
    void showTank(ActionEvent event) {
        setButtonOnFocused(tankButton);
        filteredList = getRoleList("Tank", currentSearchList, currentDifficultyList);
        currentRoleList = allList.stream().filter(s -> s.getRole().equalsIgnoreCase("Tank")).toList();
        championScreenController.setFilteredGrid();

    }
    public void setInitialButton() {
        allButton.setId("focused-filter-role");
        currentButton = allButton;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setInitialButton();
        currentRoleList = allList;
    }
}
