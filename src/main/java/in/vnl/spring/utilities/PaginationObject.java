package in.vnl.spring.utilities;

public class PaginationObject {

	private int totalPages;
	private int currentPage;
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public PaginationObject(int totalPages, int currentPage) {
		super();
		this.totalPages = totalPages;
		this.currentPage = currentPage;
	}
	
	
}
