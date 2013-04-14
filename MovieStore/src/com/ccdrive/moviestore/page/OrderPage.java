package com.ccdrive.moviestore.page;

import java.util.ArrayList;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.ccdrive.moviestore.R;
import com.ccdrive.moviestore.bean.OrderBean;
import com.ccdrive.moviestore.bean.PayOrderBean;
import com.ccdrive.moviestore.http.HttpRequest;
import com.ccdrive.moviestore.util.JsonUtil;

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
	private static int[] deleItems = { R.id.order_main_item01, R.id.order_main_item02,
		R.id.order_main_item03, R.id.order_main_item04, R.id.order_main_item05,
		R.id.order_main_item06, R.id.order_main_item07, R.id.order_main_item08,
		R.id.order_main_item09, R.id.order_main_item10 };
	
	// view id
	private static int[] lineItems = { R.id. view_order_line01, R.id.view_order_line02,
		R.id.view_order_line03, R.id.view_order_line04, R.id.view_order_line05,
		R.id.view_order_line06, R.id.view_order_line07, R.id.view_order_line08,
		R.id.view_order_line09, R.id.view_order_line10 };

	public OrderPage(Context context) {
		this.mContext = context;
		inflater =LayoutInflater.from(context);
		builder=new Dialog(context);
		aQuery =new AQuery(context);
	}
	 
	 public  void setOrderPage(){
		 
		 
		 final View orderPageView = inflater.inflate(R.layout.order_jay, null);
		 final ProgressDialog Dialog = ProgressDialog.show(aQuery.getContext(),
					"缓冲中。。", "正在加载数据请稍后。。");
		    ProDiaglogDimiss(Dialog);
		    builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
			builder.setContentView(orderPageView);
			Window dialogWindow = builder.getWindow();
			WindowManager.LayoutParams lp = dialogWindow.getAttributes();
			dialogWindow.setGravity(Gravity.CENTER);
			lp.width = 800;
			lp.height = 640;
			dialogWindow.setAttributes(lp);
			Dialog.show();
			String path =HttpRequest.URL_QUERY_LIST_ORDER;
			for (int i = 0; i < 10; i++) {
				orderPageView.findViewById(lineItems[i]).setVisibility(View.GONE);
			}
			aQuery.ajax(path, String.class, new AjaxCallback<String>() {// 这里的函数是一个内嵌函数如果是函数体比较复杂的话这种方法就不太合适了
				@Override
				public void callback(String url, String json,
						AjaxStatus status) {
					if (json != null) {
						Dialog.dismiss();
						builder.show();
						ArrayList<PayOrderBean> orderNumList = JsonUtil.getOrderNum(json);
						if(orderNumList.size()!=0){
							for (int i = 0; i < orderNumList.size(); i++) {
								orderPageView.findViewById(lineItems[i]).setVisibility(View.VISIBLE);
							TextView ductText=	(TextView) orderPageView.findViewById(orderInfoItems[i]).findViewById(R.id.order_duct);
							ductText.setText(orderNumList.get(i).getName());
							TextView numbText=	(TextView) orderPageView.findViewById(orderInfoItems[i]).findViewById(R.id.order_numb);
							numbText.setText(orderNumList.get(i).getNum());
							TextView priceText=	(TextView) orderPageView.findViewById(orderInfoItems[i]).findViewById(R.id.order_price);
							priceText.setText(orderNumList.get(i).getPrice());
							TextView kindText=	(TextView) orderPageView.findViewById(orderInfoItems[i]).findViewById(R.id.order_kind);
							kindText.setText(orderNumList.get(i).getKind());
							TextView dateText=	(TextView) orderPageView.findViewById(orderInfoItems[i]).findViewById(R.id.order_date);
							dateText.setText(orderNumList.get(i).getOrderDate());
							orderPageView.findViewById(deleItems[i]).setOnClickListener(new OnClickListener() {
								
								@Override
								public void onClick(View v) {
									// TODO Auto-generated method stub
									
								}
							});
							}
							
						}
						
					} else {
						Dialog.dismiss();
						if (status.getCode() == -101) {
							Toast.makeText(
									aQuery.getContext(),
									"Error:"
											+ status.getCode()
											+ aQuery.getContext()
													.getResources()
													.getString(
															R.string.checknetwork),
									Toast.LENGTH_LONG).show();
						} else {
							Toast.makeText(
									aQuery.getContext(),
									aQuery.getContext().getResources()
											.getString(R.string.nocontent),
									Toast.LENGTH_LONG).show();
						}
					}
				}
			});
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
