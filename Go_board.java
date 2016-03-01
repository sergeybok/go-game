import java.util.ArrayList;





class Go_board{

	public Stone[][] stoneBoard;
	private int numBlack;
	private int numWhite;
	public char cw;
	public char cb;
	//public ArrayList<Stone> stones;
	public ArrayList<ArrayList<Stone>> groups;
	private int whitePrisoners;
	private int blackPrisoners;


	public Go_board() {

		super();
		this.stoneBoard = new Stone[19][19];
		this.groups = new ArrayList<ArrayList<Stone>>();
		this.numBlack = 0;
		this.numWhite = 0;
		this.blackPrisoners =0;
		this.whitePrisoners =0;
		this.cb = 'X';
		this.cw = 'O';
		
	}

	boolean playBlack(int i, int j) {
		numBlack++;
		//return playStone(i,j,1);
		if (stoneBoard[i][j] == null)
			return playStone(i,j,true);
		else return false;
		
	}

	boolean playWhite(int i, int j) {
		numWhite++;
		//return playStone(i,j,-1);
		if (stoneBoard[i][j] == null)
			return playStone(i,j,false);
		else return false;
	}
	
	boolean playStone(int i, int j, boolean colorBlk) {
		
		if (stoneBoard[i][j] == null) {
			//
			Stone s = new Stone(i,j,colorBlk);
			stoneBoard[i][j] = s;

			if (i>1 && stoneBoard[i-1][j] != null) {
				if (stoneBoard[i-1][j].getBlack() == s.getBlack()){
					if (stoneBoard[i-1][j].getStoneGroup() != s.getStoneGroup()) {
						combineStoneGroups(stoneBoard[i-1][j].getStoneGroup(),s.getStoneGroup());
					}
				}
			} 
			if (i<18 && stoneBoard[i+1][j] != null) {
				if (stoneBoard[i+1][j].getBlack() == s.getBlack()){
					if (stoneBoard[i+1][j].getStoneGroup() != s.getStoneGroup()) {
						combineStoneGroups(stoneBoard[i+1][j].getStoneGroup(),s.getStoneGroup());
					}
				}
			}
			if (j>1 && stoneBoard[i][j-1] != null) {
				if (stoneBoard[i][j-1].getBlack() == s.getBlack()){
					if (stoneBoard[i][j-1].getStoneGroup() != s.getStoneGroup()) {
						combineStoneGroups(stoneBoard[i][j-1].getStoneGroup(),s.getStoneGroup());
					}
				}
			}
			if (j<18 && stoneBoard[i][j+1] !=null) {
				if (stoneBoard[i][j+1].getBlack() == s.getBlack()){
					if (stoneBoard[i][j+1].getStoneGroup() != s.getStoneGroup()) {
						combineStoneGroups(stoneBoard[i][j+1].getStoneGroup(),s.getStoneGroup());
					}
				}
			}
			//stones.add(s);
			ArrayList<ArrayList<Stone>> removedGroups = new ArrayList<ArrayList<Stone>>();
			for (ArrayList<Stone> group: groups) {
				if (!checkLiberties(group)) {
					removedGroups.add(group);
					removeGroup(group);

				}
			}
			for (ArrayList<Stone> group: removedGroups) {
				groups.remove(group);
			}
			return true;
		}
		else return false;
		
	}

	boolean fillEmptyArea(int i, int j, boolean blk) {
		if (stoneBoard[i][j] != null) {
			if (stoneBoard[i][j].getBlack() != blk)
				stoneBoard[i][j] = null;
			else
		 		return false;
		}
		if (playEmptyStone(i,j,blk)) {
			if (i!= 0)
				if (stoneBoard[i-1][j] == null) fillEmptyArea(i-1,j,blk);
			if (i != 18)
				if (stoneBoard[i+1][j] == null) fillEmptyArea(i+1,j,blk);
			if (j != 0)
				if (stoneBoard[i][j-1] == null) fillEmptyArea(i,j-1,blk);
			if (j != 18)
				if (stoneBoard[i][j+1] == null) fillEmptyArea(i,j+1,blk);

		}
		return true;
	}

