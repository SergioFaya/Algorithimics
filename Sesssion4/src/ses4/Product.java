package ses4;

public class Product {

	private int id;
	private int length;
	private float request;
	
	public Product(int id, int length, float request){
		this.id = id;
		this.length = length;
		this.request = request;
	}

	public int getId() {
		return id;
	}

	public int getLength() {
		return length;
	}

	public float getRequest() {
		return request;
	}
	
	
}
