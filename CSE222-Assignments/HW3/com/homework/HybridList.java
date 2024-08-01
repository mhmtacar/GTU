package com.homework;

public class HybridList {
	
   private KWSingleLinkedListFurniture furniture;
   
   public HybridList() {
	    furniture=new KWSingleLinkedListFurniture();
   }
   
   /** Get the furniture name
	@param index The position where wanted furniture name is placed
	@return furniture name
   */
   public String get(int index) {
		String furniture_name;
		furniture_name=furniture.get(index);
		return furniture_name;
   }
   
   /** Get the model num
	@param index The position where wanted model num is placed
	@return model num
  */
   public int get2(int index) {
		int model_num;
		model_num=furniture.get2(index);
		return model_num;
  }
   
   /** Get the color num
	@param index The position where wanted color num is placed
	@return color num
  */
   public int get3(int index) {
		int color_num;
		color_num=furniture.get3(index);
		return color_num;
 }
   
   /** Get the furniture num
	@return furniture num
  */
   public int getSize() {
	   return furniture.getSize();
   }
   
   /** Sets the furniture name at the given index
	@param index The position where new furniture name is placed
	@param furniture_name The new furniture name
	@return old furniture name
  */
   public String set(int index, String furniture_name) {
		String result;
		result=furniture.set(index, furniture_name);
		return result;
	}
   
   /** Sets the model num at the given index
	@param index The position where new model num is placed
	@param model_num The new model num
	@return old model num
  */
   public int set2(int index, int model_num) {
		int result;
		result=furniture.set2(index, model_num);
		return result;
	}
   
   /** Sets the color num at the given index
	@param index The position where new color num is placed
	@param color_num The new color num
	@return old color num
   */
   public int set3(int index, int color_num) {
		int result;
		result=furniture.set3(index, color_num);
		return result;
	}
   
   /** Add new furniture to the given index
	@param index The position where furniture is added
	@param furniture_name The furniture name to be inserted
	@param model_num The model num to be inserted
	@param color_num The color num to be inserted
   */
   public void add(int index, String furniture_name,int model_num,int color_num) {
		
	   furniture.add(index,furniture_name,model_num,color_num);
	    
	}
   
   /** Removes furniture from given index
	@param index The position where furniture is removed
	@return removed furniture name
   */
   public String remove(int index) {
		String res;
		res=furniture.remove(index);
		return res;
	}
   
   
}
