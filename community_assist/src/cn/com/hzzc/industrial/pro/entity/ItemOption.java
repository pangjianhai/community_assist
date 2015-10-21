package cn.com.hzzc.industrial.pro.entity;

/**
 * @todo 统计条目
 * @author pang
 *
 */
public class ItemOption {

	private String id;
	private String statisticItemId;
	private String option;
	private int num;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatisticItemId() {
		return statisticItemId;
	}

	public void setStatisticItemId(String statisticItemId) {
		this.statisticItemId = statisticItemId;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

}
