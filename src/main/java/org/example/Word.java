package org.example;

import java.io.*;
import java.util.Scanner;

public class Word {
    public static int wordLine(String path)
    {
        int lineCount=1;
        try(Scanner sc = new Scanner(new File(path)))
        {
            while(sc.hasNextLine())
            {
                lineCount++;
                sc.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (int) (Math.random()*lineCount);
    }

    public static String hiddenWord(int line, String path)
    {
        String hiddenWord = null;
        try(FileReader fileReader = new FileReader(path))
        {
            int lineCount=1;
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String reader = bufferedReader.readLine();
            while(reader != null)
            {
                if(lineCount == line)
                {
                    hiddenWord = reader;
                    break;
                }
                lineCount++;
                reader = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return hiddenWord;
    }
}
