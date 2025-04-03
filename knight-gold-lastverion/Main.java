/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
import java.util.*;

public class Main
{
	/*
		static char[][] board;

		static void printBoard(String text)
		{
			int row = Character.getNumericValue(text.charAt(0));
			int col = Character.getNumericValue(text.charAt(1));

			board = new char[row][col];


			int objectIterator = 2;
			for(int i=0; i<row; i++)
			{
				for(int k=0; k<col; k++)
				{
					char object = text.charAt(objectIterator);

					board[i][k] = object;
					objectIterator++;
				}
			}

			for(int i=0; i<row; i++)
			{
				for(int k=0; k<col; k++)
				{
					System.out.print("|");
					System.out.print(board[i][k]);
					System.out.print("|");
				}
				System.out.println();
			}
		}

		*/
	public static void main(String[] args) {
	    //String inputString = "55...T.T...T..KG.T...T...T.";
		//String inputString = "55.T.T.T.T.T..KG.TTT.T.TT..";
		// String inputString = "55K.......................G";
		 //String inputString = "33K..T..G.T";
        //String inputString = "44..T..K....G.T..T";
        //String inputString = "55...T.T...T..KG.T...T...T.";
        //String inputString = "66K....T.G...T..T...T...T...T...T..TT.T";

		Scanner myObj = new Scanner(System.in); 
		String inputString = myObj.nextLine();
		
		Board board = new Board(inputString);

	//	board.printBoard();

		int totalCell = board.getRow() * board.getCol();

		//System.out.println("totalCell: " + totalCell);

		Graph graph = new Graph(totalCell);

		graph.boardBuild(board);


		int knightCell = board.getKnightIndex();
		int goldCell = board.getGoldIndex();

/*
        System.out.println("");
		System.out.println("knightCell: " + knightCell);
		System.out.println("goldCell: " + goldCell);
*/
		int shortestWay = graph.bfs(knightCell, goldCell);
		//System.out.println("shortest way: " + shortestWay);

		String Output = "";
		for(int w: graph.getPath(knightCell, goldCell))
		{
			// cell index'i = i * col + j
			int row = w / board.getCol();
			int col = w % board.getCol();

			Output = Output + "c"+(row+1)+","+(col+1)+" -> ";
		}
		if(Output.length()>0)
		{
		    String newOutput = Output.substring(0, Output.length() - 4);
		    System.out.print(newOutput);
		}
		else
		{
		    System.out.print(Output);
		}
		

	}
}


