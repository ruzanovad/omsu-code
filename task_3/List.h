//
// Created by light on 20.03.2023.
//


#ifndef TASK3_LIST_H
#define TASK3_LIST_H

#include <iostream>

template<typename T>
class Iterator;

template<typename T>
class List {
public:
    virtual void insert(Iterator<T> *it, const T &value) = 0;

    virtual void erase(Iterator<T> *it) = 0;

    virtual Iterator<T>* find(const T &element) = 0;

    virtual void make_empty() = 0;

    virtual bool empty() const = 0;

    virtual std::size_t length() const = 0;

    virtual Iterator<T>* begin() const = 0;

    virtual ~List() = default;
};

#endif //TASK3_LIST_H
