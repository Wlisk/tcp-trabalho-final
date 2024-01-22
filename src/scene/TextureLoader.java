package scene;

import com.raylib.Jaylib;
import com.raylib.Raylib.Texture;

/** 
 * Classe para gerenciar o carregamento e o descarregamento de texturas da memória.
 */
class TextureLoader {
    private boolean isTextureLoaded = false;
    private Texture texture = null;

    /**
     * Retorna da memória a textura, e ou carrega na memória caso já não esteja 
     * @param imageSrc o caminho de imagem da textura  
     * @return (Texture) a textura carregada na memória ou null caso não consiga carregar a textura
     */
    public Texture getTexture(String imageSrc) {
        if(!isTextureLoaded) {
            texture = Jaylib.LoadTexture(imageSrc);
            isTextureLoaded = true;
        }
        return texture;
    }

    /**
     * Descarrega a textura da memória
     * @return (boolean) retorna true se foi descarregada ou false se não há nada a descarregar
     */
    public boolean unloadTexture() {
        if(isTextureLoaded) {
            Jaylib.UnloadTexture(texture);
            isTextureLoaded = false;
            texture = null;
            return true;
        }
        return false;
    }
}
