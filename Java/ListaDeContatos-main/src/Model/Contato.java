package Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Contato {

  private long id;
  private String nome;
  private String email;
  private long telefone;
  private LocalDate datinha;

  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public long getTelefone() {
    return telefone;
  }

  public void setTelefone(long telefone) {
    this.telefone = telefone;
  }

  public LocalDate getDatinha() {
    return datinha;
  }

  public void setDate(LocalDate datinha) {
    this.datinha = datinha;
  }

  public void setDatinha(String data) {
    LocalDate datinha = LocalDate.parse(data, dtf);
    setDate(datinha);
  }

  public String toString() {
    return "Nome: " + this.nome + " E-mail: " + this.email + " Telefone: " +  this.telefone + " Data: " + this.datinha;
  }

}