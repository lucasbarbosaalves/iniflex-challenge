import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>();

        // 3.1 - Inserir todos os funcionários na mesma ordem da tabela
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

        System.out.println("=== 3.1 - TODOS OS FUNCIONÁRIOS ===");
        funcionarios.forEach(Funcionario::exibirDetalhes);

        // 3.2 - Remover o funcionário "João" da lista
        System.out.println("\n=== 3.2 - REMOVENDO JOÃO ===");
        boolean joaoRemovido = funcionarios.removeIf(f -> "João".equals(f.getNome()));
        System.out.println(joaoRemovido ? "João foi removido com sucesso!" : "João não foi encontrado!");

        // 3.3 - Imprimir todos os funcionários com todas suas informações
        System.out.println("\n=== 3.3 - FUNCIONÁRIOS APÓS REMOÇÃO ===");
        funcionarios.forEach(Funcionario::exibirDetalhes);

        // 3.4 - Aplicar 10% de aumento de salário
        System.out.println("\n=== 3.4 - APÓS AUMENTO DE 10% ===");
        funcionarios.forEach(f -> f.aplicarAumento(10.0));
        funcionarios.forEach(Funcionario::exibirDetalhes);

        // 3.5 - Agrupar funcionários por função em um MAP
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        // 3.6 - Imprimir funcionários agrupados por função
        System.out.println("\n=== 3.5/3.6 - FUNCIONÁRIOS AGRUPADOS POR FUNÇÃO ===");
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.printf("%n=== %s ===%n", funcao.toUpperCase());
            lista.forEach(Funcionario::exibirDetalhes);
        });

        // 3.8 - Imprimir funcionários que fazem aniversário no mês 10 e 12
        System.out.println("\n=== 3.8 - ANIVERSARIANTES DOS MESES 10 E 12 ===");
        funcionarios.stream()
                .filter(f -> f.fazAniversarioEm(10, 12))
                .forEach(Funcionario::exibirDetalhes);

        // 3.9 - Imprimir funcionário com maior idade
        System.out.println("\n=== 3.9 - FUNCIONÁRIO MAIS VELHO ===");
        funcionarios.stream()
                .min(Comparator.comparing(Pessoa::getDataNascimento))
                .ifPresent(f -> System.out.printf("Nome: %s, Idade: %d anos%n",
                        f.getNome(), f.getIdade()));

        // 3.10 - Imprimir lista de funcionários por ordem alfabética
        System.out.println("\n=== 3.10 - FUNCIONÁRIOS EM ORDEM ALFABÉTICA ===");
        funcionarios.stream()
                .sorted(Comparator.comparing(Pessoa::getNome))
                .forEach(Funcionario::exibirDetalhes);

        // 3.11 - Imprimir total dos salários
        System.out.println("\n=== 3.11 - TOTAL DOS SALÁRIOS ===");
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("pt", "BR"));
        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator(',');
        DecimalFormat formatter = new DecimalFormat("#,##0.00", symbols);
        System.out.println("Total dos salários: " + formatter.format(totalSalarios));

        // 3.12 - Imprimir quantos salários mínimos ganha cada funcionário
        System.out.println("\n=== 3.12 - SALÁRIOS EM MÚLTIPLOS DO SALÁRIO MÍNIMO ===");
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        funcionarios.forEach(f -> {
            BigDecimal multiplos = f.calcularSalariosMinimos(salarioMinimo);
            System.out.printf("%s ganha %.2f salários mínimos%n",
                    f.getNome(), multiplos);
        });
    }
}