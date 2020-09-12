package com.blisskid.fb;
import java.io.*;
import java.util.*;

class Dino {
    private String name;
    private Double legLen;
    private String diet;
    private Double strideLen;
    private String stance;

    Dino () {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLegLen() {
        return legLen;
    }

    public void setLegLen(Double legLen) {
        this.legLen = legLen;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public Double getStrideLen() {
        return strideLen;
    }

    public void setStrideLen(Double strideLen) {
        this.strideLen = strideLen;
    }

    public String getStance() {
        return stance;
    }

    public void setStance(String stance) {
        this.stance = stance;
    }
}

public class Dinosaur {


    public static void main(String[] args) {
        Dinosaur d = new Dinosaur();
        d.readFile();
    }


    private List<Dino> readFile() {
        File f1 = new File("/Users/tongadele/Documents//GitHub/ctci/out/production/ctci/com/blisskid/fb/dataset1.csv");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f1)));
            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private List<Dino> readFileByLine() {
        List<Dino> result = new ArrayList();
        File f1 = new File("/Users/tongadele/Documents//GitHub/ctci/out/production/ctci/com/blisskid/fb/dataset1.csv");
        File f2 = new File("/Users/tongadele/Documents//GitHub/ctci/out/production/ctci/com/blisskid/fb/dataset2.csv");
        String line;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(f1)));
            line = reader.readLine();
            line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private void readFileByByte1() {
        File f1 = new File("/Users/tongadele/Documents//GitHub/ctci/out/production/ctci/com/blisskid/fb/dataset1.csv");
        try {
            FileInputStream in = new FileInputStream(f1);
            byte[] b = new byte[256];
            int r;
            while ((r = in.read()) != -1) {
                System.out.write(r);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFileByByte() {
        File f1 = new File("/Users/tongadele/Documents//GitHub/ctci/out/production/ctci/com/blisskid/fb/dataset1.csv");
        try {
            FileInputStream in = new FileInputStream(f1);
            //InputStreamReader reader = new InputStreamReader(in);
            byte[] b = new byte[256];
            int r;
            while ((r = in.read(b)) != -1) {
                System.out.write(b, 0, r);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFileByChar() {
        File f1 = new File("/Users/tongadele/Documents//GitHub/ctci/out/production/ctci/com/blisskid/fb/dataset1.csv");
        try {
            FileInputStream in = new FileInputStream(f1);
            InputStreamReader reader = new InputStreamReader(in);
            int r;
            while ((r = reader.read()) != -1) {
                System.out.println((char)r);
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
