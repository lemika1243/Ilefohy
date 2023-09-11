package gameplay;

import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.ilefohy.game.Ilefohy;

public abstract class GameObject extends Sprite {
	float acceleration=0;
    SpriteBatch model = new SpriteBatch();
	Texture textures;
	TextureRegion[] frames;
	Animation<TextureRegion> animation;
	TextureRegion currentFrame=new TextureRegion();
	float deltaTime=0f;
	float throughTime;
	int framewidth, frameheight;
    float speed;
    boolean flipHorizontally = false;
    boolean isDead=false;
    int hery=3;
    boolean loopedAnimation=true;
    World world;
    Body body;
	boolean isGrounded=false;
	Ilefohy ilefohy;
	float shapeW,shapeH;

	int width=20,height=20;
	float WH;
	OrthographicCamera camera;
    

    //CONSTRUCTORS
    public GameObject(World wo,Ilefohy i,OrthographicCamera cam) {
        world=wo;
        camera=cam;
        ilefohy=i;
        speed = 1;
        throughTime=0f;
    }
    //END

    
    
    
    
    //GET AND SET FUNCTIONS
    public OrthographicCamera getCamera() {
    	return camera;
    }
    public boolean isFliped() {
    	return flipHorizontally;
    }
    public void setWidth(int w) {
    	width=w;
    }
    public void setHeight(int h) {
    	height=h;
    }
    public boolean isGrounded() {
    	return isGrounded;
    }
    public void setIsGrounded(boolean t) {
    	isGrounded=t;
    }
    public Body getBody() {
    	return body;
    }
    public void setAcceleration(float a) {
    	acceleration=a;
    }
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

    public void setModel(SpriteBatch s) {
        model = s;
    }
    public void setLoopAnimation(boolean l) {
        loopedAnimation = l;
        
        if (animation != null) {
            // Set the play mode of the animation
            if (loopedAnimation) {
                animation.setPlayMode(Animation.PlayMode.LOOP);
            } else {
                animation.setPlayMode(Animation.PlayMode.NORMAL);
            }
        }
    }
    public void setShape(float w,float h) {
    	shapeW=w;shapeH=h;
    }

    
    //END
    
    
    
    
    
    
    //REDEFINITION OF ABSTRACT FUNCTIONS

    public abstract void move(Vector<Integer> keys);
    public abstract void mitifitra(int key);
    public abstract void maty();
    public abstract void matyAnimation();
    public abstract void hurt();
    public abstract void hurtAnimation();
    
    //END
    
    
    
    
    
    //UTILITY FUNCTIONS
    
    public void defineMe(BodyType type, float x, float y) {

        WH=(shapeH*2000)/7;
        setBounds(x, y, shapeW, shapeH);
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = type;
        bodyDef.position.set(getX(), getY());
        bodyDef.fixedRotation = true;
        body = world.createBody(bodyDef);

        PolygonShape playerShape = new PolygonShape();
        playerShape.setAsBox(getWidth(), getHeight());

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = playerShape;
        fixtureDef.density = 1.0f;

        body.createFixture(fixtureDef);
    }

    public void drawMe(float delta) {
        deltaTime = delta;
        throughTime += delta;
        model.setProjectionMatrix(camera.combined);
        if (loopedAnimation) {
            loopAnimation();
        } else {
            finishAnimation();
        }
        // Set the sprite's position to match the Box2D body's position

        if (textures != null)
            this.setRegion(currentFrame);

        // Flip the image horizontally if needed
        if (flipHorizontally) {
            this.setFlip(true, false); // Flip horizontally
        } else {
            this.setFlip(false, false); // Reset flip
        }

        setBounds(ilefohy.getWidth()/2-shapeW*2, ilefohy.getHeight()/2-shapeW*14,WH,WH);
        model.begin();
        model.draw(this, body.getPosition().x-shapeW, body.getPosition().y-shapeH, width, height);
        model.end();
    }

    
    
    
    // MOVEMENT FUNCTION
    public void moveRight() {
    	body.applyLinearImpulse(new Vector2(speed,0), body.getWorldCenter(), true);
        setFlipHorizontally(false);
    }

    public void moveLeft() {
    	body.applyLinearImpulse(new Vector2(-speed,0), body.getWorldCenter(), true);
        setFlipHorizontally(true);
    }
    void finishAnimation() {
    	if(animation!=null)
    		currentFrame=animation.getKeyFrame(throughTime,false);
    }
    void loopAnimation() {
    	if(animation!=null)
    		currentFrame = animation.getKeyFrame(throughTime, true);
    }
    // END
    //END
    
}
