package bookmall.test;

import java.util.List;

import bookmall.dao.CategoryDao;
import bookmall.vo.CategoryVo;

public class CategoryDaoTest {
public static void main(String[] args) {
 	insert("소설");
	insert("수필");
	insert("컴퓨터/IT");
	insert("인문");
	insert("경제");
	insert("예술");
	getList();
	
}
public static void insert(String name) {
	new CategoryDao().insert(new CategoryVo(name));
}

public static void  getList(){
	List<CategoryVo> list=new CategoryDao().getList();
	for(CategoryVo vo:list) System.out.println(vo.getName());
}
}
