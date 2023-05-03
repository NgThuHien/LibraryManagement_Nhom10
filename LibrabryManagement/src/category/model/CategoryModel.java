package category.model;

import java.util.ArrayList;
import java.util.Objects;

import category.dao.CategoryDAOImp;


public class CategoryModel {
	private int categoryId;
	private String categoryName;
	//private int amoutC;
	
	
	public CategoryModel() {
		
	}

	public CategoryModel(int categoryId, String categoryName){// int amoutC) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		//this.amoutC = amoutC;
	}

	
	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

//	public int getAmoutC() {
//		return amoutC;
//	}
//
//	public void setAmoutC(int amoutC) {
//		this.amoutC = amoutC;
//	}

	public static ArrayList<CategoryModel> getAllCategory(){
		return CategoryDAOImp.getInstance().selectAll();
		
	}
	public static CategoryModel getCtgById(int ctg) {
		return CategoryDAOImp.getInstance().selectById(ctg);
	}

	@Override
	public int hashCode() {
		return Objects.hash( categoryId, categoryName);
//		amoutC,
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CategoryModel other = (CategoryModel) obj;
//		 amoutC == other.amoutC &&
		return categoryId == other.categoryId
				&& Objects.equals(categoryName, other.categoryName);
	}

	
	
	
	
}
