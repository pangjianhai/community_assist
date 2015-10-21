package cn.com.hzzc.industrial.pro.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
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
		String flag = hi.getFlag();
		if (convertview == null) {
			convertview = View.inflate(cx, R.layout.home_item, null);
			holder.home_item_name = (TextView) convertview
					.findViewById(R.id.home_item_name);
			convertview.setTag(holder);
		} else {
			holder = (HolderView) convertview.getTag();
		}
		int green_color = Color.parseColor("#00DB00");
		int orange_color = Color.parseColor("#FFA500");
		int fen_color = Color.parseColor("#E9408F");
		holder.home_item_name.setText(hi.getContent());
		if (HomeItem.all_act.equals(flag) || HomeItem.all_topic.equals(flag)) {
			holder.home_item_name.setTextColor(orange_color);
		} else if (HomeItem.add_act.equals(flag)
				|| HomeItem.add_topic.equals(flag)) {
			holder.home_item_name.setTextColor(fen_color);
		} else {
			holder.home_item_name.setTextColor(green_color);
		}
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
