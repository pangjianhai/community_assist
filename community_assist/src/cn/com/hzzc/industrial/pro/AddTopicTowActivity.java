package cn.com.hzzc.industrial.pro;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import cn.com.hzzc.industrial.pro.cons.SystemConst;
import cn.com.hzzc.industrial.pro.task.UploadFileTask;

import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * @todo 添加主题图片
 * @author pang
 *
 */
public class AddTopicTowActivity extends ParentActActivity implements
		OnClickListener {
	private String topicId;
	private ImageView add_topic_tow_img;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.add_topic_tow);
		init();
	}

	/**
	 * @user:pang
	 * @data:2015年10月27日
	 * @todo:初始化 界面
	 * @return:void
	 */
	private void init() {
		topicId = getIntent().getStringExtra("topicId");
		add_topic_tow_img = (ImageView) findViewById(R.id.add_topic_tow_img);
		add_topic_tow_img.setOnClickListener(this);
		String pic_url = SystemConst.server_url
				+ SystemConst.TopicUrl.getTopicImgByPicId + "?para={picId:'"
				+ topicId + "'}";
		ImageLoader.getInstance().displayImage(pic_url, add_topic_tow_img,
				GloableApplication.getDisplayImageOption());
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.add_topic_tow_img) {// 选择背景图片
			selectPicture(null);
		}

	}

	/**
	 * 选择了背景图片
	 */
	public void selectActImg(ArrayList<String> list) {
		if (!list.isEmpty()) {
			String path = list.get(0);
			if (path != null && !"".equals(path)) {
				ImageLoader.getInstance().displayImage("file://" + path,
						add_topic_tow_img,
						GloableApplication.getDisplayImageOption());
				saveImg(path);
			}
		}
	}

	/**
	 * @param imgI
	 * @param path
	 * @user:pang
	 * @data:2015年10月23日
	 * @todo:保存图片
	 * @return:void
	 */
	public void saveImg(String path) {
		String url = SystemConst.server_url
				+ SystemConst.TopicUrl.addBaseTopicImg;
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			Map textPram = new HashMap();
			List<File> files = new ArrayList<File>();
			JSONObject obj = new JSONObject();
			obj.put("picId", topicId);
			textPram.put(SystemConst.json_param_name, obj.toString());
			files.add(new File(path));
			map.put(UploadFileTask.text_param, textPram);
			map.put(UploadFileTask.file_param, files);
			new UploadFileTask(AddTopicTowActivity.this, url).execute(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
