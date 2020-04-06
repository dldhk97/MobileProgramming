package com.dldhk97.mgji_adder.logic;

class Calculator {
    // 2진수에서 1 더하기
    String plusOne(String str){
        char[] charArr = str.toCharArray();
        for(int i = charArr.length - 1 ; i >= 0 ; i--)
        {
            if(charArr[i] == '1'){
                charArr[i] = '0';
            }
            else{
                charArr[i] = '1';
                break;
            }
        }
        return String.valueOf(charArr);
    }

    // 2진수에서 1 빼기
    String minusOne(String str){
        char[] charArr = str.toCharArray();
        for(int i = charArr.length - 1 ; i >= 0 ; i--)
        {
            if(charArr[i] == '0'){
                charArr[i] = '1';
            }
            else{
                charArr[i] = '0';
                break;
            }
        }
        return String.valueOf(charArr);
    }

    // 0과 1 반전
    String reverseBinary(String str){
        StringBuilder result = new StringBuilder();
        for(int i = 0 ; i < str.length() ; i++){
            if(str.charAt(i) == '0'){
                result.append('1');
            }
            else{
                result.append('0');
            }
        }
        return result.toString();
    }

    // 양수 이진수를 10진수로 변환
    int plusBinaryToDemical(String plustStr){
        int result = 0;
        int multiply = 1;
        for(int i = plustStr.length() - 1 ; i >= 0 ; i--){
            String tmp1 = String.valueOf(plustStr.charAt(i));
            int tmp = Integer.parseInt((tmp1));
            result += tmp * multiply;
            multiply *= 2;
        }
        return result;
    }
}
