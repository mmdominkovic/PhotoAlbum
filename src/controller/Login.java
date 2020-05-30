package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.Main;
import model.Osoba;

public class Login {
    public static Osoba loggedInTeacher;

    @FXML
    Button prijaviSeBtn;
@FXML
Button odustaniBtn;
    @FXML
    TextField korisnickoImeTxt;

    @FXML
    PasswordField lozinkaTxt;
    @FXML
    TextField uloga;
    @FXML
    Label greskaLbl;

    @FXML
    Label uspjehLbl;

    @FXML
    public void odustani(ActionEvent ev){
    System.exit(0);

}
    @FXML
    public void prijaviSe(ActionEvent ev){
        String korisnickoIme = this.korisnickoImeTxt.getText();
        String lozinka = this.lozinkaTxt.getText();
        String uloga= this.uloga.getText();

        if (korisnickoIme.equals("") || lozinka.equals("")|| uloga.equals("") ){
            greskaLbl.setVisible(true);
            uspjehLbl.setVisible(false);
        } else {
            greskaLbl.setVisible(false);
            uspjehLbl.setVisible(true);

            try {
                Login.loggedInTeacher = Osoba.login(korisnickoIme, lozinka, uloga);
                if (Login.loggedInTeacher.getUloga().equals("admin")) {
                    Main.showWindow(
                            getClass(),
                            "../view/Admin.fxml",
                            "Welcome to administration", 600, 400
                    );

                } else if(Login.loggedInTeacher.getUloga().equals("korisnik")){
                    Main.showWindow(getClass(),
                            "../view/Korisnik.fxml",
                            "Dobrodošli!",600,400);
                }
                else {
                    greskaLbl.setText("Unesite ispravne korisničke podatke");
                    greskaLbl.setVisible(true);
                    uspjehLbl.setVisible(false);
                }

            } catch (Exception e) {
                System.out.println("Nastala je greška " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}