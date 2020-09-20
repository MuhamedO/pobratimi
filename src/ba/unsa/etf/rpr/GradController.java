package ba.unsa.etf.rpr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GradController {
    public TextField fieldNaziv;
    public TextField fieldBrojStanovnika;
    public ChoiceBox<Drzava> choiceDrzava;
    public ObservableList<Drzava> listDrzave;
    private Grad grad;
    public ListView<Grad> listViewPobratimi;
    public ObservableList<Grad> listPobratimi;
    public ChoiceBox<Grad> choiceGrad;
    public ObservableList<Grad> listGradovi;

    public GradController(Grad grad, ArrayList<Drzava> drzave) {
        this.grad = grad;
        listDrzave = FXCollections.observableArrayList(drzave);
        listGradovi = FXCollections.observableArrayList();
    }

    public GradController(Grad grad, ArrayList<Drzava> drzave, ArrayList<Grad> gradovi) {
        this.grad = grad;
        listDrzave = FXCollections.observableArrayList(drzave);
        listGradovi = FXCollections.observableArrayList(gradovi);
    }

    @FXML
    public void initialize() {
        choiceDrzava.setItems(listDrzave);
        choiceGrad.setItems(listGradovi);
        if (grad != null) {
            fieldNaziv.setText(grad.getNaziv());
            fieldBrojStanovnika.setText(Integer.toString(grad.getBrojStanovnika()));
            // choiceDrzava.getSelectionModel().select(grad.getDrzava());
            // ovo ne radi jer grad.getDrzava() nije identički jednak objekat kao član listDrzave
            for (Drzava drzava : listDrzave)
                if (drzava.getId() == grad.getDrzava().getId())
                    choiceDrzava.getSelectionModel().select(drzava);
            listPobratimi = FXCollections.observableArrayList(grad.getPobratimi());
        } else {
            choiceDrzava.getSelectionModel().selectFirst();
            listPobratimi = FXCollections.observableArrayList();
        }
        listViewPobratimi.setItems(listPobratimi);
    }

    public Grad getGrad() {
        return grad;
    }

    public void clickCancel(ActionEvent actionEvent) {
        grad = null;
        Stage stage = (Stage) fieldNaziv.getScene().getWindow();
        stage.close();
    }

    public void clickDodaj(ActionEvent actionEvent) {
        boolean provjera=false;
        if(choiceGrad.getValue()!=null) {
            for(Grad g : listPobratimi){
                if(g.equals(choiceGrad.getValue()) || choiceGrad.getValue()==grad){
                    provjera=true;
                    break;
                }
            }
            if(provjera==false) {
                listPobratimi.add(choiceGrad.getValue());
            }
        }
    }

    public void clickOk(ActionEvent actionEvent) {
        boolean sveOk = true;

        if (fieldNaziv.getText().trim().isEmpty()) {
            fieldNaziv.getStyleClass().removeAll("poljeIspravno");
            fieldNaziv.getStyleClass().add("poljeNijeIspravno");
            sveOk = false;
        } else {
            fieldNaziv.getStyleClass().removeAll("poljeNijeIspravno");
            fieldNaziv.getStyleClass().add("poljeIspravno");
        }


        int brojStanovnika = 0;
        try {
            brojStanovnika = Integer.parseInt(fieldBrojStanovnika.getText());
        } catch (NumberFormatException e) {
            // ...
        }
        if (brojStanovnika <= 0) {
            fieldBrojStanovnika.getStyleClass().removeAll("poljeIspravno");
            fieldBrojStanovnika.getStyleClass().add("poljeNijeIspravno");
            sveOk = false;
        } else {
            fieldBrojStanovnika.getStyleClass().removeAll("poljeNijeIspravno");
            fieldBrojStanovnika.getStyleClass().add("poljeIspravno");
        }

        if (!sveOk) return;

        if (grad == null) grad = new Grad();
        grad.setNaziv(fieldNaziv.getText());
        grad.setBrojStanovnika(Integer.parseInt(fieldBrojStanovnika.getText()));
        grad.setDrzava(choiceDrzava.getValue());
        ArrayList<Grad> pobratimi = new ArrayList<>();
        pobratimi.addAll(listPobratimi);
        grad.setPobratimi(pobratimi);
        Stage stage = (Stage) fieldNaziv.getScene().getWindow();
        stage.close();
    }
}
