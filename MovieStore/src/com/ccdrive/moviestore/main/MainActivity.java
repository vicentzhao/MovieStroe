package com.ccdrive.moviestore.main;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.ccdrive.moviestore.R;

public class MainActivity  extends Activity{
	
	 @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
		setContentView(R.layout.splash); 
		PackageManager pm = getPackageManager();
		try {
		PackageInfo pi = pm.getPackageInfo("com.lyt.android", 0);
		TextView versionNumber = (TextView) findViewById(R.id.versionNumber);
		versionNumber.setText("Version " + pi.versionName);
		} catch (NameNotFoundException e) {
		e.printStackTrace();
		}

		new Handler().postDelayed(new Runnable(){

		@Override
		public void run() {
		Intent intent = new Intent(MainActivity.this,MainActivity1.class);
		startActivity(intent);
		MainActivity.this.finish();
		}

		}, 2500);
	}

}
