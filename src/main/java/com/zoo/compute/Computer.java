package com.zoo.compute;

public class Computer {

	
	
	
	
	
	
	
	
	
	
	/*char data[]; // 原始数组
	String d[]; // 转换后的数组
	int n;
	double result;

	*//**
	 * @param args
	 *//*
	public Computer(char[] a) // 以表达式及其数组长度初始化
	{
		n = a.length;
		data = new char[n + 2];
		d = new String[n * 2 + 1];

		for (int i = 0; i < n * 3 / 2 + 1; i++) {
			d[i] = "";
		}
		for (int i = 0; i < n; i++) {
			data[i] = a[i];
		}
		data[n] = '#';

	}

	private boolean isNeg(int i) {
		if (i == 0 && data[i] == '-')
			return true;
		else if (i != 0 && data[i] == '-' && data[i - 1] == '(')
			return true;
		else
			return false;
	}

	private int jiecheng(int a) {
		if (a < 0)
			return -1;
		else if (a == 0)
			return 1;
		else {
			int s = 1;
			for (int i = 1; i <= a; i++)
				s *= i;
			return s;
		}
	}

	private double toDouble(String s) {
		int len = s.length();
		double m = 0;
		double n = 0;
		int poi = s.indexOf('.');
		if (poi == -1)
			for (int i = 0; i < len; i++) {
				int temp = s.charAt(i) - '0';
				m = temp + 10 * m;
			}
		else {
			for (int i = 0; i < poi; i++) {
				double temp = s.charAt(i) - '0';
				m = temp + 10 * m;
			}
			for (int i = len - 1; i > poi; i--) {
				double temp = s.charAt(i) - '0';
				n = n * 0.1 + temp;
			}
			n = n * 0.1;
		}
		double r = m + n;
		return r;
	}

	private String transform()//求出后缀表达式
	 {
	  int p=0;
	  for(int w=0;w<n;w++)
	     {if(data[w]=='(') p++;
	      if(data[w]==')') p--;
	   if(p<0) {return" 输入错误：括号不匹配";}
	  }
	  if(p!=0){return" 输入错误：括号不匹配";}

	  if(data[0]=='.')
	  {
	   return" 输入错误：一个小数的小数点前缺少数字";
	  }
	     Stack s=new Stack();
	    s.push('#');
	    int j=0;
	    
	    for(int i=0;i<n+1;i++)
	    {
	     //System.out.println("使用元素为:"+data[i]);
	     if(i==0&&data[0]>='0'&&data[0]<='9')  //第0位是数
	     {
	               d[j]+=data[0];
	      j++;
	     }
	     else if(data[i]>='0'&&data[i]<='9'&&((data[i-1]<'0'||data[i-1]>'9')&&data[i-1]!='.'))  
	     {                                                        //多位数字第一位或单位数除了第一位是数的可能
	               d[j]+=data[i];
	               //System.out.println(' ');//cout<<data[i];
	      j++;
	     }
	     else if(data[i]>='0'&&data[i]<='9'||i!=0&&data[i-1]=='.')//多位数
	     {
	      if(j!=0)  d[j-1]=d[j-1]+data[i];
	      //cout<<data[i];
	     }
	     else if(data[i]=='.')//小数点
	     {
	               for(int u=0;u<d[j-1].length();u++) //检查是否有过多的小数点
	       if(d[j-1].charAt(u)=='.')
	       {
	        return" 输入错误：一个小数中出现多个小数点";
	       }
	               if(data[i+1]<'0'||data[i+1]>'9')
	       {
	                return" 输入错误：一个小数的小数点后缺少数字";
	       }
	      if(data[i-1]<'0'||data[i-1]>'9')
	       {
	       return" 输入错误：一个小数的小数点前缺少数字";
	       }
	               d[j-1]=d[j-1]+data[i];//cout<<data[i];
	     }
	     else if(isNeg(i))//负数
	     {
	      //cout<<isNeg(i)<<endl;
	      d[j]="0";j++;//cout<<" 0";
	      if(data[i+1]=='.') 
	      {return" 出现错误：错误类型为出现 /"-.x/"结构";}
	               d[j]+=data[i+1];
	      j++;
	      //cout<<' '<<data[i+1];
	      i=i+2;
	      while(data[i]>='0'&&data[i]<='9'||data[i]=='.')
	      {
	       d[j-1]=d[j-1]+data[i];
	       i++;
	      }
	      i--;
	      d[j]+='-';j++;//cout<<" -";
	     }
	     else if(data[i]=='('||data[i]==')'||data[i]=='+'||data[i]=='-'||data[i]=='√'
	         ||data[i]=='*'||data[i]=='/'||data[i]=='#'&&i==n||data[i]=='^'||data[i]=='!')  //是运算符
	     {
	      if((data[i]=='+'||data[i]=='-'||data[i]=='*'||data[i]=='/'||data[i]=='^'||data[i]=='√')
	     &&(data[i+1]=='+'||data[i+1]=='-'||data[i+1]=='*'||data[i+1]=='/'||data[i+1]=='^'||data[i+1]=='!'))
	       {
	       return" 输入错误：两个运算符不能连续使用";
	       }
	      if(data[i]==')'&&data[i-1]=='(')
	      {
	       return" 括号不匹配";
	      }
	      int c=compare(data[i],s.Top());   //比较优先级
	      if(c==1) s.push(data[i]);   //大于
	      else if(c==-1)            //小于
	      {
	       while(compare(data[i],s.Top())==-1)
	       {
	        char m;
	           m=s.pop();
	           d[j]+=m;
	        //cout<<' ';
	        //cout<<m;
	              j++;
	       };
	       if(compare(data[i],s.Top())==1){s.push(data[i]);}
	                   else if(s.Top()=='('&&data[i]==')'){s.pop();}
	          else if(s.Top()=='#'&&data[i]=='#'){s.pop();break;}
	      }
	      else if(s.Top()=='('&&data[i]==')')
	       {s.pop();}
	      else if(s.Top()=='#'&&data[i]=='#')break;
	      //else  cout<<"表达式有误"<<endl;
	     }
	           else 
	     {return" 输入错误：出现非法字符"+data[i];}
	    } 
	          String houzhui="";
	    System.out.println("后缀表达式为：");
	    for(int i=0;d[i]!="";i++)
	    {
	     houzhui+=d[i];
	     System.out.println(i+" "+d[i]);
	    }
	    return "h后缀表达式为"+houzhui;
	 }

	private int compare(char a, char b) // 比较优先级,返回1则为a大于b
	{
		// char b=c.charAt(0);
		switch (a) {
		case '+':
		case '-': {
			if (b == '+' || b == '-' || b == '*' || b == '/' || b == ')' || b == '^' || b == '!' || b == '√')
				return -1;
			if (b == '(' || b == '#')
				return 1;
		}
		case '*':
		case '/': {
			if (b == '+' || b == '-' || b == '#' || b == '(')
				return 1;
			if (b == ')' || b == '*' || b == '/' || b == '^' || b == '!' || b == '√')
				return -1;
		}
		case '(': {
			return 1;
		}
		case ')': {
			if (b == '+' || b == '-' || b == '*' || b == '/' || b == '#' || b == '^' || b == '!' || b == '√')
				return -1;
			if (b == '(')
				return 0;
		}
		case '^': {
			if (b == '+' || b == '-' || b == '#' || b == '(' || b == '*' || b == '/')
				return 1;
			if (b == ')' || b == '!' || b == '√')
				return -1;
		}
		case '!': {
			if (b == '+' || b == '-' || b == '#' || b == '(' || b == '*' || b == '/' || b == '^' || b == '√')
				return 1;
			if (b == ')')
				return -1;
		}
		case '√': {
			if (b == '+' || b == '-' || b == '#' || b == '(' || b == '*' || b == '/' || b == '^' || b == '√')
				return 1;
			if (b == ')')
				return -1;
		}
		case '#': {
			if (b == '#')
				return 0;
			else
				return -1;
		}
		default:
			System.out.println("出现非法运算符");
		}
		return 0;
	}

	String calculate()// 根据后缀表达式计算
	{
		int c = 0;
		for (; d[c] != ""; c++)
			;
		// cout<<c<<endl;
		doubleStack q = new doubleStack();
		double r;
		for (int i = 0; i < c; i++) {
			if (d[i].charAt(0) >= '0' && d[i].charAt(0) <= '9') {
				// cout<<d[i][0]<<endl;
				double qdouble = toDouble(d[i]);
				// cout<<"入栈"<<qdouble<<endl;
				q.push(qdouble);
			} else {
				double m, n;
				m = q.pop();
				if (d[i].charAt(0) != '√' && q.isEmpty()) {
					return " 无法计算：操作数不匹配";
				} // 解决"1+"问题

				switch (d[i].charAt(0)) {
				case '+': {
					n = q.pop();
					r = m + n;
					q.push(r);
					break;
				}
				case '-': {
					n = q.pop();
					r = n - m;
					q.push(r);
					break;
				}
				case '*': {
					n = q.pop();
					r = n * m;
					q.push(r);
					break;
				}
				case '/': {
					n = q.pop();
					if (m == 0) {
						return " 无法计算：除数不能为零/n";
					}
					r = n / m;
					q.push(r);
					break;
				}
				case '^': {
					n = q.pop();
					r = Math.pow(n, m);
					q.push(r);
					break;
				}
				case '!': {
					if (m - (int) m != 0 || m < 0)
						return "无法计算：小数和负数不能阶乘";
					else {
						r = jiecheng((int) m);
						q.push(r);
					}
					break;
				}
				case '√':// 开平方
				{
					if (m < 0)
						return "无法计算：负数不能开平方";
					else {
						r = Math.sqrt(m);
						q.push(r);
					}
					break;
				}
				}
			}
		}
		r = q.pop();
		result = r;
		// System.out.println("/n最后计算结果为："+r);
		String s = Double.toString(r);
		return s;
	}

	String run()// 其中所有错误的返回字符串都以空格开头，若以h开头则为可以写出后缀表达式但不能计算
	{
		String hou = transform();
		if (hou.charAt(0) == 'h') {
			String res = calculate();
			if (res.charAt(0) == ' ')
				return ' ' + hou.substring(1, hou.length()) + "/n但是" + res;
			else
				return res;
		}
		return hou;
		// System.out.println("运算正常!!");}
		// else System.out.println("此错误表达式无法生成后缀表达式!!!");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char[] a = new char[6];
		a[0] = '√';
		a[1] = '4';
		a[2] = '9';
		a[3] = '-';
		a[4] = '1';
		a[5] = '0';
		Computer e = new Computer(a);
		System.out.println("最终结果为：" + e.run());
		// System.out.println("成功!!!");
	}*/

}
