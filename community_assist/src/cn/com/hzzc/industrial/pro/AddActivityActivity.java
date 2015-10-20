package cn.com.hzzc.industrial.pro;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.AdapterView.OnItemSelectedListener;
import cn.com.hzzc.industrial.pro.cons.SystemConst;
import cn.com.hzzc.industrial.pro.task.UploadFileTask;
import cn.com.hzzc.industrial.pro.util.CommonDateUtil;

import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * @todo 添加具体活动
 * @author pang
 *
 */
public class AddActivityActivity extends ParentActActivity implements
		View.OnTouchListener, OnClickListener {

	private EditText add_act_title, add_act_introduce, add_act_beginDate,
			add_act_endDate;
	private ImageView add_act_img, add_act_self;
	private Spinner add_act_type;
	private ArrayAdapter<String> typesAd = null;
	private String type = "0";
	String[] nt = { "线下活动", "调查活动", "统计活动" };
	String file = "";
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
		initTypeData();
		initDate();
	}

	/**
	 * @user:pang
	 * @data:2015年10月20日
	 * @todo:初始化元素
	 * @return:void
	 */
	private void init() {
		add_act_title = (EditText) findViewById(R.id.add_act_title);
		add_act_introduce = (EditText) findViewById(R.id.add_act_introduce);
		add_act_beginDate = (EditText) findViewById(R.id.add_act_beginDate);
		add_act_endDate = (EditText) findViewById(R.id.add_act_endDate);
		add_act_beginDate.setOnTouchListener(this);
		add_act_endDate.setOnTouchListener(this);

		add_act_img = (ImageView) findViewById(R.id.add_act_img);
		add_act_img.setOnClickListener(this);

		add_act_self = (ImageView) findViewById(R.id.add_act_self);
		add_act_self.setOnClickListener(this);

		add_act_type = (Spinner) findViewById(R.id.add_act_type);
	}

	/**
	 * @user:pang
	 * @data:2015年10月20日
	 * @todo:初始化选项
	 * @return:void
	 */
	private void initTypeData() {
		typesAd = new ArrayAdapter<String>(AddActivityActivity.this,
				android.R.layout.simple_spinner_item, nt);
		add_act_type.setAdapter(typesAd);
		add_act_type.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				String proType = typesAd.getItem(arg2);
				if ("线下活动".equals(proType)) {
					type = "0";
				} else if ("调查活动".equals(proType)) {
					type = "1";
				} else if ("统计活动".equals(proType)) {
					type = "2";
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});
	}

	private void initDate() {
		String b = CommonDateUtil.getNowTime();
		b = b.substring(0, b.length() - 3);
		add_act_beginDate.setText(b);
		add_act_endDate.setText(b);
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

	/**
	 * 选择了背景图片
	 */
	public void selectActImg(ArrayList<String> list) {
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
	@Override
	public void sendSuccess() {
		System.out.println("*******************************sendSuccess");
		Intent intent = new Intent();
		intent.setClass(AddActivityActivity.this,
				ShowActivityDetailActivity.class);
		startActivity(intent);
	}

	@SuppressWarnings("unchecked")
	public void next(View view) {
		String url = SystemConst.server_url
				+ SystemConst.Type2Url.save_act_type_2;
		System.out.println("**************url:" + url);
		String title = add_act_title.getText().toString();
		String intro = add_act_introduce.getText().toString();
		String begin = add_act_beginDate.getText().toString() + ":00";
		String end = add_act_endDate.getText().toString() + ":00";
		try {
			Map map = new HashMap();

			Map textPram = new HashMap();
			List<File> files = new ArrayList<File>();
			JSONObject obj = new JSONObject();
			obj.put("type", type);
			obj.put("title", title);
			obj.put("introduction", intro);
			obj.put("ifNeedSelfSociety", isSelf);
			obj.put("beginDate", begin);
			obj.put("endDate", end);
			textPram.put(SystemConst.json_param_name, obj.toString());
			if (file != null && !"".equals(file)) {
				System.out.println("*******file:" + file);
				files.add(new File(file));
			}
			map.put(UploadFileTask.text_param, textPram);
			map.put(UploadFileTask.file_param, files);
			new UploadFileTask(AddActivityActivity.this, url).execute(map);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
