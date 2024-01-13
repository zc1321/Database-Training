import java.util.*;

/**
 * 迷宫问题(java)递归
 * 机器人方向为:后->右->上->左
 * @author zc
 * @// TODO: 2023/6/24
 * @version 1.0
 */
public class Maze {
    private static int x=0;
    private static int y=0;
    private static int x1=0;
    private static  int y1=0;
    private static int number=0;
    private static int z=0;
    private static int c=0;
    private static String choice;
    private static  String []zc;
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        //a数组为核心
        int[][] a=new int[8][8];
        //b数组主要为渲染
        String [][]b=new String[8][8];
        for(int i=0;i<8;i++) {
            a[0][i] =1;
            a[7][i] = 1;
            b[0][i] = "围墙";
            b[7][i] = "围墙";
        }
        for(int i=0;i<8;i++){
            a[i][0]= 1;
            a[i][7]= 1;
            b[i][0]= "围墙";
            b[i][7]= "围墙";
        }
        System.out.println("-----------------Maze figure-------------------");
        for(int i=0;i<8;i++) {
            for (int j = 0; j < 8; j++) {
                if (b[i][j] == null) {
                    b[i][j] = "空地";
                }
                System.out.print(b[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println("------------------------------------------");
        System.out.println("Number of input trap:");//小概率事件
         number=in.nextInt();
         if(number>25){
             System.out.println("No more than 25 trap");
             return;
         }
         zc=new String[number];
       for(int i=0;i<number;i++){
             z=(int)(Math.random()*6+1);
             c= (int)(Math.random()*6+1);
            //判断是否重复
           //if(o==1||o==2||o==3||o==4){
               choice=z+","+c;
               zc[i]=choice;
               //System.out.println(zc[i]);
         //  }
             //  a[z][c]=1;
              // b[z][c]="陷阱";
       }
        Set<String>sameSet=new HashSet<>();
        for(String element:zc) {
            sameSet.add(element);
        }

        if(sameSet.size()==zc.length) {
           // System.out.println(".......................................");
            int o=0;
            for (int i = 0; i < number; i++) {
                String[] split = zc[i].split(",");
                for (String s : split) {
                    o++;
                    if(o%2==0){
                        c = Integer.parseInt(s);
                        //System.out.println("c的值"+c);
                        a[z][c]=1;
                        b[z][c]="陷阱";
                    }
                    else{
                        z = Integer.parseInt(s);
                      //  System.out.println("z的值"+z);
                    }
                }
            }
        }

        else{
            System.out.println("Warning: trap number may do not agree with set!!!!!");
            a[3][2]= 1;
            a[3][1]= 1;
            b[3][2]="陷阱";
            b[3][1]="陷阱";
        }
        System.out.println("----------------To create a maze figure-------------------");
        for(int i=0;i<8;i++) {
            for (int j = 0; j < 8; j++) {
                if (b[i][j] == null) {
                    b[i][j] = "空地";
                }
                System.out.print(b[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println("------------------------------------------");
        //a[3][2]= 1;
       // a[3][1]= 1;
        //b[3][2]="陷阱";
       // b[3][1]="陷阱";
        //a[2][1]=1;
        //a[1][2]=1;
        System.out.println("Enter the starting point of x, y values (separated by Spaces):");
         x=in.nextInt();
        //System.out.println("输入起点的y值:");
         y=in.nextInt();
        System.out.println("Input end of x and y values (separated by Spaces):");
        x1=in.nextInt();
        //System.out.println("输入终点的y值:");
        y1=in.nextInt();
        if(x>=0&&x<=7&&y>=0&&y<=7&&x1>=0&&x1<=7&&y1>=0&&y1<=7){
            b[x][y]="起点";
            b[x1][y1]="终点";
        }
        else{
            System.out.println("Up to 0 ~ 7");
            return;
        }
        System.out.println("----------------To create a maze figure-------------------");
        for(int i=0;i<8;i++) {
            for (int j = 0; j < 8; j++) {
                if (b[i][j] == null) {
                    b[i][j] = "空地";
                }
                System.out.print(b[i][j] + "  ");
            }
            System.out.println();
        }
        boolean run = run(a,b ,x, y);
        System.out.println("------------------------------------------");
        System.out.println("Start the robot? (y/n)");
        String tip=in.next();
        if(tip.equals("n")){
            return;
        }
        if(run) {
            System.out.println("-----------------The robot figure-------------------");
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    System.out.print(b[i][j] + "  ");
                }
                System.out.println();
            }
            System.out.println("------------------------------------------");
        }
       else if(!run){
            System.out.println("Robots have downtime!!!!!");
        }
    }
    public static boolean run(int[][] a, String[][]b,int i, int j){
        if(a[x1][y1]==2){
            b[x1][y1]="终点";
            return true;
        }
        if(a[i][j]==0){
           b[i][j]= "行走";
           b[x][y]="起点";
           a[i][j]=2;
           if(run(a,b,i+1,j)||run(a,b,i,j+1)||run(a,b,i-1,j)||run(a,b,i,j-1)){
                return true;
           }
           else{
              a[i][j]= 0;
              return false;
          }
        }
        else {
            return false;
        }
    }
}
