package cn.com.hzzc.industrial.pro;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import cn.com.hzzc.industrial.pro.cons.SystemConst;
import cn.com.hzzc.industrial.pro.entity.OffLineActEntity;
import cn.com.hzzc.industrial.pro.util.ActUtils;

import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * @todo 线下活动
 * @author pang
 *
 */
public class ActivityTypeOneFragment extends ParentActFragment implements
		OnClickListener {

	private Button type_1_edit;
	private TextView one_content;
	private ImageView one_img0, one_img1;
	private OffLineActEntity oae = null;

	View diaView;

	public ActivityTypeOneFragment(String dId) {
		this.dId = dId;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LayoutInflater inflater = getActivity().getLayoutInflater();
		mMainView = inflater.inflate(
				R.layout.activity_type_one_fragment,
				(ViewGroup) getActivity().findViewById(
						R.id.act_fragment_parent_viewpager), false);
		init();
		getDetail();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		ViewGroup p = (ViewGroup) mMainView.getParent();
		if (p != null) {
			p.removeAllViewsInLayout();
		}
		return mMainView;
	}

	/**
	 * @user:pang
	 * @data:2015年10月20日
	 * @todo:初始化按钮
	 * @return:void
	 */
	private void init() {
		// 输入框
		one_content = (TextView) mMainView.findViewById(R.id.one_content);
		// 图片
		one_img0 = (ImageView) mMainView.findViewById(R.id.one_img0);
		one_img1 = (ImageView) mMainView.findViewById(R.id.one_img1);
		// 获取按钮
		type_1_edit = (Button) mMainView.findViewById(R.id.type_1_edit);
		type_1_edit.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.type_1_edit) {
			Intent intent = new Intent();
			intent.setClass(getActivity(), EditActTypeOneActivity.class);
			intent.putExtra("dId", dId);
			getActivity().startActivity(intent);
			getActivity().finish();
		} else if (v.getId() == R.id.question_btn) {// 保存或者修改
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
		if (oae == null) {
			return;
		}
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
}
