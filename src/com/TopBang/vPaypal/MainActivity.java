package com.TopBang.vPaypal;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.Tebyt.payment.R;
/**
 * 作者：TopBang
 * 时间:2013.08
 * 功能：主界面（交易记录）GUI
 * */
public class MainActivity extends Activity {
	private Button pay;  //转账
	private Button exit;  //退出
	private Button manage;  //管理

	private String[] text;
	private int index;

	private LinearLayout mLayout;

	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setTitle("个人中心");
		pay = (Button) findViewById(R.id.pay);
		pay.setOnClickListener(new PayListener());
		exit = (Button) findViewById(R.id.exit);
		exit.setOnClickListener(new ExitListener());
		manage = (Button) findViewById(R.id.manage);
		manage.setOnClickListener(new ManageListener());
		
		Intent i = getIntent();
		text = i.getStringArrayExtra("text");
		index = i.getIntExtra("index", 0);

		mLayout = (LinearLayout) this.findViewById(R.id.LinearLayout);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		params.topMargin = 20;
		for (int j = 0; j < index; j++) {
			TextView tView = new TextView(MainActivity.this);
			tView.setText(text[j]);
			tView.setTextSize(20);
			mLayout.addView(tView, params);// 添加一个TextView控件
		}
		intent = new Intent();
		intent.putExtra("source", "Main");
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		finish();
		super.onPause();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			new AlertDialog.Builder(this)
					.setMessage("确认退出vPaypal？")
					.setNegativeButton("取消",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
								}
							})
					.setPositiveButton("确定",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									finish();
								}
							}).show();

			return true;
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}

	class PayListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			intent.setClass(MainActivity.this, LocationActivity.class);
			intent.putExtra("text", text);
			intent.putExtra("index", index);
			startActivity(intent);
		}
	}
	
	class ManageListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			intent.setClass(MainActivity.this, ManageActivity.class);
			intent.putExtra("text", text);
			intent.putExtra("index", index);
			startActivity(intent);
		}
	}

	class ExitListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			new AlertDialog.Builder(MainActivity.this)
					.setMessage("确认退出vPaypal？")
					.setNegativeButton("取消",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
								}
							})
					.setPositiveButton("确定",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									finish();
								}
							}).show();
		}
	}
}
