package cn.com.hzzc.industrial.pro;

import android.os.Bundle;
import android.view.Window;

/**
 * @todo 当前用户的所有的社群活动
 * @author pang
 *
 */
public class AllActivitiesActivity extends BaseActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.all_activities);

	}
}
