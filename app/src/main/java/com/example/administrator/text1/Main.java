package com.example.administrator.text1;

/**
 * Created by Vzc on 2017/3/18.
 */

import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.hasNext();
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            int total=0;
            int m = scanner.nextInt();
            int[] a = new int[m];
            for (int j = 0; j < m; j++) {
                a[i] = scanner.nextInt();
                total+=a[i];
            }
            int d = test(a,m);
            System.out.println("Case #"+(i+1)+":"+d+" "+(total-d));
        }

    }

    private static int test(int[] a, int m) {
        int sc1=0;
        boolean flag = true;
        int x =0,y=m-1;
        for(int i =0;i<m/2;i++){
            if(a[x]>a[y]){
                if(flag) {
                    sc1+=a[x];
                    flag=false;
                }else flag = true;
                x++;
            }else {
                if(flag) {
                    sc1+=a[y];
                    flag=false;
                }else flag = true;
                y--;
            }
        }
        return sc1;
    }
}