package cn.com.hzzc.industrial.pro;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import cn.com.hzzc.industrial.pro.adapter.HomeGridAdapter;
import cn.com.hzzc.industrial.pro.entity.HomeItem;
import cn.com.hzzc.industrial.pro.opsinterface.IHomeItemOps;

/**
 * @todo 首页
 * @author pang
 *
 */
public class HomeActivity extends BaseActivity implements OnClickListener,
		IHomeItemOps {

	private Button home_activities, home_add_activity;

	private GridView home_gridview;
	private List<HomeItem> ds = new ArrayList<HomeItem>();
	private HomeGridAdapter adapter;

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
		if (v.getId() == R.id.home_activities) {
			listActivity();
		}
		if (v.getId() == R.id.home_add_activity) {
			createAct();
		}
	}

	private void initGridView() {
		home_gridview = (GridView) findViewById(R.id.home_gridview);
		adapter = new HomeGridAdapter(ds, HomeActivity.this);
		home_gridview.setAdapter(adapter);

		/**
		 * 如果是第一张则是打开照相机
		 */
		home_gridview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			}
		});
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

	@Override
	public void click(int index) {
		// TODO Auto-generated method stub

	}

}
