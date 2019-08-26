package vora.priya.JDBC;

public interface Storable {
	String serialize();
	Object deserialize(String data);
	int serializedSize();
	
	
}
