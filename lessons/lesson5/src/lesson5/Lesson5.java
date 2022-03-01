package lesson5;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lesson5 {

    public static void main(String[] args){
        File fileCSV = new File("1.csv" );
        String[] arrHeader = {"Value 1; ", "Value 2; ", "Value 3; "};
        int[][] arrData = {{100, 200, 123}, {300, 400, 500}};
        AppData dataWriter = new AppData(arrHeader, arrData);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileCSV))) {
            for (String str : dataWriter.getHeader()) {
                bufferedWriter.write(str);
            }
            for (int i = 0; i < dataWriter.getData().length; i++) {
                bufferedWriter.write("  \n");
                for (int j = 0; j < dataWriter.getData()[i].length; j++) {
                    String result = Integer.toString(arrData[i][j]);
                    bufferedWriter.write(result + ";");
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        AppData  dataReader= new AppData();
        List<List<String>> reading = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileCSV))) {
            String line = reader.readLine();
            dataReader.setHeader(line.split(" "));
            while ((line = reader.readLine()) != null) {
                    String[] values = line.replace(" ", "").split(";");
                reading.add(Arrays.asList(values));
            }
            int[][] valueData = new int[reading.size()][3];
            for (int i = 0; i < reading.size(); i++) {
                for (int j = 0; j < reading.get(i).size(); j++) {
                    valueData[i][j] = Integer.valueOf(reading.get(i).get(j));
                }
            }
            dataReader.setData(valueData);
            System.out.println(dataReader);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}











