package mpanampy;

import com.badlogic.gdx.math.Vector2;

public class VectorialCalculus {
	
	public static Vector2 dstVect(Vector2 v1,Vector2 v2) {
		float xtarget=v2.x-v1.x;
		float ytarget=v2.y-v1.y;
		Vector2 valiny=new Vector2(xtarget, ytarget);
		return valiny;
	}
	
	public static Vector2 centerOf(Vector2 v1, Vector2 v2) {
		float xtarget=(v2.x+v1.x)/2;
		float ytarget=(v2.y-v1.y)/2;
		Vector2 valiny=new Vector2(xtarget, ytarget);
		return valiny;
	}
	
	public static Vector2 abs(Vector2 v) {
		return new Vector2(Math.abs(v.x), Math.abs(v.y));
	}
}
