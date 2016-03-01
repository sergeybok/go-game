import java.util.ArrayList

class Go_board{

	private short[][] board;
	private int numBlack;
	private int numWhite;


	public Go_board() {
		Go_board go_b = new go_board();
		go_b.board = new stone[19][19];
		go_b.numBlack = 0;
		go_b.numWhite = 0;
		return go_b;
	}

	boolean playBlack(int i, int j) {
		numBlack++;
		return playStone(i,j,1);
	}

	boolean playWhite(int i, int j) {
		numWhite++;
		return playStone(i,j,-1);
	}
	
	boolean playStone(int i, int j, int color) {
		if (board[i][j] == null) {
			board[i][j] = color;
		}
		else {
			return false;
		}
		return true;
	}

	void printBoard() {
		for (int i=0; i <19; i++) {
			for (int j=0; j<19; j++) {
				int spot = go_b[i][j];
				if (spot == 1)
					System.out.print(' b ');
				else if(spot == -1) 
					System.out.print(' w ');
				else 
					System.out.print(' + ');
			}
			System.out.print('\n');
		}
	}

	private class Stone {
		int i;
		int j;
		boolean colorBlack;
		ArrayList stoneGroup;

		private Stone(int i, int j, boolean c) {
			Stone s = new Stone();
			s.i = i;
			s.j = j;
			s.colorBlack = c;
			s.stoneGroup = new ArrayList<Stone>();
			s.stoneGroup.add(s);
			return s;
		}

		private getStoneGroup() {
			return stoneGroup;
		}
	}

	public static void main(argv args[]) {
		
	}


}