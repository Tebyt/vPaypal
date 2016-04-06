package com.TopBang.vPaypal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.Tebyt.payment.R;
/**
 * ���ߣ�TopBang
 * ʱ��:2013.08
 * ���ܣ������п�����GUI
 * */
public class BindActivity extends Activity {

	private Button bind_ok; //ȷ��
	private EditText bind_account; //���п��ʺ�
	private EditText bind_name; //����
	private EditText bind_password; //���п�����
	private EditText bind_id;  //���֤��

	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bind);
		setTitle("�����п�");
		bind_ok = (Button) findViewById(R.id.bind_ok);
		bind_ok.setOnClickListener(new Bind_OkListener());

		bind_account = (EditText) findViewById(R.id.bind_account);
		bind_name = (EditText) findViewById(R.id.bind_name);
		bind_password = (EditText) findViewById(R.id.bind_password);
		bind_id = (EditText) findViewById(R.id.bind_id);

		intent = new Intent();
		intent.putExtra("source", "Bind");
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
			intent.setClass(BindActivity.this, LogActivity.class);
			startActivity(intent);
			return true;
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}

	class Bind_OkListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			if (bind_account.getText().length() == 0
					|| bind_name.getText().length() == 0
					|| bind_password.getText().length() == 0
					|| bind_id.getText().length() == 0) {
				Toast.makeText(BindActivity.this, "����д����", 500).show();
			} else {
				String name = bind_name.getText().toString();
				if (checkNameChese(name) == false) {
					Toast.makeText(BindActivity.this, "����ȷ��������", 500).show();
				} else {
					Toast.makeText(BindActivity.this, "�󶨳ɹ���", 500).show();
					intent.setClass(BindActivity.this, LocationActivity.class);
					startActivity(intent);
				}
			}
		}
	}

	/**
	 * �ж����뺺��
	 * 
	 * @param c
	 * @return
	 */
	public boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}
		return false;
	}

	/**
	 * ���String�Ƿ�ȫ������
	 * 
	 * @param name
	 * @return
	 */
	public boolean checkNameChese(String name) {
		boolean res = true;
		char[] cTemp = name.toCharArray();
		for (int i = 0; i < name.length(); i++) {
			if (!isChinese(cTemp[i])) {
				res = false;
				break;
			}
		}
		return res;
	}

}