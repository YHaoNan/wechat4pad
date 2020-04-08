package site.lilpig.wechat4pad.utils;

import android.content.Context;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class FileUtils {
    public static String getFromAssets(Context context, String fileName,Boolean autoAddSemi){
        try {
            InputStreamReader inputReader = new InputStreamReader(context.getResources().getAssets().open(fileName) );
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line="";
            String Result="";
            while((line = bufReader.readLine()) != null){
                if (autoAddSemi && line.charAt(line.length()-1)!=';')
                    line += ";";
                Result += line;
            }
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
