package bookmall.test;


import java.util.List;

import bookmall.dao.OrdersDao;
import bookmall.vo.OrdersVo;


public class OrdersDaoTest {

	public static void main(String[] args) {
		
		insert(29500,"서울시 강남구 역삼동 자이APT",1L);
		insert(26500,"서울시 강남구 대치동 동부APT",2L);
		insert(54000,"서울시 강남구 대치동 동부APT",2L);
		insert(30000,"서울시 강남구 도곡동 삼성APT",3L);
		insert(24000,"서울시 강남구 청담동 자이APT",4L);
		getOrderList(2L);
		
	}
	public static void insert(int price,String address,Long memberNo) {
		OrdersVo vo=new OrdersVo();
		vo.setPirce(price);
		vo.setAddress(address);
		vo.setMemberNo(memberNo);
		new OrdersDao().insert(vo);
	}
 
	public static void   getOrderList(Long MemeberNo){
		List<OrdersVo> list=new OrdersDao().getOrderList(MemeberNo);
		String name=null;
		for(OrdersVo vo:list) {
			if(name==null) {
				name=vo.getName();
				System.out.println("------------- "+name+"님의 주문내역 --------------");
			}
		  System.out.println("주문번호:"+vo.getCode()+" | 주소:"
				  + vo.getAddress()+" | 가격:"+vo.getPirce()+" | 구매일:"+vo.getOrdersDate());
		}
	}
	
}
