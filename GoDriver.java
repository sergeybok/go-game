import java.util.Scanner;




public class GoDriver {




	public static void main(String[] args) {

		Go_board gb = new Go_board();


		for (int i =0; i < 9; i++) {
			gb.playBlack(i,9);
			gb.playBlack(9,i);
		}
		gb.playBlack(9,9);

		for (int i =10; i<19; i++) {
			gb.playWhite(i,10);
			gb.playWhite(10,i);
		}

		gb.playBlack(16,16);
		gb.playWhite(4,4);

		gb.printBoard();

		System.out.println("Filling empty area black");
		gb.fillEmptyArea(5,5,true);

		System.out.println("Filling empty area white");
		gb.fillEmptyArea(15,15,false);

		gb.printBoard();

		System.out.println("Black final score: " + gb.countColor(true));
		System.out.println("White final score: " + gb.countColor(false));

		
		/*
		boolean black=false;
		boolean prevPass = false;
		while (true) {
			if (!black) {
				int x,y;
				Scanner reader = new Scanner(System.in);  // Reading from System.in
				System.out.print("Black to move: ");

				if (reader.hasNextInt()) {
					while (true) {
						x = reader.nextInt();
						y = reader.nextInt();
					
						if (x > 19 || y > 19 ) {
							System.out.print("\nInvalid Move. Try again\n");
						} else break;
					
						reader = new Scanner(System.in);  // Reading from System.in
						System.out.print("Black to move: ");
					
					}
					if (gb.playBlack(x-1,y-1))
						gb.printBoard();
					else System.out.print("Problem");
				
					black = true;
				}
				else {
					String s = reader.nextLine();
					if (s.equalsIgnoreCase("pass")) {
						if (prevPass == true) {
							//Endgame count score
							break;
						}
						else {
							prevPass = true;
							black = true;
							//System.out.println("PASS");
						}		
					}
				}

			}
			else {
				int x,y;
				Scanner reader = new Scanner(System.in);  // Reading from System.in
				System.out.print("White to move: ");

				if (reader.hasNextInt()) {
					while (true) {
						x = reader.nextInt();
						y = reader.nextInt();
					
						if (x > 19 || y > 19 ) {
							System.out.print("\nInvalid Move. Try again\n");
						} else break;
					
						reader = new Scanner(System.in);  // Reading from System.in
						System.out.print("White to move: ");
					
					}
					if (gb.playWhite(x-1,y-1))
						gb.printBoard();
					else System.out.print("Problem");
				
					black = false;
				}
				else {
					String s = reader.nextLine();
					if (s.equalsIgnoreCase("pass")) {
						if (prevPass == true) {
							//Endgame count score
							break;
						}
						else {
							prevPass = true;
							black = false;
							//System.out.println("PASS");
						}		
					}
				}
			}
		}

		System.out.println("Escaped loop");
*/
		
	}


}