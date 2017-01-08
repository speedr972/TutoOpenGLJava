package engineTester;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import entities.Light;
import entities.Player;
import models.RawModel;
import models.TexturedModel;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;
import terrains.Terrain;
import textures.ModelTexture;
import textures.TerrainTexture;
import textures.TerrainTexturePack;

public class MainGameLoop {

	public static void main(String[] args) {
		DisplayManager.createDisplay();

		Loader loader = new Loader();
		
		RawModel treeModel = OBJLoader.loaadObjModel("models/tree", loader);
		ModelTexture treeTexture = new ModelTexture(loader.loadTexture("textures/tree"));
		treeTexture.setShineDamper(10);
		treeTexture.setReflectivity(1);
		TexturedModel treeTexturedModel = new TexturedModel(treeModel, treeTexture);
		
		RawModel grassModel = OBJLoader.loaadObjModel("models/grassModel", loader);
		ModelTexture grassTexture = new ModelTexture(loader.loadTexture("textures/grassTexture"));
		grassTexture.setReflectivity(0.2f);
		grassTexture.setShineDamper(20);
		grassTexture.setHasTransparency(true);
		grassTexture.setUseFakeLighting(true);
		TexturedModel grassTexturedModel = new TexturedModel(grassModel, grassTexture);
		
		TexturedModel fernTexturedModel = new TexturedModel(OBJLoader.loaadObjModel("models/fern", loader), 
				new ModelTexture(loader.loadTexture("textures/fern")));
		
		//Entity ent = new Entity(treeTexturedModel, new Vector3f(0,0,-20), 0, 0, 0, 0.3f);
		
		List<Entity> allEntities = new ArrayList<Entity>();
		Random random = new Random();
		for(int i = 0; i<500; i++){
			float x = random.nextFloat()*256 - 128;
			float y = 0;
			float z = -(random.nextFloat()*256 - 128);
			allEntities.add(new Entity(treeTexturedModel, new Vector3f(x, y, z), 0, random.nextFloat()*180f, 0f, 1.25f));
		}
		for(int i = 0; i<500; i++){
			float x = random.nextFloat()*256 - 128;
			float y = 0;
			float z = -(random.nextFloat()*256 - 128);
			allEntities.add(new Entity(grassTexturedModel, new Vector3f(x, y, z), 0, random.nextFloat()*180f, 0f, 1.0f));
		}
		for(int i = 0; i<500; i++){
			float x = random.nextFloat()*256 - 128;
			float y = 0;
			float z = -(random.nextFloat()*256 - 128);
			allEntities.add(new Entity(fernTexturedModel, new Vector3f(x, y, z), 0, random.nextFloat()*180f, 0f, 0.15f));
		}
		
		/*
		 * TEXTURE PACK
		 */
		TerrainTexture backgroundTexture = new TerrainTexture(loader.loadTexture("textures/grassy"));
		TerrainTexture rTexture = new TerrainTexture(loader.loadTexture("textures/dirt"));
		TerrainTexture gTexture = new TerrainTexture(loader.loadTexture("textures/pinkFlowers"));
		TerrainTexture bTexture = new TerrainTexture(loader.loadTexture("textures/path"));
		TerrainTexture blendMap = new TerrainTexture(loader.loadTexture("textures/blendMap"));
		
		TerrainTexturePack texturePack = new TerrainTexturePack(backgroundTexture, rTexture, gTexture, bTexture);
		
		Terrain terrain0 = new Terrain(1,  0, loader, texturePack, blendMap);
		Terrain terrain1 = new Terrain(0,  0, loader, texturePack, blendMap);
		
		
		Light light = new Light(new Vector3f(2000, 2000, 2000), new Vector3f(1,1,1));
		
		Camera camera = new Camera();
		
		camera.setPosition(new Vector3f(0,5,0));
		camera.setPitch(10);
		
		
		
		RawModel bunnyModel = OBJLoader.loaadObjModel("models/bunny", loader);
		TexturedModel stanfordBunny = new TexturedModel(bunnyModel, new ModelTexture(loader.loadTexture("textures/white")));
		
		Player player = new Player(stanfordBunny, new Vector3f(0, 0, -10), 0, 0, 0, 0.2f);

		MasterRenderer renderer = new MasterRenderer();
		
		while(!Display.isCloseRequested()){
			//entity.increasePosition(0, 0, -0.01f);
			//entity.increaseRotation(0, 1.0f, 0);
			camera.move();
			player.move();
			renderer.processEntity(player);
			renderer.processTerrain(terrain0);
			renderer.processTerrain(terrain1);
			
			for(Entity ent : allEntities){
				renderer.processEntity(ent);
			}
			renderer.render(light, camera);
			DisplayManager.updateDisplay();
		} 
		renderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}
		


}
