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

import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

/**
 * @todo 添加主题信息
 * @author pang
 *
 */
public class AddTopicOneActivity extends BaseActivity {

	private ProgressBar add_topic_one_loading_now;
	private EditText add_topic_name, add_topic_desc;
	private String topicId;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.add_topic_one);
		init();
	}

	/**
	 * @user:pang
	 * @data:2015年10月27日
	 * @todo:初始化
	 * @return:void
	 */
	private void init() {
		add_topic_name = (EditText) findViewById(R.id.add_topic_name);
		add_topic_desc = (EditText) findViewById(R.id.add_topic_desc);
		add_topic_one_loading_now = (ProgressBar) findViewById(R.id.add_topic_one_loading_now);
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
		String url = SystemConst.server_url + SystemConst.TopicUrl.addBaseTopic;
		try {

			JSONObject obj = new JSONObject();
			String name = add_topic_name.getText().toString();
			String desc = add_topic_desc.getText().toString();
			obj.put("Name", name);
			obj.put("content", desc);
			obj.put("userId", userId);
			RequestCallBack<String> rcb = new RequestCallBack<String>() {

				@Override
				public void onSuccess(ResponseInfo<String> responseInfo) {
					String data = responseInfo.result;
					System.out.println("*****>data:" + data);
					try {
						JSONObject j = new JSONObject(data);
						topicId = j.getString("picId");
						toStep2();
					} catch (JSONException e) {
						e.printStackTrace();
					}

				}

				@Override
				public void onFailure(HttpException error, String msg) {
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
		intent.setClass(AddTopicOneActivity.this, AddTopicTowActivity.class);
		intent.putExtra("topicId", topicId);
		add_topic_one_loading_now.setVisibility(View.GONE);
		startActivity(intent);
		finish();
	}
}
