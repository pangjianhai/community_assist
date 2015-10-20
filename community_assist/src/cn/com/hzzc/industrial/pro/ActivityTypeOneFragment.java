package cn.com.hzzc.industrial.pro;

import java.util.ArrayList;
import java.util.List;

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
import cn.com.hzzc.industrial.pro.entity.CheckItem;

public class ActivityTypeOneFragment extends ParentActFragment implements
		OnClickListener {

	private Button type_2_add, question_btn;
	private EditText question_info;
	Dialog dialog;

	private ListView item_lv;
	private List<CheckItem> ds = new ArrayList<CheckItem>();
	private QuestionItemAdapter adapter = null;

	public static ActivityTypeOneFragment newInstance(int tag) {
		ActivityTypeOneFragment fragment = new ActivityTypeOneFragment();
		Bundle b = new Bundle();
		b.putInt("tag", tag);
		fragment.setArguments(b);
		return fragment;
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
		adapter = new QuestionItemAdapter(getActivity(), ds);
		item_lv.setAdapter(adapter);
		loadData();

	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.type_2_add) {
			dialog.show();
		} else if (v.getId() == R.id.question_btn) {
			Toast.makeText(getActivity(), question_info.getText().toString(),
					Toast.LENGTH_SHORT).show();
			dialog.dismiss();
			question_info.setText("");
		}
	}

	private void loadData() {
		for (int i = 0; i < 9; i++) {
			CheckItem ci = new CheckItem();
			ci.setItemName("您对这个优惠的价格感到满意吗？" + i);
			ds.add(ci);
		}
		adapter.notifyDataSetChanged();
	}
}
