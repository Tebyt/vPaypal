package com.TopBang.vPaypal;

import com.baidu.location.*;

import android.app.Application;
import android.util.Log;
import android.widget.TextView;
import android.os.Process;
import android.os.Vibrator;
/**
 * ���ߣ�TopBang
 * ʱ��:2013.08
 * ���ܣ���λʵ��ʵ����
 * */
public class Location extends Application {

	public LocationClient mLocationClient = null;
	private String mData;  
	public MyLocationListenner myListener = new MyLocationListenner();
	public TextView mTv;
	public NotifyLister mNotifyer=null;
	public Vibrator mVibrator01;
	public static String TAG = "LocTestDemo";
	
	@Override
	public void onCreate() {
		mLocationClient = new LocationClient( this );
		mLocationClient.registerLocationListener( myListener );
		super.onCreate(); 
		Log.d(TAG, "... Application onCreate... pid=" + Process.myPid());
	}
	
	/**
	 * ��ʾ�ַ���
	 * @param str
	 */
	public void logMsg(String str) {
		try {
			mData = str;
			if ( mTv != null )
				mTv.setText(mData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ��������������λ�õ�ʱ�򣬸�ʽ�����ַ������������Ļ��
	 */
	public class MyLocationListenner implements BDLocationListener {
		@Override
		public void onReceiveLocation(BDLocation location) {
			if (location == null)
				return ;
			StringBuffer sb = new StringBuffer(256);
			sb.append("ʱ�� : ");
			sb.append(location.getTime());
			sb.append("\nγ�� : ");
			sb.append(location.getLatitude());
			sb.append("\n���� : ");
			sb.append(location.getLongitude());
			sb.append("\n����(��) : ");
			sb.append(location.getRadius());
			if (location.getLocType() == BDLocation.TypeGpsLocation){
				sb.append("\n���� :GPS���Ƕ�λ");
				/*sb.append("\n�ٶ� : ");
				sb.append(location.getSpeed());
				sb.append("\n�������� : ");
				sb.append(location.getSatelliteNumber());*/
			} else if (location.getLocType() == BDLocation.TypeNetWorkLocation){
				sb.append("\n���� :���綨λ");
/*				sb.append("\nʡ��");
				sb.append(location.getProvince());
				sb.append("\n�У�");
				sb.append(location.getCity());
				sb.append("\n��/�أ�");
				sb.append(location.getDistrict());*/
			}
			sb.append("\n��ַ : ");
			sb.append(location.getAddrStr());
			logMsg(sb.toString());
			Log.i(TAG, sb.toString());
		}
		
		public void onReceivePoi(BDLocation poiLocation) {
			if (poiLocation == null){
				return ; 
			}
			StringBuffer sb = new StringBuffer(256);
			sb.append("Poi time : ");
			sb.append(poiLocation.getTime());
			sb.append("\nerror code : "); 
			sb.append(poiLocation.getLocType());
			sb.append("\nlatitude : ");
			sb.append(poiLocation.getLatitude());
			sb.append("\nlontitude : ");
			sb.append(poiLocation.getLongitude());
			sb.append("\nradius : ");
			sb.append(poiLocation.getRadius());
			if (poiLocation.getLocType() == BDLocation.TypeNetWorkLocation){
				sb.append("\naddr : ");
				sb.append(poiLocation.getAddrStr());
			} 
			if(poiLocation.hasPoi()){
				sb.append("\nPoi:");
				sb.append(poiLocation.getPoi());
			}else{				
				sb.append("noPoi information");
			}
			logMsg(sb.toString());
		}
	}
	
	public class NotifyLister extends BDNotifyListener{
		public void onNotify(BDLocation mlocation, float distance){
			mVibrator01.vibrate(1000);
		}
	}
}