package com.mokoid.hello;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HelloMoko extends Activity implements 
			View.OnClickListener {
		
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); 
        setContentView(R.layout.main);       
               
        Button btn = (Button)findViewById(R.id.btn);
        btn.setOnClickListener(this);  
        
        TextView tv=(TextView)findViewById(R.id.tv);
        tv.setOnClickListener(this);
    }
    
    public void onClick(View v) {
        Display display = getWindowManager().getDefaultDisplay();
        Toast.makeText(this, display.getWidth() + "x" + display.getHeight(), Toast.LENGTH_LONG).show();
    }
    
    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflate = getMenuInflater();
    	inflate.inflate(R.menu.options_menu, menu);
		return true;    	
    }
    
    public boolean onOptionsItemSelected(MenuItem item) {
    	int item_id = item.getItemId();
    	switch (item_id) {
		    	case R.id.item_1:
		    			AlertDialog.Builder builder = new AlertDialog.Builder(this);
		    			
		    			builder.setMessage("Are you ready to Quit ?");
		    			builder.setCancelable(false);
		    			
		    			builder.setPositiveButton("Yes",
		    						new DialogInterface.OnClickListener() {
		    							public void onClick(DialogInterface dialog, int id) {
		    								finish();
		    							}
		    						}
		    			);
		    			
		    			builder.setNegativeButton("No",
	    						new DialogInterface.OnClickListener() {
	    							public void onClick(DialogInterface dialog, int id) {
	    							}
	    						}
		    			);		    			
		    			
		    			AlertDialog alert = builder.create();
		    			alert.show();
		    			break;	    
		    	case R.id.item_2:
		    			Intent cm1 = new Intent("com.moko.hello.CAMERA");
		    			startActivity(cm1);
		    			break;		
		    	case R.id.item_3:
	    			Intent cm2 = new Intent("com.moko.hello.CAMERA_BTN");
	    			startActivity(cm2);
	    			break;	
    			default: return false;
    	}
    	return true;
    }
}