package cn.com.hzzc.industrial.pro;

import android.os.Bundle;
import android.view.Window;

/**
 * @todo 首页
 * @author pang
 *
 */
public class HomeActivity extends BaseActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

	}
}
