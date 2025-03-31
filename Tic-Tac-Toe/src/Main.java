import java.util.Scanner;
import java.util.Random;

class Main {
    public static char player = 'X', computer = 'O';

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] spaces = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};

        while (true) {
            GetBoard(spaces);
            PlayerMove(spaces, scanner);
            if (CheckWinner(spaces) || isBoardFull(spaces)) {
                break;
            }

            GetBoard(spaces);
            ComputerMove(spaces);
            if (CheckWinner(spaces) || isBoardFull(spaces)) {
                break;
            }
        }

        GetBoard(spaces);
        scanner.close();
    }

    public static void GetBoard(char[] spaces) {
        System.out.println("     |     |     ");
        System.out.println("  " + spaces[0] + "  |  " + spaces[1] + "  |  " + spaces[2] + "  ");
        System.out.println("_____|_____|_____");
        System.out.println("     |     |     ");
        System.out.println("  " + spaces[3] + "  |  " + spaces[4] + "  |  " + spaces[5] + "  ");
        System.out.println("_____|_____|_____");
        System.out.println("     |     |     ");
        System.out.println("  " + spaces[6] + "  |  " + spaces[7] + "  |  " + spaces[8] + "  ");
        System.out.println("     |     |     ");
    }

    public static void PlayerMove(char[] spaces, Scanner scanner) {
        int spot;
        while (true) {
            System.out.print("Select a spot (1-9): ");
            if (scanner.hasNextInt()) {
                spot = scanner.nextInt();

                if (spot >= 1 && spot <= 9 && spaces[spot - 1] == ' ') {
                    spaces[spot - 1] = player;
                    break;
                }
                else {
                    System.out.println("Invalid move. Try again.");
                }
            }
            else {
                System.out.println("Invalid input. Enter a number between 1-9.");
                scanner.next(); // clear invalid input
            }
        }
    }

    public static void ComputerMove(char[] spaces) {
        Random random = new Random();
        int move;
        while (true) {
            move = random.nextInt(9);
            if (spaces[move] == ' ') {
                spaces[move] = computer;
                break;
            }
        }
    }

    public static boolean CheckWinner(char[] spaces) {
        int[][] winningCombinations = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                {0, 4, 8}, {2, 4, 6}
        };

        for (int[] combo : winningCombinations) {
            if (spaces[combo[0]] != ' ' &&
                    spaces[combo[0]] == spaces[combo[1]] &&
                    spaces[combo[1]] == spaces[combo[2]]) {

                System.out.println((spaces[combo[0]] == player) ? "Player has won!" : "Computer has won!");
                return true;
            }
        }
        return false;
    }

    public static boolean isBoardFull(char[] spaces) {
        for (char space : spaces) {
            if (space == ' ') {
                return false;
            }
        }
        System.out.println("It's a draw!");
        return true;
    }
}