package cn.com.hzzc.industrial.pro.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import cn.com.hzzc.industrial.pro.R;
import cn.com.hzzc.industrial.pro.entity.HomeItem;
import cn.com.hzzc.industrial.pro.opsinterface.IHomeItemOps;

public class HomeGridAdapter extends BaseAdapter {
	private Context cx;
	private IHomeItemOps ops;
	private List<HomeItem> ls = null;
	private HolderView holder;

	public HomeGridAdapter(List<HomeItem> ls, IHomeItemOps ops) {
		super();
		this.ls = ls;
		this.ops = ops;
	}

	/**
	 * 参数
	 */
	LayoutParams params = new AbsListView.LayoutParams(
			AbsListView.LayoutParams.WRAP_CONTENT, 300);

	@Override
	public int getCount() {
		return ls.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	/**
	 * 显然图片
	 */
	@Override
	public View getView(int position, View convertview, ViewGroup parent) {
		holder = new HolderView();
		if (convertview == null) {
			convertview = View.inflate(cx, R.layout.home_item, null);
			holder.question_item_name = (TextView) convertview
					.findViewById(R.id.question_item_name);
			convertview.setTag(holder);
		} else {
			holder = (HolderView) convertview.getTag();
		}
		return convertview;
	}

	private class HolderView {
		private TextView question_item_name;

	}
}
