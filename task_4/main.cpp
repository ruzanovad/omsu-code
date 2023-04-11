#include <iostream>
#include <vector>
#include <map>
#include <cassert>
#include <string>
#include "hashtable.h"
#include "linked_hashtable.h"

using namespace std;

int main() {
    {
        hashtable<int, int> a;

        assert(a.empty());

        a.insert(1, 1);
        assert(a.find(1) == 1);
        a.insert(1, 2);
        assert(a.find(1) == 2);
        a.insert(1001, 1);
        assert(a.find(1001) == 1);

        hashtable<wchar_t, string> s;
        assert(s.empty());
        s.insert(L'а', "арбуз");
        s.insert(L'б', "банан");
        s.insert(L'в', "вишня");
        assert(s.length() == 3);
        assert(s.find(L'а') == "арбуз");
        try {
            assert(s.find(L'r') == "киви");
        }
        catch (const std::invalid_argument &ex) {
            cout << ex.what() << endl;
        }
        s.make_empty();
        assert(s.empty());
        try {
            assert(s.find(L'б') == "банан");
        }
        catch (const std::invalid_argument &ex) {
            cout << ex.what() << endl;
        }
        try {
            assert(s.find(L'а') == "арбуз");
        }
        catch (const std::invalid_argument &ex) {
            cout << ex.what() << endl;
        }
        try {
            assert(s.find(L'в') == "вишня");
        }
        catch (const std::invalid_argument &ex) {
            cout << ex.what() << endl;
        }
        s.insert(L'м', "манго");
        assert(s.find(L'м') == "манго");
    }

    {
        linked_hashtable<int, int> a;

        assert(a.empty());

        a.insert(1, 1);
        assert(a.find(1) == 1);
        a.insert(1, 2);
        assert(a.find(1) == 2);
        a.insert(1001, 1);
        assert(a.find(1001) == 1);

        a.insert(102, 2);
        a.insert(-2, 2);
        a.insert(102, 3);

        for (auto it = a.begin();!it->finish(); it->next()){
            cout << it->get().key << " " << it->get().value << '\n';
        }
        linked_hashtable<wchar_t, string> s;
        assert(s.empty());
        s.insert(L'а', "арбуз");
        s.insert(L'б', "банан");
        s.insert(L'в', "вишня");
        assert(s.length() == 3);
        assert(s.find(L'а') == "арбуз");
        try {
            assert(s.find(L'r') == "киви");
        }
        catch (const std::invalid_argument &ex) {
            cout << ex.what() << endl;
        }
        s.make_empty();
        assert(s.empty());
        try {
            assert(s.find(L'б') == "банан");
        }
        catch (const std::invalid_argument &ex) {
            cout << ex.what() << endl;
        }
        try {
            assert(s.find(L'а') == "арбуз");
        }
        catch (const std::invalid_argument &ex) {
            cout << ex.what() << endl;
        }
        try {
            assert(s.find(L'в') == "вишня");
        }
        catch (const std::invalid_argument &ex) {
            cout << ex.what() << endl;
        }
        s.insert(L'м', "манго");
        assert(s.find(L'м') == "манго");
    }

}
