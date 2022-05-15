# Projeto Java
Editor gráfico vetorial desenvolvido na disciplina Linguagem de Programação 2 do curso de Ciência da computação da UERJ

## Execução
Para executar o projeto é necessário ter o JDK instalado. Tendo-o, basta executar os seguintes comandos:
```
    git clone git@github.com:MaiconRenildo/ProjetoJava.git
    cd ProjetoJava
    javac PackApp.java
    java PackApp
```

## Funcionalidades
- [x] Criação de figura
- [X] Exclusão de figura
- [x] Alteração do tamanho 
- [X] Alteração da posição no eixo XY
- [X] Alteração da posição no eixo Z
- [X] Alteração da cor de fundo
- [X] Alteração da cor da borda
- [X] Alteração da figura em foco
- [X] Esconder ou mostrar menu
- [X] Salvamento dos dados em um arquivo binário
- [ ] Digitação
- [ ] Desenho livre

### Criação de figura
Para criar uma figura basta selecionar as teclas de acordo com a tabela abaixo:
| Figura  |  Tecla  |
| :---: | :---: |
|  Quadrado |  r |
|  Círculo |  c |
|  Elipse |  e |
|  Triângulo |  t |
|  Linha |  l |

### Exclusão de figura
Para excluir uma figura basta selecioná-la e apertar a tecla "Delete"

### Alteração do tamanho
Para alterar o tamanho de uma figura basta selecioná-la e apertar as teclas "+" e "-"

### Alteração da posição
Para alterar a posição de uma figura basta arrastá-la utilizando o mouse ou utilizar as setas do teclado após tê-la selecionado 

### Alteração da posição no eixo Z
Para alterar a posição Z de uma figura basta selecioná-la e apertar a tecla "u" para subir e "i" para descer

### Alteração da cor de fundo
Para alterar a cor de fundo de uma figura basta selecioná-la e escolher a cor desejada na palheta de cores

### Alteração da cor da borda
Por padrão, ao selecionar uma figura e escolher uma das cores da palheta, a cor de fundo da figura é alterada. Para que esse processo altere a cor da borda é preciso que a tecla espaço seja clicada. Feito isso, basta selecionar a figura e escolher a cor desejada na palheta de cores. Para voltar a configuração anterior basta clicar na tecla espaço novamente

### Alteração da figura em foco
Para alterar a figura em foco basta apertar a tecla "tab"

### Esconder ou mostrar o menu
Para esconder ou mostrar o menu basta clicar na tecla "esc"

