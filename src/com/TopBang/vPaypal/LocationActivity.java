package com.TopBang.vPaypal;

import com.Tebyt.payment.R;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
/**
 * ���ߣ�TopBang
 * ʱ��:2013.08
 * ���ܣ���λ����GUI
 * */
public class LocationActivity extends Activity {
	// ��ȡ����λ�ò���ʾ�����ðٶȶ�λapi3.3��

	private Vibrator mVibrator01 = null;
	private LocationClient mLocClient;
	public Button next;  //��һ��
	public TextView location;  //λ����Ϣ��ʾ
	private Intent intent;
	private Intent i;
	private String source;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_location);
		setTitle("����λ�ö�λ");

		i = getIntent();
		source = i.getStringExtra("source");
		intent = new Intent();
		intent.putExtra("source", source);

		// �󶨿ؼ�
		location = (TextView) findViewById(R.id.textLocation);
		next = (Button) findViewById(R.id.buttonLocation1);

		// ��λ��Ϣ��ʼ��
		mLocClient = ((Location) getApplication()).mLocationClient;
		((Location) getApplication()).mTv = location;
		mVibrator01 = (Vibrator) getApplication().getSystemService(
				Service.VIBRATOR_SERVICE);
		((Location) getApplication()).mVibrator01 = mVibrator01;

		setLocationOption();
		mLocClient.start();

		// ����һ����Button
		next.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mLocClient.stop();

				if (source.equals("Main")) {
					intent.putExtra("text", i.getStringArrayExtra("text"));
					intent.putExtra("index", i.getIntExtra("index", 0));
				}
				intent.setClass(LocationActivity.this, PayActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (source.equals("Main")) {
				intent.putExtra("text", i.getStringArrayExtra("text"));
				intent.putExtra("index", i.getIntExtra("index", 0));
			}
			intent.setClass(LocationActivity.this, MainActivity.class);
			startActivity(intent);
			return true;
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}

	protected void setLocationOption() {
		// TODO Auto-generated method stub
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true);
		option.setAddrType("all");// ���صĶ�λ���������ַ��Ϣ
		option.setCoorType("gcj02");// ���صĶ�λ����ǰٶȾ�γ��,Ĭ��ֵgcj02
		option.setScanSpan(2000);// ���÷���λ����ļ��ʱ��Ϊ5000ms
		option.disableCache(true);// ��ֹ���û��涨λ
		option.setPriority(LocationClientOption.GpsFirst);
		option.setPoiNumber(5); // ��෵��POI����
		option.setPoiDistance(1000); // poi��ѯ����
		option.setPoiExtraInfo(true); // �Ƿ���ҪPOI�ĵ绰�͵�ַ����ϸ��Ϣ
		mLocClient.setLocOption(option);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub

		this.finish();
		super.onPause();
	}
}