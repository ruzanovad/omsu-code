#include <iostream>
#include <vector>
#include <cassert>
#include "LinkedList.h"
#include "Iterator.h"

using namespace std;

int main() {
//    std::vector<int> v;
//    v.insert(v.begin(), 1);
//    v.insert(v.end(), 2);
//    v.erase(v.end());
    LinkedList<int> a;
    Iterator<int> *it = a.begin(), *itt = a.begin();

    a.insert(it, 1);
    a.insert(it, 2);
    a.insert(it, 3);

    a.print();
    assert(a.length() == 3);

    itt->next();
    for (int i = 3; i >= 1; i--) {
        assert(itt->get() == i);
        itt->next();
    }
    a.print();

    it->next();
    it->next();
    assert(it->get() == 2);

    a.erase(it);
    a.erase(it);
    try {
        assert(it->get() == 3);
    }
    catch (const std::logic_error &ex) {
        assert(it->finish());
    }
    //buffer element
    it->start();
    it->next();
    assert(it->get() == 3);

    a.erase(it);
    assert(a.empty() == it->finish() == true);

    LinkedList<double> b;
    Iterator<double> *d = b.begin();
    for (int i = 0; i < 10; ++i) {
        b.insert(d, i);
        d->next();
    }


    LinkedList<double> b_copy(b);
    cout << "b: ";
    b.print();
    b.make_empty();
    assert(b.empty());

    cout << "b_copy: ";
    b_copy.print();

    LinkedList<double> b_copy_copy(std::move(b_copy));
    cout << "b_copy_copy: ";
    b_copy_copy.print();
    assert(b_copy_copy.find(0)->get() == b_copy_copy.begin()->get());
    assert(b_copy_copy.find(-1) == nullptr);

    LinkedList<string> c, s;
    Iterator<string> *e = c.begin();
    c.insert(e, "s");
    c.insert(e, "e");
    c.insert(e, "a");
    c.insert(e, "u");
    e->next();
    c.insert(e, "A");

    s.insert(s.begin(), "FF");
    LinkedList<string> m;
    for (int i = 0; i < 7; ++i)
        m.insert(m.begin(), "k");
    s = c;
    s.print();
    s.insert(s.begin(), "G");

    cout << "c: ";
    c.print();

    cout << "s: ";
    s.print();

    m = std::move(c);
    cout << "m: ";
    m.print();

    return 0;
}
