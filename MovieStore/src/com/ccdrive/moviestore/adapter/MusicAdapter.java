package com.ccdrive.moviestore.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.ccdrive.moviestore.R;
import com.ccdrive.moviestore.bean.Music;
import com.ccdrive.moviestore.content.Constant;
import com.ccdrive.moviestore.http.HttpRequest;
import com.ccdrive.moviestore.main.MainActivity1;

public class MusicAdapter extends BaseAdapter {
	private Context mContext;
	ArrayList<Music> list;
	private LayoutInflater inflater;
	private int isWhatRight;

	public MusicAdapter(Context c, ArrayList<Music> list,int iswhat) {
		mContext = c;
		this.list = list;
		inflater = LayoutInflater.from(mContext);
		isWhatRight =iswhat;
	}

	public int getCount() {
		return list.size();
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.musicdetial_list_item, null);
			holder.name = (TextView) convertView
					.findViewById(R.id.musicchartername);
			holder.btn_play = (Button) convertView
			        .findViewById(R.id.btn_music_play);
//			holder.count = (TextView) convertView
//			         .findViewById(R.id.count_tv);
			holder.btn_down = (Button) convertView
			        .findViewById(R.id.btn_music_down);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.name.setText(list.get(position).getName());
		if(isWhatRight==Constant.MUSICSTORE){
			holder.btn_down.setVisibility(View.GONE);
		}
		holder.btn_play.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String appDownPathtrue  =HttpRequest.URL_QUERY_DOWNLOAD_URL+list.get(position).getDownload_path();
			}
		});
		holder.btn_down.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MainActivity1.setMusicDown(list.get(position).getId());
			}
		});
		return convertView;
	}

	class ViewHolder {
		TextView name;
		Button   btn_play;
//		TextView count;
		Button  btn_down;
	}
}