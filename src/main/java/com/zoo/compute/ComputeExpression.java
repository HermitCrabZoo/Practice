package com.zoo.compute;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

/**
 * 此类用于对一个综合数学表达试求值，用一个数学表达式实例化这个类后，
 * getResult()方法就会返回结果，如4.5/1.5+5*(60-20)/(35.7-25.7)的
 * 计算结果为22.999999999999993（因为是浮点运算，精度稍有不同）。
 *
 */
public class ComputeExpression
{
    private String expression;
    public ComputeExpression(String expression)
    {
        this.expression=expression;
    }
    
    private String[] convert()
    {
        ArrayList<String> arraylist=new ArrayList<String>();
        for(int i=0;i<expression.length();i++)
        {
            if(expression.charAt(i)>='0'&&expression.charAt(i)<='9'||expression.charAt(i)=='.')
            {
                String bridge=new String("");
                for(;i<expression.length()&&(expression.charAt(i)>='0'&&expression.charAt(i)<='9'||expression.charAt(i)=='.');i++)
                    bridge=bridge+Character.toString(expression.charAt(i));
                arraylist.add(bridge);
                i--;
            }
            else
                arraylist.add(Character.toString(expression.charAt(i)));
        }
        Iterator<String> it=arraylist.iterator();
        String[] temp=new String[arraylist.size()];
        int i=0;
        while(it.hasNext())
            temp[i++]=(String)it.next();
        return temp;
    }
    
    private String[] getPostfix(String[] temp)
    {
        String[] result=null;
        ArrayList<String> arraylist=new ArrayList<String>();
        Stack<String> stack=new Stack<String>();
        stack.push(new String("#"));
        for(int i=0;i<temp.length;i++)
        {
            if(temp[i].charAt(0)>='0'&&temp[i].charAt(0)<='9')
                arraylist.add(temp[i]);
            else if(temp[i].equals("("))
                stack.push(temp[i]);
            else if(temp[i].equals("×")||temp[i].equals("÷"))
            {
                while(((String)stack.peek()).equals("×")||((String)stack.peek()).equals("÷"))
                    arraylist.add((String)stack.pop());
                stack.push(temp[i]);
            }
            else if(temp[i].equals("＋")||temp[i].equals("－"))
            {
                while(!((String)stack.peek()).equals("(")&&!((String)stack.peek()).equals("#"))
                    arraylist.add((String)stack.pop());
                stack.push(temp[i]);
            }
            else if(temp[i].equals(")"))
            {
                while(!((String)stack.peek()).equals("("))
                    arraylist.add((String)stack.pop());
                stack.pop();
            }
            else
                break;
        }
        while(!((String)stack.peek()).equals("#"))
            arraylist.add((String)stack.pop());
        result=new String[arraylist.size()];
        Iterator<String> it=arraylist.iterator();
        int i=0;
        while(it.hasNext())
            result[i++]=(String)it.next();
        return result;
    }
    
    private String getValue(String[] temp)
    {
        Stack<String> stack=new Stack<String>();
        for(int i=0;i<temp.length;i++)
        {
            if(temp[i].charAt(0)>='0'&&temp[i].charAt(0)<='9')
                stack.push(temp[i]);
            else if(temp[i].equals("＋"))
                stack.push(Double.toString(Double.parseDouble((String)stack.pop())+Double.parseDouble((String)stack.pop())));
            else if(temp[i].equals("－"))
                stack.push(Double.toString(-(Double.parseDouble((String)stack.pop())-Double.parseDouble((String)stack.pop()))));
            else if(temp[i].equals("×"))
                stack.push(Double.toString(Double.parseDouble((String)stack.pop())*Double.parseDouble((String)stack.pop())));
            else if(temp[i].equals("÷"))
                try
                {
                    stack.push(Double.toString(1/(Double.parseDouble((String)stack.pop())/Double.parseDouble((String)stack.pop()))));
                }
                catch(Exception ex)
                {
                    System.out.println("除数不能为0");
                }
        }
        return (String)stack.pop();
    }
    
    public String getResult()
    {
        String[] temp=null;
        String result=null;
        temp=getPostfix(convert());
        result=getValue(temp);
        return result;        
    }
}
