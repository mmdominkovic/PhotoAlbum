package controller;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import main.Main;
import model.Slika;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.ResourceBundle;

public class Album implements Initializable {

    @FXML
   Label loggedUserLbl;
    @FXML
    TableView<Object> slikaView;
    @FXML
    TextField nazivTxt;
    @FXML
    TableColumn<Slika, String>nazivTblCol;
    @FXML
    TableColumn<Slika, ImageView> slikaTblCol;
    @FXML
    ImageView ucitanaSlika;

BufferedImage buffImage;
Image initialImage;
@FXML
public void dodajSliku(ActionEvent e) throws Exception{
  if(!this.nazivTxt.getText().equals(""))
  { Slika s = new Slika();

    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    s.setDodano(timestamp);
   s.setNazivSlike(this.nazivTxt.getText());
  s.setIdAlbum(Login.loggedInTeacher.getId());
    SerialBlob image =new SerialBlob(imageToByte(this.buffImage));
    s.setSadrzajSlike(image);
    s.save();
    this.populateTableView();
    this.nazivTxt.setText("");
    this.ucitanaSlika.setImage(this.initialImage);
}}
    @FXML
    public void openFileDialog(MouseEvent e) throws Exception {
        FileChooser fc = new FileChooser();
        Node node = (Node) e.getSource();
        File file = fc.showOpenDialog(node.getScene().getWindow());
        this.buffImage = ImageIO.read(file);
        this.initialImage = ucitanaSlika.getImage();
        ucitanaSlika.setImage(SwingFXUtils.toFXImage(buffImage, null));
    }

        public void vratiNazad(ActionEvent e)throws Exception{
            Main.showWindow(getClass(),"../view/Korisnik.fxml","Album",600,400);
        }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.loggedUserLbl.setText(
                Login.loggedInTeacher.getIme() + " "
                        + Login.loggedInTeacher.getPrezime()
        );
        this.nazivTblCol.setCellValueFactory(new PropertyValueFactory<>("nazivSlike"));
    this.slikaTblCol.setCellValueFactory(new PropertyValueFactory<>("sadrzajSlike"));
this.populateTableView();
}
    private void populateTableView(){
        try {
            this.slikaView.getItems().setAll((Collection<? extends Slika>)Slika.list(Slika.class));

        } catch (Exception e) {
            System.out.println("Nismo uspjeli dohvatiti podatke");
        }
    }
    private byte[] imageToByte(BufferedImage bufferimage) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferimage, "jpg", output );
        } catch (IOException e) {
            System.out.println("Nastala je greška: " + e.getMessage());
            e.printStackTrace();
        }
        byte [] data = output.toByteArray();
        return data;
    }


}
