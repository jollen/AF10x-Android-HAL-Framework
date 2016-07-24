package com.mokoid.hello;

import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback, Camera.PictureCallback,  Camera.ShutterCallback {
	SurfaceHolder mHolder;
    Camera mCamera;
    Bitmap mPhoto;
    Context mPictureCallback;

    /**
     * Constructs a CameraPreview based on inflation from XML
     */
    public CameraPreview(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initCameraPreview();
    }

    public CameraPreview(Context context, AttributeSet attrs) {
        super(context, attrs);
        initCameraPreview();        
    }
    
    private void initCameraPreview() {
        // Install a SurfaceHolder.Callback so we get notified when the
        // underlying surface is created and destroyed.
        mHolder = getHolder();
        mHolder.addCallback(this);
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    public void surfaceCreated(SurfaceHolder holder) {
        // The Surface has been created, acquire the camera and tell it where
        // to draw.
        mCamera = Camera.open();
        try {
           mCamera.setPreviewDisplay(holder);
        } catch (IOException exception) {
            mCamera.release();
            mCamera = null;
            // TODO: add more exception handling logic here
        }
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        // Surface will be destroyed when we return, so stop the preview.
        // Because the CameraDevice object is not a shared resource, it's very
        // important to release it when the activity is paused.
        mCamera.stopPreview();
        mCamera.release();
        mCamera = null;
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
        // Now that the size is known, set up the camera parameters and begin
        // the preview.
        //Camera.Parameters parameters = mCamera.getParameters();

        mCamera.startPreview();
    }

    // Invoked when Camera.takePicture() is called. Got Bitmap.
	public void onPictureTaken(byte[] data, Camera camera) {
		
	}
	
    // We add a public API here to take a picture.
    public void takePicture() {
    	// Call Camera.takePicture() to take a picture actually.
    	if (mCamera == null) {
    		mCamera = Camera.open();
    	}
    	
    	// If we can't get a registered Callback, make ourself to it.
    	if (mPictureCallback == null) {
    		mCamera.takePicture(this, this, this);
    	} else {
    	    mCamera.takePicture(this, this, (PictureCallback) mPictureCallback);
    	}
    }

	public void onShutter() {
	}
	
    /**
     * Reigster a PictureCall back.
     * 
     * @param cb Object who want to get JPEG raw data.
     */
	public void setPictureCallback(Context cb) {
		mPictureCallback = cb;
	}
}
