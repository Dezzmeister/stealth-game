package control;

import static org.lwjgl.glfw.GLFW.glfwGetTime;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Represents a running game; contains the game loop and other information about the game.
 *
 * @author Joe Desmond
 */
public class Game implements Runnable {
	private GameState state = GameState.INTRO;
	private boolean threadCreated = false;
	private AtomicBoolean running = new AtomicBoolean(true);
	private double lastLoopTime;
	private float accumulator = 0.0f;
	private int targetFPS = 60;
	private float interval;
	private float alpha;
	
	@Override
	public void run() {
		init();
		loop();
		dispose();
	}
	
	public Thread createThreadAndStart() throws IllegalStateException {
		Thread thread = createThread();
		thread.start();
		return thread;
	}
	
	public Thread createThread() throws IllegalStateException {
		if (!threadCreated) {
			threadCreated = true;
			return new Thread(this,"Stealth Game Main Loop");
		} else {
			throw new IllegalStateException("Cannot create more than one Game Loop Thread for a Game!");
		}
	}
	
	private void init() {
		interval = 1.0f / (float)targetFPS;
		lastLoopTime = getTime();
	}
	
	private void loop() {
		while (running.get()) {
			float delta = getDelta();
			accumulator += delta;
			
			while (accumulator > interval) {
				update(interval);
				accumulator -= interval;
			}
			
			alpha = accumulator / interval;
			render(alpha);
			
			//Update window
		}
	}
	
	private void dispose() {
		
	}
	
	private void update(float delta) {
		
	}
	
	private void render(float alpha) {
		
	}
	
	private double getTime() {
		return glfwGetTime();
	}
	
	private float getDelta() {
		double time = getTime();
		float delta = (float) (time - lastLoopTime);
		lastLoopTime = time;
		return delta;
	}
}
