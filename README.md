Vira7 (Flip7) - Jogo de Cartas

Este é um projeto académico desenvolvido em Java utilizando **JavaFX** para a disciplina de Programação Orientada a Objetos. O jogo é baseado no popular jogo de cartas Flip7, onde o objetivo é acumular o máximo de pontos sem repetir números na mesa.

## Funcionalidades

- **Multiplayer Local:** Suporte para 2 a 4 jogadores.
- **Mecânica de Jogo Fiel:**
    - Cartas de números (1 a 12).
    - **Cartas Especiais:**
        - `FREEZE`: Força o jogador a parar e recolher os pontos atuais.
        - `x2`: Duplica os pontos da rodada.
        - `+2, +4, +6, ...`: Bónus de pontuação.
- **Ambiente Imersivo:**
    - Música de fundo contínua (`chill.mp3`).
    - Menu de definições com controlo de volume e som em tempo real.
- **Interface Gráfica:** Desenvolvida inteiramente em JavaFX com design moderno e responsivo.
- **Testes Unitários:** Suite de testes para garantir a integridade da lógica do baralho e do jogo.

## Tecnologias Utilizadas

- **Java 21**
- **Maven** (Gestão de dependências)
- **JavaFX 21** (Interface Gráfica e Media)
- **JUnit 5** (Testes Unitários)

## Como Correr o Projeto

### Pré-requisitos
- JDK 21 ou superior instalado.
- Maven configurado.

### Execução via Terminal
1. Navegue até à pasta raiz do projeto.
2. Execute o comando:
   ```bash
   mvn javafx:run
   ```