	boolean playEmptyStone(int i, int j, boolean blk) {
		if (stoneBoard[i][j] == null){
			stoneBoard[i][j] = new Stone(i,j,blk);
			return true;
		}
		return false;
	}

	int countColor(boolean blk) {
		int sum = 0;
		for (int i =0; i< 19; i++) {
			for (int j =0; j< 19; j++) {
				if(stoneBoard[i][j] != null)
					if (stoneBoard[i][j].getBlack() == blk) sum++;
			}
		}
		return sum;
	}

	boolean checkLiberties(ArrayList<Stone> group) {
		for (Stone s: group) {
			if (s.i != 0)
				if (stoneBoard[s.i-1][s.j] == null) 
					return true;
			if (s.i != 19)
				if (stoneBoard[s.i+1][s.j] == null)
					return true; 
			if (s.j != 0)
				if (stoneBoard[s.i][s.j -1] == null)
					return true;
			if (s.j != 19)
				if (stoneBoard[s.i][s.j+1] == null)
					return true;
		}
		return false;
	}

	void removeGroup(ArrayList<Stone> group) {
		for (Stone s: group) {
			stoneBoard[s.i][s.j] = null;
		}
	}

	void addGroup(ArrayList<Stone> g) {
		groups.add(g);
	}

	ArrayList<Stone> combineStoneGroups(ArrayList<Stone> group1, ArrayList<Stone> group2) {
			Stone s;
			for (int i =0; i< group2.size() ;i++) {
				s = group2.get(i);
				s.setGroup(group1);
				group1.add(s);
				groups.remove(group2);
			}
			return group1;
		}

	void printBoard() {
		System.out.print("   ");
		for (int i =0; i< 19; i++) {
			System.out.print(""+((String.format("%02d", i+1)))+" ");
		}
		System.out.print("\n");
		for (int i=0; i <19; i++) {
			System.out.print((String.format("%02d ", i+1)));
			int j =0;
			//int spot = board[i][j];
			Stone spot = stoneBoard[i][j];

			if (spot == null)
				System.out.print(" -");
			else if (spot.getBlack())
				System.out.print(""+cb+"-");
			else  
				System.out.print(""+cw+"-");
				
			for (j=1; j<18; j++) {
				//spot = board[i][j];
				spot = stoneBoard[i][j];
				if (spot == null){
					if ((i==3 && j==3) || (i==3 && j==15) || (i==15 && j==3)
						|| (i==15 && j==15) || (i==9 && j==3) || (i==3 && j==9)
						|| (i==9 && j==9) || (i==15 && j==9) || (i==9 && j==15))
						System.out.print("-*-");
					else System.out.print("- -");
				}
				else if (spot.getBlack())	
					System.out.print("-"+cb+"-");
				else 
					System.out.print("-"+cw+"-"); 
					
			}
			//spot = board[i][j];
			spot = stoneBoard[i][j];
			if (spot == null) {
				System.out.print("-  ");
			}
			else if (spot.getBlack())
				System.out.print("-"+cb+" ");
			else 
				System.out.print("-"+cw+" "); 
					
			System.out.print("\n");
			if (i < 18){
				System.out.print(" ");
				for (j=0;j<19;j++){
					System.out.print("  |");
				}
			}
			
			System.out.print("\n");
		}
		System.out.println("________________________________________\n");
	}
	void printGroups() {
		for (ArrayList<Stone> s: groups) {
			System.out.println(s);
		}
	}


	private class Stone {
		int i;
		int j;
		boolean colorBlack;
		ArrayList<Stone> stoneGroup;

		private Stone(int i, int j, boolean c) {
			super();
			this.i = i;
			this.j = j;
			this.colorBlack = c;
			this.stoneGroup = new ArrayList<Stone>();
			this.stoneGroup.add(this);
			addGroup(this.stoneGroup);
		}
		private boolean getBlack() {
			return colorBlack;
		}

		private void setGroup(ArrayList<Stone> g) {
			this.stoneGroup = g;
		}
 
		private ArrayList<Stone> getStoneGroup() {
			return stoneGroup;
		}


	}
	

}