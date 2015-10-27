package cn.com.hzzc.industrial.pro;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
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

	private EditText add_topic_name, add_topic_desc;
	private String topicId;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.add_topic_one);
		init();
	}

	private void init() {
		add_topic_name = (EditText) findViewById(R.id.add_topic_name);
		add_topic_desc = (EditText) findViewById(R.id.add_topic_desc);
	}

	public void next(View v) {
		String url = SystemConst.server_url + SystemConst.TopicUrl.addBaseTopic;
		try {

			JSONObject obj = new JSONObject();
			obj.put("Name", "");
			obj.put("content", "");
			obj.put("creatorId", userId);
			RequestCallBack<String> rcb = new RequestCallBack<String>() {

				@Override
				public void onSuccess(ResponseInfo<String> responseInfo) {
					String data = responseInfo.result;
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
		startActivity(intent);
	}
}
