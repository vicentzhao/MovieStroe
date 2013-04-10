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

public class LoginActivity extends Activity {
	SharedPreferences sharedPreferences;
	String tempPath ="http://192.168.1.32:8080/login.action?username=tasss113&password=123456&type=2&driveid=11&mac=11";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        final AQuery  aQuery = new AQuery(LoginActivity.this);
        EditText eName =(EditText) findViewById(R.id.login_username);
        EditText ePassword =(EditText) findViewById(R.id.login_pas);
        Button  btn_login=(Button) findViewById(R.id.login);
        sharedPreferences = getSharedPreferences("userInfo", LoginActivity.MODE_WORLD_READABLE);
        String userInfo =  sharedPreferences.getString("userInfo", "");
        final Intent  i = new Intent(LoginActivity.this,MainActivity1.class);
//        if(userInfo!=null){
//        	String[] users = userInfo.split(",");
//            for (String str : users)
//            {
//              String name = users[0];
//              String password= users[1];
//            }
//        }
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
							System.out.println(object);
							pd.dismiss();
							Toast.makeText(aQuery.getContext(), object, 1).show();
							String ss ="'false'";
						if(!ss.equals(object)){
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
        
	}

}
