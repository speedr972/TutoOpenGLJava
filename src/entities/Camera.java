package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

public class Camera {

	private final float moveIncrement = 0.1f;
	private Vector3f position = new Vector3f(0,0,0);
	private float pitch;
	private float yaw;
	private float roll;

	public Camera(){

	}

	public void move(){
		/*
		if(Keyboard.isKeyDown(Keyboard.KEY_Z)){
			position.z -= moveIncrement;
			System.out.println("z = " + position.z);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)){
			position.z += moveIncrement;
			System.out.println("z = " + position.z);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_Q)){
			position.x -= moveIncrement;
			System.out.println("x = " + position.x);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)){
			position.x += moveIncrement;
			System.out.println("x = " + position.x);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_R)){
			position.y += moveIncrement;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_F)){
			position.y -= moveIncrement;
		}

		if(Keyboard.isKeyDown(Keyboard.KEY_UP)){
			pitch +=0.5f;
		}

		if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
			pitch -=0.5f;
		}

		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
			yaw -=0.5f;
		}

		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
			yaw +=0.5f;
		}
		*/
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public float getPitch() {
		return pitch;
	}

	public void setPitch(float pitch) {
		this.pitch = pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public void setYaw(float yaw) {
		this.yaw = yaw;
	}

	public float getRoll() {
		return roll;
	}

	public void setRoll(float roll) {
		this.roll = roll;
	}


}
