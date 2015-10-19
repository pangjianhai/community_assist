package cn.com.hzzc.industrial.pro;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityTypeOneFragment extends ParentActFragment implements
		OnClickListener {

	private Button type_2_add, question_btn;
	private EditText question_info;
	Dialog dialog;

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

	private void init() {
		type_2_add = (Button) mMainView.findViewById(R.id.type_2_add);
		type_2_add.setOnClickListener(this);

		View diaView = View.inflate(getActivity(),
				R.layout.act_statistics_dialog, null);
		dialog = new Dialog(getActivity(), R.style.question_dialog);
		dialog.setContentView(diaView);

		WindowManager windowManager = getActivity().getWindowManager();
		Display display = windowManager.getDefaultDisplay();
		WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
		lp.width = 600; // 设置宽度
		dialog.getWindow().setAttributes(lp);

		question_btn = (Button) diaView.findViewById(R.id.question_btn);
		question_btn.setOnClickListener(this);
		question_info = (EditText) diaView.findViewById(R.id.question_info);
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
}
