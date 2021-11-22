package fes.aragon.controller;

public class Dia {
  private String movimiento;
  private Integer cantidad;
  private Double precio;
  private Integer dia;
  private Integer acciones;
  private Double balance;

  public Dia(String movimiento, Integer cantidad, Double precio, Integer dia, Integer acciones, Double balance) {
    this.movimiento = movimiento;
    this.cantidad = cantidad;
    this.precio = precio;
    this.dia = dia;
    this.acciones = acciones;
    this.balance = balance;
  }

  public String getMovimiento() {
    return movimiento;
  }

  public void setMovimiento(String movimiento) {
    this.movimiento = movimiento;
  }

  public Integer getCantidad() {
    return cantidad;
  }

  public void setCantidad(Integer cantidad) {
    this.cantidad = cantidad;
  }

  public Double getPrecio() {
    return precio;
  }

  public void setPrecio(Double precio) {
    this.precio = precio;
  }

  public Integer getDia() {
    return dia;
  }

  public void setDia(Integer dia) {
    this.dia = dia;
  }

  public Integer getAcciones() {
    return acciones;
  }

  public void setAcciones(Integer acciones) {
    this.acciones = acciones;
  }

  public Double getBalance() {
    return balance;
  }

  public void setBalance(Double balance) {
    this.balance = balance;
  }

}
