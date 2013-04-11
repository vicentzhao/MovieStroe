package com.ccdrive.moviestore.main;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.ccdrive.moviestore.R;
import com.ccdrive.moviestore.http.HttpRequest;

public class LoginActivity extends Activity {
	SharedPreferences sharedPreferences;
	String tempPath ="http://192.168.1.32:8080/login.action?username=tasss123&password=123456&type=2&driveid=11&mac=11";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		final Intent  i = new Intent(LoginActivity.this,MainActivity1.class);
		sharedPreferences = getSharedPreferences("userInfo", LoginActivity.MODE_WORLD_READABLE);
		 String userInfo =  sharedPreferences.getString("userInfo", "");
		if(userInfo.length()!=0){
			startActivity(i);
	     	 finish();
	         }
        setContentView(R.layout.login);
        final AQuery  aQuery = new AQuery(LoginActivity.this);
        EditText eName =(EditText) findViewById(R.id.login_username);
        EditText ePassword =(EditText) findViewById(R.id.login_pas);
        Button  btn_login=(Button) findViewById(R.id.login);
        Button btn_cancle=(Button) findViewById(R.id.cancel);
        String name = eName.getText().toString().trim();
        String password=ePassword.getText().toString().trim();
        btn_login.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				final ProgressDialog pd = new ProgressDialog(LoginActivity.this);
				pd.setMessage("正在登陆");
				pd.show();
				aQuery.ajax(tempPath, String.class, new AjaxCallback<String>() {

					@Override
					public void callback(String url, String object,
							AjaxStatus status) {
						if(object!=null){
							pd.dismiss();
							String ss ="\"fasle\"";
						if(!ss.equals(object)){
							SharedPreferences.Editor editor = sharedPreferences.edit();
							editor.putString("userInfo", "tasss123"+","+"123456");
							editor.commit();
							startActivity(i);
							finish();
						}else{
							Toast.makeText(aQuery.getContext(), "用户名或密码错误，请重试", 1).show();
						}
					}
					else {
						pd.dismiss();
						Toast.makeText(aQuery.getContext(), "网络错误，请重试", 1).show();
					}
						
					}
					 
				});
			}
		});

        btn_cancle.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
        
        
	}
}

