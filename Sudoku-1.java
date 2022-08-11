package kylepinueladatastructuresproj1;
import java.util.Random;

public class Sudoku {

    private char[][] table;
    private static final char hiddenValue = ' '; //To Change the Visibility of Certain Cells
    private String difficulty;
	
    private final char charArray[] = {'1','2','3','4','5','6','7','8','9'};
	
	

    public Sudoku() {
        table = new char[9][9]; //9x9 grid
    }

    public Sudoku(char table[][], int probability) {
        this.table= table;    
    }



    private boolean rowBoolean(int row,char character){ 
        for (int i = 0; i<9; i++){
            if (table[row][i] == character){
                return false;
            }
        }
        return true;
    }
    private boolean columnBoolean(int col,char character){
        for (int i = 0; i<9; i++){
            if (table[i][col] == character){
                return false;
            }
        }
        return true;
    }

    private boolean fullGridBoolean(int row, int col,char character){
        for (int i = row-(row%3); i<row-(row%3)+3; i++){
            for (int j = col-(col%3); j<col-(col%3)+3; j++){
                if (table[i][j] == character){
                    return false;
                }
            }
        }
        return true;
    }
    private boolean allBooleans(int row, int col,char character){
        return rowBoolean(row,character) && columnBoolean(col, character) && fullGridBoolean(row, col, character);
    }
   
    
    
    /*int userDifficulty() {
    	int diffProb=0;
    	
    	try{Scanner sc= new Scanner(System.in);
         System.out.println("Please Choose Your Difficulty: Easy, Medium, or Hard");
         String userInput = sc.nextLine();
         while (sc.hasNextLine()) {
        	 if ("easy".equals(userInput)) {
            	 return 45;
               
             } else if ("medium".equals(userInput)) {
            	 return 50;
                 
             } else if("hard".equals(userInput)){
                 return 65;
             }
         }
    	}
    	catch(Exception e) {
		System.out.println("Invalid User Input");
    	}
		return diffProb;

         
		
    	
    }
*/
    int userDifficulty(String difficulty) { //determines the difficulty of the game by dividing the amount of possible numbers, by the index of the random number
    	int probability = 0;
    	if(difficulty == "easy") {
    		probability= 22;  // 9/22 is equal to 44% chance of a cell being visible
    	}
    	else if (difficulty == "medium") {
    		probability =25 ;  // 9/25 is equal to 38% chance of a cell being visible
            
        } else if(difficulty == "hard"){
        	probability=30; // 9/30 is equal to 30% chance of a cell being visible
        }
  return probability;
    	
    
    }
    
   
    public void generateRandomTable(){
        table = new char[9][9];
        for (int i = 0; i<9; i++){
            for (int j = 0; j<9; j++){
                table[i][j] = getRandomChar(i,j);
            }
        }
    }
  

    public char getRandomChar(int row, int col){ // determines the amount of Cells to be filled according to the difficulty
        Random random = new Random();
        String difficulty= "hard";
       
		
		//int diffProb = random.nextInt(userDifficulty());
        int diffProbIndex = random.nextInt(userDifficulty(difficulty)); // Creates a Random number and Determines the number of visible cells 
        if (diffProbIndex < 9){
            if (allBooleans(row, col, charArray[diffProbIndex])){
                return charArray[diffProbIndex];   
            }else {
                return getRandomChar(row, col);     //recursively generates A Grid of Values
            }
        }else {
            return hiddenValue; 
        }
    }

   

	public void showTable(){
        System.out.println("_______________________________");
        for (int i = 0; i<9; i++){
            if (i%3 == 0 && i != 0){
                System.out.println("-------------------------------");
            }
            for (int j = 0; j<9; j++){
                if (j%3 == 0){
                    System.out.print("|");
                }
                System.out.print(" " + table[i][j] + " ");
                if (j == 8){
                    System.out.print("|");
                }
            }
            System.out.println("");
        }
        System.out.println("_______________________________");
    }

    public boolean solve(){
        for (int i = 0; i<9; i++){
            for (int j = 0; j<9; j++){
                if (table[i][j] == hiddenValue){
                    for (int index = 0; index<9; index++){
                        if (allBooleans(i,j,charArray[index])){
                            table[i][j] = charArray[index];
                            if (solve()){
                                return true;
                            } else{
                                table[i][j] = hiddenValue;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

}