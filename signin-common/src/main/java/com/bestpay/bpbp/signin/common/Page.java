package com.bestpay.bpbp.signin.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Page
 * Author Liyang
 * Version 1.0
 * Date 2016/5/18 10:03
 */
public class Page<T> implements Serializable {

	private static final long serialVersionUID = -2053800594583879853L;

	/** 内容 */
	private List<T> lists = new ArrayList<T>();

	/** 总记录数 */
	private long total;

	/** 当前页码 */
	private int pageNo;

	/** 总页码 */
	private int totalPages;

	/** 每页显示条数 */
	private int rowNum;

	/**
	 * 初始化一个新创建的Page对象
	 */
	public Page() {
		this.total = 0L;
	}

	/**
	 * @param content
	 *            内容
	 * @param total
	 *            总记录数
	 * @param pageable
	 *            分页信息
	 */
	public Page(List<T> content, long total, Pageable pageable) {
		this.lists.addAll(content);
		this.setTotal(total);
		this.setPageNo(pageable.getPageNo()+1);
		this.setRowNum(pageable.getRowNum());
		this.setTotalPages((int) Math.ceil((double) getTotal() / (double)this.getRowNum()));
	}

	public List<T> getLists() {
		return lists;
	}

	public void setLists(List<T> lists) {
		this.lists = lists;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
}