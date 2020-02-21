package com.company;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner inputScanner = new Scanner(System.in);
//    URL main = Main.class.getResource("Main.class");
    //System.out.println(main.getPath());
    static File file = new File("src/com/company/data.txt");
    static Scanner fileScanner;

    static {
        try {
            fileScanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void startUI() throws FileNotFoundException {
         System.out.print("==== Book Manager ====\n 1) View all books\n 2) Add a book\n 3) Edit a book\n 4) Search for a book\n 5) Save and exit\n Choose [1-5]: ");

     }
    public static void addBook() throws IOException {
        System.out.print("==== Add a Book ====\nPlease enter the following information:\n Title: ");
        inputScanner.nextLine();
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
            System.out.format("Book [%s] Saved\n",count+1);
        }
    }

    public static void editBook() throws IOException {

        System.out.println("==== Edit a Book ====");
        List<String> arrayList =  new ArrayList<String>();
        inputScanner.nextLine();
        while (fileScanner.hasNextLine())
        {
            String book = fileScanner.nextLine();
            arrayList.add(book);
            String bookData[] = book.split(",");
            System.out.println(" [" + bookData[0] + "] " + bookData[1]);

        }
        scopeOfRepeating: while(true){ System.out.print("Enter the book ID of the book you want to edit; to return press <Enter>.\n");
        System.out.print(" Book ID: ");
        String id = inputScanner.nextLine();
        if(id.equals("")) break scopeOfRepeating;
        String editableBook[] = arrayList.get(Integer.parseInt(id)-1).split(",");
        System.out.format("Input the following information. To leave a field unchanged, hit <Enter> \n Title [%s]: ",editableBook[1]);

        String title = inputScanner.nextLine();
        if(title.equals("")) title = editableBook[1];
        System.out.format(" Author [%s]: ", editableBook[2]);
        String author = inputScanner.nextLine();
        if(author.equals("")) author = editableBook[2];
        System.out.format(" Description [%s]: ",editableBook[3]);
        String description = inputScanner.nextLine();
        if(description.equals("")) description = editableBook[3];

        String newBookAfterUpdate = String.format("%s,%s,%s,%s",id,title,author,description);
        arrayList.set(Integer.parseInt(id)-1,newBookAfterUpdate);

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file.getPath()));
        try {
            for (int i = 0; i < arrayList.size(); i++)
                    bufferedWriter.write(arrayList.get(i)+"\n");
        }finally {
            bufferedWriter.close();
            System.out.println("Book Edited & Saved");

        }
}





    }
    public static void searchForBook(boolean firstTime) throws FileNotFoundException {
           System.out.print("To view details enter the book ID, to return press <Enter>.\n Book ID: ");
           if(firstTime)
           inputScanner.nextLine();
           String id = inputScanner.nextLine();
           if(id.equals("")) return;
            fileScanner = new Scanner(file);
        while (fileScanner.hasNextLine())
        {
            String bookData[] = fileScanner.nextLine().split(",");
            if(bookData[0].equals(id) ){
                System.out.format(" ID: %s\n Title: %s\n Author: %s\n Description: %s\n\n",bookData[0],bookData[1],bookData[2],bookData[3]);

                break;
            }

        }

       searchForBook(false);

    }


     public static void viewAllBooks() throws FileNotFoundException {


         System.out.println("==== View Books ====");
         fileScanner = new Scanner(file);
         while (fileScanner.hasNextLine())
             {
                 String bookData[] = fileScanner.nextLine().split(",");
                 System.out.println("[" + bookData[0] + "] " + bookData[1]);

             }

     }

    public static void main(String[] args) throws IOException {
        int operation;

        while (true){
            startUI();
            operation = inputScanner.nextInt();
            if(operation == 1){
                viewAllBooks();
                searchForBook(true);
            }else if(operation == 2){
                addBook();
            }else if (operation == 3){
                editBook();
            }else if( operation == 4){
                searchForBook(true);
            }else
            {
                System.out.println("Library saved."); // we already saving in every step
                return;
            }
        }

    }
}
