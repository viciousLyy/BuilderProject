#include<stdio.h>

int main(void){
    int a,b,result;
    char ch;
    printf("please enter the formulation:");
	scanf("%d%c%d",&a,&ch,&b);
	cin>>a>>ch>>b;
    switch(ch){
        case '+':result = a+b;
                 break;
        case '-':result = a-b;
                 break;
        case '*':result = a*b;
                 break;
        case '/':if( b == 0 )
                    {
                        printf("the b can't be the 0\n");
                        return 0;
                    }
                 result = a/b;
                 break;
        default :printf("There is not the char!");
                  return 0;
    }

    printf("the result of the formulation is %d\n",result);
    printf("enter the Enter to stop!\n");
	scanf("%c",&ch);
	return 0;

}
