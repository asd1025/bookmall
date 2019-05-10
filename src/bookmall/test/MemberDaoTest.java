package bookmall.test;

import java.util.List;

import bookmall.dao.MemberDao;
import bookmall.vo.MemberVo;

public class MemberDaoTest {
	public static void main(String[] args) {
		insert("김우빈","010-9854-8752","snmf@naver.com","1234");
		insert("김정은","010-2324-2125","cvb@naver.com","1234");
		insert("채희성","010-3424-3365","d22@naver.com","1234");
		insert("이유나","010-8248-2263","vxcv@naver.com","1234");
		insert("박현민","010-2884-4325","dsf@naver.com","1234");
		insert("정진우","010-8903-5552","zzzv@naver.com","1234");
		insert("김승빈","010-4562-9732","asdaa@naver.com","1234");
		insert("장남주","010-2842-4953","xvvx@naver.com","1234");
		getList();
		
	}
	public static void insert(String name,String tel,String email,String pwd) {
		MemberVo vo=new MemberVo();
		vo.setName(name);
		vo.setTel(tel);
		vo.setEmail(email);
		vo.setPwd(pwd);
		new MemberDao().insert(vo);
	}

	public static void  getList(){
		List<MemberVo> list=new MemberDao().getList();
		for(MemberVo vo:list) {
			System.out.println("Name:" +vo.getName()+" / Tel:"+vo.getTel()+
					"/ Email:"+vo.getEmail());
		}
	}
}
