package model;

public class Neighborhood {
    
    // Attributes.
    
    private boolean[][] neighbors;
    private int[] origin = new int[2];
    private int[] size = new int[2];
    private int count;
    
    // Methods.
    
    public Neighborhood() {
        this.origin[0] = 1;
        this.origin[1] = 1;
        this.size[0] = 3;
        this.size[1] = 3;
        this.neighbors = new boolean[3][3];
        this.clearNeighbors();
        
        this.neighbors[1][0] = true;
        this.neighbors[1][2] = true;
        this.neighbors[0][1] = true;
        this.neighbors[2][1] = true;
        
        this.count = 4;
    }
    
    public Neighborhood(boolean[][] selected_cells, int[] origin) {
        this.size[0] = selected_cells[0].length;
        this.size[1] = selected_cells.length;
        this.neighbors = selected_cells.clone();
        this.origin = origin;
        
        this.count = 0;
        for(boolean[] row : selected_cells) {
            for(boolean cell : row) {
                if(cell) this.count++;
            }
        }
    }
    
    public int[][] getNeighbors() {
        int[][] deltas = new int[2][count];
        int local_count = 0;
        for(int i = 0; i < this.size[0]; i++) {
            for(int j = 0; j < this.size[1]; j++) {
                if(this.neighbors[j][i]) {
                    // WIP
                    //deltas[local_count][0] = i - this.origin[0];
                    //deltas[local_count][1] = j - this.origin[1];
                    local_count++;
                }
            }
        }
        return deltas;
    }
    
    public void invertNeighborValue(int x, int y) {
        this.neighbors[x][y] = !this.neighbors[x][y];
    }
    
    public void clearNeighbors() {
        for(int i = 0; i < this.size[0]; i++) {
            for(int j = 0; j < this.size[1]; j++) {
                this.neighbors[j][i] = false;
            }
        }
    }
}
