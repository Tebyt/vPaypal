package com.TopBang.vPaypal;
/**
 * ���ߣ�TopBang
 * ʱ��:2013.08
 * ���ܣ���֤����ƽӿ�
 * */
public interface CheckAction 
{
	
	// ������֤����ʱ��Ϊ�ĸ��������Ժ���Ը�
	public void setCheckNum(int [] chenckNum);
	
	// ��ȡ��֤��
	public int [] getCheckNum();
	
	// ������֤����ʾ
	public void invaliChenkNum();
}
