package cn.com.hzzc.industrial.pro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import cn.com.hzzc.industrial.pro.adapter.QuestionItemAdapter;
import cn.com.hzzc.industrial.pro.cons.SystemConst;
import cn.com.hzzc.industrial.pro.entity.CheckItem;
import cn.com.hzzc.industrial.pro.opsinterface.IQuestionItemOperator;
import cn.com.hzzc.industrial.pro.util.ActUtils;

import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

/**
 * @todo 调查问卷
 * @author pang
 *
 */
public class ActivityTypeTowFragment extends ParentActFragment implements
		OnClickListener, IQuestionItemOperator {

	private Button type_2_add, question_btn;
	private EditText question_info;
	Dialog dialog;

	private ListView item_lv;
	private List<CheckItem> ds = new ArrayList<CheckItem>();
	private QuestionItemAdapter adapter = null;

	public ActivityTypeTowFragment(String dId) {
		this.dId = dId;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LayoutInflater inflater = getActivity().getLayoutInflater();
		mMainView = inflater.inflate(
				R.layout.activity_type_tow_fragment,
				(ViewGroup) getActivity().findViewById(
						R.id.act_fragment_parent_viewpager), false);
		init();
		initListView();
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
		// 获取按钮
		type_2_add = (Button) mMainView.findViewById(R.id.type_2_add);
		type_2_add.setOnClickListener(this);
		// 获取对话框
		View diaView = View.inflate(getActivity(),
				R.layout.act_question_dialog, null);
		dialog = new Dialog(getActivity(), R.style.question_dialog);
		dialog.setContentView(diaView);
		Window dialogWindow = dialog.getWindow();
		WindowManager.LayoutParams lp = dialogWindow.getAttributes();
		dialogWindow.setGravity(Gravity.CENTER);
		lp.width = 600; // 宽度
		lp.height = 500; // 高度
		lp.alpha = 0.7f; // 透明度
		dialogWindow.setAttributes(lp);
		dialog.getWindow().setAttributes(lp);
		question_btn = (Button) diaView.findViewById(R.id.question_btn);
		question_btn.setOnClickListener(this);
		question_info = (EditText) diaView.findViewById(R.id.question_info);

	}

	/**
	 * @user:pang
	 * @data:2015年10月20日
	 * @todo:初始化listview
	 * @return:void
	 */
	private void initListView() {
		item_lv = (ListView) mMainView.findViewById(R.id.item_lv);
		adapter = new QuestionItemAdapter(getActivity(), ds,
				ActivityTypeTowFragment.this);
		item_lv.setAdapter(adapter);
		loadData();

	}

	/**
	 * @user:pang
	 * @data:2015年10月20日
	 * @todo:加载数据
	 * @return:void
	 */
	private void loadData() {
		ds.clear();
		String url = SystemConst.server_url
				+ SystemConst.Type2Url.queryQuestionItemByquestionId;
		try {
			JSONObject d = new JSONObject();
			d.put("questionId", dId);
			RequestCallBack<String> rcb = new RequestCallBack<String>() {

				@Override
				public void onSuccess(ResponseInfo<String> responseInfo) {
					String data = responseInfo.result;
					List<CheckItem> lst = ActUtils.getQuestioinItems(data);
					ds.addAll(lst);
					adapter.notifyDataSetChanged();
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

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.type_2_add) {
			dialog.show();
		} else if (v.getId() == R.id.question_btn) {// 保存或者修改
			Toast.makeText(getActivity(), question_info.getText().toString(),
					Toast.LENGTH_SHORT).show();
			dialog.dismiss();
			String opt = question_info.getText().toString();
			question_info.setText("");
			if (editId != null && !"".equals(editId)) {// 修改
				realEdit(editId, opt);
			} else {// 新增
				realAdd(opt);
			}
			editId = "";
		}
	}

	private String editId;

	@Override
	public void edit(int index, String id) {
		editId = id;
		question_info.setText(ds.get(index).getItemName());
		dialog.show();
	}

	@Override
	public void del(final int index, String id) {
		String url = SystemConst.server_url
				+ SystemConst.Type2Url.delQuestionItem;
		try {
			JSONObject d = new JSONObject();
			d.put("Id", id);
			RequestCallBack<String> rcb = new RequestCallBack<String>() {

				@Override
				public void onSuccess(ResponseInfo<String> responseInfo) {
					String data = responseInfo.result;
					// 删除成功刷新列表
					ds.remove(index);
					adapter.notifyDataSetChanged();
				}

				@Override
				public void onFailure(HttpException error, String msg) {
					Toast.makeText(getActivity(), "删除失败请重试", Toast.LENGTH_SHORT)
							.show();
				}
			};
			Map map = new HashMap();
			map.put("para", d.toString());
			send_normal_request(url, map, rcb);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void realEdit(String id, String question) {
		String url = SystemConst.server_url
				+ SystemConst.Type2Url.editQuestionItem;
		try {
			JSONObject d = new JSONObject();
			d.put("Id", id);
			d.put("question", question);
			RequestCallBack<String> rcb = new RequestCallBack<String>() {

				@Override
				public void onSuccess(ResponseInfo<String> responseInfo) {
					String data = responseInfo.result;
					// 删除成功刷新列表
					loadData();
				}

				@Override
				public void onFailure(HttpException error, String msg) {
					Toast.makeText(getActivity(), "删除失败请重试", Toast.LENGTH_SHORT)
							.show();
				}
			};
			Map map = new HashMap();
			map.put("para", d.toString());
			send_normal_request(url, map, rcb);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void realAdd(String question) {
		String url = SystemConst.server_url
				+ SystemConst.Type2Url.addQuestionItem;
		try {
			JSONObject d = new JSONObject();
			d.put("questionId", dId);
			d.put("question", question);
			RequestCallBack<String> rcb = new RequestCallBack<String>() {

				@Override
				public void onSuccess(ResponseInfo<String> responseInfo) {
					String data = responseInfo.result;
					loadData();
				}

				@Override
				public void onFailure(HttpException error, String msg) {
					Toast.makeText(getActivity(), "删除失败请重试", Toast.LENGTH_SHORT)
							.show();
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
}
