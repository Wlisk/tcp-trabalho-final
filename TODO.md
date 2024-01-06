# TODO

Tarefas concluídas e ainda a serem feitas no projeto.

## Tarefas

**Prioridade**: MUITO ALTA

**Descricao**: representa a logica de renderizacao na classe Scene

- [ ] criar cenas do jogo
  - [x] MAIN_MENU (Francisco)
  - [x] SELECTING_CLASS (Francisco)
  - [x] BATTLE_START (Francisco)
  - [ ] TURN_START (Richard)
  - [ ] TURN_PLAYER_CHOOSE
  - [ ] TURN_PLAYER_CHOSEN
  - [ ] TURN_ENEMY_CHOOSE
  - [ ] TURN_ENEMY_CHOSEN
  - [ ] TURN_END
  - [ ] BATTLE_END

---

**Prioridade**: MUITO ALTA

**Descricao**: representa a logica do jogo na classe Game

- [ ] implementar estado do jogo
  - [x] MAIN_MENU (Francisco)
  - [x] SELECTING_CLASS (Francisco)
  - [ ] BATTLE_START 
    - [x] apresentar mensagem de inicio de batalha (Francisco)
    - [ ] deve resetar o player para cada nova batalha (Richard)
    - [ ] deve iniciar um novo boss (Richard)
  - [ ] TURN_START (Richard)
  - [ ] TURN_PLAYER_CHOOSE
  - [ ] TURN_PLAYER_CHOSEN
  - [ ] TURN_ENEMY_CHOOSE
  - [ ] TURN_ENEMY_CHOSEN
  - [ ] TURN_END
  - [ ] BATTLE_END
    - [ ] verificar se existem mais bosses
      - [ ] se sim, continua para BATTLE_START
      - [ ] se não, apresenta mensagem de sucesso e finaliza jogo
  - [x] GAME_END (Francisco)

---

**Prioridade**: MUITO ALTA

**Descricao**: logica da batalha e execucao de ataques/defesas

- [ ] implementar batalhas Player vs Boss
  - [ ] implementar jogada de turnos (Richard)
    - [ ] quando Player ataca Boss nao pode atacar
    - [ ] quando Player ataca Boss talvez possa se defender
    - [ ] quando Boss ataca Player nao pode atacar
    - [ ] quando Boss ataca Player talvez possa se defender
  - [ ] Player e Boss podem causar dano um ao outro
  - [ ] Player morre, fim de jogo
  - [ ] Boss morre, proxima cena/Boss
  - [ ] implementar logica de defesa nas batalhas
    - [ ] se Player estiver defendendo nao pode atacar
    - [ ] se Boss estiver defendendo nao pode atacar

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

- [ ] parte grafica da class Inventory
  - [ ] desenhar equipamentos equipados
  - [ ] desenhar itens no inventario

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

- [ ] criar e implementar logica de estados (FSM) para o Boss
  - [ ] estados podem trocar a cada turno
  - [x] IDLE (Richard)
  - [x] ATTACK (Richard)
  - [ ] DEFEND
    - [ ] aumenta defesa do Boss
    - [ ] nao pode mais atacar
  - [ ] ATTACK_SUPER
  - [ ] BERSERK
    - [ ] aumenta dano do Boss
    - [ ] aumenta defesa do Boss
    - [ ] proximo turno estado vai para WEAK
  - [ ] WEAK
    - [ ] nao pode mais se defender
    - [ ] nao pode mais ir para estado BERSERK
    - [ ] nivel/porcentagem de 'fraqueza' setavel dependendo do Boss
  - criar/implementar verificadores de estados para checagem de atingimento de estados
    - [x] reachedStateBerserk() (Richard)
    - [x] reachedStateDefend() (Richard)
    - [ ] reachedStateAttackSuper()
  - [ ] criar/implementar setadores para os estados atingidos
    - [ ] setStateAttack()
    - [ ] setStateDefend()
    - [ ] setStateBerserk()
    - [ ] setStateWeak()
    - [ ] setStateAttackSuper()

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

**Descricao**: verificar se erros nao ocorrerao durante runtime, melhor estrategia seria TDD

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
