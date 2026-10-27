# Refatoração de um sistema de notas

## Integrante
- Wellington Correa Monteiro

## Como compilar e executar

Requisito: JDK 11 ou superior instalado.

```bash
mkdir out
javac -d out src/*.java
java -cp out Main
```

## Estrutura

| Classe | Responsabilidade |
|---|---|
| `Notas` | Validar, armazenar e calcular a média das notas |
| `RepositorioNotas` | Interface (abstração) de persistência |
| `RepositorioMySQL` | Implementação simulada para MySQL |
| `RepositorioPostgreSQL` | Segunda implementação simulada, usada na troca de banco |
| `ServicoEmail` | Envio de e-mails (simulado) |
| `ServicoNotas` | Serviço que coordena as operações |
| `Main` | Demonstração |

## Como cada problema foi resolvido

### 1. Exposição dos dados
A lista `notas` agora é `private` na classe `Notas`. A única forma de incluir notas é o método
`Notas.adicionar(double)`, que aceita apenas valores finitos entre 0 e 10 e lança
`IllegalArgumentException` caso contrário. O método `Notas.obterNotas()` devolve
`Collections.unmodifiableList(...)`, então quem consulta as notas não consegue alterar a lista interna
(tentar fazê-lo lança `UnsupportedOperationException`). O método `calcularMedia()` preserva a média
aritmética e retorna 0 quando não há notas.

### 2. Acúmulo de responsabilidades
A classe original foi dividida em:
- `Notas`: regras de notas;
- `RepositorioNotas` / `RepositorioMySQL`: persistência;
- `ServicoEmail`: envio de e-mails;
- `ServicoNotas`: apenas coordena as operações, delegando cada tarefa para a classe adequada.

### 3. Dependência do MySQL
Foi criada a interface `RepositorioNotas`, com o método `salvar(String, List<Double>)`.
`RepositorioMySQL` é uma das implementações. `ServicoNotas` recebe um `RepositorioNotas`
pelo construtor e nunca menciona MySQL.

## Relação com os princípios

- **Ocultamento de informação:** a lista de notas é privada e só pode ser acessada via `adicionar`
  (com validação) e `obterNotas` (somente leitura). Os detalhes internos podem mudar sem afetar os clientes.
- **Responsabilidade única:** cada classe tem um único motivo para mudar (regras de notas, banco, e-mail ou coordenação).
- **Inversão de dependências:** `ServicoNotas` (alto nível) depende da abstração `RepositorioNotas`,
  e não da implementação concreta `RepositorioMySQL` (baixo nível). A implementação é injetada pelo construtor.

## Como substituir o MySQL por outra implementação

1. Criar uma nova classe que implemente `RepositorioNotas` (ex.: `RepositorioPostgreSQL`).
2. Passá-la ao construtor: `new ServicoNotas(new RepositorioPostgreSQL(), email)`.

Nenhuma linha de `ServicoNotas` precisa ser alterada. Isso é demonstrado no passo 4 do `Main`.
