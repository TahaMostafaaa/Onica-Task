package com.company;

import java.io.*;
import java.util.Scanner;

public class Main {

    static Scanner inputScanner = new Scanner(System.in);
    static File file = new File("C:/Users/vdcg9145/Desktop/Play Ground/Onica-Task/src/com/company/data.txt");
    static Scanner fileScanner;

    static {
        try {
            fileScanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Main() throws IOException {
    }

    public static void startUI() throws FileNotFoundException {
         System.out.println("==== Book Manager ====\n 1) View all books\n 2) Add a book\n 3) Edit a book\n 4) Search for a book\n 5) Save and exit\n Choose [1-5]: ");
         if(inputScanner.nextInt() == 1){
             viewAllBooks();
         }
     }
    public static void addBook() throws IOException {
        System.out.print("==== Add a Book ====\nPlease enter the following information:\n Title: ");
        String title = inputScanner.nextLine();
        System.out.print(" Author: ");
        String author = inputScanner.nextLine();
        System.out.print(" Description: ");
        String description = inputScanner.nextLine();
        int count = 0;
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file.getPath(), true));
        try {
        for(String line = ""; fileScanner.hasNext(); line = fileScanner.nextLine())
           count++;
        bufferedWriter.append("\n" +(count+1)+ "," + title +"," + author + "," + description);
        }finally {
            bufferedWriter.close();
            System.out.println("Done");
        }


    }
     public static void viewAllBooks() throws FileNotFoundException {

//         URL main = Main.class.getResource("Main.class");
//         System.out.println(main.getPath());
         System.out.println("==== View Books ====");
         while (fileScanner.hasNextLine())
             {
                 String bookData[] = fileScanner.nextLine().split(",");
                 System.out.println("[" + bookData[0] + "] " + bookData[1]);

             }
     }

    public static void main(String[] args) throws IOException {
        addBook();

    }
}
