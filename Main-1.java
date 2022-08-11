package kylepinueladatastructuresproj1;



public class Main {

    public static void main(String[] args) {

        Sudoku sudoku = new Sudoku();
       

        for (int i = 0; i<1; i++){
            
            sudoku.generateRandomTable();
            System.out.println("Unsolved Sudoku ");
            sudoku.showTable();
          
          
            
            if (sudoku.solve()){
                System.out.println("Solution");
                sudoku.showTable();
                
               
            }else {
                System.out.println("Unsolvable Sudoku Generated, Run The Program Again");
                System.out.println("May take 3-4 Attempts to Generate Valid Sudoku");
            }
       

    }
}
}