package cn.com.hzzc.industrial.pro;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import cn.com.hzzc.industrial.pro.cons.SystemConst;
import cn.com.hzzc.industrial.pro.entity.ActivityEntity;
import cn.com.hzzc.industrial.pro.util.ActUtils;

import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

public class ActivityIntroductionFragment extends ParentActFragment {

	private TextView add_act_title, add_act_type, add_act_beginDate,
			add_act_endDate, add_act_introduce;
	private ImageView add_act_self, add_act_img;
	public ActivityEntity entity;

	public ActivityIntroductionFragment() {
		super();
	}

	public ActivityIntroductionFragment(String cId) {
		this.cId = cId;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LayoutInflater inflater = getActivity().getLayoutInflater();
		mMainView = inflater.inflate(
				R.layout.activity_intro_fragment,
				(ViewGroup) getActivity().findViewById(
						R.id.act_fragment_parent_viewpager), false);
		init();
		getCommonInfo();
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
	 * @todo:初始化
	 * @return:void
	 */
	private void init() {
		add_act_title = (TextView) mMainView.findViewById(R.id.add_act_title);
		add_act_type = (TextView) mMainView.findViewById(R.id.add_act_type);
		add_act_beginDate = (TextView) mMainView
				.findViewById(R.id.add_act_beginDate);
		add_act_endDate = (TextView) mMainView
				.findViewById(R.id.add_act_endDate);
		add_act_introduce = (TextView) mMainView
				.findViewById(R.id.add_act_introduce);

		add_act_self = (ImageView) mMainView.findViewById(R.id.add_act_self);
		add_act_img = (ImageView) mMainView.findViewById(R.id.add_act_img);
	}

	/**
	 * @param entity
	 * @user:pang
	 * @data:2015年10月20日
	 * @todo:渲染
	 * @return:void
	 */
	public void render(ActivityEntity entity) {
		add_act_title.setText(entity.getTitle());
		String proType = entity.getType();
		String t = "";
		if ("0".equals(proType)) {
			t = "线下活动";
		} else if ("1".equals(proType)) {
			t = "调查活动";
		} else if ("2".equals(proType)) {
			t = "统计活动";
		}
		add_act_type.setText(t);
		add_act_beginDate.setText(entity.getBeginDate());
		add_act_endDate.setText(entity.getEndDate());
		add_act_introduce.setText(entity.getIntroduction());
		boolean isSelf = entity.isIfNeedSelfSociety();
		if (isSelf) {
			add_act_self.setImageResource(R.drawable.table_on);
		} else {
			add_act_self.setImageResource(R.drawable.table_off);
		}
		String imgId = entity.getImgId();

	}

	/**
	 * @user:pang
	 * @data:2015年10月20日
	 * @todo:获取互动的通用信息
	 * @return:void
	 */
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
					render(entity);
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
