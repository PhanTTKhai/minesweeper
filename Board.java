import java.util.Arrays;
import java.util.Random;
//Playground
public class Board{
	private static char[][] board;// Player board
	private static  int[][] inBoard;// inner board
	private int w;// width
	private int h;// height
	private int nuMines;// num of mines
	private int count = 0;// num of open cells exclude mines
	private Mine[] bombs;// array of mines

	//constructor
	public Board(int h, int w, int nuMines){
		this.nuMines = nuMines;
		this.w = w;
		this.h = h;
		createBoard();
		this.bombs = createMines(nuMines);
		displayBoard(board);
	}
	//accessors
	public int getCount(){
		return count;
	}

	public void incrementCount(int num){
		this.count+=num;
	}

	public int[][] getInBoard(){
		return inBoard;
	}

	public char[][] getBoard(){
		return board;
	}
	// creatng objects for Board and inBoard
	public void createBoard(){
		board = new char[h][w];
		for (char[] row: board)
			Arrays.fill(row,'x');

		inBoard = new int[h][w];
		for (int[] row: inBoard)
			Arrays.fill(row,0);
	}
	// display either board
	public void displayBoard(int[][] board){
		System.out.print(" "+"\t");// creating boarders
		for (int k = 0; k<w; k++){
			System.out.print(k+"\t");
		}
		System.out.println();
		for (int k = 0; k<w; k++){
			System.out.print("-"+"\t");
		}
		System.out.println();
		for (int i=0; i<h; i++){// printing every element of arrays
			System.out.print(i+"|"+"\t");
			for (int j=0; j<w; j++){
				System.out.print(board[i][j]+"\t");
			}
			System.out.println();		
		}
		System.out.println();
	}
	
	// the overload method 
	public void displayBoard(char[][] board){
		System.out.print(" "+"\t");
		for (int k = 0; k<w; k++){
			System.out.print(k+"\t");
		}
		System.out.println();
		for (int k = 0; k<w; k++){
			System.out.print("-"+"\t");
		}

		System.out.println();
		for (int i=0; i<h; i++){
			System.out.print(i+"|"+"\t");
			for (int j=0; j<w; j++){
				System.out.print(board[i][j]+"\t");
			}
			System.out.println();		
		}
		System.out.println();
	}
	// placing mines on the board
	public Mine[] createMines(int n){
		int count = 0;
		int x,y;
		Mine[] bombs = new Mine[n];
		Random rnd = new Random();
		while (count != n){
			x = rnd.nextInt(h);
			y = rnd.nextInt(w);
			if (inBoard[x][y]<=8){
				bombs[count] = new Mine(x, y);
				count++;
			}
		}
		return bombs;
	}
	// open all cells with mines
	public void displayMines(){
		for (int i = 0; i< nuMines; i++){
			uncover(bombs[i].getxCoord(), bombs[i].getyCoord());
		}
	}
	// open cell
	public void uncover(int x, int y){
		if (board[x][y] == 'x')
		if (inBoard[x][y] > 8)
			board[x][y] = 'B';
		else{
			count++;
			board[x][y] = (char)(inBoard[x][y] + '0');
			if (inBoard[x][y] == 0) {
				openCellsAround(x, y);
			}
		}
	}
	//label cell
	public void label(int x, int y){
		if (board[x][y] =='x')
			board[x][y] = 'L';
		else 
			board[x][y] = 'x';
	}
	// open all cells around the chosen cell
	public void openCellsAround(int x, int y){
		for ( int i = x-1; i <= x+1; i++)
			for (int j = y-1; j <= y+1; j++){
				if (isInside(i,j)){
					uncover(i,j);
				}
			}	
	}
	
	//is inside the playground
	public boolean isInside(int x, int y){
		 return (x >= 0) &&  (x < h) &&  (y >= 0) &&  (y < w); 
	}
	
	//The Mine class with coordinates
	class Mine{
	private int x,y;

	public Mine(int x, int y){
		this.x = x;
		this.y = y;
		inBoard[x][y] = 9;
		for ( int i = x-1; i <= x+1; i++)
			for (int j = y-1; j <= y+1; j++)
				if (i != x || j!=y) plusOne(i,j);
	}
	//getters
	public int getxCoord(){
		return this.x;
	}

	public int getyCoord(){
		return this.y;
	}
	// increment by 1 the value around the chosen cell
	public void plusOne(int x, int y){
			if (isInside(x,y)){
			inBoard[x][y]+=1;
			}
	}
}
}

