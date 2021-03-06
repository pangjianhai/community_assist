package cn.com.hzzc.industrial.pro.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.com.hzzc.industrial.pro.entity.ActivityEntity;
import cn.com.hzzc.industrial.pro.entity.CheckItem;
import cn.com.hzzc.industrial.pro.entity.FavoriteActiEntity;
import cn.com.hzzc.industrial.pro.entity.ItemOption;
import cn.com.hzzc.industrial.pro.entity.OffLineActEntity;
import cn.com.hzzc.industrial.pro.entity.TopicEntity;

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
				JSONArray array = js.getJSONArray("options");
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

	/**
	 * @param data
	 * @return
	 * @user:pang
	 * @data:2015年10月23日
	 * @todo:解析线下活动bean
	 * @return:OffLineActEntity
	 */
	public static OffLineActEntity getOffAct(String data) {
		OffLineActEntity b = new OffLineActEntity();
		if (data != null && !"".equals(data)) {
			try {
				JSONObject j = new JSONObject(data);
				String id = j.getString("id");
				String content = j.getString("content");
				String img0 = j.getString("img0");
				String img1 = j.getString("img1");
				if ("null".equals(img0)) {
					img0 = "";
				}
				if ("null".equals(img1)) {
					img1 = "";
				}
				b.setId(id);
				b.setContent(content);
				b.setImg0(img0);
				b.setImg1(img1);
				return b;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * @param data
	 * @return
	 * @user:pang
	 * @data:2015年10月26日
	 * @todo:解析促销活动
	 * @return:FavoriteActiEntity
	 */
	public static FavoriteActiEntity getFavoriteEntity(String data) {
		FavoriteActiEntity b = new FavoriteActiEntity();
		if (data != null && !"".equals(data)) {
			try {
				JSONObject j = new JSONObject(data);
				String id = j.getString("id");
				String content = j.getString("content");
				if ("null".equals(content)) {
					content = "";
				}
				String name = j.getString("name");
				if ("null".equals(name)) {
					name = "";
				}
				String oldPrice = j.getString("oldPrice");
				String newPrice = j.getString("newPrice");
				String buyStatus = j.getString("buyStatus");
				String commonId = j.getString("commonId");
				String address = j.getString("address");
				if ("null".equals(address)) {
					address = "";
				}
				String img0 = j.getString("img0");
				String img1 = j.getString("img1");
				if ("null".equals(img0)) {
					img0 = "";
				}
				if ("null".equals(img1)) {
					img1 = "";
				}
				b.setId(id);
				b.setName(name);
				b.setOldPrice(oldPrice);
				b.setNewPrice(newPrice);
				b.setAddress(address);
				b.setContent(content);
				b.setImg0(img0);
				b.setImg1(img1);
				return b;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 
	 * @param data
	 * @return
	 * @user:pang
	 * @data:2015年10月27日
	 * @todo:解析主题信息
	 * @return:TopicEntity
	 */
	public static TopicEntity parseEntityByJSON(String data) {
		try {
			JSONObject obj = new JSONObject(data);
			String id = obj.getString("id");
			String name = obj.getString("name");
			String content = obj.getString("content");
			String createDate = obj.getString("createDate");
			String imgId = obj.getString("imgId");
			TopicEntity bean = new TopicEntity();
			bean.setId(id);
			bean.setDesc(content);
			bean.setName(name);
			bean.setImgId(imgId);
			bean.setCreateDate(createDate);
			return bean;
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * @param data
	 * @return
	 * @user:pang
	 * @data:2015年10月27日
	 * @todo:解析
	 * @return:List<TopicEntity>
	 */
	public static List<TopicEntity> getTopics(String data) {
		System.out.println("util data:"+data);
		List<TopicEntity> lst = new ArrayList<TopicEntity>();
		try {
			JSONObject j = new JSONObject(data);
			JSONArray array = j.getJSONArray("baseTopics");
			if (array != null && array.length() > 0) {
				for (int i = 0; i < array.length(); i++) {
					JSONObject o = array.getJSONObject(i);
					String id = o.getString("id");
					String name = o.getString("name");
					String content = o.getString("content");
					String createDate = o.getString("createDate");
					String imgId = o.getString("imgId");
					String userId = o.getString("userId");
					TopicEntity te = new TopicEntity();
					te.setId(id);
					te.setName(name);
					te.setDesc(content);
					te.setImgId(imgId);
					te.setCreateDate(createDate);
					lst.add(te);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return lst;
	}
}
