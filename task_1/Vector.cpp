#include "Vector.h"
#include <iostream>
#include <cstring>
#include <exception>

//#define DEBUG

#ifdef DEBUG
#define LOG(x) std::cerr << x << std::endl
#else
#define LOG(x)
#endif

Vector::Vector() {
    base = new int[size];
    for (int i = 0; i < size; ++i) {
        base[i] = 0;
    }
    LOG("default constructor");
}

Vector::Vector(unsigned size) {
    this->size = size;
    base = new int[size];

    for (int i = 0; i < size; ++i) {
        base[i] = 0;
    }
    LOG("constructor by size");
}

Vector::Vector(unsigned size, int value) {
    this->size = size;
    base = new int[size];

    for (int i = 0; i < size; ++i) {
        base[i] = value;
    }
    LOG("constructor by size and value");
}

Vector::Vector(unsigned size, unsigned cap, int value) {
    this->reserved = cap;
    this->size = size;
    base = new int[size + cap];

    for (int i = 0; i < size; ++i) {
        base[i] = value;
    }
    LOG("constructor by size, capacity, value");
}

Vector::Vector(const Vector &other) {
    this->size = other.size;
    this->reserved = other.reserved;
    base = new int[size + reserved];
//    memcpy(base, other.base, (size + reserved) * sizeof other);
    memcpy(base, other.base, (size + reserved) * sizeof(int));
//    for (int i = 0; i < size; ++i) {
//        base[i] = other.base[i];
//    }
    LOG("copy constructor");
}

Vector::Vector(Vector &&other) {
    this->size = other.size;
    this->reserved = other.reserved;
    base = other.base;
    other.base = nullptr;
    other.reserved = 0;
    other.size = 0;
    LOG("move constructor");
}

Vector::~Vector() {
    size = 0;
    reserved = 0;
    delete[] base;
    base = nullptr;
    LOG("destructor");
}

size_t Vector::length() const {
    return size;
}

bool Vector::operator==(const Vector &rhs) const {
    LOG("operator==");
    if (size != rhs.size)
        throw std::invalid_argument("Incorrect comparison");
    bool ret = true;
    for (int i = 0; i < size && ret; ++i) {
        ret *= (base[i] == rhs.base[i]);
    }

    return ret;
}

bool Vector::operator!=(const Vector &rhs) const {
    LOG("operator!=");
    return !(*this == rhs);
}

bool Vector::operator<(const Vector &rhs) const {
    LOG("operator<");
    bool ret = true;
    if (size == rhs.size && (rhs == *this)) {
        return false;
    }
    if (rhs.size == 0) return false;
    if (size == 0) return true;
    int i = 0;
    while (i < size && i < rhs.size && rhs.base[i] == base[i]) {
        i++;
    }
    if (i < size && i < rhs.size && rhs.base[i] != base[i]) {
        return (rhs.base[i] > base[i]);
    } else {
        return (i == size);
    }
}

bool Vector::operator>(const Vector &rhs) const {
    LOG("operator>");
    return (rhs < *this);
}

bool Vector::operator<=(const Vector &rhs) const {
    LOG("operator<=");
    bool ret = true;
    if (size == rhs.size && (rhs == *this)) {
        return true;
    }
    if (rhs.size == 0) return false;
    if (size == 0) return true;
    int i = 0;
    while (i < size && i < rhs.size && rhs.base[i] == base[i]) {
        i++;
    }
    if (i < size && i < rhs.size && rhs.base[i] != base[i]) {
        return (rhs.base[i] > base[i]);
    } else {
        return (i == size - 1);
    }
}

bool Vector::operator>=(const Vector &rhs) const {
    LOG("operator>=");
    return (rhs <= *this);
}

Vector &Vector::operator=(const Vector &rhs) {
    LOG("assigment operator");
    if (this == &rhs) {
        return *this;
    }
    delete[] base;

    this->size = rhs.size;
    this->reserved = rhs.reserved;
    base = new int[size];

    std::memcpy(base, rhs.base, size * sizeof(int));
//    for (int i = 0; i < size; ++i) {
//        base[i] = rhs.base[i];
//    }
    return *this;
}

void Vector::resize(unsigned int newSize) {

    if (newSize > reserved + size) {
        LOG("capacity = 0, re-copy array");
        reserved = 0;
        int *copy = new int[newSize];
        memcpy(copy, base, sizeof(int) * size);
//        for (int i = 0; i < size ; ++i){
//            copy[i] = this->base[i];
//        }
        for (unsigned i = size; i < newSize; ++i) {
            copy[i] = 0;
        }
        delete[] base;
        base = copy;
    } else if (newSize < size) {
        LOG("capacity is enlarged");
        reserved += size - newSize;
    } else if (newSize <= size + reserved && reserved > 0) {
        LOG("capacity is reduced");
        reserved -= newSize - size;
        for (unsigned i = size; i < newSize; ++i) {
            base[i] = 0;
        }
    } else {
        LOG("capacity has not changed");
    }
    size = newSize;
}

int &Vector::operator[](unsigned index) {
    LOG("operator[]");
    if (!empty() && index < size) {
        return base[index];
    }
    throw std::out_of_range("Invalid index");
}

const int &Vector::operator[](unsigned index) const {
    LOG("operator[]");
    if (!empty() && index < size) {
        return base[index];
    }
    throw std::out_of_range("Invalid index");
}

Vector Vector::operator+(const Vector &rhs) {
    LOG("operator+");
    unsigned newSize = size + rhs.size;
    Vector ret(newSize, reserved + rhs.reserved, 0);
    memcpy(ret.base, base, sizeof(int) * size);
    memcpy((ret.base + size), rhs.base, sizeof(int) * rhs.size);
//    for (int i = 0; i < size; ++i){
//        ret[i] = base[i];
//    }
//    for (int i = size; i < newSize; ++i){
//        ret[i] = rhs.base[i];
//    }
    return ret;
}

Vector &Vector::operator=(Vector &&rhs) {
    LOG("Move assignment operator");

    delete[] base;
    this->size = rhs.size;
    this->base = rhs.base;
    this->reserved = rhs.reserved;

    rhs.base = nullptr;
    rhs.size = 0;
    rhs.reserved = 0;
    return *this;
}

std::ostream &operator<<(std::ostream &os, const Vector &vector) {
    os << "size: " << vector.size << ", elements: ";
    if (vector.size != 0)
        os << vector.base[0];
    for (int i = 1; i < vector.size; ++i) {
        os << ", " << vector.base[i];
    }
    return os;
}

std::istream &operator>>(std::istream &in, Vector &vector) {
    in >> vector.size;
    for (int i = 0; i < vector.size; ++i) {
        in >> vector.base[i];
    }
    return in;
}

void Vector::reserve(unsigned int cap) {
    reserved = cap;
    int *newBase = new int[reserved + size];

    memcpy(newBase, base, size * sizeof base);
    delete[] base;
    base = newBase;
}

unsigned int Vector::capacity() const {
    return reserved + size;
}

void Vector::pushBack(int x) {
    resize(size + 1);
    base[size - 1] = x;

}

int Vector::popBack() {
    int x = base[size - 1];
    resize(size - 1);
    return x;
}

bool Vector::empty() const {
    return size == 0;
}

