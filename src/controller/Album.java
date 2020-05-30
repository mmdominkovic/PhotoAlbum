package controller;


import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.Main;
import model.Slika;

import javax.imageio.ImageIO;
import javax.sql.rowset.serial.SerialBlob;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;

public class Album implements Initializable {
    @FXML
    ImageView slikaView;
    @FXML
    TextField nazivSlikeTxt;


BufferedImage buffImage;
Image initialImage;
@FXML
public void dodajSliku(ActionEvent e) throws Exception{
   Slika s = new Slika();
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    s.setDodano(timestamp);

   s.setNazivSlike(this.nazivSlikeTxt.getText());
    SerialBlob image =new SerialBlob(imageToByte(this.buffImage));
    s.setSadrzajSlike(image);
}
    @FXML
    public void openFileDialog(MouseEvent e) throws Exception {
        FileChooser fc = new FileChooser();
        Node node = (Node) e.getSource();
        File file = fc.showOpenDialog(node.getScene().getWindow());
        this.buffImage = ImageIO.read(file);
        this.initialImage = slikaView.getImage();
        slikaView.setImage(SwingFXUtils.toFXImage(buffImage, null));
    }

        public void vratiNazad(ActionEvent e)throws Exception{
            Main.showWindow(getClass(),"../view/Korisnik.fxml","Album",600,400);
        }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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
