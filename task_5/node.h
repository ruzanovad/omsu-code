//
// Created by light on 08.04.2023.
//

#ifndef TASK5_NODE_H
#define TASK5_NODE_H


struct node {
    int data;
    node *left;
    node *right;

    explicit node(int data, node* left = nullptr, node*right = nullptr)  {
        this->data = data;
        this->left = left;
        this->right = right;
    }
};


#endif //TASK5_NODE_H
