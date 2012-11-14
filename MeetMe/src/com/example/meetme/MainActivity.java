package com.example.meetme;

import com.example.meetme.network.RequestTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	Intent mapActivityIntent;
	EditText myStatus;

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mapActivityIntent = new Intent(this,MapsActivity.class);
        myStatus = (EditText) findViewById(R.id.editText_myStatus);
        
        
        
        Button shareButton = (Button) findViewById(R.id.button_share_location);
        shareButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				mapActivityIntent.putExtra("MyStatus", myStatus.getText().toString());		
				startActivity(mapActivityIntent);
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    
}
