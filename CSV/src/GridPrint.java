import java.awt.*;

class GridPrint {
    String[][] content;
    String[] column;
    String[] row;
    Point select = new Point(0,0);
    Point max;
    Point[] cells;
    private Color color = new Color();

    GridPrint(int xSize, int ySize) {
        content = new String[xSize][ySize];
        column = new String[xSize];
        row = new String[ySize];
        cells = new Point[xSize * ySize];
        max = new Point(xSize,ySize);
        int i = 0;
        for(int x = 0; x < xSize; x++) for(int y = 0; y < ySize; y++) {
            cells[i] = new Point(x,y);
            i++;
        }
        for(i = 0; i < column.length; i++) column[i] = "" + (char) (i + 65);
        for(i = 1; i <= row.length; i++) row[i - 1] = "" + i;
    }

    void Generate() {
        int[] columnMax = new int[column.length];
        int rowMax = 0;
        for(int x = 0; x < column.length; x++) {
            columnMax[x] = color.length(column[x]);
            for(int y = 0; y < row.length; y++) if(columnMax[x] < color.length(content[x][y])) columnMax[x] = color.length(content[x][y]);
        }
        for(String s : row) if(rowMax < color.length(s)) rowMax = color.length(s);

        System.out.print(color.Green + "#" + space(rowMax));
        for(int x = 0; x < max.x; x++) System.out.print(column[x] + " " + space(columnMax[x] - color.length(column[x])));

        for(int y = 0; y < max.y; y++) {
            System.out.println();
            System.out.print(color.Green(row[y]) + space(rowMax - color.length(row[y])));
            for(int x = 0; x < max.x; x++) {
                if(y == select.y && x == select.x) System.out.print("[");
                else if(y == select.y && x > 0 && x == select.x + 1) System.out.print("]");
                else System.out.print(" ");
                System.out.print(content[x][y] + space(columnMax[x] - color.length(content[x][y])));
            }
            if(y == select.y && max.y - 1 == select.x) System.out.print("]");
            else System.out.print(" ");
            System.out.print(color.Green(row[y]));
        }
        System.out.println();
        System.out.print(color.Green + " " + space(rowMax));
        for(int x = 0; x < max.x; x++) System.out.print(column[x] + " " + space(columnMax[x] - color.length(column[x])));
        System.out.println(color.Clear);
    }

    boolean Select(String cell) {
        cell = cell.toLowerCase();
        select.y = 0;
        select.x = 0;
        boolean moving = true;
        if(cell.split(" ").length < 2) return false;
        while(select.x < column.length && moving) {
            if(!column[select.x].toLowerCase().contains(cell.split(" ")[0])) select.x++;
            else moving = false;
        }
        if(moving) return false;
        moving = true;
        while(select.y < row.length && moving) {
            if(!row[select.y].toLowerCase().contains(cell.split(" ")[1])) select.y++;
            else moving = false;
        }
        return !moving;
    }

    boolean contains(int x, int y) { return x >= 0 && x < column.length && y >= 0 && y < row.length;}

    private String space(int multiplier) {
        String s = "";
        for(int i = 0; i < multiplier; i++) s += " ";
        return s;
    }
}
