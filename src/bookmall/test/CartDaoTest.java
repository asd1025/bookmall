package bookmall.test;


import java.util.List;

import bookmall.dao.CartDao;
import bookmall.vo.CartVo;

public class CartDaoTest {
	
public static void main(String[] args) {
	insert(1L,1L,2);
	insert(2L,1L,1);
	insert(10L,2L,1);
	insert(14L,3L,1);
	getList(1L);
	
}
public static void insert(Long bookNo,Long memberNo,int count) {
	CartVo vo=new CartVo();
	vo.setBookNo(bookNo);
	vo.setMemberNo(memberNo);
	vo.setCount(count);
	new CartDao().insert(vo);
}

public static void  getList(Long memberNo){
	List<CartVo> list=new CartDao().getList(memberNo);
	String name=null;
	for(CartVo vo:list) {
		if(name==null) {
			System.out.println("---------------- "+vo.getName()+"님의 카트 도서 리스트 ----------------");
			name=vo.getName();
		} 
		System.out.println(
				 "제목:"+vo.getTitle()+", 수량:"+vo.getCount());
		 
	}
}
}
