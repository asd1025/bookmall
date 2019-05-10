package bookmall.test;

import java.util.List;
import bookmall.dao.BookOrdersDao;
import bookmall.vo.BookOrdersVo;

public class BookOrdersDaoTest {
	public static void main(String[] args) {
		
		insert(1L, 1L, 1);
		insert(2L, 1L, 1);
		insert(4L, 2L, 1);
		insert(5L, 2L, 1);
		insert(13L, 3L, 2);
		insert(5L, 4L, 2);
		insert(6L, 5L, 2);
		getList(1L);
		
	}
	public static void insert( Long bookNo,Long ordersNo,int count) {
		BookOrdersVo vo=new BookOrdersVo();
		vo.setBookNo(bookNo); 
		vo.setOrdersNo(ordersNo);
		vo.setCount(count);
		new BookOrdersDao().insert(vo);
	}

	public static void  getList(Long ordersNo){
		List<BookOrdersVo> list=new BookOrdersDao().getOrderBookList(ordersNo);
		System.out.println("=================== 주문된 책 리스트 ===================");
		for(BookOrdersVo vo:list) {
			System.out.println("제목:"+vo.getBookName()+" | 수량:"+vo.getCount()
			+" | 가격:"+vo.getPrice()+" | 분류:"+vo.getCategoryName());
		}
	}
}
