/*
Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
For example, 2 is written as II in Roman numeral, just two ones added together. 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.

Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:

I can be placed before V (5) and X (10) to make 4 and 9. 
X can be placed before L (50) and C (100) to make 40 and 90. 
C can be placed before D (500) and M (1000) to make 400 and 900.
Given a roman numeral, convert it to an integer.
*/

/*
Runtime: 6 ms, faster than 81.57% of Java online submissions for Roman to Integer.
Memory Usage: 45.4 MB, less than 58.38% of Java online submissions for Roman to Integer.
*/

class Solution {
    
    public static Map<Character, Integer> dictSymbolValue = new HashMap<>(7){{
        put('I',1);
        put('V',5);
        put('X',10);
        put('L',50);
        put('C',100);
        put('D',500);
        put('M',1000);
    }};
    
    public static List<Character> charsThatCanModifyNext = Arrays.asList('I','X','C'); 
    public static List<Character> charsThatCanBeModified = Arrays.asList('V','L','X', 'C','D','M'); 
    
    public int romanToInt(String s) { 
        int intNum = 0;
        int charPosition = 0;
        while(charPosition<s.length()){
            if(((charPosition+1) <= (s.length()-1)) && verifyNext(s.charAt(charPosition), s.charAt(charPosition+1))){
                intNum += getModifiedNumber(s.charAt(charPosition), s.charAt(charPosition+1));
                charPosition+=2;
                continue;
            }
            intNum += dictSymbolValue.get(s.charAt(charPosition)); 
            charPosition++;
        }
        return intNum;
    }
    
    public boolean verifyNext(char beforeChar, char afterChar){
        if(beforeChar==afterChar){
            return false;
        }
        if(charsThatCanModifyNext.contains(beforeChar) && charsThatCanBeModified.contains(beforeChar)){
            if(dictSymbolValue.get(beforeChar) > dictSymbolValue.get(afterChar)){
                return false;
            }
            return true;
        }
        if(!(charsThatCanModifyNext.contains(beforeChar))){
            return false;
        }
        if(!(charsThatCanBeModified.contains(afterChar))){
            return false;
        }
        return true;
    }
    
    public int getModifiedNumber(char beforeChar, char afterChar){
        return dictSymbolValue.get(afterChar) - dictSymbolValue.get(beforeChar);
    }
    
}
