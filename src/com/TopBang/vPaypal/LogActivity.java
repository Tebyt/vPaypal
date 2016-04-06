package com.TopBang.vPaypal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.Tebyt.payment.R;
/**
 * 作者：TopBang
 * 时间:2013.08
 * 功能：登录界面GUI
 * */
public class LogActivity extends Activity {

	private Button log;  //登录按钮
	private Button register;  //注册按钮
	private EditText phone;  //手机号

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_log);
		// 登陆按钮
		log = (Button) findViewById(R.id.log);
		log.setOnClickListener(new LogListener());
		// 注册按钮
		register = (Button) findViewById(R.id.register);
		register.setOnClickListener(new RegisterListener());

		phone = (EditText) findViewById(R.id.phone);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		this.finish();
		super.onPause();
	}

	class LogListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			if (phone.getText().length() < 11) {
				Toast.makeText(LogActivity.this, "请正确输入手机号", 500).show();
			} else {
				Intent intent = new Intent();
				intent.putExtra("source", "Log");
				intent.setClass(LogActivity.this, VoiceActivity.class);
				startActivity(intent);
			}
		}

	}

	class RegisterListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			if (phone.getText().length() < 11) {
				Toast.makeText(LogActivity.this, "请正确输入手机号", 500).show();
			} else {
				Intent intent = new Intent();
				intent.putExtra("source", "Log");
				intent.setClass(LogActivity.this, CheckActivity.class);
				startActivity(intent);
			}
		}

	}

}
