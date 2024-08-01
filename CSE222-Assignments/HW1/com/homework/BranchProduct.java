package com.homework;

	public class BranchProduct extends Furniture {
		
	    private int [][] product_num;
	    
	    /**
	     * It initializes name,model_num,color_num variable and initializes all product num to 1.
	     * @param name
	     * @param model_num
	     * @param color_num
	     */
		public BranchProduct(String name,int model_num,int color_num) {
			
			super(name,model_num,color_num);
			
			product_num=new int[model_num][color_num];
			
			for(int i=0;i<model_num;i++) {
				for(int j=0;j<color_num;j++) {
					product_num[i][j]=1;
				}
			}
			
		}
		
		/**
		 * It prints product infos.
		 */
		@Override
		public void showInfos() {
			
			System.out.println("Furniture name:" + getName());
			System.out.println("Furniture model num:" + getModel_num());
			System.out.println("Furniture color_num:" + getColor_num());
			
		}

		/**
		 * It returns product num.
		 * @param model_index
		 * @param color_index
		 * @return product_num[model_index][color_index]
		 */
		public int get_ProductNum(int model_index,int color_index) {
			return product_num[model_index][color_index];
		}
		
		/**
		 * It increments product num according to product_val parameter.
		 * @param model_index
		 * @param color_index
		 * @param product_val
		 */
		public void add_product(int model_index,int color_index,int product_val) {
			product_num[model_index][color_index]+=product_val;
		}
		
		/**
		 * It decrements product num according to product_val parameter.
		 * @param model_index
		 * @param color_index
		 * @param product_val
		 */
		public void remove_product(int model_index,int color_index,int product_val) {
			product_num[model_index][color_index]-=product_val;
		}
		
		
	}


