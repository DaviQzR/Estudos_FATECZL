package Model;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.text.ParseException;

public class Funcionario {

    private long id;
    private String nome;
    private String matricula;
    private LocalDate admissao;
    private LocalDate demissao;
    private float salario;
    private LocalTime horario;

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DateTimeFormatter hora = DateTimeFormatter.ofPattern("HH:mm:ss");
    DecimalFormat formatoSalario = new DecimalFormat("#,##0.00");

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

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public LocalDate getAdmissao() {
        return admissao;
    }

    public void setAdmissao(LocalDate admissao) {
        this.admissao = admissao;
    }

    public void setAdmissao(String data) {
        LocalDate admissao = LocalDate.parse(data, dtf);
        setAdmissao(admissao);
    }

    public LocalDate getDemissao() {
        return demissao;
    }

    public void setDemissao(String data) {
        demissao = LocalDate.parse(data, dtf);
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public String getSalarioFormatado() {
        return formatoSalario.format(salario);
    }

    public void setSalarioFormatado(String salarioFormatado) throws ParseException {
        salario = formatoSalario.parse(salarioFormatado).floatValue();
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public void setHorario(String horario) throws DateTimeParseException {
        this.horario = LocalTime.parse(horario, hora);
    }

    public void exibir() {
        System.out.println("Dados do Funcionário:");
        System.out.println("ID: " + getId());
        System.out.println("Nome: " + getNome());
        System.out.println("Matrícula: " + getMatricula());
        System.out.println("Admissão: " + getAdmissao().format(dtf));
        System.out.println("Demissão: " + (getDemissao() != null ? getDemissao().format(dtf) : "Não especificada"));
        System.out.println("Salário: " + getSalarioFormatado());
        System.out.println("Horário: " + getHorario().format(hora));
    }

}
