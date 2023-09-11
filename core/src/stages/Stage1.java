package stages;

import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
	
	
	public Stage1(ScreenMenu m) {
		game = m.getGames();
		Gdx.input.setInputProcessor(hidy);
		camera=new OrthographicCamera();
		world = new World(new Vector2(0, -200), true);
		box=new Box2DDebugRenderer();
		player=new Player(world,m.getGames(),camera);
		player.setSpeed(500);
		contact=new MpihainoCollision(world,player);
		world.setContactListener(contact);
		menu=m;
		gameport=new FitViewport(m.getGames().getWidth()/10,m.getGames().getHeight()/10,camera);
		tmxLoader=new TmxMapLoader();
		tiledMap=tmxLoader.load("level1.tmx");
		ortho=new OrthogonalTiledMapRenderer(tiledMap);
		camera.position.set(gameport.getWorldWidth()/2,gameport.getWorldHeight()/2, 0);
		makeAllBoxCollider();
	}

@Override
	public void show() {
		menu.dispose();
	}

	@Override
	public void render(float delta) {
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	    handleGrounded();
	    renderAll();
	    player.move(hidy.getIndexOfMovement());
	    player.drawMe(delta);
	    player.handleMitifitra(hidy.getMouseKey());
	    handleBala();
	    world.step(1/60f, 6, 2);
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
//		box.render(world, camera.combined);
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
		Vector<Bala> ball=contact.getBala();
		int count=ball.size();
		for(int i=0;i<count;i++) {
			ball.elementAt(i).maty();
			ball.remove(i);
		}
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
