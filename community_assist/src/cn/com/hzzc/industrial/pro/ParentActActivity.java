package cn.com.hzzc.industrial.pro;

import java.util.ArrayList;

import android.content.Intent;
import android.view.View;

/**
 * @todo 任务父类
 * @author pang
 *
 */
public class ParentActActivity extends BaseActivity {
	/**
	 * 存储选择图片的路径
	 */
	public ArrayList<String> selectedPicture = new ArrayList<String>();

	public static final int REQUEST_PICK = 0;

	public void selectPicture(View view) {
		Intent intent = new Intent(ParentActActivity.this,
				ShareSelectPicActivity.class);
		intent.putExtra(
				ShareSelectPicActivity.INTENT_SELECTED_PICTURE_FROM_BEGINACTIVITY,
				selectedPicture.size());
		startActivityForResult(intent, REQUEST_PICK);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			ArrayList<String> newPics = (ArrayList<String>) data
					.getSerializableExtra(ShareSelectPicActivity.INTENT_SELECTED_PICTURE);
			selectActImg(newPics);
		}
	}

	public void selectActImg(ArrayList<String> list) {

	}

	/**
	 * @user:pang
	 * @data:2015年10月20日
	 * @todo:新增和编辑用到（操作成功之后的后续操作）
	 * @return:void
	 */
	public void sendSuccess() {

	}
}
