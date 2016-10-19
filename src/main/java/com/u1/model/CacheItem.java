package com.u1.model;

import java.util.Date;

public class CacheItem<O> implements java.io.Serializable{
	private Long lastupdate;
	private O o;
	private Long cacheTime; 
	public CacheItem(){
		this.cacheTime=5*60*1000l;//default 5 mins
	}
	public CacheItem(Long cacheTime){
		this.cacheTime=cacheTime;
	}
	public Long getCacheTime() {
		return cacheTime;
	}
	public void setCacheTime(Long cacheTime) {
		this.cacheTime = cacheTime;
	}
	public Long getLastupdate() {
		return lastupdate;
	}
	public void setLastupdate(Long lastupdate) {
		this.lastupdate = lastupdate;
	}
	public O getO() {
		return o;
	}
	public void setO(O o) {
		this.o = o;
	}
	public void updateCache(O o){
		if(isExpiry()){
			forceUpdateCache(o);
		}
	}
	public void forceUpdateCache(O o){
		this.o = o;
		this.lastupdate=System.currentTimeMillis();
	}
	public boolean isExpiry(){
		if(lastupdate==null)
			return true;
		if(System.currentTimeMillis()-lastupdate>cacheTime)
			return true;
		return false;
	}
}
