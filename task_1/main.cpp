#include <iostream>
#include "Vector.h"
#include <fstream>
#include <exception>

using namespace std;

int main() {
    cout << "---Constructors---" << endl;
    {
        Vector v;
        cout << "v: " << v << endl;
        Vector v1(2);
        cout << "v1: " << v1 << endl;
        Vector v2(2, -2);
        cout << "v2: " << v2 << endl;
        Vector v3(2, 1, -1);
        Vector v4(v3);

        v4[0] = 0;

        cout << "v3: " << v3 << endl;
        cout << "v4: " << v4 << endl;
        Vector v5(std::move(v3));
        cout << "v5: " << v5 << endl;

        cout << "After moving v3 is empty:";
        (v3.empty()) ? cout << "true" << endl : cout << "false" << endl;
    }
    cout << "------" << endl;
    {
        Vector v(0);
        v.reserve(4);
        cout << v << endl;
        v.resize(2);
        cout << v << endl;
        cout << "Empty vector, reserved 4, resized to 2" << endl;
        try {
            cout << v[2] << endl;
        }
        catch (exception &ex) {
            cout << ex.what() << endl;
            cout << "Reserved element is not accessible by operator[]" << endl;
        }
        for (int i = 0; i < 5; ++i) {
            v.pushBack(i);
        }
        cout << v << endl;
        cout << "popping 2 elements" << endl;
        v.popBack();
        cout << "capacity: " << v.capacity() << endl;
        v.popBack();
        cout << "capacity: " << v.capacity() << endl;
        cout << v << endl;
    }
    cout << "---Equality Operators---" << endl;
    {
        Vector v(10);
        Vector vv(0);
        Vector u(10);
        Vector g(11);
        for (int i = 0; i < 10; ++i) {
            v[i] = i + 1;
            vv.pushBack(i + 1);
            u[i] = v[i] ^ 10;
            g[i] = v[i] ^ u[i];
        }
        g[10] = -1;

        cout << "v " << v << endl;
        cout << "vv: " << vv << endl;
        cout << "u: " << u << endl;
        cout << "g: " << g << endl;

        cout << "v == vv: " << (v == vv) << endl;
        cout << "v != vv: " << (v != vv) << endl;
        cout << "u == vv: " << (u == vv) << endl;
        cout << "g == u: " << endl;
        try {
            cout << (g == u) << endl;
        }
        catch (const std::invalid_argument &e) {
            cout << e.what() << endl;
            cout << "We cannot compare vectors with different lengths" << endl;
        }
        cout << "Empty vectors are equal: " << (Vector() == Vector()) << endl;
    }
    cout << "---Assignment Operators---" << endl;
    {
        Vector v(5);
        Vector g(5);
        Vector u(6);
        for (int i = 0; i < 5; ++i) {
            v[i] = i | (i + 2);
            u[i] = (i + 1) | (i + 3);
            g[i] = v[i] & g[i];
        }

        cout << v << endl;
        cout << u << endl;
        cout << g << endl;

        Vector copyAssign = v;
        cout << copyAssign << endl;
        Vector moveAssign = std::move(v);
        cout << moveAssign << endl;
        cout << v.empty() << endl;
    }
    cout << "---Lexicographic Comparison---" << endl;
    {
        Vector maxi(4);
        Vector midi(4);
        Vector equal(6);
        Vector mini(6);
        for (int i = 0; i < 6; ++i) {
            mini[i] = i;
            equal[i] = i;
        }
        for (int i = 0; i < 4; ++i) {
            midi[i] = i;
            maxi[i] = i + 1;
        }
        cout << "maxi: " << maxi << endl;
        cout << "midi: " << midi << endl;
        cout << "mini: " << mini << endl;
        cout << "equal: " << mini << endl;
        cout << "mini is bigger than midi " << (midi < mini) << endl;
        cout << "maxi is bigger than midi " << (maxi > midi) << endl;
        cout << "midi is bigger than empty vector: " << (midi > Vector()) << endl;
        cout << "Is equal NOT LESS than mini? ";
        (0 == (equal < mini)) ? cout << "true" << endl : cout << "false" << endl;
        cout << "Is equal less or equal than mini? ";
        (1 == (equal <= mini)) ? cout << "true" << endl : cout << "false" << endl;
    }
    cout << "---Concatenation---" << endl;
    {
        Vector m(3), n(2), k(1);
        for (int i = 0; i < 3; ++i) {
            m[i] = (i + 1) ^ ((1 << (i + 3)) + 10);
        }
        n[0] = -(m[0] & m[1]);
        n[1] = -(m[1] & m[2]);

        k[0] = 100000000;

        cout << "m: " << m << endl;
        cout << "n: " << n << endl;
        cout << "k: " << k << endl;

        Vector a = m + n, b = m + k, c = n + m + k;
        cout << "m + n: " << a << endl;
        cout << "m + k: " << b << endl;
        cout << "n + m + k: " << c << endl;
    }
    cout << "---Input---" << endl;
    {
        Vector v;
        ifstream file("vector.txt");
        if (file.is_open()) {
            file >> v;
            cout << v << endl;
        }

    }
}
