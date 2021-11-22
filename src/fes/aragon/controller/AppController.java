package fes.aragon.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import fes.aragon.cola.Cola;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

public class AppController implements Initializable {

  @FXML
  private Button btnCargar;

  @FXML
  private TableView<Dia> tabla;

  @FXML
  private TableColumn<Dia, Integer> tblAcciones;

  @FXML
  private TableColumn<Dia, Double> tblBalance;

  @FXML
  private TableColumn<Dia, Integer> tblCantidad;

  @FXML
  private TableColumn<Dia, Integer> tblDia;

  @FXML
  private TableColumn<Dia, String> tblMovimiento;

  @FXML
  private TableColumn<Dia, Double> tblPrecio;

  // Estructura de datos
  Cola<Dia> diaLista = new Cola<Dia>();
  Cola<Double> balanceAcciones = new Cola<Double>();

  // Contadores y auxiliares
  private String movimiento;
  private Integer cantidad;
  private Double precio;
  private Integer dia;
  private Integer acciones = 0;
  private Double balance = 0.0;

  @FXML
  void cargar(ActionEvent event) {
    cargarEnLista();
    try {
      cargarEnTabla();
    } catch (Exception e) {
      System.out.println("No se pudo cargar en tabal");
      e.printStackTrace();
    }
  }

  // Cargar el csv en la lista
  private void cargarEnLista() {
    FileChooser file = new FileChooser();
    file.setTitle("Open File");
    File csv = file.showOpenDialog(null);
    try {
      Scanner csvReader = new Scanner(csv);
      while (csvReader.hasNextLine()) {
        // Linea con el día
        String diaCsv = csvReader.nextLine();
        // Arreglo con cada cantidad del día
        String diaCsvSeparado[] = diaCsv.split(";");
        // Asignacion a auxiliares para mayor comprención
        movimiento = diaCsvSeparado[0];
        cantidad = Integer.parseInt(diaCsvSeparado[1]);
        precio = Double.parseDouble(diaCsvSeparado[2]);
        dia = Integer.parseInt(diaCsvSeparado[3]);

        // Se inserta el día a la lista
        if (diaLista.estaVacia()) {
          acciones += cantidad;
          for (int i = 1; i <= cantidad; i++) {
            balanceAcciones.insertar(precio);
          }
          diaLista.insertar(new Dia(movimiento, cantidad, precio, dia, acciones, null));
        } else {
          if (movimiento.equalsIgnoreCase("C")) {
            acciones += cantidad;
            for (int i = 1; i <= cantidad; i++) {
              balanceAcciones.insertar(precio);
            }
            diaLista.insertar(new Dia(movimiento, cantidad, precio, dia, acciones, null));
          } else {
            acciones -= cantidad;
            balance += (double) cantidad * precio;
            for (int i = 1; i <= cantidad; i++) {
              balance -= balanceAcciones.extraer();
            }
            diaLista.insertar(new Dia(movimiento, cantidad, precio, dia, acciones, balance));
            balance = 0.0;
          }
        }

      }
      csvReader.close();
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Error al cargar el archivo");
    }
  }

  // Carga la lista a la ui
  private void cargarEnTabla() throws Exception {
    while (!diaLista.estaVacia()) {
      tabla.getItems().add(diaLista.extraer());
    }
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    tblAcciones.setCellValueFactory(new PropertyValueFactory<>("acciones"));
    tblBalance.setCellValueFactory(new PropertyValueFactory<>("balance"));
    tblCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
    tblDia.setCellValueFactory(new PropertyValueFactory<>("dia"));
    tblMovimiento.setCellValueFactory(new PropertyValueFactory<>("movimiento"));
    tblPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
  }

}
