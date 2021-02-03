import java.util.Scanner;
//the Player class where you play
public class Player{

	private static int h = 6;
	private static int w = 6;
	private static int nuMines = 10;
	private static int n = h*w-nuMines;
	private static Board ground;

	public static void main(String arg[]){
		System.out.println("The game will now begin");
		System.out.println("Give input for ex: x y");
		System.out.println("To label for ex: L x y");
		if(arg.length != 0){
			h = Integer.parseInt(arg[0]);
			w = Integer.parseInt(arg[1]);
			nuMines = Integer.parseInt(arg[2]);}
		startGame();
		}
	// creating a board
	public static void startGame(){
		String[] move;
		ground = new Board(h, w, nuMines);
 		n = h*w-nuMines;
		ground.displayBoard(ground.getInBoard());

		while(ground.getCount() < n){
			System.out.println("Your move:");
			Scanner sc = new Scanner(System.in);
			move = sc.nextLine().split(" ");
			if (move[0].charAt(0) == 'L') ground.label(Integer.parseInt(move[1]), Integer.parseInt(move[2]));
			else playerInp(Integer.parseInt(move[0]), Integer.parseInt(move[1]));
			ground.displayBoard(ground.getBoard());
		
			}
		if (ground.getCount()  == n)
			System.out.println("You Win!");
		System.out.println("Reset? Y/N");
		Scanner sc = new Scanner(System.in);
		String Choice = sc.nextLine();
			if (Choice.equals("Y")){
				reset();
			};
	}
	//getting input from a player
	public static void playerInp(int x, int y){
		if (ground.getBoard()[x][y] == 'L')
			ground.label(x, y);
		if (ground.getBoard()[x][y] != 'x')
			System.out.println("Make another move");
		else
			ground.uncover(x, y);
		if (ground.getInBoard()[x][y] > 8){
			System.out.println("GAME OVER");
			ground.displayMines();
			ground.incrementCount(n+1);
		}			
	}

	// reset game by creating new board object
	public static void reset(){
	startGame();
	}
}
