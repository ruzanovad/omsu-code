#include "BoxC.h"

namespace containers {
    BoxC::BoxC() {
        length = 0;
        width = 0;
        height = 0;
        weight = 0;
        value = 0;
    }
    BoxC::BoxC(int length, int width, int height, double weight, int value) {
        this->length = length;
        this->width = width;
        this->height = height;
        this->weight = weight;
        this->value = value;
    }

    std::ostream &operator<<(std::ostream &out, const BoxC &box) {
        out << "Box(length=" << box.length << ", width=" << box.width << ", height=" << box.height << ", weight="
            << box.weight << ", value=" << box.value << ")";
        return out;
    }

    std::istream &operator>>(std::istream &in, BoxC &box) {
        in >> box.length >> box.width >> box.height >> box.weight >> box.value;

        return in;
    }

    int BoxC::getLength() const {
        return length;
    }

    void BoxC::setLength(int v) {
        BoxC::length = v;
    }

    int BoxC::getWidth() const {
        return width;
    }

    void BoxC::setWidth(int v) {
        BoxC::width = v;
    }

    int BoxC::getHeight() const {
        return height;
    }

    void BoxC::setHeight(int v) {
        BoxC::height = v;
    }

    double BoxC::getWeight() const {
        return weight;
    }

    void BoxC::setWeight(double v) {
        BoxC::weight = v;
    }

    int BoxC::getValue() const {
        return value;
    }

    void BoxC::setValue(int v) {
        BoxC::value = v;
    }

    bool operator==(const BoxC &lhs, const BoxC &rhs) {
        return lhs.length == rhs.length &&
               lhs.width == rhs.width &&
               lhs.height == rhs.height &&
               lhs.weight == rhs.weight &&
               lhs.value == rhs.value;
    }
}
