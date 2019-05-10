package bookmall.test;

import java.util.List;
import bookmall.dao.BookDao;
import bookmall.vo.BookVo;

public class BookDaoTest {
	public static void main(String[] args) {
		
		
		insert("돌이킬 수 없는 약속",13500 ,1L);
		insert("인어가 잠든 집",16000 ,1L);
		insert("편지",13000 ,1L);
		insert("봉제인형 살인사건",13500 ,1L);
		insert("숨",15000 ,1L);
		insert("여행의 이유",12000 ,2L);
		insert("연필로 쓰기",13950 ,2L);
		insert("스무 살, 빨간머리 앤",13050 ,2L);
		insert("널 보러 왔어",13500 ,2L);
		insert("걸어서 환장속으로",13050 ,2L);
		insert("자바 최적화",35100 ,3L);
		insert("커리어 스킬",25200 ,3L);
		insert("GREATE CODE.1",27000 ,3L);
		insert("Vue.js 철저 입문",27000 ,3L);
		insert("스피노자 메뉴얼",13500 ,4L);
		insert("라틴어 수업",13500 ,4L);
		insert("마음으로 부터 일곱 발자국",14400,4L);
		insert("라틴어 수업",13500,4L);
		insert("스피노자 메뉴얼",13500,4L);
		insert("초격자",16200,5L);
		insert("신뢰이동",14400,5L);
		insert("팀쿡",22500,5L);
		insert("결단",15300,5L);
		insert("그림의역사",19800,6L);
		insert("다시 그림이다",22500,6L);
		insert("프레디",16200,6L);
		 
		 
		getList();
		
	}
	public static void insert(String title,int price,Long categoryNo) {
		BookVo vo=new BookVo();
		vo.setTitle(title);
		vo.setPrice(price);
		vo.setCategoryNo(categoryNo);
		new BookDao().insert(vo);
	}

	public static void  getList(){
		List<BookVo> list=new BookDao().getList();
		for(BookVo vo:list) 
			System.out.println("Title:"+vo.getTitle()+"/ Price:"+vo.getPrice()+
					"/ Category:"+vo.getCategoryName());
	}
}
