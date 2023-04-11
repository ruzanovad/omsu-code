#include <iostream>
#include "binary_tree.h"
#include "cassert"
using namespace std;

int main() {
    {
        binary_tree first;
        try {
            vector<bool> p;
            p.push_back(false);
            p.push_back(true);
            first.insert(-5, p);
        }
        catch (const invalid_argument &ex) {
            cout << ex.what() << '\n';
        }
        vector<bool> path;
        first.insert(1, std::vector<bool>());
        cout << first << endl;
        path.push_back(true);
        first.insert(2, path);
        path[0] = false;
        first.insert(0, path);

        try {
            path.push_back(false);
            path.push_back(false);
            first.insert(-5, path);
        }
        catch (const invalid_argument &ex) {
            cout << ex.what() << '\n';
        }
        path.pop_back();
        first.insert(5, path);
        path.pop_back();
        path.push_back(true);
        first.insert(-1, path);

        cout << first << endl;
        cout << "-" << endl;
        path.push_back(true);
        first.insert(-4, path);
        path.pop_back();
        path.push_back(false);
        first.insert(10, path);
        cout << first << endl;
        assert(4 == first.count_even());
        assert(!first.only_positive());
        path[path.size() - 1] = true;
        first.insert(8, path);
        path.pop_back();
        first.insert(60, path);
        vector<bool> n = {false};
        first.insert(300, n);
        cout << "-" << endl;
        cout << "first tree:\n";
        cout << first;

        assert(first.only_positive());

        assert(abs(first.get_mean() - 55.1428571429) < 1e-5);
        assert(first.find(1).empty());
        assert(first.find(2) == vector<bool>({true}));
        assert(first.find(5) == vector<bool>({false, false}));
        assert(first.find(8) == vector<bool>({false, true, true}));
        try {
            assert(first.find(7).empty());
        }
        catch (const invalid_argument &ex) {
            cout << ex.what() << endl;
        }

        cout << "after deleting\n";

        first.delete_leaves();
        cout << first;

        cout << "after deleting\n";

        first.delete_leaves();

        cout << first;

        cout << "after deleting\n";

        first.delete_leaves();

        cout << first;

        cout << "after deleting\n";

        first.delete_leaves();

        cout << first << "---" <<'\n';
    }
    {
        binary_tree second;
        vector<bool> path;
        second.insert(1, path);
        path.push_back(true);
        second.insert(1, path);
        assert(second.find(1).empty());
        cout << second;
        assert(!second.is_binary_search_tree());
    }
    {
        cout << "---"<< endl;
        binary_tree third;
        third.insert(5, vector<bool>({}));
        third.insert(7, vector<bool>({true}));
        third.insert(4, vector<bool >({false}));
        third.insert(3, vector<bool>({false, false}));
        third.insert(6, vector<bool>({true, false}));
        third.insert(8, vector<bool>({true, true}));
        third.insert(8, vector<bool>({true, true, true}));

        cout << third;

        assert(third.only_positive());
        assert(!third.is_binary_search_tree());

        third.insert(11, vector<bool>({true, true, true}));

        assert(third.is_binary_search_tree());

        cout << "---"<< endl << third;
    }
}
