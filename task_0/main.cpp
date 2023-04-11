#include <iostream>
#include <vector>
#include "Box.h"
#include "Container.h"
#include <fstream>

using namespace containers;
using namespace std;

int main() {
    cout << "Struct test" << endl;
    {
        Box box1(10, 10, 10, 1, 21);
        Box box2(9, 3, 5, 2, 13);
        Box box3(8, 5, 5, 4.5, 18);
        Box box4(4, 1, 4, 5.9, 2);

        Box arr[] = {box1, box2, box3, box4};

        for (const auto &i: arr) {
            cout << i << endl;
        }

        cout << "sumPrice: " << sumPrice(arr, 4) << endl;
        cout << condition(arr, 4, 10) << endl;
        cout << condition(arr, 4, 30) << endl;
        cout << getMaxWeight(arr, 4, 3) << endl;
        cout << getMaxWeight(arr, 4, 18) << endl;
    }
    cout << "---5th Task---" << endl;
    {
        Box box1(2, 2, 2, 1, 1);
        Box box2(3, 3, 3, 2, 1);
        Box box3(4, 4, 4, 4.5, 1);
        Box box4(1, 1, 1, 5.9, 1);

        Box arr[] = {box1, box2, box3, box4};

        cout << checkNesting(arr, 4) << endl;
    }
    cout << "------" << endl;
    {
        Box box1(2, 2, 2, 1, 1);
        Box box2(3, 3, 3, 2, 1);
        Box box3(4, 3, 4, 4.5, 1);
        Box box4(1, 1, 1, 5.9, 1);

        Box arr[] = {box1, box2, box3, box4};

        cout << checkNesting(arr, 4) << endl;
    }
    cout << "-----" << endl;
    {
        Box box1(2, 3, 2, 1, 1);
        Box box2(10, 10, 10, 2, 1);
        Box box3(4, 4, 4, 4.5, 1);
        Box box4(1, 1, 1, 5.9, 1);

        Box arr[] = {box1, box2, box3, box4};

        cout << checkNesting(arr, 4) << endl;
    }
    cout << "---6th Task---" << endl;
    {
        Box box1(2, 3, 2, 1, 1);
        Box box2(2, 3, 2, 1, 1);
        Box box3(1, 3, 2, 1, 1);
        cout << (box1 == box2) << endl;
        cout << (box1 == box3) << endl;
    }
    cout << "---Container Tests---" << endl;
    {
        BoxC box1(2, 3, 2, 1, 1);
        BoxC box2(2, 3, 2, 1, 1);
        BoxC box3(1, 3, 1, 1, 1);
        BoxC box4(3, 3, 2, 1, 1);
        Container a(1, 1, 1, 4.5);
        a.addBox(box1);
        a.addBox(box2);
        a.addBox(box3);
        a.addBox(box4);
        cout << (a.sumWeight() == 4) << endl;
        cout << (a.sumValue() == 4) << endl;
        cout << (a[1] == box2) << endl;
        a[1].setWidth(1);
        cout << (a[1] == box2) << endl;
    }
    cout << "---Exceptions---" << endl;
    {
        BoxC box1(2, 3, 2, 1, 1);
        BoxC box2(2, 3, 2, 1, 1);
        BoxC box3(1, 3, 1, 1, 1);
        BoxC box4(3, 3, 2, 1.5, 1);
        Container a(1, 1, 1, 2.5);
        try {

            a.addBox(box1);
            a.addBox(box2);
            a.addBox(box3);
            a.addBox(box4);
        }
        catch (const InvalidWeightException &ex) {
            cout << ex.what() << endl;
        }
        for (int i = 0; i < a.countOfBoxes(); ++i) {
            cout << a[i] << endl;
        }
        cout << a.removeBox(2) << endl;
        cout << a.removeBox(1) << endl;
        a.addBox(box4);
        cout << (a[a.countOfBoxes() - 1] == box4) << endl;
    }
    cout << "---Input---" << endl;
    {
        Box b;
        ifstream file("box.txt");
        if (file.is_open()) {
            file >> b;
            cout << b << endl;
            file.clear();
            file.seekg(0);

            BoxC a;
            file >> a;
            cout << a << endl;
            file.close();
        }
        Container c;
        file.open("container.txt");
        if (file.is_open()) {
            file >> c;
            cout << c << endl;
            file.close();
        }
    }
}
