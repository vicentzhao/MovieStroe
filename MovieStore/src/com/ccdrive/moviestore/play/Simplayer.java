package com.ccdrive.moviestore.play;

import android.app.Activity;
import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

import com.ccdrive.moviestore.R;

public class Simplayer extends Activity { 

@Override
protected void onCreate(Bundle savedInstanceState) { 
super.onCreate(savedInstanceState); 
setContentView(R.layout.videodemo); 

ProgressDialog Dialog = ProgressDialog.show(Simplayer.this, "正在加载。。", "正在加载请稍后。。");
VideoView videoView = (VideoView)findViewById(R.id.videoView1); 
/*** 
* 将播放器关联上一个音频或者视频文件 
* videoView.setVideoURI(Uri uri) 
* videoView.setVideoPath(String path) 
* 以上两个方法都可以。 
*/
Dialog.show();
String path =getIntent().getStringExtra("path");
videoView.setVideoPath(path); 
videoView.start();
Dialog.dismiss();
/** 
* w为其提供一个控制器，控制其暂停、播放……等功能 
*/

videoView.setMediaController(new MediaController(this)); 

/** 
* 视频或者音频到结尾时触发的方法 
*/
videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { 
	
@Override
public void onCompletion(MediaPlayer mp) { 
Log.i("通知", "完成"); 
finish();
} 
}); 



videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() { 

@Override
public boolean onError(MediaPlayer mp, int what, int extra) { 
Log.i("通知", "播放中出现错误"); 
return false; 
} 
}); 

} 
} 