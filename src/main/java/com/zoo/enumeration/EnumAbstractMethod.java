package com.zoo.enumeration;

public enum EnumAbstractMethod {
	FIRST{
        @Override
        public String getInfo() {
            return "FIRST TIME";
        }
    },
    SECOND{
        @Override
        public String getInfo() {
            return "SECOND TIME";
        }
    };

    /**
     * 定义抽象方法
     * @return
     */
    public abstract String getInfo();

    //测试
    public static void main(String[] args){
        System.out.println("F:"+EnumAbstractMethod.FIRST.getInfo());
        System.out.println("S:"+EnumAbstractMethod.SECOND.getInfo());
//        F:FIRST TIME
//        S:SECOND TIME
    }

}
