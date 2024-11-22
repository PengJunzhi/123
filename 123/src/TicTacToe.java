import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TicTacToe extends JFrame {
    private String[][] board = new String[10][10];
    private boolean player1Turn = true;
    private int turnCount = 0;
    private JLabel statusLabel = new JLabel("Player X's Turn");
    private final int cellSize = 80;

    public TicTacToe() {
        setTitle("Tic Tac Toe");
        setSize(800, 900);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawBoard(g);
                drawPieces(g);
            }
        };

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / cellSize;
                int y = e.getY() / cellSize;

                if (board[y][x] == null) {
                    board[y][x] = player1Turn ? "X" : "O";
                    turnCount++;

                    if (checkWin()) {
                        String winner = player1Turn ? "Player X" : "Player O";
                        JOptionPane.showMessageDialog(null, winner + " wins!");
                        resetBoard();
                    } else if (turnCount == 100) {
                        JOptionPane.showMessageDialog(null, "It's a tie!");
                        resetBoard();
                    } else {
                        player1Turn = !player1Turn;
                        statusLabel.setText(player1Turn ? "Player X's Turn" : "Player O's Turn");
                    }

                    panel.repaint();
                }
            }
        });

        panel.setPreferredSize(new Dimension(800, 800));
        add(panel);
        add(statusLabel, BorderLayout.SOUTH);
        setVisible(true);
    }

    private void drawBoard(Graphics g) {
        g.setColor(Color.BLACK);
        for (int i = 0; i <= 10; i++) {
            g.drawLine(0, i * cellSize, 800, i * cellSize);
            g.drawLine(i * cellSize, 0, i * cellSize, 800);
        }
    }

    private void drawPieces(Graphics g) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (board[i][j] != null) {
                    int x = j * cellSize + cellSize / 2;
                    int y = i * cellSize + cellSize / 2;
                    if (board[i][j].equals("X")) {
                        g.setColor(Color.BLACK);
                        g.fillOval(x - 10, y - 10, 20, 20);
                    } else if (board[i][j].equals("O")) {
                        g.setColor(Color.WHITE);
                        g.fillOval(x - 10, y - 10, 20, 20);
                        g.setColor(Color.BLACK);
                        g.drawOval(x - 10, y - 10, 20, 20);
                    }
                }
            }
        }
    }

    private boolean checkWin() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j <= 5; j++) {
                if (board[i][j] != null &&
                    board[i][j].equals(board[i][j + 1]) &&
                    board[i][j].equals(board[i][j + 2]) &&
                    board[i][j].equals(board[i][j + 3]) &&
                    board[i][j].equals(board[i][j + 4])) {
                    return true;
                }
            }
        }

        for (int i = 0; i <= 5; i++) {
            for (int j = 0; j < 10; j++) {
                if (board[i][j] != null &&
                    board[i][j].equals(board[i + 1][j]) &&
                    board[i][j].equals(board[i + 2][j]) &&
                    board[i][j].equals(board[i + 3][j]) &&
                    board[i][j].equals(board[i + 4][j])) {
                    return true;
                }
            }
        }

        for (int i = 0; i <= 5; i++) {
            for (int j = 0; j <= 5; j++) {
                if (board[i][j] != null &&
                    board[i][j].equals(board[i + 1][j + 1]) &&
                    board[i][j].equals(board[i + 2][j + 2]) &&
                    board[i][j].equals(board[i + 3][j + 3]) &&
                    board[i][j].equals(board[i + 4][j + 4])) {
                    return true;
                }
            }
        }

        for (int i = 0; i <= 5; i++) {
            for (int j = 4; j < 10; j++) {
                if (board[i][j] != null &&
                    board[i][j].equals(board[i + 1][j - 1]) &&
                    board[i][j].equals(board[i + 2][j - 2]) &&
                    board[i][j].equals(board[i + 3][j - 3]) &&
                    board[i][j].equals(board[i + 4][j - 4])) {
                    return true;
                }
            }
        }

        return false;
    }

    private void resetBoard() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                board[i][j] = null;
            }
        }
        turnCount = 0;
        player1Turn = true;
        statusLabel.setText("Player X's Turn");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TicTacToe());
    }
}