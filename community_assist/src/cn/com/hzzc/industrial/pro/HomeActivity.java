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
		initGridView();
	}

	@Override
	public void onClick(View v) {
	}

	private void initGridView() {
		buildHome();
		home_gridview = (GridView) findViewById(R.id.home_gridview);
		adapter = new HomeGridAdapter(HomeActivity.this, HomeActivity.this, ds);
		home_gridview.setAdapter(adapter);

	}

	private void buildHome() {
		HomeItem hi0 = new HomeItem();
		hi0.setContent("社群人数/90");
		HomeItem hi1 = new HomeItem();
		hi1.setContent("趋势查询");
		HomeItem hi2 = new HomeItem();
		hi2.setFlag(HomeItem.add_act);
		hi2.setContent("新增活动");
		HomeItem hi3 = new HomeItem();
		hi3.setFlag(HomeItem.all_act);
		hi3.setContent("所有活动");
		ds.add(hi0);
		ds.add(hi1);
		ds.add(hi2);
		ds.add(hi3);
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
		HomeItem hi = ds.get(index);
		String flag = hi.getFlag();
		if (HomeItem.add_act.equals(flag)) {
			createAct();
		} else if (HomeItem.all_act.equals(flag)) {
			listActivity();
		}
	}

}
