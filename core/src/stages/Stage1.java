package stages;

import java.util.Iterator;
import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ilefohy.game.Ilefohy;

import ScreenMenu.ScreenMenu;
import gameplay.Bala;
import gameplay.Player;
import gameplay.fahavalo.Fahavalo;
import gameplay.fahavalo.Flyer;
import gameplay.fahavalo.Portal;
import mpihaino.MpihainoCollision;
import mpihaino.MpihainoFanalaHidy;

public class Stage1 implements Screen {
	ScreenMenu menu;
	Ilefohy game;
	Player player;
	OrthographicCamera camera;
	TmxMapLoader tmxLoader;
	TiledMap tiledMap;
	OrthogonalTiledMapRenderer ortho;
	Viewport gameport;
	World world; Box2DDebugRenderer box;
	MpihainoFanalaHidy hidy=new MpihainoFanalaHidy();
	MpihainoCollision contact;
	ParticleEffect particle=new ParticleEffect();
	SpriteBatch batch=new SpriteBatch();
	Vector<Fahavalo> fahavalo=new Vector<Fahavalo>();
	Portal portal;
	float deltaTime=0f;
	
	
	public Stage1(ScreenMenu m) {
		game = m.getGames();
		Gdx.input.setInputProcessor(hidy);
		camera=new OrthographicCamera();
		world = new World(new Vector2(0, -50), true);
		box=new Box2DDebugRenderer();
		player=new Player(world,m.getGames(),camera);
		contact=new MpihainoCollision(world,player);
		world.setContactListener(contact);
		menu=m;
		gameport=new FitViewport(m.getGames().getWidth()/7,m.getGames().getHeight()/7,camera);
		tmxLoader=new TmxMapLoader();
		tiledMap=tmxLoader.load("level1.tmx");
		ortho=new OrthogonalTiledMapRenderer(tiledMap);
		camera.position.set(gameport.getWorldWidth()/2,gameport.getWorldHeight()/2, 0);
		particle.load(Gdx.files.internal("bulletParticle.p"), Gdx.files.internal(""));
	    particle.scaleEffect(0.1f);
	    portal=new Portal(world, game, camera, player);
		makeAllBoxCollider();
	}

@Override
	public void show() {
		menu.dispose();
	}

	@Override
	public void render(float delta) {
		deltaTime=delta;
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	    handleGrounded();
	    renderAll();
	    player.move(hidy.getIndexOfMovement());
	    player.drawMe(1);
	    player.handleMitifitra(hidy.getMouseKey());
	    portal.handled();
	    for(Fahavalo fav:portal.getFahavalo()) {
	    	fav.drawMe(8);
	    	fav.move();
	    }
	    handleBala();
	    world.step(1/30f, 6, 2);
	    camera.position.set(player.getBody().getPosition().x, player.getBody().getPosition().y, 0);
	    camera.update();
	    ortho.setView(camera);
	}

	
	public void makeCollider(int index) {
		BodyDef bodydef=new BodyDef();
		PolygonShape shape=new PolygonShape();
		FixtureDef fixture=new FixtureDef();
		Body body;
		for (MapObject object : tiledMap.getLayers().get(index).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rec=((RectangleMapObject)object).getRectangle();
			bodydef.type=BodyType.StaticBody;
			bodydef.position.set(rec.getX()+rec.getWidth()/2,rec.getY()+rec.getHeight()/2);
			body=world.createBody(bodydef);
			shape.setAsBox(rec.getWidth()/2, rec.getHeight()/2);
			fixture.shape=shape;
			body.createFixture(fixture);
		}
	}
	
	public void renderAll() {
		ortho.render();
		box.render(world, camera.combined);
	}
	
	public void makeAllBoxCollider() {
		for (int i = 0; i < 6; i++) {
			makeCollider(i);
		}
	}

	public Ilefohy getPrincipalGame() {
		return game;
	}
	
	@Override
	public void resize(int width, int height) {
		gameport.update(width, height);
	}
	
	public void handleGrounded() {
		if(player.getBody().getLinearVelocity().y==0) {
			player.setIsGrounded(true);
		}else {
			player.setIsGrounded(false);
		}
	}
	public void handleBala() {
	    particle.update(deltaTime);
		Vector<Bala> ball=contact.getBala();
		int count=ball.size();
		if (count >= 1) {
		    Bala bala = ball.elementAt(0);

		    // Set the position of the particle effect
		    particle.setPosition(bala.getBody().getPosition().x, bala.getBody().getPosition().y);
		    particle.reset(false);
		    

	        ball.elementAt(0).maty();
	        ball.remove(0);
		}
	    batch.setProjectionMatrix(camera.combined);
	    batch.begin();
	    particle.draw(batch);
	    batch.end();

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
	}
	
}
