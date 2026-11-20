import java.util.Random;
import java.util.Scanner;

public class TicTacToe{
    static Scanner input = new Scanner(System.in);

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
        while (true) {
            System.out.println("Player 1: Do you want to be X or O? ");
            if (!input.hasNextLine()) {
                return null;
            }
            String marker = input.nextLine().trim().toUpperCase();
            if ("X".equals(marker) || "O".equals(marker)) {
                return new String[]{marker, "X".equals(marker) ? "O" : "X"};
            }
        }
    }

    public static int playerChoice(String[] board){
        while (true) {
            System.out.println("Enter a position from 1 to 9:");
            if (!input.hasNextLine()) {
                return 0;
            }
            int pos;
            try {
                pos = Integer.parseInt(input.nextLine().trim());
            } catch (NumberFormatException error) {
                continue;
            }
            if (pos >= 1 && pos <= board.length) {
                if (space_check(board, pos - 1)) {
                    return pos;
                }
                System.out.println("Spot is taken. Please try again.");
            }
        }
    }

    public static String place_marker(String[] board, String marker, int position){
        if (space_check(board, position)) {
            board[position] = marker;
        }
        return board[position];
    }

    public static boolean space_check(String[] board, int position){
        return " ".equals(board[position]);
    }

    public static boolean win_check(String[] board, String mark){
        return ((mark.equals(board[6]) && mark.equals(board[7]) && mark.equals(board[8])) ||
                (mark.equals(board[3]) && mark.equals(board[4]) && mark.equals(board[5])) ||
                (mark.equals(board[0]) && mark.equals(board[1]) && mark.equals(board[2])) ||
                (mark.equals(board[6]) && mark.equals(board[3]) && mark.equals(board[0])) ||
                (mark.equals(board[7]) && mark.equals(board[4]) && mark.equals(board[1])) ||
                (mark.equals(board[8]) && mark.equals(board[5]) && mark.equals(board[2])) ||
                (mark.equals(board[6]) && mark.equals(board[4]) && mark.equals(board[2])) ||
                (mark.equals(board[8]) && mark.equals(board[4]) && mark.equals(board[0])));
    }

    public static boolean full_board_check(String[] board){
        for (int i = 0; i <board.length; i++){
            if (space_check(board, i))
                    return false;
        }
        return true;
    }

    public static boolean replay(){
        System.out.println("Do you want to play again? Enter Yes or No: ");
        return input.hasNextLine() && input.nextLine().trim().toLowerCase().startsWith("y");
    }

    public static void main (String[] args){
        System.out.println("Welcome to Tic Tac Toe!");
        String[] player_markers;
        String turn;
        int pos;
        while (true) {
            boolean game_on = true;
            String[] game_board = new String[9];
            for (int j = 0; j < 9; j++) {
                game_board[j] = " ";
            }
            player_markers = player_input();
            if (player_markers == null) {
                return;
            }
            System.out.println("Player 1's marker is : " + player_markers[0]);
            System.out.println("Player 2's marker is : " + player_markers[1]);
            turn = choose_first();
            System.out.println(turn + " will go first.");
            while (game_on == true) {
                display_board(game_board);
                System.out.println();
                pos = playerChoice(game_board);
                if (pos == 0) {
                    return;
                }
                if ("Player 1".equals(turn)) {
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
