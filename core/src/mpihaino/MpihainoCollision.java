package mpihaino;

import java.util.Vector;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;

import gameplay.Bala;
import gameplay.GameObject;
import gameplay.Player;

public class MpihainoCollision implements ContactListener {
    GameObject gameObject;
    World world;
    Vector<Contact> contactList=new Vector<Contact>();
    Vector<Bala>balaPotika=new Vector<Bala>();

    public MpihainoCollision(World w, GameObject o) {
    	world=w;
        gameObject = o;
    }

	@Override
	public void beginContact(Contact contact) {
		contactList.add(contact);
		if(gameObject instanceof Player) {
			bulletCollide(contact);
			Vector<Bala> bala=((Player)gameObject).getBala();
			for (int i = 0; i < bala.size(); i++) {
				Bala temp=bala.elementAt(i);
				Fixture a=contact.getFixtureA();
				Fixture b=contact.getFixtureB();
				if(temp.getBody().equals(a.getBody())||temp.getBody().equals(b.getBody())) {
					balaPotika.add(temp);
					bala.remove(i);
				}
			}
		}
	}

	@Override
	public void endContact(Contact contact) {
		contactList.remove(contact);
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {

	}
	
	public Vector<Contact> getContacts(){
		return contactList;
	}
	public Vector<Bala> getBala(){
		return balaPotika;
	}
	
	//VERIFY IF GAMEOBJECT IS HIT BY A BULLET
	public void bulletCollide(Contact con) {
		Fixture a=con.getFixtureA();
		Fixture b=con.getFixtureB();
		Player player=(Player)gameObject;
		if((a.getBody().equals(player.getBody())||b.getBody().equals(player.getBody()))
				&&(a.getBody().isBullet()||b.getBody().isBullet())) {
			player.setHurt(true);
		}
	}
	//END
}
