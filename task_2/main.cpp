#include <iostream>
#include "Queue.h"

using namespace std;

int main() {
    Queue a(10);
    cout << "Queue is empty?: "<<a.empty() << endl;
    cout << "Created queue with 10 elements:" << endl;

    for (int i = 0; i < 10; ++i){
        cout << i << " ";
        a.push(i);
    }
    cout << endl;
    cout << "Popping all elements:" << endl;
    for (int i = 0; i < 10; ++i){
        cout << a.pop() << " ";
    }
    cout << endl;
    cout << "Pushing 5 elements:" << endl;
    for (int i = 5; i < 10; ++i){
        cout << i << " ";
        a.push(i);
    }
    cout << endl;
    cout << "Popping 2 elements:" << endl;
    for (int i = 0; i < 2; ++i){
        cout << a.pop() << " ";
    }
    cout << endl;
    cout << "Pushing 6 elements:" << endl;
    for (int i = 3; i < 9; ++i){
        cout << i << " ";
        a.push(i);
    }
    cout << endl;
    cout << "iterate:" << endl;

    {
        Queue::iterator it(a);
        it.start();
        while (!it.finish()){
            int x = it.getValue();
            cout << x << " ";
            it.next();
        }
        cout << endl;
    }
    a.pop();
    a.push(-2);
    a.push(-1);
    cout << "iterate:" << endl;
    {
        Queue::iterator it(a);
        it.start();
        while (!it.finish()){
            cout << it.getValue() << " ";
            it.next();
        }
        cout << endl;
    }
    try{
        a.push(1);
    }
    catch (std::length_error& ex){
        cout << ex.what() << endl;
    }
    a.make_empty();
    try{
        a.pop();
    }
    catch (std::length_error& ex){
        cout << ex.what() << endl;
    }
    a.push(-9);
    cout << "iterate queue with one element:" << endl;
    {
        Queue::iterator it(a);
        it.start();
        while (!it.finish()){
            cout << it.getValue() << " ";
            it.next();
        }
        cout << endl;
    }

    {
        Queue q(10);
        int i = -1;
        q.push(i);
    }
}
