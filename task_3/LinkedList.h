//
// Created by light on 20.03.2023.
//
//#include <vector>
#include <exception>
#include <iostream>

#include "List.h"
#include "Node.h"
#include "Iterator.h"

#ifndef TASK3_LINKEDLIST_H
#define TASK3_LINKEDLIST_H

template<typename T>
class LinkedList : public List<T> {
    friend class Iterator<T>;
private:
    Node<T> *head;
    std::size_t size;
public:
    LinkedList();

    LinkedList(const LinkedList<T> &);

    LinkedList(LinkedList<T> &&) noexcept;

    void insert(Iterator<T> *, const T &) override;

    void erase(Iterator<T> *) override;

    std::size_t length() const override;

    bool empty() const override;

    Iterator<T> *begin() const override;

    Iterator<T> * end(){
        auto *it =new ListIterator(*this);
        it->end();
        return it;
    }

    void make_empty() override;

    Iterator<T> *find(const T &) override;

    void print() {
        if (!empty()) {
            Node<T> *h = head->next;
            while (h != head) {
                std::cout << h->value << " ";
                h = h->next;
            }
            std::cout << "\n";
        }

    }

    LinkedList<T> &operator=(const LinkedList<T> &other);

    LinkedList<T> &operator=(LinkedList<T> &&other) noexcept;

    ~LinkedList() {
        make_empty();
        head = nullptr;
    }

    class ListIterator : public Iterator<T> {
    private:
        Node<T> *head;
        Node<T> *position;
    public:
        friend class LinkedList<T>;

        explicit ListIterator(const LinkedList<T>& par) : head(par.head), position(par.head) {};

        void start() override {
            position = head;
        }

        T get() const override {
            if (finish())
                throw std::logic_error("cannot get element from buffer element");
            return position->value;
        }

        void next() override {
            position = position->next;
        }

        bool finish() const override {
            return position == head;
        }

        inline void end(){
            position = head->prev;
        }

        ~ListIterator() {
            position->prev = nullptr;
            position->next = nullptr;

            delete position;
            position = nullptr;
        }
    };

};

template<typename T>
LinkedList<T>::LinkedList(const LinkedList<T> &other) : size(other.size) {
    head = new Node<T>();
    Node<T> *thisCurrent = head;
    Node<T> *current = other.head;

    head->value = other.head->value;
    while (current->next != other.head) {
        Node<T> *prv = thisCurrent;
        thisCurrent->next = new Node<T>();
        thisCurrent = thisCurrent->next;
        thisCurrent->prev = prv;
        current = current->next;
        thisCurrent->value = current->value;
    }
    thisCurrent->next = head;
    head->prev = thisCurrent;
}

template<typename T>
LinkedList<T>::LinkedList(LinkedList<T> &&other) noexcept : size(other.size), head(other.head){
    other.size = 0;
    other.head = nullptr;
}

template<typename T>
void LinkedList<T>::insert(Iterator<T> *it, const T &value) {
    auto *iter = dynamic_cast<ListIterator *>(it);
//    if (iter->parent != this)
//        throw std::logic_error("iterator from wrong parent");
    auto *newNode = new Node<T>(value, iter->position, iter->position->next);

    iter->position->next->prev = newNode;
    iter->position->next = newNode;

    size++;
}

template<typename T>
void LinkedList<T>::erase(Iterator<T> *it) {
    if (empty())
        throw std::logic_error("cannot erase from empty list");
    if (it->finish())
        throw std::logic_error("cannot erase from iterator that points to buffer element");

    auto *iter = dynamic_cast<ListIterator *>(it);
    Node<T> *pr = iter->position->prev;
    Node<T> *nx = iter->position->next;

    delete iter->position;
    pr->next = nx;
    nx->prev = pr;

    iter->position = nx;
    size--;
}

template<typename T>
inline size_t LinkedList<T>::length() const { return size; }

template<typename T>
inline bool LinkedList<T>::empty() const { return size == 0; }

template<typename T>
Iterator<T> *LinkedList<T>::begin() const {
    if (empty())
        return new ListIterator(*this);
    else {
        auto *a = new ListIterator(*this);
        a->next();
        return a;
    }
}

template<typename T>
void LinkedList<T>::make_empty() {
    if (!empty()) {
        Node<T> *curr = head->next;
        while (curr != head) {
            Node<T> *c = curr, *next = curr->next;
            c->prev = nullptr;
            c->next = nullptr;
            delete c;
            size--;
            curr = next;
        }
        head->next = head;
        head->prev = head;

    }

//    Node<T>* curr = head;
//    while (curr->ne)

}

template<typename T>
Iterator<T> *LinkedList<T>::find(const T &value) {
    if (!empty()) {
        auto it = dynamic_cast<ListIterator *>(this->begin());
        while (!it->finish()) {

            if (it->get() == value) {
                return it;
            }
            it->next();
        }
    }
    return nullptr;
}

template<typename T>
LinkedList<T>::LinkedList() {
    size = 0;
    head = new Node<T>;
    head->next = head;
    head->prev = head;
}

template<typename T>
LinkedList<T> &LinkedList<T>::operator=(const LinkedList<T> &other) {
    if (this == &other)
        return *this;
    make_empty();
    head = new Node<T>();
    Node<T> *thisCurrent = head;
    Node<T> *current = other.head;

    head->value = other.head->value;
    while (current->next != other.head) {
        Node<T> *prv = thisCurrent;
        thisCurrent->next = new Node<T>();
        thisCurrent = thisCurrent->next;
        thisCurrent->prev = prv;
        current = current->next;
        thisCurrent->value = current->value;
    }
    thisCurrent->next = head;
    return *this;
}

template<typename T>
LinkedList<T> &LinkedList<T>::operator=(LinkedList<T> &&other) noexcept {
    make_empty();
    size = other.size;
    head = other.head;

    other.size = 0;
    other.head = nullptr;
    return *this;
}


#endif //TASK3_LINKEDLIST_H

