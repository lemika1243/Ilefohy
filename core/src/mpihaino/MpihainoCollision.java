package mpihaino;

import java.util.Vector;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

import gameplay.GameObject;

public class MpihainoCollision implements ContactListener {
    GameObject gameObject;
    Vector<Contact> contactList=new Vector<Contact>();

    public MpihainoCollision(GameObject o) {
        gameObject = o;
    }

	@Override
	public void beginContact(Contact contact) {
		contactList.add(contact);
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
}
