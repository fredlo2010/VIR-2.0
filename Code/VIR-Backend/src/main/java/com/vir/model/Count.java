package com.vir.model;

public class Count {

	private long awl;
	private long hi;
	private long med;
	private long low;
	private long total;

	private Count() {
	}

	public Count(long awl, long hi, long med, long low) {
		this.awl = awl;
		this.hi = hi;
		this.med = med;
		this.low = low;
		this.total = this.awl + this.hi + this.med + this.low;
	}
	public long getAwl() {
		return awl;
	}
	public void setAwl(long awl) {
		this.awl = awl;
	}
	public long getHi() {
		return hi;
	}
	public void setHi(long hi) {
		this.hi = hi;
	}
	public long getMed() {
		return med;
	}
	public void setMed(long med) {
		this.med = med;
	}
	public long getLow() {
		return low;
	}
	public void setLow(long low) {
		this.low = low;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
}
