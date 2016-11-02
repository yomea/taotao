package com.taotao.common.pojo;

import java.util.List;

public class EUDatagridResult {
	private long total;
	
	private List<?> rows;

	public long getTotal() {
		return total;
	}
	
	public EUDatagridResult() {
		
	}

	public EUDatagridResult(long total, List<?> rows) {
		super();
		this.total = total;
		this.rows = rows;
	}



	public void setTotal(long total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	
	
}
