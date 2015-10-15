package cn.com.hzzc.industrial.pro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

/**
 * @todo 首页
 * @author pang
 *
 */
public class HomeActivity extends BaseActivity implements OnClickListener {

	private Button home_activities, home_add_activity;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.community_home);
		init();
	}

	private void init() {
		home_activities = (Button) findViewById(R.id.home_activities);
		home_activities.setOnClickListener(this);
		home_add_activity = (Button) findViewById(R.id.home_add_activity);
		home_add_activity.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		System.out.println("__v.getId():" + v.getId());
		if (v.getId() == R.id.home_activities) {
			listActivity();
		}
		if (v.getId() == R.id.home_add_activity) {
			createAct();
		}
	}

	/**
	 * @user:pang
	 * @data:2015年10月15日
	 * @todo:查看所有的活动
	 * @return:void
	 */
	private void listActivity() {
		Intent intent = new Intent();
		intent.setClass(HomeActivity.this, AllActivitiesActivity.class);
		startActivity(intent);
	}

	private void createAct() {
		Intent intent = new Intent();
		intent.setClass(HomeActivity.this, AddActivityActivity.class);
		startActivity(intent);
	}

}
