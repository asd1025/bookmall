package bookmall.vo;

public class BookOrdersVo {
private Long bookNo;
private Long ordersNo;
private int count;

private String bookName;
private int price;
private String categoryName;
public Long getBookNo() {
	return bookNo;
}
public void setBookNo(Long bookNo) {
	this.bookNo = bookNo;
}
public Long getOrdersNo() {
	return ordersNo;
}
public void setOrdersNo(Long ordersNo) {
	this.ordersNo = ordersNo;
}
public int getCount() {
	return count;
}
public void setCount(int count) {
	this.count = count;
}
public String getBookName() {
	return bookName;
}
public void setBookName(String bookName) {
	this.bookName = bookName;
}
public int getPrice() {
	return price;
}
public void setPrice(int price) {
	this.price = price;
}
public String getCategoryName() {
	return categoryName;
}
public void setCategoryName(String categoryName) {
	this.categoryName = categoryName;
}
@Override
public String toString() {
	return "BookOrdersVo [bookNo=" + bookNo + ", ordersNo=" + ordersNo + ", count=" + count + ", bookName=" + bookName
			+ ", price=" + price + ", categoryName=" + categoryName + "]";
}
 


}
