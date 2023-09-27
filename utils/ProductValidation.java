package utils;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import constants.Constants;
import vailidationInterface.Vailidation;

public class ProductValidation implements Vailidation{
	private HttpSession session;

	public ProductValidation(HttpSession session) {
		this.session = session;
	}

	// Method to validate id for product
	@Override
	public boolean validateID(String id) {
		// check if title is not empty
				if (id.trim() == "" || id == null) {
					session.setAttribute(Constants.ID_ERROR, Constants.FIELD_CANNOT_BE_EMPTY);
					return false;
				}
				// handle invalid size value error
				try {
					if (Integer.parseInt(id) < 1) {
						throw new IllegalArgumentException();
					}
				} catch (IllegalArgumentException e) {
					session.setAttribute(Constants.ID_ERROR, Constants.INVALID_VALUE);
					return false;
				}

				return true;
	}



	// Method to validate title for product
	@Override
	public boolean validateTitle(String title) {
		if (title.trim() == "" || title == null) {
			session.setAttribute(Constants.TITLE_ERROR, Constants.FIELD_CANNOT_BE_EMPTY);
			return false;
		}
		return true;
	}


	// Method to validate quantity of product
	@Override
	public boolean validateQuantity(String quantity) {
		// check if quantity is not empty
				if (quantity.trim() == "" || quantity == null) {
					session.setAttribute(Constants.QUANTITY_ERROR, Constants.FIELD_CANNOT_BE_EMPTY);
					return false;
				}

				// handle invalid quantity value error
				try {
					if (Integer.parseInt(quantity) < 0) {
						throw new IllegalArgumentException();
					}
				} catch (IllegalArgumentException e) {
					session.setAttribute(Constants.QUANTITY_ERROR, Constants.INVALID_VALUE);
					return false;
				}

				return true;
	}

	

	// Method to validate size of product
	@Override
	public boolean validateSize(String size) {
		// check if size is not empty
		if (size.trim() == "" || size == null) {
			session.setAttribute(Constants.SIZE_ERROR, Constants.FIELD_CANNOT_BE_EMPTY);
			return false;
		}

		// handle invalid size value error
		try {
			if (Integer.parseInt(size) < 0) {
				throw new IllegalArgumentException();
			}
		} catch (IllegalArgumentException e) {
			session.setAttribute(Constants.SIZE_ERROR, Constants.INVALID_VALUE);
			return false;
		}

		return true;
	}


	@Override
	public boolean validateImgUrl(String imgUrl) {
		// check if image field is not empty
		if (imgUrl.trim() == "" || imgUrl == null) {
			session.setAttribute(Constants.IMAGE_URL_ERROR, Constants.FIELD_CANNOT_BE_EMPTY);
			return false;
		}

		// check if the url is a valid image url
		if (!Pattern.matches(Constants.IMAGE_URL_PATTERN, imgUrl)) {
			session.setAttribute(Constants.IMAGE_URL_ERROR, Constants.INVALID_IMAGE_URL);
			return false;
		}

		return true;
	}

}
