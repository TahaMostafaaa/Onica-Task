package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.Scanner;

public class Main {

     public static void startUI(){
         System.out.println("==== Book Manager ====\n 1) View all books\n 2) Add a book\n 3) Edit a book\n 4) Search for a book\n 5) Save and exit\n Choose [1-5]: ");

     }
     public static void viewAllBooks() throws FileNotFoundException {

//         URL main = Main.class.getResource("Main.class");
//         System.out.println(main.getPath());
         File file = new File("C:/Users/vdcg9145/Desktop/Play Ground/Onica-Task/out/production/Onica-Task/com/company/data.txt");

         Scanner sc = new Scanner(file);
         while (sc.hasNextLine())
             {
                 System.out.println(sc.nextLine());
             }
     }

    public static void main(String[] args) throws FileNotFoundException {
        viewAllBooks();

    }
}
