package ba.unsa.etf.rpr;

import java.util.ArrayList;

public class Grad {
    private int id;
    private String naziv;
    private int brojStanovnika;
    private Drzava drzava;
    private ArrayList<Grad> pobratimi;

    public Grad(int id, String naziv, int brojStanovnika, Drzava drzava) {
        this.id = id;
        this.naziv = naziv;
        this.brojStanovnika = brojStanovnika;
        this.drzava = drzava;
        this.pobratimi = new ArrayList<>();
    }

    public Grad(int id, String naziv, int brojStanovnika, Drzava drzava, ArrayList<Grad> pobratimi) {
        this.id = id;
        this.naziv = naziv;
        this.brojStanovnika = brojStanovnika;
        this.drzava = drzava;
        this.pobratimi = pobratimi;
    }

    public Grad() {
        this.pobratimi = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getBrojStanovnika() {
        return brojStanovnika;
    }

    public void setBrojStanovnika(int brojStanovnika) {
        this.brojStanovnika = brojStanovnika;
    }

    public Drzava getDrzava() {
        return drzava;
    }

    public void setDrzava(Drzava drzava) {
        this.drzava = drzava;
    }

    public ArrayList<Grad> getPobratimi() {
        return pobratimi;
    }

    public void setPobratimi(ArrayList<Grad> pobratimi) {
        this.pobratimi = pobratimi;
    }

    @Override
    public String toString() { return naziv; }
}
