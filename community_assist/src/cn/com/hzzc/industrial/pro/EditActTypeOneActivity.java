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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.com.hzzc.industrial.pro.cons.SystemConst;
import cn.com.hzzc.industrial.pro.entity.OffLineActEntity;
import cn.com.hzzc.industrial.pro.task.UploadFileTask;
import cn.com.hzzc.industrial.pro.util.ActUtils;

import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * @todo 编辑线下活动的类型
 * @author pang
 *
 */
public class EditActTypeOneActivity extends ParentActActivity implements
		OnClickListener {
	// 详情ID
	private String dId;
	// 活动内容
	private EditText one_content;
	// 活动图片
	private ImageView one_img0, one_img1;
	// 关于图片的处理
	private int index = -1;
	private String path1 = "";
	private String path2 = "";
	// 保存按钮
	private TextView edit_type_1_save;
	private OffLineActEntity oae = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.edit_type_one_detail);
		dId = getIntent().getStringExtra("dId");
		init();
		getDetail();
	}

	/**
	 * @user:pang
	 * @data:2015年10月22日
	 * @todo:初始化元素
	 * @return:void
	 */
	public void init() {
		// 输入框
		one_content = (EditText) findViewById(R.id.edit_one_content);
		// 图片
		one_img0 = (ImageView) findViewById(R.id.edit_one_img0);
		one_img1 = (ImageView) findViewById(R.id.edit_one_img1);
		// 获取按钮
		edit_type_1_save = (TextView) findViewById(R.id.edit_edit_type_1_save);
		edit_type_1_save.setOnClickListener(this);
		one_img0.setOnClickListener(this);
		one_img1.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		System.out.println("onclick");
		if (v.getId() == R.id.edit_one_img0) {// 选择背景图片
			index = 0;
			selectPicture(null);
		} else if (v.getId() == R.id.edit_one_img1) {// 选择背景图片
			index = 1;
			selectPicture(null);
		} else if (v.getId() == R.id.edit_edit_type_1_save) {// 保存了编辑
			save();
		}

	}

	/**
	 * 选择了背景图片
	 */
	public void selectActImg(ArrayList<String> list) {
		if (!list.isEmpty()) {
			String path = list.get(0);
			if (path != null && !"".equals(path)) {
				if (index == 0) {
					ImageLoader.getInstance().displayImage("file://" + path,
							one_img0,
							GloableApplication.getDisplayImageOption());
					path1 = path;
					saveImg("img0", path1);
				} else {
					ImageLoader.getInstance().displayImage("file://" + path,
							one_img1,
							GloableApplication.getDisplayImageOption());
					path2 = path;
					saveImg("img1", path2);
				}
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
	public void saveImg(String imgI, String path) {
		String url = SystemConst.server_url
				+ SystemConst.Type1Url.addActivityOfflineDetailImgOne;
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			Map textPram = new HashMap();
			List<File> files = new ArrayList<File>();
			JSONObject obj = new JSONObject();
			obj.put("offlineId", dId);
			obj.put("imgfield", imgI);
			textPram.put(SystemConst.json_param_name, obj.toString());
			files.add(new File(path));
			map.put(UploadFileTask.text_param, textPram);
			map.put(UploadFileTask.file_param, files);
			new UploadFileTask(EditActTypeOneActivity.this, url).execute(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 提示保存成功
	 */
	@Override
	public void sendSuccess(String result) {
		Toast.makeText(getApplicationContext(), "保存成功", Toast.LENGTH_SHORT)
				.show();
	}

	public void save() {
		String url = SystemConst.server_url
				+ SystemConst.Type1Url.editActivityOfflineDetail;
		String c = one_content.getText().toString();
		try {
			JSONObject d = new JSONObject();
			d.put("Id", dId);
			d.put("content", c);
			RequestCallBack<String> rcb = new RequestCallBack<String>() {

				@Override
				public void onSuccess(ResponseInfo<String> responseInfo) {
					String data = responseInfo.result;
					backoff(null);
				}

				@Override
				public void onFailure(HttpException error, String msg) {
					error.printStackTrace();
				}
			};
			Map map = new HashMap();
			map.put("para", d.toString());
			send_normal_request(url, map, rcb);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @user:pang
	 * @data:2015年10月23日
	 * @todo:获取线下活动详情
	 * @return:void
	 */
	public void getDetail() {
		String url = SystemConst.server_url
				+ SystemConst.Type1Url.queryActivityOfflineDetailBycommonId;
		try {
			JSONObject d = new JSONObject();
			d.put("Id", dId);
			RequestCallBack<String> rcb = new RequestCallBack<String>() {

				@Override
				public void onSuccess(ResponseInfo<String> responseInfo) {
					String data = responseInfo.result;
					System.out.println("=====>data:" + data);
					oae = ActUtils.getOffAct(data);
					render(oae);
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

	private void render(OffLineActEntity oae) {
		one_content.setText(oae.getContent());
		String img0 = oae.getImg0();
		String img1 = oae.getImg1();

		if (img0 != null && !"".equals(img0)) {
			String pic_url = SystemConst.server_url
					+ SystemConst.Type2Url.getImgByImgId + "?para={imgId:"
					+ img0 + "}";
			ImageLoader.getInstance().displayImage(pic_url, one_img0,
					GloableApplication.getDisplayImageOption());
		}
		if (img1 != null && !"".equals(img1)) {
			String pic_url = SystemConst.server_url
					+ SystemConst.Type2Url.getImgByImgId + "?para={imgId:"
					+ img1 + "}";
			ImageLoader.getInstance().displayImage(pic_url, one_img1,
					GloableApplication.getDisplayImageOption());
		}
	}

	public void backoff(View v) {
		finish();
	}
}
