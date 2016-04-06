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
 * 作者：TopBang
 * 时间:2013.08
 * 功能：绑定银行卡界面GUI
 * */
public class BindActivity extends Activity {

	private Button bind_ok; //确定
	private EditText bind_account; //银行卡帐号
	private EditText bind_name; //姓名
	private EditText bind_password; //银行卡密码
	private EditText bind_id;  //身份证号

	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bind);
		setTitle("绑定银行卡");
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
				Toast.makeText(BindActivity.this, "请填写完整", 500).show();
			} else {
				String name = bind_name.getText().toString();
				if (checkNameChese(name) == false) {
					Toast.makeText(BindActivity.this, "请正确输入姓名", 500).show();
				} else {
					Toast.makeText(BindActivity.this, "绑定成功！", 500).show();
					intent.setClass(BindActivity.this, LocationActivity.class);
					startActivity(intent);
				}
			}
		}
	}

	/**
	 * 判定输入汉字
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
	 * 检测String是否全是中文
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