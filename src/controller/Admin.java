package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import main.Main;
import model.Osoba;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;


public class Admin implements Initializable {
    @FXML
    TextField ime;

    @FXML
    TextField prezime;
    @FXML
    TextField korIme;
    @FXML
    TextField uloga;
    @FXML
    PasswordField lozinka;

    @FXML
    Label loggedUserLbl;

    @FXML
    TableView<Osoba> tableView;

    @FXML
    TableColumn<Osoba, String> imeTblCol;

    @FXML
    TableColumn<Osoba, String> prezimeTblCol;
    @FXML
    TableColumn<Osoba, String>ulogaTblCol;
    @FXML
    TableColumn<Osoba, String>korImeTblCol;
    @FXML
    public void addUserToDatabase (ActionEvent e) throws Exception{
            if(!this.ime.getText().equals("")&&
                    !this.prezime.getText().equals("")&&
                    !this.korIme.getText().equals("")&&
                    !this.uloga.getText().equals("")&&
                    !this.lozinka.getText().equals(""))
            {
                Osoba t = new Osoba();
                t.setIme(this.ime.getText());
                t.setPrezime(this.prezime.getText());
                t.setLozinka(this.lozinka.getText());
                t.setKorisnickoIme(this.korIme.getText());
                t.setUloga(this.uloga.getText());
                t.save();
                this.populateTableView();

                this.ime.setText("");
                this.prezime.setText("");
                this.lozinka.setText("");
                this.korIme.setText("");
                this.uloga.setText("");

            }


        }

    public void deleteUserFromDatabase(ActionEvent evt) throws Exception {
        Osoba o = tableView.getSelectionModel().getSelectedItem();
        o.delete();
        this.populateTableView();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.loggedUserLbl.setText(
                Login.loggedInTeacher.getIme() +
                        " " +
                        Login.loggedInTeacher.getPrezime()
        );

    this.imeTblCol.setCellValueFactory(new PropertyValueFactory<>("ime"));
    this.prezimeTblCol.setCellValueFactory(new PropertyValueFactory<>("prezime"));
        this.ulogaTblCol.setCellValueFactory(new PropertyValueFactory<>("uloga"));
        this.korImeTblCol.setCellValueFactory(new PropertyValueFactory<>("korisnickoIme"));

        this.populateTableView();
    }
private void populateTableView(){
    try {
        this.tableView.getItems().setAll((Collection<? extends Osoba>) Osoba.list(Osoba.class));

    } catch (Exception e) {
        System.out.println("Nismo uspjeli dohvatiti podatke");
    }
}





    @FXML
    public void logout(ActionEvent ev) throws IOException {
        Login.loggedInTeacher = null;
        Main.showWindow(
                getClass(),
                "../view/Login.fxml",
                "Login to system", 600, 215
        );
    }
}