package com.ccdrive.moviestore.main;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;

public class BaseActivity extends FragmentActivity {
	
	ProgressDialog pd;
	// 用户可见的时候 执行
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		pd = new ProgressDialog(this);
		SharedPreferences sp = getSharedPreferences("setting",
				Context.MODE_PRIVATE);
		super.onCreate(savedInstanceState);
	}
	// 打开进度条
	public void showProgress(RelativeLayout rl) {
		// 把进度对话框显示出来
		AlphaAnimation aa = new AlphaAnimation(0, 1);
		aa.setDuration(2000);
		rl.setAnimation(aa);
		rl.setAnimationCacheEnabled(false);
		rl.setVisibility(View.VISIBLE);
	}

	// 关闭进度条
	public void hideProgress(RelativeLayout rl) {
		AlphaAnimation aa = new AlphaAnimation(1, 0);
		aa.setDuration(2000);
		rl.setAnimation(aa);
		rl.setAnimationCacheEnabled(false);
		rl.setVisibility(View.INVISIBLE);
	}

	public void showProgressDialog(String message) {
		pd.setMessage(message);
		pd.show();
	}
	public void dismissProgressDialog() {
		pd.dismiss();
	}

}
