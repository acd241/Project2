import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SwingTest extends JFrame
{
    private JPanel[][] squares = new JPanel[40][40];
    private Color[][] colors = new Color[40][40];
    private ArrayList<int[][]> boardStates = new ArrayList<>();
    int boardNum = 0;

    public SwingTest()
    {
        populateBoardStates();

        setTitle("Grid App");
        setSize(420, 470);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel grid = new JPanel();
        JPanel button = new JPanel();
        JButton prev = new JButton("Prev");
        JButton next = new JButton("Next");

        grid.setLayout(new GridLayout(40, 40));
        initializeGrid(grid);
        add(grid, BorderLayout.CENTER);

        prev.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                goBack();
            }
        });

        next.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                goForward();
            }
        });

        button.add(prev);
        button.add(next);
        add(button, BorderLayout.SOUTH);
        setColors(boardStates.get(0));
        setVisible(true);
    }

    private void goBack()
    {
        if (boardNum > 0)
        {
            boardNum--;
            setColors(boardStates.get(boardNum));
        }
    }

    private void goForward()
    {
        if (boardNum < boardStates.size() - 1)
        {
            boardNum++;
            setColors(boardStates.get(boardNum));
        }
    }

    private void setColors(int[][] c)
    {
        for (int i = 0; i < 40; i++)
        {
            for (int j = 0; j < 40; j++)
            {
                if (c[i][j] == 1)
                {
                    colors[i][j] = Color.LIGHT_GRAY;
                }
                else if (c[i][j] == 0)
                {
                    colors[i][j] = Color.DARK_GRAY;
                }
                else if (c[i][j] == 2)
                {
                    colors[i][j] = Color.BLACK;
                }
                else if (c[i][j] == 3)
                {
                    colors[i][j] = Color.PINK;
                }
                else if (c[i][j] == 5)
                {
                    colors[i][j] = Color.ORANGE;
                }

                squares[i][j].setBackground(colors[i][j]);;
            }
        }
    }

    private void initializeGrid(JPanel g)
    {
        for (int i = 0; i < 40; i++)
        {
            for (int j = 0; j < 40; j++)
            {
                JPanel box = new JPanel();
                box.setPreferredSize(new Dimension(10, 10));
                box.setBackground(Color.WHITE);
                g.add(box);

                colors[i][j] = Color.WHITE;
                squares[i][j] = box;
            }
        }
    }

    private void populateBoardStates() 
    {
        try
        {
            BufferedReader r = new BufferedReader(new FileReader("moves.txt"));
            String line;

            while ((line = r.readLine()) != null)
            {
                String[] nums = line.split(" ");
                int[][] board = new int[40][40];
                int c = 0;

                for (int i = 0; i < 40; i++)
                {
                    for (int j = 0; j < 40; j++)
                    {
                        board[i][j] = Integer.parseInt(nums[c]);
                        c++;
                    }
                }

                boardStates.add(board);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) 
    {
        new SwingTest();    
    }
}
