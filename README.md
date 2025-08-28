# Projedata - Iniflex Challenge

##  Visão Geral
Desafio de desenvolvimento em Java focado em modelagem orientada a objetos, encapsulamento de lógica de negócio e manipulação de coleções utilizando streams.

### Estrutura das Classes

####  **Pessoa** (Classe Abstrata)
**Responsabilidade**: Definir o contrato base e lógicas essenciais de pessoas

**Lógicas Essenciais do Modelo Interno:**
- `getDataFormatada()`: Formatação dd/mm/aaaa
- `getIdade()`: Cálculo baseado na data de nascimento
- `getMesAniversario()`: Extração do mês para verificações
- `fazAniversarioEm(int... meses)`: Verificação de aniversário em meses específicos
- `ehMaisVelhoQue(PessoaAbstrata outra)`: Comparação de idades

**Template Methods (Contratos Abstratos):**
- `getIdentificacao()`: Cada subclasse define sua identificação
- `exibirDetalhes()`: Cada subclasse define como se apresentar

####  **Funcionario** (Herda de Pessoa)
**Responsabilidade**: Modelar um funcionário e suas operações intrínsecas

**Lógicas Essenciais do Modelo:**
- `getSalarioFormatado()`: Formatação monetária brasileira (ponto/vírgula)
- `aplicarAumento(double percentual)`: Modificação salarial com precisão
- `calcularSalariosMinimos(BigDecimal salarioMinimo)`: Cálculo proporcional
- `temFuncao(String funcao)`: Verificação de função (case-insensitive)

**Implementações dos Template Methods:**
- `getIdentificacao()`: Retorna "Nome - Função"
- `exibirDetalhes()`: Apresentação completa formatada

#### **Principal** (Camada de Aplicação)
**Responsabilidade**: Executar todos os requisitos do desafio

**Características:**
- População direta dos funcionários conforme tabela
- Execução sequencial de todos os requisitos (3.1 a 3.12)
- Uso das lógicas do domínio para operações específicas
- Coordenação de operações em listas (streams, collectors)

##  Distribuição de Responsabilidades

### ✅ **O que FICA no Modelo (Funcionario/PessoaAbstrata)**
- **Formatação de dados próprios**: `getSalarioFormatado()`, `getDataFormatada()`
- **Cálculos baseados em atributos**: `getIdade()`, `calcularSalariosMinimos()`
- **Modificações de estado**: `aplicarAumento()`
- **Validações internas**: `fazAniversarioEm()`, `temFuncao()`
- **Apresentação individual**: `exibirDetalhes()`

### ✅ **O que FICA na Aplicação (Principal)**
- **População inicial**: Criação da lista de funcionários
- **Operações em coleções**: Streams, filters, sorting, grouping
- **Coordenação sequencial**: Execução dos requisitos na ordem
- **Formatação de totais**: Somatórias e apresentação de resultados consolidados

## 🔧 Funcionalidades Implementadas

| Requisito | Implementação | Onde a Lógica Reside |
|-----------|---------------|----------------------|
| 3.1 | População da lista | `Principal` - Instanciação direta |
| 3.2 | Remoção do João | `Principal` - `removeIf` com lambda |
| 3.3 | Formatação de exibição | `Funcionario.exibirDetalhes()` + formatação interna |
| 3.4 | Aumento de 10% | `Funcionario.aplicarAumento(10.0)` |
| 3.5 | Agrupamento MAP | `Principal` - `Collectors.groupingBy()` |
| 3.6 | Exibição por função | `Principal` - Iteração + `Funcionario.exibirDetalhes()` |
| 3.8 | Aniversariantes | `PessoaAbstrata.fazAniversarioEm(10, 12)` |
| 3.9 | Mais velho | `PessoaAbstrata.getDataNascimento()` + `getIdade()` |
| 3.10 | Ordem alfabética | `Principal` - `sorted()` + `PessoaAbstrata.getNome()` |
| 3.11 | Total salários | `Principal` - Stream reduce + formatação |
| 3.12 | Salários mínimos | `Funcionario.calcularSalariosMinimos()` |



## 🚀 Como Executar

```bash
javac *.java
java Principal
```
