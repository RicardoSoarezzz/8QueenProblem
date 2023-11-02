/**
 * Problema de Satisfação de Restrições - 8 Rainhas
 * Inteligência Artificial - T2
 *
 * @author  Ricardo Soares - 44375
 * @author  Miguel Moreira - 44594
 * @author  Manuel Brandão - 38909
 */

import java.util.Arrays;
import java.util.Random;

public class ConstraintSatisfaction {
    // Tamanho do tabuleiro (N x N)
    static final int N = 8;
    static Random random = new Random();

    /**
     * Verifica se é seguro colocar uma rainha em uma determinada posição no tabuleiro.
     *
     * @param board O tabuleiro de xadrez
     * @param row   A linha onde se pretende colocar a rainha
     * @param col   A coluna onde se pretende colocar a rainha
     * @return      True se for seguro colocar a rainha, caso contrário, False
     */
    static boolean isSafe(int[][] board, int row, int col) {
        // Verifica se há uma rainha na mesma linha à esquerda
        for (int x = 0; x < col; x++) {
            if (board[row][x] == 1) {
                return false;
            }
        }

        // Verifica se há uma rainha na diagonal superior esquerda
        for (int x = row, y = col; x >= 0 && y >= 0; x--, y--) {
            if (board[x][y] == 1) {
                return false;
            }
        }

        // Verifica se há uma rainha na diagonal inferior esquerda
        for (int x = row, y = col; x < N && y >= 0; x++, y--) {
            if (board[x][y] == 1) {
                return false;
            }
        }

        // Se não houver rainha em nenhuma das posições acima, é seguro colocar uma rainha
        return true;
    }

    /**
     * Resolve o problema das N-rainhas com "backtracking".
     *
     * @param board O tabuleiro de xadrez
     * @param col   A coluna atual onde se está a tentar colocar uma rainha
     * @return      True se encontrar uma solução, caso contrário, False
     */
    static boolean solveNQueens(int[][] board, int col) {
        // Se todas as rainhas estiverem colocadas, imprime o tabuleiro
        if (col == N) {
            for (int[] row : board) {
                System.out.println(Arrays.toString(row));
            }
            System.out.println();
            return true;
        }

        // Gere uma permutação aleatória das posições das linhas
        int[] randomPermutation = getRandomPermutation(N);
        for (int i = 0; i < N; i++) {
            int row = randomPermutation[i];
            if (isSafe(board, row, col)) {
                board[row][col] = 1; // Coloca a rainha
                if (solveNQueens(board, col + 1)) {
                    return true;
                }

                // Retrocede se a colocação da rainha não levar a uma solução
                board[row][col] = 0;
            }
        }

        // Se nenhuma solução for encontrada, retorna falso
        return false;
    }

    /**
     * Gera uma permutação aleatória de números de 0 a N-1.
     *
     * @param N O tamanho da permutação
     * @return  Um array que contem uma permutação aleatória
     */
    static int[] getRandomPermutation(int N) {
        int[] permutation = new int[N];
        for (int i = 0; i < N; i++) {
            permutation[i] = i;
        }

        for (int i = N - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = permutation[i];
            permutation[i] = permutation[j];
            permutation[j] = temp;
        }

        return permutation;
    }

    // Inicia o tabuleiro com zeros e chama a função solveNQueens para resolver o problema
    public static void main(String[] args) {
        int[][] board = new int[N][N];
        if (!solveNQueens(board, 0)) {
            System.out.println("Nenhuma solução encontrada");
        }
    }
}
