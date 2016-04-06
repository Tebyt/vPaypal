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
 * 作者：TopBang
 * 时间:2013.08
 * 功能：定位界面GUI
 * */
public class LocationActivity extends Activity {
	// 获取地理位置并显示（采用百度定位api3.3）

	private Vibrator mVibrator01 = null;
	private LocationClient mLocClient;
	public Button next;  //下一步
	public TextView location;  //位置信息显示
	private Intent intent;
	private Intent i;
	private String source;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_location);
		setTitle("地理位置定位");

		i = getIntent();
		source = i.getStringExtra("source");
		intent = new Intent();
		intent.putExtra("source", source);

		// 绑定控件
		location = (TextView) findViewById(R.id.textLocation);
		next = (Button) findViewById(R.id.buttonLocation1);

		// 定位信息初始化
		mLocClient = ((Location) getApplication()).mLocationClient;
		((Location) getApplication()).mTv = location;
		mVibrator01 = (Vibrator) getApplication().getSystemService(
				Service.VIBRATOR_SERVICE);
		((Location) getApplication()).mVibrator01 = mVibrator01;

		setLocationOption();
		mLocClient.start();

		// “下一步”Button
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
		option.setAddrType("all");// 返回的定位结果包含地址信息
		option.setCoorType("gcj02");// 返回的定位结果是百度经纬度,默认值gcj02
		option.setScanSpan(2000);// 设置发起定位请求的间隔时间为5000ms
		option.disableCache(true);// 禁止启用缓存定位
		option.setPriority(LocationClientOption.GpsFirst);
		option.setPoiNumber(5); // 最多返回POI个数
		option.setPoiDistance(1000); // poi查询距离
		option.setPoiExtraInfo(true); // 是否需要POI的电话和地址等详细信息
		mLocClient.setLocOption(option);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub

		this.finish();
		super.onPause();
	}
}