package cn.com.hzzc.industrial.pro;

import android.os.Bundle;
import android.view.Window;

/**
 * @todo 添加具体活动
 * @author pang
 *
 */
public class ShowActivityDetailActivity extends ParentActActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.show_activity_detail);
		init();
	}

	private void init() {
	}

}
