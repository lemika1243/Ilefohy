package mpihaino;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import java.util.Vector;

import gameplay.GameObject;

public class MpihainoFanalaHidy implements InputProcessor{
	Vector<Integer> indexOfMovement=new Vector<Integer>();
	int mouseKey=-1;
	@Override
	public boolean keyDown(int keycode) {
		indexOfMovement.add(keycode);
		return false;
	}
	public Vector getIndexOfMovement() {return indexOfMovement;}

	@Override
	public boolean keyUp(int keycode) {
		for (int i = 0; i < indexOfMovement.size(); i++) {
			if(indexOfMovement.elementAt(i)==keycode) {
				indexOfMovement.remove(i);
			}
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		mouseKey=button;
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		mouseKey=-1;
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		// TODO Auto-generated method stub
		return false;
	}
	public int getMouseKey() {
		return mouseKey;
	}

}
