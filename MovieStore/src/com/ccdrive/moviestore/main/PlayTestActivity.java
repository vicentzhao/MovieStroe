package com.ccdrive.moviestore.main;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.MediaPlayer.OnCompletionListener;
import io.vov.vitamio.MediaPlayer.OnInfoListener;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.widget.Toast;

import com.ccdrive.moviestore.R;

public class PlayTestActivity extends Activity{
	private boolean isplay;
	private boolean needResume;
	 VideoView mVideoView;
	  @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		if (!io.vov.vitamio.LibsChecker.checkVitamioLibs(this))
			return;
		setContentView(R.layout.videoview);
		 String ms = "http://192.168.1.32:8080/index/download.action?token=908d05b6-15ee-4b0a-8aa8-be639ed4332c|k212F6D6E742F6469676974616C2F6D75736963736F757263652F31332F30332F32302F3133363337343430343030383730303030312E6D7034|k21E681B0E4BCBCE4BDA0E79A84E6B8A9E69F9420";

		Intent intent = getIntent();
		String path=intent.getStringExtra("path");
		
		mVideoView = (VideoView)findViewById(R.id.surface_view);
		mVideoView.setVideoPath(path);
		mVideoView.setVideoQuality(io.vov.vitamio.MediaPlayer.VIDEOQUALITY_HIGH);
		mVideoView.setMediaController(new MediaController(PlayTestActivity.this));
		mVideoView.setBufferSize(4024);
		
//		    
//			 this.setOnKeyListener(new OnKeyListener() {
//				@Override
//				public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
//					// TODO Auto-generated method stub
//					if(keyCode==KeyEvent.KEYCODE_BACK){
//						System.out.println("监听到返回键");
//						if(isplay){
//							if(mVideoView!=null){
//								mVideoView.stopPlayback();
//							}
//						}
//						  return false;
//					}
//					return false;
//				}
//			});
		final ProgressDialog Dialog = ProgressDialog.show(PlayTestActivity.this, "正在加载。。", "正在加载请稍后。。");
		Dialog.setOnKeyListener(new OnKeyListener() {
			
			@Override
			public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
				  if(keyCode == KeyEvent.KEYCODE_BACK){
					  PlayTestActivity.this.finish();
//		              if(!isaExit){
//		                   isaExit=true;
//		                   Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
//		                   aHandler.sendEmptyMessageDelayed(0, 2000);
//		              }
//		              else{
//		              }
		         }
				return false;
			}
		});
		 mVideoView.setOnCompletionListener(new OnCompletionListener() {
			
			@Override
			public void onCompletion(MediaPlayer arg0) {
				finish();
				
			}
		});
			 mVideoView.setOnInfoListener(new OnInfoListener() {
				@Override
				public boolean onInfo(io.vov.vitamio.MediaPlayer arg0, int arg1, int arg2) {
					System.out.println("下载的速度为======"+arg2);
					switch (arg1) {
			        case io.vov.vitamio.MediaPlayer.MEDIA_INFO_BUFFERING_START:
			            //开始缓存，暂停播放
			            if (mVideoView.isPlaying()) {
			            	mVideoView.pause();
			                needResume = true;
			            }
			           Dialog.show();
			            break;
			        case io.vov.vitamio.MediaPlayer.MEDIA_INFO_BUFFERING_END:
			            //缓存完成，继续播放
			            if (needResume)
			            	mVideoView.start();
			            Dialog.dismiss();
			            break;
			        case io.vov.vitamio.MediaPlayer.MEDIA_INFO_DOWNLOAD_RATE_CHANGED:
			            //显示 下载速度
			            System.out.println("下载的速度===="+arg2);
			            break;
			        }
			        return true;
				}
			});
	}
	  boolean isaExit=false;
		Handler aHandler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				isaExit=false;
			}
		};
	boolean isExit=false;
	Handler mHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			isExit=false;
		}
	};
	/**
	 * 捕捉回退键
	 */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
         if(keyCode == KeyEvent.KEYCODE_BACK){
              if(!isExit){
                   isExit=true;
                   Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                   mHandler.sendEmptyMessageDelayed(0, 2000);
              }
              else{
                   PlayTestActivity.this.finish();
              }
         }
         return false;
    }
}
