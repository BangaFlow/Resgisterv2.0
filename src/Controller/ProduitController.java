package Controller;

import Model.ProduitStock;
import Model.StockServices;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProduitController {

    @FXML
    private JFXTextField name_produit;

    @FXML
    private JFXTextField price_store;

    @FXML
    private JFXTextField description_store;

    @FXML
    private JFXTextField category_store;

    @FXML
    private JFXTextField quantity_store;

    @FXML
    private AnchorPane mainPane;

    @FXML
    void saveProduct(ActionEvent event) {

        StockServices st= new StockServices();
        ProduitStock product = new ProduitStock();
        product.setName(name_produit.getText());
        product.setPrice(Double.parseDouble(price_store.getText()));
        product.setDescription(description_store.getText());
        product.setCategory(category_store.getText());
        product.setQuantity(Integer.parseInt(quantity_store.getText()));
        st.ajouterProduit(product);
        saveAlert(product);
        clearFields();
    }



    private void saveAlert(ProduitStock p){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Produit enregistré avec succès.");
        alert.setHeaderText(null);
        alert.setContentText("Le produit "+p.getName()+ " a été créé.");
        alert.showAndWait();
    }
    private void clearFields() {
        //Prod_id.setText("Prod ID:");
        name_produit.clear();
        price_store.clear();
        description_store.clear();
        category_store.clear();
        quantity_store.clear();
    }

    @FXML
    private void resetAjouter(ActionEvent actionEvent) {
        clearFields();
    }

    @FXML
    private void showList(ActionEvent actionEvent) {
        Pane pane = null;
        try {
            pane = FXMLLoader.load(getClass().getResource("../View/listproduits.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        mainPane.getChildren().setAll(pane);

    }
}



