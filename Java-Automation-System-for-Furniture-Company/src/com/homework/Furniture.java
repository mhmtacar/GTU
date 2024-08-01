package com.homework;

	public abstract class Furniture {

		private String name;
		private int model_num;
		private int color_num;
		   
		
		/**
		 * It initializes name,model_num and color_num variable.
		 * @param name
		 * @param model_num
		 * @param color_num
		 */
		public Furniture(String name,int model_num,int color_num) {
			
			this.name=name;
			this.model_num=model_num;
			this.color_num=color_num;
			
		}

		/**
		 * It returns name.
		 * @return name
		 */
		public String getName() {
			return name;
		}
		
		/**
		 * It sets name variable.
		 * @param name
		 */
		public void setName(String name) {
			this.name = name;
		}
		
		/**
		 * It returns model_num.
		 * @return model_num
		 */
		public int getModel_num() {
			return model_num;
		}
		
		/**
		 * It sets model_num variable.
		 * @param model_num
		 */
		public void setModel_num(int model_num) {
			this.model_num = model_num;
		}
		
		/**
		 * It returns color_num.
		 * @return color_num
		 */
		public int getColor_num() {
			return color_num;
		}
		
		/**
		 * It sets color_num variable.
		 * @param color_num
		 */
		public void setColor_num(int color_num) {
			this.color_num = color_num;
		}

		abstract public void showInfos();


}


