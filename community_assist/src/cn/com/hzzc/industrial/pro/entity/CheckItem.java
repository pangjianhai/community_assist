package cn.com.hzzc.industrial.pro.entity;

/**
 * @todo 问卷调查和统计调查选项
 * @author pang
 *
 */
public class CheckItem {
	private String id;
	private String actId;// 所属活动
	private String itemName;// 问题
	private String type;// 类型
	private String itemOpts;// 选项

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getActId() {
		return actId;
	}

	public void setActId(String actId) {
		this.actId = actId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getItemOpts() {
		return itemOpts;
	}

	public void setItemOpts(String itemOpts) {
		this.itemOpts = itemOpts;
	}

}
