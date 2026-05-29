# 🧩 Sudoku UI Game

Uma aplicação desktop do clássico jogo **Sudoku**, desenvolvida em **Java** utilizando a biblioteca gráfica **Swing**. O projeto foi estruturado seguindo boas práticas de arquitetura, separando as responsabilidades de interface (UI), regras de negócio (Service) e dados (Model).

---

## 🚀 Funcionalidades

* **Tabuleiro Interativo:** Interface intuitiva dividida perfeitamente em setores $3 \times 3$.
* **Validação de Inputs:** Restrição nativa que permite apenas a digitação de números válidos entre 1 e 9.
* **Status do Jogo:** Acompanhamento do progresso do jogo (se está em andamento, concluído ou com erros).
* **Ações Rápidas:** Botões dedicados para reiniciar a partida, verificar o status atual e finalizar o jogo.

---

## 🎮 Exemplo de Inicialização (Parâmetros do Tabuleiro)

Para iniciar o jogo com um tabuleiro pré-configurado, a aplicação aceita uma sequência de parâmetros no formato `linha,coluna;numero,ehFixo` separados por espaços, onde:
* **linha e coluna:** Posições indexadas de 0 a 8.
* **numero:** O valor da célula (1 a 9).
* **ehFixo (true/false):** Define se o número faz parte do desafio inicial (bloqueado para edição) ou se é modificável.

### Matriz de Exemplo para Execução

Você pode copiar a linha abaixo para passar como argumento de inicialização no seu terminal ou na configuração de *Run/Debug Arguments* da sua IDE:

```bash
0,0;4,false 1,0;7,false 2,0;9,true 3,0;5,false 4,0;8,true 5,0;6,true 6,0;2,true 7,0;3,false 8,0;1,false 0,1;1,false 1,1;3,true 2,1;5,false 3,1;4,false 4,1;7,true 5,1;2,false 6,1;8,false 7,1;9,true 8,1;6,true 0,2;2,false 1,2;6,true 2,2;8,false 3,2;9,false 4,2;1,true 5,2;3,false 6,2;7,false 7,2;4,false 8,2;5,true 0,3;5,true 1,3;1,false 2,3;3,true 3,3;7,false 4,3;6,false 5,3;4,false 6,3;9,false 7,3;8,true 8,3;2,false 0,4;8,false 1,4;9,true 2,4;7,false 3,4;1,true 4,4;2,true 5,4;5,true 6,4;3,false 7,4;6,true 8,4;4,false 0,5;6,false 1,5;4,true 2,5;2,false 3,5;3,false 4,5;9,false 5,5;8,false 6,5;1,true 7,5;5,false 8,5;7,true 0,6;7,true 1,6;5,false 2,6;4,false 3,6;2,false 4,6;3,true 5,6;9,false 6,6;6,false 7,6;1,true 8,6;8,false 0,7;9,true 1,7;8,true 2,7;1,false 3,7;6,false 4,7;4,true 5,7;7,false 6,7;5,false 7,7;2,true 8,7;3,false 0,8;3,false 1,8;2,false 2,8;6,true 3,8;8,true 4,8;5,true 5,8;1,false 6,8;4,true 7,8;7,false 8,8;9,false
```

---

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java
* **Interface Gráfica:** Java Swing / AWT
* **IDE Recomendada:** IntelliJ IDEA (Arquivos de configuração inclusos)

---

## 📂 Estrutura do Projeto

O projeto está organizado sob o pacote base `br.com.dio`:

```text
src/br/com/dio/
├── Main.java                  # Ponto de entrada padrão da aplicação
├── UIMain.java                # Inicializador principal da interface gráfica
│
├── model/                     # Modelagem dos dados do jogo
│   ├── Board.java             # Representação matemática e lógica do tabuleiro
│   ├── Space.java             # Representação de cada célula individual
│   └── GameStatusEnum.java    # Estados possíveis do jogo (Pendente, Ganhou, etc.)
│
├── service/                   # Regras de negócio e comunicação
│   ├── BoardService.java      # Validações de linhas, colunas e quadrantes
│   ├── NotifierService.java   # Gerenciamento de eventos do sistema
│   ├── EventListener.java     # Interface para escutar mudanças no tabuleiro
│   └── EventEnum.java         # Tipos de eventos suportados
│
├── ui/custom/                 # Componentes customizados da interface gráfica
│   ├── button/                # Botões de controle estilizados
│   │   ├── CheckGameStatusButton.java
│   │   ├── FinishGameButton.java
│   │   └── ResetButton.java
│   ├── frame/                 # Telas e janelas principais
│   │   └── MainFrame.java
│   ├── input/                 # Inputs de texto especializados
│   │   ├── NumberText.java
│   │   └── NumberTextLimit.java
│   ├── panel/                 # Painéis organizadores de layout
│   │   ├── MainPanel.java
│   │   └── SudokuSector.java  # Grade individual de quadrantes 3x3
│   └── screen/                # Telas agregadoras
│       └── MainScreen.java
│
└── util/
    └── BoardTemplate.java     # Matrizes e gabaritos pré-definidos para jogos
```

🔧 Como Executar o Projeto
Pré-requisitos
Ter o Java JDK (versão 11 ou superior recomendada) instalado em sua máquina.

Passo a Passo
Clonar o repositório:

git clone [https://github.com/trainningjava/sudoku.git](https://github.com/trainningjava/sudoku.git)

