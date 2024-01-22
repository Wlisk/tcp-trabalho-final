package interfaces;

/** Padronização de métodos para clicar na tela */
public interface IClickable {
    /** 
     * Método para verificar se o mouse está pressionando o objeto na tela 
     * @param mouseButton qual dos botões do mouse para verificar 
     *   (0 para o esquerdo e 1 para o direto)
     * @return (boolean) se o mouve está precionado ou não
     */
    public boolean isMousePressed(int mouseButton);
}
