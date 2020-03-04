/**
 * 
 * @author 233
 *
 */
public class GridCounting {
	/** Returns the total number of possible routes (paths) from
	 * (x,y) to (tx,ty).
	 * There are only three valid kinds of moves:
	 *  Increment x by one.
	 *  Increment x by two.
	 *  Increment y by one.
	 *  
	 *  Hint: You'll need to test two base cases.
	 */
	public static int count(int x,int y, int tx, int ty) {
//		/System.out.println(getX(tx-x));
		//System.out.println(tx+"m"+x+"M"+(tx-x));
		if(x>tx || y>ty) return 0;
		else if(x==tx && y==ty)return 1;		
		return count(x+1,y,tx,ty)+count(x+2,y,tx,ty)+count(x,y+1,tx,ty);
	}
	//TODO add an helper function
	/*
	public static int getX(int x){
		if(x==0) return 1;
		if(x==1) return 2;
		if(x==2) return 3;
		return getX(x-1)+getX(x-2);
	}
	public static int trial(int x,int y, int tx, int ty) {
		int total = 0;
		for (int numberx = 1; numberx <= tx-x ; numberx++){
			int numberx2 = ((tx-x)-numberx)/2;
			if(numberx2<=0) {total+=numberx;}
			else total+=numberx*numberx2/2;
			System.out.println(total+"m"+numberx+"m"+numberx2);
		}
		//System.out.println(total);
		if(ty-y>0) {total*=(ty-y);return total;}
		else return total;
	}/*
	/*
	public static boolean isequal(int[] x, int[] y){
		for(int i = 0 ; i < x.length ; i++){
			if(x[i]!=y[i]) return false;
		}
		return true;
	}*/
}
