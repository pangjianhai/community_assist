package cn.com.hzzc.industrial.pro.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import cn.com.hzzc.industrial.pro.R;
import cn.com.hzzc.industrial.pro.entity.CheckItem;
import cn.com.hzzc.industrial.pro.opsinterface.IQuestionItemOperator;

/**
 * @todo 问卷调查适配器
 * @author pang
 *
 */
public class QuestionItemAdapter extends BaseAdapter {
	private List<CheckItem> ds = new ArrayList<CheckItem>();
	private IQuestionItemOperator opr;
	private HolderView holder;
	private Context context;

	public QuestionItemAdapter(Context context, List<CheckItem> dataSourceList,
			IQuestionItemOperator opr) {
		super();
		this.context = context;
		this.ds = dataSourceList;
		this.opr = opr;
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
		final CheckItem act = ds.get(position);
		final String id = act.getId();
		if (convertview == null) {
			convertview = View.inflate(context, R.layout.question_item, null);
			holder.question_item_name = (TextView) convertview
					.findViewById(R.id.question_item_name);
			holder.question_edit = (ImageView) convertview
					.findViewById(R.id.question_edit);
			holder.question_del = (ImageView) convertview
					.findViewById(R.id.question_del);
			convertview.setTag(holder);
		} else {
			holder = (HolderView) convertview.getTag();
		}
		holder.question_item_name.setText(act.getItemName());
		holder.question_item_name.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
		holder.question_edit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				opr.edit(position, id);
			}
		});
		holder.question_del.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				opr.del(position, id);
			}
		});
		return convertview;
	}

	private class HolderView {
		private TextView question_item_name;
		private ImageView question_edit, question_del;

	}

}
