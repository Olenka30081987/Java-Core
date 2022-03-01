package lesson5;

public class AppData {
    private String[] header;
    private int[][] data;

    public AppData(String[] header, int[][] data) {
        this.header = header;
        this.data = data;
    }

    public AppData() {

    }

    public String[] getHeader() {
        return header;
    }

    public int[][] getData() {
        return data;
    }

    public void setHeader(String[] header) {
        this.header = header;
    }

    public void setData(int[][] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        String result = "";
        for (String str : header) {
            result = result + str;
        }
        result = result + "\n";
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
               result = result + data[i][j] + ";";
            }
            result = result + "\n";
        }
        return result;
    }
}



