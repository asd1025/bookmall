package bookmall.vo;

public class OrdersVo {
private Long no;
private int pirce;
private String address;
private String ordersDate;
private Long memberNo;
private String code;

private String name;

public Long getNo() {
	return no;
}

public void setNo(Long no) {
	this.no = no;
}

public int getPirce() {
	return pirce;
}

public void setPirce(int pirce) {
	this.pirce = pirce;
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}

public String getOrdersDate() {
	return ordersDate;
}

public void setOrdersDate(String ordersDate) {
	this.ordersDate = ordersDate;
}

public Long getMemberNo() {
	return memberNo;
}

public void setMemberNo(Long memberNo) {
	this.memberNo = memberNo;
}

public String getCode() {
	return code;
}

public void setCode(String code) {
	this.code = code;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

@Override
public String toString() {
	return "OrdersVo [no=" + no + ", pirce=" + pirce + ", address=" + address + ", ordersDate=" + ordersDate
			+ ", memberNo=" + memberNo + ", code=" + code + ", name=" + name + "]";
}



 

}
