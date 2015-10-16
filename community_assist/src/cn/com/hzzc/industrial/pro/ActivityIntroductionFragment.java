package cn.com.hzzc.industrial.pro;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ActivityIntroductionFragment extends BaseTopicFragment {

	public static ActivityIntroductionFragment newInstance(int tag) {
		ActivityIntroductionFragment fragment = new ActivityIntroductionFragment();
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
				R.layout.activity_intro_fragment,
				(ViewGroup) getActivity().findViewById(
						R.id.act_fragment_parent_viewpager), false);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		super.onCreateView(inflater, container, savedInstanceState);
		return mMainView;
	}
}
