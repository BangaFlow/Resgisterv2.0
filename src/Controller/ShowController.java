package Controller;

import Model.ConnectionDB;
import Model.ProduitStock;
import Model.StockServices;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.cells.editors.IntegerTextFieldEditorBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ShowController implements Initializable {
    @FXML
    private AnchorPane mainPane;

    @FXML
    private TableView<ProduitStock> table_produit;

    @FXML
    private TableColumn<ProduitStock, String> table_nom;

    @FXML
    private TableColumn<ProduitStock, Double> table_prix;

    @FXML
    private TableColumn<ProduitStock, String> table_description;

    @FXML
    private TableColumn<ProduitStock, String> table_category;

    @FXML
    private TableColumn<ProduitStock, Integer> table_Quantity;

    @FXML
    private TableColumn<String, Button> table_update;

    private Connection cnx;
    @FXML
    private JFXTextField searchInput;

    public ShowController() {
        cnx = ConnectionDB.getInstance().getConnection();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        table_produit.setEditable(true);
        table_nom.setCellFactory(TextFieldTableCell.forTableColumn());
        table_description.setCellFactory(TextFieldTableCell.forTableColumn());
        table_category.setCellFactory(TextFieldTableCell.forTableColumn());
        table_prix.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        table_Quantity.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        initColumns();
        loadData();
    }

    private void initColumns() {

        //id_prod.setCellValueFactory(new PropertyValueFactory<>("id"));
        table_nom.setCellValueFactory(new PropertyValueFactory<>("name"));
        table_Quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        table_category.setCellValueFactory(new PropertyValueFactory<>("category"));
        table_prix.setCellValueFactory(new PropertyValueFactory<>("price"));
        table_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        //table_update.setCellValueFactory(new PropertyValueFactory<String,Button>(new Button("update")));
        //colEdit.setCellFactory(cellFactory);

    }

    @FXML
    private void editableName(TableColumn.CellEditEvent editedCell) throws SQLException {
        ProduitStock prod = table_produit.getSelectionModel().getSelectedItem();
        //prod.setName(editedCell.getNewValue().toString());
        int id = prod.getId();
        String req = "update product set name=? where id=" + id;
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, editedCell.getNewValue().toString());
        ps.executeUpdate();
    }

    @FXML
    private void editablePrice(TableColumn.CellEditEvent editedCell) throws SQLException {
        ProduitStock prod = table_produit.getSelectionModel().getSelectedItem();
        //prod.setName(editedCell.getNewValue().toString());
        int id = prod.getId();
        String req = "update product set price=? where id=" + id;
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, editedCell.getNewValue().toString());
        ps.executeUpdate();
    }

    @FXML
    private void editableDescription(TableColumn.CellEditEvent editedCell) throws SQLException {
        ProduitStock prod = table_produit.getSelectionModel().getSelectedItem();
        //prod.setName(editedCell.getNewValue().toString());
        int id = prod.getId();
        String req = "update product set description=? where id=" + id;
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, editedCell.getNewValue().toString());
        ps.executeUpdate();
    }

    @FXML
    private void editableCategory(TableColumn.CellEditEvent editedCell) throws SQLException {
        ProduitStock prod = table_produit.getSelectionModel().getSelectedItem();
        //prod.setName(editedCell.getNewValue().toString());
        int id = prod.getId();
        String req = "update product set category=? where id=" + id;
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, editedCell.getNewValue().toString());
        ps.executeUpdate();
    }

    @FXML
    private void editableQuantity(TableColumn.CellEditEvent editedCell) throws SQLException {
        ProduitStock prod = table_produit.getSelectionModel().getSelectedItem();
        //prod.setName(editedCell.getNewValue().toString());
        int id = prod.getId();
        String req = "update product set quantity=? where id=" + id;
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, editedCell.getNewValue().toString());
        ps.executeUpdate();
    }

    private void loadData() {
        ObservableList<ProduitStock> data = null;
        try {
            data = FXCollections.observableArrayList(new StockServices().listerProduit());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        table_produit.setItems(data);
    }

    @FXML
    private void Retour(ActionEvent actionEvent) {
        Pane pane = null;
        try {
            pane = FXMLLoader.load(getClass().getResource("../View/sample.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        mainPane.getChildren().setAll(pane);
    }

    @FXML
    private void deleteProduit(javafx.scene.input.MouseEvent mouseEvent) {
        ProduitStock prod = table_produit.getSelectionModel().getSelectedItem();
        StockServices s = new StockServices();
        int id = prod.getId();
        try {
            s.supprimerProduit(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        loadData();
    }

    @FXML
    private void searchTable() {
        String s = searchInput.getText();
        ObservableList<ProduitStock> data = null;
        try {
            data = FXCollections.observableArrayList(new StockServices().filtrerProduit(s));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /*initColumns();
        table_produit.setItems(data);*/
    }

    @FXML
    private void deleteProduitMenu(ActionEvent actionEvent) {
        ProduitStock prod = table_produit.getSelectionModel().getSelectedItem();
        StockServices s = new StockServices();
        int id = prod.getId();
        try {
            s.supprimerProduit(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        loadData();
    }
}
