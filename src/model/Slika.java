package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Timestamp;

public class Slika extends Table {
    @Entity(type = "INTEGER", size =11, primary = true)
    int id;
    @Entity(type = "VARCHAR", size = 50,isnull = false)
    String nazivSlike;
    @Entity(type = "VARCHAR",size = 11, isnull = true)
    String favorites;
    @Entity(type = "BLOB", size = 10000000, isnull = false)
    SerialBlob sadrzajSlike;
    @Entity(type = "TIMESTAMP", isnull = false)
    Timestamp dodano;
    @ForeignKey(table = "Album",attribute = "id")
    @Entity(type = "INTEGER", size = 11)
    int idAlbum;

    public String getFavorites() {
        return favorites;
    }

    public void setFavorites(String favorites) {
        this.favorites = favorites;
    }

    public ImageView getSadrzajSlike() {
      try {
          return new ImageView(new Image(sadrzajSlike.getBinaryStream()));
      }catch (Exception e){
          return null;
      }
    }

    public void setSadrzajSlike(SerialBlob sadrzajSlike) {
        this.sadrzajSlike = sadrzajSlike;
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazivSlike() {
        return nazivSlike;
    }

    public void setNazivSlike(String nazivSlike) {
        this.nazivSlike = nazivSlike;
    }

    public Timestamp getDodano() {
        return dodano;
    }

    public void setDodano(Timestamp dodano) {
        this.dodano = dodano;
    }

    public int getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(int idAlbum) {
        this.idAlbum = idAlbum;
    }
  /*  @Override
    public String toString(){
        return "Photo [id="+ id + ", NazivSlike=" + nazivSlike +",Sadrzaj="+sadrzaj+" ]";
    } */
}
