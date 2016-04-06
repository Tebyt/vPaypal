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
 * 作者：TopBang
 * 时间:2013.08
 * 功能：声纹提取界面GUI
 * */
public class VoiceActivity extends Activity {

	private ExtAudioRecorder recorder;  //录音机
	private Button record;  //录音按钮
	private TextView prompt;  //提示文字
	private int time = 1; // 第几次录制
	String filename = "test.wav";
	String dir = Environment.getExternalStorageDirectory().getPath();
	String location = dir + "/" + filename;  //wav文件存放位置
	private String source;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_voice);

		Intent i = getIntent();
		source = i.getStringExtra("source");
		// 录音按钮
		record = (Button) findViewById(R.id.record);
		// 提示文字
		prompt = (TextView) findViewById(R.id.prompt);
		if (source.equals("Check")) {
			setTitle("声纹信息录入");
			record.setOnTouchListener(new RegisterListener());
			prompt.setText("录入您的声音");
		} else if (source.equals("Log")) {
			setTitle("声纹信息验证");
			record.setOnTouchListener(new LogInListener());
			prompt.setText("验证您的声音");
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
				// 按住事件发生后执行代码的区域
				recorder = ExtAudioRecorder.getInstanse(false);
				recorder.record(location);

				return true;
			}
			case MotionEvent.ACTION_UP: {
				// 松开事件发生后执行代码的区域
				recorder.stopRecord();
				switch (time) {
				case 1:
					Toast.makeText(VoiceActivity.this, "第一次录入成功！", 500).show();
					time++;
					break;
				case 2:
					Toast.makeText(VoiceActivity.this, "第二次录入成功！", 500).show();
					time++;
					break;
				case 3:
					Toast.makeText(VoiceActivity.this, "声纹信息录入成功！", 500).show();
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
				// 按住事件发生后执行代码的区域
				recorder = ExtAudioRecorder.getInstanse(false);
				recorder.record(location);

				return true;
			}
			case MotionEvent.ACTION_UP: {
				// 松开事件发生后执行代码的区域
				Toast.makeText(VoiceActivity.this, "识别成功！", Toast.LENGTH_SHORT)
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
