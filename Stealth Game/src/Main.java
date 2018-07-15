import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.nio.IntBuffer;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

public class Main {
	/**
	 * An explicit POINTER in JAVA!
	 */
	private static long window;
	public static float angle = 0.0f;
	
	public static void main(String[] args) {
		System.out.println("LWJGL Version: " + Version.getVersion());
		init();
		loop();
		
		glfwFreeCallbacks(window);
		glfwDestroyWindow(window);
		
		glfwTerminate();
		glfwSetErrorCallback(null).free();
	}
	
	/**
	 * Uses GLFW to create and initialize an OpenGL context, screw GLFW
	 */
	public static void init() {
		GLFWErrorCallback.createPrint(System.err).set();
		
		if (!glfwInit()) {
			throw new IllegalStateException("Could not initialize GLFW, screw GLFW");
		}
		
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
		
		window = glfwCreateWindow(300, 300, "LWJGL Test", NULL, NULL);
		if (window == NULL) {
			throw new RuntimeException("GLFW failed to create the d*** window, screw GLFW");
		}
		
		glfwSetKeyCallback(window, Main::keyCallBackFunction);
		
		try (MemoryStack stack = stackPush()) {
			IntBuffer pWidth = stack.mallocInt(1);
			IntBuffer pHeight = stack.mallocInt(1);
			
			glfwGetWindowSize(window, pWidth, pHeight);
			
			GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
			
			glfwSetWindowPos(window, (vidmode.width() - pWidth.get(0)) / 2, (vidmode.height() - pHeight.get(0)) / 2);
		}
		
		glfwMakeContextCurrent(window);
		
		//V-sync
		glfwSwapInterval(1);
		
		glfwShowWindow(window);
	}
	
	public static void loop() {
		GL.createCapabilities();
		
		glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
		
		while (!glfwWindowShouldClose(window)) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			
			draw();
			
			glfwSwapBuffers(window);
			
			glfwPollEvents();
		}
	}
	
	public static void draw() {
		glLoadIdentity();
		glColor3f(1.0f, 1.0f, 1.0f);
		glRotatef(angle, 0.0f, 0.3f, 0.1f);

		glBegin(GL_TRIANGLES);
			glVertex3f(-0.5f,-0.5f, 0.0f);
			glVertex3f( 0.5f, 0.0f, 0.0f);
			glVertex3f( 0.0f, 0.5f, 0.0f);
		glEnd();
		
		angle += 1.0f;
	}
	
	public static void keyCallBackFunction(long window, int key, int scancode, int action, int mods) {
		if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
			glfwSetWindowShouldClose(window, true);
		}
	}
}
