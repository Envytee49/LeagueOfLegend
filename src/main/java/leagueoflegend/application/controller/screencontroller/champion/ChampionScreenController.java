package leagueoflegend.application.controller.screencontroller.champion;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import leagueoflegend.application.controller.RelatedDataLoaderController;
import leagueoflegend.application.controller.screencontroller.champion.filtercontroller.FilterController;
import leagueoflegend.executiondata.championdata.ChampionData;
import leagueoflegend.model.champion.Champion;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
public class ChampionScreenController implements Initializable {
    private Label emptyGridLabel;
    @FXML
    private AnchorPane championGrid;
    @FXML
    private GridPane gridpane;
    private FilterController filterController = new FilterController();
    protected static final List<Champion> allList = ChampionData.getChampionData().getData();
    public void setChampionImageCss(ImageView imageView) {
        imageView.setFitWidth(230);
        imageView.setFitHeight(270);
        imageView.setPreserveRatio(false);
    }
    public void setChampionImageOnMouseClick(ImageView championImage, Champion champion) {
        championImage.setOnMouseClicked( event ->
                new RelatedDataLoaderController<>(champion).loadData("/view/detail/champion/championDetail.fxml", event)
        );
    }
    public ImageView getChampionImage(String path) {
        ImageView imageView = new ImageView();
        Image image = new Image(path);
        imageView.setImage(image);
        setChampionImageCss(imageView);
        return imageView;
    }
    public void setNameButtonCss(Button nameButton) {
        nameButton.setPrefWidth(230);
        nameButton.setId("nameButton");
    }
    public void setNameButtonOnMouseClick(Button nameButton, Champion champion) {
        nameButton.setOnMouseClicked( event ->
                new RelatedDataLoaderController<>(champion).loadData("/view/detail/champion/championDetail.fxml", event)
        );
    }
    public Button getNameButton(String championName) {
        Button nameButton = new Button(championName.toUpperCase());
        setNameButtonCss(nameButton);
        return nameButton;
    }
    public void setEmptyGridLabel() {
        emptyGridLabel = new Label("No champions match the filter criteria.");
        setEmptyGridLabelCss();
    }
    public void setEmptyGridLabelCss() {
        AnchorPane.setTopAnchor(emptyGridLabel, 0.0);
        AnchorPane.setBottomAnchor(emptyGridLabel, 0.0);
        AnchorPane.setLeftAnchor(emptyGridLabel, 0.0);
        AnchorPane.setRightAnchor(emptyGridLabel, 0.0);
        emptyGridLabel.setPrefWidth(championGrid.getPrefWidth());
        emptyGridLabel.setStyle("-fx-font-size:20px");
        emptyGridLabel.setAlignment(Pos.CENTER);
    }
    public void displayEmptyGridLabel(GridPane grid) {
        try {
            if (grid.getChildren().isEmpty()) {
                championGrid.getChildren().add(emptyGridLabel);
            } else {
                championGrid.getChildren().remove(emptyGridLabel);
            }
        }catch (IllegalArgumentException e) {
            System.out.println("nothing biggy");
        }
    }
    public GridPane getFilteredGrid() {
        GridPane grid = new GridPane();
        int COLUMN_SIZE = 4;
        int ROW_SIZE = (int) Math.ceil((double) FilterController.filteredList.size() / COLUMN_SIZE);
        int cellValue = 0;
        for (int row = 0; row < ROW_SIZE; row++) {
            for (int col = 0; col < COLUMN_SIZE; col++) {
                if (cellValue < FilterController.filteredList.size()) {
                    Champion champion = FilterController.filteredList.get(cellValue);
                    String championName = champion.getName();
                    String path ="/image/Champion/" + championName + "/" + championName + "-screen.png";
                    // Set up Image
                    ImageView championImage = getChampionImage(path);
                    setChampionImageOnMouseClick(championImage, champion);
                    // Set up name button
                    Button nameButton = getNameButton(championName);
                    setNameButtonOnMouseClick(nameButton, champion);
                    // Set up image and button
                    VBox vBox = new VBox(championImage, nameButton);
                    vBox.setAlignment(Pos.CENTER);
                    vBox.setId("vbox-image-mainscreen");

                    grid.add(vBox, col, row);
                    cellValue++;
                } else {
                    break;  // Break out of the inner loop when all elements in filteredList have been processed
                }
            }
        }
        displayEmptyGridLabel(grid);
        return grid;
    }
    public void setFilteredGrid() {
        GridPane filteredGrid = getFilteredGrid();
        gridpane.getChildren().setAll(filteredGrid.getChildren());
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FilterController.filteredList = allList;
        setEmptyGridLabel();
        setFilteredGrid();
        filterController.setChampionScreenController(this);
    }

}