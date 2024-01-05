# TODO

Tarefas concluídas e ainda a serem feitas no projeto.

## &nbsp;

### Tarefas

(Richard)

- [ ] implementar class Sprite
  - [ ] pegar imagem e subdividir em partes
  - [ ] implementar metodo nextSprite()
  - [ ] menu de selecao com sprites/imagens animadas (se movendo)
  - [ ] criar interface ISpritable
  - [ ] criar propriedade sprites em cada classe spritable
    - [ ] class Player
    - [ ] class Boss

---

- [ ] criar cenas do jogo
  - [x] MAIN_MENU
  - [x] SELECTING_CLASS
  - [ ] BATTLE_START
  - [ ] TURN_START
  - [ ] TURN_PLAYER_CHOOSE
  - [ ] TURN_PLAYER_CHOSEN
  - [ ] TURN_ENEMY_CHOOSE
  - [ ] TURN_ENEMY_CHOSEN
  - [ ] TURN_END
  - [ ] BATTLE_END

---

- [ ] implementar acoes usando teclado
  - [ ] selecionar botoes menu inicial com teclado

---

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
  - [ ] testar classes em utils/

---

- [ ] implementar sons de jogo
  - [ ] sons ao selecionar, passar mouse por botoes do jogo
  - [ ] musica de fundo menu inicial e selecao
  - [ ] musica de fundo para os bosses

---

- [ ] implementar batalhas Player vs Boss
  - [ ] implementar jogada de turnos
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

- [ ] setar interfaces e implementar seus devidos códigos
  - [] para class Button
    - [x] IClickable
    - [x] IDrawable
    - [ ] IKeyboardable
    - [x] IMouseOverable
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

- [ ] caixa de texto para jogador colocar/setar o nome

---

- [ ] implementar estado do jogo
  - [x] MAIN_MENU
  - [x] SELECTING_CLASS
  - [ ] BATTLE_START
  - [ ] TURN_START
  - [ ] TURN_PLAYER_CHOOSE
  - [ ] TURN_PLAYER_CHOSEN
  - [ ] TURN_ENEMY_CHOOSE
  - [ ] TURN_ENEMY_CHOSEN
  - [ ] TURN_END
  - [ ] BATTLE_END
  - [x] GAME_END

---

- [ ] criar logica do score do jogador

---

- [ ] criar logica para drop de items
  - [ ] criar novos items
  - [ ] selecionar de uma lista de items um item randomicamente

---

- [ ] implementar classe Super
  - [ ] implementar super para o Player
  - [ ] implementar super para o Boss
  - [ ] implementar caixa de selecao de supers para o Player

---

- [ ] implementar callbacks para butoes e outros clicaveis
  - [ ] quando botao clicado aciona um callback (funcao)
  - [ ] adicionar callback em Iclickable

---

- [ ] implementar posicao (Vector2D) em todos os items que aparecem graficamente

---

- [ ] conferir e implementar codigo de setters
  - [ ] setters definidos devem ser protegidos com 'guard statements'
  - [ ] verificar e proteger setters em class Entity

---

- [ ] implementar bloco grafico de visualizacao de estatisticas do jogador
  - [ ] desenhar em canto especifico das laterais
  - [ ] apresenta dados corrente do Player

---

- [ ] implementar logica de itens que geram buff/debuff overtime (por X turnos)

---

- [ ] implementar logica de attackSuper() da class Entity

---

- [x] implementar barras
  - [x] barra de vida
    - [x] do Player
    - [x] do Boss
  - [x] barra de mana
    - [x] do Player
    - [x] do Boss

---

- [ ] parte grafica da class Inventory
  - [ ] desenhar equipamentos equipados
  - [ ] desenhar itens no inventario

---

- [ ] apresentar/desenhar relacao entre quantidade de Boss e o indice do Boss corrente

---

- [ ] criar e implementar detalhes para que itens tenham dados 'corretos'
  - [ ] detalhes de bosses
  - [ ] detalhes de armas
  - [ ] detalhes de armaduras
  - [ ] detalhes de consumiveis

---

- [ ] apresentar estatisticas basicas das classes no menu de selecao de classes

---

- [ ] criar e implementar logica de estados (FSM) para o Boss
  - [ ] estados podem trocar a cada turno
  - [x] IDLE
  - [x] ATTACK
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
    - [x] reachedStateBerserk()
    - [x] reachedStateDefend()
    - [ ] reachedStateAttackSuper()
  - [ ] criar/implementar setadores para os estados atingidos
    - [ ] setStateAttack()
    - [ ] setStateDefend()
    - [ ] setStateBerserk()
    - [ ] setStateWeak()
    - [ ] setStateAttackSuper()

---

(Richard)

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
  - [x] documentar classes em utils/

---

- [ ] implementar logica de turnos
  - [ ] logica de regeneracao de mana ao longo dos turnos
  
---

- [ ] implementar efeitos especiais
  - [ ] efeitos especiais ao atacar
  - [ ] efeitos especiais ao defender
  - [ ] efeitos especiais ao interagir com objetos/itens
