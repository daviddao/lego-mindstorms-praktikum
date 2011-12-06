import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;

/**
 * 
 * Motor A: rechts
 * Motor C: links
 */
public class Engine {
	
	private final NXTRegulatedMotor LEFT = Motor.C; 
	private final NXTRegulatedMotor RIGHT = Motor.A;
	
	public final int MAX_SPEED = 900;
	//private int defaultSpeed = 200;
	//	private int speed = 0; // [-100%, 100%]

	
	public Engine() {
		
	}
	
	/**
	 * Lege Geschindigkeit fest.
	 * Die Geschwindigkeit wird dabei als Buchteil der Maximalgeschwindigkeit festgelegt.
	 * @param v Geschwindigkeit in Prozent [-100,100]
	 */
	private void setSpeed(int v) {
		if (v < -100 && v > 100) throw new IllegalArgumentException();
		
		float motor_speed = MAX_SPEED * v / 100;
		if (motor_speed < 0) motor_speed *= -1;
		
		LEFT.setSpeed(motor_speed);
		RIGHT.setSpeed(motor_speed);	
	}
	
	public void forward(int v) {
		setSpeed(v);
		LEFT.forward();
		RIGHT.forward();
	}
	
	public void backward(int v) {
		setSpeed( v * (-1) );
		LEFT.backward();
		RIGHT.backward();
	}
	
	public void stop() {
		setSpeed(0);
		LEFT.stop();
		RIGHT.stop();
	}
	
	public void turn() {
		setSpeed(100);
		LEFT.forward();
		RIGHT.backward();
	}
	
	/**
	 * Gibt an wieviel Prozent das linke Rad langsamer sein soll als das rechte.
	 *
	 * Bei 100% blockiert das linke Rad.
	 * Bei 0% f�hrt der Roboter grade aus.
	 * 
	 * @param p st�rke der Kurve [0, 100]
	 */
	public void bendLeft(int p) {
		if (p < 0 && p > 100) throw new IllegalArgumentException();
		
		float left_speed = RIGHT.getSpeed() * p / 100;
		LEFT.setSpeed(left_speed);
	}
	
	/**
	 * Gibt an wieviel Prozent das rechte Rad langsamer sein soll als das linke.
	 *
	 * Bei 100% blockiert das rechte Rad.
	 * Bei 0% f�hrt der Roboter grade aus.
	 * 
	 * @param p st�rke der Kurve [0, 100]
	 */
	public void bendRight(int p) {
		if (p < 0 && p > 100) throw new IllegalArgumentException();
		
		float right_speed = LEFT.getSpeed() * p / 100;
		RIGHT.setSpeed(right_speed);
	}
	
	/**
	 * @return true wenn der Roboter in Bewegung ist
	 */
	public boolean isMoving() {
		return LEFT.isMoving() || RIGHT.isMoving();
	}
	
	public void update() {
		
	}
}
