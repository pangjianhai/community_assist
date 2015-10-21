package cn.com.hzzc.industrial.pro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Toast;
import cn.com.hzzc.industrial.pro.adapter.ActivityItemAdapter;
import cn.com.hzzc.industrial.pro.entity.ActivityEntity;
import cn.com.hzzc.industrial.pro.opsinterface.IActivityOperator;
import cn.com.hzzc.industrial.pro.part.wipelist.SwipeMenu;
import cn.com.hzzc.industrial.pro.part.wipelist.SwipeMenuCreator;
import cn.com.hzzc.industrial.pro.part.wipelist.SwipeMenuItem;
import cn.com.hzzc.industrial.pro.part.wipelist.SwipeMenuListView;
import cn.com.hzzc.industrial.pro.part.wipelist.SwipeMenuListView.OnMenuItemClickListener;
import cn.com.hzzc.industrial.pro.part.wipelist.SwipeMenuListView.OnSwipeListener;

import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

/**
 * @todo 当前用户的所有的社群活动
 * @author pang
 *
 */
public class AllActivitiesActivity extends BaseActivity implements
		IActivityOperator {
	private SwipeMenuListView acts_lv;
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
		acts_lv = (SwipeMenuListView) findViewById(R.id.acts_lv);
		actItemAdapter = new ActivityItemAdapter(AllActivitiesActivity.this,
				AllActivitiesActivity.this, ds);
		acts_lv.setAdapter(actItemAdapter);
	}

	private void initListView() {
		// step 1. create a MenuCreator
		SwipeMenuCreator creator = new SwipeMenuCreator() {

			@Override
			public void create(SwipeMenu menu) {
				// create "open" item
				SwipeMenuItem openItem = new SwipeMenuItem(
						getApplicationContext());
				// set item background
				openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
						0xCE)));
				// set item width
				openItem.setWidth(dp2px(90));
				// set item title
				openItem.setTitle("进入");
				// set item title fontsize
				openItem.setTitleSize(18);
				// set item title font color
				openItem.setTitleColor(Color.WHITE);
				// add to menu
				menu.addMenuItem(openItem);

				// create "delete" item
				SwipeMenuItem deleteItem = new SwipeMenuItem(
						getApplicationContext());
				// set item background
				deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
						0x3F, 0x25)));
				// set item width
				deleteItem.setWidth(dp2px(90));
				// set a icon
				// deleteItem.setIcon(R.drawable.ic_delete);
				deleteItem.setTitle("编辑");
				// set item title fontsize
				deleteItem.setTitleSize(18);
				// set item title font color
				deleteItem.setTitleColor(Color.WHITE);
				// add to menu
				menu.addMenuItem(deleteItem);
			}
		};
		// set creator
		acts_lv.setMenuCreator(creator);

		// step 2. listener item click event
		acts_lv.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			@Override
			public void onMenuItemClick(int position, SwipeMenu menu, int index) {
				ActivityEntity item = ds.get(position);
				switch (index) {
				case 0:
					goInto(item);
					break;
				case 1:
					edit(item);
					break;
				}
			}
		});

		// set SwipeListener
		acts_lv.setOnSwipeListener(new OnSwipeListener() {

			@Override
			public void onSwipeStart(int position) {
				// swipe start
			}

			@Override
			public void onSwipeEnd(int position) {
				// swipe end
			}
		});

		// other setting
		// listView.setCloseInterpolator(new BounceInterpolator());

		// test item long click
		acts_lv.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(getApplicationContext(),
						position + " long click", 0).show();
				return false;
			}
		});
	}

	private void loadDataMore() {
		for (int i = 0; i < 10; i++) {
			ActivityEntity act = new ActivityEntity();
			act.setTitle("xxx" + i);
			ds.add(act);
		}
	}

	private void loadDataMore2() {
		try {
			String url = "";
			RequestCallBack<String> rcb = new RequestCallBack<String>() {

				@Override
				public void onSuccess(ResponseInfo<String> responseInfo) {
				}

				@Override
				public void onFailure(HttpException error, String msg) {
					error.printStackTrace();
				}
			};
			Map map = new HashMap();
			map.put("para", "");
			send_normal_request(url, map, rcb);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void clickAct(ActivityEntity bean) {
		Toast.makeText(getApplicationContext(), bean.getTitle(),
				Toast.LENGTH_SHORT).show();
	}

	private int dp2px(int dp) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
				getResources().getDisplayMetrics());
	}

	/**
	 * @user:pang
	 * @data:2015年10月21日
	 * @todo:查看详情
	 * @return:void
	 */
	public void goInto(ActivityEntity item) {

	}

	public void edit(ActivityEntity item) {

	}
}
