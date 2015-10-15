package cn.com.hzzc.industrial.pro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.view.Window;
import cn.com.hzzc.industrial.pro.adapter.ActivityItemAdapter;
import cn.com.hzzc.industrial.pro.entity.ActivityEntity;
import cn.com.hzzc.industrial.pro.opsinterface.IActivityOperator;
import cn.com.hzzc.industrial.pro.part.XListView;
import cn.com.hzzc.industrial.pro.part.XListView.IXListViewListener;

import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

/**
 * @todo 当前用户的所有的社群活动
 * @author pang
 *
 */
public class AllActivitiesActivity extends BaseActivity implements
		IXListViewListener, IActivityOperator {
	private XListView acts_lv;
	private ActivityItemAdapter actItemAdapter;
	private List<ActivityEntity> ds = new ArrayList<ActivityEntity>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.all_activities);
		init();
		initListView();
		loadDataMore();
	}

	private void init() {
		acts_lv = (XListView) findViewById(R.id.acts_lv);
		acts_lv.setPullRefreshEnable(false);
		acts_lv.setPullLoadEnable(true);
		acts_lv.setXListViewListener(this);
	}

	private void initListView() {
		actItemAdapter = new ActivityItemAdapter(AllActivitiesActivity.this,
				AllActivitiesActivity.this, ds);
		acts_lv.setAdapter(actItemAdapter);
	}

	private void loadDataMore() {
		for (int i = 0; i < 10; i++) {
			ActivityEntity act = new ActivityEntity();
			act.setTitle("xxx" + i);
			ds.add(act);
		}
		actItemAdapter.notifyDataSetChanged();
	}

	private void loadDataMore2() {
		try {
			String url = "";
			RequestCallBack<String> rcb = new RequestCallBack<String>() {

				@Override
				public void onSuccess(ResponseInfo<String> responseInfo) {
					onLoadOver();
				}

				@Override
				public void onFailure(HttpException error, String msg) {
					error.printStackTrace();
					onLoadOver();
				}
			};
			Map map = new HashMap();
			map.put("para", "");
			send_normal_request(url, map, rcb);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void onLoadOver() {
		acts_lv.stopRefresh();
		acts_lv.stopLoadMore();
		acts_lv.setRefreshTime("刚刚");
	}

	@Override
	public void onRefresh() {
		onLoadOver();
	}

	@Override
	public void onLoadMore() {
		loadDataMore();
	}

	@Override
	public void clickAct(ActivityEntity bean) {
		// TODO Auto-generated method stub

	}
}
