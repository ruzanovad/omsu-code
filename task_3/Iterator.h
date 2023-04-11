//
// Created by light on 20.03.2023.
//

#ifndef TASK3_ITERATOR_H
#define TASK3_ITERATOR_H

template<typename T>
class List;

template<typename T>
class Node;

template<typename T>
class Iterator {
public:

    virtual void start() = 0;

    virtual T get() const = 0;

    virtual void next() = 0;

    virtual bool finish() const = 0;
};


#endif //TASK3_ITERATOR_H
