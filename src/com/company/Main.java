package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
            System.out.println("Saved");
        }
    }

    public static void editBook() throws IOException {
        System.out.println("==== Edit a Book ====");
        List<String> arrayList =  new ArrayList<String>();
        while (fileScanner.hasNextLine())
        {
            String book = fileScanner.nextLine();
            arrayList.add(book);
            String bookData[] = book.split(",");
            System.out.println(" [" + bookData[0] + "] " + bookData[1]);

        }
        System.out.print("\n Book ID: ");
        int id = inputScanner.nextInt();
        String editableBook[] = arrayList.get(id-1).split(",");
        System.out.format("Input the following information. To leave a field unchanged, hit <Enter> \n Title [%s]: ",editableBook[1]);
        inputScanner.nextLine();
        String title = inputScanner.nextLine();
        if(title.equals("")) title = editableBook[1];
        System.out.format(" Author [%s]: ", editableBook[2]);
        String author = inputScanner.nextLine();
        if(author.equals("")) author = editableBook[2];
        System.out.format(" Description [%s]: ",editableBook[3]);
        String description = inputScanner.nextLine();
        if(description.equals("")) description = editableBook[3];

        String newBookAfterUpdate = String.format("%d,%s,%s,%s",id,title,author,description);
        arrayList.set(id-1,newBookAfterUpdate);
        System.out.println("==========================");
        System.out.println(arrayList);

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file.getPath()));
        try {
            for (int i = 0; i < arrayList.size(); i++)
                    bufferedWriter.write(arrayList.get(i)+"\n");
        }finally {
            bufferedWriter.close();
            System.out.println("Saved");
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
        editBook();

    }
}
