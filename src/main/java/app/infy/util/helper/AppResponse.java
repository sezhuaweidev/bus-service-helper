package app.infy.util.helper;

import java.util.Arrays;

public class AppResponse<T> {
	private T data;
	private String[] additionalData;
	
	public AppResponse(T data, String...additionalData) { 
		super(); 
		this.data = data;
		this.additionalData = additionalData;
	}

	public T getData() {
		return data;
	}

	public String[] getAdditionalData() {
		return additionalData;
	}

	@Override
	public String toString() {
		return "AppResponse [data=" + data + ", additionalData=" + Arrays.deepToString(additionalData) + "]";
	}
}
