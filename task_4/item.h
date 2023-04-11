//
// Created by light on 08.04.2023.
//

#ifndef TASK4_ITEM_H
#define TASK4_ITEM_H

#include <ostream>

template<class K, class V>
struct item {
    K key;
    V value;
    item() = default;
    item(K key, V value): key(key), value(value){};
//        bool operator==(item&& other){
//            return this->key == other.key && this->value ==  other.value;
//        }
    bool operator==(const item& other){
        return this->key == other.key && this->value ==  other.value;
    }

    friend std::ostream &operator<<(std::ostream &os, const item &item) {
        os << "key: " << item.key << " value: " << item.value;
        return os;
    }
};


#endif //TASK4_ITEM_H
