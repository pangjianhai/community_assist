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
import cn.com.hzzc.industrial.pro.util.ActUtils;

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
	public ActivityEntity entity;
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
		initActivityDetail();
	}

	private void initActivityDetail() {
		Fragment sspace = new ActivityIntroductionFragment(cId);
		Fragment tspace = new ActivityTypeTowFragment(dId);
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
		getCommonInfo();
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

	private void getCommonInfo() {
		String url = SystemConst.server_url
				+ SystemConst.Type2Url.queryCommenActivityByIdForQuestionDetailIdByType;
		try {
			JSONObject d = new JSONObject();
			d.put("Id", cId);
			RequestCallBack<String> rcb = new RequestCallBack<String>() {

				@Override
				public void onSuccess(ResponseInfo<String> responseInfo) {
					String data = responseInfo.result;
					entity = ActUtils.getActivityEntity(data);
					dId = entity.getDetailId();
					actType = entity.getType();
					initActivityDetail();// 获取detailId之后可以继续渲染其他部分了
				}

				@Override
				public void onFailure(HttpException error, String msg) {
				}
			};
			Map map = new HashMap();
			map.put("para", d.toString());
			send_normal_request(url, map, rcb);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
