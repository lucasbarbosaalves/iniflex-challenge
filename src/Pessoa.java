import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public abstract class Pessoa {
    protected final String nome;
    protected final LocalDate dataNascimento;

    protected Pessoa(String nome, LocalDate dataNascimento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }


    public abstract String getIdentificacao();

    public abstract void exibirDetalhes();


    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public String getDataFormatada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dataNascimento.format(formatter);
    }

    public int getIdade() {
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }

    public int getMesAniversario() {
        return dataNascimento.getMonthValue();
    }


    public boolean fazAniversarioEm(int... meses) {
        int mesAniversario = getMesAniversario();
        for (int mes : meses) {
            if (mesAniversario == mes) {
                return true;
            }
        }
        return false;
    }

    public boolean ehMaisVelhoQue(Pessoa outra) {
        return this.dataNascimento.isBefore(outra.dataNascimento);
    }
}