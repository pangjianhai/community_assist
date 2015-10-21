package cn.com.hzzc.industrial.pro.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.TextView;
import cn.com.hzzc.industrial.pro.R;
import cn.com.hzzc.industrial.pro.entity.HomeItem;
import cn.com.hzzc.industrial.pro.opsinterface.IHomeItemOps;

public class HomeGridAdapter extends BaseAdapter {
	private Context cx;
	private IHomeItemOps ops;
	private List<HomeItem> ls = null;
	private HolderView holder;

	public HomeGridAdapter(Context cx, IHomeItemOps ops, List<HomeItem> ls) {
		super();
		this.cx = cx;
		this.ops = ops;
		this.ls = ls;
	}

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
	public View getView(final int position, View convertview, ViewGroup parent) {
		holder = new HolderView();
		final HomeItem hi = ls.get(position);
		if (convertview == null) {
			convertview = View.inflate(cx, R.layout.home_item, null);
			holder.home_item_name = (TextView) convertview
					.findViewById(R.id.home_item_name);
			convertview.setTag(holder);
		} else {
			holder = (HolderView) convertview.getTag();
		}

		holder.home_item_name.setText(hi.getContent());
		holder.home_item_name.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ops.click(position);
			}
		});
		return convertview;
	}

	private class HolderView {
		private TextView home_item_name;

	}
}
