package gameplay;

import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class GameObject extends Actor {
    SpriteBatch model = new SpriteBatch();
    Sprite model2d;
	Texture textures;
	TextureRegion[] frames;
	Animation animation;
	float deltaTime=0f;
	float throughTime;
	int framewidth, frameheight;
    float speed;
    boolean flipHorizontally = false;
    boolean isDead=false;
    int hery=3;

    public GameObject(float x, float y, float w, float h) {
        this.setPosition(x, y);
        setHeight(h);
        setWidth(w);
        speed = 1;
        throughTime=0f;
    }

    public void drawMe(float delta) {
    	deltaTime=delta;
        model.begin();
        throughTime+=delta;
        TextureRegion currentFrame = (TextureRegion) animation.getKeyFrame(throughTime, true);
        model2d=new Sprite(currentFrame);

        // Flip the image horizontally if needed
        if (flipHorizontally) {
            model2d.setFlip(true, false); // Flip horizontally
        } else {
            model2d.setFlip(false, false); // Reset flip
        }

        model.draw(model2d, getX(), getY(), getHeight(), getWidth());
        model.end();
    }

    public abstract void move(Vector<Integer> keys);
    public abstract void mitifitra(int key);
    public abstract void maty();

    public void setModel(SpriteBatch s) {
        model = s;
    }

    // MOVEMENT FUNCTION
    public void moveRight() {
        float move = (float) (getX() + getSpeed());
        setX(move);
        setFlipHorizontally(false);
    }

    public void moveLeft() {
        float move = (float) (getX() - getSpeed());
        setX(move);
        setFlipHorizontally(true);
    }
    // END

    public SpriteBatch getModel() {
        return model;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(float s) {
        speed = s;
    }

    public void setFlipHorizontally(boolean flip) {
        flipHorizontally = flip; // Set the horizontal flip flag
    }
    
    
    public Texture getTextures() {
    	return textures;
    }
    
    
    public void setTextures(String assetLocation) {
    	textures=new Texture(Gdx.files.internal(assetLocation));
        frameheight=textures.getHeight();
    }
    
    
    public void setTextures(String assetLocation,int a) {
    	textures=new Texture(Gdx.files.internal(assetLocation));
        frameheight=textures.getHeight()/a;
    }
    
    
    public void setFrame(int a) {
    	framewidth=textures.getWidth()/a;
    	frames=new TextureRegion[a];
    	for (int i = 0; i < frames.length; i++) {
            frames[i] = new TextureRegion(textures, i * framewidth, 0, framewidth, frameheight);
		}
    	setAnimation(new Animation<TextureRegion>(0.25f, frames));
    }
    
    public void setFrame(int a, float duration) {
    	framewidth=textures.getWidth()/a;
    	frames=new TextureRegion[a];
    	for (int i = 0; i < frames.length; i++) {
            frames[i] = new TextureRegion(textures, i * framewidth, 0, framewidth, frameheight);
		}
    	setAnimation(new Animation<TextureRegion>(duration, frames));
    }
    
    public void setAnimation(Animation<TextureRegion> a) {
    	animation=a;
    }
    
    public int getHery() {
    	return hery;
    }
    
    public void setHery(int h) {
    	h=Math.abs(h);
    	hery=h;
    }
    
    public void setIsDead(boolean d) {
    	isDead=d;
    }
}
