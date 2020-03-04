/**
 * @author 233
 * Complete the following GeocacheList, to ensure all unit tests pass.
 * There are several errors in the code below
 *
 * Hint: Get the Geocache class working and passing its tests first.
 */
public class GeocacheList {
	private Geocache[] data = new Geocache[0];
	private int size = 0;
	
	public Geocache getGeocache(int i) {
		if(data.length > 0) return data[i];
		else return null;
	}

	public int getSize() {
		return size;
	}
	public GeocacheList(){
		data = new Geocache[0];
		size = 0;
	}
	public GeocacheList(Geocache[] g) {
		data = g;
		size = g.length;
		
	}

	public GeocacheList(GeocacheList other, boolean deepCopy) {
		if (!deepCopy) {
			data = new Geocache[other.getSize()];
			size = other.getSize();
			for (int i = 0; i < other.getSize() ; i ++){
				data[i] = other.data[i];
				}
			}
		
		else{
			GeocacheList a233 = new GeocacheList();
			/*for (int i = 0; i < other.getSize(); i++){
				
				data[i] = other.data[i];
				data[i].setX(other.data[i].getX());
				data[i].setY(other.data[i].getY());
				
			}*/
			for(int i = 0; i < other.getSize(); i++) {
				a233.add(other.copy(other.data[i].getX(), other.data[i].getY())); 
			}
			//System.out.println(a233.toString());
			data = new Geocache[a233.getSize()];
			for (int i = 0 ; i < a233.getSize();i++) {
				data[i] = a233.data[i];
				//System.out.println(data[i]);
			}
			size = a233.getSize();
			
		}
	}
	public Geocache copy(double a,double b){
		Geocache g= new Geocache(a,b);
		g.setX(a);
		g.setY(b);
		return g;
	}
	public void add(Geocache p) {
		Geocache[] result = new Geocache[data.length+1];
		result[data.length] = p;
		size ++;
		for (int k = 0; k < data.length  ; k++){
			result[k] = data[k];
		}
		data = new Geocache[data.length+1];
		for (int i = 0 ; i < data.length  ; i ++){
			data[i] = result[i];
		}
		//TODO kenengba
	}

	public void removeFromTop() {
		Geocache[] result = new Geocache[data.length-1];
		for (int k = 0; k < data.length-1 ; k++){
			result[k] = data[k+1];
		}
		size -- ;
		data = new Geocache[data.length-1];
		for (int i = 0 ; i < data.length-1 ; i ++){
			data[i] = result[i];
		}
	}

	public String toString() {
		//StringBuffer s = new StringBuffer("GeocacheList:");
		String s = "GeocacheList:";
		for (int k = 0 ; k < data.length ; k++){
			s += data[k];
			if(k!= data.length-1) s+=",";
		}
		//TODO kenengshiyaozijixieba.
		return s;
	}
}