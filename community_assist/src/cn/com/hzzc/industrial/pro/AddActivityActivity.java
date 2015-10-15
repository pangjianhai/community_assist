package cn.com.hzzc.industrial.pro;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;

import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * @todo 添加具体活动
 * @author pang
 *
 */
public class AddActivityActivity extends ParentActActivity implements
		View.OnTouchListener, OnClickListener {

	private EditText add_act_beginDate, add_act_endDate;
	private ImageView add_act_img, add_act_self;
	/**
	 * 专属本社区
	 */
	private boolean isSelf = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.add_act_intro);
		init();
	}

	private void init() {
		add_act_beginDate = (EditText) findViewById(R.id.add_act_beginDate);
		add_act_endDate = (EditText) findViewById(R.id.add_act_endDate);
		add_act_beginDate.setOnTouchListener(this);
		add_act_endDate.setOnTouchListener(this);

		add_act_img = (ImageView) findViewById(R.id.add_act_img);
		add_act_img.setOnClickListener(this);

		add_act_self = (ImageView) findViewById(R.id.add_act_self);
		add_act_self.setOnClickListener(this);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {

			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			View view = View.inflate(this, R.layout.date_time_dialog, null);
			final DatePicker datePicker = (DatePicker) view
					.findViewById(R.id.date_picker);
			final TimePicker timePicker = (android.widget.TimePicker) view
					.findViewById(R.id.time_picker);
			builder.setView(view);

			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(System.currentTimeMillis());
			datePicker.init(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
					cal.get(Calendar.DAY_OF_MONTH), null);

			timePicker.setIs24HourView(true);
			timePicker.setCurrentHour(cal.get(Calendar.HOUR_OF_DAY));
			timePicker.setCurrentMinute(Calendar.MINUTE);

			if (v.getId() == R.id.add_act_beginDate) {
				final int inType = add_act_beginDate.getInputType();
				add_act_beginDate.setInputType(InputType.TYPE_NULL);
				add_act_beginDate.onTouchEvent(event);
				add_act_beginDate.setInputType(inType);
				add_act_beginDate.setSelection(add_act_beginDate.getText()
						.length());

				builder.setTitle("选取时间");
				builder.setPositiveButton("确  定",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {

								StringBuffer sb = new StringBuffer();
								sb.append(String.format("%d-%02d-%02d",
										datePicker.getYear(),
										datePicker.getMonth() + 1,
										datePicker.getDayOfMonth()));
								sb.append("  ");
								sb.append(timePicker.getCurrentHour())
										.append(":")
										.append(timePicker.getCurrentMinute());

								add_act_beginDate.setText(sb);
								add_act_beginDate.requestFocus();

								dialog.cancel();
							}
						});

			}
			if (v.getId() == R.id.add_act_endDate) {
				final int inType = add_act_beginDate.getInputType();
				add_act_endDate.setInputType(InputType.TYPE_NULL);
				add_act_endDate.onTouchEvent(event);
				add_act_endDate.setInputType(inType);
				add_act_endDate
						.setSelection(add_act_endDate.getText().length());

				builder.setTitle("选取时间");
				builder.setPositiveButton("确  定",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {

								StringBuffer sb = new StringBuffer();
								sb.append(String.format("%d-%02d-%02d",
										datePicker.getYear(),
										datePicker.getMonth() + 1,
										datePicker.getDayOfMonth()));
								sb.append("  ");
								sb.append(timePicker.getCurrentHour())
										.append(":")
										.append(timePicker.getCurrentMinute());

								add_act_endDate.setText(sb);
								add_act_endDate.requestFocus();

								dialog.cancel();
							}
						});

			}
			Dialog dialog = builder.create();
			dialog.show();
		}

		return true;
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.add_act_img) {// 选择背景图片
			selectPicture(null);
		} else if (v.getId() == R.id.add_act_self) {// 调整是否专属本社群
			isSelf = !isSelf;
			if (isSelf) {
				add_act_self.setImageResource(R.drawable.table_on);
			} else {
				add_act_self.setImageResource(R.drawable.table_off);
			}
		}
	}

	public void selectActImg(ArrayList<String> list) {
		System.out.println("0000000000000000000");
		if (!list.isEmpty()) {
			String path = list.get(0);
			ImageLoader.getInstance().displayImage("file://" + path,
					add_act_img, GloableApplication.getDisplayImageOption());
		}
	}

	/**
	 * @param v
	 * @user:pang
	 * @data:2015年10月15日
	 * @todo:点击下一步
	 * @return:void
	 */
	public void next(View v) {

	}
}
