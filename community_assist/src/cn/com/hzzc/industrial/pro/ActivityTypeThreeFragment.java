package cn.com.hzzc.industrial.pro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
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
 * @todo 统计问卷
 * @author pang
 *
 */
public class ActivityTypeThreeFragment extends ParentActFragment implements
		OnClickListener, IQuestionItemOperator {

	private Button type_2_add, question_btn;
	private EditText stat_info, stat_item_1, stat_item_2, stat_item_3,
			stat_item_4, stat_item_5;
	Dialog dialog;

	private ListView item_lv;
	private List<CheckItem> ds = new ArrayList<CheckItem>();
	private QuestionItemAdapter adapter = null;

	View diaView;

	public ActivityTypeThreeFragment(String dId) {
		this.dId = dId;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LayoutInflater inflater = getActivity().getLayoutInflater();
		mMainView = inflater.inflate(
				R.layout.activity_type_three_fragment,
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
		diaView = View.inflate(getActivity(), R.layout.act_statistics_dialog,
				null);
		dialog = new AlertDialog.Builder(getActivity()).setView(diaView)
				.create();

		question_btn = (Button) diaView.findViewById(R.id.question_btn);
		question_btn.setOnClickListener(this);
		stat_info = (EditText) diaView.findViewById(R.id.stat_info);
		stat_item_1 = (EditText) diaView.findViewById(R.id.stat_item_1);
		stat_item_2 = (EditText) diaView.findViewById(R.id.stat_item_2);
		stat_item_3 = (EditText) diaView.findViewById(R.id.stat_item_3);
		stat_item_4 = (EditText) diaView.findViewById(R.id.stat_item_4);
		stat_item_5 = (EditText) diaView.findViewById(R.id.stat_item_5);

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
				ActivityTypeThreeFragment.this);
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
			Toast.makeText(getActivity(), stat_info.getText().toString(),
					Toast.LENGTH_SHORT).show();
			dialog.dismiss();
			String opt = stat_info.getText().toString();
			stat_info.setText("");
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
		stat_info.setText(ds.get(index).getItemName());
		stat_item_1.setText("");
		stat_item_2.setText("");
		stat_item_3.setText("");
		stat_item_4.setText("");
		stat_item_5.setText("");
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
				+ SystemConst.Type3Url.addActivityStatisticItem;
		String opt1 = stat_item_1.getText().toString();
		String opt2 = stat_item_2.getText().toString();
		String opt3 = stat_item_3.getText().toString();
		String opt4 = stat_item_4.getText().toString();
		String opt5 = stat_item_5.getText().toString();
		try {
			JSONObject d = new JSONObject();
			d.put("statisticId", dId);
			d.put("content", question);
			JSONArray array = new JSONArray();
			if (opt1 != null && !"".equals(opt1)) {
				JSONObject i = new JSONObject();
				i.put("option", opt1);
				array.put(i);
			}

			if (opt2 != null && !"".equals(opt2)) {
				JSONObject i = new JSONObject();
				i.put("option", opt2);
				array.put(i);
			}
			if (opt3 != null && !"".equals(opt3)) {
				JSONObject i = new JSONObject();
				i.put("option", opt3);
				array.put(i);
			}
			if (opt4 != null && !"".equals(opt4)) {
				JSONObject i = new JSONObject();
				i.put("option", opt4);
				array.put(i);
			}
			if (opt5 != null && !"".equals(opt5)) {
				JSONObject i = new JSONObject();
				i.put("option", opt5);
				array.put(i);
			}
			d.put("options", array);
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
