# Etapa 1

- requisitos
- projeto
- interface
- obrigatoriamente dividido em 4 partes principais (1.2, 1.3, 1.4 e 1.5)

&nbsp;

## 1.2) Mudanças em Relação à Etapa Anterior

- quais mudanças ocorreram desde a etapa 0 (todas as mudanças devem ser registradas)
- se nao houver mudanças, registrar por escrito que nao houve mudanças desde a etapa 0

&nbsp;

## 1.3) Requisitos

Esta parte do projeto presenta apenas uma introducao informal sobre o conceito

- descriçao do que o sistema deve fazer
- serviços que o sistema oferece
- restrições ao funcionamento do sistema

### Classificacao de Requisitos

- funcional: relativo a funcionalidade do sistema
- nao funcional: relativo a caracteristica da performance, usabilidade, cronograma, orçamento, limitacoes tecnologicas, etc.

### O Que Deve Ser Feito?

Deve ser elaborado um conjunto de requisitos que o sistema deve possuir.

- cada requisito deve ser apresentado em uma unica frase
- deve haver duas listas conforme o tipo (funcional e nao-funcional)
- requisitos funcionais devem seguir o codigo 'RF-x' | x E {1,2,...,N}
- requisitos nao-funcionais devem seguir o codigo 'RNF-x' | x E {1,2,...,N}
- devem haver no minimo 5 requisitos funcionais
- devem haver no minimo 5 requisitos nao-funcionais
- seja 1 <= x <= N, onde 1 tem maior prioridade ate N com menor prioridade, em termos de requisitos funcionais e nao-funcionais

Obs: deve ser escrito um texto sucinto indicando o motivo de ter excolhido a ordem de prioridade da lista dos requisitos funcionais e nao-funcionais.

&nbsp;

## 1.4) Projeto

Deve conter projeto da parte funcional, apartir da definicao de um diagrama de classes, seguindo a perspectiva de implementacao do sistema mais voltada ao desenvolvimento do software.

Deve conter:

- modificadores de acesso
- tipos dos atributos
- nomes
- tipos dos parametros dos metodos
- tipos de retorno dos metodos

### Do Projeto

- deve ser definido no minimo 8 classes
- classes devem possuir atributos e metodos
- classes devem possuir contrutores, getters e setters
- o projeto deve conter do minimo 1 constante
- o projeto deve conter no minimo um attributo ou metodo de classe static)

### Do Diagrama de Classes

- devem ser indicado todos os relacionamentos
- os relacionamentos devem conter/indicar pelo menos o seu tipo
- os tipos de relacionamentos sao: dependencia, generalizacao/especializacao, associacao, associacao-agregacao e associacao-composicao
- os relacionamentos devem ser indicado conforme notacao grafica
- e obrigatorio o uso de no minimo um relacionamento de cada tipo, exceto associacao que pode ser um dos 3 existentes (associacao, assossiacao-agregacao e associacao-composicao)
- no relacionamento de associacao, devem ser indicados nome da assossiacao, direcao de leitura e suas cardinalidades

### Do Por quê

- deve ser explicado ou apresentado sobre os motivos que levaram a criacao do projeto da forma concebida
- deve ser apresentado uma explicacao geral sobre os diagramas de classe (nao devem ser detalhes muito especificos, mas mais gerais)

&nbsp;

## 1.5) Interface

Deve ser projetado a UI (User Interface ou Interface de Usuario) esperada para o sistema

- deve conter prototipos de imagens de cada uma das telas do sistema
- cada tela deve conter todas as tarefas/funcionalidades previstas no sitema
- cada pagina deve conter uma tela ou unidade de apresentacao da funcionalidade (imagem)
- estas telas ou imagens devem ser seguidas de explicacoes e argumentacoes que justifiquem o layout ou o comportamento da UI
- a qualidade dos desenhos/rascunhos da UI nao e importante
