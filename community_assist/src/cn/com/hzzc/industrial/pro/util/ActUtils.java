package cn.com.hzzc.industrial.pro.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.com.hzzc.industrial.pro.entity.ActivityEntity;
import cn.com.hzzc.industrial.pro.entity.CheckItem;

/**
 * @todo 活动工具类
 * @author pang
 *
 */
public class ActUtils {

	/**
	 * 
	 * @param data
	 * @return
	 * @user:pang
	 * @data:2015年10月20日
	 * @todo:获取通用活动的ID
	 * @return:String
	 */
	public static String getActId(String data) {
		try {
			JSONObject js = new JSONObject(data);
			return js.getString("activityId");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * @param data
	 * @return
	 * @user:pang
	 * @data:2015年10月20日
	 * @todo:获取具体活动的ID
	 * @return:String
	 */
	public static String getActDetailId(String data) {
		try {
			JSONObject js = new JSONObject(data);
			return js.getString("activityTypeId");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 
	 * @param data
	 * @return
	 * @user:pang
	 * @data:2015年10月20日
	 * @todo:解析通用活动
	 * @return:ActivityEntity
	 */
	public static ActivityEntity getActivityEntity(String data) {
		ActivityEntity ae = new ActivityEntity();
		try {
			JSONObject js = new JSONObject(data);
			JSONObject act = js.getJSONObject("commenActivity");
			String id = act.getString("id");
			String type = act.getString("type");
			String status = act.getString("status");
			String createDate = act.getString("createDate");
			String title = act.getString("title");
			String imgId = act.getString("imgId");
			String endDate = act.getString("endDate");
			String beginDate = act.getString("beginDate");
			String readNum = act.getString("readNum");
			String introduction = act.getString("introduction");
			String ifNeedSelfSociety = act.getString("ifNeedSelfSociety");
			String societyId = act.getString("societyId");

			ae.setId(id);
			ae.setTitle(title);
			ae.setIntroduction(introduction);
			ae.setBeginDate(beginDate);
			ae.setEndDate(endDate);
			ae.setType(type);
			ae.setSocietyId(societyId);
			ae.setStatus(status);
			ae.setImgId(imgId);
			ae.setCreateDate(createDate);
			ae.setReadNum(readNum);
			ae.setIfNeedSelfSociety("true".equals(ifNeedSelfSociety) ? true
					: false);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ae;
	}

	/**
	 * 
	 * @param data
	 * @return
	 * @user:pang
	 * @data:2015年10月20日
	 * @todo:获取调查问卷所有问题
	 * @return:List<CheckItem>
	 */
	public static List<CheckItem> getQuestioinItems(String data) {
		List<CheckItem> l = new ArrayList<CheckItem>();
		if (data != null && !"".equals(data)) {
			try {
				JSONObject js = new JSONObject(data);
				String ex = js.getString("questionItems");
				if (ex == null || "".equals(ex) || "null".equals(ex)) {
					return l;
				}
				JSONArray array = js.getJSONArray("questionItems");
				if (array.length() > 0) {
					for (int i = 0; i < array.length(); i++) {
						JSONObject j = array.getJSONObject(i);
						String Id = j.getString("id");
						String questionId = j.getString("questionId");
						String question = j.getString("question");

						CheckItem ci = new CheckItem();
						ci.setId(Id);
						ci.setItemName(question);
						ci.setActDetailId(questionId);
						l.add(ci);
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		return l;

	}
}
