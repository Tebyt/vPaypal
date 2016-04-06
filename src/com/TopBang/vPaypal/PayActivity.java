package com.TopBang.vPaypal;

import java.text.DateFormat;
import java.util.Date;

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
 * 功能：转账界面GUI
 * */
public class PayActivity extends Activity {

	private Button pay_ok;  //确定支付
	private EditText name_text;  //对方户名
	private EditText account_text;  //对方帐号
	private EditText amount_text;  //转账金额
	private String[] text;
	private int index;
	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pay);
		setTitle("转账到银行卡");
		pay_ok = (Button) findViewById(R.id.pay_ok);
		pay_ok.setOnClickListener(new Pay_OkListener());
		Intent i = getIntent();
		if (i.getStringExtra("source").equals("Main")) {
			text = i.getStringArrayExtra("text");
			index = i.getIntExtra("index", 0);
		} else {
			text = new String[100];
			index = 0;
		}
		intent = new Intent();
		intent.putExtra("source", "Pay");

		name_text = (EditText) findViewById(R.id.pay_name);
		account_text = (EditText) findViewById(R.id.pay_account);
		amount_text = (EditText) findViewById(R.id.pay_amount);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		finish();
		super.onPause();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			intent.setClass(PayActivity.this, MainActivity.class);
			intent.putExtra("text", text);
			intent.putExtra("index", index);
			startActivity(intent);
			return true;
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}

	class Pay_OkListener implements OnClickListener {

		@Override
		public void onClick(View arg0) {
			if (name_text.getText().length() == 0
					|| account_text.getText().length() == 0
					|| amount_text.getText().length() == 0) {
				Toast.makeText(PayActivity.this, "请填写完整", 500).show();
			} else {
				String name = name_text.getText().toString();
				if (checkNameChese(name) == false) {
					Toast.makeText(PayActivity.this, "请正确输入姓名", 500).show();
				} else {
					String account = account_text.getText().toString();
					int amount = Integer.parseInt(amount_text.getText()
							.toString());

					Date now = new Date();
					DateFormat d = DateFormat.getDateTimeInstance(
							DateFormat.SHORT, DateFormat.SHORT); // 显示日期，时间（精确到分）
					String time = d.format(now);

					String s = "您于" + time + "向" + name + "的账号（" + account
							+ "）转账" + amount + "元";
					text[index++] = s;
					intent.putExtra("text", text);
					intent.putExtra("index", index);
					if (amount > 0 && amount < 10000) {
						Toast.makeText(PayActivity.this, "转账成功！", 500).show();
						intent.setClass(PayActivity.this, MainActivity.class);
						startActivity(intent);
					} else if (amount >= 10000) {
						Toast.makeText(PayActivity.this, "金额超过10000，需进行验证", 500)
								.show();
						intent.setClass(PayActivity.this, CheckActivity.class);
						startActivity(intent);
					}
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
