package model;

import java.sql.Blob;
import java.sql.Timestamp;

public class Slika extends Table {
    @Entity(type = "INTEGER", size =11, primary = true)
    int id;
    @Entity(type = "VARCHAR", size = 50,isnull = false)
    String nazivSlike;
    @Entity(type = "BLOB", isnull = false)
    Blob sadrzaj;
    @Entity(type = "TIMESTAMP", isnull = false)
    Timestamp dodano;
    @ForeignKey(table = "Album",attribute = "id")
    @Entity(type = "INTEGER", size = 11)
    int idAlbum;

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

    public Blob getSadrzaj() {
        return sadrzaj;
    }

    public void setSadrzaj(Blob sadrzaj) {
        this.sadrzaj = sadrzaj;
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
