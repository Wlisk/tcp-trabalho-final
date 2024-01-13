# TODO

Tarefas concluídas e ainda a serem feitas no projeto.

## Tarefas

**Prioridade**: MUITO ALTA

**Descricao**: representa a logica de renderizacao na classe Scene

- [x] criar cenas do jogo
  - [x] MAIN_MENU (Francisco)
  - [x] SELECTING_CLASS (Francisco)
  - [x] BATTLE_START (Francisco)
  - [x] TURN_START (Richard)
  - [x] TURN_PLAYER_CHOOSE
  - [x] TURN_PLAYER_CHOSEN
  - [x] TURN_ENEMY_CHOOSE
  - [x] TURN_ENEMY_CHOSEN
  - [x] TURN_END
  - [x] BATTLE_END

---

**Prioridade**: MUITO ALTA

**Descricao**: representa a logica do jogo na classe Game

- [x] implementar estado do jogo
  - [x] MAIN_MENU (Francisco)
  - [x] SELECTING_CLASS (Francisco)
  - [x] BATTLE_START
    - [x] apresentar mensagem de inicio de batalha (Francisco)
    - [x] deve resetar o player para cada nova batalha (Richard)
    - [x] deve iniciar um novo boss (Richard)
  - [x] TURN_START (Richard)
  - [x] TURN_PLAYER_CHOOSE
  - [x] TURN_PLAYER_CHOSEN
  - [x] TURN_ENEMY_CHOOSE
  - [x] TURN_ENEMY_CHOSEN
  - [x] TURN_END
  - [x] BATTLE_END
    - [x] verificar se existem mais bosses
      - [x] se sim, continua para BATTLE_START
      - [x] se não, apresenta mensagem de sucesso e finaliza jogo
  - [x] GAME_END (Francisco)

---

**Prioridade**: MUITO ALTA

**Descricao**: logica da batalha e execucao de ataques/defesas

- [x] implementar batalhas Player vs Boss
  - [x] implementar jogada de turnos (Richard)
  - [x] Player e Boss podem causar dano um ao outro
  - [x] Player morre, fim de jogo
  - [x] Boss morre, proxima cena/Boss
  - [x] implementar logica de defesa nas batalhas
  - [ ] implementar uso de items e gerenciamento de inventário

---

**Prioridade**: ALTA

**Descricao**: mostra dados (estatisticas) atuais do player

(Francisco)

- [x] implementar bloco grafico de visualizacao de estatisticas do jogador
  - [x] desenhar em canto especifico das laterais
  - [x] apresenta dados corrente do Player

---

**Prioridade**: ALTA

**Descricao**: barras de energia com seus valores currentes e maximos

(Francisco)

- [x] implementar barras
  - [x] barra de vida
    - [x] do Player
    - [x] do Boss
  - [x] barra de mana
    - [x] do Player
    - [x] do Boss

---

**Prioridade**: ALTA

**Descricao**: mostrar os items equipados e armazenados no inventory, alem de logica de uso

(Francisco)

- [x] parte grafica da class Inventory
  - [x] desenhar informações dos items
  - [x] desenhar equipamentos equipados
  - [x] desenhar itens no inventario
  
---

**Prioridade**: ALTA

**Descricao**: criacao de testes e verificacao de erros, melhor estrategia seria TDD

- [ ] criar testes para todas as classes
  - [ ] testar classes em entities/
    - [ ] em entities/player
    - [ ] em entities/boss
  - [ ] testar classes em game/
  - [ ] testar classes em items/
    - [ ] em items/armor
    - [ ] em items/consumable
    - [ ] em items/weapon
  - [ ] testar classes em scene/
    - [ ] em scene/button
    - [ ] em scene/bars
    - [ ] em scene/sprite
    - [ ] em scene/textbox
  - [ ] testar classes em utils/

---

**Prioridade**: ALTA

**Descricao**: criacao de itens, colocar seus valores, storytelling e descricoes

- [ ] criar e implementar detalhes para que itens tenham dados 'corretos'
  - [ ] detalhes de bosses
  - [ ] detalhes de armas
  - [ ] detalhes de armaduras
  - [ ] detalhes de consumiveis

---

**Prioridade**: ALTA

**Descricao**: maquina de estados do boss (IA)

- [x] criar e implementar logica de estados (FSM) para o Boss
  - [x] estados podem trocar a cada turno
  - [x] BASE (Richard)
  - [x] BERSERK
    - [x] aumenta dano do Boss
    - [x] após dois turnos estado vai para WEAK
  - [x] WEAK
    - [x] diminui defesa em 50%
    - [x] diminui dano em 50%
  - criar/implementar verificadores de estados para checagem de atingimento de estados
    - [x] reachedStateBerserk() (Richard)
    - [x] reachedStateDefend() (Richard)
    - [ ] reachedStateAttackSuper()
  - [x] criar/implementar setadores para os estados atingidos
    - [x] setBase()
    - [x] setBerserk()
    - [x] seteWeak()

---

**Prioridade**: MEDIA

**Descricao**: sons de butoes, ataques, mouse over, etc.

- [ ] implementar sons de jogo
  - [ ] sons ao selecionar, passar mouse por botoes do jogo
  - [ ] musica de fundo menu inicial e selecao
  - [ ] musica de fundo para os bosses

---

**Prioridade**: MEDIA

**Descricao**: metodos como draw(), isMouseOver(), isKeyPressed, isMousePressed() para padronizacao de metodos nas classes

