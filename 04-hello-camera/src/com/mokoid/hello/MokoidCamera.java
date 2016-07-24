/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mokoid.hello;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.view.View;

// ----------------------------------------------------------------------

public class MokoidCamera extends Activity implements View.OnClickListener, Camera.PictureCallback {
	private CameraPreview mCameraPreview;
	private ImageView mImageView;
	private BitmapDrawable mBitmapDrawable;
	private Bitmap mBitmap;
	private Resources mResource;

    @Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide the window title.
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        // Use UI inflation instead.
        setContentView(R.layout.camera);
        
        Button btn = (Button)findViewById(R.id.btn);
        btn.setOnClickListener(this);
        
        mImageView = (ImageView)findViewById(R.id.img);
        
        // Find CameraPreview object.
        mCameraPreview = (CameraPreview)findViewById(R.id.camera);
        
        // Register PictureCallback to get JPEG raw.
        mCameraPreview.setPictureCallback(this);
        
        // Get our resource.
        mResource = getResources();
    }

	public void onClick(View v) {
		// TODO Auto-generated method stub
		mCameraPreview.takePicture();
	}

	// Got JPEG.
	public void onPictureTaken(byte[] data, Camera camera) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "Captured:" + data.length, Toast.LENGTH_LONG).show();
		
		// BUG We can't do this due to the implementation of this API.
		//mImageView.setImageBitmap(BitmapFactory.decodeByteArray(data, 0, data.length));
		// Using BitmapFactory to transform byte[] into Bitmap
		
        // Get an instance of BitmapDrawable.
		// We will need just one instance. A ImageView can only has one BitmapDrawable.
		// TODO Make singleton pattern here.
		if (mBitmapDrawable == null) {
			/**
			 * Static object is not allowed to create more than one instance in the heap.
			 */
			mBitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
            mBitmapDrawable = new BitmapDrawable(mResource, mBitmap);
    		mImageView.setImageDrawable(mBitmapDrawable);
		} else {
			/**
			 * When a object is a static object, it need to be recycled before it can be instanced again.
			 */
            mBitmap.recycle();
            
            /**
             * A static object will be recycled by the system (automatically), or it can
             *   be recycled manually. So, a recycle() API should be made it the design.
             */
            	
            /**
             * However, this is not a effience way to re-draw ImageView.
             * ImageView contains one BitmapDrawable, but we create several BitmapDrawable objects.
             */
			mBitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
            mBitmapDrawable = new BitmapDrawable(mResource, mBitmap);
    		mImageView.setImageDrawable(mBitmapDrawable);            
		}
		
		// Continue preview.
		camera.startPreview();
	}
}