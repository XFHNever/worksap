package jp.co.worksap.global.orienteering;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

/**
 * Created by never on 2014/9/25.
 */
public class Orienteering {
    private char[][] data;
    private int width;
    private int height;
    private int count;
    private int[][] distance;
    private ArrayList<Integer>[] sArray;
    private TreeMap<Integer, int[]> A1, A2;


    public static void main(String[] args) {
        Orienteering orienteering = new Orienteering();
        orienteering.readData();
        orienteering.initDistance();
        System.out.println("min: " + orienteering.solve());
    }

    public int solve() {
        sArray = new ArrayList[count + 1];

        for (int i = 0; i < count + 1; i++) {
            sArray[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i <= Math.pow(2, count) - 1; i++) {
            sArray[getOneCount(i)].add(i);
        }

        A1 = new TreeMap<Integer, int[]>();
        A2 = new TreeMap<Integer, int[]>();

        for (int s: sArray[1]) {
            int[] J = new int[count];
            int[] pos = getOnePosition(s, 1);
            int length = pos.length;
            for (int i = 0; i < length; i++) {
                int j = pos[i];
                J[j] = distance[count][j];
            }
            A1.put(s, J);

        }


        for (int m = 2; m <= count; m++) {
            A2.clear();
            for (int s : sArray[m]) {
                int[] tem = new int[count];
                int[] pos = getOnePosition(s, m);
                int length = pos.length;
                for (int i = 0; i < length; i++) {
                    int j = pos[i];
                    tem[j] = Integer.MAX_VALUE;
                    int smj = s&(~(j+1));
                    int[] K = A1.get(smj);
                    int kLenght = K.length;
                    for (int k = 0; k < kLenght; k++) {
                        if (K[k] > 0 && (K[k] + distance[k][j] < tem[j])) {
                            tem[j] = K[k] + distance[k][j];
                        }
                    }
                }
                A2.put(s, tem);
            }
            A1.clear();
            Iterator iterator = A2.keySet().iterator();
            while (iterator.hasNext()) {
                int key = Integer.parseInt(iterator.next().toString());
                A1.put(key, A2.get(key));
            }
        }

        int min = Integer.MAX_VALUE;
        int s = (int) (Math.pow(2, count) - 1);
        int[] K = A1.get(s);
        int kLenght = K.length;
        for (int k = 0; k < kLenght; k++) {
            if (K[k] > 0 && (K[k] + distance[k][count+1] < min)) {
                min = K[k] + distance[k][count+1];
            }
        }

        return min;
    }

    private int[] getOnePosition(int s, int number) {
        int[] result = new int[number];
        int i = 0, j = 0;
        while (s > 0) {
            if (s % 2 == 1) {
                result[i++] = j;
            }
            s = s/2;
            j++;
        }
        return  result;
    }

    public void initDistance() {
        distance = new int[count + 2][count+2];



        //TODO
        distance[0][0] = distance[1][1] = distance[2][2] = distance[3][3] = 0;
        distance[0][1] = distance[1][0] = 1;
        distance[0][2] = distance[2][0] = 2;
        distance[0][3] = distance[3][0] = 5;
        distance[1][3] = distance[3][1] = 6;
        distance[1][2] = distance[2][1] = 3;
        distance[2][3] = distance[3][2] = 3;
    }

    public void readData() {
        FileReader fr = null;
        BufferedReader br = null;
        int i = 0,j = 0;

        try {
            fr = new FileReader("C:\\Users\\fuxie\\worksap\\src\\main\\resources\\data.txt");
            br = new BufferedReader(fr);

            String line="";
            String[] arrs=null;

            if ((line=br.readLine())!=null) {
                arrs = line.split(" ");
                width = Integer.parseInt(arrs[0]);
                height = Integer.parseInt(arrs[0]);
            } else {
                throw new IOException("");
            }

            data = new char[width][height];

            while ((line=br.readLine())!=null) {
                for (j = 0; j < width; j++) {
                    data[i][j] = line.charAt(j);
                    if (data[i][j] == '@') {
                        count++;
                    }
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //close BufferedReader & FileReader
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private int dist() {
        return 0;
    }

    public int getOneCount(int number) {
        int count = 0;
        while (number != 0) {
            number &= (number - 1);
            count++;
        }
        return count;
    }
}
