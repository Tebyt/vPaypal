package com.TopBang.vPaypal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.Tebyt.payment.R;
/**
 * ���ߣ�TopBang
 * ʱ��:2013.08
 * ���ܣ�������ȡ����GUI
 * */
public class VoiceActivity extends Activity {

	private ExtAudioRecorder recorder;  //¼����
	private Button record;  //¼����ť
	private TextView prompt;  //��ʾ����
	private int time = 1; // �ڼ���¼��
	String filename = "test.wav";
	String dir = Environment.getExternalStorageDirectory().getPath();
	String location = dir + "/" + filename;  //wav�ļ����λ��
	private String source;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_voice);

		Intent i = getIntent();
		source = i.getStringExtra("source");
		// ¼����ť
		record = (Button) findViewById(R.id.record);
		// ��ʾ����
		prompt = (TextView) findViewById(R.id.prompt);
		if (source.equals("Check")) {
			setTitle("������Ϣ¼��");
			record.setOnTouchListener(new RegisterListener());
			prompt.setText("¼����������");
		} else if (source.equals("Log")) {
			setTitle("������Ϣ��֤");
			record.setOnTouchListener(new LogInListener());
			prompt.setText("��֤��������");
		}
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
			Intent intent = new Intent();
			intent.setClass(VoiceActivity.this, LogActivity.class);
			startActivity(intent);
			return true;
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}

	class RegisterListener implements OnTouchListener {
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN: {
				// ��ס�¼�������ִ�д��������
				recorder = ExtAudioRecorder.getInstanse(false);
				recorder.record(location);

				return true;
			}
			case MotionEvent.ACTION_UP: {
				// �ɿ��¼�������ִ�д��������
				recorder.stopRecord();
				switch (time) {
				case 1:
					Toast.makeText(VoiceActivity.this, "��һ��¼��ɹ���", 500).show();
					time++;
					break;
				case 2:
					Toast.makeText(VoiceActivity.this, "�ڶ���¼��ɹ���", 500).show();
					time++;
					break;
				case 3:
					Toast.makeText(VoiceActivity.this, "������Ϣ¼��ɹ���", 500).show();
					Intent intent = new Intent();
					intent.setClass(VoiceActivity.this, BindActivity.class);
					startActivity(intent);
					time = 1;
					break;
				}
			}
			default:
				return false;
			}
		}
	}

	class LogInListener implements OnTouchListener {
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN: {
				// ��ס�¼�������ִ�д��������
				recorder = ExtAudioRecorder.getInstanse(false);
				recorder.record(location);

				return true;
			}
			case MotionEvent.ACTION_UP: {
				// �ɿ��¼�������ִ�д��������
				Toast.makeText(VoiceActivity.this, "ʶ��ɹ���", Toast.LENGTH_SHORT)
						.show();
				recorder.stopRecord();
				Intent intent = new Intent();
				intent.putExtra("source", "Voice");
				intent.setClass(VoiceActivity.this, CheckActivity.class);
				startActivity(intent);
				return true;
			}
			default:
				return false;
			}
		}
	}

}
