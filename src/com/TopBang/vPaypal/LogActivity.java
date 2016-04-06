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
 * ���ߣ�TopBang
 * ʱ��:2013.08
 * ���ܣ���¼����GUI
 * */
public class LogActivity extends Activity {

	private Button log;  //��¼��ť
	private Button register;  //ע�ᰴť
	private EditText phone;  //�ֻ���

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_log);
		// ��½��ť
		log = (Button) findViewById(R.id.log);
		log.setOnClickListener(new LogListener());
		// ע�ᰴť
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
				Toast.makeText(LogActivity.this, "����ȷ�����ֻ���", 500).show();
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
				Toast.makeText(LogActivity.this, "����ȷ�����ֻ���", 500).show();
			} else {
				Intent intent = new Intent();
				intent.putExtra("source", "Log");
				intent.setClass(LogActivity.this, CheckActivity.class);
				startActivity(intent);
			}
		}

	}

}
