package game;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import control.Keyboard;
import control.Mouse;
import tools.Print;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import static constant.Constants.PlayerConstants.*;
import static constant.Constants.Directions.*;

//*********************************************//
// CONTROLS THE OBJECTS DRAWING
//*********************************************//
public class GamePanel extends JPanel {
    private int x, y;
    private Random random;
    private BufferedImage img;
    private BufferedImage[][] animations;
	private int aniTick, aniIndex, aniSpeed = 15;
	private int playerAction = IDLE;
	private int playerDir = -1;
	private boolean moving = false;

    public GamePanel() {
        x = y = 10;
        random = new Random();

        importIMG();
        loadAnimations();

        final Mouse mouse_controler = new Mouse(this);
        // configura o tamanho da area de desenho, isto é, diferente
        // do jframe que calcula o tamanho total da janela incluindo bordas da janela e outros
        setPreferredSize(new Dimension(800, 600));
        // for keyboard events
        addKeyListener(new Keyboard(this));
        // for mouse clicks and move events
        addMouseListener(mouse_controler);
        // for mouse dragging events
        addMouseMotionListener(mouse_controler);
    }

    private void importIMG() {
        final InputStream img_stream = getClass().getResourceAsStream("/images/player_sprites.png");

        try {
            img = ImageIO.read(img_stream);
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                img_stream.close();
            } catch(IOException e) {
				e.printStackTrace();
			}
        }
    }

    private void loadAnimations() {
		animations = new BufferedImage[9][6];
		for (int j = 0; j < animations.length; j++)
			for (int i = 0; i < animations[j].length; i++)
				animations[j][i] = img.getSubimage(i * 64, j * 40, 64, 40);
	}

    public void setDirection(int direction) {
		this.playerDir = direction;
		moving = true;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	private void updateAnimationTick() {
		aniTick++;
		if (aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if (aniIndex >= GetSpriteAmount(playerAction))
				aniIndex = 0;
		}

	}

	private void setAnimation() {
		if (moving)
			playerAction = RUNNING;
		else
			playerAction = IDLE;
	}

	private void updatePos() {
		if (moving) {
			switch (playerDir) {
			case LEFT:
				x -= 5;
				break;
			case UP:
				y -= 5;
				break;
			case RIGHT:
				x += 5;
				break;
			case DOWN:
				y += 5;
				break;
			}
		}
	}

    public void updateGame() {
		updateAnimationTick();
		setAnimation();
		updatePos();
	}

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawString("Hello World!", x, y);

        g.drawImage(animations[playerAction][aniIndex], x, y, 256, 160, null); //128,80
    }

    public void changeX(int delta_x) {
        this.x += delta_x;
    }

    public void changeY(int delta_y) {
        this.y += delta_y;
    }

    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
