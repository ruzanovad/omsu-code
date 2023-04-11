//
// Created by light on 30.03.2023.
//

#ifndef TASK4_HASHTABLE_H
#define TASK4_HASHTABLE_H
#include <iostream>
#include <vector>
#include "..//task3//LinkedList.h"
#include "item.h"

template<class K, class V>
class hashtable {
private:
    std::vector<LinkedList<item<K, V>>> list;
    size_t size;
    size_t capacity;
    size_t hash(const K& key) const{
        return (std::hash<K>{}(key)+capacity) % capacity;
    };
public:
    hashtable(): size(0), capacity(1000) {
        list = std::vector<LinkedList<item<K, V>>>(capacity, LinkedList<item<K, V>>());
    }

    explicit hashtable(size_t capacity): size(0), capacity(capacity){
        list = std::vector<LinkedList<item<K, V>>>(capacity, LinkedList<item<K, V>>());
    }

    void insert(const K& key, const V& value){
//        if (size*100>=75*capacity)
//            capacity*=2;
        size_t index = hash(key);
        if (list[index].empty()){
            list[hash(key)].insert(list[hash(key)].end(), item<K, V>(key, value));
        }
        else {
            for (auto it = list[index].begin(); !it->finish(); it->next()){
                if (it->get().key==key){
                    list[index].erase(it);
                    list[hash(key)].insert(list[hash(key)].end(), item<K, V>(key, value));
                    return;
                }
            }
            list[hash(key)].insert(list[hash(key)].end(), item<K, V>(key, value));
        }
        size++;
    };
    void erase(const V& key, const V& value){
        size_t index = hash(key);

        if (list[index].length() != 0){
            for (auto it = list[index].begin(); !it->finish(); it->next()){
                if (it->get()->key == key && it->get()->value == value){
                    list.erase(it, list[hash(key)].end());
                    size--;
                    break;
                }
            }
        }

    };
    V find(const K& key) const{
        size_t index = hash(key);

        if (list[index].length() != 0){
            for (auto it = list[index].begin(); !it->finish(); it->next()){
                if (it->get().key == key){
                    return it->get().value;
                }
            }
        }
        throw std::invalid_argument("Cannot find element");
    };
    void make_empty(){
        for (auto &x: list){
            x.make_empty();
        }
        size = 0;
    };
    inline bool empty() const{
        return size == 0;
    };
    inline size_t length() const { return size;}
};


#endif //TASK4_HASHTABLE_H
