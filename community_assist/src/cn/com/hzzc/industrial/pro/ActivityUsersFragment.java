package cn.com.hzzc.industrial.pro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cn.com.hzzc.industrial.pro.adapter.UserFragmentAdapter;
import cn.com.hzzc.industrial.pro.entity.UserEntity;
import cn.com.hzzc.industrial.pro.part.XListView;
import cn.com.hzzc.industrial.pro.part.XListView.IXListViewListener;

import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

public class ActivityUsersFragment extends ParentActFragment implements
		IXListViewListener {

	private View mMainView;
	private XListView topic_post_lv;
	private int currentPage = 1;
	private int rows = 10;
	List<UserEntity> ds = new ArrayList<UserEntity>();
	private UserFragmentAdapter adpater;
	private String actId;

	public static ActivityUsersFragment newInstance(int tag) {
		ActivityUsersFragment fragment = new ActivityUsersFragment();
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
				R.layout.activity_users_fragment,
				(ViewGroup) getActivity().findViewById(
						R.id.act_fragment_parent_viewpager), false);
		findView();
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

	private void findView() {
		System.out.println("0000000000:" + mMainView);
		topic_post_lv = (XListView) mMainView.findViewById(R.id.space_lv);
		topic_post_lv.setPullRefreshEnable(false);
		topic_post_lv.setPullLoadEnable(false);
		topic_post_lv.setXListViewListener(this);
		adpater = new UserFragmentAdapter(getActivity(), ds);
		topic_post_lv.setAdapter(adpater);
		System.out.println("over");
	}

	public void loadMoreData() {
		try {
			JSONObject d = new JSONObject();
			d.put("picId", actId);
			d.put("begin", currentPage);
			d.put("limit", rows);
			currentPage = currentPage + 1;
			String url = "";
			RequestCallBack<String> rcb = new RequestCallBack<String>() {

				@Override
				public void onSuccess(ResponseInfo<String> responseInfo) {
					String data = responseInfo.result;
					List<UserEntity> lst = null;// UserUtils.parseJsonAddToList(data);
					ds.addAll(lst);
					adpater.notifyDataSetChanged();
					onLoadOver();
				}

				@Override
				public void onFailure(HttpException error, String msg) {
					onLoadOver();
				}
			};
			Map map = new HashMap();
			map.put("para", d.toString());
			send_normal_request(url, map, rcb);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void onLoadOver() {
		topic_post_lv.stopRefresh();
		topic_post_lv.stopLoadMore();
		topic_post_lv.setRefreshTime("刚刚");
	}

	@Override
	public void onRefresh() {
		onLoadOver();
	}

	@Override
	public void onLoadMore() {
		loadMoreData();

	}
}
