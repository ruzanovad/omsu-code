#include "BoxC.h"
#include "InvalidWeightException.h"
#ifndef TASK0_CONTAINER_H
#define TASK0_CONTAINER_H

namespace containers{
    class Container {
    public:
        Container();
        Container(int length, int width, int height, double weightBound);
        int addBox(BoxC box);
        bool removeBox(int index);
        size_t countOfBoxes();
        double sumWeight();
        int sumValue();
        BoxC getBox(int idx);
        BoxC& operator[](int index);

        friend std::ostream &operator<<(std::ostream &, const Container &);
        friend std::istream & operator>> (std::istream&, Container&);

    private:
        std::vector<BoxC> boxes;
        int length{};
        int width{};
        int height{};
        double weightBound{};
    };

}


#endif //TASK0_CONTAINER_H
