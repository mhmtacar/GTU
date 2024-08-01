package com.homework;

	public interface CustomerInfos {
		
		public String getAddress(int index);
		public void setAddress(int index,String address);
		public String getPhone_num(int index);
		public void setPhone_num(int index,String phone_num);
		public void shopInfos(int index);
		public void shopInfos(int index,String address,String phone_num);
	}

