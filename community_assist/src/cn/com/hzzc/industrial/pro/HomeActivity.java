package cn.com.hzzc.industrial.pro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;

/**
 * @todo 首页
 * @author pang
 *
 */
public class HomeActivity extends BaseActivity implements OnClickListener {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.community_home);

	}

	@Override
	public void onClick(View v) {
		System.out.println("__v.getId():"+v.getId());
		if (v.getId() == R.id.home_activities) {
			listActivity();
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
		System.out.println("**********listActivity");
		startActivity(intent);
	}

}
