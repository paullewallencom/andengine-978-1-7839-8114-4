package com.nyanko.andengineontour;

import java.io.IOException;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.bitmap.AssetBitmapTexture;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.ui.activity.*;

public class MainActivity extends BaseGameActivity {
	private Scene mScene;
	private static final int mCameraHeight = 480;
	private static final int mCameraWidth = 800;
	
	private ITexture mFaceTexture;
	private ITextureRegion mFaceTextureRegion;

	@Override
	public EngineOptions onCreateEngineOptions() {
		Camera mCamera = new Camera(0, 0, mCameraWidth, mCameraHeight);
		final EngineOptions engineOptions = new EngineOptions(true,
				ScreenOrientation.LANDSCAPE_SENSOR, new RatioResolutionPolicy(
						mCameraWidth, mCameraHeight), mCamera);
		return engineOptions;
	}

	@Override
	public void onCreateResources(
			OnCreateResourcesCallback pOnCreateResourcesCallback)
			throws IOException {
		this.mFaceTexture = new AssetBitmapTexture(this.getTextureManager(), this.getAssets(), 
				"gfx/helloworld.png");
		this.mFaceTextureRegion = TextureRegionFactory.extractFromTexture(this.mFaceTexture);
		this.mFaceTexture.load();
		
		pOnCreateResourcesCallback.onCreateResourcesFinished();		
	}

	@Override
	public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback) 
			throws IOException {
		mScene = new Scene();
		pOnCreateSceneCallback.onCreateSceneFinished(mScene);		
	}

	@Override
	public void onPopulateScene(Scene pScene,
			OnPopulateSceneCallback pOnPopulateSceneCallback)
			throws IOException {
		pScene.getBackground().setColor(0.8f, 0.8f, 0.8f);

		final float centerX = mCameraWidth / 2;
		final float centerY = mCameraHeight / 2;

		// Create the sprite and add it to the scene.
		final Sprite sprite = new Sprite(centerX, centerY, this.mFaceTextureRegion, 
				this.getVertexBufferObjectManager());
		pScene.attachChild(sprite);
		pOnPopulateSceneCallback.onPopulateSceneFinished();		
	}

}
