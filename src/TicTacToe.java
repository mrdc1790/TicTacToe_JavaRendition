import java.util.Random;
import java.util.Scanner;

public class TicTacToe{
    static Scanner input = new Scanner(System.in);

    private static boolean check(int[] arr, int toCheckValue) {
        boolean test = false;
        for (int element : arr) {
            if (element == toCheckValue) {
                test = true;
                break;
            }
        }
        if (test == true)
            return true;
        else
            return false;
    }

    public static String choose_first(){
        Random rand = new Random();
        int upperBound = 2;
        int int_random = rand.nextInt(upperBound);
        if (int_random == 0)
                return "Player 1";
        else
            return "Player 2";
    }

    public static void display_board(String[] board){
        System.out.print(board[0]+" | "+board[1]+" | "+board[2]);
        System.out.println();
        System.out.print(board[3]+" | "+board[4]+" | "+board[5]);
        System.out.println();
        System.out.print(board[6]+" | "+board[7]+" | "+board[8]);
    }

    public static String[] player_input(){
        Scanner input = new Scanner(System.in);
        String marker = "";
        String[] markerArray = new String[2];
        while (!(marker.equals("X") || marker.equals("O"))) {
            System.out.println("Player 1: Do you want to be X or O? ");
            marker = input.nextLine().toUpperCase();
        }
        if (!(marker=="X")){
            markerArray[0] = "X";
            markerArray[1] = "O";
        } else {
            markerArray[0] = "O";
            markerArray[1] = "X";
        }
        return markerArray;
    }

    public static int playerChoice(String[] board){
        int[] checkInt = new int[]{1,2,3,4,5,6,7,8,9};
        int pos = 0;
        while ((check(checkInt, pos)==false)){
            System.out.println("Please enter a numerical value from 1-9 for where you want your marker to be placed: ");
            pos = input.nextInt();
        }
        return pos;
    }

    public static String place_marker(String[] board, String marker, int position){
        if (space_check(board, position)==true)
            return board[position] = marker;
        else {
            System.out.println("Spot is taken. Please try again.");
            place_marker(board, marker, playerChoice(board)-1);
        }
        return board[position];
    }

    public static boolean space_check(String[] board, int position){
        if (board[position] == " ")
            return true;
        else
            return false;
    }

    public static boolean win_check(String[] board, String mark){
        return ((board[6] == mark && board[7] == mark && board[8] == mark) ||
                (board[3] == mark && board[4] == mark && board[5] == mark) ||
                (board[0] == mark && board[1] == mark && board[2] == mark) ||
                (board[6] == mark && board[3] == mark && board[0] == mark) ||
                (board[7] == mark && board[4] == mark && board[1] == mark) ||
                (board[8] == mark && board[5] == mark && board[2] == mark) ||
                (board[6] == mark && board[4] == mark && board[2] == mark) ||
                (board[8] == mark && board[4] == mark && board[0] == mark));
    }

    public static boolean full_board_check(String[] board){
        for (int i = 1; i <board.length; i++){
            if (space_check(board, i))
                    return false;
        }
        return true;
    }

    public static boolean replay(){
        System.out.println("Do you want to play again? Enter Yes or No: ");
        String answer = input.nextLine().toLowerCase();
        if (answer.startsWith("y"))
            return true;
        else
            return false;
    }

    public static void main (String[] args){
        System.out.println("Welcome to Tic Tac Toe!");
        String[] player_markers;
        String turn;
        int pos;
        boolean game_on = true;
        while (true) {
            String[] game_board = new String[9];
            for (int j = 0; j < 9; j++) {
                game_board[j] = " ";
            }
            player_markers = player_input();
            System.out.println("Player 1's marker is : " + player_markers[0]);
            System.out.println("Player 2's marker is : " + player_markers[1]);
            turn = choose_first();
            System.out.println(turn + " will go first.");
            while (game_on == true) {
                display_board(game_board);
                System.out.println();
                pos = playerChoice(game_board);
                if (turn == "Player 1") {
                    place_marker(game_board, player_markers[0], pos - 1);

                    if (win_check(game_board, player_markers[0])) {
                        display_board(game_board);
                        System.out.println("Congratulations! You won!");
                        game_on = false;
                    } else {
                        if (full_board_check(game_board)) {
                            display_board(game_board);
                            System.out.println("The game is a draw!");
                            break;
                        } else {
                            turn = "Player 2";
                            System.out.println("\nPlayer 2's Turn!\n");
                            continue;
                        }
                    }
                } else {
                    place_marker(game_board, player_markers[1], pos - 1);

                    if (win_check(game_board, player_markers[1])) {
                        display_board(game_board);
                        System.out.println("\nCongratulations! You won!");
                        game_on = false;
                    } else {
                        if (full_board_check(game_board)) {
                            display_board(game_board);
                            System.out.println("The game is a draw!");
                            break;
                        } else {
                            turn = "Player 1";
                            System.out.println("\nPlayer 1's Turn!\n");
                            continue;
                        }
                    }
                }
            }
            if (replay() == false) {
                System.out.print("Thank you for playing!");
                break;
            }
        }
    }
}
