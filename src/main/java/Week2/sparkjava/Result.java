package Week2.sparkjava;

public class Result {
	private String key;
	private int squareResult;

	public Result(String k) {
		this.setKey(k);

		int n = Integer.parseInt(key);
		this.squareResult = n*n;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getSquare() {
		return squareResult;
	}
}