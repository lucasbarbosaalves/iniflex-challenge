import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.util.Locale;

public class Funcionario extends Pessoa {
    private BigDecimal salario;
    private final String funcao;

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    @Override
    public String getIdentificacao() {
        return String.format("%s - %s", nome, funcao);
    }

    @Override
    public void exibirDetalhes() {
        System.out.printf("Nome: %s, Data Nascimento: %s, Salário: %s, Função: %s%n",
                nome, getDataFormatada(), getSalarioFormatado(), funcao);
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public String getSalarioFormatado() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("pt", "BR"));
        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator(',');
        DecimalFormat formatter = new DecimalFormat("#,##0.00", symbols);
        return formatter.format(salario);
    }

    public void aplicarAumento(double percentual) {
        BigDecimal fator = BigDecimal.valueOf(1 + percentual / 100);
        this.salario = this.salario.multiply(fator).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal calcularSalariosMinimos(BigDecimal salarioMinimo) {
        return salario.divide(salarioMinimo, 2, RoundingMode.HALF_UP);
    }

    public boolean temFuncao(String funcao) {
        return this.funcao.equalsIgnoreCase(funcao);
    }

    @Override
    public String toString() {
        return String.format("%s - %s (%s)", nome, funcao, getSalarioFormatado());
    }
}