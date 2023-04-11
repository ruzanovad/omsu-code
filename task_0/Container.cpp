//
// Created by light on 15.02.2023.
//
#include <ostream>
#include "vector"
#include "Container.h"

namespace containers{
    Container::Container(int length, int width, int height, double weightBound){
        this->width = width;
        this->length = length;
        this->height = height;
        this->weightBound =weightBound;
        this->boxes = std::vector<BoxC>();
    }

    size_t Container::countOfBoxes() {
        return boxes.size();
    }

    double Container::sumWeight() {
        double sum = 0;
        for (auto & box : boxes){
            sum+=box.getWeight();
        }
        return sum;
    }

    int Container::sumValue() {
        int sum = 0;
        for (auto & box : boxes){
            sum+=box.getValue();
        }
        return sum;
    }

    BoxC Container::getBox(int idx) {
        return boxes[idx];
    }

    std::ostream &operator<<(std::ostream &os, const Container &container) {
        os << "boxes: " << std::endl;

        if (!container.boxes.empty()){
            os << container.boxes[0];
            if (container.boxes.size() > 1){
                for (int i = 1; i < container.boxes.size(); ++i) {
                    os << ", " << container.boxes[i];
                }
            }
        }
        else{
            os << "empty";
        }

        os <<std::endl<< "length: " << container.length << ", width: " << container.width
           << ", height: " << container.height << ", weightBound: " << container.weightBound;
        return os;
    }
    std::istream &operator>>(std::istream &in, Container &container) {
        int count;
        in >> count;
        std::vector<BoxC> n(count);
        for (int i = 0; i < count; ++i){
            in >> n[i];
        }
        container.boxes = n;
        in >> container.length >> container.width >> container.height >> container.weightBound;

        return in;
    }

    BoxC& Container::operator[](int index) {
        return boxes[index];
    }

    int Container::addBox(BoxC box) {
        if (sumWeight()+ box.getWeight() <= weightBound){
            boxes.push_back(box);
            return boxes.size()-1;
        }
        else{
            throw InvalidWeightException(boxes.size());
        }
    }

    bool Container::removeBox(int index) {
        if (index < boxes.size() && index>= 0 && !boxes.empty()){
            boxes.pop_back();
            return true;
        }
        return false;
    }

    Container::Container() = default;

}
