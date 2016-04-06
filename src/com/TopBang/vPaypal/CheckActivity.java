package com.TopBang.vPaypal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.Tebyt.payment.R;
/**
 * ���ߣ�TopBang
 * ʱ��:2013.08
 * ���ܣ���֤�����GUI
 * */
public class CheckActivity extends Activity implements Button.OnClickListener {

	private String source;
	private Intent i;
	private Intent intent;

	// ������֤����Ϣ
	protected static final int UPDATA_CHECKNUM = 0x101;

	CheckAction mCheckView;
	TextView mShowPassViwe;
	EditText mEditPass;
	Button mSubmit; //�ύ��֤��
	Button mRef;  //������֤��

	// ��֤�룺
	int[] checkNum = { 0, 0, 0, 0 };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_check);
		setTitle("��֤");
		initView();
		initCheckNum();
		i = getIntent();
		source = i.getStringExtra("source");
		intent = new Intent();
		intent.putExtra("source", "Check");
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		this.finish();
		super.onPause();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (source.equals("Log")) {
				intent.setClass(CheckActivity.this, LogActivity.class);
				startActivity(intent);
			} else if (source.equals("Voice")) {
				intent.setClass(CheckActivity.this, LogActivity.class);
				startActivity(intent);
			} else if (source.equals("Pay")) {
				intent.putExtra("text", i.getStringArrayExtra("text"));
				intent.putExtra("index", i.getIntExtra("index", 0));
				intent.setClass(CheckActivity.this, MainActivity.class);
				startActivity(intent);
			}
			return true;
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}

	public void initView() {
		mCheckView = (CheckView) findViewById(R.id.checkView);
		mEditPass = (EditText) findViewById(R.id.checkTest);
		mSubmit = (Button) findViewById(R.id.submit);
		mRef = (Button) findViewById(R.id.ref);

		mSubmit.setOnClickListener(this);
		mRef.setOnClickListener(this);
	}

	// ��ʼ����֤�벢��ˢ�½���
	public void initCheckNum() {
		checkNum = CheckGetUtil.getCheckNum();
		mCheckView.setCheckNum(checkNum);
		mCheckView.invaliChenkNum();
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.submit:
			String userInput = mEditPass.getText().toString();
			if (CheckGetUtil.checkNum(userInput, checkNum)) {
				if (source.equals("Log")) {
					Toast.makeText(CheckActivity.this, "��֤�ɹ���", 500).show();
					intent.setClass(CheckActivity.this, VoiceActivity.class);
					startActivity(intent);
				} else if (source.equals("Voice")) {
					Toast.makeText(CheckActivity.this, "��֤�ɹ���", 500).show();
					intent.setClass(CheckActivity.this, LocationActivity.class);
					startActivity(intent);
				} else if (source.equals("Pay")) {
					Toast.makeText(CheckActivity.this, "ת�˳ɹ���", 500).show();
					intent.putExtra("text", i.getStringArrayExtra("text"));
					intent.putExtra("index", i.getIntExtra("index", 0));
					intent.setClass(CheckActivity.this, MainActivity.class);
					startActivity(intent);
				}
			} else {
				Toast.makeText(this, "��֤ʧ��", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.ref:
			initCheckNum();
			break;
		default:
			break;
		}
	}

	public void onResume() {
		// ����ˢ����֤��
		new Thread(new myThread()).start();
		super.onResume();

	}

	class myThread implements Runnable {
		public void run() {
			while (!Thread.currentThread().isInterrupted()) {
				// ������Ϣ
				Message message = new Message();
				message.what = CheckActivity.UPDATA_CHECKNUM; // Hander�������ռ�

				CheckActivity.this.myHandler.sendMessage(message);
				try {
					// �߳�����
					Thread.sleep(ConmentConfig.PTEDE_TIME);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		}
	}

	Handler myHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case CheckActivity.UPDATA_CHECKNUM:
				mCheckView.invaliChenkNum();
				break;
			}
			super.handleMessage(msg);
		}
	};
}
