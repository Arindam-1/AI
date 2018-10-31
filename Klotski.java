/** 
 * This skeleton is just an exmple.
 * Feel free to change this skeleton or using better ideas to implement.  
 */

import java.util.*;

// implement the class of block if necessary
class Block {
    
}

class GameState {      
    public int[][] board = new int[5][4];
    public GameState parent = null;
    public int cost = 0;
    public int steps = 0;

    public GameState(int [][] inputBoard, int steps) {
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 4; j++)
                this.board[i][j] = inputBoard[i][j];
        this.steps = steps;
    }

    public GameState(int [][] inputBoard) {
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 4; j++)
                this.board[i][j] = inputBoard[i][j];
    }

    public Comparator<GameState> stateComparator = new Comparator<GameState>() {
        @Override
        public int compare(GameState o1, GameState o2) {
            if (o1.cost - o2.cost != 0)
                return o1.cost - o2.cost;
            else
                return o1.getStateID().compareTo(o2.getStateID());
        }
    };   

   

	void initarray(int[][] X, int[][] Y)
	{
		for(int a=0 ; a<5 ; a++)
		{
			for(int b=0 ; b<4 ; b++)
			{
				X[a][b] = Y[a][b] ;
			}
		}
	}
            
    // get all successors and return them in sorted order
    public List<GameState> getNextStates() {
        List<GameState> successors = new ArrayList<>();        
        
        int[][] Temp = new int[5][4] ;
		initarray(Temp,board) ;

		int[][] checkset = new int[5][4] ;		// For Case 2 i.e 2x1 blocks
		for(int a=0 ; a<5 ; a++)
		{
			for(int b=0; b<4 ; b++)
				{
					checkset[a][b]=0 ;
				}
		}
//---------------------------------------------------------------------------------------------------------------------------------------------------------
//--------------------------------------------------------|| CASE 1 ||-------------------------------------------------------------------------------------

// TBH The first 1 we would come up with would be the top left 1 with which we could determine the position of the other ones

for(int i=0 ; i<5 ; i++){
	for(int j=0 ; j<4 ; j++){


		// FOR 1 REPRESENTING THE 2X2 CUBE MOVES - UP DOWN LEFT RIGHT - SUCCESSOR FUNCTIONS MOVE ONLY ONE MOVE
		if( ( (j+1)<4 ) && ( (i+1)<5 ) && (board[i][j]==1) && (board[i][j+1]==1) && (board[i+1][j]==1)  )	// Top Left Corner as it would help us control the whole square
		{
			if( ((j+2)<4) &&  (board[i][j+2]==0) && (board[i+1][j+2]==0) ) 		// Case Right 
				{
					//swap(Temp[i][j+1],Temp[i][j+2]) ;
					Temp[i][j+1] = Temp[i][j+1] + Temp[i][j+2] ;
					Temp[i][j+2] = Temp[i][j+1] - Temp[i][j+2]  ;
					Temp[i][j+1] = Temp[i][j+1] - Temp[i][j+2] ;

					//swap(Temp[i+1][j+1],Temp[i+1][j+2]) ;
					Temp[i+1][j+1] = Temp[i+1][j+1] + Temp[i+1][j+2] ;
					Temp[i+1][j+2] = Temp[i+1][j+1] - Temp[i+1][j+2]  ;
					Temp[i+1][j+1] = Temp[i+1][j+1] - Temp[i+1][j+2] ;

					//swap(Temp[i][j],Temp[i][j+1]) ;
					Temp[i][j] = Temp[i][j] + Temp[i][j+1] ;
					Temp[i][j+1] = Temp[i][j] - Temp[i][j+1]  ;
					Temp[i][j] = Temp[i][j] - Temp[i][j+1] ;

					//swap(Temp[i+1][j],Temp[i+1][j+1]) ; 
					Temp[i+1][j] = Temp[i+1][j] + Temp[i+1][j+1] ;
					Temp[i+1][j+1] = Temp[i+1][j] - Temp[i+1][j+1]  ;
					Temp[i+1][j] = Temp[i+1][j] - Temp[i+1][j+1] ;

					successors.add(new GameState(Temp)) ;
				}


			if( ((j-1)>-1) && (board[i][j-1]==0) && (board[i+1][j-1]==0) )		// Case Left
				{
					//swap(Temp[i][j-1],Temp[i][j]) ;
					Temp[i][j-1] = Temp[i][j-1] + Temp[i][j] ;
					Temp[i][j] = Temp[i][j-1] - Temp[i][j]  ;
					Temp[i][j-1] = Temp[i][j-1] - Temp[i][j] ;

					//swap(Temp[i+1][j-1],Temp[i+1][j]) ;
					Temp[i+1][j-1] = Temp[i+1][j-1] + Temp[i+1][j] ;
					Temp[i+1][j] = Temp[i+1][j-1] - Temp[i+1][j]  ;
					Temp[i+1][j-1] = Temp[i+1][j-1] - Temp[i+1][j] ;

					//swap(Temp[i][j],Temp[i][j+1]) ; 
					Temp[i][j] = Temp[i][j] + Temp[i][j+1] ;
					Temp[i][j+1] = Temp[i][j] - Temp[i][j+1]  ;
					Temp[i][j] = Temp[i][j] - Temp[i][j+1] ;

					//swap(Temp[i+1][j],Temp[i+1][j+1]) ;
					Temp[i+1][j] = Temp[i+1][j] + Temp[i+1][j+1] ;
					Temp[i+1][j+1] = Temp[i+1][j] - Temp[i+1][j+1]  ;
					Temp[i+1][j] = Temp[i+1][j] - Temp[i+1][j+1] ;

					successors.add(new GameState(Temp)) ;
				}

			if( ((i+2)<5) && (board[i+2][j]==0) && (board[i+2][j+1]==0) )		// Case Down
				{
					//swap(Temp[i+1][j],Temp[i+2][j]) ;
					Temp[i+1][j] = Temp[i+1][j] + Temp[i+2][j] ;
					Temp[i+2][j] = Temp[i+1][j] - Temp[i+2][j]  ;
					Temp[i+1][j] = Temp[i+1][j] - Temp[i+2][j] ;

					//swap(Temp[i+1][j+1],Temp[i+2][j+1]) ;
					Temp[i+1][j+1] = Temp[i+1][j+1] + Temp[i+2][j+1] ;
					Temp[i+2][j+1] = Temp[i+1][j+1] - Temp[i+2][j+1] ;
					Temp[i+1][j+1] = Temp[i+1][j+1] - Temp[i+2][j+1] ;

					//swap(Temp[i][j],Temp[i+1][j]) ;
					Temp[i][j] = Temp[i][j] + Temp[i+1][j] ;
					Temp[i+1][j] = Temp[i][j] - Temp[i+1][j] ;
					Temp[i][j] = Temp[i][j] - Temp[i+1][j] ;

					//swap(Temp[i][j+1],Temp[i+1][j+1]) ;
					Temp[i][j+1] = Temp[i][j+1] + Temp[i+1][j+1] ;
					Temp[i+1][j+1] = Temp[i][j+1] - Temp[i+1][j+1] ;
					Temp[i][j+1] = Temp[i][j+1] - Temp[i+1][j+1] ;  

					successors.add(new GameState(Temp)) ;
				}

			if( ((i-1)>-1) && (board[i-1][j]==0) && (board[i-1][j+1]==0) )	   // Case Up
				{
					//swap(Temp[i-1][j],Temp[i][j]) ;
					Temp[i-1][j] = Temp[i-1][j] + Temp[i][j] ;
					Temp[i][j] = Temp[i-1][j] - Temp[i][j] ;
					Temp[i-1][j] = Temp[i-1][j] - Temp[i][j] ;

					//swap(Temp[i-1][j+1],Temp[i][j+1]) ;
					Temp[i-1][j+1] = Temp[i-1][j+1] + Temp[i][j+1] ;
					Temp[i][j+1] = Temp[i-1][j+1] - Temp[i][j+1] ;
					Temp[i-1][j+1] = Temp[i-1][j+1] - Temp[i][j+1] ;

					//swap(Temp[i][j],Temp[i+1][j]) ;
					Temp[i][j] = Temp[i][j] + Temp[i+1][j] ;
					Temp[i+1][j] = Temp[i][j] - Temp[i+1][j] ;
					Temp[i][j] = Temp[i][j] - Temp[i+1][j] ;

					//swap(Temp[i][j+1],Temp[i+1][j+1]) ;
					Temp[i][j+1] = Temp[i][j+1] + Temp[i+1][j+1] ;
					Temp[i+1][j+1] = Temp[i][j+1] - Temp[i+1][j+1] ;
					Temp[i][j+1] = Temp[i][j+1] - Temp[i+1][j+1] ;

					successors.add(new GameState(Temp)) ;
				}

			initarray(Temp,board) ;
		}	



//-----------------------------------------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------|| CASE 2 ||----------------------------------------------------------------------------
	
// FOR 2 REPRESENTING THE 2X1 CUBE MOVES - UP DOWN LEFT RIGHT - SUCCESSOR FUNCTIONS MOVE ONLY ONE MOVE
// We Implement a temporary checkset2 array which would hold all the elements that have been included already

		if( board[i][j]==2 && checkset[i][j]==0 )
		{
				checkset[i][j] = 1;
				checkset[i+1][j] = 1 ; 

				if( ((i+2)<5) && board[i+2][j]==0 )						// Case Down
				{
					//swap(Temp[i+1][j],Temp[i+2][j]) ;
					Temp[i+1][j] = Temp[i+1][j] + Temp[i+2][j] ;
					Temp[i+2][j] = Temp[i+1][j] - Temp[i+2][j] ;
					Temp[i+1][j] = Temp[i+1][j] - Temp[i+2][j] ;

					//swap(Temp[i][j],Temp[i+1][j]) ;
					Temp[i][j] = Temp[i][j] + Temp[i+1][j] ;
					Temp[i+1][j] = Temp[i][j] - Temp[i+1][j] ;
					Temp[i][j] = Temp[i][j] - Temp[i+1][j] ;

					successors.add(new GameState(Temp))	;
				}

				initarray(Temp,board) ;

				// Another issue is that we have to reset temp everytime so that it could be the correct successors. There can be a case when it can move both up 
				// and down as there are 2 blank zeros and one could be above it and one could be below it

				if( ((i-1)>-1) && board[i-1][j]==0 ) 					// Case Up
				{
					//swap(Temp[i-1][j],Temp[i][j]) ;
					Temp[i-1][j] = Temp[i-1][j] + Temp[i][j] ;
					Temp[i][j] = Temp[i-1][j] - Temp[i][j] ;
					Temp[i-1][j] = Temp[i-1][j] - Temp[i][j] ;

					//swap(Temp[i][j], Temp[i+1][j]) ;
					Temp[i][j] = Temp[i][j] + Temp[i+1][j] ;
					Temp[i+1][j] = Temp[i][j] - Temp[i+1][j] ;
					Temp[i][j] = Temp[i][j] - Temp[i+1][j] ;

					successors.add(new GameState(Temp)) ;
				}

				initarray(Temp,board) ;

				if( ((j+1)<4) && board[i][j+1]==0 && board[i+1][j+1]==0 )	// Case Right
				{
					//swap(Temp[i][j],Temp[i][j+1]) ;
					Temp[i][j] = Temp[i][j] + Temp[i][j+1] ;
					Temp[i][j+1] = Temp[i][j] - Temp[i][j+1] ;
					Temp[i][j] = Temp[i][j] - Temp[i][j+1] ;

					//swap(Temp[i+1][j],Temp[i+1][j+1]) ;
					Temp[i+1][j] = Temp[i+1][j] + Temp[i+1][j+1] ;
					Temp[i+1][j+1] = Temp[i+1][j] - Temp[i+1][j+1] ;
					Temp[i+1][j] = Temp[i+1][j] - Temp[i+1][j+1] ;

					successors.add(new GameState(Temp)) ;
				}
		
				initarray(Temp,board) ;


				if( ((j-1)>-1) && board[i][j-1]==0 && board[i+1][j-1]==0 )	// Case Left 
				{
					//swap(Temp[i][j],Temp[i][j-1]) ;
					Temp[i][j] = Temp[i][j] + Temp[i][j-1] ;
					Temp[i][j-1] = Temp[i][j] - Temp[i][j-1] ;
					Temp[i][j] = Temp[i][j] - Temp[i][j-1] ;

					//swap(Temp[i+1][j],Temp[i+1][j-1]) ;
					Temp[i+1][j] = Temp[i+1][j] + Temp[i+1][j-1] ;
					Temp[i+1][j-1] = Temp[i+1][j] - Temp[i+1][j-1] ;
					Temp[i+1][j] = Temp[i+1][j] - Temp[i+1][j-1] ;

					successors.add(new GameState(Temp)) ;
				}

				initarray(Temp,board) ;
		}


//-------------------------------------------------------------------------------------------------------------------------------------------------------------
//----------------------------------------------------------------|| CASE 3 ||--------------------------------------------------------------------------------

// For representing 3 - The 1X2 block

		if( ((j+1)<4) && board[i][j]==3 && board[i][j+1]==3 ) 
		{
			if( ((i+1)<5) && board[i+1][j]==0 && board[i+1][j+1]==0 )  		//Case Down
			{
				//swap(Temp[i][j],Temp[i+1][j]) ;
				Temp[i][j] = Temp[i][j] + Temp[i+1][j] ;
				Temp[i+1][j] = Temp[i][j] - Temp[i+1][j] ;
				Temp[i][j] = Temp[i][j] - Temp[i+1][j] ;

				//swap(Temp[i][j+1],Temp[i+1][j+1]) ;
				Temp[i][j+1] = Temp[i][j+1] + Temp[i+1][j+1] ;
				Temp[i+1][j+1] = Temp[i][j+1] - Temp[i+1][j+1] ;
			Temp[i][j+1] = Temp[i][j+1] - Temp[i+1][j+1] ;

			successors.add(new GameState(Temp)) ;
			}

			initarray(Temp,board) ;

			if( ((i-1)>-1) && board[i-1][j]==0 && board[i-1][j+1]==0 )		//Case Up
			{
				//swap(Temp[i][j],Temp[i-1][j]) ;
				Temp[i][j] = Temp[i][j] + Temp[i-1][j] ;
				Temp[i-1][j] = Temp[i][j] - Temp[i-1][j] ;
				Temp[i][j] = Temp[i][j] - Temp[i-1][j] ;

				//swap(Temp[i][j+1],Temp[i-1][j+1]) ;
				Temp[i][j+1] = Temp[i][j+1] + Temp[i-1][j+1] ;
				Temp[i-1][j+1] = Temp[i][j+1] - Temp[i-1][j+1] ;
				Temp[i][j+1] = Temp[i][j+1] - Temp[i-1][j+1] ;

				successors.add(new GameState(Temp)) ;
			}

			initarray(Temp,board) ;

			if( ((j+2)<4) && board[i][j+2]==0 )							//Case Right
			{
				//swap(Temp[i][j+1],Temp[i][j+2]) ;
				Temp[i][j+1] = Temp[i][j+1] + Temp[i][j+2] ;
				Temp[i][j+2] = Temp[i][j+1] - Temp[i][j+2]  ;
				Temp[i][j+1] = Temp[i][j+1] - Temp[i][j+2] ;

				//swap(Temp[i][j],Temp[i][j+1]) ;
				Temp[i][j] = Temp[i][j] + Temp[i][j+1] ;
				Temp[i][j+1] = Temp[i][j] - Temp[i][j+1]  ;
				Temp[i][j] = Temp[i][j] - Temp[i][j+1] ;

				successors.add(new GameState(Temp)) ;
			}

			initarray(Temp,board) ;

			// Another issue is that we have to reset temp everytime so that it could be the correct successors. There can be a case when it can move both left 
			// and right as there are 2 blank zeros and one could be above it and one could be below it


			if( ((j-1)>-1) && board[i][j-1]==0)							//Case Left
			{
				//swap(Temp[i][j-1],Temp[i][j]) ;
				Temp[i][j-1] = Temp[i][j-1] + Temp[i][j] ;
				Temp[i][j] = Temp[i][j-1] - Temp[i][j]  ;
				Temp[i][j-1] = Temp[i][j-1] - Temp[i][j] ;

				//swap(Temp[i][j],Temp[i][j+1]) ;
				Temp[i][j] = Temp[i][j] + Temp[i][j+1] ;
				Temp[i][j+1] = Temp[i][j] - Temp[i][j+1]  ;
				Temp[i][j] = Temp[i][j] - Temp[i][j+1] ;


				successors.add(new GameState(Temp)) ;	
			}

			initarray(Temp,board) ;
		}



//-------------------------------------------------------------------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------|| CASE 4 ||-----------------------------------------------------------------------

		
        		if(board[i][j]==4)
				{
					int[] zeros = new int[4] ;
					int k=0 ;
					for(int a=0 ; a<5 ; a++)
					{
						for(int b=0 ; b<4 ; b++)
							{
								if(board[a][b]==0)
								{
				 				zeros[k] = a ;
				 				zeros[k+1] = b ;
				 				k = k + 2 ;
								}
							}
					}
					//System.out.println(zeros[0] + " " + zeros[1] + "||" + zeros[2] + " " + zeros[3]) ;
					
					//swap(Temp[i][j],Temp[zeros[0]][zeros[1]]) ;
					Temp[i][j] = Temp[i][j] + Temp[zeros[0]][zeros[1]] ;
					Temp[zeros[0]][zeros[1]] = Temp[i][j] - Temp[zeros[0]][zeros[1]]  ;
					Temp[i][j] = Temp[i][j] - Temp[zeros[0]][zeros[1]] ;

					successors.add(new GameState(Temp)) ;

					// WE WILL HAVE TO RESET TEMP, we can create a function to reset temp

					initarray(Temp,board) ;

					//swap(Temp[i][j],Temp[zeros[2]][zeros[3]]) ;
					Temp[i][j] = Temp[i][j] + Temp[zeros[2]][zeros[3]] ;
					Temp[zeros[2]][zeros[3]] = Temp[i][j] - Temp[zeros[2]][zeros[3]]  ;
					Temp[i][j] = Temp[i][j] - Temp[zeros[2]][zeros[3]] ;
					successors.add(new GameState(Temp)) ;

					initarray(Temp,board) ;
				}
			}
		}		//END OF FOR LOOP TRAVERSING THROUGH THE ARRAY BOARD[i][j]


//-------------------------------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------------------------------
		Collections.sort(successors, stateComparator);

        return successors;
    }

    // return the 20-digit number as ID
    public String getStateID() {  
        String s = "";
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++)
                s += this.board[i][j];
        }
        return s;
    }

    public void printBoard() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++)
                System.out.print(this.board[i][j]);
            System.out.println();
        }
    }

    // check whether the current state is the goal
    public boolean goalCheck() {        
    	if(board[3][1]==1 && board[3][2]==1 && board[4][1]==1 && board[4][2]==1)
    		return true ;
    	else
        	return false;
    }

    // add new methods for the GameState if necessary        

}