- [ ] setar interfaces e implementar seus devidos códigos
  - [] para class Button
    - [x] IClickable (Francisco)
    - [x] IDrawable (Francisco)
    - [ ] IKeyboardable
    - [x] IMouseOverable (Francisco)
  - [ ] para class Player
    - [ ] IDrawable
  - [ ] para class Boss
    - [ ] IDrawable
  - [ ] para class Inventory
    - [ ] IClickable: usar/equipar/consumir item
    - [ ] IDrawable
    - [ ] IKeyboardable: selecionar e usar/quipar/consumir item
    - [ ] IMouseOverable: mostrar descricao e estatistica de item
  - [ ] para class Super
    - [ ] IDrawable
    - [ ] ISpritable
  - [ ] para class Item
    - [ ] IClickable: ser equipado/usado/consumido
    - [ ] IDrawable
    - [ ] IMouseOverable: mostrar descricao e estatistica

---

**Prioridade**: MEDIA

**Descricao**: forma do jogador obter os itens apos a derrota de um boss

- [ ] criar logica para drop de items
  - [ ] criar novos items
  - [ ] selecionar de uma lista de items um item randomicamente
  - [ ] adicionar item ao Inventory do Player
    - [ ] mostrar novo item no Inventory

---

**Prioridade**: MEDIA

**Descricao**: ataques especiais do boss e player, alem da logica de ataques especiais

- [ ] implementar classe Super
  - [ ] implementar super para o Player
  - [ ] implementar super para o Boss
  - [ ] implementar caixa de selecao de supers para o Player
  - [ ] implementar logica de attackSuper() da class Entity

---

**Prioridade**: MEDIA

**Descricao**: posicao (x, y) para poder decidir onde renderizar os componentes do jogo na janela

- [ ] implementar posicao (Vector2D) em todos os items que aparecem graficamente
  - [x] classe Entity (Player e Boss) (Richard)
  - [ ] classe Item
  - [ ] ...

---

**Prioridade**: MEDIA

**Descricao**: para que valores arbtrarios nao possam ser colocados nas variaveis atraves dos setters

- [ ] conferir e implementar codigo de setters
  - [ ] setters definidos devem ser protegidos com 'guard statements'
  - [ ] verificar e proteger setters em class Entity

---

**Prioridade**: MEDIA

**Descricao**: certos acontecimentos devem ocorrer a cada turno, um deles é a regeneracao de mana, assim entities podem recuperar mana para poder continuar usando ataques especiais depois de um tempo

- [ ] implementar logica de turnos
  - [ ] logica de regeneracao de mana ao longo dos turnos
  
---

**Prioridade**: BAIXA

**Descricao**: selecao e acoes

- [ ] implementar acoes usando teclado
  - [ ] selecionar botoes menu inicial com teclado

---

**Prioridade**: BAIXA

**Descricao**: forma do jogador poder setar seu nome no jogo

- [ ] caixa de texto para jogador colocar/setar o nome

---

**Prioridade**: BAIXA

**Descricao**: pontuacao do jogador

- [ ] criar logica do score do jogador

---

**Prioridade**: BAIXA

**Descricao**: mostrar dados de cada classe, para que jogador possa escolher melhor sua classe

- [ ] apresentar estatisticas basicas das classes no menu de selecao de classes

---

**Prioridade**: BAIXA

**Descricao**: animacao e afins

- [ ] implementar class Sprite
  - [ ] pegar imagem e subdividir em partes
  - [ ] implementar metodo nextSprite()
  - [ ] menu de selecao com sprites/imagens animadas (se movendo)
  - [ ] criar interface ISpritable
  - [ ] criar propriedade sprites em cada classe spritable
    - [ ] class Player
    - [ ] class Boss

---

**Prioridade**: BAIXA

**Descricao**: documentacao do projeto, tanto para uso pessoal quanto por outrem

- [ ] escrever comentarios/anotacoes jsdoc para documentar projeto
  - [ ]  documentar classes em entities/
    - [ ] documentar classes em entities/boss
    - [ ] documentar classes em entities/player
  - [ ] documentar classes em exceptions/
  - [ ] documentar classes em game/
  - [ ] documentar classes em interfaces/
  - [ ] documentar classes em items/
    - [ ] documentar classes em items/armor
    - [ ] documentar classes em items/consumable
    - [ ] documentar classes em items/weapon
  - [ ] documentar classes em scene/
    - [ ] documentar classes em scene/button
  - [x] documentar classes em utils/ (Richard)

---

**Prioridade**: MUITO BAIXA

**Descricao**: dinamismo para butoes executarem acoes ao serem clicados e ou o mouse passar por cima

- [ ] implementar callbacks para butoes e outros clicaveis
  - [ ] quando botao clicado aciona um callback (funcao)
  - [ ] adicionar callback em Iclickable

---

**Prioridade**: MUITO BAIXA

**Descricao**: aumentar uma propriedade a cada turno

- [ ] implementar logica de itens que geram buff/debuff overtime (por X turnos)

---

**Prioridade**: MUITO BAIXA

**Descricao**: mostra relacao entre o numero (indice) do boss corrente e o numero maximo de bosses

- [ ] apresentar/desenhar relacao entre quantidade de Boss e o indice do Boss corrente

---

**Prioridade**: MUITO BAIXA

**Descricao**: VFX

- [ ] implementar efeitos especiais
  - [ ] efeitos especiais ao atacar
  - [ ] efeitos especiais ao defender
  - [ ] efeitos especiais ao interagir com objetos/itens
