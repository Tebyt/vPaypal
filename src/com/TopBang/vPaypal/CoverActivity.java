package com.TopBang.vPaypal;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.Tebyt.payment.R;
/**
 * 作者：TopBang
 * 时间:2013.08
 * 功能：打开软件出现的封面GUI
 * */
public class CoverActivity extends Activity {

	private Timer timer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cover);
		timer = new Timer();
		timer.schedule(new MyTask(), 1500);

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		this.finish();
		super.onPause();
	}

	private class MyTask extends TimerTask {
		@Override
		public void run() {
			Intent intent = new Intent();
			intent.setClass(CoverActivity.this, LogActivity.class);
			startActivity(intent);
		}
	}
}
