//
// Created by light on 08.04.2023.
//

#ifndef TASK4_LINKED_HASHTABLE_H
#define TASK4_LINKED_HASHTABLE_H

#include <iostream>
#include <vector>
#include "..//task3//LinkedList.h"
#include "item.h"

template<class K, class V>
class linked_hashtable {
private:
    LinkedList<item<K, V>> iterable;

    std::vector<LinkedList<item<K, V>>> list;

    size_t size;
    size_t capacity;

    size_t hash(const K &key) const {
        return (std::hash<K>{}(key) + capacity) % capacity;
    };

public:


    linked_hashtable() : size(0), capacity(1000) {
        list = std::vector<LinkedList<item<K, V>>>(capacity, LinkedList<item<K, V>>());
    }

    explicit linked_hashtable(size_t capacity) : size(0), capacity(capacity) {
        list = std::vector<LinkedList<item<K, V>>>(capacity, LinkedList<item<K, V>>());
    }

    void insert(const K &key, const V &value) {
//        if (size * 100 >= 75 * capacity)
//            capacity *= 2;
        size_t index = hash(key);
        if (list[index].empty()) {
            list[hash(key)].insert(list[hash(key)].end(), item<K, V>(key, value));
            iterable.insert(iterable.end(), item<K, V>(key, value));
        } else {
            for (auto it = list[index].begin(); !it->finish(); it->next()) {
                if (it->get().key == key) {
                    auto itt = iterable.find(it->get());
                    iterable.erase(itt);
                    list[index].erase(it);
                    list[hash(key)].insert(list[hash(key)].end(), item<K, V>(key, value));
                    iterable.insert(iterable.end(), item<K, V>(key, value));
//                    iterable.print();
                    return;
                }
            }
            iterable.insert(list[hash(key)].end(), item<K, V>(key, value));
            list[hash(key)].insert(iterable.end(), item<K, V>(key, value));
        }
//        iterable.print();
        size++;
    };

    void erase(const V &key, const V &value) {
        size_t index = hash(key);

        if (list[index].length() != 0) {
            for (auto it = list[index].begin(); !it->finish(); it->next()) {
                if (it->get()->key == key && it->get()->value == value) {
                    list.erase(it, list[hash(key)].end());
                    auto itt = iterable.find(*it->get());
                    iterable.erase(itt);
                    size--;
                    break;
                }
            }
        }

    };

    V find(const K &key) const {
        size_t index = hash(key);

        if (list[index].length() != 0) {
            for (auto it = list[index].begin(); !it->finish(); it->next()) {
                if (it->get().key == key) {
                    return it->get().value;
                }
            }
        }
        throw std::invalid_argument("Cannot find element");
    };

    void make_empty() {
        for (auto &x: list) {
            x.make_empty();
        }
        size = 0;
    };

    bool empty() const {
        return size == 0;
    };

    size_t length() const { return size; }

    Iterator<item<K, V>> *begin() {
        return iterable.begin();
    }
};


#endif //TASK4_LINKED_HASHTABLE_H
