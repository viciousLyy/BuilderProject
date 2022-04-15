#include "../include/header.h"
#include<stdlib.h>

int main(void){
    int a,b,result;
    char ch;
    cout<<"please enter the formulation:";
    cin>>a>>ch>>b;
    switch(ch){
        case '+':result = adFunc(a,b);
                 break;
        case '-':result = suFunc(a,b);
                 break;
        case '*':result = muFunc(a,b);
                 break;
        case '/':if( b == 0 )
                    {
                        cout<<"the b can't be the 0\n";
                        return 0;
                    }
                 result = deFunc(a,b);
                 break;
        default :cout<<"There is not the char!"<<endl;
                  return 0;
    }

    cout<<"the result of the formulation is"<<result<<"\n";
}
