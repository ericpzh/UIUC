/**
 * @author 233
 */
import java.awt.Color;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class KeyValueMap { // aka Dictionary or Map
	private Map<String, Color> mp = new HashMap<String,Color>();
	
	/**
	 * Adds a key and value. If the key already exists, it replaces the original
	 * entry.
	 */
	public void add(String key, Color value) {
		//TODO
		//Map<String,Color> map = new HashMap<String,Color>(mp.size()+1);
		if (mp.isEmpty()){}
		else{
			mp.remove(key);				
		}	
			mp.put(key, value);
	}

	/**
	 * Returns particular Color object previously added to this map.
	 */
	public Color find(String key) {
		//TODO
		return mp.get(key);
		//throw new RuntimeException("Ho");
	}

	/**
	 * Returns true if the key exists in this map.
	 */
	public boolean exists(String key) {
		//TODO
		if (mp.containsKey(key)) return true;
		else return false;
		//throw  new RuntimeException("Hi");
	}

	/**
	 * Removes the key (and the color) from this map.
	 */
	public void remove(String key) {
		if(!mp.isEmpty()) mp.remove(key);
		//TODO qishijiushi map chujijiaocheng
	}

}
