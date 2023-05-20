import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.LinkedList;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class chess2
{
    public static JFrame frame;

    public static ImageIcon wrook, wknight, wbishop, wking, wqueen, wpawn;
    public static ImageIcon brook, bknight, bbishop, bking, bqueen, bpawn;
    static JButton[][] chessTiles = new JButton[8][8];

    static JPanel PiecePanel;

    static String[] names;

    public static int fromX, fromY;
    public static int toX, toY;
    public static ImageIcon image;
    static String path;
    static String name;
    
    static LinkedList<Pece> black_piece_list = new LinkedList<Pece>();
    static LinkedList<Pece> white_piece_list = new LinkedList<Pece>();

    static int row, col;

    static boolean check2 = false;
    static int capX = 0, capY = 0;
    static int beCapX = 0, beCapY = 0;

    static Boolean turn_for_white = true;

    static ImageIcon blackPawnAttack;
    static int bPAttackPX, bPAttackPY;
    static String tooltip;
    static boolean one_more;
    static int fromXP, fromYP, toXP, toYP;

    static int fromAttackX = 0, fromAttackY = 0;
    static Boolean bishopAttack = null;
    static ImageIcon icon = null;

    static Boolean rookAttack = null;

    static int Process_control;

    static Boolean absolute = null;
    static int capture = 0;
    static JButton fromButton;
    static Boolean color1 = null, color2 = null;
    
    static int rook_castle1 = 0, rook_castle2 = 0, king_castle = 0;
    static int Rook_castle1 = 0, Rook_castle2 = 0, King_castle = 0;
    static Pece rook1, rook2, king;
    static Pece Rook1, Rook2, King;

    static int bkingX, bkingY;
    static int wkingX, wkingY;

    static int wcheck = 0, bcheck  = 0;

    public static JFrame mainFrame;
    public static JFrame methodFrame;
    public static JPanel panel;

    static Font font = new Font("Arial", Font.BOLD, 16);
    static Font font1 = new Font("Arial", Font.BOLD, 15);

    public static void main(String[] args)
    {
        createMainMenu();
    }

    public static void createMainMenu() 
    {
        wqueen = new ImageIcon("C:\\VS Code\\CHESS\\Chess\\src\\white\\Queen.png");
        wpawn = new ImageIcon("C:\\VS Code\\CHESS\\Chess\\src\\white\\Pawn.png");

        mainFrame = new JFrame("Main Menu");
        mainFrame.setSize(500, 500);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JButton b = new JButton("CHESS");
        JButton c = new JButton("SPECIAL");

        b.setBounds(60, 170, 100, 100);
        c.setBounds(300, 170, 100, 100);

        panel = new JPanel();
        panel.setBounds(0, 0, 500, 500);
        panel.setLayout(null);

        panel.add(b);
        panel.add(c);
        panel.setBackground(Color.black);

        b.setLayout(new BoxLayout(b, BoxLayout.Y_AXIS));
        c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));

        // Set text alignment to center
        b.setHorizontalTextPosition(JButton.CENTER);
        b.setVerticalTextPosition(JButton.BOTTOM);
        c.setHorizontalTextPosition(JButton.CENTER);
        c.setVerticalTextPosition(JButton.BOTTOM);

        b.setBackground(Color.gray);
        c.setBackground(Color.gray);
        b.setForeground(Color.black);
        c.setForeground(Color.black);

        b.setFont(font);
        c.setFont(font1);

        b.setIcon(wpawn);
        c.setIcon(wqueen);

        b.setFocusable(false);
        c.setFocusable(false);

        ImageIcon i = new ImageIcon("C:\\VS Code\\CHESS\\Chess\\src\\tilted\\Bishop.png");
        JLabel imageLabel = new JLabel(i);
        imageLabel.setBounds(435, 410, 50, 50);
        
        ImageIcon k = new ImageIcon("C:\\VS Code\\CHESS\\Chess\\src\\tilted\\Knight.png");
        JLabel knight = new JLabel(k);
        knight.setBounds(5, 410, 50, 50);

        ImageIcon ki = new ImageIcon("C:\\VS Code\\CHESS\\Chess\\src\\tilted\\King.png");
        JLabel king = new JLabel(ki);
        king.setBounds(230, 0, 50, 50);

        panel.add(imageLabel);
        panel.add(knight);
        panel.add(king);

        mainFrame.add(panel);
        mainFrame.setLayout(null);

        b.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                mainFrame.setVisible(false);
                run();
            }
        });

        c.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                mainFrame.setVisible(false);
                special();
            }
        });

        mainFrame.setVisible(true);
    }

    public static void run()
    {
        frame = new JFrame("Chess");
        chessTiles = new JButton[8][8];

        JButton back = new JButton("BACK");
        back.setFont(font);
        back.setForeground(Color.white);
        back.setBackground(Color.black);
        back.setBounds(770, 15, 110, 40);
        back.setFocusable(false);
        frame.add(back);

        back.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                frame.dispose(); // Close the method frame
                mainFrame.setVisible(true); // Show the main menu frame
            }
        });

        JPanel panel = new JPanel(new GridLayout(8, 8));
        //panel.setBackground(Color.black);
        panel.setBounds(10, 10, 640, 640);
        
        JPanel side_panel = new JPanel();
        side_panel.setLayout(null);
        side_panel.setBounds(660, 10, 240, 620);
        side_panel.setBackground(Color.gray);
        frame.add(side_panel);

        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                chessTiles[i][j] = new JButton();
                if((i + j) % 2 == 0)
                    chessTiles[i][j].setBackground(Color.WHITE);
                else
                    chessTiles[i][j].setBackground(Color.DARK_GRAY);

                panel.add(chessTiles[i][j]);
            }
        }

        wrook = new ImageIcon("C:\\VS Code\\CHESS\\Chess\\src\\white\\Rook.png");
        wknight = new ImageIcon("C:\\VS Code\\CHESS\\Chess\\src\\white\\Knight.png");
        wbishop = new ImageIcon("C:\\VS Code\\CHESS\\Chess\\src\\white\\Bishop.png");
        wking = new ImageIcon("C:\\VS Code\\CHESS\\Chess\\src\\white\\King.png");
        wqueen = new ImageIcon("C:\\VS Code\\CHESS\\Chess\\src\\white\\Queen.png");
        wpawn = new ImageIcon("C:\\VS Code\\CHESS\\Chess\\src\\white\\Pawn.png");
        
        brook = new ImageIcon("C:\\VS Code\\CHESS\\Chess\\src\\black\\Rook.png");
        bknight = new ImageIcon("C:\\VS Code\\CHESS\\Chess\\src\\black\\Knight.png");
        bbishop = new ImageIcon("C:\\VS Code\\CHESS\\Chess\\src\\black\\Bishop.png");
        bking = new ImageIcon("C:\\VS Code\\CHESS\\Chess\\src\\black\\King.png");
        bqueen = new ImageIcon("C:\\VS Code\\CHESS\\Chess\\src\\black\\Queen.png");
        bpawn = new ImageIcon("C:\\VS Code\\CHESS\\Chess\\src\\black\\Pawn.png");
        
        Pece knight1 = new Pece("Knight", true, 0, 1, bknight);
        Pece knight2 = new Pece("Knight", true, 0, 6, bknight);
        Pece queen = new Pece("Queen", true, 0, 3, bqueen);
        Pece bishop1 = new Pece("Bishop", true, 0, 2, bbishop);
        Pece bishop2 = new Pece("Bishop", true, 0, 5, bbishop);
        Pece p1 = new Pece("Pawn", true, 1, 0, bpawn);
        Pece p2 = new Pece("Pawn", true, 1, 1, bpawn);
        Pece p3 = new Pece("Pawn", true, 1, 2, bpawn);
        Pece p4 = new Pece("Pawn", true, 1, 3, bpawn);
        Pece p5 = new Pece("Pawn", true, 1, 4, bpawn);
        Pece p6 = new Pece("Pawn", true, 1, 5, bpawn);
        Pece p7 = new Pece("Pawn", true, 1, 6, bpawn);
        Pece p8 = new Pece("Pawn", true, 1, 7, bpawn);
        king = new Pece("King", true, 0, 4, bking);
        rook1 = new Pece("Rook", true, 0, 0, brook);
        rook2 = new Pece("Rook", true, 0, 7, brook);
        
        black_piece_list.add(rook1);
        black_piece_list.add(rook2);
        black_piece_list.add(bishop1);
        black_piece_list.add(bishop2);
        black_piece_list.add(king);
        black_piece_list.add(queen);
        black_piece_list.add(knight1);
        black_piece_list.add(knight2);
        black_piece_list.add(p1);
        black_piece_list.add(p2);
        black_piece_list.add(p3);
        black_piece_list.add(p4);
        black_piece_list.add(p5);
        black_piece_list.add(p6);
        black_piece_list.add(p7);
        black_piece_list.add(p8);

        names = new String[16];

        for(int i = 0; i < 16; i++)
            names[i] = black_piece_list.get(i).getName();
       
        for(int i = 0; i < 16; i++) 
        {
            Pece element = black_piece_list.get(i);
            chessTiles[element.xCell][element.yCell].setIcon(element.getIcon());
            chessTiles[element.xCell][element.yCell].setToolTipText(names[i]);
        }
        
        Pece Knight1 = new Pece("Knight", false, 7, 1, wknight);
        Pece Knight2 = new Pece("Knight", false, 7, 6, wknight);
        Pece Queen = new Pece("Queen", false, 7, 3, wqueen);
        Pece Bishop1 = new Pece("Bishop", false, 7, 2, wbishop);
        Pece Bishop2 = new Pece("Bishop", false, 7, 5, wbishop);
        Pece P1 = new Pece("Pawn", false, 6, 0, wpawn);
        Pece P2 = new Pece("Pawn", false, 6, 1, wpawn);
        Pece P3 = new Pece("Pawn", false, 6, 2, wpawn);
        Pece P4 = new Pece("Pawn", false, 6, 3, wpawn);
        Pece P5 = new Pece("Pawn", false, 6, 4, wpawn);
        Pece P6 = new Pece("Pawn", false, 6, 5, wpawn);
        Pece P7 = new Pece("Pawn", false, 6, 6, wpawn);
        Pece P8 = new Pece("Pawn", false, 6, 7, wpawn);
        King = new Pece("King", false, 7, 4, wking);
        Rook1 = new Pece("Rook", false, 7, 0, wrook);
        Rook2 = new Pece("Rook", false, 7, 7, wrook);

        white_piece_list.add(Rook1);
        white_piece_list.add(Rook2);
        white_piece_list.add(Bishop1);
        white_piece_list.add(Bishop2);
        white_piece_list.add(Queen);
        white_piece_list.add(King);
        white_piece_list.add(Knight1);
        white_piece_list.add(Knight2);
        white_piece_list.add(P1);
        white_piece_list.add(P2);
        white_piece_list.add(P3);
        white_piece_list.add(P4);
        white_piece_list.add(P5);
        white_piece_list.add(P6);
        white_piece_list.add(P7);
        white_piece_list.add(P8);

        
        

        for(int i = 0; i < 16; i++) 
        {
            Pece element = white_piece_list.get(i);
            chessTiles[element.xCell][element.yCell].setIcon(element.getIcon());
            chessTiles[element.xCell][element.yCell].setToolTipText(names[i]);
        }
        chessTiles[7][4].setToolTipText("King");
        chessTiles[5][3].setToolTipText("Queen");

        bkingX = king.getxCell();
        bkingY = king.getyCell();

        wkingX = King.getxCell();
        wkingY = King.getyCell();
        

        for(int i = 0;  i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                chessTiles[i][j].setFocusable(false);
                chessTiles[i][j].addActionListener(new ActionListener() 
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        JButton source  = (JButton) e.getSource(); 
                        boolean proces = false;
                        image = new ImageIcon();
                        image = (ImageIcon) source.getIcon();

                        for(int i = 0; i < 8; i++)
                        {
                            if(chessTiles[0][i].getToolTipText() != null)// || chessTiles[0][i].getToolTipText() != null)
                            if(chessTiles[0][i].getToolTipText().equals("Pawn") )//|| chessTiles[0][i].getToolTipText().equals("Pawn"))
                            {
                                PiecePanel = new JPanel(null);
                                PiecePanel.setBounds(280, 280, 100, 100);
                                panel.add(PiecePanel);
                            }
                        }
                        
                        for (int i = 0; i < 8; i++) 
                        {
                           for (int j = 0; j < 8; j++) 
                            {
                               if (source == chessTiles[i][j] && wcheck == 0) //i is the column and j is the row
                                {
                                    if (capture == 0 && ((AbstractButton) source).getIcon() != null )
                                    {
                                        fromButton = (JButton) source;
                                        fromX = i;
                                        fromY = j;
                                        name = source.getToolTipText();
                                        Process_control = 1;
                                        capture++;
                                    }
                                    
                                    if (capture == 1 && ((AbstractButton) source).getIcon() != null && (fromX != i || fromY != j))
                                    {
                                        toX = i;
                                        toY = j;
                                    
                                        capture = 0;
                                        if(blackPiece(chessTiles[fromX][fromY].getIcon()) != blackPiece(chessTiles[toX][toY].getIcon()) && turn_for_white == !blackPiece(chessTiles[fromX][fromY].getIcon()))
                                            proces = true;
                                        
                                        else    
                                            capture = 0;
                                        
                                    }
                                    if (((AbstractButton) source).getIcon() == null && capture == 1)  
                                    {
                                        capture = 0;
                                        toX = i;
                                        toY = j;
                                        proces = true;
                                        Process_control = 0;
                                    }
                                    if(image != null)
                                    {
                                        File file = new File(image.toString());
                                        path = file.getAbsolutePath();
                                        path = path.replace("\\", "\\\\");
                                    }
                                    
                                    if (proces) 
                                    {
                                        String Piece_name = source.getToolTipText();
                                        ImageIcon Using_path = new ImageIcon(path);
                                        System.out.println();

                                        Boolean substitute = turn_for_white;
                                        if(name != null )// name is not null
                                        if(isValid(fromX, fromY, toX, toY, name))
                                        {
                                            
                                            for(int x = 0; x < white_piece_list.size(); x++)
                                            {
                                                white_piece_list.get(x).getName().equals(Piece_name);
                                                if(turn_for_white != null)
                                                if(turn_for_white)
                                                    if(fromX == white_piece_list.get(x).getxCell() && fromY == white_piece_list.get(x).getyCell())
                                                    {
                                                        
                                                        String name = chessTiles[fromX][fromY].getToolTipText();
                                                        turn_for_white = false;
                                                        white_piece_list.get(x).setyCell(toY);
                                                        white_piece_list.get(x).setxCell(toX);
                                                        chessTiles[fromX][fromY].setIcon(null);
                                                        chessTiles[toX][toY].setIcon(Using_path); 
                                                        chessTiles[white_piece_list.get(x).getxCell()][white_piece_list.get(x).getyCell()].setToolTipText(name);
                                                       
                                                        chessTiles[fromX][fromY].setToolTipText(null);

                                                    }
                                               
                                                if(turn_for_white != null)
                                                if(!turn_for_white)
                                                {
                                                    if(fromX == black_piece_list.get(x).getxCell() && fromY == black_piece_list.get(x).getyCell())
                                                    {
                                                        turn_for_white = true;
                                                        black_piece_list.get(x).setyCell(toY);
                                                        black_piece_list.get(x).setxCell(toX);
                                                        chessTiles[fromX][fromY].setIcon(null);
                                                        chessTiles[toX][toY].setIcon(Using_path); 
                                                        chessTiles[black_piece_list.get(x).getxCell()][black_piece_list.get(x).getyCell()].setToolTipText(black_piece_list.get(x).getName());
                                                        chessTiles[fromX][fromY].setToolTipText(null);
                                                    }
                                                }
                                            }
                                            turn_for_white = !substitute;
                                            System.out.println("turn for white after the substitute: "+turn_for_white);;
                                        }
                                        proces = false;
                                        System.out.println();
                                        
                                    }
                                } 
                            }
                        }
                    }    
                });
            
            }
        }
        frame.add(panel);
        frame.setSize(900, 700);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void special() 
    {
        new Special();
    }

    public static boolean isValid(int fromX, int fromY, int toX, int toY, String name)
    {
        boolean color_black = true;
        for(int i = 0; i < white_piece_list.size(); i++)
                if(white_piece_list.get(i).getxCell() == fromX && white_piece_list.get(i).getyCell() == fromY)
                    color_black = white_piece_list.get(i).isBlack;

        if(name.equals("Knight"))
        {  
            if(blackPiece( chessTiles[fromX][fromY].getIcon()))
            {
            
                int wrowDelta = Math.abs(wkingX - toX);
                int wcolDelta = Math.abs(wkingY - toY);
                if ((wrowDelta == 1 && wcolDelta == 2) || (wrowDelta == 2 && wcolDelta == 1)) 
                {
                    wcheck = 1;
                }
            }
            
            if(chessTiles[toX][toY].getIcon() == null && KnightAttack(fromX, fromY, toX, toY))
                return true;
            
            if(chessTiles[toX][toY].getIcon() != null && KnightAttack(fromX, fromY, toX, toY))
            {
                if(blackPiece(chessTiles[toX][toY].getIcon()) != blackPiece(chessTiles[fromX][fromY].getIcon()))
                {
                    kill(fromX, fromY, toX, toY, chessTiles[fromX][fromY].getIcon());
                    turn_for_white = (Boolean) null;
                    return true;
                }
            }

            return true;
        }
        
        if(name.equals("Pawn"))
        {

            int deltaY, deltaX;
            deltaX = Math.abs(toX - fromX);
            deltaY = Math.abs(toY - fromY);

            int direction;

            boolean Pcheck = blackPiece(chessTiles[fromX][fromY].getIcon());

            if(Pcheck == true)
                direction = 1;
            else    
                direction = -1;


            if(Pcheck)
                if ((toX + direction == wkingX) && (Math.abs(toY - wkingY) == 1)) 
                    System.out.println("Checkkkk");
                
            if(!Pcheck)
                if ((fromX + direction == bkingX) && (Math.abs(fromY - bkingY) == 1)) 
                    System.out.println("Checkkkk");

            if(chessTiles[toX][toY].getIcon() == null && PawnMovement(fromX, fromY, toX, toY, color_black))
                return true;
            
            if (deltaX == 1 && deltaY == 1 )//&& opposite_pieces) 
            {
                int adjacentX = (toX > fromX) ? fromX + 1 : fromX - 1;  // X coordinate of the adjacent position
                int adjacentY = (toY > fromY) ? fromY + 1 : fromY - 1;  // Y coordinate of the adjacent position

                if (chessTiles[adjacentX][adjacentY].getIcon() != null) 
                {
                    boolean opposite_pieces = blackPiece(chessTiles[fromX][fromY].getIcon()) != blackPiece(chessTiles[adjacentX][adjacentY].getIcon());
                    if(opposite_pieces)
                        kill(fromX, fromY, adjacentX, adjacentY, chessTiles[fromX][fromY].getIcon());
                    turn_for_white = (Boolean) null;
                    return true;
                }
            }

            return false;
        }
        
        if(name.equals("Bishop"))
        {
            
            int xDir = (toX - fromX) > 0 ? 1 : -1;
            int yDir = (toY - fromY) > 0 ? 1 : -1;
            int i = fromX + xDir;
            int j = fromY + yDir;
            while (i != toX && j != toY) 
            {
                if (chessTiles[i][j].getIcon() != null) 
                    return false;
                i += xDir;
                j += yDir;
            }

            if(isDiagonalMove(fromX, fromY, toX, toY) == false)
                return false;

            if(chessTiles[toX][toY].getIcon() != null && isDiagonalMove(fromX, fromY, toX, toY))
            {
                if(blackPiece(chessTiles[toX][toY].getIcon()) != blackPiece(chessTiles[fromX][fromY].getIcon()))
                {
                    kill(fromX, fromY, toX, toY, chessTiles[fromX][fromY].getIcon());
                    turn_for_white = (Boolean) null;
                    return true;
                }
            }
            return true;
        }
        if(name.equals("Rook"))
        {
            if(fromX == 0 && fromY == 0)//top left rook prevention for the castle
                rook_castle2++;

            if(fromX == 0 && fromY == 7)//top right rook
                rook_castle1++;

            if(fromX == 7 && fromY == 7)//bottom right
                Rook_castle2++;

            if(fromX == 7 && fromY == 0)//bttom left
                Rook_castle1++;

            if (toX == fromX) 
            {
                int minY = Math.min(fromY, toY);
                int maxY = Math.max(fromY, toY);
                for (int y = minY + 1; y < maxY; y++) 
                    if (chessTiles[toX][y].getIcon() != null) 
                        return false;
            }
            else if (toY == fromY) 
            {
                int minX = Math.min(fromX, toX);
                int maxX = Math.max(fromX, toX);
                for (int x = minX + 1; x < maxX; x++) 
                    if (chessTiles[x][toY].getIcon() != null) 
                        return false;
            } 

            if(isStrightMove(fromX, fromY, toX, toY) == false)
                return false;

            if(chessTiles[toX][toY].getIcon() != null && isStrightMove(fromX, fromY, toX, toY))
            {
                if(blackPiece(chessTiles[toX][toY].getIcon()) != blackPiece(chessTiles[fromX][fromY].getIcon()))
                {
                    kill(fromX, fromY, toX, toY, chessTiles[fromX][fromY].getIcon());
                    turn_for_white = (Boolean) null;
                    return true;
                }
            }
            return true;
        }
        if(name.equals("King"))
        {
            int fromx = fromX, fromy = fromY;
           
            if(chessTiles[toX][toY].getIcon() == null && KingMove(fromx, fromy, toX, toY))
            {
                // this is the black king
                if(fromX == bkingX && fromY == bkingY)
                {
                    bkingX = toX;
                    bkingY = toY;
                }
                
                if(fromX == wkingY && fromY == wkingY)// this is the white king
                {
                    wkingX = toX;
                    wkingY = toY;
                }
                return true;
            }
            if(chessTiles[toX][toY].getIcon() != null && KingMove(fromX, fromY, toX, toY))
            {
                kill(fromX, fromY, toX, toY, chessTiles[fromX][fromY].getIcon());
                turn_for_white = (Boolean) null;
                return true;
            }
        }
        if (name.equals("Queen")) 
        {
            if (toX == fromX || toY == fromY || Math.abs(toX - fromX) == Math.abs(toY - fromY)) 
            {
                if (toX == fromX) 
                {
                    int minY = Math.min(fromY, toY);
                    int maxY = Math.max(fromY, toY);
                    for (int y = minY + 1; y < maxY; y++) 
                        if (chessTiles[toX][y].getIcon() != null) 
                            return false;
                }
                else if (toY == fromY) 
                {
                    int minX = Math.min(fromX, toX);
                    int maxX = Math.max(fromX, toX);
                    for (int x = minX + 1; x < maxX; x++) 
                        if (chessTiles[x][toY].getIcon() != null) 
                            return false;
                } 
                else 
                {
                    
                    int xDir = (toX - fromX) > 0 ? 1 : -1;
                    int yDir = (toY - fromY) > 0 ? 1 : -1;
                    int i = fromX + xDir;
                    int j = fromY + yDir;
                    while (i != toX && j != toY) 
                    {
                        if (chessTiles[i][j].getIcon() != null) 
                            return false;
                        
                        i += xDir;
                        j += yDir;
                    }
                }
                if(chessTiles[toX][toY].getIcon() != null && Math.abs(toX - fromX) == Math.abs(toY - fromY))
                {
                    if(blackPiece(chessTiles[toX][toY].getIcon()) != blackPiece(chessTiles[fromX][fromY].getIcon()))
                    {
                        kill(fromX, fromY, toX, toY, chessTiles[fromX][fromY].getIcon());
                        turn_for_white = (Boolean) null;
                        return true;
                    }
                }
                if(chessTiles[toX][toY].getIcon() != null && toX == fromX || toY == fromY)
                {
                    if(blackPiece(chessTiles[toX][toY].getIcon()) != blackPiece(chessTiles[fromX][fromY].getIcon()))
                    {
                        kill(fromX, fromY, toX, toY, chessTiles[fromX][fromY].getIcon());
                        turn_for_white = (Boolean) null;
                        return true;
                    }
                }
                return true; 
            }
            return false; 
        }

        return false;
    }
    
    public static boolean blackPiece(Icon icon1)
    {
        String path1 = "";
        ImageIcon icon = (ImageIcon)icon1;

        if(icon != null)
            path1 = icon.toString();
        if (path1.contains("black")) 
            return true;
        else if(path1.contains("white"))  
            return false;
        
        return false;
    }

    public static void kill(int fromX, int fromY, int toX, int toY, Icon icon2)
    {
        String s = chessTiles[fromX][fromY].getToolTipText();
        chessTiles[fromX][fromY].setIcon(null);
        chessTiles[fromX][fromY].setToolTipText(null);

        chessTiles[toX][toY].setToolTipText(s);
        chessTiles[toX][toY].setIcon(icon2);
        
    }

    public static boolean isDiagonalMove(int fromX, int fromY, int toX, int toY) 
    {
        int dx = Math.abs(toX - fromX);
        int dy = Math.abs(toY - fromY);

        int xDir = (toX - fromX) > 0 ? 1 : -1;
        int yDir = (toY - fromY) > 0 ? 1 : -1;

        int i = fromX + xDir;
        int j = fromY + yDir;

        while (i != toX && j != toY) 
        {
            if (chessTiles[i][j].getIcon() != null) 
                return false;
            i += xDir;
            j += yDir;
        }

        return dx == dy;
    }

    public static boolean isStrightMove(int fromX, int fromY, int toX, int toY) 
    {
        if (toX == fromX) 
        {
            int minY = Math.min(fromY, toY);
            int maxY = Math.max(fromY, toY);
            for (int y = minY + 1; y < maxY; y++) 
                if (chessTiles[toX][y].getIcon() != null) 
                    return false;
            return true;
        }
        else if (toY == fromY) 
        {
            int minX = Math.min(fromX, toX);
            int maxX = Math.max(fromX, toX);
            for (int x = minX + 1; x < maxX; x++) 
                if (chessTiles[x][toY].getIcon() != null) 
                    return false;
            return true;
        } 
        return false;
    }

    public static boolean KingMove(int fromX, int fromY, int toX, int toY)
    {
        
        int dx = Math.abs(fromX - toX);
        int dy = Math.abs(fromY - toY);

        if(fromX == 0 && fromY == 4 && dy != 2) 
            king_castle++;

        if(fromX == 7 && fromY == 4 && dy != 2)
            King_castle++;

        if ((dx == 1 && dy == 0) || (dx == 0 && dy == 1) || (dx == 1 && dy == 1)) 
            return true;

        if(fromX == 0 && fromY == 4 && king_castle == 0 && !turn_for_white) // castling with respect to the black king  
        {
            if (toY - fromY == 2 && rook_castle1 == 0)//castling for the right black rook
            {
                turn_for_white =  !turn_for_white;

                position_change(fromX, fromY, toX, toY, bking);
                position_change(0, 7, 0, 5, brook);

                king_castle++;
                rook_castle1++;

                
                rook2.setxCell(0);
                rook2.setyCell(5);

                king.setxCell(toX);
                king.setyCell(toY);
                return true;
            }
            if (toY - fromY == -2 && rook_castle1 == 0)//castling for the top left rook
            {
                turn_for_white =  !turn_for_white;

                position_change(fromX, fromY, toX, toY, bking);
                position_change(0, 0, 0, 3, brook);

                king_castle++;
                rook_castle1++;

                rook1.setxCell(0);
                rook1.setyCell(3);

                king.setxCell(toX);
                king.setyCell(toY);
                return true;
            }
        }

        if(fromX == 7 && fromY == 4 && king_castle == 0 && turn_for_white) // castling with respect to the white king  
        {
            if (toY - fromY == 2 && Rook_castle1 == 0)//castling for the right white rook
            {
                turn_for_white =  !turn_for_white;

                position_change(fromX, fromY, toX, toY, wking);
                position_change(7, 7, 7, 5, wrook);

                King_castle++;
                Rook_castle1++;

                //Rook 2 default location 7, 7
                Rook2.setxCell(7);
                Rook2.setyCell(5);

                King.setxCell(toX);
                King.setyCell(toY);
                return true;
            }
            if (toY - fromY == -2 && Rook_castle2 == 0)//castling for the bottom left rook
            {
                turn_for_white =  !turn_for_white;

                position_change(fromX, fromY, toX, toY, wking);
                position_change(7, 0, 7, 3, wrook);

                King_castle++;
                Rook_castle2++;

                Rook1.setxCell(7);
                Rook1.setyCell(3);

                King.setxCell(toX);
                King.setyCell(toY);
                return true;
            }
        }
        return false;    
    }

    public static boolean KnightAttack(int fromX, int fromY, int toX, int toY) 
    {
        int rowDelta = Math.abs(toX - fromX);
        int colDelta = Math.abs(toY - fromY);

        if(rowDelta == 1 && colDelta == 2)
            return true;

        if(rowDelta == 2 && colDelta == 1)
            return true;
        return false;    
    }

    public static boolean PawnMovement(int fromX, int fromY, int toX, int toY, boolean color_black) 
    {
        int deltaX = toX - fromX;
        int deltaY = toY - fromY;

        if(!color_black && toX == 0) {System.out.println(543543);
            return true;
        }
        
        if(!color_black && (Math.abs(deltaX) == 1 || Math.abs(deltaX) == 2) && Math.abs(deltaY) == 0 && fromX == 6 && toX < fromX)
            return true;
        
        if(fromX == 1 && !blackPiece(chessTiles[fromX][fromY].getIcon()))// to check if a white piece has reached and going the last rank
            return true;

        if(fromX == 6 && blackPiece(chessTiles[fromX][fromY].getIcon()))// to check if a black piece has reached and going the first rank
            return true;

        

        if(color_black && (Math.abs(deltaX) == 1 || Math.abs(deltaX) == 2) && Math.abs(deltaY) == 0 && fromX == 1 && toX > fromX)
            return true;
        
        else if(color_black && deltaX == 1 && deltaY == 0)
            return true;

            return false;    
    }

    public static void position_change(int fromX, int fromY, int replaceX, int replaceY, ImageIcon icon1)
    {
        String s =  chessTiles[fromX][fromY].getToolTipText();

        chessTiles[fromX][fromY].setIcon(null);
        chessTiles[replaceX][replaceY].setIcon(icon1);   

        chessTiles[fromX][fromY].setToolTipText(null);
        chessTiles[replaceX][replaceY].setToolTipText(s);
    }
    
    public static void Update_King_Location(int fromX, int fromY, int toX, int toY, boolean black) 
    {
        System.out.println(king.getxCell()+" "+king.getyCell());
        if(fromX == king.getxCell() && fromY == king.getyCell())
        {
            bkingX = toX;
            bkingY = toY;
        }
            if(!black)
            {
                wkingX = toX;
                wkingY = toY;
            }    
    }
    public static boolean isCheckByKnight(int fromX, int fromY, int kingX, int kingY) 
    {
        int rowDelta = Math.abs(kingX - fromX);
        int colDelta = Math.abs(kingY - fromY);
    
        // Check if the move is a valid knight attack
        if ((rowDelta == 1 && colDelta == 2) || (rowDelta == 2 && colDelta == 1)) {
            // Check if the knight move attacks the opponent's king position
            if (fromX == kingX || fromY == kingY) {
                return true;
            }
        }
        return false;
    }    
}