class AStarSearch{
    Queue<GameState> openSet;
    Set<GameState> closedSet;

    //Comparator for the GameState
    public Comparator<GameState> stateComparator = new Comparator<GameState>() {
        @Override
        public int compare(GameState o1, GameState o2) {
            if (o1.cost - o2.cost != 0)
                return o1.cost - o2.cost;
            else
                return o1.getStateID().compareTo(o2.getStateID());
        }
    };   

    // print the states of board in open set
    public void printOpenList(int flag, GameState state) {
        System.out.println("OPEN");
        
        
    }

    public void printClosedList(int flag, GameState state) {
        System.out.println("CLOSED");
        
    }

    // implement the A* search
    public GameState aStarSearch(int flag, GameState state) {   
        // feel free to using other data structures if necessary
        openSet = new PriorityQueue<>(stateComparator);
        closedSet = new HashSet<>();
        int goalCheck = 0;
        int maxOPEN = -1;
        int maxCLOSED = -1;
        int steps = 0;       
        GameState n = state ; 


        if (flag == 200){
        	while(n.goalCheck()==false){
        	//Putting a start node on the priority queue
        	openSet.add(state);
        	//Checking if OPEN Set is Empty
        	if(openSet.isEmpty()){
        		System.out.println("The OPEN Set is Empty, Exiting with Failure") ;
        		return state ;
        	}
        	//Peeking into the OPEN Set as Hashtable peek not possible
        	n = openSet.peek() ;

        	//Inserting value into the closed table
        	closedSet.add(openSet.remove()) ; 
        	n.printBoard() ; 
        	//Adding the neighbours
        	 List<GameState> statesucc = n.getNextStates() ;
        	 for(GameState sucstate:statesucc){
        	 	openSet.add(sucstate) ;
        	 }
        	}
        }

        if(flag==3){
        System.out.println("goalCheckTimes " + goalCheck);
        System.out.println("maxOPENSize " + maxOPEN);
        System.out.println("maxCLOSEDSize " + maxCLOSED);
        System.out.println("steps " + steps);
    }
        return state;
    }    

    // add more methods for the board* search if necessary
}

public class Klotski {  

	
    public static void printNextStates(GameState s) {
        List<GameState> states = s.getNextStates();
        for (GameState state: states) {
            state.printBoard();
            System.out.println();
        }
    }

    public static void main(String[] args) {
        if (args == null || args.length < 21) {
        	System.out.println("ERROR") ;
        	//int a = 3 ;
        	//int b = 2 ;
        	//swap(a,b) ;
        	//System.out.println(a + " " + b) ;
            return;
        }
        int flag = Integer.parseInt(args[0]);
        int[][] board = new int[5][4];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                board[i][j] = Integer.parseInt(args[i * 4 + j + 1]);
            }                
        }        
        GameState s = new GameState(board, 0);
        AStarSearch search = new AStarSearch(); 

        if (flag == 100) {
            printNextStates(s);
            System.out.println(s.goalCheck()) ;
            return;
        }
        if (flag == 200){	        
        	search.aStarSearch(flag, s);
        	return;
        }
  
        
    }

}
