package bookmall.main;

import java.util.List;

import bookmall.dao.BookDao;
import bookmall.dao.BookOrdersDao;
import bookmall.dao.CartDao;
import bookmall.dao.CategoryDao;
import bookmall.dao.MemberDao;
import bookmall.dao.OrdersDao;
import bookmall.vo.BookOrdersVo;
import bookmall.vo.BookVo;
import bookmall.vo.CartVo;
import bookmall.vo.CategoryVo;
import bookmall.vo.MemberVo;
import bookmall.vo.OrdersVo;

public class BookMall {
public static void main(String[] args) {
 
	getMemberList();
    getCaegoeryList();
    getBookList();
    getCartList(1L);
    getOrderList(1L);
    getOrderBookList(1L);
		 
}
public static void getMemberList(){
	List<MemberVo> list=new MemberDao().getList();
	System.out.println("************************* 현재 회원 리스트를 보여줍니다 **************************");
	for(MemberVo vo:list) {
		System.out.println("이름:" +vo.getName()+" | 번호:"+vo.getTel()+
				" | 이메일:"+vo.getEmail());
	}
}

public static void  getCaegoeryList(){
	List<CategoryVo> list=new CategoryDao().getList();
	System.out.println("************************* 카테고리 리스트를 보여줍니다 **************************");
	for(CategoryVo vo:list) {
		System.out.println("- "+vo.getName());
	}

}
public static void  getBookList(){
	System.out.println("************************* 책 리스트를 보여줍니다 **************************");
	List<BookVo> list=new BookDao().getList();
	for(BookVo vo:list) 
		System.out.println("제목:"+vo.getTitle()+" | 가격:"+vo.getPrice()+
				" | 카테고리:"+vo.getCategoryName());
	
}
public static void  getCartList(Long memberNo){
	List<CartVo> list=new CartDao().getList(memberNo);
	String name=null;
	for(CartVo vo:list) {
		if(name==null) {
			System.out.println("************************* "+vo.getName()+"님의 카트 도서 리스트 *************************");
			name=vo.getName();
		} 
		System.out.println(
				 "제목:"+vo.getTitle()+", 수량:"+vo.getCount());
		 
	}
	
} 
public static void  getOrderList(Long MemeberNo){
	List<OrdersVo> list=new OrdersDao().getOrderList(MemeberNo);
	String name=null;
	for(OrdersVo vo:list) {
		if(name==null) {
			name=vo.getName();
			System.out.println("************************* "+name+"님의 주문내역 *************************");
		}
	  System.out.println("주문번호:"+vo.getCode()+" | 주소:"
			  + vo.getAddress()+" | 가격:"+vo.getPirce()+" | 구매일:"+vo.getOrdersDate());
	}
	
}
public static void  getOrderBookList(Long MemeberNo){
	List<BookOrdersVo> list=new BookOrdersDao().getOrderBookList(MemeberNo);
	System.out.println("************************* 주문된 책 리스트 *************************");
	for(BookOrdersVo vo:list) {
		System.out.println("제목:"+vo.getBookName()+" | 수량:"+vo.getCount()
		+" | 가격:"+vo.getPrice()+" | 분류:"+vo.getCategoryName());
	}
	
}
}
