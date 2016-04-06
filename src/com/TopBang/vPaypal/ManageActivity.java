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
import android.widget.Toast;

import com.Tebyt.payment.R;
/**
 * ���ߣ�TopBang
 * ʱ��:2013.08
 * ���ܣ��������GUI
 * */
public class ManageActivity extends Activity {

	private Button history;  //���׼�¼��ѯ
	private Button delete;  //ɾ�����׼�¼
	private Button remind;  //����
	private Button back;  //����
	private Button logOut;  //�ǳ�

	private Intent intent;
	private Intent i;

	private String[] text;
	private int index;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manage);
		history = (Button) findViewById(R.id.history);
		history.setOnClickListener(new HistoryListener());
		delete = (Button) findViewById(R.id.delete);
		delete.setOnClickListener(new DeleteListener());
		back = (Button) findViewById(R.id.back1);
		back.setOnClickListener(new BackListener());
		logOut = (Button) findViewById(R.id.logOut);
		logOut.setOnClickListener(new LogOutListener());
		remind = (Button) findViewById(R.id.remind);
		remind.setOnClickListener(new RemindListener());

		intent = new Intent();
		intent.putExtra("source", "Manage");
		i = getIntent();
		text = i.getStringArrayExtra("text");
		index = i.getIntExtra("index", 0);
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
			intent.setClass(ManageActivity.this, MainActivity.class);
			intent.putExtra("text", text);
			intent.putExtra("index", index);
			startActivity(intent);
			return true;
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}

	class HistoryListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			Intent intent = new Intent();
			intent.setClass(ManageActivity.this, MainActivity.class);
			intent.putExtra("text", text);
			intent.putExtra("index", index);
			startActivity(intent);
		}

	}

	class DeleteListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			new AlertDialog.Builder(ManageActivity.this)
					.setMessage("ȷ��ɾ�����м�¼��")
					.setNegativeButton("ȡ��",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
								}
							})
					.setPositiveButton("ȷ��",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									text = new String[100];
									index = 0;
									Toast.makeText(ManageActivity.this,
											"ɾ���ɹ���", 500).show();
								}
							}).show();

		}

	}

	class RemindListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
		}

	}

	class LogOutListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			new AlertDialog.Builder(ManageActivity.this)
					.setMessage("ȷ��ע����½��")
					.setNegativeButton("ȡ��",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
								}
							})
					.setPositiveButton("ȷ��",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									ManageActivity.this
											.startActivity(new Intent(
													ManageActivity.this,
													LogActivity.class));
								}
							}).show();
		}
	}

	class BackListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			Intent intent = new Intent();
			intent.setClass(ManageActivity.this, MainActivity.class);
			intent.putExtra("text", text);
			intent.putExtra("index", index);
			startActivity(intent);
		}

	}
}
