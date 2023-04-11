//
// Created by light on 25.03.2023.
//

#ifndef TASK3_NODE_H
#define TASK3_NODE_H

template<typename T>
struct Node {
    T value;
    Node *prev;
    Node *next;

    explicit Node() : prev(nullptr), next(nullptr) {}

    explicit Node(const T &value, Node<T>* p = nullptr, Node<T>* n = nullptr) : value(value), prev(p), next(n) {}
};

#endif //TASK3_NODE_H
