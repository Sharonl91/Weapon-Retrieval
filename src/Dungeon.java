import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
public class Dungeon {
    private Tile[][] dungeon;
    private Swordmaster s;
    private Monster mon;
    private ArrayList<Monster> m;
    private boolean gameEnded;

    public Dungeon(String file){
        m = new ArrayList<>();
        int[][] map = getMap(file);
        dungeon = new Tile[map.length][map[0].length];
        for (int r = 0; r < map.length; r++) {
            for (int c = 0; c < map[0].length; c++) {
                Tile t = new Tile(map[r][c]);
                dungeon[r][c] = t;
                if (dungeon[r][c] == null){
                    dungeon[r][c].setTileType(0);
                    dungeon[r][c].setPath();
                }
            }
        }
        gameEnded = false;
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

        ArrayList<String> fileData = new ArrayList<>();
        while (a.hasNextLine())
            fileData.add(a.nextLine());

        int rows = fileData.size();
        int cols = fileData.get(0).length();

        int[][] worldData = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (fileData.get(i).charAt(j) == ',') {
                    worldData[i][j] = 1;
                }
                else if (fileData.get(i).charAt(j) == '.'){
                    worldData[i][j] = 0;
                }
                else if (fileData.get(i).charAt(j) == '/') {
                    worldData[i][j] = 2;
                }
                else if (fileData.get(i).charAt(j) == '$') {
                    s = new Swordmaster(i, j);
                }
                else if (fileData.get(i).charAt(j) == '%') {
                    mon = new Monster(i,j);
                    m.add(mon);
                }
            }
        }
        return worldData;
    }

    public void move(String direction){
        int currentRow = s.getRow();
        int currentCol = s.getCol();
        //going up
        if(direction.equalsIgnoreCase("W")){
            if(currentRow > 0 && dungeon[currentRow - 1][currentCol].getTileType() == 0){
                s.setRow(currentRow - 1);

            }
        }
        //going down
        if(direction.equalsIgnoreCase("S")){
            if(currentRow < dungeon.length && dungeon[currentRow + 1][currentCol].getTileType() == 0){
                s.setRow(currentRow + 1);
            }
        }
        //going left
        if(direction.equalsIgnoreCase("A")){
            if(currentCol > 0 && dungeon[currentRow][currentCol - 1].getTileType() == 0){
                s.setCol(currentCol - 1);
            }
        }
        //going right
        if(direction.equalsIgnoreCase("D")){
            if(currentCol < dungeon[0].length && dungeon[currentRow][currentCol + 1].getTileType() == 0){
                s.setCol(currentCol + 1);
            }
        }
    }

    public void setGameEnded() {
        gameEnded = true;
    }

    public boolean isGameEnded() {
        return gameEnded;
    }

    public Tile[][] getTiles(){
        return dungeon;
    }

    public Swordmaster getS(){
        return s;
    }

    public ArrayList<Monster> getM() {
        return m;
    }
}
