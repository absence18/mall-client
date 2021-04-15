package mall.client.vo;

public class Cart {
	
	private int cartNo;
	private String clientName;
	private int ebookNo;
	private String cartDate;
	private String clientMail;
	
	public int getCartNo() {
		return cartNo;
	}
	public void setCartNo(int cartNo) {
		this.cartNo = cartNo;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public int getEbookNo() {
		return ebookNo;
	}
	public void setEbookNo(int ebookNo) {
		this.ebookNo = ebookNo;
	}
	public String getCartDate() {
		return cartDate;
	}
	public void setCartDate(String cartDate) {
		this.cartDate = cartDate;
	}
	public String getClientMail() {
		return clientMail;
	}
	public void setClientMail(String clientMail) {
		this.clientMail = clientMail;
	}
	
	@Override
	public String toString() {
		return "Cart [cartNo=" + cartNo + ", clientName=" + clientName + ", ebookNo=" + ebookNo + ", cartDate="
				+ cartDate + "]";
	}
	
}
