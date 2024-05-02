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
        int[][] map = getMap("background/map");
        dungeon = new Tile[16][22];
        for (int r = 0; r < map.length; r++) {
            for (int c = 0; c < map[0].length; c++) {
                Tile t = new Tile(map[r][c]);
                dungeon[r][c] = t;
                //if dungeon[r][c] = null
                if (!dungeon[r][c].isPath()) {
                    dungeon[r][c].setTileType(2);
                    }
                }
        }
        highlightPath();
    }

    private int[][] getMap(String fileName) {
        File f = new File(fileName);
        Scanner a = null;
        try {
            a = new Scanner(f);
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(1);
        }

        ArrayList<String> fileData = new ArrayList<String>();
        while (a.hasNextLine())
            fileData.add(a.nextLine());

        int rows = fileData.size();
        int cols = fileData.get(0).length();

        int[][] worldData = new int[rows][cols];

        for (int i = 0; i < fileData.size(); i++) {
            String d = fileData.get(i);
            for (int j = 0; j < d.length(); j++) {
                if (d.charAt(j) == ',')
                    worldData[i][j] = 1;
                if (d.charAt(j) == '.')
                    worldData[i][j] = 0;
                if (d.charAt(j) == '/') {
                    worldData[i][j] = 2;
                }
                if (d.charAt(j) == '$') {
                    this.s = new Swordmaster(i, j);
                }
            }
        }
        return worldData;
    }

    private void highlightPath() {
        boolean check = true;
        while (check) {
            for (Tile[] tiles : dungeon) {
                for (int c = 0; c < dungeon[0].length; c++) {
                    if (tiles[c].isPath()) {
                        // Highlight the current tile
                        tiles[c].setPath();
                    }
                }
            }

            // Check if any more tiles need to be highlighted
            check = false;
            for (int r = 0; r < dungeon.length; r++) {
                for (int c = 0; c < dungeon[0].length; c++) {
                    if (dungeon[r][c].isPath()) {
                        // Check if any adjacent tiles are not part of the main path
                        if ((r > 0 && !dungeon[r - 1][c].isPath()) ||
                                (r < dungeon.length - 1 && !dungeon[r + 1][c].isPath()) ||
                                (c > 0 && !dungeon[r][c - 1].isPath()) ||
                                (c < dungeon[0].length - 1 && !dungeon[r][c + 1].isPath())) {
                            check = true;
                        }
                    }
                }
            }
        }
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
    }

    public void setGameEnded() {
        gameEnded = true;
    }

    public Tile[][] getTiles(){
        return dungeon;
    }

    public Swordmaster getS(){
        return s;
    }
}
