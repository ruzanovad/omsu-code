//
// Created by light on 08.04.2023.
//

#ifndef TASK5_BINARY_TREE_H
#define TASK5_BINARY_TREE_H

#include <iostream>
#include <vector>
#include "node.h"

class binary_tree {
private:
    node *root;

    node *copy_tree(const node *n);

    void clear_elems(node *r);

    void clear(node *&r);

    void print_tree(std::ostream &os, node *node, int level = 0);

    static bool is_leaf(node *node) { return node->left == node->right; }

public:
    binary_tree();

    binary_tree(const binary_tree &);

    binary_tree(binary_tree &&) noexcept;

    binary_tree &operator=(const binary_tree &);

    binary_tree &operator=(binary_tree &&) noexcept;

    void insert(int value, const std::vector<bool> &path);

    void insert(int value, std::vector<bool>&& path);

    friend std::ostream &operator<<(std::ostream &, binary_tree &);

    int count_even() const;

    bool only_positive() const;

    void delete_leaves();

    bool is_binary_search_tree() const;

    double get_mean() const;

    std::vector<bool> find(int);

    void make_empty();

    ~binary_tree();
};


#endif //TASK5_BINARY_TREE_H
