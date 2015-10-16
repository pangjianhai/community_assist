package cn.com.hzzc.industrial.pro.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import cn.com.hzzc.industrial.pro.GloableApplication;
import cn.com.hzzc.industrial.pro.R;
import cn.com.hzzc.industrial.pro.entity.UserEntity;
import cn.com.hzzc.industrial.pro.part.CircularImage;

import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * 
 * @author pang
 * @todo 用户搜索时候的adapter
 *
 */
public class UserFragmentAdapter extends BaseAdapter {
	private List<UserEntity> dataSourceList = new ArrayList<UserEntity>();
	private HolderView holder;
	private Context context;

	public UserFragmentAdapter(Context context, List<UserEntity> dataSourceList) {
		super();
		this.dataSourceList = dataSourceList;
		this.context = context;
	}

	@Override
	public int getCount() {
		return dataSourceList.size();
	}

	@Override
	public Object getItem(int position) {
		return dataSourceList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		holder = new HolderView();
		if (convertView != null) {
			holder = (HolderView) convertView.getTag();
		} else {
			convertView = View.inflate(context, R.layout.act_users_item, null);
			holder.find_search_result_header = (CircularImage) convertView
					.findViewById(R.id.find_search_result_header);
			holder.check_someone = (ImageView) convertView
					.findViewById(R.id.check_someone);
			holder.find_search_result_username = (TextView) convertView
					.findViewById(R.id.find_search_result_username);
			holder.find_search_result_tags = (TextView) convertView
					.findViewById(R.id.find_search_result_tags);
			convertView.setTag(holder);
		}
		UserEntity ui = dataSourceList.get(position);
		String userName = ui.getUserName();
		if (userName == null || "".equals(userName)) {
			userName = "匿名者";
		}
		holder.find_search_result_username.setText(userName);
		String imgId = ui.getImg();
		final String uuid = ui.getUuid();
		if (imgId != null && !"".equals(imgId)) {
			// String pic_url = SystemConst.server_url
			// + SystemConst.FunctionUrl.getHeadImgById
			// + "?para={headImg:'" + imgId + "'}";
			String pic_url = "";
			ImageLoader.getInstance().displayImage(pic_url,
					holder.find_search_result_header,
					GloableApplication.getDisplayImageOption());
		} else {
			String imageUri = "drawable://" + R.drawable.visitor_me_cover;
			ImageLoader.getInstance().displayImage(imageUri,
					holder.find_search_result_header);
		}
		convertView.setClickable(true);
		return convertView;
	}

	private class HolderView {
		private ImageView check_someone;
		private CircularImage find_search_result_header;
		private TextView find_search_result_username;
		private TextView find_search_result_tags;
	}

}
