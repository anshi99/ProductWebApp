package constants;

public class Constants {
	
	public static final String GET_USER_QUERY = "FROM User WHERE username=:u";
	public static final String IMAGE_URL_PATTERN = "([^\\s]+(\\.(?i)(jpg|png|gif|bmp|jpeg))$)";
	public static final String GET_PRODUCT_QUERY = "FROM Product WHERE id=:id";
	public static final String DELETE_PRODUCT_QUERY = "DELETE Product WHERE id=:id";
	public static final String USER_SESSION = "user";
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String USERNAME_ERROR = "username-error";
	public static final String PASSWORD_ERROR = "password-error";
	public static final String ID = "id";
	public static final String TITLE = "title";
	public static final String QUANTITY = "quantity";
	public static final String SIZE = "size";
	public static final String IMAGE_URL = "imgUrl";
	public static final String ID_ERROR = "id-error";
	public static final String TITLE_ERROR = "title-error";
	public static final String QUANTITY_ERROR = "quantity-error";
	public static final String SIZE_ERROR = "size-error";
	public static final String IMAGE_URL_ERROR = "imgurl-error";
	public static final String FIELD_CANNOT_BE_EMPTY = "This field cannot be empty.";
	public static final String USERNAME_DOES_NOT_EXISTS = "Username does not exists.";
	public static final String INCORRECT_PASSWORD = "Incorrect Password.";
	public static final String INVALID_VALUE = "Invalid Value.";
	public static final String INVALID_IMAGE_URL = "Enter a valid Image URL.";
	public static final String ID_DOES_NOT_EXISTS = "ID does not exists.";
}
