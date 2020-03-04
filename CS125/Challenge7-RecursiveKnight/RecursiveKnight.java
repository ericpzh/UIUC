/**
 * 
 * @author 233
 *
 */
public class RecursiveKnight {
	public static void print(int[][] steps,int step){
		System.out.println("----------"+step);
		int i = 0;
		while (i < steps.length ){
			for(int j = 0 ; j < steps[0].length ; j++){
				System.out.print(steps[i][j]);
			}
			System.out.println();
			i++;
		}
	}
	public static boolean check(int[][] steps){
		int i = 0;
		int k = 0;
		while (i < steps.length ){
			for(int j = 0 ; j < steps[0].length ; j++){
				if(steps[i][j]==0) k++;
			}
			i++;
		}
		if(k>5) return false;
		else return true;
	}
	/**
	 * This method recursively determines which 
	 * board positions the knight can reach in the
	 * next few moves.
	 * 
	 * Base cases: Return immediately if 
	 * 1) x or y are invalid
	 * i.e. visited[x][y] would cause IndexOutOfBounds exceptions.
	 * 
	 * or, 2) visited[x][y] is true and step is a positive integer
	 * i.e. The current coordinates do not represent a valid square that we can hop to.
	 * 
	 * or, 3) steps[x][y] already has a positive integer, which is less than the parameter value. 
	 * i.e. There is a shorter path to this point than the one we are considering.
	 *
	 *Recursive case:
	 *Update steps[x][y]
	 *Recursively call explore() using the eight possible knight moves
	 * {1,2},{-1,-2},{2,1} etc (Work it out!)
	 * 
	 * The recursive call will use a different step value
	 * because it will be evaluating the next move.
	 * 
	 * The 'visited' array is unchanged by this method:.
	 * Assume visited and steps are already initialized to a square array and are the same size.
	 */
	public static void explore(boolean[][] visited, int x, int y, int[][] steps, int step) {
		//print(steps,step);
		//System.out.println(steps[0][4]);
	//TODO: Implement RecursiveKnight.explore
		try{boolean trial = visited[x][y];}catch(ArrayIndexOutOfBoundsException e){return;}
		//try{int trial = steps[x][y];}catch(ArrayIndexOutOfBoundsException e){return;}
		if(visited[x][y]==true && step > 0){return;}
		if(steps[x][y] > 0 && steps[x][y]<step){return;}
		//if(steps[x][y] > 0 && steps[x][y]>step){steps[x][y]=step;}
		//if (check(steps)) return;
		steps[x][y]=step;
		//if(steps[x][y]>step||steps[x][y]==0) steps[x][y] = step;
		explore(visited,x+1,y-2,steps,step+1);
		explore(visited,x+1,y+2,steps,step+1);
		explore(visited,x-1,y-2,steps,step+1);
		explore(visited,x-1,y+2,steps,step+1);
		explore(visited,x+2,y+1,steps,step+1);
		explore(visited,x+2,y-1,steps,step+1);
		explore(visited,x-2,y-1,steps,step+1);
		explore(visited,x-2,y+1,steps,step+1);

		//if(step > 10) return;
		//throw new RuntimeException("Not yet Implemented!"); // you can remove this line!
		/*
		try{boolean i = visited[x+1][y+2];
			//step++;
			//System.out.println(step+"d"+steps[x+1][y+2]);
			steps[x][y]=step;
			explore(visited,x+1,y+2,steps,step+1);//{1,2}
		}
		catch(ArrayIndexOutOfBoundsException e){return;};
		try{boolean i = visited[x+2][y+1];
			//step++;
			steps[x+2][y+1]=step+1;
			explore(visited,x+2,y+1,steps,step+1);//{2,1}
		}	
		catch(ArrayIndexOutOfBoundsException e){return;};
		try{boolean i = visited[x-1][y+2];
			//visited[x-1][y+2]=true;
			//step++;
			steps[x-1][y+2]=step+1;
			explore(visited,x-1,y+2,steps,step+1);//{-1,2}
		}
		catch(ArrayIndexOutOfBoundsException e){return;};
		try{boolean i = visited[x+2][y-1];
			//visited[x+2][y-1]=true;
			//step++;
			steps[x+2][y-1]=step+1;

			explore(visited,x+1,y+2,steps,step+1);//{2,-1}
		}
		catch(ArrayIndexOutOfBoundsException e){return;};
		try{boolean i = visited[x+1][y-2];
			//visited[x+1][y-2]=true;
			//step++;
			steps[x+1][y-2]=step+1;

			explore(visited,x+1,y-2,steps,step+1);//{1,-2}
		}
		catch(ArrayIndexOutOfBoundsException e){return;};
		try{boolean i = visited[x-2][y+1];
			//step++;
			///visited[x-2][y+1]=true;
			steps[x-2][y+1]=step+1;

			explore(visited,x-2,y+1,steps,step+1);//{-2,1}
		}
		catch(ArrayIndexOutOfBoundsException e){return;};
		try{boolean i = visited[x-1][y-2];
			//step++;			//visited[x-1][y-2]=true;
			steps[x-1][y-2]=step+1;

			explore(visited,x-1,y-2,steps,step+1);//{-1,-2}
		}
		catch(ArrayIndexOutOfBoundsException e){return;};
		try{boolean i = visited[x-2][y-1];
			//step++;			//visited[x-2][y-1]=true;
			steps[x-2][y-1]=step+1;
			explore(visited,x-2,y-1,steps,step+1);//{-2,-1}
		}
		catch(ArrayIndexOutOfBoundsException e){return;};
		*/
	}
}