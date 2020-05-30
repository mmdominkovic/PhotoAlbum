package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import main.Main;
import model.Album;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.ResourceBundle;

public class Korisnik implements Initializable {
   @FXML
    Label loggedUserLbl;
   @FXML
    TextField nazivAlbumTxt;
   @FXML
    TableView<Album> AlbumView;
   @FXML
   TableColumn<Album, String>NazivAlbumaCol;
   @FXML
   TableColumn<Album, Timestamp>datumCol;

    public void otvoriAlbum(ActionEvent e)throws Exception{
Main.showWindow(getClass(),"../view/Album.fxml","Album",600,400);
    }
   public void createAlbum(ActionEvent e) throws Exception{
           if(!this.nazivAlbumTxt.getText().equals(""))
           {   Album a = new Album();

               Timestamp timestamp = new Timestamp(System.currentTimeMillis());
               a.setNazivAlbuma(this.nazivAlbumTxt.getText());
               a.setKreiran(timestamp);
               a.setIdOsoba(Login.loggedInTeacher.getId());
               a.save();
               this.populateTableView();

               this.nazivAlbumTxt.setText("");

           }
   }
   public void  brisiAlbum(ActionEvent evt) throws  Exception{
       Album a= AlbumView.getSelectionModel().getSelectedItem();
       a.delete();
       this.populateTableView();
   }
@Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.loggedUserLbl.setText(
                Login.loggedInTeacher.getIme() + " "
                + Login.loggedInTeacher.getPrezime()
        );

 // this.NazivAlbumaCol.setCellValueFactory(new PropertyValueFactory<>("nazivAlbuma"));
 // this.datumCol.setCellValueFactory(new PropertyValueFactory<>("kreiran"));

        this.populateTableView();
    }

    private void populateTableView(){
        try {
            this.AlbumView.getItems().setAll((Collection<? extends Album>) Album.list(Album.class));

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
    }}
