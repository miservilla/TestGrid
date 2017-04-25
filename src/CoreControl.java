import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class CoreControl {

    public static class Grid extends JPanel {

        private List<String> fillCells;
        private static final Color O = Color.RED;
        private static final Color X = Color.BLUE;
        private static final Color H = Color.YELLOW;
        private static final Color U = Color.BLACK;
        private static final Color G = Color.LIGHT_GRAY;

        public Grid() {
            fillCells = new ArrayList<>(25);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (String fillCell : fillCells) {
                String[] stringBlock = fillCell.split("\\s+");
                int x = Integer.parseInt(stringBlock[0]);
                int y = Integer.parseInt(stringBlock[1]);
                String charColor = stringBlock[2];


                int cellX = 10 + (x * 10);
                int cellY = 10 + (y * 10);
                g.setColor(getColor(charColor));
                g.fillRect(cellX, cellY, 50, 50);//Sets fill cell size.
            }
            g.setColor(Color.BLACK);
            g.drawRect(10, 10, 500, 800);

            for (int i = 10; i <= 500; i += 50) {
                g.drawLine(i, 10, i, 810);
            }

            for (int i = 10; i <= 800; i += 50) {
                g.drawLine(10, i, 510, i);
            }
        }

        public void fillCell(String block) {
            fillCells.add(block);
            repaint();
        }
        Color getColor( String colorCode) {
            Color cellColor = G;
            switch (colorCode) {
                case "O": cellColor = O;
                    break;
                case "X": cellColor = X;
                    break;
                case "H": cellColor = H;
                    break;
                case "U": cellColor = U;
                    break;
                case "G": cellColor = G;
            }
            return cellColor;
        }

    }

    public static void main(String[] a) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }

                Grid grid = new Grid();
                JFrame window = new JFrame("COLUMNS.UGH");
                window.setSize(523, 847);
                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                window.setResizable(false);
                window.add(grid);
                window.setVisible(true);
                grid.fillCell("15 30 U");
                grid.fillCell("0 25 X");
                grid.fillCell("35 5 H");
                grid.fillCell("25 5 U");
                grid.fillCell("5 5 H");
            }
        });
    }
}