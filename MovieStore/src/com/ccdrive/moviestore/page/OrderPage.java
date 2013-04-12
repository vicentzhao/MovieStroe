package com.ccdrive.moviestore.page;

import java.util.ArrayList;

import com.androidquery.AQuery;
import com.ccdrive.moviestore.R;
import com.ccdrive.moviestore.bean.OrderBean;
import com.ccdrive.moviestore.main.MainActivity1;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class OrderPage {
	
	
	private Context  mContext ;
	private static LayoutInflater inflater;
	private ArrayList<OrderBean> orderList;
	private Dialog builder ;
	private AQuery aQuery;
	
	//orderInfo id
	private static int[] orderInfoItems = { R.id.order_main_item01, R.id.order_main_item02,
		R.id.order_main_item03, R.id.order_main_item04, R.id.order_main_item05,
		R.id.order_main_item06, R.id.order_main_item07, R.id.order_main_item08,
		R.id.order_main_item09, R.id.order_main_item10 };
	
	//dele id 
	private static int[] horItems = { R.id.order_main_item01, R.id.order_main_item02,
		R.id.order_main_item03, R.id.order_main_item04, R.id.order_main_item05,
		R.id.order_main_item06, R.id.order_main_item07, R.id.order_main_item08,
		R.id.order_main_item09, R.id.order_main_item10 };

	public OrderPage(Context context) {
		this.mContext = context;
		inflater =LayoutInflater.from(context);
		builder=new Dialog(context);
		aQuery =new AQuery(context);
	}
	 
	 public  void setOrderPage(){
		 
		 
		 View orderPageView = inflater.inflate(R.layout.order_jay, null);
		 ProgressDialog Dialog = ProgressDialog.show(aQuery.getContext(),
					"缓冲中。。", "正在加载数据请稍后。。");
		    ProDiaglogDimiss(Dialog);
			builder.setContentView(orderPageView);
			Window dialogWindow = builder.getWindow();
			WindowManager.LayoutParams lp = dialogWindow.getAttributes();
			dialogWindow.setGravity(Gravity.CENTER);
			lp.width = 800;
			lp.height = 640;
			dialogWindow.setAttributes(lp);
			Dialog.show();
	 }
	  public static void DiaglogDimiss(Dialog dialog){
		  dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
				@Override
				public boolean onKey(DialogInterface dialog, int keyCode,
						KeyEvent event) {
					 if (keyCode == KeyEvent.KEYCODE_BACK){
					          // Perform action on key press
						 dialog.dismiss();
					          return true;
					        }
					return false;
				}
			});
	  }
	  public static void ProDiaglogDimiss(ProgressDialog dialog){
		  dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
			  @Override
			  public boolean onKey(DialogInterface dialog, int keyCode,
					  KeyEvent event) {
				  if (keyCode == KeyEvent.KEYCODE_BACK){
					  // Perform action on key press
					  dialog.dismiss();
					  return true;
				  }
				  return false;
			  }
		  });
	  }
}
