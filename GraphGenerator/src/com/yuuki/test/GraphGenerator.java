package com.yuuki.test;

import javax.swing.*;
import java.awt.*;

public class GraphGenerator extends JFrame {

    int max_num;
    double[] degrees = new double[100];
    int[] percentage = new int[100];
    String[] name = new String[100];
    String input;
    String[] temp;
    Color[] color = new Color[100];
    int pie_x = 250;
    int pie_y = 100;
    int pie_d = 500;
    double dst1 = 150;
    double dst2 = 300;

    public GraphGenerator(){
        input = JOptionPane.showInputDialog("Please input your data here. \nFormat: Name 1/Percentage 1/Name 2/Percentage 2... \n\"/\" should separate the data and stuff. ");
        JFrame frame = new JFrame();
        frame.setSize(1000, 800);
        frame.setTitle("Pie Chart Generator");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setResizable(false);
        MyJPanel myJPanel = new MyJPanel();
        frame.add(myJPanel);
    }

    public static void main(String[] args) {
	new GraphGenerator();
    }

    public class MyJPanel extends JPanel{
        MyJPanel(){
            try {
                temp = input.split("/");
            }catch (Exception e){
                System.exit(0);
            }
            max_num = temp.length / 2;
            try{
                for(int i = 0; i < temp.length / 2; i++){
                    name[i] = temp[i * 2];
                    percentage[i] = Integer.parseInt(temp[i * 2 + 1]);
                    degrees[i] = percentage[i] * 3.6;
                }
                System.out.println(degrees[0] + " " + name[0]);
                System.out.println(degrees[1] + " " + name[1]);
            }catch (Exception e){
                JOptionPane.showMessageDialog(null, "Input error. Please check the inputted data, and especially make sure no spaces are present. ");
                System.exit(0);
            }
            int total = 0;
            for (int i = 0; i < max_num; i++){
                total += percentage[i];
                if(percentage[i] <= 0){
                    JOptionPane.showMessageDialog(null, "Percentage error. Please do not input 0 or negative values. ");
                    System.exit(0);
                }
            }
            if(total > 100){
                JOptionPane.showMessageDialog(null, "Percentage error. Please be aware that these are PERCENTAGES, not DEGREES. ");
                System.exit(0);
            }
            for(int i = 0; i < max_num; i++){
                color[i] = new Color((int) (Math.random() * 155 + 100), (int) (Math.random() * 155 + 100), (int) (Math.random() * 155 + 100));
            }
            repaint();
        }
        public void paintComponent(Graphics g){
            super.paintComponents(g);
            double angle = 0;
            for(int i = 0; i < max_num; i++) {
                g.setColor(color[i]);
                g.setFont(new Font("DengXian", Font.PLAIN, 18));
                g.fillArc(pie_x, pie_y, pie_d, pie_d, (int) angle, (int) degrees[i]);
                g.setColor(Color.BLACK);
                g.drawString((int) percentage[i] + "%", (int) (pie_x + pie_d / 2 + dst1 * Math.cos((angle + degrees[i] / 2) * Math.PI / 180) - 10), (int) (pie_y + pie_d / 2 - dst1 * Math.sin((angle + degrees[i] / 2) * Math.PI / 180) + 10));
                g.drawString(name[i], (int) (pie_x + pie_d / 2 + dst2 * Math.cos((angle + degrees[i] / 2) * Math.PI / 180) - 30), (int) (pie_y + pie_d / 2 - dst2 * Math.sin((angle + degrees[i] / 2) * Math.PI / 180) + 10));
                System.out.println(degrees[i]);
                angle += degrees[i];
            }
        }
    }
}