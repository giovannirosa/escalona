# Sistemas de Banco de Dados Trabalho

Trabalho da matéria de Sistemas de Banco de Dados do segundo semestre de 2018, Universidade Federal do Paraná - UFPR.

## Início

Existe um makefile neste diretório com os seguintes comandos disponíveis:

* make: clean + compila os arquivos .java e adiciona ao diretório bin, também gera javadoc
* make clean: limpa diretório bin e script escalona

### Pré-requisitos

É necessario ter o Java Development Kit 8 ou superior instalado na máquina, o gmake e shell script.

### Javadoc

Com o comando make são gerados arquivos do javadoc no diretório bin. Abra o index.html para navegar entre as classes.

### IntelliJ

Esse trabalho foi desenvolvido utilizando a IDE IntelliJ(https://www.jetbrains.com/idea/), é possível importar esse projeto para visualizá-lo.

## Testando

Após compilar com o comando make, é possível utilizar o script escalona para testar o programa.

### Exemplo de Teste

Em qualquer arquivo insira a lista de transações.
Exemplo arquivo teste.in:

```
1 1 R X
2 2 R X
3 2 W X
4 1 W X
5 2 C -
6 1 C -
7 3 R X
8 3 R Y
9 4 R X
10 3 W Y
11 4 C -
12 3 C -
13 1 R X
14 3 R X
15 1 W X
16 2 R X
17 3 W X
18 1 C -
19 2 C -
20 3 C -
```

Agora, utilize o script gerado para testar o programa e obter a saída:

```
./escalona < teste.in > teste.out
```

Exemplo arquivo teste.out:

```
1 1,2 NS NV
2 3,4 SS SV
3 1,2,3 NS NV
```

### Como testar a saída

Para facilitar a correção é recomendado a utilização do comando diff, comparando com uma solução sabidamente correta:

```
diff teste.sol teste.out
```

## Bibliotecas Utilizadas

* [jdk8](https://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html) - Java Development Kit 8
* [gmake](https://www.gnu.org/software/make/) - GNU Make

## Autor

* **Giovanni Rosa** - [giovannirosa](https://github.com/giovannirosa)

## Licença

Código aberto, qualquer um pode usar para qualquer propósito.

## Reconhecimentos

* O teste de equivalência por visão foi desafiador.

## Bugs

Nenhum bug aparente com os testes realizados.