package cn.com.hzzc.industrial.pro;

import java.util.ArrayList;

import com.nostra13.universalimageloader.core.ImageLoader;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

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
	private Button edit_type_1_save;

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
		one_content = (EditText) findViewById(R.id.one_content);
		// 图片
		one_img0 = (ImageView) findViewById(R.id.one_img0);
		one_img1 = (ImageView) findViewById(R.id.one_img1);
		// 获取按钮
		edit_type_1_save = (Button) findViewById(R.id.edit_type_1_save);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.one_img0) {// 选择背景图片
			index = 0;
			selectPicture(null);
		} else if (v.getId() == R.id.one_img1) {// 选择背景图片
			index = 1;
			selectPicture(null);
		} else if (v.getId() == R.id.edit_type_1_save) {// 保存了编辑
		}

	}

	/**
	 * 选择了背景图片
	 */
	public void selectActImg(ArrayList<String> list) {
		if (!list.isEmpty()) {
			String path = list.get(0);
			if (index == 0) {
				ImageLoader.getInstance().displayImage("file://" + path,
						one_img0, GloableApplication.getDisplayImageOption());
				path1 = path;
			} else {
				ImageLoader.getInstance().displayImage("file://" + path,
						one_img1, GloableApplication.getDisplayImageOption());
				path2 = path;
			}
		}
	}

	public void getDetail() {

	}
}
