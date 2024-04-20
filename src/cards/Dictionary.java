package cards;

import java.util.HashMap;
import java.util.Map;

public class Dictionary 
{

    private Map<String, String> dictionary;

    public Dictionary() 
    {
        dictionary = new HashMap<>();
        // nhét db vào mồm( thay đoạn này thành lấy dữ liệu từ db)
        dictionary.put("hello,", "world!");
//        dictionary.put("world", "thế giới");
        // ...
    }
    
    public void dictPut(String first, String second) {
    	dictionary.put(first, second);
    }
    
    public void replace(String oldfirst, String first, String second) {
    	dictionary.remove(oldfirst);
    	dictionary.put(first, second);
    }
    
    public String lookup(String word) 
    {
        return dictionary.get(word);
    }

    public static void main(String[] args) 
    {
        Dictionary dictionary = new Dictionary();
        //Đoạn này thay bằng nhập từ Scanner hộ chube nka
        String word = "xin chào";
        String definition = dictionary.lookup(word);
        if (definition != null) 
        {
            System.out.println("Từ này đã có " + word + ": " + definition);
        } 
        else 
        {
            System.out.println("Chưa có nhập vào " + word + ":");
            //Scanner du lieu vào ròi  dictionary.put vào db
        }
    }
}