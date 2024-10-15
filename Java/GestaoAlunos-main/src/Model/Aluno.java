package Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Aluno {

  private String nome;
  private long id;
  private String ra;
  private LocalDate nascimento;

  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getRa() {
    return ra;
  }

  public void setRa(String ra) {
    this.ra = ra;
  }

  public LocalDate getNascimento() {
    return nascimento;
  }

  public void setNascimento(LocalDate nascimento) {
    if (nascimento.isAfter(LocalDate.of(1920, 1, 1))) {
      this.nascimento = nascimento;
    }else{
      System.out.println("Data de Nascimento inválida ");
    }
  }

  public void setNascimento(String data) {
    LocalDate nascimento = LocalDate.parse(data, dtf);
    setNascimento(nascimento);
  }

  public String toString() {
    return this.ra + " - " + this.nome;
  }

}
