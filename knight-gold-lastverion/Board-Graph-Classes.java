import java.util.*;

class Board {
	private char[][] board;  // 2D bir array -> santranc tahtasi için
	private int row, col; // tahtanin row ve col sayisi
	private int knightRow, knightCol; // atin koorninati
	private int goldRow, goldCol; // gold'un koordinati

/*
	private boolean visited[][];
	private LinkedList<Integer> que;
*/
	public Board(String Text) // input string'imizi alip tahtayi olusturma
	{
		this.row = Character.getNumericValue(Text.charAt(0)); // stringte ilk eleman row
		this.col = Character.getNumericValue(Text.charAt(1)); // stringte ikinci eleman col
		this.board = new char[row][col]; // tahtayi olusturma
		//this.visited = new boolean[row][col]; 

		loadText(Text);
	}

	public int getRow()
	{
		return row;
	}
	public int getCol()
	{
		return col;
	}
	public char getCell(int r, int c)
	{
		return board[r][c];
	}
	

	public int getKnightIndex()
	{
		return knightRow * col + knightCol;
	}
	public int getGoldIndex()
	{
		return goldRow * col + goldCol;
	}


	public int getKnightRow()
	{
		return knightRow;
	}
	public int getKnightCol()
	{
		return knightCol;
	}
	public int getGoldRow()
	{
		return goldRow;
	}
	public int getGoldCol()
	{
		return goldCol;
	}


	private void loadText(String Text)
	{
		int objectIterator = 2; // stringteki ikinci elemandan sonrasi tahtadaki objeler
		for(int i=0; i<row; i++) // bütün tahtayi dolasip elemanlari yerlerine koyma
		{
			for(int k=0; k<col; k++)
			{
				char newObject = Text.charAt(objectIterator); // string'ing objectIterator'daki elemani alma
				if(newObject == 'K') // K yani atin koordinati belirleme
				{
					knightRow = i;
					knightCol = k;
				}
				if(newObject == 'G') // G yani gold'un koordinati belirleme
				{
					goldRow = i;
					goldCol = k;
				}
				board[i][k] = newObject; // at veya gold değilse ya boştur ya da agac(engel)
				objectIterator++;
			}
		}
	}

	public void printBoard() {
		System.out.print("######## BOARD ########");
		System.out.println();
		System.out.println();
		for(int i=0; i<row; i++)
		{
			for(int k=0; k<col; k++)
			{
				System.out.print("|");
				System.out.print(board[i][k]);
			}
			System.out.print("|");
			System.out.println();
		}
		System.out.println("");
		System.out.println("Knight: " + knightRow + "-" + knightCol);
		System.out.println("Gold: " + goldRow + "-" + goldCol);
	}
}

class Graph {
	public int Vertex; // her kare icin bir vertex
	private LinkedList<Integer>[] neighbors; // her vertex'in komsularini tutmak icin
	boolean visitedCells[]; // bfs'de ziyaret edilen hucreleri tutmak icin
	int distTo[]; // shortest path'i belirleyebilmek 
	int edgeTo[]; // en kisa yola giden path'i takip edebilmek

	// atin col ve row'da yapabilecegi hareketler toplami,
	// mesela (-2, -1) yani 2 sola 1 yukari gibi..
	private int knightRow[] = {-2, -1, 1, 2,  2,  1, -1, -2};
	private int knightCol[] = { 1,  2, 2, 1, -1, -2, -2, -1};

	// tahtadaki her hücreye bir vertex/node ekledik. totalde row x col kadar dugum/vertex olacka.
	public Graph(int v)
	{
		this.Vertex = v;
		this.neighbors = new LinkedList[v];

		for(int i=0; i<v; i++)
		{
			neighbors[i] = new LinkedList<Integer>();
		}
	}


	private void addEdge(int v1, int v2)
	{
		neighbors[v1].add(v2);
		//neighbors[v2].add(v1);
		/*
		vertexleri iki taraflı eklediğimizde algoritma istenilen şekilde çalışmıyor.
		emin olmamakla birlikte, sanırım at geldiği hücreye tekrar hamle yapıyor.
		bu sebeple en kısa yola ulaşımıyor..
		bu sebeple tek taraflı eklemeyi yorum satırına aldım..
		*/
	}
	
	private LinkedList<Integer> getNeighbors(int v)
	{
		return neighbors[v];
	}
	
	private boolean checkMove(int row, int col, int maxRow, int maxCol) // atin hareketleri valid mi? yani tahta disina cıkmamak icin
	{
		if(row>=0 && row < maxRow && col>=0 && col<maxCol)
		{
			return true;
		}
		return false;
	}

	public void boardBuild(Board board)
	{
		int row = board.getRow(); 
		int col = board.getCol();

		for(int r=0; r<row; r++)
		{
			for(int c=0; c<col; c++)
			{
				if(board.getCell(r, c) == 'T')// o cell'de agac-engel varsa direkt olarak geciyoruz.
				{
					continue;
				}

				int cellIndex = r * col + c; // 2D arrayi 1D getirmek icin indexleme yaptik..

				for(int i=0; i<8; i++) // atin yapabilecegi max 8 hareket var
				{
					int newR = r + knightRow[i];
					int newC = c + knightCol[i];

					// atin yaptigi hamle board disindami ve yeni hamlemiz agaca geliyor mu diye
					if(checkMove(newR, newC, row, col) && board.getCell(newR, newC) != 'T')
					{
						/*
						System.out.print("newR: " + newR);
						System.out.print("-newC: " + newC);
						System.out.println("");
						*/


						int nextVertex = newR * col + newC; // atin bir sonraki hamlesini -index olarak belirleyebilmek
						addEdge(cellIndex, nextVertex); // bu index'i komsu vertex ekleme..
					}

				}
			}
		}

	}

	public int  bfs(int knightPosition, int goldPosition)
	{
		visitedCells = new boolean[Vertex];
		distTo = new int[Vertex];
		LinkedList<Integer> queue = new LinkedList<Integer>();

		edgeTo = new int[Vertex];
		visitedCells[knightPosition] = true;
		queue.add(knightPosition);
		distTo[knightPosition] = 0;

		while(!queue.isEmpty())
		{
			int w = queue.remove();

			if(w == goldPosition)
			{
				return distTo[w];
			}

			for(int neighbs: neighbors[w])
			{
				if(!visitedCells[neighbs])
				{
					visitedCells[neighbs] = true;
					queue.add(neighbs);
					distTo[neighbs] = distTo[w] + 1;
					edgeTo[neighbs] = w;
				}
			}
		}
		System.out.println("No path to the gold");
		return -1;
	}

	public Iterable<Integer> getPath(int start, int Gold)
	{
		LinkedList<Integer> path = new LinkedList<Integer>();

		if(!visitedCells[Gold]) // eger gold'a hic ulasilmadiysa
		{
			return path;
		}
		for(int i=Gold; i != start; i = edgeTo[i])
		{
			path.addFirst(i);
		}
		path.addFirst(start);
		return path;
	}
}