package FHP;

import Cells.Hexagon;

import java.io.*;

public class FileGenerator {
    private FileWriter fw1;
    private FileWriter fw2;
    private final BufferedWriter buffer1;
    private final BufferedWriter buffer2;

    public FileGenerator(){
        try{
            FileWriter pw1 = new FileWriter("OutputPosition.xyz");
            FileWriter pw2 = new FileWriter("OutputTime.csv");
            pw1.close();
            pw2.close();
            this.fw1 = new FileWriter("OutputPosition.xyz", true);
            this.fw2 = new FileWriter("OutputTime.csv", true);
        }catch (IOException e){
            e.printStackTrace();
        }

        this.buffer1 = new BufferedWriter(fw1);
        this.buffer2 = new BufferedWriter(fw2);

        try{
            buffer2.write("t, pL, pR\n");
        }catch (IOException e){
            e.printStackTrace();
        }
        writeWall();
    }

    private void writeWall(){
        double distanceX = 0.5;
        double distanceY = (Math.sqrt(3)/2)/2;
        try{
            FileWriter fw = new FileWriter("walls.xyz");
            fw.close();
            fw = new FileWriter("walls.xyz", true);
            BufferedWriter buffer = new BufferedWriter(fw);
            buffer.write("1921\n");
            buffer.write("wall: x y\n");
            for(int i=0; i<404; i++){
                buffer.write((distanceX*0) + " " + (distanceY*i) + "\n");
                buffer.write((distanceX+distanceX*406) + " " + (distanceY*i) + "\n");
                if(i<=138 || i>=242){
                    buffer.write((distanceX*202) + " " + (distanceY*i) + "\n");
                }
            }
            for(int j=0; j<406; j++){
                buffer.write((distanceX*j) + " " + (distanceY*0) + "\n");
                buffer.write((distanceX+distanceX*j) + " " + (distanceY*404) + "\n");
            }
            buffer.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void writeXYZ(Hexagon[][] cells, int particlesQuantity){
        double x, y;
        double distanceY = (Math.sqrt(3)/2);
        double color;
        try{
            buffer1.write(particlesQuantity + "\n");
            buffer1.write("x y color\n");
            for(int i=0; i<202; i++){
                for(int j=0; j<203; j++){
                    if(!cells[i][j].getProperties().get("S")){        //deberia ser si es o no pared
                        if(j<101)
                            color = 1;
                        else
                            color  = 0.5;
                        y = i *distanceY;
                        if(j%2 == 0)
                            x = j;
                        else
                            x = j + 0.5;
                        if(cells[i][j].getProperties().get("A")){
                            buffer1.write(x + " " + y + " " + color + "\n");
                        }
                        if(cells[i][j].getProperties().get("B")){
                            buffer1.write(x + " " + y + " " + color + "\n");
                        }
                        if(cells[i][j].getProperties().get("C")){
                            buffer1.write(x + " " + y + " " + color + "\n");
                        }
                        if(cells[i][j].getProperties().get("D")){
                            buffer1.write(x + " " + y + " " + color + "\n");
                        }
                        if(cells[i][j].getProperties().get("E")){
                            buffer1.write(x + " " + y + " " + color + "\n");
                        }
                        if(cells[i][j].getProperties().get("F")){
                            buffer1.write(x + " " + y + " " + color + "\n");
                        }
                    }
                }
            }

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void writeCSV(long time, int particlesQuantity, int leftParticles, int rightParticles){
        try {
            buffer2.write(time + "," + ((double) leftParticles / (double) particlesQuantity) + "," + (((double) rightParticles / (double) particlesQuantity) + "\n"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void closeFiles(){
        try {
            buffer1.close();
            buffer2.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}

