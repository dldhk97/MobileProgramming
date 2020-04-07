package com.dldhk97.mgji_adder.logic;

import com.dldhk97.mgji_adder.MyException;
import com.dldhk97.mgji_adder.enums.ExceptionType;

public class BinaryConverter {

    // 3비트 2의보수 문자열을 10진수 int형으로 반환
    public int toDecimal(String string) throws Exception{
        String target = string;
        int result;
        Calculator calc = new Calculator();
        try{
            char firstChar = target.charAt(0);

            // 첫 비트가 1일 때 처리
            if(firstChar == '1'){
                // 2의 보수이므로 1을 먼저 뺀다
                String tmp = calc.minusOne(target);
                // 0과 1을 반전시킨다.
                target = calc.reverseBinary(tmp);
            }

            //2진수를 10진수로 변환
            result = calc.plusBinaryToDemical(target);

            if(firstChar == '1') {
                result = -result;
            }

        }
        catch (Exception e){
            throw new MyException(ExceptionType.BINARY_TO_INT_FAILED, "2진수를 10진수로 변환하는데 실패하였습니다!");
        }
        return result;
    }

    // 10진수를 3비트 2의 보수 이진수로 변환
    public String toBinary(int decimal) throws Exception{
        Calculator calc = new Calculator();
        try{
            String binaryStr;
            int bitLength = 3;

            //음수일 때 처리
            if(decimal < 0){

                // -4보다 작으면 비트수가 4개가 됨.
                if(decimal < -4){
                    bitLength = 4;
                }

                //임시로 양수로 바꿔서 2진수 얻어낸다.
                binaryStr = Integer.toBinaryString(-decimal);
                //2진수로 바꿨을 때 앞에 0 채워줌
                for(int i = binaryStr.length() ; i < bitLength ; i++){
                    binaryStr = '0' + binaryStr;
                }
                // 0 과 1 반전
                binaryStr = calc.reverseBinary(binaryStr);
                // 1 더한다.
                binaryStr = calc.plusOne(binaryStr);
            }
            else{
                // 양수면 바로 2진수로 바꾼다
                binaryStr = Integer.toBinaryString(decimal);
            }

            // 3비트로 표현하기 위해 변환
            int binaryInt = Integer.parseInt(binaryStr);
            binaryStr = String.format("%03d", binaryInt);

            // 3비트 이상이면?
            if(binaryStr.length() > 3){
                return binaryStr.substring(binaryStr.length() - bitLength, binaryStr.length());
            }

            return binaryStr;
        }
        catch(Exception e){
            throw new MyException(ExceptionType.INT_TO_BINARY_FAILED, "10진수를 2진수로 변환하는데 실패하였습니다!");
        }
    }
}
