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
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

// ----------------------------------------------------------------------

public class CameraBtnApp extends Activity implements View.OnClickListener {
	private CameraButton mCameraButton;
	private Animation mPush;
	
    @Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide the window title.
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        // Use UI inflation instead.
        setContentView(R.layout.camera_button);
                
        mCameraButton = (CameraButton)findViewById(R.id.camera_btn);
        mCameraButton.setOnClickListener(this);  
        
        // Load Animation
        mPush = AnimationUtils.loadAnimation(this, R.anim.push_up_in);
    }

	public void onClick(View v) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "Dial...", Toast.LENGTH_LONG).show();
		mCameraButton.startAnimation(mPush);
	}
}