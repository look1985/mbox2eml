package MboxToEml.MboxToEml;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Mbox2Eml convert
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException 
    {
    	
    	 File mboxFile = new File("D:/temp/input.mbox");
         File outputDir = new File("D:/temp/output_emls");
         if (!outputDir.exists()) outputDir.mkdirs();

         int count = 0;
         
         try (BufferedReader reader = new BufferedReader(new FileReader(mboxFile))) {
             String line;
             BufferedWriter writer = null;

             while ((line = reader.readLine()) != null) {
                 // MBOX의 메시지 구분자는 라인 시작의 "From " (뒤에 공백 포함)
                 if (line.startsWith("From ")) {
                     if (writer != null) writer.close();
                     
                     // 새 EML 파일 생성
                     File emlFile = new File(outputDir, "message_" + (++count) + ".eml");
                     writer = new BufferedWriter(new FileWriter(emlFile));
                     
                     if (count % 1000 == 0) System.out.println(count + "개 처리 중...");
                 } else {
                     if (writer != null) {
                         writer.write(line);
                         writer.newLine();
                     }
                 }
             }
             if (writer != null) writer.close();
         }
         System.out.println("변환 완료: 총 " + count +" 건");
    	
    	
    }
}
