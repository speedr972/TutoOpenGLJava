package shaders;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Light;
import toolbox.MathOGL;

public class StaticShader extends ShaderProgram{

	private static final String VERTEX_SHADER = "src/shaders/vertexShader.txt";
	private static final String FRAGMENT_SHADER = "src/shaders/fragmentShader.txt";
	
	private int location_transformationMatrix;
	private int location_projectionMatrix;
	private int location_viewMatrix;
	private int location_lightPosition;
	private int location_lightColour;
	
	private int location_shineDamper;
	private int location_reflectivity;
	
	private int location_useFakeLighting;
	
	private int location_skyColour;
	
	public StaticShader() {
		super(VERTEX_SHADER, FRAGMENT_SHADER);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void bindAttribute() {
		// TODO Auto-generated method stub
		super.bindAttribute(0,  "position");
		super.bindAttribute(1, "textureCoords");
		super.bindAttribute(2, "normal");
	}

	@Override
	protected void getAllUniformLocations() {
		// TODO Auto-generated method stub
		location_transformationMatrix = super.getUniformLocation("transformationMatrix");
		location_projectionMatrix = super.getUniformLocation("projectionMatrix");
		location_viewMatrix = super.getUniformLocation("viewMatrix");
		location_lightPosition = super.getUniformLocation("lightPositon");
		location_lightColour = super.getUniformLocation("lightColour");
		
		location_shineDamper = super.getUniformLocation("shineDamper");
		location_reflectivity = super.getUniformLocation("reflectivity");
		location_useFakeLighting = super.getUniformLocation("useFakeLighting");
		
		location_skyColour = super.getUniformLocation("skyColour");
	}
	
	public void loadSkyColour(float r, float g, float b){
		super.loadVector(location_skyColour, new Vector3f(r, g, b));
	}
	
	public void loadFakeLighting(boolean useFake){
		super.loadBoolean(location_useFakeLighting, useFake);
		
	}
	
	public void loadShineVariables(float damper, float reflectivity)
	{
		super.loadFloat(location_shineDamper, damper);
		super.loadFloat(location_reflectivity, reflectivity);
		
	}
	public void loadLight(Light light){
		super.loadVector(location_lightColour, light.getColour());
		super.loadVector(location_lightPosition, light.getPosition());
		
	}
	
	public void loadTransformationMatrix(Matrix4f matrix){
		super.loadMatrix(location_transformationMatrix, matrix);
	}
	
	public void loadProjectionMatrix(Matrix4f matrix){
		super.loadMatrix(location_projectionMatrix, matrix);
	}
	
	public void loadViewMatrix(Camera camera){
		Matrix4f viewMatrix = MathOGL.createViewMatrix(camera);
		super.loadMatrix(location_viewMatrix, viewMatrix);
	}
}
