package vailidationInterface;

public interface Vailidation {
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public boolean validateID(String id);
	
	/**
	 * 
	 * @param title
	 * @return
	 */
	public boolean validateTitle(String title);
	
	/**
	 * 
	 * @param quantity
	 * @return
	 */
	public boolean validateQuantity(String quantity);
	
	/**
	 * 
	 * @param size
	 * @return
	 */
	public boolean validateSize(String size);
	
	/**
	 * 
	 * @param imgUrl
	 * @return
	 */
	public boolean validateImgUrl(String imgUrl);
}
