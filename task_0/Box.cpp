#include <iostream>
#include "Box.h"

Box::Box() {
    this->length = 0;
    this->width = 0;
    this->height = 0;
    this->weight = 0;
    this->value = 0;
}

bool operator==(const Box &lhs, const Box &rhs) {
    return lhs.length == rhs.length &&
           lhs.width == rhs.width &&
           lhs.height == rhs.height &&
           lhs.weight == rhs.weight &&
           lhs.value == rhs.value;
}

Box::Box(int length, int width, int height, double weight, int value) {
    this->length = length;
    this->width = width;
    this->height = height;
    this->weight = weight;
    this->value = value;
}

int sumPrice(Box *boxes, int size) {
    int price = 0;
    for (int i = 0; i < size; ++i) {
        price += boxes[i].value;
    }
    return price;
}

//!
bool condition(Box *boxes, int size, int max) {
    bool cond = true;
    for (int i = 0; i < size && cond; ++i) {
        int sum = 0;
        sum += boxes[i].length;
        sum += boxes[i].width;
        sum += boxes[i].height;
        if (sum > max) {
            cond = false;
        }
    }
    return cond;
}

double getMaxWeight(Box *boxes, int size, int maxV) {
    double max = 0.0;
    for (int i = 0; i < size; ++i) {
        if (boxes[i].weight > max && boxes[i].height * boxes[i].width * boxes[i].length <= maxV) {
            max = boxes[i].weight;
        }
    }
    return max;
}

bool checkNesting(Box *boxes, int size) {
    int *mas = new int[size];
    for (int z = 0; z < size; ++z) {
        mas[z] = -1;
    }
    for (int i = 0; i < size; ++i) {
        int cnt = 0;
        for (int j = 0; j < size; ++j) {
            if (i != j &&
                boxes[i].height > boxes[j].height &&
                boxes[i].length > boxes[j].length &&
                boxes[i].width > boxes[j].width) {
                cnt += 1;
            }
        }
        if (mas[cnt] != -1) {
            return false;
        }
        mas[cnt] = cnt;
    }
    return true;
}

bool operator==(Box b1, Box b2) {
    return (b1.length == b2.length) &&
           (b1.width == b2.width) &&
           (b1.weight == b2.weight) &&
           (b1.value == b2.value) &&
           (b1.height == b2.height);
}

std::ostream &operator<<(std::ostream &out, const Box &box) {
    out << "Box(length=" << box.length << ", width=" << box.width << ", height=" << box.height << ", weight="
        << box.weight << ", value=" << box.value << ")";
    return out;
}

std::istream &operator>>(std::istream &in, Box &box) {
    in >> box.length >> box.width >> box.height >> box.weight >> box.value;

    return in;
}
