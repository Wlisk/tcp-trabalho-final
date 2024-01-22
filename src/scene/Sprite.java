package scene;

import com.raylib.Jaylib;
import com.raylib.Jaylib.Rectangle;
import com.raylib.Jaylib.Vector2;
import com.raylib.Raylib.Texture;

/**
 * Classe para gerenciar e desenhar todas as imagens do jogo na tela
 * <p>
 * A classe ficou em espera e não saíra na versão 1.0.* do jogo, 
 * portanto desconsidere essa classe até a versão 1.1.* ou maior saia
 */
public class Sprite {
    private static final float ZEROF = 0.0f;

    float imageScale = 1.0f;
    final Rectangle dimension = new Rectangle(ZEROF, ZEROF, ZEROF, ZEROF); 
    final Vector2 position = new Vector2(ZEROF, ZEROF);
    final String imageSrc;
    Texture texture;

    /**
     * Construtor de Sprite, apenas com o caminho da imagem
     * @param imageSrc caminho para a imagem da textura 
     */
    public Sprite(String imageSrc) {
        this.imageSrc = imageSrc;
        texture = null;
    }

    /**
     * Construtor de Sprite, apenas com o caminho da imagem
     * @param imageSrc caminho para a imagem da textura 
     * @param x o valor da posição x do retangulo do sprite
     * @param y o valor da posição y do retangulo do sprite
     * @param scale o tamanho do sprite na tela 
     * @param width a largura do retangulo do sprite 
     * @param height a altura do retangulo do sprite 
     */
    public Sprite(String imageSrc, float x, float y, float scale, float width, float height) {
        imageScale = scale;
        setDimension(x, y, width, height);
        texture = null;
        this.imageSrc = imageSrc;
    }

    /**
     * Seta as dimensões do retangulo do sprite
     * @param x o valor da posição x 
     * @param y o valor da posição y
     * @param width a largura do sprite 
     * @param height a altura do sprite 
     */
    public void setDimension(float x, float y, float width, float height) {
        dimension.x(x);
        dimension.y(y);
        dimension.width(width);
        dimension.height(height);
        pos(x, y);
    }

    /**
     * Retorna o vetor posição do sprite
     * @return (Vector2) o vetor posição
     */
    public Vector2 pos() { return position; }

    /**
     * Seta o vetor posição do sprite
     * @param x o novo valor da posição x
     * @param y o novo valor da posição y
     */
    public void pos(float x, float y) {
        position.x(x);
        position.y(y);
    }

    /** Retorna a posição x */
    public float x() { return position.x(); }

    /** Retorna a posição y */
    public float y() { return position.y(); }

    /**
     * Seta o valor da posição x
     * @param x posição x
     */
    public void x(float x) { position.x(x); }

    /**
     * Seta o valor da posição y
     * @param y posição y
     */
    public void y(float y) { position.y(y); }

    /** 
     * Carrega a textura para a memória
     * @return (Texture) a textura que foi carregada
     */
    public Texture loadTexture() {
        texture = Jaylib.LoadTexture(imageSrc);
        return texture;
    }

    /** Descarrega a textura da memória */
    public void unloadTexture() {
        Jaylib.UnloadTexture(texture);
    }
}
