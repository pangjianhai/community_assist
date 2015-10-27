package cn.com.hzzc.industrial.pro;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ProgressBar;
import cn.com.hzzc.industrial.pro.cons.SystemConst;
import cn.com.hzzc.industrial.pro.entity.TopicEntity;
import cn.com.hzzc.industrial.pro.util.ActUtils;

import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

/**
 * @todo 修改主题基本信息
 * @author pang
 *
 */
public class EditTopicOneActivity extends BaseActivity {
	private ProgressBar add_topic_one_loading_now;
	private EditText edit_topic_name, edit_topic_desc;
	private String topicId;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.edit_topic_one);
		init();
		initData();
	}

	/**
	 * @user:pang
	 * @data:2015年10月27日
	 * @todo:初始化
	 * @return:void
	 */
	private void init() {
		topicId = getIntent().getStringExtra("topicId");
		edit_topic_name = (EditText) findViewById(R.id.edit_topic_name);
		edit_topic_desc = (EditText) findViewById(R.id.edit_topic_desc);
		add_topic_one_loading_now = (ProgressBar) findViewById(R.id.add_topic_one_loading_now);
	}

	/**
	 * @user:pang
	 * @data:2015年10月27日
	 * @todo:初始化数据
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
					if (te != null) {
						edit_topic_name.setText(te.getName());
						edit_topic_desc.setText(te.getDesc());
					}
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

	/**
	 * @param v
	 * @user:pang
	 * @data:2015年10月27日
	 * @todo:保存基本信息
	 * @return:void
	 */
	public void next(View v) {
		add_topic_one_loading_now.setVisibility(View.VISIBLE);
		String url = SystemConst.server_url
				+ SystemConst.TopicUrl.editBaseTopicBypicId;
		try {

			JSONObject obj = new JSONObject();
			String name = edit_topic_name.getText().toString();
			String desc = edit_topic_desc.getText().toString();
			obj.put("Name", name);
			obj.put("content", desc);
			obj.put("picId", topicId);
			RequestCallBack<String> rcb = new RequestCallBack<String>() {

				@Override
				public void onSuccess(ResponseInfo<String> responseInfo) {
					String data = responseInfo.result;
					toStep2();

				}

				@Override
				public void onFailure(HttpException error, String msg) {
					add_topic_one_loading_now.setVisibility(View.GONE);
					error.printStackTrace();
				}
			};
			Map map = new HashMap();
			map.put("para", obj.toString());
			send_normal_request(url, map, rcb);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void toStep2() {
		Intent intent = new Intent();
		intent.setClass(EditTopicOneActivity.this, AddTopicTowActivity.class);
		intent.putExtra("topicId", topicId);
		add_topic_one_loading_now.setVisibility(View.GONE);
		startActivity(intent);
		finish();
	}
}
