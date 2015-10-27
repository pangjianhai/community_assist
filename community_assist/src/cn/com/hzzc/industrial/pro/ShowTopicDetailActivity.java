package cn.com.hzzc.industrial.pro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import cn.com.hzzc.industrial.pro.cons.SystemConst;
import cn.com.hzzc.industrial.pro.entity.TopicEntity;
import cn.com.hzzc.industrial.pro.part.HomeFrameAdapter;
import cn.com.hzzc.industrial.pro.util.ActUtils;

import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * @todo 查看健康主题详情
 * @author pang
 *
 */
public class ShowTopicDetailActivity extends BaseFragmentActivity {

	private TextView topic_name, topic_uer_num, topic_comment_num;

	/****** 分页有关 *******/
	private ViewPager viewPager;
	private List<Fragment> lists = new ArrayList<Fragment>();
	private HomeFrameAdapter myAdapter;
	private String topicId;
	String userId;
	/**** 指示 ****/
	private View left_arrow, right_arrow;

	private ImageView img_topic;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.topic_all_detail);
		userId = GloableApplication.getUserId();
		topicId = getIntent().getStringExtra("topicId");
		Fragment sspace = new TopicPostFragment();
		Fragment tspace = new TopicUserFragment();
		lists.add(sspace);
		lists.add(tspace);
		myAdapter = new HomeFrameAdapter(getSupportFragmentManager(), lists);
		/**
		 * 初始化viewpaper
		 */
		viewPager = (ViewPager) findViewById(R.id.topic_fragment_parent_viewpager);
		viewPager.setAdapter(myAdapter);
		viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
		initParam();
		initData();
		initImage();
		initNumData();
	}

	private void initParam() {
		topic_name = (TextView) findViewById(R.id.topic_name);

		topic_uer_num = (TextView) findViewById(R.id.topic_uer_num);
		topic_comment_num = (TextView) findViewById(R.id.topic_comment_num);

		left_arrow = findViewById(R.id.left_arrow);
		right_arrow = findViewById(R.id.right_arrow);
		img_topic = (ImageView) findViewById(R.id.img_topic);
	}

	/**
	 * @user:pang
	 * @data:2015年9月6日
	 * @todo:初始化主题名称以及相关数据
	 * @return:void
	 */
	private void initImage() {
		String pic_url = SystemConst.server_url
				+ SystemConst.TopicUrl.getTopicImgByPicId + "?para={picId:'"
				+ topicId + "'}";
		ImageLoader.getInstance().displayImage(pic_url, img_topic,
				GloableApplication.getDisplayImageOption());
	}

	/**
	 * @user:pang
	 * @data:2015年9月6日
	 * @todo:初始化主题名称以及相关数据
	 * @return:void
	 */
	private void initData() {
		try {
			JSONObject d = new JSONObject();
			d.put("picId", topicId);
			RequestCallBack<String> rcb = new RequestCallBack<String>() {

				@Override
				public void onSuccess(ResponseInfo<String> responseInfo) {
					TopicEntity te = ActUtils
							.parseEntityByJSON(responseInfo.result);
					topic_name.setText(te.getName());
				}

				@Override
				public void onFailure(HttpException error, String msg) {
				}
			};
			Map map = new HashMap();
			map.put("para", d.toString());
			send_normal_request(SystemConst.server_url
					+ SystemConst.TopicUrl.get_topic_info_by_id, map, rcb);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initNumData() {
		try {
			JSONObject d = new JSONObject();
			d.put("picId", topicId);
			RequestCallBack<String> rcb = new RequestCallBack<String>() {

				@Override
				public void onSuccess(ResponseInfo<String> responseInfo) {
					String data = responseInfo.result;
					String participationNum = "0";
					String commentsNum = "0";
					try {
						JSONObject job = new JSONObject(data);
						participationNum = job.getString("participationNum");
						commentsNum = job.getString("commentsNum");
					} catch (JSONException e) {
						e.printStackTrace();
					}
					topic_uer_num.setText(participationNum + "人参与");
					topic_comment_num.setText(commentsNum + "条发布");
					topic_uer_num.setVisibility(View.VISIBLE);
					topic_comment_num.setVisibility(View.VISIBLE);
				}

				@Override
				public void onFailure(HttpException error, String msg) {
				}
			};
			Map map = new HashMap();
			map.put("para", d.toString());
			send_normal_request(
					SystemConst.server_url
							+ SystemConst.TopicUrl.querypicPostParticipationNumAndCommentNum,
					map, rcb);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public class MyOnPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageSelected(int index) { // arg0:点击的第几页
			int orange_color = Color.parseColor("#FFA500");
			int white_color = Color.parseColor("#dedede");
			if (index == 0) {

				left_arrow.setBackgroundColor(orange_color);
				right_arrow.setBackgroundColor(white_color);
			} else if (index == 1) {
				left_arrow.setBackgroundColor(white_color);
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

	/********************* 页面 **************************/
	/**
	 * 回调接口
	 */
	public interface MyTouchListener {
		public void onTouchEvent(MotionEvent event);
	}

	/*
	 * 保存MyTouchListener接口的列表
	 */
	private ArrayList<MyTouchListener> myTouchListeners = new ArrayList<ShowTopicDetailActivity.MyTouchListener>();

	/**
	 * 提供给Fragment通过getActivity()方法来注册自己的触摸事件的方法
	 * 
	 * @param listener
	 */
	public void registerMyTouchListener(MyTouchListener listener) {
		myTouchListeners.add(listener);
	}

	/**
	 * 提供给Fragment通过getActivity()方法来取消注册自己的触摸事件的方法
	 * 
	 * @param listener
	 */
	public void unRegisterMyTouchListener(MyTouchListener listener) {
		myTouchListeners.remove(listener);
	}

	/**
	 * 分发触摸事件给所有注册了MyTouchListener的接口
	 */
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		for (MyTouchListener listener : myTouchListeners) {
			listener.onTouchEvent(ev);
		}
		return super.dispatchTouchEvent(ev);
	}

	/**
	 * @param v
	 * @user:pang
	 * @data:2015年9月6日
	 * @todo:回退
	 * @return:void
	 */
	public void backoff(View v) {
		this.finish();
	}

}
