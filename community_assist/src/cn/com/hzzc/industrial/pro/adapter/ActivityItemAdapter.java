package cn.com.hzzc.industrial.pro.adapter;

import java.util.ArrayList;
import java.util.List;

import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import cn.com.hzzc.industrial.pro.GloableApplication;
import cn.com.hzzc.industrial.pro.R;
import cn.com.hzzc.industrial.pro.cons.SystemConst;
import cn.com.hzzc.industrial.pro.entity.ActivityEntity;
import cn.com.hzzc.industrial.pro.opsinterface.IActivityOperator;

/**
 * 活动适配器
 */
public class ActivityItemAdapter extends BaseAdapter {
	private List<ActivityEntity> ds = new ArrayList<ActivityEntity>();
	private HolderView holder;
	private Context context;
	private IActivityOperator callback;

	public ActivityItemAdapter(Context context, IActivityOperator callback,
			List<ActivityEntity> dataSourceList) {
		super();
		this.context = context;
		this.callback = callback;
		this.ds = dataSourceList;
	}

	@Override
	public int getCount() {
		return ds.size();
	}

	@Override
	public Object getItem(int position) {
		return ds.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertview, ViewGroup parent) {
		holder = new HolderView();
		final ActivityEntity act = ds.get(position);
		if (convertview == null) {
			convertview = View.inflate(context, R.layout.activity_list_item,
					null);
			holder.act_photo = (ImageView) convertview
					.findViewById(R.id.act_photo);
			holder.act_name = (TextView) convertview
					.findViewById(R.id.act_name);
			holder.act_readNum = (TextView) convertview
					.findViewById(R.id.act_readNum);
			holder.act_endDate = (TextView) convertview
					.findViewById(R.id.act_endDate);
			convertview.setTag(holder);
		} else {
			holder = (HolderView) convertview.getTag();
		}

		String imgId = "";// act.getImgId();
		if (imgId != null && !"".equals(imgId)) {
			String pic_url = "";
			ImageLoader.getInstance().displayImage(pic_url, holder.act_photo,
					GloableApplication.getDisplayImageOption());
		} else {
			String imageUri = "drawable://" + R.drawable.visitor_me_cover;
			ImageLoader.getInstance().displayImage(imageUri, holder.act_photo);
		}
		holder.act_name.setText(act.getTitle());
		holder.act_name.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				callback.clickAct(act);

			}
		});
		holder.act_readNum.setText("浏览人数：" + act.getReadNum());
		holder.act_endDate.setText("截止日期：" + act.getEndDate());
		return convertview;
	}

	private class HolderView {
		private TextView act_name, act_readNum, act_endDate;

		private ImageView act_photo;
	}

}
