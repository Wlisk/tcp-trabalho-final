package interfaces;

/** Padronização de métodos para usar o teclado */
public interface IKeyboardable {
    /** 
     * Método para verificar se o teclado foi pressionado, 
     * para que a ação do objeto seja executada
     * @return (boolean) se o teclado foi precionado ou não
     */
    public boolean isKeyPressed();
}
