package cn.com.hzzc.industrial.pro.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.com.hzzc.industrial.pro.entity.ActivityEntity;
import cn.com.hzzc.industrial.pro.entity.CheckItem;
import cn.com.hzzc.industrial.pro.entity.ItemOption;

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
			String dId = "";
			if (js.has("detailId")) {
				dId = js.getString("detailId");
			}
			ae.setDetailId(dId);
		} catch (JSONException e) {
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

	/**
	 * 
	 * @param data
	 * @return
	 * @user:pang
	 * @data:2015年10月20日
	 * @todo:获取统计问卷所有问题
	 * @return:List<CheckItem>
	 */
	public static List<CheckItem> getStatItems(String data) {
		List<CheckItem> l = new ArrayList<CheckItem>();
		if (data != null && !"".equals(data)) {
			try {
				JSONObject js = new JSONObject(data);
				String ex = js.getString("activityStatisticItems");
				if (ex == null || "".equals(ex) || "null".equals(ex)) {
					return l;
				}
				JSONArray array = js.getJSONArray("activityStatisticItems");
				if (array.length() > 0) {
					for (int i = 0; i < array.length(); i++) {
						JSONObject j = array.getJSONObject(i);
						String Id = j.getString("id");
						String statisticId = j.getString("statisticId");
						String content = j.getString("content");

						CheckItem ci = new CheckItem();
						ci.setId(Id);
						ci.setItemName(content);
						ci.setActDetailId(statisticId);
						l.add(ci);
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		return l;

	}

	/**
	 * 
	 * @param data
	 * @return
	 * @user:pang
	 * @data:2015年10月21日
	 * @todo:解析活动
	 * @return:List<ActivityEntity>
	 */
	public static List<ActivityEntity> getActivities(String data) {
		List<ActivityEntity> aes = new ArrayList<ActivityEntity>();
		if (data != null && !"".equals(data)) {
			try {
				JSONObject js = new JSONObject(data);
				String str = js.getString("commenActivitys");
				if (str != null && !"".equals(str) && !"[]".equals(str)) {
					JSONArray array = js.getJSONArray("commenActivitys");
					for (int i = 0; i < array.length(); i++) {
						JSONObject j = array.getJSONObject(i);
						String id = j.getString("id");
						String type = j.getString("type");
						String status = j.getString("status");
						String title = j.getString("title");
						String createDate = j.getString("createDate");
						String introduction = j.getString("introduction");
						String ifNeedSelfSociety = j
								.getString("ifNeedSelfSociety");
						String imgId = j.getString("imgId");
						String beginDate = j.getString("beginDate");
						String endDate = j.getString("endDate");
						String readNum = j.getString("readNum");
						String societyId = j.getString("societyId");
						ActivityEntity ae = new ActivityEntity();
						ae.setId(id);
						ae.setSocietyId(societyId);
						ae.setBeginDate(beginDate);
						ae.setEndDate(endDate);
						ae.setCreateDate(createDate);
						ae.setTitle(title);
						ae.setStatus(status);
						ae.setImgId(imgId);
						ae.setReadNum(readNum);
						ae.setIntroduction(introduction);
						ae.setIfNeedSelfSociety("true"
								.equals(ifNeedSelfSociety) ? true : false);
						aes.add(ae);
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return aes;
	}

	/**
	 * 
	 * @param data
	 * @return
	 * @user:pang
	 * @data:2015年10月21日
	 * @todo:解析选项option
	 * @return:List<ItemOption>
	 */
	public static List<ItemOption> getStatItemOptions(String data) {
		List<ItemOption> l = new ArrayList<ItemOption>();
		if (data != null && !"".equals(data)) {
			try {
				JSONObject js = new JSONObject(data);
				String ex = js.getString("options");
				if (ex == null || "".equals(ex) || "null".equals(ex)) {
					return l;
				}
				JSONArray array = js.getJSONArray("activityStatisticItems");
				if (array.length() > 0) {
					for (int i = 0; i < array.length(); i++) {
						JSONObject j = array.getJSONObject(i);
						String id = j.getString("id");
						String statisticItemId = j.getString("statisticItemId");
						String option = j.getString("option");
						int num = j.getInt("num");

						ItemOption io = new ItemOption();
						io.setId(id);
						io.setStatisticItemId(statisticItemId);
						io.setNum(num);
						io.setOption(option);
						l.add(io);
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		return l;

	}
}
