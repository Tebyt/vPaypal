package com.TopBang.vPaypal;
/**
 * 作者：TopBang
 * 时间:2013.08
 * 功能：验证码控制接口
 * */
public interface CheckAction 
{
	
	// 设置验证码暂时定为四个，但是以后可以改
	public void setCheckNum(int [] chenckNum);
	
	// 获取验证码
	public int [] getCheckNum();
	
	// 更新验证码显示
	public void invaliChenkNum();
}
