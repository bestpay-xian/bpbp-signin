package com.bestpay.bpbp.signin.common;

import java.io.Serializable;

/**
 * Pageable
 * Author Liyang
 * Version 1.0
 * Date 2016/5/18 10:03
 */
public class Pageable implements Serializable {

	private static final long serialVersionUID = -3930180379790344299L;

	/** 默认页码 */
	private static final int DEFAULT_PAGE_NUMBER = 1;

	/** 默认每页记录数 */
	private static final int DEFAULT_ROWS = 10;

	/** 最大每页记录数 */
	private static final int MAX_PAGE_SIZE = 100;

	/** 页码 */
	private int pageNo = DEFAULT_PAGE_NUMBER;

	/** 每页记录数 */
	private int rowNum = DEFAULT_ROWS;

	/** 不分页显示 */
	private boolean isAll = false;

	/**
	 * 初始化一个新创建的Pageable对象
	 */
	public Pageable() {
	}

	/**
	 * 初始化一个新创建的Pageable对象
	 * 
	 * @param pageNumber
	 *            页码
	 * @param pageSize
	 *            每页记录数
	 */
	public Pageable(Integer pageNumber, Integer pageSize) {
		if (pageNumber != null && pageNumber >= 1) {
			this.pageNo = pageNumber;
		}
		if (pageSize != null && pageSize >= 1 && pageSize <= MAX_PAGE_SIZE) {
			this.rowNum = pageSize;
		}
	}

	/**
	 * 获取页码
	 * 
	 * @return 页码
	 */
	public int getPageNo() {
		return pageNo-1;
	}

	/**
	 * 设置页码
	 * 
	 * @param pageNumber
	 *            页码
	 */
	public void setPageNo(int pageNumber) {
		if (pageNumber < 1) {
			pageNumber = DEFAULT_PAGE_NUMBER;
		}
		this.pageNo = pageNumber;
	}

	/**
	 * 获取每页记录数
	 * 
	 * @return 每页记录数
	 */
	public int getRowNum() {
		return rowNum;
	}

	/**
	 * 设置每页记录数
	 * 
	 * @param pageSize
	 *            每页记录数
	 */
	public void setRowNum(int pageSize) {
		if (pageSize < 1 || pageSize > MAX_PAGE_SIZE) {
			pageSize = DEFAULT_ROWS;
		}
		this.rowNum = pageSize;
	}


	public boolean isAll() {
		return isAll;
	}

	public void setAll(boolean isAll) {
		this.isAll = isAll;
	}

	public static int getDefaultPageNumber() {
		return DEFAULT_PAGE_NUMBER;
	}

	public static int getDefaultRows() {
		return DEFAULT_ROWS;
	}

	public static int getMaxPageSize() {
		return MAX_PAGE_SIZE;
	}


}