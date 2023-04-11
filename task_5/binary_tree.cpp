//
// Created by light on 08.04.2023.
//

#include "binary_tree.h"

binary_tree::binary_tree() {
    root = nullptr;
}

binary_tree::binary_tree(const binary_tree &rhs) {
    node *copy = copy_tree(rhs.root);
    root = copy;
}

binary_tree::binary_tree(binary_tree &&rhs) noexcept {
    root = rhs.root;
    rhs.root = nullptr;
}

binary_tree &binary_tree::operator=(const binary_tree &rhs) {
    if (&rhs == this)
        return *this;
    make_empty();
    node *copy = copy_tree(rhs.root);
    root = copy;
    return *this;
}

binary_tree &binary_tree::operator=(binary_tree &&rhs) noexcept {
    std::swap(root, rhs.root);
    return *this;
}

void binary_tree::insert(int value, const std::vector<bool> &path) {
    if (root == nullptr && !path.empty())
        throw std::invalid_argument("invalid path");
    if (path.empty()) {
        if (root == nullptr)
            root = new node(value);
        else
            root->data = value;
        return;
    }
    node *current = root;

    for (int i = 0; i < path.size(); ++i) {
        if (!current)
            throw std::invalid_argument("invalid path");
        if (!path[i]) {
            if (current->left)
                current = current->left;
            else if (i == path.size() - 1) {
                node *node = new struct node(value);
                current->left = node;
                return;
            } else {
                throw std::invalid_argument("invalid path");
            }
        } else {
            if (current->right)
                current = current->right;
            else if (i == path.size() - 1) {
                node *node = new struct node(value);
                current->right = node;
                return;
            } else {
                throw std::invalid_argument("invalid path");
            }
        }
    }
    if (current)
        current->data = value;
    else
        throw std::invalid_argument("invalid path");
}

void binary_tree::insert(int value, std::vector<bool> &&path) {
    if (root == nullptr && !path.empty())
        throw std::invalid_argument("invalid path");
    if (path.empty()) {
        if (root == nullptr)
            root = new node(value);
        else
            root->data = value;
        return;
    }
    node *current = root;
    for (int i = 0; i < path.size(); ++i) {
        if (!current)
            throw std::invalid_argument("invalid path");
        if (!path[i]) {
            if (current->left)
                current = current->left;
            else if (i == path.size() - 1) {
                node *node = new struct node(value);
                current->left = node;
                return;
            } else {
                throw std::invalid_argument("invalid path");
            }
        } else {
            if (current->right)
                current = current->right;
            else if (i == path.size() - 1) {
                node *node = new struct node(value);
                current->right = node;
                return;
            } else {
                throw std::invalid_argument("invalid path");
            }
        }
    }
    if (current)
        current->data = value;
    else
        throw std::invalid_argument("invalid path");
}

int binary_tree::count_even() const {
    int res = 0;
    node *current = root;
    auto count = [&res](auto &ref, node *curr) mutable -> void {
        if (curr) {
            ref(ref, curr->left);
            if (curr->data % 2 == 0)
                res++;
            ref(ref, curr->right);
        }
    };
    count(count, root);
    return res;
}

std::ostream &operator<<(std::ostream &os, binary_tree &rhs) {
    rhs.print_tree(os, rhs.root);
    return os;
}

bool binary_tree::only_positive() const {
    bool res = true;
    auto count = [&res](auto &ref, node *curr) mutable -> void {
        if (curr) {
            ref(ref, curr->left);
            res *= curr->data > 0;
            if (!res)
                return;
            ref(ref, curr->right);
        }
    };
    count(count, root);
    return res;
}

void binary_tree::delete_leaves() {
    if (is_leaf(root)){
        make_empty();
        return;
    }
    auto del = [](auto &ref, node *curr) mutable -> void {
        if (!curr)
            return;
        if (curr->left) {
            if (is_leaf(curr->left)) {
                delete curr->left;
                curr->left = nullptr;
            } else
                ref(ref, curr->left);
        }
        if (curr->right) {
            if (is_leaf(curr->right)) {
                delete curr->right;
                curr->right = nullptr;
            } else
                ref(ref, curr->right);
        }

    };
    del(del, root);
}

double binary_tree::get_mean() const {
    double res = 0.;
    int count = 0;
    auto sum = [&](auto &ref, node *curr) -> void {
        if (!curr)
            return;
        ref(ref, curr->left);
        res += curr->data;
        count++;
        ref(ref, curr->right);

    };
    sum(sum, root);
    return res / count;
}

std::vector<bool> binary_tree::find(int value) {
    std::vector<bool> current;
    std::vector<bool> res;
    if (root == nullptr)
        throw std::invalid_argument("empty tree");
    if (root->data == value)
        return res;
    bool flag = false;
    auto func = [&](auto &ref, node *curr) -> void {
        if (!curr)
            return;
        if (curr->data == value) {
            res = current;
            return;
        }

        current.push_back(false);
        ref(ref, curr->left);

        current.pop_back();
        current.push_back(true);
        ref(ref, curr->right);
        current.pop_back();

    };
    func(func, root);
    if (res.empty())
        throw std::invalid_argument("not found");
    return res;
}

void binary_tree::make_empty() {
    clear(root);
}

binary_tree::~binary_tree() {
    make_empty();
}

void binary_tree::clear_elems(node *r) {
    if (!r) return;
    clear_elems(r->left);
    clear_elems(r->right);
    delete r;
}

void binary_tree::clear(node *&r) {
    clear_elems(r);
    root = nullptr;
}

node *binary_tree::copy_tree(const node *n) {
    if (!n) { return nullptr; }
    node *copy = new node(root->data);
    try {
        copy->left = copy_tree(root->left);
        copy->right = copy_tree(root->right);
        return copy;
    } catch (std::bad_alloc &c) {
        clear(copy);
        throw;
    }
}

void binary_tree::print_tree(std::ostream &os, node *node, int level) {
    if (node != nullptr) {
        print_tree(os, node->left, level + 1);
        for (int i = 0; i < 4 * level; ++i) {
            os << " ";
        }
        os << "-> " << node->data << '\n';
        print_tree(os, node->right, level + 1);
    }
}

bool binary_tree::is_binary_search_tree() const {
    bool flag = true;

//    bool low = false;
//    bool up =false;
    auto lambda = [&](auto& ref, node * curr, int lower, int upper, bool low, bool up){
        if (!curr)
            return;
        if (low && curr->data <= lower || up && curr->data >= upper) {
            flag *= false;
            return;
        }
        if (flag)
        ref(ref, curr->left, lower, curr->data, low, true);
        if (flag)
        ref(ref, curr->right, curr-> data, upper, true, up);
    };
    lambda(lambda, root, -1, -1, false, false);
    return flag;
}

