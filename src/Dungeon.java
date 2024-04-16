import World.Tile;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
public class Dungeon {
    private Tile[][] dungeon;
    private Swordmaster s;
    private boolean gameEnded;
    public Dungeon(){
        createDungeon();
        gameEnded = false;
    }
    public void createDungeon(){
        int[][]map = getWorld("background/map");
        dungeon = new Tile[14][22];
        for (int r = 0; r < dungeon.length; r++) {
            for (int c = 0; c < dungeon[0].length; c++) {
                Tile t = new Tile(map[r][c]);
                dungeon[r][c] = t;
            }
        }
        setVisibility();
    }
    public void move(String direction){
        int currentRow = s.getRow();
        int currentCol = s.getCol();
        //going up
        if(direction.equalsIgnoreCase("W")){
            if(currentRow > 0){
                s.setRow(currentRow - 1);
            }
        }
        //going down
        if(direction.equalsIgnoreCase("S")){
            if(currentRow < dungeon.length){
                s.setRow(currentRow + 1);
            }
        }
        //going left
        if(direction.equalsIgnoreCase("A")){
            if(currentCol > 0){
                s.setRow(currentCol - 1);
            }
        }
        //going right
        if(direction.equalsIgnoreCase("D")){
            if(currentCol < dungeon[0].length){
                s.setRow(currentCol + 1);
            }
        }
        setVisibility();
    }

    private int[][] getWorld(String fileName) {
        File f = new File(fileName);
        Scanner s = null;
        try {
            s = new Scanner(f);
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(1);
        }

        ArrayList<String> fileData = new ArrayList<String>();
        while (s.hasNextLine())
            fileData.add(s.nextLine());

        int rows = fileData.size();
        int cols = fileData.get(0).length();

        int[][] worldData = new int[rows][cols];

        for (int i = 0; i < fileData.size(); i++) {
            String d = fileData.get(i);
            for (int j = 0; j < d.length(); j++) {
                if (d.charAt(j) == 'S') {
                    this.s = new Swordmaster(i, j);
                }
            }
        }
        setVisibility();
        return worldData;
    }
    private void setVisibility() {
        int playerRow = s.getRow();
        int playerColumn = s.getCol();

        int topLeftRow = playerRow - 2;
        int topLeftColumn = playerColumn - 2;

        for (int i = topLeftRow; i <= topLeftRow+4; i++) {
            for (int j = topLeftColumn; j <= topLeftColumn+4; j++) {
                try {
                    dungeon[i][j].setVisible();
                }
                catch (ArrayIndexOutOfBoundsException e) { }
            }
        }
    }
    public Tile[][] getTile(){
        return dungeon;
    }

    public Swordmaster getS(){
        return s;
    }
}
