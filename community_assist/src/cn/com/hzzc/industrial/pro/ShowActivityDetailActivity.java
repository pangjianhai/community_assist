package cn.com.hzzc.industrial.pro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.Window;
import cn.com.hzzc.industrial.pro.cons.SystemConst;
import cn.com.hzzc.industrial.pro.entity.ActivityEntity;
import cn.com.hzzc.industrial.pro.part.HomeFrameAdapter;

import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

/**
 * @todo 添加具体活动
 * @author pang
 *
 */
public class ShowActivityDetailActivity extends BaseFragmentActivity {

	private String cId;
	private String dId;
	private String actType;

	public final static int num = 3;

	/****** 分页有关 *******/
	private ViewPager viewPager;
	private List<Fragment> lists = new ArrayList<Fragment>();
	private HomeFrameAdapter myAdapter;
	/**** 指示 ****/
	private View left_arrow, center_arrow, right_arrow;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.show_activity_detail);
		initParam();
		init();
	}

	private void init() {
		Fragment sspace = new ActivityIntroductionFragment(cId);
		Fragment tspace = new ActivityTypeOneFragment(dId);
		Fragment uspace = new ActivityUsersFragment(dId);

		lists.add(sspace);
		lists.add(tspace);
		lists.add(uspace);

		myAdapter = new HomeFrameAdapter(getSupportFragmentManager(), lists);
		/**
		 * 初始化viewpaper
		 */
		viewPager = (ViewPager) findViewById(R.id.act_fragment_parent_viewpager);
		viewPager.setAdapter(myAdapter);
		viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
		viewPager.setCurrentItem(0);

		left_arrow = findViewById(R.id.left_arrow);
		center_arrow = findViewById(R.id.center_arrow);
		right_arrow = findViewById(R.id.right_arrow);
	}

	private void initParam() {
		cId = getIntent().getStringExtra("cId");
		dId = getIntent().getStringExtra("dId");
		System.out.println("))))cId:" + cId);
		System.out.println("))))dId:" + dId);
		cId = "203c14601fd04272a9a5504552f13263";
		dId = "c9bcee4dc54a4a4480b5503f7d98a6f6";
	}

	public class MyOnPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageSelected(int index) { // arg0:点击的第几页
			int orange_color = Color.parseColor("#FFA500");
			resetView();
			if (index == 0) {
				left_arrow.setBackgroundColor(orange_color);
			} else if (index == 1) {
				center_arrow.setBackgroundColor(orange_color);
			} else if (index == 2) {
				right_arrow.setBackgroundColor(orange_color);
			}
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		@Override
		public void onPageScrollStateChanged(int arg0) {

		}

	}

	private void resetView() {
		int white_color = Color.parseColor("#dedede");
		left_arrow.setBackgroundColor(white_color);
		center_arrow.setBackgroundColor(white_color);
		right_arrow.setBackgroundColor(white_color);
	}

	public void backoff(View v) {
		finish();
	}
}
